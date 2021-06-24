package testingDatabaseConnectivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AuthorFrame extends JFrame {

    JTextField inputFirstName;
    JTextField inputLastName;
    JLabel listAuthor;

    public AuthorFrame() {
        super("Author Records");
        setLayout(new FlowLayout());

        inputFirstName = new JTextField();
        inputLastName = new JTextField();
        inputFirstName.setColumns(10);
        inputLastName.setColumns(10);

        JLabel labelFirstName = new JLabel();
        JLabel labelLastName = new JLabel();
        listAuthor = new JLabel();

        labelFirstName.setText("Enter First Name :");
        labelLastName.setText("Enter Last Name :");

        JButton submit = new JButton();
        JButton fetAllData = new JButton();
        submit.setText("submit");
        fetAllData.setText("Get All Author");

        add(labelFirstName);
        add(inputFirstName);
        add(labelLastName);
        add(inputLastName);
        add(submit);
        add(fetAllData);
        add(listAuthor);

        EventHandler handler = new EventHandler();
        submit.addActionListener( handler );
        fetAllData.addActionListener( handler );
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            /* To insert data given by Input field */
            if (actionEvent.getActionCommand() == "submit") {
                String firstName = inputFirstName.getText();
                String lastName = inputLastName.getText();

                if (firstName.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please Enter first Name");

                if (lastName.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please Enter last Name");

                try {

                    String url = "jdbc:mysql://localhost:3306/mysql";
                    Connection connection = DriverManager.getConnection(url, "root", "root");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("INSERT INTO books.authors (id, first_name, last_name) values (null, '"+firstName+"', '"+lastName+"')");
                    inputFirstName.setText("");
                    inputLastName.setText("");
                    JOptionPane.showMessageDialog(null, firstName + " " + lastName + " Inserted Successfully");

                } catch (Exception exception) {
                    System.out.println("Connection Error ... !");
                    System.out.println(exception.getMessage());
                }
            }

            if (actionEvent.getActionCommand() == "Get All Author"){
                System.out.println("Get all the author data");

                try{
                    String url = "jdbc:mysql://localhost:3306/mysql";
                    Connection connection = DriverManager.getConnection(url, "root", "root");
                    System.out.println("Connecting database ...");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet;

                    resultSet = statement.executeQuery("SELECT * FROM books.authors");
                   while (resultSet.next()){
                       System.out.println("Id : " + resultSet.getInt("id"));
                        System.out.println("First Name : " + resultSet.getString("first_name"));
                        System.out.println("------------------------");
                    }
                    connection.close();
                }

                catch (Exception exception){
                    System.err.println("Got an exception !");
                    System.err.println(exception.getMessage());
                }
            }
        }
    }

}
