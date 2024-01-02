A. The BaseTest which contains the driver configuration has been configured with mobile browsers as well but some of the datas for mobile automation are hard coded like "UDID" , Platform Version , Android Version etc , Drivers Executables path configuration .
B. The configuration for web browsers is dynamic and no hard coded datas is there in the framework .
C. ExtentReport with screenshot capture method along with retry analyser  (which helps in re-run the test) and iTestListener have been integrated into the framework .
D. The framework is jenkins ready and the pom file has been configured for jenkins . I am attaching a video with this emai which validates this point where I am triggering the whole automation suite from command prompt using mvn clean test -P $ProfilesName command .
E. The framework is also configured for the Selenium grid . All the tests' execution are being handled by selenium grid . The selenium grid URL can be passed in test/java/caw/studios/resources/URL.json .
F.  All the data is being retrieved from the JSON file placed under  test/java/caw/studios/resources/ . 
G. The framework can also work on Docker by simply passing the Docker's URL into the URL.json file and then it can be triggered by anyone with a Jenkins profile .
H. The framework is compatible for parallel execution of tests in multiple browsers as shown in the video attached with this email . 
