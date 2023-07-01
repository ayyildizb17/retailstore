# retailstore
First, basically run RetailstoreApplication.java which is on the src\main\java\com\retailstore\retailstore path.
After that, on console h2 (available at http://localhost:8080/h2-console), the table user_user will be created.
The JDBC URL is: "jdbc:h2:mem:app", the username is: "sa" and you should leave the password field blank. 
You can create your own data, if you want or you can use my example data which is on the src\main\resources\data.sql.
You can basically, copy this query and paste it to the h2 console.
Via postman, you can test the correctness of code. The code will be running on the http://localhost:8080/netPayableAmount.
The example request is: {
                          "bill": 200.0,
                          "userId": "QFF66QMA8IK",
                          "isBoughtPhone": true
                        }
And the example response is: {
                               "netPayableAmount": 155.0
                             }
