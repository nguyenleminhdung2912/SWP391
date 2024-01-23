-- Disable foreign key --
SET FOREIGN_KEY_CHECKS = 0;

-- Create Account Table --
DROP TABLE IF EXISTS `account`;
CREATE TABLE account (
    account_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role_ID TINYINT DEFAULT 1 NOT NULL  
);
 
 -- Create Guest Table --
 DROP TABLE IF EXISTS `guest`;
CREATE TABLE guest(
    guest_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	account_ID INT NOT NULL,
    CONSTRAINT account_id FOREIGN KEY (account_ID) REFERENCES account(account_ID),
    name VARCHAR (60) NOT NULL ,
    phone VARCHAR (10) NOT NULL ,
    gender VARCHAR (10) NOT NULL ,
    picture BLOB
);

 -- Create Host Table --
 DROP TABLE IF EXISTS `host`;
CREATE TABLE host(
    host_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_ID INT NOT NULL,
    FOREIGN KEY (account_ID) REFERENCES account(account_ID),
    name VARCHAR (60) NOT NULL ,
    phone VARCHAR (10) NOT NULL ,
    picture BLOB
);

 -- Create Service Table --
 DROP TABLE IF EXISTS `Service`;
CREATE TABLE Service(
    service_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    host_ID INT NOT NULL,
    FOREIGN KEY (host_ID) REFERENCES Host(host_ID),
    name VARCHAR (60) NOT NULL ,
    price DECIMAL(10, 2) NOT NULL ,
    description text,
    picture BLOB
);

 -- Create Package Table --
 DROP TABLE IF EXISTS `Package`;
CREATE TABLE Package(
    package_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    host_ID INT NOT NULL,
    FOREIGN KEY (host_ID) REFERENCES Host(host_ID),
    name VARCHAR (60) NOT NULL ,
    price DECIMAL(10, 2) NOT NULL ,
    description text,
    picture BLOB
);

 -- Create Package contains Services Table --
 DROP TABLE IF EXISTS `ServiceOfPackage`;
CREATE TABLE ServiceOfPackage(
    ServiceOfPackage_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    package_ID INT NOT NULL,
    FOREIGN KEY (package_ID) REFERENCES Package(package_ID),
    service_ID INT NOT NULL,
    FOREIGN KEY (service_ID) REFERENCES Service(service_ID)
);

 -- Create Schedule Table --
 DROP TABLE IF EXISTS `Schedule`;
CREATE TABLE Schedule(
    schedule_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    working_date DATE,
    busy boolean
);

 -- Create Schedule Detail Table --
 DROP TABLE IF EXISTS `Schedule_Detail`;
CREATE TABLE Schedule_Detail(
    schedule_detail_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    schedule_ID INT NOT NULL,
    FOREIGN KEY (schedule_ID) REFERENCES Schedule(schedule_ID),
    host_ID INT NOT NULL,
	FOREIGN KEY (host_ID) REFERENCES Host(host_ID)
);

 -- Create Feedback Table --
 DROP TABLE IF EXISTS `Feedback`;
CREATE TABLE Feedback(
    feedback_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    service_ID INT NOT NULL,
    FOREIGN KEY (service_ID) REFERENCES Service(service_ID),
    guest_ID INT NOT NULL,
    FOREIGN KEY (guest_ID) REFERENCES Guest(guest_ID),
    description text,
    feedback_date date
);

-- Enable foreign key --
SET FOREIGN_KEY_CHECKS = 1;