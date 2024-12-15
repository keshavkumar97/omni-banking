# omni-banking
omni-banking is a banking application which follows microservice architecture and all modern technology and approach


**Deployment strategy-**

* The services contain migration sql scripts  which is applied to db with the help of **Flyway**.   
* But while deploying we need to opt a strategy to deploy all microservice because every microservice have some sql script and some are dependent on other.  
* So to make smooth deployment there are many approach we can take but here we opted for ***ordered service deployment*** strategy.  

Sr.    service  
1.     customer-service
2.     account-service


**Flyway-**  

Flyway is used for sql script versioning. 
(currently not in use as it was creating some problem while executing the script in db. 
like when we run flywayMigrate task it runs the query in db and makes the table in camel case no matter what we provide)

Among multiple approach here we adopted centralized migration scripts.
Here we create one separate module in which we keep all SQL scripts as versioned and flyway tool is used here to apply the script in the DB.