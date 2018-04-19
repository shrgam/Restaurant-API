# Restaurant-API


This document explains the details about a typical Restaurant API . It assumes that the reader is unaware of the theory, rationale and technical details about the subject hence it will explain it from the basics to the final implementation. 

I hope after reading the document you would be able to make your own Restaurant API in minutes, and if you can’t, read again, you will probably get it!


What to expect in the rest of the document :


| No.  | Content | 
| ----- | ------------- |
| 1 | Deliverables  |
| 2 | Environment Setup  | 
| 3 | Design | 
| 4 | Implementation | 
| 5 | Future Scope | 
| 6 | References | 





1)Deliverables :

What will this API do ?

This Restaurant API  will suffice the following functions:

Give a list of Restaurants and its details ,i.e Name, Phone number, Rating, Average cost for two , online ordering availability.
Give a menu list of each restaurant , i.e Lunch, Breakfast , Dinner, Drinks etc.
Give a list of items in each Menu , i.e what all is available in the Lunch menu 
Update/ add/ delete restaurant entry
Update/ add/ delete  menu entry
Update/ add/ delete  menu item entry

Features that could be added - dynamic status of online delivery available or not based on the current time, location of the restaurant.
User capabilities could be added like:
Rating/feedback/pictures added by user 




2)This section will guide you on how to prepare a development environment to start working with Jersey Framework to run this RESTful Restaurant API. 


i)Setup Java Development Kit (JDK)
You can download the latest version of SDK from Oracle's Java site − Java SE Downloads.
If you are running Windows and installed the JDK in C:\jdk1.7.0_75, you would have to put the following line in your C:\autoexec.bat file.
set PATH = C:\jdk1.7.0_75\bin;%PATH% 
set JAVA_HOME = C:\jdk1.7.0_75

ii) Setup Eclipse IDE 
To install Eclipse IDE, download the latest Eclipse binaries from https://www.eclipse.org/downloads/. I have used Eclipse Luna. Once you downloaded the installation, unpack the binary distribution to a convenient location. For example, in C:\eclipse on windows, or /usr/local/eclipse on Linux/Unix and finally set the PATH variable appropriately.	
iii) Setup Jersey framework libraries. I have downloaded jaxrs-ri-2.26 from https://jersey.java.net/download.html.

iv) Setup Apache Tomcat 
You can download the latest version of Tomcat from https://tomcat.apache.org/. I have used apache-tomcat-8.0.50.

Tomcat can be started by executing the following commands on a windows machine, or you can simply double click on startup.bat.
%CATALINA_HOME%\bin\startup.bat
	
After a successful startup, the default web applications included with Tomcat will be available by visiting http://localhost:8080/. If everything is fine then it should display the following result :
 

If the port 8080 is unavailable , change the port number . I am running on port 8086.

v) MySQL Workbench for accessing the database , the file for the same is attached (database.sql). Add your MySql credentials in the DBManager file in the following line (jdbc:mysql://localhost:3306/test", "","") 

3) The design of the model is going to look something like this:

Here , there are 3 entities : 
1) Restaurant
2) Menu
3) Menu Items

Restaurant has one to many relationship with menus. I.e each Restaurant can have multiple menus. Similarly , each menu can have multiple menu items.

4) Implementation

With MySQl, Eclipse and Tomcat working , use the following requests for getting the end results:

i) To get list of all restaurants:

Enter the following on a web browser URL:

http://localhost:8086/ZapposAPI/rest/restaurant( Enter the port number your tomcat is running on)

Sample Response:

[{"id":1,"name":"Tamasha","onlineDelivery":true,"phonenumbers":"2179043445","price":50.0,"rating":5},
{"id":2,"name":"Chipotle","onlineDelivery":true,"phonenumbers":"520-218-8980","price":15.0,"rating":3}]

ii) To get list of menus for a particular restaurant :

http://localhost:8086/ZapposAPI/rest/restaurant/{restaurantId}/menu

For example:
http://localhost:8086/ZapposAPI/rest/restaurant/2/menu

Sample output:
[{"id":12,"name":"Lunch","restId":2},
{"id":13,"name":"Dinner","restId":2}]

iii) To get list of menu items in a menu:

http://localhost:8086/ZapposAPI/rest/menus/{menuId}/menuItems

For Example:
http://localhost:8086/ZapposAPI/rest/menus/12/menuItems
Sample Output:

[{"id":1,"menuId":12,"name":"Rice","price":21.0},
{"id":2,"menuId":12,"name":"Curry","price":10.0}]

iv) Create a restaurant entry:
POST /restaurant

v) Update a restaurant :
PUT /restaurant

vi) Delete a restaurant :
DELETE /restaurant/{restaurant id}

The same Create, Update , Delete methods are implemented for Menu and Menu items as well.

Use Cases:
Two cases for each situation have been tested : positive and negative .
I.e for example, an update was made or not made. Same has been tested for all methods.
While using the test cases, please use different values as these values may not work
with the new database

Error Codes Used:


| Code  | Description | Reason  |
| ----- | ------------- |-----|
| 200  | OK  |The request has succeeded |
| 400  | Bad Request  | The client has made a wrong request |
| 404  | Not Found |The Server has not found anything matching |


5) Future Scope:

i) Use of Spark
ii) Addition of a Caching Layer
iii) Authentication constraints could have been added

6) References:
•	https://www.tutorialspoint.com/restful/restful_first_application.htm
•	https://developers.zomato.com/documentation#!/restaurant/restaurant








