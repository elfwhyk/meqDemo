
# Selenium Demo 


This is setup to run chromedriver locally `https://chromedriver.chromium.org/downloads`

If you are running windows, put the `chromedriver` binary in `C:\\chromedriver.exe`
If you are running unix based, put the `chromedriver` binary in the root of this project for example `/Users/shane.burgoon/git/meqDemo-main/chromedriver.exe`
 **NOTE** I have not verified this works in unix, the code is currently hard coded expecting to find chromedriver in the aforementioned locations

To build jar: `mvn clean install`

To run tests:  in terminal, navigate to this project and run `java -cp target/meQDemo.jar com.simpleFormApp.Main`

This is currently coded to run locally on an individual machine.

This could be invoked as part of a CICD pipeline in a few ways
1. invoke a chromedriver instance within github actions docker container
2. execute against a hosted service such as browserstack, or saucelabs
3. execute against a remote, custom, selenium server, such as a selenium grid or lambda running in AWS

As part of the CICD pipeline, the result of this executable could be checked for success or failure, and either block or continue a deployment.


## Basic FrontEnd  Browser test flexing the following functionality:



The test will take a screenshot when it fails, grab console logs, and then close Browser cleanly.

**NOTE** As I do not fully understand the process which generates the widgets on the dashboard, I have not coded tests to validate their content or existence.
Were I to be part of the team, I would learn how this works, and discuss the best ways to validate the dynamic content loading.

## TODO
