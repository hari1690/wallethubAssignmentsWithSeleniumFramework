# Assignments for Wallethub 

Project is designed based on Java,Selenium,TestNG and Maven. Works only on Chrome and can be enhanced for other browsers.

## Facebook Status post Test

# Test Coverage: 

*Post status on Facebook and assert the posted status
*While trying to post status second time again in a quick span of time, facebook will throw an error. That is also handled in the code


Point to be Noted:

* Provide fb user name and password in config.properties file (Already given username may or may not work, as used temporary mail server)


## WalletHub Review Test

# Test Coverage: 

* Post review for the first time login user (Fresh Review)
* Update review if posted already
* Assert result on both the cases


## How to Execute

* Ensure the Working Facebook Username,Password updated in the config.properties file located under src/main/resources
* Ensure the Working Wallethub Username,Password updated in the config.properties file located under src/main/resources
* Both testcases would run as both are included in testng.xml. If there is a need to change, please include/exclude accordingly
* Run the POM.xml (maven clean, install, test (while test phase, execution happens))

# Enhancements proposal

* Have to add Test Failure screenshot capture
* Have to add logs (Temporarily added print statements, not a recommended practice)
* Have to add better reporting

# Tools required

* Install Java 8.0 or more
* Install TestNG (For local testng execution)
* Install Maven





