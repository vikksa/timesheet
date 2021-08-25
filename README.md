### Time Management Service

#### Introduction: 


#### Technical Stack

    Java 1.8
    Maven 3.6+
    Spring boot 2.4.1.RELEASE
    MySql8
    H2 database for test cases

#### Running the application

###### Run app using docker-compose

Job service is configured with docker-compose to run with mysql 8. To run the service run the following command:
```shell script
docker-compose up
```
In docker-compose two service is defined one is for mysql8 and second one is for service.

###### Run app using maven

```shell script
mvn spring-boot:run
```


