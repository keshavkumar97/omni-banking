# omni-banking
omni-banking is a banking application which follows microservice architecture and all modern technology and approach


**Deployment strategy-**

* The services contain migration sql scripts  which is applied to db with the help of **Flyway**.   
* But while deploying we need to opt a strategy to deploy all microservice because every microservice ahve some sql script and some are dependent on other.  
* So to make smooth deployment there are many approach we can take but here we opted for ***ordered service deployment*** strategy.  

Sr.    service  
1.     customer-service
2.     account-service
