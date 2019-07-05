# Spring Boot test project

## Task Description

### Roaming detection

XYZ telecom is in its early days and just installed their first charging network elements. They asked us to develop a system providing an API to indicate if that subscriber is browsing from XYZ's network or if they are roaming. 

### roamingCheck API 

This REST API consists of a POST request, passing a JSON payload: 
 
> { 
"subscriberNumber" : 15554312030, "ipAddress": "200.142.3.123" 
} 

And returns a JSON response: 

> { 
"subscriberNumber": 15554312030, "location": "HOME" 
} 

### Requirements: 

1. The list of IP addresses from XYZ's network elements is fixed, and can be hardcoded as this list: 200.142.3.123 , 201.34.100.1 , 202.142.4.15 , 203.6.100.77 

2. If the ipAddress attribute from the roamingCheck API request corresponds to one of XYZ's network elements, the subscriber is considered to be HOME. Otherwise, subscriber is ROAMING. This value shall be returned in response's location field. 

## Solution

This repository contains a solution as a sample Spring Boot application that can be run locally or on a server.

To run it locally please follow these steps:
1. Make sure you have JDK 8 installed together with Maven (it's been tested with OpenJDK 1.8.0_212 and Apache Maven 3.6.0 for Linux).
2. Clone the repository.
3. Run **mvn clean install**
4. Run **mvn spring-boot:run**
5. Submit POST request to http://localhost:8080/roamingCheck with parameters as described at the task.

### Configuration
To modify a list of home IP addresses you can edit app.ips parameter at application.properties file. Make sure all home IPs are comma-separated.