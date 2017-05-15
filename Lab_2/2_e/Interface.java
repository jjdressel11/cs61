/*
 * Lab 2, Part E
 * Created by Max Parker and Julia Dressel
 * 5/13/17
 *
 */

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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

            System.out.println("Type 'login' to login.");
            System.out.println("Type 'register' to register a new editor.");

            cmd = sc.nextLine();

            if (cmd.equals("register")){

                System.out.print("First Name: ");
                String fName = sc.nextLine();
                System.out.print("Last Name: ");
                String lName = sc.nextLine();

                register_editor(fName, lName);

                System.out.println(fName+" "+lName+" is registered.");
            }

            else if (cmd.equals("login")){

                System.out.print("Enter your ID: ");
                String edidS = sc.nextLine();
                int editor_id = Integer.parseInt(edidS);

                logged_in = login(EDITOR, editor_id);

                while (!logged_in) {
                    System.out.print("Sorry, re-enter your ID: ");
                    edidS = sc.nextLine();
                    editor_id = Integer.parseInt(edidS);

                    logged_in = login(EDITOR, editor_id);
                }

                String editor = "";
                Statement stmt = null;
                ResultSet rs = null;

                editor = "SELECT * FROM editor WHERE editor_ID = " + editor_id + "";

                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(editor);

                    while (rs.next()) {
                        String name = rs.getString(2) + " " + rs.getString(3);
                        System.out.println("Welcome!");
                        System.out.println("Name: " + name);
                    }

                } catch (SQLException ex) {
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        rs = null;
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        stmt = null;
                    }
                }

                stmt = null;
                rs = null;
                String query = "SELECT * FROM manuscript WHERE editor_ID = "+editor_id+" ORDER BY status, manuscript_ID ";

                int sub = 0;
                int ur = 0;
                int rej = 0;
                int acc = 0;
                int type = 0;
                int sched = 0;
                int pub = 0;

                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        String status = rs.getString(5);
                        if (status.equals("submitted")) {
                            sub++;
                        } else if (status.equals("under review")) {
                            ur++;
                        } else if (status.equals("rejected")) {
                            rej++;
                        } else if (status.equals("accepted")) {
                            acc++;
                        } else if (status.equals("in typesetting")) {
                            type++;
                        } else if (status.equals("scheduled for publication")) {
                            sched++;
                        } else if (status.equals("published")) {
                            pub++;
                        }
                    }
                } catch (SQLException ex) {
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        rs = null;
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        stmt = null;
                    }
                }

                System.out.println(sub + " submitted");
                System.out.println(ur + " under review");
                System.out.println(rej + " rejected");
                System.out.println(acc + " accepted");
                System.out.println(type + " in typesetting");
                System.out.println(sched + " scheduled for publication");
                System.out.println(pub + " published");

                System.out.println("Possible commands are 'status', 'assign', 'reject', accept', typeset', 'schedule', and 'publish', or 'exit'");
                System.out.print("Enter a command: ");

                while (!exit){
                    cmd = sc.nextLine();

                    if (cmd.equals("status")){

                        stmt = null;
                        rs = null;
                        query="SELECT * FROM manuscript WHERE editor_ID = "+editor_id+" ORDER BY status, manuscript_ID";

                        try {
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query);

                            while (rs.next()) {
                                String status = "ID: "+rs.getString(1)+", Status: "+rs.getString(5);
                                System.out.println(status);
                            }
                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        System.out.print("Enter another command, or type 'exit': ");
                    }

                    else if (cmd.equals("assign")){
                        System.out.print("Manuscript ID: ");
                        String manuIDstring = sc.nextLine();
                        int manuID = Integer.parseInt(manuIDstring);
                        System.out.print("Reviewer ID: ");
                        String revIDstring = sc.nextLine();
                        int revID = Integer.parseInt(revIDstring);

                        String manuAssign = "";
                        stmt = null;

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                        manuAssign = "INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned) VALUES ("+manuID+","+revID+",'"+timestamp+"')";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuAssign);
                            System.out.println("Assigned on: " + timestamp);
                        }
                        catch (SQLException ex){
                            // handle any errors
                            System.out.println("Sorry, either the manuscript ID or the reviewer ID is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        String manuReview = "";
                        stmt = null;

                        manuReview = "UPDATE manuscript SET status = 'reviewing', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+manuID+"";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuReview);
                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        System.out.print("Enter another command, or type 'exit': ");
                    }

                    else if (cmd.equals("reject")){
                        System.out.print("Manuscript ID: ");
                        String manuIDstring = sc.nextLine();
                        int manuID = Integer.parseInt(manuIDstring);

                        String manuReview = "";
                        stmt = null;

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


                        manuReview = "UPDATE manuscript SET status = 'rejected', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+manuID+"";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuReview);
                            System.out.println("Rejected on: " + timestamp);
                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        System.out.print("Enter another command, or type 'exit': ");
                    }

                    else if (cmd.equals("accept")){
                        System.out.print("Manuscript ID: ");
                        String manuIDstring = sc.nextLine();
                        int manuID = Integer.parseInt(manuIDstring);

                        String manuReview = "";
                        stmt = null;

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                        manuReview = "UPDATE manuscript SET status = 'accepted', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+manuID+"";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuReview);
                            System.out.println("Accepted on: " + timestamp);

                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        System.out.print("Enter another command, or type 'exit': ");
                    }

                    else if (cmd.equals("typeset")){
                        System.out.print("Manuscript ID: ");
                        String manuIDstring = sc.nextLine();
                        int manuID = Integer.parseInt(manuIDstring);
                        System.out.print("Number of Pages: ");
                        String pagesString = sc.nextLine();
                        int pages = Integer.parseInt(pagesString);

                        String manuReview = "";
                        stmt = null;

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                        manuReview = "UPDATE manuscript SET status = 'in typsetting', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+manuID+"";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuReview);
                            System.out.println("Typeset on: " + timestamp);

                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        String manuFinal = "";
                        stmt = null;

                        manuFinal = "INSERT INTO manuscript_final (manuscript_ID,num_pages) VALUES ("+manuID+","+pages+")";

                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(manuFinal);
                        }
                        catch (SQLException ex){
                            // handle any errors
                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt = null;
                            }
                        }

                        System.out.print("Enter another command, or type 'exit': ");

                    }

                    else if (cmd.equals("schedule")){
                        System.out.print("Manuscript ID: ");
                        String manuIDstring = sc.nextLine();
                        int manuID = Integer.parseInt(manuIDstring);
                        System.out.print("Issue Publication Year: ");
                        String issueYearString = sc.nextLine();
                        int issueYear = Integer.parseInt(issueYearString);
                        System.out.print("Issue Period Number: ");
                        String issuePerString = sc.nextLine();
                        int issuePer = Integer.parseInt(issuePerString);

                        String findManPages = "";
                        stmt = null;
                        rs = null;
                        int manPages = 0;

                        findManPages = "SELECT * FROM manuscript_final WHERE manuscript_ID = "+manuID+"";

                        try {
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(findManPages);

                            while (rs.next()) {
                                String pages = rs.getString(2);
                                manPages = Integer.parseInt(pages);
                            }
                        }
                        catch (SQLException ex){
                            // handle any errors
                            System.out.println("Sorry, the manuscript ID entered is not valid.");
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
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

                        String findIssuePages = "";
                        stmt = null;
                        rs = null;
                        int issuePages = 0;

                        findIssuePages = "SELECT * FROM issue WHERE pub_year = "+issueYear+" AND period_num = "+issuePer+"";

                        try {
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(findIssuePages);

                            while (rs.next()) {
                                String pages = rs.getString(3);
                                issuePages = Integer.parseInt(pages);
                            }
                        }
                        catch (SQLException ex){
                            // handle any errors
                            System.out.println("Sorry, the issue entered is not valid.");

//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
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

                        if (issuePages + manPages < 100) {

                            int start = issuePages+1;

                            String startPage = "UPDATE manuscript_final SET start_page = "+issuePages+" WHERE manuscript_ID = "+manuID+"";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(startPage);
                            }
                            catch (SQLException ex){
                                // handle any errors
                                System.out.println("SQLException: " + ex.getMessage());
                                System.out.println("SQLState: " + ex.getSQLState());
                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            issuePages += manPages;

                            String manuReview = "";
                            stmt = null;

                            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                            manuReview = "UPDATE manuscript SET status = 'scheduled for publication', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+manuID+"";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(manuReview);
                                System.out.println("Scheduled on: " + timestamp);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("Sorry, the manuscript ID entered is not valid.");
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            String manuSchedule = "";
                            stmt = null;

                            manuSchedule = "UPDATE manuscript_final SET pub_year = "+issueYear+", period_num = "+issuePer+" WHERE manuscript_ID = "+manuID+"";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(manuSchedule);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("Sorry, the manuscript ID entered is not valid.");
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            String updatePages = "";
                            stmt = null;

                            updatePages = "UPDATE issue SET total_pages = "+issuePages+" WHERE pub_year = "+issueYear+" AND period_num = "+issuePer+"";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(updatePages);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }
                        }

                        else {
                            System.out.println("Sorry, that issue cannot be over 100 pages.");
                        }

                        System.out.print("Enter another command, or type 'exit': ");

                    }

                    else if (cmd.equals("publish")){
                        System.out.print("Issue Publication Year: ");
                        String issueYearString = sc.nextLine();
                        int issueYear = Integer.parseInt(issueYearString);
                        System.out.print("Issue Period Number: ");
                        String issuePerString = sc.nextLine();
                        int issuePer = Integer.parseInt(issuePerString);

                        int numManus = 0;

                        stmt = null;
                        rs = null;
                        query = "SELECT * FROM manuscript_final WHERE pub_year = "+issueYear+" AND period_num = "+issuePer+"";


                        try {
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query);

                            while (rs.next()) {
                                numManus++;
                            }
                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
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

                        if (numManus > 0) {

                            String issuePub = "";
                            stmt = null;
                            rs = null;
                            query="";
                            int man_ID = 0;

                            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                            query = "SELECT * FROM manuscript_final WHERE pub_year = "+issueYear+" AND period_num = "+issuePer+"";

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);
                                System.out.println("Published on: " + timestamp);

                                while (rs.next()) {
                                    String man_ID_string = rs.getString(1);
                                    man_ID = Integer.parseInt(man_ID_string);

                                    Statement stmt2 = null;

                                    issuePub = "UPDATE manuscript SET status = 'published', status_time_stamp = '"+timestamp+"' WHERE manuscript_ID = "+man_ID+"";

                                    try {
                                        stmt2 = conn.createStatement();
                                        stmt2.executeUpdate(issuePub);
                                    }
                                    catch (SQLException ex){
                                        // handle any errors
//                                        System.out.println("SQLException: " + ex.getMessage());
//                                        System.out.println("SQLState: " + ex.getSQLState());
//                                        System.out.println("VendorError: " + ex.getErrorCode());
                                    }
                                    finally {
                                        if (stmt2 != null) {
                                            try {
                                                stmt2.close();
                                            } catch (SQLException sqlEx) { } // ignore

                                            stmt2 = null;
                                        }
                                    }

                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                        }
                        else {
                            System.out.println("Sorry, that issue has no manuscripts.");
                        }

                        System.out.print("Enter another command, or type 'exit': ");


                    }

                    else if (cmd.equals("exit")){
                        exit = true;
                    }
                }


                if(!logged_in){
                    System.out.println("ERROR TRY AGAIN");
                }
            }

        }

        return true;
    }

    public static boolean author_interface(Scanner sc){
        boolean logged_in = false;
        boolean exit = false;

        String cmd;

        while (logged_in == false){

            System.out.println("Type 'login' to login.");
            System.out.println("Type 'register' to register a new author.");

            cmd = sc.nextLine();

            if (cmd.equals("register")){

                System.out.print("First Name: ");
                String fName = sc.nextLine();
                System.out.print("Last Name: ");
                String lName = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Address: ");
                String address = sc.nextLine();

                register_author(fName, lName, email, address);

                System.out.println(fName+" "+lName+" is registered.");
            }
            else if (cmd.equals("login")){

                System.out.print("Enter your ID: ");
                String authIdS = sc.nextLine();
                int author_id = Integer.parseInt(authIdS);

                    logged_in = login(AUTHOR, author_id);

                    while (!logged_in) {
                        System.out.print("Sorry, re-enter your ID: ");
                        authIdS = sc.nextLine();
                        author_id = Integer.parseInt(authIdS);

                        logged_in = login(AUTHOR, author_id);
                    }

                    String author = "";
                    Statement stmt = null;
                    ResultSet rs = null;

                    author = "SELECT * FROM author WHERE author_ID = "+author_id+"";

                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(author);

                        while (rs.next()) {
                            String name = rs.getString(2) + " " + rs.getString(3);
                            String address = rs.getString(4);
                            System.out.println("Welcome!");
                            System.out.println("Name: " + name);
                            System.out.println("Address: " + address);
                        }

                    }
                    catch (SQLException ex){
                        // handle any errors
//                        System.out.println("SQLException: " + ex.getMessage());
//                        System.out.println("SQLState: " + ex.getSQLState());
//                        System.out.println("VendorError: " + ex.getErrorCode());
                    }
                    finally {
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

                    stmt = null;
                    rs = null;
                    String query="SELECT * FROM manuscript WHERE author_ID = "+author_id+" ORDER BY status, manuscript_ID";

                    int sub = 0;
                    int ur = 0;
                    int rej = 0;
                    int acc = 0;
                    int type = 0;
                    int sched = 0;
                    int pub = 0;

                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            String status = rs.getString(5);
                            if (status.equals("submitted")) { sub++; }
                            else if (status.equals("under review")) { ur++; }
                            else if (status.equals("rejected")) { rej++; }
                            else if (status.equals("accepted")) { acc++; }
                            else if (status.equals("in typesetting")) { type++; }
                            else if (status.equals("scheduled for publication")) { sched++; }
                            else if (status.equals("published")) { pub++; }
                        }
                    }
                    catch (SQLException ex){
                        // handle any errors
//                        System.out.println("SQLException: " + ex.getMessage());
//                        System.out.println("SQLState: " + ex.getSQLState());
//                        System.out.println("VendorError: " + ex.getErrorCode());
                    }
                    finally {
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

                    System.out.println(sub + " submitted");
                    System.out.println(ur + " under review");
                    System.out.println(rej + " rejected");
                    System.out.println(acc + " accepted");
                    System.out.println(type + " in typesetting");
                    System.out.println(sched + " scheduled for publication");
                    System.out.println(pub + " published");

                    System.out.println("Possible commands are 'submit', 'status', and 'retract', or 'exit'");
                    System.out.print("Enter a command: ");

                    while (!exit){
                        cmd = sc.nextLine();

                        if (cmd.equals("submit")){
                            System.out.print("Enter title: ");
                            String title = sc.nextLine();
                            System.out.print("Enter Affiliation: ");
                            String affil = sc.nextLine();
                            System.out.print("Enter RI Code: ");
                            String ri_string = sc.nextLine();
                            int ri_code = Integer.parseInt(ri_string);
                            String [] secondAuthors = new String[10];
                            System.out.print("Would you like to enter another author? (y/n)");
                            String anotherAuthor = sc.nextLine();
                            int i = 0;
                            while (anotherAuthor.equals("y")){
                                System.out.print("Enter additional author first name: ");
                                secondAuthors[i] = sc.nextLine();
                                i++;
                                System.out.print("Enter additional author last name: ");
                                secondAuthors[i] = sc.nextLine();
                                System.out.print("Would you like to enter another author? (y/n)");
                                anotherAuthor = sc.nextLine();
                                i++;
                            }

                            System.out.print("Enter filename: ");
                            String filename = sc.nextLine();


                            String manuscript = "";
                            stmt = null;

                            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                            System.out.println("Submitted on: " + timestamp);

                            manuscript = "INSERT INTO manuscript (author_ID,title,status,status_time_stamp,RI_Code) VALUES ("+author_id+",'"+title+"','submitted','"+timestamp+"',"+ri_code+")";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(manuscript);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            stmt = null;

                            String affiliation = "INSERT INTO affiliation (name) VALUES ('"+affil+"')";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(affiliation);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            stmt = null;
                            rs = null;
                            int affil_ID = 0;

                            query = "SELECT * FROM  affiliation WHERE name = '"+affil+"'";

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);

                                while (rs.next()) {
                                    String affil_id_string = rs.getString(1);
                                    affil_ID = Integer.parseInt(affil_id_string);
                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            stmt = null;

                            String author_affil = "UPDATE author SET affiliation_ID = "+affil_ID+" WHERE author_ID = "+author_id+"";

                            try {
                                stmt = conn.createStatement();
                                stmt.executeUpdate(author_affil);
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            stmt = null;
                            rs = null;
                            query="";
                            int manuID = 0;

                            query = "SELECT * FROM  manuscript WHERE title = '"+title+"'";

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);
                                ResultSetMetaData rsmd = rs.getMetaData();

                                int columnsNumber = rsmd.getColumnCount();
                                while (rs.next()) {
                                    String manuIDstring = rs.getString(1);
                                    manuID = Integer.parseInt(manuIDstring);
                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            for (int j = 0; j < secondAuthors.length; j+=2){

                                stmt = null;

                                String firstName = secondAuthors[j];
                                String lastName = secondAuthors[j+1];

                                int rank = j/2;

                                if (firstName != null && lastName != null){
                                    String secondAuthor = "INSERT INTO secondary_author (manuscript_ID,rank,first_name,last_name) VALUES ("+manuID+","+rank+",'"+firstName+"','"+lastName+"')";

                                    try {
                                        stmt = conn.createStatement();
                                        stmt.executeUpdate(secondAuthor);
                                    }
                                    catch (SQLException ex){
                                        // handle any errors
//                                        System.out.println("SQLException: " + ex.getMessage());
//                                        System.out.println("SQLState: " + ex.getSQLState());
//                                        System.out.println("VendorError: " + ex.getErrorCode());
                                    }
                                    finally {
                                        if (stmt != null) {
                                            try {
                                                stmt.close();
                                            } catch (SQLException sqlEx) { } // ignore

                                            stmt = null;
                                        }
                                    }
                                }
                            }

                            System.out.print("Enter another command, or type 'exit': ");
                        }

                        else if (cmd.equals("status")){

                            stmt = null;
                            rs = null;
                            query="SELECT * FROM manuscript WHERE author_ID = "+author_id+"";

                            sub = 0;
                            ur = 0;
                            rej = 0;
                            acc = 0;
                            type = 0;
                            sched = 0;
                            pub = 0;

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);
                                ResultSetMetaData rsmd = rs.getMetaData();

                                int columnsNumber = rsmd.getColumnCount();
                                while (rs.next()) {
                                    String status = rs.getString(5);
                                    if (status.equals("submitted")) { sub++; }
                                    else if (status.equals("under review")) { ur++; }
                                    else if (status.equals("rejected")) { rej++; }
                                    else if (status.equals("accepted")) { acc++; }
                                    else if (status.equals("in typesetting")) { type++; }
                                    else if (status.equals("scheduled for publication")) { sched++; }
                                    else if (status.equals("published")) { pub++; }
                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            System.out.println(sub + " submitted");
                            System.out.println(ur + " under review");
                            System.out.println(rej + " rejected");
                            System.out.println(acc + " accepted");
                            System.out.println(type + " in typesetting");
                            System.out.println(sched + " scheduled for publication");
                            System.out.println(pub + " published");

                            System.out.print("Enter another command, or type 'exit': ");
                        }

                        else if (cmd.equals("retract")){

                            System.out.print("Enter a manuscrupt ID to retract: ");
                            String idS = sc.nextLine();
                            int manID = Integer.parseInt(idS);

                            System.out.print("Are you sure you want to retract manuscript "+manID+"? (yes/no)");
                            String sure = sc.nextLine();
                            if (sure.equals("yes")){

                                String noManuReviews = "";
                                stmt = null;

                                noManuReviews = "DELETE FROM manuscript_review WHERE manuscript_ID = "+manID+"";

                                try {
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(noManuReviews);
                                }
                                catch (SQLException ex){
                                    // handle any errors
//                                    System.out.println("SQLException: " + ex.getMessage());
//                                    System.out.println("SQLState: " + ex.getSQLState());
//                                    System.out.println("VendorError: " + ex.getErrorCode());

                                }
                                finally {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (SQLException sqlEx) {
                                        } // ignore

                                        stmt = null;
                                    }
                                }

                                String noManuFinal = "";
                                stmt = null;

                                noManuFinal = "DELETE FROM manuscript_final WHERE manuscript_ID = "+manID+"";

                                try {
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(noManuFinal);
                                }
                                catch (SQLException ex){
                                    // handle any errors
//                                    System.out.println("SQLException: " + ex.getMessage());
//                                    System.out.println("SQLState: " + ex.getSQLState());
//                                    System.out.println("VendorError: " + ex.getErrorCode());

                                }
                                finally {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (SQLException sqlEx) {
                                        } // ignore

                                        stmt = null;
                                    }
                                }

                                String noSecAuthor = "";
                                stmt = null;

                                noSecAuthor = "DELETE FROM secondary_author WHERE manuscript_ID = "+manID+"";

                                try {
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(noSecAuthor);
                                }
                                catch (SQLException ex){
                                    // handle any errors
//                                    System.out.println("SQLException: " + ex.getMessage());
//                                    System.out.println("SQLState: " + ex.getSQLState());
//                                    System.out.println("VendorError: " + ex.getErrorCode());

                                }
                                finally {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (SQLException sqlEx) {
                                        } // ignore

                                        stmt = null;
                                    }
                                }

                                String noManuscript = "";
                                stmt = null;

                                noManuscript = "DELETE FROM manuscript WHERE manuscript_ID = "+manID+"";

                                try {
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(noManuscript);
                                }
                                catch (SQLException ex){
                                    // handle any errors
//                                    System.out.println("SQLException: " + ex.getMessage());
//                                    System.out.println("SQLState: " + ex.getSQLState());
//                                    System.out.println("VendorError: " + ex.getErrorCode());

                                }
                                finally {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (SQLException sqlEx) {
                                        } // ignore

                                        stmt = null;
                                    }
                                }
                            }

                            System.out.print("Enter another command, or type 'exit': ");

                        }

                        else if (cmd.equals("exit")){
                            exit = true;
                        }
                    }

                    if(!logged_in){
                        System.out.println("ERROR TRY AGAIN");
                    }
                }
            }

        return true;
    }

    public static boolean reviewer_interface(Scanner sc){
        boolean logged_in = false;
        boolean exit = false;
        String cmd;

        while (!logged_in){

            System.out.println("Type 'login' to login.");
            System.out.println("Type 'register' to register a new reviewer.");
            System.out.println("Type 'resign' to resign.");

            cmd = sc.nextLine();

            if (cmd.equals("register")){

                System.out.print("First Name: ");
                String fName = sc.nextLine();
                System.out.print("Last Name: ");
                String lName = sc.nextLine();
                System.out.print("Enter up to 3 RI Codes, separated by spaces: ");
                String codesString = sc.nextLine();

                String [] codes = codesString.split(" ");

                int[] interests = new int[3];

                String ri_code_string = codes[0];
                String ri_code_string2 = null;
                String ri_code_string3 = null;

                if (codes.length == 2) {
                    ri_code_string2 = codes[1];
                }

                else if (codes.length == 3) {
                    ri_code_string2 = codes[1];
                    ri_code_string3 = codes[2];
                }

                register_reviewer(fName, lName, ri_code_string, ri_code_string2, ri_code_string3);

                System.out.println(fName+" "+lName+" is registered.");
            }

            else if (cmd.equals("resign")) {

                System.out.print("Enter your ID: ");
                String resignS = sc.nextLine();
                int resignID = Integer.parseInt(resignS);

                String noManuReviews = "";
                Statement stmt = null;
                ResultSet rs = null;

                noManuReviews = "DELETE FROM manuscript_review WHERE reviewer_ID = "+resignID+"";

                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(noManuReviews);
                }
                catch (SQLException ex){
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());

                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        stmt = null;
                    }
                }

                String noRevInterests = "";
                stmt = null;

                noRevInterests = "DELETE FROM reviewer_interest WHERE reviewer_ID = "+resignID+"";

                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(noRevInterests);
                }
                catch (SQLException ex){
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());

                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        stmt = null;
                    }
                }

                String resign = "";
                stmt = null;

                resign = "DELETE FROM reviewer WHERE reviewer_ID = "+resignID+"";

                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(resign);
                }
                catch (SQLException ex){
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());

                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) {
                        } // ignore

                        stmt = null;
                    }
                }

                System.out.println("Thank you for your service.");

            }

            else if (cmd.equals("login")) {

                System.out.print("Enter your ID: ");
                String revIdS = sc.nextLine();
                int rev_id = Integer.parseInt(revIdS);

                    logged_in = login(REVIEWER, rev_id);

                    while (!logged_in) {
                        System.out.print("Sorry, re-enter your ID: ");
                        revIdS = sc.nextLine();
                        rev_id = Integer.parseInt(revIdS);

                        logged_in = login(REVIEWER, rev_id);
                    }

                    String reviewer = "";
                    Statement stmt = null;
                    ResultSet rs = null;

                    reviewer = "SELECT * FROM reviewer WHERE reviewer_ID = "+rev_id+"";

                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(reviewer);

                        while (rs.next()) {
                            String name = rs.getString(2) + " " + rs.getString(3);
                            System.out.println("Welcome!");
                            System.out.println("Name: " + name);
                        }

                    }
                    catch (SQLException ex){
                        // handle any errors
//                        System.out.println("SQLException: " + ex.getMessage());
//                        System.out.println("SQLState: " + ex.getSQLState());
//                        System.out.println("VendorError: " + ex.getErrorCode());
                    }
                    finally {
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

                stmt = null;
                rs = null;
                String query="";
                int man_ID = 0;

                int sub = 0;
                int ur = 0;
                int rej = 0;
                int acc = 0;
                int type = 0;
                int sched = 0;
                int pub = 0;

                query = "SELECT * FROM manuscript_review WHERE reviewer_ID = "+rev_id+"";

                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        String man_ID_string = rs.getString(1);
                        man_ID = Integer.parseInt(man_ID_string);

                        Statement stmt2 = null;
                        ResultSet rs2 = null;
                        String query2="";


                        query2 = "SELECT * FROM manuscript WHERE manuscript_ID = "+man_ID+"";

                        try {
                            stmt2 = conn.createStatement();
                            rs2 = stmt2.executeQuery(query2);

                            while (rs2.next()) {
                                String status = rs.getString(5);
                                if (status.equals("submitted")) { sub++; }
                                else if (status.equals("under review")) { ur++; }
                                else if (status.equals("rejected")) { rej++; }
                                else if (status.equals("accepted")) { acc++; }
                                else if (status.equals("in typesetting")) { type++; }
                                else if (status.equals("scheduled for publication")) { sched++; }
                                else if (status.equals("published")) { pub++; }

                            }
                        }
                        catch (SQLException ex){
                            // handle any errors
//                            System.out.println("SQLException: " + ex.getMessage());
//                            System.out.println("SQLState: " + ex.getSQLState());
//                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                        finally {
                            if (stmt2 != null) {
                                try {
                                    stmt2.close();
                                } catch (SQLException sqlEx) { } // ignore

                                stmt2 = null;
                            }
                        }

                    }
                }
                catch (SQLException ex){
                    // handle any errors
//                    System.out.println("SQLException: " + ex.getMessage());
//                    System.out.println("SQLState: " + ex.getSQLState());
//                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) { } // ignore

                        stmt = null;
                    }
                }

                System.out.println(sub + " submitted");
                System.out.println(ur + " under review");
                System.out.println(rej + " rejected");
                System.out.println(acc + " accepted");
                System.out.println(type + " in typesetting");
                System.out.println(sched + " scheduled for publication");
                System.out.println(pub + " published");

                    System.out.println("Possible commands are 'status', 'review', or 'exit'");
                    System.out.print("Enter a command: ");


                    while (!exit){
                        cmd = sc.nextLine();

                        if (cmd.equals("status")){

                            stmt = null;
                            rs = null;
                            query="";
                            man_ID = 0;

                            sub = 0;
                            ur = 0;
                            rej = 0;
                            acc = 0;
                            type = 0;
                            sched = 0;
                            pub = 0;

                            query = "SELECT * FROM manuscript_review WHERE reviewer_ID = "+rev_id+"";

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);
                                ResultSetMetaData rsmd = rs.getMetaData();

                                int columnsNumber = rsmd.getColumnCount();
                                while (rs.next()) {
                                    String man_ID_string = rs.getString(1);
                                    man_ID = Integer.parseInt(man_ID_string);

                                    Statement stmt2 = null;
                                    ResultSet rs2 = null;
                                    String query2="";

                                    query2 = "SELECT * FROM manuscript WHERE manuscript_ID = "+man_ID+"";

                                    try {
                                        stmt2 = conn.createStatement();
                                        rs2 = stmt2.executeQuery(query2);

                                        while (rs2.next()) {
                                            String status = rs.getString(5);
                                            if (status.equals("submitted")) { sub++; }
                                            else if (status.equals("under review")) { ur++; }
                                            else if (status.equals("rejected")) { rej++; }
                                            else if (status.equals("accepted")) { acc++; }
                                            else if (status.equals("in typesetting")) { type++; }
                                            else if (status.equals("scheduled for publication")) { sched++; }
                                            else if (status.equals("published")) { pub++; }

                                        }
                                    }
                                    catch (SQLException ex){
                                        // handle any errors
//                                        System.out.println("SQLException: " + ex.getMessage());
//                                        System.out.println("SQLState: " + ex.getSQLState());
//                                        System.out.println("VendorError: " + ex.getErrorCode());
                                    }
                                    finally {
                                        if (stmt2 != null) {
                                            try {
                                                stmt2.close();
                                            } catch (SQLException sqlEx) { } // ignore

                                            stmt2 = null;
                                        }
                                    }

                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            System.out.println(sub + " submitted");
                            System.out.println(ur + " under review");
                            System.out.println(rej + " rejected");
                            System.out.println(acc + " accepted");
                            System.out.println(type + " in typesetting");
                            System.out.println(sched + " scheduled for publication");
                            System.out.println(pub + " published");

                            System.out.print("Enter another command, or type 'exit': ");

                        }

                        else if (cmd.equals("review")){

                            System.out.print("Manuscript ID: ");
                            String manuID = sc.nextLine();
                            int manID = Integer.parseInt(manuID);

                            query = "SELECT * FROM manuscript WHERE manuscript_ID = "+manID+"";

                            try {
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(query);
                                ResultSetMetaData rsmd = rs.getMetaData();

                                while (rs.next()) {
                                    String status = rs.getString(5);

                                    if(status.equals("under review")) {
                                        System.out.print("Appropriateness (1-10): ");
                                        String appS = sc.nextLine();
                                        int app = Integer.parseInt(appS);
                                        System.out.print("Clarity (1-10): ");
                                        String clarS = sc.nextLine();
                                        int clar = Integer.parseInt(clarS);
                                        System.out.print("Methodology (1-10): ");
                                        String methS = sc.nextLine();
                                        int meth = Integer.parseInt(methS);
                                        System.out.print("Contribution to the Field (1-10): ");
                                        String contS = sc.nextLine();
                                        int cont = Integer.parseInt(contS);

                                        System.out.print("'accept' or 'reject'?: ");
                                        String review = sc.nextLine();

                                        System.out.println("Thank you for your review.");

//                                        if (review.equals("Accept")){
                                        String manuReview = "";
                                        stmt = null;

                                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

//                                        manuReview2 = "INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES ("+manID+","+rev_id+","+app+","+clar+","+meth+","+cont+",'"+review+"','"+timestamp+"')";
                                        manuReview = "UPDATE manuscript_review SET appropriateness = "+app+", clarity = "+clar+", methodology = "+meth+", contribution = "+cont+", recommendation = '"+review+"', date_reviewed = '"+timestamp+"' WHERE manuscript_ID = "+manID+" AND reviewer_ID = "+rev_id+"";

                                        try {
                                            stmt = conn.createStatement();
                                            stmt.executeUpdate(manuReview);
                                        }
                                        catch (SQLException ex){
                                            // handle any errors
//                                            System.out.println("SQLException: " + ex.getMessage());
//                                            System.out.println("SQLState: " + ex.getSQLState());
//                                            System.out.println("VendorError: " + ex.getErrorCode());
                                        }
                                        finally {
                                            if (stmt != null) {
                                                try {
                                                    stmt.close();
                                                } catch (SQLException sqlEx) { } // ignore

                                                stmt = null;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Sorry, that manuscript is not under review.");
                                        System.out.println("Type 'review' to try again.");
                                    }
                                }
                            }
                            catch (SQLException ex){
                                // handle any errors
//                                System.out.println("SQLException: " + ex.getMessage());
//                                System.out.println("SQLState: " + ex.getSQLState());
//                                System.out.println("VendorError: " + ex.getErrorCode());
                            }
                            finally {
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (SQLException sqlEx) { } // ignore

                                    stmt = null;
                                }
                            }

                            System.out.print("Enter another command, or type 'exit': ");

                        }

                        else if (cmd.equals("exit")){
                            exit = true;
                        }
                    }

                    if(!logged_in){
                        System.out.println("ERROR TRY AGAIN");
                    }
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

                // user found in system
                if (user_ID == rs.getInt(1)){
                    return true;
                }
            }
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
        }
        finally {
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

    public static void register_reviewer(String fName, String lName, String ri1, String ri2, String ri3) {
        String reviewer = "";
        Statement stmt = null;
        ResultSet rs = null;

        reviewer = "INSERT INTO reviewer (first_name,last_name) VALUES ('"+fName+"','"+lName+"')";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(reviewer);
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());

        }
        finally {

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

        stmt = null;
        rs = null;
        String query="";
        int rev_id = 0;

        query = "SELECT * FROM reviewer WHERE first_name = '"+fName+"' AND last_name = '"+lName+"'";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                String rev_id_string = rs.getString(1);
                rev_id = Integer.parseInt(rev_id_string);
            }
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }

        int ri_1 = Integer.parseInt(ri1);

        stmt = null;
        rs = null;

        String reviewerInt = "INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES ("+rev_id+","+ri_1+")";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(reviewerInt);
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());

        }
        finally {

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


        if (ri2 != null) {
            int ri_2 = Integer.parseInt(ri2);

            stmt = null;
            rs = null;

            reviewerInt = "INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES ("+rev_id+","+ri_2+")";

            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(reviewerInt);
            }
            catch (SQLException ex){
                // handle any errors
//                System.out.println("SQLException: " + ex.getMessage());
//                System.out.println("SQLState: " + ex.getSQLState());
//                System.out.println("VendorError: " + ex.getErrorCode());

            }
            finally {

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
        }
        if (ri3 != null) {
            int ri_3 = Integer.parseInt(ri3);

            stmt = null;
            rs = null;

            reviewerInt = "INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES ("+rev_id+","+ri_3+")";

            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(reviewerInt);
            }
            catch (SQLException ex){
                // handle any errors
//                System.out.println("SQLException: " + ex.getMessage());
//                System.out.println("SQLState: " + ex.getSQLState());
//                System.out.println("VendorError: " + ex.getErrorCode());

            }
            finally {

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
        }

    }

    public static void register_author(String fName, String lName, String emailAdd, String physAdd) {

        String author = "";
        Statement stmt = null;
        ResultSet rs = null;

        author = "INSERT INTO author (first_name,last_name,address,email) VALUES ('"+fName+"', '"+lName+"', '"+physAdd+"', '"+emailAdd+"')";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(author);
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());

        }
        finally {
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

    }

    public static void register_editor(String fName, String lName) {

        String editor = "";
        Statement stmt = null;
        ResultSet rs = null;

        editor = "INSERT INTO editor (first_name,last_name) VALUES ('"+fName+"', '"+lName+"')";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(editor);
        }
        catch (SQLException ex){
            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());

        }
        finally {

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

    }

    public static boolean connect_to_DB(){


        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
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
            return false;
        }

        return true;
    }
}
