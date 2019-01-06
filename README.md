# About

##### Financial System project
 * demonstrates layer architecture
 * demonstrates different test strategies

![test pyramid](https://abstracta.us/wp-content/uploads/2015/10/Screen-Shot-2017-03-27-at-6.21.09-PM-min-1.png)

## Requirements
* Java 11
* Maven 3.6.0

## Build
* mvn clean install

# Usage
## Running service
###### Run service
* java -jar ./target/financial-service-jar-with-dependencies.jar

###### Service will be available at url:
* http://localhost:5050

# API

### Adding employee
#### Available employee types:
* regular
* manager
* vp
* contractor

#### Request
* curl --request POST \
  --url <SERVICE_URL:PORT>/employee \
  --header 'content-type: application/json' \
  --data '{
  "id": <EMPLOYEE_ID>,
  "type": "<EMPLOYEE_TYPE>",
  "salary": \<SALARY>
}'

#### Request example
* curl --request POST \
  --url http://localhost:5050/employee \
  --header 'content-type: application/json' \
  --data '{
  "id": 1,
  "type": "regular",
  "salary": 1000
}'

### Getting employee

#### Request
* curl --request GET \
  --url <SERVICE_URL:PORT>/employee/<EMPLOYEE_ID>

#### Request example
* curl --request GET \
  --url http://localhost:5050/employee/1

### Update salary

#### Request
* curl --request PUT \
  --url <SERVICE_URL:PORT>/employee/<EMPLOYEE_ID>/salary

#### Request example
curl --request PUT \
  --url http://localhost:5050/employee/1/salary