# Test plan

## 1.	Introduction
### 1.1	Purpose
- The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given iteration. It describes the approach to testing the software, and is the top-level plan generated and used by managers to direct the test effort.

  This *Test Plan* for the DHBWieWarsEssen-App supports the following objectives:

  •           figure out if all functionalities work 

  •           find out if all wrong inputs are catched
### 1.2	Scope
Unit-Tests are planned to cover most of the Use-Cases for the front-end.
### 1.3	Intended Audience
The targeted Audience are Developers working on this Project.
### 1.4	Document Terminology and Acronyms
- **SRS**	Software Requirements Specification
- **n/a**	not applicable
- **tbd**	to be determined
### 1.5	 References
- n.a.

## 2.	Evaluation Mission and Test Motivation
### 2.1	Background
The main goal of testing is to be sure, that after small changes the functionalities will still work without problems.

Furthermore to find out where Bugs could possibly hide. 

### 2.2	Evaluation Mission
·        find as many bugs as possible

·        make sure everything works after changes

·        advise about testing

### 2.3	Test Motivators
The motivation for testing our App with Unit-Test is, that our App has the quality we imagined at the beginning. Another point is time, because it is much faster to test the App with Unit-Test rather then test everything yourself.

## 3.	Target Test Items
For testing we decided to test Use-Cases that are linked to the frontend.

## 4.	Outline of Planned Tests
### 4.1	Outline of Test Inclusions
n.a.
### 4.2	Outline of Other Candidates for Potential Inclusion
n.a.
## 5.	Test Approach
### 5.1	Testing Techniques and Types
#### 5.1.1	Function and Database Integrity Testing
The section Tested is Front- and BackEnd.

#### 5.1.2	Unit Testing
|Technique Objective:| Target: LogIn, Create and   Delete Rating                    |
|---|---|
| Technique:              | Unit-Tests                                                   |
| Oracles:                | -Success   <br />- Errormessages                             |
| Required Tools:         | Unit-Test-Interface                                          |
| Success Criteria:       | - Server returns success, after sending data   <br />- Correct Errormessage gets   displayed |
| Special Considerations: | A working   Internetconnection                               |
|                         |                                                              |
#### 5.1.3	Business Cycle Testing
n/a
#### 5.1.4	User Interface Testing
Automated with use of Cucumber and Feature-Files
#### 5.1.5	Performance Profiling 
n/a
#### 5.1.6	Load Testing
n/a
#### 5.1.7	Stress Testing
n/a
#### 5.1.8	Volume Testing
n/a
#### 5.1.9	Security and Access Control Testing
n/a
#### 5.1.10	Failover and Recovery Testing
n/a
#### 5.1.11	Configuration Testing
n/a
#### 5.1.12	Installation Testing
n/a
## 6.	Entry and Exit Criteria
### 6.1	Test Plan
#### 6.1.1	Test Plan Entry Criteria
Stable Internet connection.
#### 6.1.2	Test Plan Exit Criteria
Success from Server or right Error
## 7.	Deliverables
### 7.1	Test Evaluation Summaries
The tests are mainly for the Input the User makes.
### 7.2	Reporting on Test Coverage
Not yet decided
### 7.3	Perceived Quality Reports
n.a.
### 7.4	Incident Logs and Change Requests
n/a
### 7.5	Smoke Test Suite and Supporting Test Scripts
n/a
### 7.6	Additional Work Products
n/a
#### 7.6.1	Detailed Test Results
n/a
#### 7.6.2	Additional Automated Functional Test Scripts
n/a
#### 7.6.3	Test Guidelines
n.a.

#### 7.6.4	Traceability Matrices
n/a
## 8.	Testing Workflow
Unit-Tests in IDE
## 9.	Environmental Needs
### 9.1	Base System Hardware
n/a
### 9.2	Base Software Elements in the Test Environment

| **Software Element Name** | **Version** | **Type and Other Notes** |
| ------------------------- | ----------- | ------------------------ |
| Android Studio            |             | IDE                      |
| Android 8.1               |             | Operating System         |
### 9.3	Productivity and Support Tools
n/a
## 10.	Responsibilities, Staffing, and Training Needs
### 10.1	People and Roles

| **Human Resources** |                     |                                                     |
| ------------------- | ------------------- | --------------------------------------------------- |
| **Role**            | **Person assigned** | **Specific Responsibilities or Comments**           |
| Test Manager        | Thimo               | · Plan Test   <br />· Code Tests   <br />· Do Tests |
### 10.2	Staffing and Training Needs
n/a
## 11.	Iteration Milestones
We want to cover all Use-Cases of the frontend

# 12.    Risks, Dependencies, Assumptions, and Constraints

| **Risk**                           | **Mitigation Strategy**                 | **Contingency    (Risk is realized)** |
| ---------------------------------- | --------------------------------------- | ------------------------------------- |
| Problems with Testcode and   Steps | Work on more workable   Testcode        | Publish new Testcode                  |
| Code has lots of side effects      | Refactor code (Clean Code   principles) | publish new refactored tests          |

# 13.    Management Process and Procedures

n.a.