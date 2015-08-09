# Expense Report
This is a simple expense report generator. This was created because it is becoming annoying to encode expenses in excel. (Or ako lang yun?). Anyway I was also hoping this lead to something other than a simple expense report generator.

## Features (Sorry 2 palang hehe)
1. Autocomplete of receipts based on TIN.
2. Generation of a simple excel file where the rows corresponds to the data we need for our expense report. Note that his WILL NOT generate an excel file that is the same as our expense report. But it will generate the rows we needed. So for now after generating, just copy and paste the rows to our expense report.

## How to set this up?
Unfortunately there is no server for this so for now we need to run the web app in our own machines. For QAs and other non-technical people I apologize for a not so detailed explanation. 

### What tools do you need?
1. Maven 3
2. Git
3. Java 7
4. MySQL

### Steps
1. Clone the latest version of opentides. 
```git clone https://github.com/Ideyatech/opentides3.git```
2. Install it to your repository
```mvn clean install -Dmaven.test.skip=true```
3. Clone the expense report app
```git clone https://github.com/gcbjr/idt_expense_report.git```
4. Create a new MySQL database.
```
mysql -u root -ppassword;
create user 'allowance'@'localhost' identified by 'password';
create schema allowance;
grant all privileges on allowance.* to 'allowance'@'localhost';
```
Then just run jetty
```
mvn jetty:run -Pdev
```
That's it! You should be able to access the web app in http://localhost:8080/
Username is admin password is opentides.

## Some technical stuffs
* This was created using opentides 3.1. 
* The database details used by jetty can be found in jetty-env.xml under src/main/resources. The following are the database default values:
```
username = allowance
password = password
db name = allowance
db host = localhost
db port = 3306
```
* You can define your own jetty-env.xml. Create a new profile in your maven settings.xml like this:
```
<profile>
		<id>myProfile</id>
		<properties>
			<jettyEnvPath>\path\to\jetty-env.xml</jettyEnvPath>
		</properties>
	</profile>
```
Then just run the app:
```
mvn jetty:run -PmyProfile
```

##Limitations/Issues/Known Bugs
1. No server so kanya kanya muna. I think may issue sa confidentiality ng ating allowance since tayo tayo ding devs and magmemaintain nito if ever. 
2. Ako palang ang nagtetest so use at your own risk. But I was able to generate my expense report correctly.
3. The excel generated is not the same as our expense report.
4. When adding a new expense report make sure to clear the TIN. 
5. Updating expense report not yet working because of the autocomplete thingy.

##Mga naiisip kong features...
1. Store photos of receipts so we don't have to photocopy if pwede. Sayang kasi papel...
2. Mobile app para deretso na dito after mo kumain or magnakaw ng resibo
3. Integrate with HR system? (Need to take into consideration the confidentiality issue above)
4. Eto pang PHD. Take a picture of the receipt. Upload. That's it.

