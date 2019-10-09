# Employee View

Employee view is a employee management Java application that allows you to interact with an MySQL database hosted with wamp.

## Employee Data:
1. PPS: varchar(7) - Personal Public Service Number (SSN) This is used as the primary key and how the database is indexed as most searching is done though this item.
2. DOB: date - Employee data of birth.
3. FirstName: varchar(20) - Employee first name.
4. SurName: varchar(20) - Employee last name.
5. Salary: int(8) - Employee salary.
6. Gender: varchar(1) - Employee gender, can be M, F or O.
7. JobTitle: varchar(20) - Employee job title.


## Using the Application

### Adding:

Fill out the Fields with the user data and press the add button. Some error handling exists so the program will tell you if there is any problems

### Updating:
Fill in the users PPSN, select the field you would like to update from the drop down, and enter the new data, press updated. The program will tell you if it worked or not.

### Removing:
FIll in the user PPSN, press the remove button.

### Search:
Select the field from the dropdown that you want to search by. Put in your data and then press the search button. Your results if any will be displayed in the display section.

### Display
By default all of the user data can be looked at. A result set of all the users can been iterated though by pressing next to go forward one, previous to go back one. 

When you use the search function your result will be displayed in the same fields, you can iterate though this by pressing next and previous. When you are finished you can press clear and all the user data will be displayed again.


