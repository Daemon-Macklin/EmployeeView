package employeeView;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author dmacklin
 */
public class DBController {

    // Connection object for application
    private Connection dbCon;

    /**
     * Method to connect to database
     */
    public void connectToMySql() {

        Connection con;

        // Try to connect
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Try to connect to the "test" database with the GMT timezone with user name root and no password
            con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", "root", "");

            // Check if it is connected
            if (!con.isClosed())

                // If connect print to console connected
                System.out.println("Connected to mysql server!");

            // return the connection object
            dbCon = con;

        } catch (Exception e) {

            // if it fails print the error to the console, inform the user and then return null
            System.err.println("Exception " + e.getMessage());
        }
    }

    /**
     * Method to add user
     * @param ppsn user ppsn will be used as uid and primary key
     * @param firstName User first Name
     * @param lastName User Sur Name
     * @param jobTitle User Job title
     * @param gender User Gender
     * @param salary User Salary
     * @param dob User data of birth
     * @return Returns true if add succeeded. False is not.
     */
    public boolean addUser( String ppsn, String firstName, String lastName, String jobTitle, String gender, int salary, LocalDate dob) {

        try {
            // Create prepared statement to insert the data into the database without the vulnerability of an sql injection attack.
            PreparedStatement pstmt = dbCon.prepareStatement("INSERT  INTO employee VALUES (?,?,?,?,?,?,?)");

            // Add variables to the sql command
            pstmt.setString(1, ppsn);
            pstmt.setDate(2, java.sql.Date.valueOf(dob));
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setInt(5, salary);
            pstmt.setString(6, gender);
            pstmt.setString(7, jobTitle);

            // Execute update command return true
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            // If it fails print error and return false
            System.out.println(e);
            return false;
        }
    }

    /**
     * Overloaded function that will execute the sql command
     * @param userId User SSn
     * @param field The field to be updated
     * @param textData Data that is a String
     * @param intData Data that is an int
     * @return returns true if user is added, false if not
     */
    public boolean updateUserData(String userId, String field, String textData, int intData, LocalDate dateData){

        // Create base sql update command
        // i.e. UPDATE employee SET Gender=? WHERE SSn='08490079-0c33-45fd-9164-66700b73ac01'
        String sql_update = "UPDATE employee SET " + field + "=? WHERE PPS='" + userId + "'";
        //System.out.println(sql_update);


        try {
            // Create prepared statement to insert the data into the database without the vulnerability of an sql injection attack.
            PreparedStatement pstmt = dbCon.prepareStatement(sql_update);

            // Based on the arguments set the right data in the command
            if (textData != null) {

                // If text data is not null use the text data
                pstmt.setString(1, textData);
            } else if (intData != -1) {

                // If the int is -1 use the int data
                pstmt.setInt(1,intData);
            } else if(dateData != null){

                pstmt.setDate(1, java.sql.Date.valueOf(dateData));
            }

            // Execute the update command and return true
            pstmt.executeUpdate();
            return true;
        }catch (Exception e){

            // If fail print error to console and return false
            System.out.println(e);
            return false;
        }
    }

    /**
     * Method to search though users
     * @param field Field to search though
     * @param parameter Search parameters
     * @return Result set of users found
     */
    public ResultSet search(String field, String parameter) {


        String sql_command = "Select * from employee WHERE " + field.replaceAll("\\s+","") + "=?";
        ResultSet rs = null;
        try {
            // Create prepared statement to insert the data into the database without the vulnerability of an sql injection attack.
            PreparedStatement pstmt = dbCon.prepareStatement(sql_command);
            pstmt.setString(1, parameter);

            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();

        } catch (Exception e) {
            // If it fails print message to console
            System.out.println(e);
        }
        if (rs != null) {
            return rs;
        }else{
            return null;
        }
    }

    /**
     * Method to remove user
     * @param ppsn Use ppsn
     * @return True if use is removed false if not.
     */
    public boolean removeUser(String ppsn) {

        String sql_command = "delete from employee WHERE PPS =?";

        try {

            PreparedStatement pstmt = dbCon.prepareStatement(sql_command);
            pstmt.setString(1, ppsn);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            // If it fails print message to console
            System.out.println(e);
            return false;
        }
    }

    /**
     * Method that returns all user data
     * @return Result set of all user data
     */
    public ResultSet getAllUserData(){
        ResultSet rs = null;
        try {

            // Connect to the databse
            Statement s = dbCon.createStatement();

            // Execute SQL command
            s.executeQuery("Select * from employee");

            // Get the results
            rs = s.getResultSet();

        } catch (Exception e) {
            // If it fails print message to console
            System.out.println(e);
        }
        if (rs != null) {
            return rs;
        }else{
            return null;
        }
    }
}

