# Use-Case Specification: 

# Table of Contents
- [Analyze Acceleration Behavior](#1-analyze-acceleration-behavior)
    - [Brief Description](#11-brief-description)
    - [Screenshots](#12-screenshots)
- [Flow of Events](#2-flow-of-events)
    - [Basic Flow](#21-basic-flow)
    - [Alternative Flows](#22-alternative-flows)
- [Special Requirements](#3-special-requirements)
- [Preconditions](#4-preconditions)
- [Postconditions](#5-postconditions)

# 1. Analyze Acceleration Behavior
## 1.1 Brief Description

The process on the server of responding to a request from the app to retrieve the menu of a specific date.

## 1.2 Screenshots

n/a

# 2. Flow of Events
## 2.1 Basic Flow

![Provide Menus Diagram](ProvideMenusDiagram.png)

## 2.2 Alternative Flows
# 3. Special Requirements


# 4. Preconditions

There has to be a menu in the database on the Server for the given date.
The request has to contain a parameter "date" in the format "yyyymmdd" via the POST method. 

# 5. Postconditions

The Server should send a response with the correct menu for the given date.

# 6. Function Points
