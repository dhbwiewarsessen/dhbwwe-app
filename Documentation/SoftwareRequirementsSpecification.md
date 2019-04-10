# DHBWieWarsEssen - Software Requirements Specification

# Table of Contents
- [Introduction](#1-introduction)
    - [Purpose](#11-purpose)
    - [Scope](#12-scope)
    - [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
    - [References](#14-references)
    - [Overview](#15-overview)
- [Overall Description](#2-overall-description)
- [Specific Requirements](#3-specific-requirements)
    - [Functionality](#31-functionality--data-analytics)
    - [Usability](#32-usability)
    - [Reliability](#33-reliability)
    - [Performance](#34-performance)
    - [Supportability](#35-supportability)
    - [Design Constraints](#36-design-constraints)
    - [Online User Documentation and Help System Requirements](#37-online-user-documentation-and-help-system-requirements)
    - [Purchased Components](#38-purchased-components)
    - [Interfaces](#39-interfaces)
    - [Licensing Requirements](#310-licensing-requirements)
    - [Legal, Copyright and other Notices](#311-legal-copyright-and-other-notices)
    - [Applicable Standards](#312-applicable-standards)
- [Supporting Information](#4-supporting-information)

# 1. Introduction

## 1.1 Purpose
The purpose of this document is to provide a general overview and a detailed description of the DHBWieWarsEssen project. In order of achieving this, it explains the general vision or rather the purpose and all the features of the product. This document offers insights into the system, its interfaces and the constraints of the product.

## 1.2 Scope
This document is designed for internal use only and will outline the development process of the project. An overview of the features and modules of the product are documented in the use case diagram (Overall Description).

## 1.3 Definitions, Acronyms and Abbreviations
|Term||
|-|-|
|**SRS**|Software Requirements Specification|
|**UC**|Use Case|
|**IDE**|Integrated development environment|

## 1.4 References
|Name|Link|
|-|-|
|Blog|https://dhbwiewarsessen.wordpress.com/ |
|Repository|https://github.com/TvRXVII/DHBWieWarsEssen |
|YouTrack|https://dhbwiewarsessen.myjetbrains.com/youtrack/issues |

## 1.5 Overview

The next chapter gives an overall description about the project an requirements such as functionality, usability and performance.



# 2. Overall Description
## 2.1. Idea

The Idea of our App is that you can improve the food in the Casino of the DHBW-Karlsruhe by giving constructive critic. We want to make this process happen through an Android App where you can rate your food, leave a comment on your rating, see the ratings of the other people and look whether the food got better or worse to the last time they served it. The idea is to put all these information on a server, also on the server are the login data of every person, that uses the App. Only a verified user can rate the dishes but everyone can look at the ratings. Furthermore there should be a website where the people from the DHBW-Casino can look at the ratings an comments.

## 2.2. General overview

![uc diagramm.png](UC%20Diagram.png "use case diagram")

## 2.3. User characteristics

Our target group is obviously the people eating in the Casino of the DHBW-Karlsruhe but also the people working there. So that they can get an better overview of how the food tastes and how it improved, or even what the students want more often.


# 3. Specific Requirements

## 3.1 Functionality
This section lists our use cases and elaborates their functionality in the App. 

### 3.1.1 UCs for Semester 3
These use cases are done in the third semester.

#### 3.1.1.1 Register
A new user is able to create a new User-Account. To register the user is asked to provide his or her full name, a username, an email and a password. The use case specification can be found [here](<https://github.com/TvRXVII/DHBWieWarsEssen/blob/master/Documentation/Register/UC%20Register.md>).

#### 3.1.1.2 Login
Already registered users are able to Login by using their username and password. Logged in users can create ratings and view all the rating they have already submitted. The use case specification can be found [here](<https://github.com/TvRXVII/DHBWieWarsEssen/blob/master/Documentation/Login/UC%20Login.md>).

#### 3.1.1.3 Logout
A logged in user can log out in order to go back and use the app as a user with fewer privileges. Unregistered or not logged in users can not submit ratings. The use case specification can be found [here](<https://github.com/TvRXVII/DHBWieWarsEssen/blob/master/Documentation/Logout/UC%20Logout.md>)

#### 3.1.1.4 List Ratings of other Users
A functionality all users can use. It shows all ratings given to a certain meal. The use case specification can be found [here](<https://github.com/TvRXVII/DHBWieWarsEssen/blob/master/Documentation/List%20ratings%20of%20other%20users/UC%20List%20ratings%20of%20other%20users.md>) 

#### 3.1.1.5 Manage Ratings
Logged-in users can edit and delete their own ratings. The use case specification can be found [here](<https://github.com/TvRXVII/DHBWieWarsEssen/blob/master/Documentation/Manage%20Rating/UC%20Manage%20Rating.md>)

#### 3.1.1.6 Provide Menu
The menus are provided for the app of the user. The use case specification can be found [here](https://github.com/TvRXVII/DHBWieWarsEssen/tree/master/Documentation/Provide%20Menu).

#### 3.1.1.7 Receive Menu
The menus get saved into the database. The use case specification can be found [here](https://github.com/TvRXVII/DHBWieWarsEssen/tree/master/Documentation/Receive%20Menu).


### 3.1.2 UCs for Semester 4
These use cases are done in the fourth semester.

#### 3.1.2.1 Submit Rating to Server
Logged-in users can submit ratings to meals. The use case specification can be found [here](<>)

#### 3.1.2.2 Sort Ratings
A feature all users can use. Sorting ratings by the amount of stars a meal received. The use case specification can be found [here](<>)

#### 3.1.2.3 Get Ratings from Server
A feature that should run in the background of the app to always be able to show all available ratings to the user. The use case specification does not exist yet.

#### 3.1.2.4Receive Rating (Server)
The server should be able to receive ratings from Clients and save them into the database ([Manage Ratings (Server)](#3125-manage-rating)). The use case specification does not exist yet.

#### 3.1.2.5 Provide Ratings of all users
The server should send a list of (all/requested) ratings on a request by a client. The use case specification does not exist yet.

#### 3.1.2.5 Manage Rating (Server)
The server should store new ratings in the database and also update existing ratings, that have been changed by the user. The use case specification does not exist yet.

#### 3.1.2.6 List Ratings (Server)
The server has to send a request to the database to list (all/requested) ratings. The use case specification does not exist yet.


## 3.2 Usability
### 3.2.1 Easy to use
The app should be easy to use and easy to understand. That way people won't have to spend a lot of time to learn how to use the app. An easy to use app will allow users to take a quick look at the ratings of the current day and decide wether to go to the canteen. 

## 3.3 Reliability
### 3.3.1 Stable Application
The application should be mostly bugfree to allow a reliable usage. There should be no crashes or freezes. 
### 3.3.2 Reliably Reachable Server
The main features of the app base mostly on communication with the server. Therefore the server must be reachable at all times to correctly provide menus and ratings when a user needs them. 

## 3.4 Performance
### 3.4.1 Smooth interface
A smooth interface is essential for an enjoyable and efficient experience. There should little to no waiting time between switching fragments. 
### 3.4.2 Short server response time
A smooth interface requires a server with short response times. A login is most of the time bottle-necked by the server rather than the client application. Showing ratings, meals and features like login and  logout rely on quick server responses.   

## 3.5 Supportability
### 3.5.1 Android
The app operates only on android to provide optimal application of programmer-experience during development.
A smartphone app is also a practical way to realize the idea of "DHBWieWarsEssen" because almost every person own a mobile device.

## 3.6 Design Constraints
We use Git for version control and YouTrack is our project management tool. 
### 3.6.1 MVC Architecture
Because we create an android app we use the Model-View-Controller Architecture. 
In the Model we have classes which extend the Observable class. Those classes contain data we get from the server and notify the Activitys in the Controller when something changed.
### 3.6.2 Programming Languages
The app is developed in Java and we use Android Studio as out IDE.
The database system is based on SQL , and PHP scripts are used by the application for reading and writing actions.

## 3.7 Online User Documentation and Help System Requirements
N/A

## 3.8 Purchased Components
N/A

## 3.9 Interfaces
### 3.9.1 User Interfaces
- "Main screen" shows menu of the current day.
- List of all ratings to a meal.
- List of all ratings created by the user that is logged in.
- "Create Ratings" view.

### 3.9.2 Hardware Interfaces
N/A

### 3.9.3 Software Interfaces
N/A

### 3.9.4 Communications Interfaces
A connection to the server must be available in order to read and write information to the database.   

## 3.10 Licensing Requirements
N/A

## 3.11 Legal, Copyright and other Notices
N/A

## 3.12 Applicable Standards
N/A

# 4. Supporting Information
N/A
