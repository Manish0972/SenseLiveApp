
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author manis
 */
public class display extends javax.swing.JFrame {

    /**
     * Creates new form display
     */
    public display() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        portbox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        baudratebox2 = new javax.swing.JComboBox<>();
        paritybox1 = new javax.swing.JComboBox<>();
        stopbitbox = new javax.swing.JComboBox<>();
        checkconnbtn = new javax.swing.JButton();
        connectbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" SenseLive - Connecting things");
        setMinimumSize(new java.awt.Dimension(1500, 800));
        setPreferredSize(new java.awt.Dimension(1500, 800));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        jLabel1.setText("R-Sense device panel ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(420, 20, 380, 40);

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 320, 1170, 350);

        jLabel3.setFont(new java.awt.Font("Castellar", 1, 12)); // NOI18N
        jLabel3.setText("COMPORT : ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 130, 90, 30);

        jLabel5.setFont(new java.awt.Font("Castellar", 1, 12)); // NOI18N
        jLabel5.setText("parity:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(980, 130, 70, 30);

        portbox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "COM13", "COM14", "COM15", "COM16", "COM17", "COM18", "COM19", "COM20", "COM21", "COM22", "COM23", "COM24", "COM25" }));
        portbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portbox1ActionPerformed(evt);
            }
        });
        getContentPane().add(portbox1);
        portbox1.setBounds(120, 130, 180, 26);

        jLabel6.setFont(new java.awt.Font("Castellar", 1, 12)); // NOI18N
        jLabel6.setText("BAUDRATE :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(340, 130, 90, 30);

        jLabel7.setFont(new java.awt.Font("Castellar", 1, 12)); // NOI18N
        jLabel7.setText("stopbit:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(680, 130, 70, 30);

        baudratebox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "110", "300", "600", "1200", "1800", "2400", "4800", "7200", "9600", "14400", "19200", "28800", "38400", "57600", "76800", "115200", "230400" }));
        baudratebox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baudratebox2ActionPerformed(evt);
            }
        });
        getContentPane().add(baudratebox2);
        baudratebox2.setBounds(440, 130, 180, 26);

        paritybox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "even", "odd" }));
        paritybox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paritybox1ActionPerformed(evt);
            }
        });
        getContentPane().add(paritybox1);
        paritybox1.setBounds(1050, 130, 180, 26);

        stopbitbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        stopbitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopbitboxActionPerformed(evt);
            }
        });
        getContentPane().add(stopbitbox);
        stopbitbox.setBounds(750, 130, 180, 26);

        checkconnbtn.setText("check connection");
        checkconnbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkconnbtnActionPerformed(evt);
            }
        });
        getContentPane().add(checkconnbtn);
        checkconnbtn.setBounds(680, 220, 160, 50);

        connectbtn.setText("connect");
        connectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectbtnActionPerformed(evt);
            }
        });
        getContentPane().add(connectbtn);
        connectbtn.setBounds(900, 220, 160, 50);

        pack();
    }// </editor-fold>

    private void portbox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String port = (String)portbox1.getSelectedItem();
    }

    private void baudratebox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        String baudrate = (String)baudratebox2.getSelectedItem();
    }

    private void paritybox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        String parity = (String)paritybox1.getSelectedItem();
    }

    private void stopbitboxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int stopbit = (int)stopbitbox.getSelectedItem();
    }

    private void checkconnbtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            String selectedPort = (String) portbox1.getSelectedItem();
            String portName = selectedPort;

            SerialPort testPort = new SerialPort(portName);
            testPort.openPort();
            testPort.closePort();

            // If opening and closing the port did not throw an exception, display success message
            JOptionPane.showMessageDialog(this, "Connection checked successfully!", "Connection Status", JOptionPane.INFORMATION_MESSAGE);

        } catch (SerialPortException e) {
            // If an exception occurs, display an error message
            JOptionPane.showMessageDialog(this, "Error checking the connection. Please check the port and try again.", "Connection Status", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void connectbtnActionPerformed(java.awt.event.ActionEvent evt) {
        // Get selected values from JComboBoxes
        String selectedPort = (String) portbox1.getSelectedItem();
        String selectedBaudRate = (String) baudratebox2.getSelectedItem();
        String selectedParity = (String) paritybox1.getSelectedItem();
        String selectedStopBit = (String) stopbitbox.getSelectedItem();

        // Convert selected values to appropriate types
        int baudRate = Integer.parseInt(selectedBaudRate);
        int stopBit = Integer.parseInt(selectedStopBit);

        // Set the selected port
        String portName = selectedPort;

        try {
            // Use the existing static serialPort field to set serial port parameters
            serialPort = new SerialPort(portName);
            // Open the serial port
            serialPort.openPort();
            serialPort.setParams(
                    baudRate,
                    SerialPort.DATABITS_8,
                    stopBit == 1 ? SerialPort.STOPBITS_1 : SerialPort.STOPBITS_2,
                    selectedParity.equalsIgnoreCase("none") ? SerialPort.PARITY_NONE :
                            selectedParity.equalsIgnoreCase("even") ? SerialPort.PARITY_EVEN : SerialPort.PARITY_ODD
            );

            // Display a message dialog indicating successful connection
            JOptionPane.showMessageDialog(this, "Serial port connected successfully to " + portName + "!", "Connection Status", JOptionPane.INFORMATION_MESSAGE);


            // Add an event listener to start reading data when data is available
            serialPort.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                        try {
                            String data = serialPort.readString(serialPortEvent.getEventValue());
                            // Process and display the data as needed
                            SwingUtilities.invokeLater(() -> {
                                Document doc = jTextPane1.getDocument();
                                try {
                                    doc.insertString(doc.getLength(), data, null);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            });
                            // Insert data into the database if needed
                            insertIntoDatabase(processReading(data));
                        } catch (SerialPortException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        } catch (SerialPortException e) {
            e.printStackTrace();
            // Display an error message if there is an issue with connecting to the serial port
//            JOptionPane.showMessageDialog(this, "Error connecting to the serial port. Please check the port and try again.", "Connection Status", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Error connecting to the serial port: " + e.getMessage(), "Connection Status", JOptionPane.ERROR_MESSAGE);

        }
    }



    //==================================================================================================================
    private static SerialPort serialPort;
    private static Connection connection;
    int id;

    private enum Column { DeviceUID, meterID,flowRate,totalVolume    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */


        // Database connection details
        String url = "jdbc:mysql://localhost:3306/rsense";
        String username = "root";
        String password = "0000";

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch ( SQLException e) {
            e.printStackTrace();
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new display().setVisible(true);
            }
        });

    }


    private static Map<display.Column, String> processReading(String data) {
        Map<display.Column, String> readings = new HashMap<>();

        String[] lines = data.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (!line.isEmpty() && line.contains(":")) {
                String[] parts = line.split(":", 2); // Limit the split to 2 parts
                if (parts.length == 2) {
                    String key = parts[0].trim().toUpperCase().replace("-", "_"); // Adjust key format
                    String value = parts[1].trim();

                    try {
                        display.Column enumKey = display.Column.valueOf(key);
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

    private static void insertIntoDatabase(Map<display.Column, String> readings) {

        int ID = 0;
        String sql = "INSERT INTO rsense.actual_data ( DeviceUID, flowRate, totalVolume,TimeStamp) VALUES(?, ?, ?,NOW() );";



        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
//            preparedStatement.setInt(1, ID);//
            preparedStatement.setString(1, readings.get(display.Column.DeviceUID) != null ? readings.get(display.Column.DeviceUID).toString() : null);

            preparedStatement.setString(2, readings.get(Column.flowRate));
            preparedStatement.setString(3, readings.get(Column.totalVolume));
//            preparedStatement.setString(3, readings.get(display.Column.meterID));

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
                    Thread.sleep(5000); // 5 second delay
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



    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> baudratebox2;
    private javax.swing.JButton checkconnbtn;
    private javax.swing.JButton connectbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextPane jTextPane1;
    private javax.swing.JComboBox<String> paritybox1;
    private javax.swing.JComboBox<String> portbox1;
    private javax.swing.JComboBox<String> stopbitbox;
    // End of variables declaration
}