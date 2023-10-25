
# Upgrade

The workflow under test is:
  homepage -> open account -> enter profile information ->













# MeQuilibrium 


This is setup to run chromedriver locally `https://chromedriver.chromium.org/downloads`

If you are running windows, put the `chromedriver` binary in `C:\\chromedriver.exe`
If you are running unix based, put the `chromedriver` binary in the root of this project for example `/Users/shane.burgoon/git/meqDemo-main/chromedriver.exe`
 **NOTE** I have not verified this works in unix, the code is currently hard coded expecting to find chromedriver in the aforementioned locations

To build jar: `mvn clean install`

To run tests:  in terminal, navigate to this project and run `java -cp target/meQDemo.jar com.meq.Main`

This is currently coded to run locally on an individual machine.

This could be invoked as part of a CICD pipeline in a few ways
1. invoke a chromedriver instance within github actions docker container
2. execute against a hosted service such as browserstack, or saucelabs
3. execute against a remote, custom, selenium server, such as a selenium grid or lambda running in AWS

As part of the CICD pipeline, the result of this executable could be checked for success or failure, and either block or continue a deployment.


## Basic FrontEnd  Browser test flexing the following functionality:

- can successfully login with known good credentials
  - should land on dashboard
- NavBar navigation should work for each of the following:
  - Me
    - Profile
    - Skills Journey
    - Activities
    - Journal
    - Saved for Later
  - Discover
    - Browse All Topics
    - Skills
    - Activities
    - Cup of Calm Blog
    - Calm-Cast Videos
    - Meditations

The test will take a screenshot when it fails, grab console logs, and then close Browser cleanly.

**NOTE** As I do not fully understand the process which generates the widgets on the dashboard, I have not coded tests to validate their content or existence.
Were I to be part of the team, I would learn how this works, and discuss the best ways to validate the dynamic content loading.

## Login test

- navigate to `https://mymeq.com/`
- enter email address into field id = `email` type = `email` email = `shane.burgoon@gmail.com`
  - click `continue` buttonId = `qa-enter-email-continue`
  - should result in the same `https://mymeq.com/`
    - wait for password fieldId = `password` type = `password`
      - makes this request `https://mymeq.com/json/auth/email` with body `{"mail":"shane.burgoon@gmail.com"}:`
- enter password `K45ufJ4CfZLhyCb$`
  - click `Login` buttonId = `qa-enter-password-login`
  - oh look, a JWT    `'eyJzdHJpbmciOiJMb2dpbiIsImhhc2giOiI5ZDYzMjJjMWY0ZDlkM2YzOGFlZDhiZmJlMGIyYmNhZGY2NmFkODJjMDA4NDc2ZmE2MjU0MWZhMDY5MTM4ZTk0In0='`
  - should land on `https://mymeq.com/my-meq/dashboard/#/my-meq/my-active-skill`
  - wait for what to load?


## Takeaways and questions

- hit 429 on login pretty quick, like 5 attempts in a minute
  - would benefit from a limit reset count in the headers so tests could throttle appropriately instead of making arbitrary waits
  - is this super prone to DDOS? what ddos protection is in place for the login? IP rate limiting only?
      based on the fact I cannot login anymore, looks like its IP based. This is really easy to break using a distributed DDOS attack
      why is the failed login attempt a 200 with body instead of 401?

## TODO

1. gatling test of the various API endpoints to see how fast they fall over?
2. collect network traffic history from the webBrowser
3. create remote webDriver executor in AWS or BrowserStack
4. run in a github actions pipeline
5. introduce the concept of automation breaking failures vs attempting to continue execution on some failures
   6. collect failures and report them in a list, instead of breaking on the first failure
