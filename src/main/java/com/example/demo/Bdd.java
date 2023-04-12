package com.example.demo;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public abstract class Bdd {

    static Connection dbConnection;

    public Bdd(Connection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void openDBConnection(String url, String user, String password) {
        JdbcDataSource dataSource = new JdbcDataSource();

        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        try {
            dbConnection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void clearDB() {
        try {
            Statement stmt = dbConnection.createStatement();

            stmt.executeUpdate("DELETE FROM Utilisateur;");
            stmt.executeUpdate("DELETE FROM File_format;");
            stmt.executeUpdate("DELETE FROM CSV_input;");
            stmt.executeUpdate("DELETE FROM CSV_output;");
            stmt.executeUpdate("DELETE FROM Coefficient_friction;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display() {

        Collection<String> table = retrieve();

        for (String s : table) {
            System.out.println(s);
        }
    }

    public abstract Collection<String> retrieve();

    public abstract void insert(String[] columns);





}
