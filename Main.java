package employeeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    // Set up front end components

    // Items for add user functionality
    private static JLabel labeladdFirstName = new JLabel("First Name");
    private static JTextField addFirstName = new JTextField();
    private static JLabel labeladdSurName = new JLabel("Last Name");
    private static JTextField addSurName = new JTextField();
    private static JLabel labeladdPPSN = new JLabel("PPSN");
    private static JTextField addPPSN = new JTextField();
    private static JLabel labeladdSalary = new JLabel("Salary");
    private static JTextField addSalary = new JTextField();
    private static JLabel labeladdJobTitle = new JLabel("Job Title");
    private static JTextField addJobTitle = new JTextField();
    private static JLabel labeladdDOB = new JLabel("Data of Birth");
    private static JTextField addDOB = new JTextField();
    private static JLabel labeladdGender = new JLabel("Gender");
    private static JComboBox<String> addGender = new JComboBox<>();
    private static JButton addUser = new JButton("Add");
    private static JLabel errorPrompt = new JLabel();

    // Items for update functionality
    private static JLabel labelupdateUser = new JLabel("User PPSN");
    private static JTextField updateUserPPSN = new JTextField();
    private static JLabel labelUpdateField = new JLabel("Field to Update");
    private static JComboBox<String> updateField = new JComboBox<>();
    private static JLabel labelUpdateData = new JLabel("New Data");
    private static JTextField updateData = new JTextField();
    private static JButton updateUser = new JButton("Update");
    private static JLabel updateError = new JLabel();

    // Items for delete functionality
    private static JLabel labelDeleteUser = new JLabel("User PPSN");
    private static JTextField deleteUserPPSN = new JTextField();
    private static JButton deleteUser = new JButton("Remove");
    private static JLabel deleteError = new JLabel();

    // Items to show user data
    private static JLabel userFirstName = new JLabel("First Name: ");
    private static JLabel userSurName = new JLabel("Sur Name:");
    private static JLabel userPPSN = new JLabel("PPSN:");
    private static JLabel userSalary = new JLabel("Salary:");
    private static JLabel userJobTitle = new JLabel("JobTitle");
    private static JLabel userGender = new JLabel("Gender:");
    private static JLabel userDOB = new JLabel("DOB:");
    private static JButton viewNext = new JButton("Next");
    private static JButton viewPrev = new JButton("Previous");
    private static JButton viewClear = new JButton("Clear");

    // Items for search functionality
    private static JComboBox<String> searchUserField = new JComboBox<>();
    private static JLabel labelsearchUserData = new JLabel("Parameters");
    private static JTextField searchUserData = new JTextField();
    private static JButton searchUser = new JButton("Search");
    private static JLabel searchError = new JLabel();

    // Global Variables
    private static DBController dbController;
    private static ResultSet queryData;

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();

        // Initialize a DBController object
        dbController = new DBController();

        // Connect to the database
        dbController.connectToMySql();

        // Get the front end and get all the user data
        main.createFrontEnd();
        main.getAllUserData();
    }

    /**
     * Method to set up the front end gui
     */
    private void createFrontEnd() {
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add user section panel
        JPanel addContents = new JPanel();
        addContents.setLayout(new BoxLayout(addContents, BoxLayout.Y_AXIS));

        addContents.add(labeladdFirstName);
        addContents.add(addFirstName);
        addFirstName.setPreferredSize(new Dimension(200, 25));
        addFirstName.setMaximumSize(addFirstName.getPreferredSize());

        addContents.add(labeladdSurName);
        addContents.add(addSurName);
        addSurName.setPreferredSize(new Dimension(200, 25));
        addSurName.setMaximumSize(addSurName.getPreferredSize());

        addContents.add(labeladdPPSN);
        addContents.add(addPPSN);
        addPPSN.setPreferredSize(new Dimension(200, 25));
        addPPSN.setMaximumSize(addPPSN.getPreferredSize());

        addContents.add(labeladdSalary);
        addContents.add(addSalary);
        addSalary.setPreferredSize(new Dimension(200, 25));
        addSalary.setMaximumSize(addSalary.getPreferredSize());

        addContents.add(labeladdJobTitle);
        addContents.add(addJobTitle);
        addJobTitle.setPreferredSize(new Dimension(200, 25));
        addJobTitle.setMaximumSize(addJobTitle.getPreferredSize());

        addContents.add(labeladdDOB);
        addContents.add(addDOB);
        addDOB.setPreferredSize(new Dimension(200, 25));
        addDOB.setMaximumSize(addDOB.getPreferredSize());

        addContents.add(labeladdGender);
        addContents.add(addGender);
        addGender.setPreferredSize(new Dimension(200, 25));
        addGender.setMaximumSize(addGender.getPreferredSize());
        addGender.addItem("Male");
        addGender.addItem("Female");
        addGender.addItem("Other");

        addContents.add(Box.createVerticalStrut(5));
        addContents.add(addUser);
        addContents.add(Box.createVerticalStrut(10));
        addContents.add(errorPrompt);

        // Add action listener for the add user button
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // Call the add user method
                addUser();
            }
        });

        // Update and delete user sections
        JPanel otherContents = new JPanel();

        otherContents.setLayout(new BoxLayout(otherContents, BoxLayout.Y_AXIS));

        otherContents.add(labelupdateUser);
        otherContents.add(updateUserPPSN);
        updateUserPPSN.setPreferredSize(new Dimension(200, 25));
        updateUserPPSN.setMaximumSize(updateUserPPSN.getPreferredSize());

        otherContents.add(labelUpdateField);
        otherContents.add(updateField);
        updateField.setPreferredSize(new Dimension(200, 25));
        updateField.setMaximumSize(updateField.getPreferredSize());
        updateField.addItem("First Name");
        updateField.addItem("Sur Name");
        updateField.addItem("DOB");
        updateField.addItem("Salary");
        updateField.addItem("Job Title");

        otherContents.add(labelUpdateData);
        otherContents.add(updateData);
        updateData.setPreferredSize(new Dimension(200, 25));
        updateData.setMaximumSize(updateData.getPreferredSize());
        
        otherContents.add(Box.createVerticalStrut(5));

        otherContents.add(updateUser);

        // Create action listener for update user button
        updateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateUser();
            }
        });

        otherContents.add(updateError);

        otherContents.add(Box.createVerticalStrut(20)); // Fixed width invisible separator.

        otherContents.add(labelDeleteUser);
        otherContents.add(deleteUserPPSN);
        deleteUserPPSN.setPreferredSize(new Dimension(200, 25));
        deleteUserPPSN.setMaximumSize(updateData.getPreferredSize());

        otherContents.add(Box.createVerticalStrut(5));
        otherContents.add(deleteUser);

        // Create action listener for remove button
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeUser();
            }
        });

        otherContents.add(Box.createHorizontalStrut(10)); // Fixed width invisible separator.
        otherContents.add(deleteError);

        JPanel viewContents = new JPanel();
        viewContents.setLayout(new BoxLayout(viewContents, BoxLayout.Y_AXIS));

        viewContents.add(userPPSN);
        viewContents.add(userFirstName);
        viewContents.add(userSurName);
        viewContents.add(userJobTitle);
        viewContents.add(userSalary);
        viewContents.add(userDOB);
        viewContents.add(userGender);

        viewContents.add(Box.createVerticalStrut(10)); // Fixed width invisible separator.

        viewContents.add(viewNext);

        viewContents.add(Box.createVerticalStrut(5)); // Fixed width invisible separator.

        viewContents.add(viewPrev);

        viewContents.add(Box.createVerticalStrut(5)); // Fixed width invisible separator.

        viewContents.add(viewClear);

        viewContents.add(Box.createVerticalStrut(5)); // Fixed width invisible separator.

        viewContents.add(searchError);
        // Create action listeners for the next, previous and clean buttons
        viewNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                moveResultSet("next");
            }
        });

        viewPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                moveResultSet("previous");
            }
        });

        viewClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getAllUserData();
            }
        });

        JPanel searchContents = new JPanel();
        // searchContents.setLayout(new BoxLayout(searchContents, BoxLayout.Y_AXIS));

        searchContents.add(searchUserField);
        searchUserField.setPreferredSize(new Dimension(150, 25));
        searchUserField.setMaximumSize(searchUserField.getPreferredSize());
        searchUserField.addItem("First Name");
        searchUserField.addItem("Sur Name");
        searchUserField.addItem("Salary");
        searchUserField.addItem("Job Title");
        searchUserField.addItem("Gender");
        searchUserField.addItem("PPS");

        searchContents.add(labelsearchUserData);
        searchContents.add(searchUserData);
        searchUserData.setPreferredSize(new Dimension(150, 25));
        searchUserData.setMaximumSize(searchUserData.getPreferredSize());

        otherContents.add(Box.createHorizontalStrut(10)); // Fixed width invisible separator.

        searchContents.add(searchUser);

        // Create action listener for search user button
        searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchError.setText("");
                searchUser();
            }
        });


        frame.add(addContents, BorderLayout.WEST);
        frame.add(otherContents, BorderLayout.EAST);
        frame.add(viewContents, BorderLayout.CENTER);
        frame.add(searchContents, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(700, 400);
        frame.setVisible(true);
    }

    /**
     * Method to Gather data from form do some validation and then call db function to add to database
     */
    private void addUser() {
        // System.out.println("Add");
        String ppsn = addPPSN.getText();
        String firstName = addFirstName.getText();
        String surName = addSurName.getText();
        String jobTitle = addJobTitle.getText();
        String salary = addSalary.getText();
        String gender = addGender.getSelectedItem().toString();
        String dob = addDOB.getText();

        // Check to see if the fields are filled. Prompt user if they are missing data
        if (ppsn.equals("")) {
            errorPrompt.setText("Please add PPSN");
        } else if (firstName.equals("")) {
            errorPrompt.setText("Please add First Name");
        } else if (surName.equals("")) {
            errorPrompt.setText("Please add Last Name");
        } else if (salary.equals("")) {
            errorPrompt.setText("Please add Salary");
        } else if (jobTitle.equals("")) {
            errorPrompt.setText("Please add Job Title");
        } else if (dob.equals("")) {
            errorPrompt.setText("Please add Date of Birth");
        } else {

            // Try and case the salary value as an int
            int intSalary = 0;
            try {
                intSalary = Integer.parseInt(salary);
            } catch (Exception e) {
                // If it fails give the user an error
                System.out.println(e);
                errorPrompt.setText("Please Use Number for Salary(30000)");
                return;
            }

            // Try and format date data as a date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDob;
            try {

                // Try to format it
                localDob = LocalDate.parse(dob, formatter);
            } catch (Exception e) {

                // If it fails inform the user
                System.out.println(e);
                errorPrompt.setText("Please Use Correct Date format");
                return;
            }


            // Set the gender data to the DB format
            if (gender.equals("Male")) {
                gender = "M";
            } else if (gender.equals("Female")) {
                gender = "F";
            } else {
                gender = "O";
            }

            /*
            System.out.println(ppsn);
            System.out.println(firstName);
            System.out.println(surName);
            System.out.println(jobTitle);
            System.out.println(salary);
            System.out.println(gender);
            System.out.println(localDob);
            */

            // Call the dbController addUser function
            boolean result = dbController.addUser(ppsn, firstName, surName, jobTitle, gender, intSalary, localDob);

            // If the user is added tell the user and clear the forms
            if (result) {
                errorPrompt.setText("User Added");
                addGender.setSelectedIndex(0);
                addFirstName.setText("");
                addSurName.setText("");
                addDOB.setText("");
                addJobTitle.setText("");
                addPPSN.setText("");
                addSalary.setText("");
                getAllUserData();
            } else {
                // else tell the user
                errorPrompt.setText("Failed to Add user");
            }
        }
    }

    /**
     * Method to get data from form and call dbController search function
     */
    private void searchUser() {

        // Get data from fields
        String field = searchUserField.getSelectedItem().toString();
        String data = searchUserData.getText();

        // Call the dbController method
        ResultSet searchResult = dbController.search(field, data);

        try {
            // Check if the search result is null or if it empty
            if(searchResult != null && searchResult.next()) {

            // Set the global setResult as the query result
            queryData = searchResult;

            // Get the first item in the search result
            queryData.first();

            // Display the data
            displayUser();

            } else {
            searchError.setText("No Users Found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method to gather form data and remove user
     */
    private void removeUser() {

        // Get data from field
        String ppsn = deleteUserPPSN.getText();

        // Call dbController method to remove use
        boolean result = dbController.removeUser(ppsn);

        // If the result is true
        if (result) {

            // Tell user that user has been removed
            deleteError.setText("User Removed");

            // Fix display
            deleteUserPPSN.setText("");
            getAllUserData();
        } else {

            // Other wise tell user
            deleteError.setText("Error removing user");
        }
    }

    /**
     * Method to get data from fields and update user
     */
    private void updateUser() {
        //System.out.println("Update");

        // Get data from fields
        String ppsn = updateUserPPSN.getText();
        String field = updateField.getSelectedItem().toString();
        String data = updateData.getText();

        // Edit field strings so they are the same as DB column names
        field = field.replaceAll("\\s+", "");


        boolean result;

        // If the field is salary
        if (field.equals("Salary")) {

            // Cast the field as an int
            int intData = -1;
            try {
                intData = Integer.parseInt(data);
            } catch (Exception e) {
                System.out.println(e);
                updateError.setText("Please use number for salary");
                return;
            }

            // Call the dbController method with the int data
            result = dbController.updateUserData(ppsn, field, null, intData, null);
        } else if (field.equals("DOB")) {
            // If the field is DOB

            // Cast the data as a Local date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDob;
            try {
                localDob = LocalDate.parse(data, formatter);
            } catch (Exception e) {
                System.out.println(e);
                errorPrompt.setText("Please Use Correct Date format");
                return;
            }

            // Call the dbController method
            result = dbController.updateUserData(ppsn, field, null, -1, localDob);

        } else {

            // Other wise just call the dbController method with the data unchanged
            result = dbController.updateUserData(ppsn, field, data, -1, null);
        }

        // Check the value of the result
        if (result) {

            // If it is true the user has been updated.
            updateError.setText("User updated");
            updateUserPPSN.setText("");
            updateField.setSelectedIndex(0);
            updateData.setText("");
            getAllUserData();
        } else {

            // Else if failed
            updateError.setText("Failed to update user");
        }
    }

    // Method to manage displaying the result set
    public void moveResultSet(String option) {

        // If the option is previous
        if (option.equals("previous")) {

            // Move the set result back one
            try {
                queryData.previous();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if(option.equals("first")){

            // Else move the result to the first
            try {
                queryData.first();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{

            // Else move the result forward
            try {
                queryData.next();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Display the result set
        displayUser();
    }

    /**
     * Method to display the selected result set one the display
     */
    private void displayUser () {
        try {
            userPPSN.setText("PPSN: " + queryData.getString("PPS"));
            userFirstName.setText("First Nanme: " + queryData.getString("FirstName"));
            userSurName.setText("Sur Name: " + queryData.getString("SurName"));
            userDOB.setText("DOB: " + queryData.getDate("DOB").toString());
            userGender.setText("Gender: " + queryData.getString("Gender"));
            userSalary.setText("Salary: " + Integer.toString(queryData.getInt("Salary")));
            userJobTitle.setText("Job Title: " + queryData.getString("JobTitle"));
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Method that calls the get all user data method
     */
    private void getAllUserData(){

        // Get all of the user data from the dbController get all function
        ResultSet searchData = dbController.getAllUserData();

        try {
            // Check if the result set is not null and it is not empty
            if (searchData != null && searchData.next()) {

                // Set the global result set to be the result of the query
                queryData = searchData;
                moveResultSet("first");
            } else {
                searchError.setText("No Users");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
  }

