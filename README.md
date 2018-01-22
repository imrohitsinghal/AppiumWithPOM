# AppiumWithPOM

## How to Run:
 - Run this from terminal: ./gradlew cucumber
 
 
## Output:
 - For Report: open reports/cucumber/cucumber-html-report/index.html
 
 
## Improvements/Enhancements
 - Custom logic for Scroll and remove hard-coding for particular devices(Eg. Device - Nexus 6)
 - Visual comparisons for each screen using NAKAL utility
 - Adding support for GIF/Video for every test run (success/failure) to ease debugging on CI
 - Support for validating analytics at each test step
 - Segregating step definitions into more logical files(as per functionality) 

  
## Notes:
 - Code is written and tested using real/physical device (Nexus 6 - ANDROID 7.1.1)
 - Screenshot is only taken and embedded in case of failure
 - Pages does not have all the element locators
 - Improving reports in case of test failures
