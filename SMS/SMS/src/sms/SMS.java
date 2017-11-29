package sms;


import java.sql.*; 
import javax.swing.JOptionPane;

public class SMS {
    public static void createTables() {
        String url = "jdbc:mysql://localhost:3306/smsDB";
        String username = "root";
        String password = "admin";
        
        String sql = "CREATE TABLE Items (\n" +
            "  itemNum int AUTO_INCREMENT ,\n" +
            "  itemName varchar(255),\n" +
            "  QoH int,\n" +
            "  PPU float(10,2),\n" +
            "  providerName varchar(255),\n" +
            "  providerEmail varchar(255),\n" +
            "  PRIMARY KEY(itemNum)\n" +
            ");";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "INSERT INTO Items ()\n" +
            "   VALUES\n" +
            "   (1, 'Lettuce', 57, 1.50, 'Produce', 'Produce@publix.org'),\n" +
            "   (2, 'Spinach', 20, 2.50, 'Produce', 'Produce@publix.org'),\n" +
            "   (3, 'Chicken Breast', 178, 4.99, 'Meat', 'Meat@publix.org'),\n" +
            "   (4, 'Lean Ground Beef', 587, 5.99, 'Meat', 'Meat@publix.org'),\n" +
            "   (5, 'Canned Black Beans', 23, 0.59, 'Grocery', 'Grocery@publix.org'); ";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE TABLE ItemLists (\n" +
            "  listNum int,\n" +
            "  itemNum int,\n" +
            "  itemQuantity int,\n" +
            "  subTotal float(10,2),\n" +
            "  PRIMARY KEY(listNum,itemNum),\n" +
            "  FOREIGN KEY (itemNum) REFERENCES Items(itemNum)\n" +
            "  );";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "INSERT INTO ItemLists ()\n" +
            "    VALUES\n" +
            "    (1, 1, 15, 22.50),\n" +
            "    (1, 2, 4, 10.00),\n" +
            "    (2, 1, 1, 1.50),\n" +
            "    (2, 2, 1, 2.50),\n" +
            "    (2, 3, 1, 4.99),\n" +
            "    (2, 4, 1, 5.99),\n" +
            "    (2, 5, 2, 1.18);";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE TABLE Orders (\n" +
            "    orderNum int AUTO_INCREMENT,\n" +
            "    total float(20,2) DEFAULT 0.00,\n" +
            "    customerName varchar(255),\n" +
            "    customerNum varchar(255),\n" +
            "    datePlaced date,\n" +
            "    dateFilled date,\n" +
            "    listNum int,\n" +
            "    PRIMARY KEY (orderNum)\n" +
            "    );";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "INSERT INTO Orders ()\n" +
            "      Values\n" +
            "      (1, 32.50, 'James', '706.331.4127', '2017-09-29', NULL, 1),\n" +
            "      (2, 78.50, 'Jack', '706.331.4128', '2017-09-17', '2017-09-20', NULL),\n" +
            "      (3, 16.16, 'John', '706.331.4129', '2017-09-25', NULL, 2),\n" +
            "      (4, 5.32, 'Josh', '706.331.4130', '2017-09-02', '2017-09-17', NULL),\n" +
            "      (5, 27.50, 'Jamie', '706.331.4131', '2017-09-18', '2017-09-19', NULL);";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE TABLE Employees (\n" +
            "  employeeID int AUTO_INCREMENT ,\n" +
            "  name varchar(255),\n" +
            "  jobclass varchar(255),\n" +
            "  username varchar(255),\n" +
            "  password varchar(255),\n" +
            "  PRIMARY KEY(employeeID)\n" +
            ");";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "INSERT INTO Employees ()\n" +
            "   VALUES\n" +
            " (1,'Manager','Manager','Manager','admin'),\n" +
            " (2,'Employee','Employee','Employee','admin');";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE TABLE transactions(\n" +
            "   transactionID int AUTO_INCREMENT,\n" +
            "   itemName varchar(255),\n" +
            "   quantity int,\n" +
            "   cost float(10,2),\n" +
            "   sold date,\n" +
            "   PRIMARY KEY(transactionID)\n" +
            "   );";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE PROCEDURE view_item(IN vNum int)\n" +
            "BEGIN\n" +
            "  SELECT *\n" +
            "  FROM Items\n" +
            "  Where itemNum = vNum;\n" +
            "END;";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE PROCEDURE add_item(IN vName varchar(255),IN vQoH int, IN vPrice float(10,2), IN vProvName varchar(255), IN vProvEmail varchar(255))  \n" +
            "BEGIN\n" +
            "  INSERT INTO Items(itemName, QoH, PPU, providerName, providerEmail)\n" +
            "  VALUES (vName, vQoH, vPrice, vProvname, vProvemail);\n" +
            "END;";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
                
        sql = "CREATE PROCEDURE edit_item(IN vNum int, IN vName varchar(255),IN vQoH int, IN vPrice float(10,2), IN vProvName varchar(255), IN vProvEmail varchar(255))\n" +
            "BEGIN\n" +
            "  UPDATE Items\n" +
            "  SET itemName = vName, QoH = vQoH, PPU = vPrice, providerName = vProvName, providerEmail = vProvEmail\n" +
            "  WHERE itemNum = vNum;\n" +
            "END;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        sql = "CREATE PROCEDURE  add_item_to_order(IN lNum int, IN iNum int, IN iQuantity int)\n" +
            "BEGIN\n" +
            "    INSERT INTO ItemLists(listNum, itemNum, itemQuantity)\n" +
            "    VALUES(lnum, iNum, iQuantity);\n" +
            "    \n" +
            "    UPDATE ItemLists\n" +
            "    SET subTotal = (iQuantity*(SELECT PPU FROM Items WHERE itemNum = iNum))\n" +
            "    WHERE itemNum = iNum AND listNum = lNum;\n" +
            "           \n" +
            "    UPDATE Orders\n" +
            "    SET total=(total + (SELECT subTotal FROM ItemLists WHERE itemNum = iNum))\n" +
            "    WHERE listNum = lNum;   \n" +
            "END; ";

            try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            } catch (Exception e) {e.printStackTrace();}
            
            sql = "CREATE PROCEDURE create_order(IN cName varchar(255), IN cNum varchar(255))\n" +
                "BEGIN\n" +
                "	INSERT INTO Orders(customerName, customerNum, datePlaced)\n" +
                "    Values(cName, cNum, CURDATE());\n" +
                "    \n" +
                "    UPDATE Orders\n" +
                "    SET listNum = ((SELECT MAX(listNum) FROM ItemLists) + 1)\n" +
                "    WHERE customerName = cName;\n" +
                "   \n" +
                "END;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
}

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost";
        String username = "root";
        String password = "admin";

        // SQL command to create a database in MySQL.
        String sql = "CREATE DATABASE IF NOT EXISTS smsDB";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {e.printStackTrace();}
        
        url = "jdbc:mysql://localhost:3306/smsDB";
        createTables();

                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(baseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(baseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(baseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(baseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        String uname = JOptionPane.showInputDialog("Enter User Name");
        String pword = JOptionPane.showInputDialog("Enter Password");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new baseFrame().setVisible(true);
            }
        });
    }
}
