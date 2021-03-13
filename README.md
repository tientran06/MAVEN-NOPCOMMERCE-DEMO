# MAVEN-NOPCOMMERCE-DEMO
1. Environment Set up
+ Eclipse IDE
Download and install Eclipse IDE from : https://www.eclipse.org/downloads/
+ Java JDK 1.8.0_251
+ Selenium 3.141.59
+ TestNG 6.9.10
+ Browser with latest version
+ Open project with Eclipse IDE

2. How to run Test
+ Run test from .xml file : ...\src\test\resources\runNopCommerce.xml
--> Right click on file > Run as> TestNG Test 
+ Run all tests with Maven: 
	- mvn clean
	- mnv test
	- Run via Eclipse : Right click on project > Run as > 1. Maven clean / 2. Maven test
+ The report is generated in: 
	- ExtentReport: ...\ExtentReportV4\ExtentReport.html
  - Surefire Report: ...\target\surefire-reports\html\index.html
  - Log Testscript: ...\logTestscript\NopCommerce.txt

3. The test automation framework is comprised of tools and libraries as below
+ Page Object Pattern
+ Selenium: Browser automation tool
+ JAVA: Programming language
+ Maven: Build and dependencies tool
+ Eclipse: Integrated Development Environment
+ Git-Github: Version Control, Git repository hosted server
+ ExtentReportV4: Reporting
