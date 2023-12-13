import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
// Add this import statement at the beginning of your Java code
import java.util.regex.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SerialReader {
    private static SerialPort serialPort;
    private static Connection connection;
    int id;

    private enum Column {
        DEVICEUID, VOLTAGE_1N, VOLTAGE_2N, VOLTAGE_3N, VOLTAGE_N, VOLTAGE_12, VOLTAGE_23, VOLTAGE_31, VOLTAGE_L, CURRENT_1,
        CURRENT_3, AVG_CURRENT, KW_1, KW_2, KW_3, KVAR_1, KVAR_2, KVAR_3, KVA_1, KVA_2, CURRENT_2, KVA_3,
        PF_1, PF_2, PF_3, PF, FREQ, KW, KVAR, KVA, MAX_KW, MIN_KW, MAX_KVAR, MIN_KVAR, MAX_KVA, MAX_INT_V1N,
        MAX_INT_V2N, MAX_INT_V3N, MAX_INT_V12, MAX_INT_V23, MAX_INT_V31, MAX_INT_I1, MAX_INT_I2, MAX_INT_I3,
        IMP_KWH, EXP_KWH, KWH, IMP_KVARH, EXP_KVARH, KVARH, KVAH, RUN_H, ON_H, THD_V1N, THD_V2N, THD_V3N, THD_V12,
        THD_V23, THD_V31, THD_I1, THD_I2, THD_I3
    }


    public static void main(String[] args) {
        // Serial port initialization
        String portName = "COM7";
        serialPort = new SerialPort(portName);


        // Database connection details
        String url = "jdbc:mysql://localhost:3306/energydb";
        String username = "root";
        String password = "0000";

        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );

            connection = DriverManager.getConnection(url, username, password);
            // Inside the main class
            StringBuilder serialDataBuffer = new StringBuilder();

            serialPort.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                        try {
                            String data = serialPort.readString(serialPortEvent.getEventValue());
                            serialDataBuffer.append(data);
                            System.out.println("Received data: " + data);
//                            insertIntoDatabase(processReading(serialDataBuffer.toString()));



                            // Check if the received data contains the delimiter
                            if (serialDataBuffer.toString().contains("---")) {
                                // Process data and insert into the database
                                insertIntoDatabase(processReading(serialDataBuffer.toString()));

                                // Clear the buffer after processing the data
                                serialDataBuffer.setLength(0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });



        } catch (SerialPortException | SQLException e) {
            e.printStackTrace();
        }
    }


    private static Map<Column, String> processReading(String data) {
        Map<Column, String> readings = new HashMap<>();

        String[] lines = data.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (!line.isEmpty() && line.contains(":")) {
                String[] parts = line.split(":", 2); // Limit the split to 2 parts
                if (parts.length == 2) {
                    String key = parts[0].trim().toUpperCase().replace("-", "_"); // Adjust key format
                    String value = parts[1].trim();

                    try {
                        Column enumKey = Column.valueOf(key);
                        readings.put(enumKey, value);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid column name: " + key);
                    }
                } else {
                    System.err.println("Invalid reading format: " + line);
                    System.err.println("Unexpected data: " + line);

                }
            } else {
                System.err.println("Invalid reading format: " + line);
            }
        }

        return readings;
    }

    private static void insertIntoDatabase(Map<Column, String> readings) {

        int ID = 0;
        String sql = "INSERT INTO energydb.energyreading " +
                "(ID, DEVICEUID, VOLTAGE_1N, VOLTAGE_2N, VOLTAGE_3N, VOLTAGE_N, VOLTAGE_12, VOLTAGE_23, VOLTAGE_31, VOLTAGE_L, " +
                "CURRENT_1, CURRENT_3, AVG_CURRENT, KW_1, KW_2, KW_3, KVAR_1, KVAR_2, KVAR_3, KVA_1, KVA_2, CURRENT_2, KVA_3, " +
                "PF_1, PF_2, PF_3, PF, FREQ, KW, KVAR, KVA, MAX_KW, MIN_KW, MAX_KVAR, MIN_KVAR, MAX_KVA, MAX_INT_V1N, " +
                "MAX_INT_V2N, MAX_INT_V3N, MAX_INT_V12, MAX_INT_V23, MAX_INT_V31, MAX_INT_I1, MAX_INT_I2, MAX_INT_I3, " +
                "IMP_KWH, EXP_KWH, KWH, IMP_KVARH, EXP_KVARH, KVARH, KVAH, RUN_H, ON_H, THD_V1N, THD_V2N, THD_V3N, THD_V12, " +
                "THD_V23, THD_V31, THD_I1, THD_I2, THD_I3) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, readings.get(Column.DEVICEUID) != null ? readings.get(Column.DEVICEUID).toString() : null);
            preparedStatement.setString(3, readings.get(Column.VOLTAGE_1N));
            preparedStatement.setString(4, readings.get(Column.VOLTAGE_2N));
            preparedStatement.setString(5, readings.get(Column.VOLTAGE_3N));
            preparedStatement.setString(6, readings.get(Column.VOLTAGE_N));
            preparedStatement.setString(7, readings.get(Column.VOLTAGE_12));
            preparedStatement.setString(8, readings.get(Column.VOLTAGE_23));
            preparedStatement.setString(9, readings.get(Column.VOLTAGE_31));
            preparedStatement.setString(10, readings.get(Column.VOLTAGE_L));
            preparedStatement.setString(11, readings.get(Column.CURRENT_1));
            preparedStatement.setString(12, readings.get(Column.CURRENT_3));
            preparedStatement.setString(13, readings.get(Column.AVG_CURRENT));
            preparedStatement.setString(14, readings.get(Column.KW_1));
            preparedStatement.setString(15, readings.get(Column.KW_2));
            preparedStatement.setString(16, readings.get(Column.KW_3));
            preparedStatement.setString(17, readings.get(Column.KVAR_1));
            preparedStatement.setString(18, readings.get(Column.KVAR_2));
            preparedStatement.setString(19, readings.get(Column.KVAR_3));
            preparedStatement.setString(20, readings.get(Column.KVA_1));
            preparedStatement.setString(21, readings.get(Column.KVA_2));
            preparedStatement.setString(22, readings.get(Column.KVA_3));
            preparedStatement.setString(23, readings.get(Column.CURRENT_2));
            preparedStatement.setString(24, readings.get(Column.PF_1));
            preparedStatement.setString(25, readings.get(Column.PF_2));
            preparedStatement.setString(26, readings.get(Column.PF_3));
            preparedStatement.setString(27, readings.get(Column.PF));
            preparedStatement.setString(28, readings.get(Column.FREQ));
            preparedStatement.setString(29, readings.get(Column.KW));
            preparedStatement.setString(30, readings.get(Column.KVAR));
            preparedStatement.setString(31, readings.get(Column.KVA));
            preparedStatement.setString(32, readings.get(Column.MAX_KW));
            preparedStatement.setString(33, readings.get(Column.MIN_KW));
            preparedStatement.setString(34, readings.get(Column.MAX_KVAR));
            preparedStatement.setString(35, readings.get(Column.MIN_KVAR));
            preparedStatement.setString(36, readings.get(Column.MAX_KVA));
            preparedStatement.setString(37, readings.get(Column.MAX_INT_V1N));
            preparedStatement.setString(38, readings.get(Column.MAX_INT_V2N));
            preparedStatement.setString(39, readings.get(Column.MAX_INT_V3N));
            preparedStatement.setString(40, readings.get(Column.MAX_INT_V12));
            preparedStatement.setString(41, readings.get(Column.MAX_INT_V23));
            preparedStatement.setString(42, readings.get(Column.MAX_INT_V31));
            preparedStatement.setString(43, readings.get(Column.MAX_INT_I1));
            preparedStatement.setString(44, readings.get(Column.MAX_INT_I2));
            preparedStatement.setString(45, readings.get(Column.MAX_INT_I3));
            preparedStatement.setString(46, readings.get(Column.IMP_KWH));
            preparedStatement.setString(47, readings.get(Column.EXP_KWH));
            preparedStatement.setString(48, readings.get(Column.KWH));
            preparedStatement.setString(49, readings.get(Column.IMP_KVARH));
            preparedStatement.setString(50, readings.get(Column.EXP_KVARH));
            preparedStatement.setString(51, readings.get(Column.KVARH));
            preparedStatement.setString(52, readings.get(Column.KVAH));
            preparedStatement.setString(53, readings.get(Column.RUN_H));
            preparedStatement.setString(54, readings.get(Column.ON_H));
            preparedStatement.setString(55, readings.get(Column.THD_V1N));
            preparedStatement.setString(56, readings.get(Column.THD_V2N));
            preparedStatement.setString(57, readings.get(Column.THD_V3N));
            preparedStatement.setString(58, readings.get(Column.THD_V12));
            preparedStatement.setString(59, readings.get(Column.THD_V23));
            preparedStatement.setString(60, readings.get(Column.THD_V31));
            preparedStatement.setString(61, readings.get(Column.THD_I1));
            preparedStatement.setString(62, readings.get(Column.THD_I2));
            preparedStatement.setString(63, readings.get(Column.THD_I3));

            // Execute the SQL update
            preparedStatement.executeUpdate();

            // Execute the SQL update
            int rowsAffected = preparedStatement.executeUpdate();

//            // Commit the changes
//            connection.commit();
            // Add a delay thread for 1 minute
            // Start a separate thread for the 1-minute delay
            Thread delayThread = new Thread(() -> {
                try {
                    Thread.sleep(60000); // 1 minute delay
//                    serialPort.closePort(); // Close the serial port after the delay
                    System.out.println("1 minute delay completed. Serial port closed.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            delayThread.start();

            // Retrieve the generated keys, if needed
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    // Do something with the generated ID, if needed
                }
            }

            System.out.println(rowsAffected + " row(s) affected.");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}