package com.cybertek.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Finder {
    public static void main(String[] args) throws Exception {
        //scanner for entry
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the value you are looking for :");
        String searchObject = scan.next();

        //connection info
        String url = "jdbc:postgresql://54.148.96.210:5432/BriteErpDemo";
        String user ="podoo";
        String pass ="podoo";
        createConnection(url, user, pass);

        //create table list for tables
        List<String> tables = new ArrayList<>();

        //database meta data for getting metadata from db such as all table names
        DatabaseMetaData dbmd = connection.getMetaData();

        //this code is getting all table data got this info from google
        String[] types = {"TABLE"};
        resultSet = dbmd.getTables(null, null, "%", types);

        //this code assign all table values to table list
        while (resultSet.next()) {
            tables.add(resultSet.getString("TABLE_NAME"));
        }

        //here all heavy work
        for(int i=0;i<tables.size();i++) {
            //creating list for column key names in table
            List<Object>columns = new ArrayList<>();

            //execute query which gets all columns in i.index table
            executeQuery("SELECT * FROM "+tables.get(i));

            //creating and  resultSetMetaData and assigning our last query result to it.
            ResultSetMetaData rsmd = resultSet.getMetaData();

            //here just printing the name to see results is keep going and not freeze
            System.out.println("===========PROGRESS ON===================="+tables.get(i));

            //creating new arraylist for rows for every cell.Its two list because method return is two list.
            List<List<Object>> rows = new ArrayList<>();

            for(int j=1;j<= rsmd.getColumnCount();j++) {
                //here we are adding all column Key names to list we created.
                columns.add(rsmd.getColumnName(j));

                //we are assigning rows list with getQueryResultList method.
                rows = getQueryResultList("SELECT \""+columns.get(j-1)+"\" FROM "+tables.get(i)+";");

                for(int l=0;l<rows.size();l++) {
                    //check if that cell contains searching value.
                    if((rows.get(l).get(0)+"").contains(searchObject))
                        System.out.println("***********************************************FOUND***********************************************\n+"
                                + "Select "+columns.get(j-1) + " FROM " + tables.get(i) + "\n" +
                                "***********************************************FOUND***********************************************");
                }
            }
        }

        System.out.println("END HERE");

        scan.close();
        destroy();
    }

    public static Connection connection;
    //to execute query
    public static Statement statement;
    //to get actoual data from the database
    public static ResultSet resultSet;

    public static void createConnection(String url,String user,String password) {
        //connect to the data base
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void destroy() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rowList;
    }

    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
