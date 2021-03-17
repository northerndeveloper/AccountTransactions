## ASSIGNMENT DEFINITION

This assignment was implement for an API to create a new current account
for an already existing customer. User is able to create a new account with
the balance or without balance. If user creates an account with a balance in it
then a new transaction will be creaated as well creating a new account. Users should already be created
in the acount as we have no user creation at this assignment.

## Used Technologies

* JAVA 11
* SPRINT BOOT 2.4.3
* SPRING BOOT STARTER DATA JPA
* SPRING MVC
* THYMELEAF   
* H2 IN-MEMORY SQL DATABASE
* JUNIT 5.4.2
* MOCKITO 3.2.4
* PROJECT LOMBOK 
* MAVEN

## How To Run The Application
Please run AccountTransactionsApplications from the project. When you run the application,
the tables will be created automatically and the insert scripts will be executed which are in data.sql.
You will have customer, account and already inserted transactions when you run the application.
As you know H2 is an In-Memory Database, so each time when you restart the application the data that you inserted
will be lost.

## How to run the H2-Console

Please browse following link to query and make DB operations.

http://localhost:8080/h2-console/

Username: blueharvest
Password : blueharvest
    
## REST-SERVICES

### Create A New 'Current' Account

Web Service POST Request : 

http://localhost:8080/createaccount?customerid=323132&initialcredit=201

Pure Request :
http://localhost:8080/createaccount

Request Parameters:

'customerid' : The ID of the customer which already created or resided at DB
'initialcredit' : The amount of money that you will send to initiate a transaction.It can be 0

### Get Custmer By ID

Web Service GET Request : 

http://localhost:8080/showcustomer?customerid=323132

Pure Request :
http://localhost:8080/showcustomer

Request Parameters:
'customerid' : The ID of the customer which already created or resided at DB

### Get All Transactions Of A User By Customer ID

Web Service GET Request :

http://localhost:8080/transactionsofcustomer?customerid=323132

Pure Request :
http://localhost:8080/transactionsofcustomer

Request Parameters:
'customerid' : The ID of the customer which already created or resided at DB

## FRONTEND VIEW 

### Create a New Account for the Customer

If you want to create a new account which will be opened connected to the user 
whoe ID is customerID , please go the following link :

http://localhost:8080/account

You will see CustomerID and InitialCredit input boxes below. Please
type customerID into the CustomerID box and initial credit to the initial credit input box.

Sample Customer ID : 323132
Sample Initial Credit: 50

After you click Submit button, if you write an inital credit a transaction will also be crated as well as a
current account will be  created.

If you do not mention initialCredit only a new current account will be created.

### List User Information

If you want to list user information which outputs the customer name,
surname, balance of customer's total accounts and all transactions of the 
account please browse following link: 

http://localhost:8080/customer

Please type Customer ID into the Customer ID field and click submit button.

Sample Customer IDS : 323132,323133,323134

You will see Customer Name, Customer Surname, Total Balance and related
Account and Transaction information

## CI - CD Integration

A CI/CD pipeline was created with GitHub Actions.
Pipeline helped in automating software delivery process.

Please click following link to see CI / CD pipelines :

https://github.com/northerndeveloper/AccountTransactions/actions




