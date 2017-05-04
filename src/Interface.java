/**
 * Created by juliadressel on 5/4/17.
 */

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.Date;


public class Interface {

    public static final int EDITOR = 1;
    public static final int AUTHOR = 2;
    public static final int REVIEWER = 3;

    public static Connection conn;

    public static void main (String args[]) throws IOException {


        // start by connecting to the database
        if (!connect_to_DB()){
            System.out.println("Error connecting to the database.");
        } else {
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to the Journal of E-commerce Research Knowledge");


            boolean exit = false;
            boolean user_type_set = false;


            int user_type = 0;

            while (!exit){

                while (user_type_set == false){
                    System.out.print("Are you an editor, author, or reviewer? ");
                    String type = sc.nextLine();

                    if (type.equals("editor")){
                        user_type = EDITOR;
                        user_type_set = true;

                        exit = editor_interface(sc);
                    }
                    else if (type.equals("author")){
                        user_type = AUTHOR;
                        user_type_set = true;

                        exit = author_interface(sc);
                    }
                    else if (type.equals("reviewer")){
                        user_type = REVIEWER;
                        user_type_set = true;

                        exit = reviewer_interface(sc);
                    }
                    else{
                        System.out.println("ERROR: Invalid user type.");
                    }
                }

            }
        }
    }

    public static boolean editor_interface(Scanner sc){
        boolean logged_in = false;
        boolean exit = false;
        String cmd;

        while (logged_in == false){

            System.out.println("To login, type 'login <id>' with your user ID.");
            System.out.println("To register as a new editor, type 'register editor <fname> <lname>': ");

            cmd = sc.nextLine();

            if (cmd.length() >= 8 && cmd.substring(0,8).equals("register")){
                System.out.println("REGISTERING AUTHOR");
            }
            else if (cmd.length() >= 5 && cmd.substring(0,5).equals("login")){
               // System.out.println("LOGIN");

                String [] log_id = cmd.split(" ");

                if (log_id.length == 2) {

                    int id = Integer.valueOf(log_id[1]);

                    logged_in = login(EDITOR, id);

                    if(!logged_in){
                        System.out.println("ERROR TRY AGAIN");
                    }
                }

            }
        }

        System.out.print("Login Successful. Enter command: ");

        while (!exit){
            cmd = sc.nextLine();

            if (cmd.equals("exit")){
                exit = true;
            }
        }

        return true;
    }

    public static boolean author_interface(Scanner sc){
        boolean logged_in = false;
        boolean exit = false;

        String cmd;

        while (logged_in == false){

            System.out.println("To login, type 'login <id>' with your user ID.");
            System.out.println("To register as a new author, type 'register author <fname> <lname> <email> <address>': ");


            cmd = sc.nextLine();

            if (cmd.length() >= 8 && cmd.substring(0,8).equals("register")){
                System.out.println("REGISTERING AUTHOR");
            }
            else if (cmd.length() >= 5 && cmd.substring(0,5).equals("login")){
                //System.out.println("LOGIN");

                String [] log_id = cmd.split(" ");

                if (log_id.length == 2) {

                    int id = Integer.valueOf(log_id[1]);

                    logged_in = login(AUTHOR, id);

                    if(!logged_in){
                        System.out.println("ERROR TRY AGAIN");
                    }
                }
            }
        }

        System.out.print("Login Successful. Enter command: ");

        while (!exit){
            cmd = sc.nextLine();

            if (cmd.equals("exit")){
                exit = true;
            }
        }


        return true;
    }

    public static boolean reviewer_interface(Scanner sc){
        boolean logged_in = false;
        boolean exit = false;
        String cmd;

        while (!logged_in){

            System.out.println("To login, type 'login <id>' with your user ID.");
            System.out.println("To register as a new reviewer, type 'register reviewer <fname> <lname> <email> <address>' : ");


            cmd = sc.nextLine();

            if (cmd.length() >= 8 && cmd.substring(0,8).equals("register")){
                System.out.println("REGISTERING AUTHOR");
            }
            else if (cmd.length() >= 5 && cmd.substring(0,5).equals("login")){
               // System.out.println("LOGIN");

                String [] log_id = cmd.split(" ");

                if (log_id.length == 2) {

                    int id = Integer.valueOf(log_id[1]);

                    logged_in = login(REVIEWER, id);

                    if(!logged_in){
                        System.out.println("ERROR TRY AGAIN");
                    }
                }
            }

        }

        System.out.print("Login Successful. Enter command: ");

        while (!exit){
            cmd = sc.nextLine();

            if (cmd.equals("exit")){
                exit = true;
            }
        }

        return true;
    }

    public static boolean login(int user_type, int user_ID){

        String query = "";
        Statement stmt = null;
        ResultSet rs = null;

        if (user_type == EDITOR){
            query = "SELECT * FROM editor";
        } else if (user_type == AUTHOR){
            query = "SELECT * FROM author";
        } else {
            query = "SELECT * FROM reviewer";
        }

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {

           //     System.out.println("ID: " + rs.getInt(1));

                // user found in system
                if (user_ID == rs.getInt(1)){
                    return true;
                }

            }

        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }

        return false;
    }

  //  public static boolean register()

    public static boolean connect_to_DB(){


        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            //System.out.println("ERROR");
            return false;
        }

        String URL = "jdbc:mysql://sunapee.cs.dartmouth.edu:3306";
        String USER = "jdressel";
        String PASS = "julia11";

        try{
            conn = DriverManager.getConnection(URL, USER, PASS);

            Statement stmt = null;
            ResultSet rs = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery("USE jdressel_db");

        } catch (SQLException e){
            // handle exception
            //System.out.println("ERROR CONNECTING");
            return false;
        }

        return true;
    }

}


/*
try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM author");

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
            }
            catch (SQLException ex){
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed

                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) { } // ignore

                    rs = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { } // ignore

                    stmt = null;
                }
            }
 */