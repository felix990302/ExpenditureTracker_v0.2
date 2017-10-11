/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.*;
import Accessors.Accessor;
import Objects.Consumer;
import Objects.Spending;
import java.util.ArrayList;

/**
 *
 * @author chang
 */
public class Model {

    public static void updateExp(int id, String type, double amount) {

        Connection connection = null;
        PreparedStatement statement = null;

        String insert = "insert into expenditure (userID,amount,form) values (?,?,?);";

        try {
            connection = Accessor.getConnection();
            statement = connection.prepareStatement(insert);

            statement.setInt(1, id);
            statement.setDouble(2, amount);
            statement.setString(3, type);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public static ArrayList<Spending> getExpenditure(int id) throws SQLException {
        ArrayList<Spending> temp = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;

        String query = "select* from expenditure where userID="
                + id + ";";

        try {
            connection = Accessor.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (rs.next()) {
            double amountTemp = rs.getDouble(2);
            String typeTemp = rs.getString(3);
            String dateTemp = rs.getString(4);

            Spending spendingTemp = new Spending(amountTemp, typeTemp, dateTemp);
            temp.add(spendingTemp);
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return temp;
    }

    public static boolean newConsumer(String username, String password) {

        Connection connection = null;
        PreparedStatement statement = null;

        String insert = "insert into users (username, pword) values (?,?);";
        int test = 0;

        try {
            connection = Accessor.getConnection();
            statement = connection.prepareStatement(insert);
            statement.setString(1, username);
            statement.setString(2, password);
            test = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return test > 0;
    }

    public static Consumer getConsumer(String username, String password) {
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;

        Consumer consumer = null;
        String query = "select* from users where username='"
                + username + "' and pword='" + password + "';";

        try {
            connection = Accessor.getConnection();
            statement = connection.createStatement();
            System.out.println("Creating statement...");
            rs = statement.executeQuery(query);

            if (rs.next()) {
                consumer = new Consumer();
                consumer.setConsID(rs.getInt("userID"));
                consumer.setConsName(rs.getString("username"));
                consumer.setConsPassword(rs.getString("pword"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return consumer;

    }

}
