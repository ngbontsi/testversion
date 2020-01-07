CREATE SCHEMA `gfarms` ;

CREATE TABLE `gfarms`.`bill` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `productId` INT NULL,
  `cost` VARCHAR(45) NULL,
  `bildate` DATETIME NULL,
  `fullPaid` TINYINT NULL,
  `payment` VARCHAR(45) NULL,
  `outstanding` VARCHAR(45) NULL,
  PRIMARY KEY (`uid`));


  CREATE TABLE `gfarms`.`customer` (
    `uid` INT NOT NULL,
    `firstName` VARCHAR(45) NOT NULL,
    `lastName` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NULL,
    `contact` VARCHAR(45) NULL,
    PRIMARY KEY (`uid`));

    CREATE TABLE `gfarms`.`product` (
      `uid` INT NOT NULL,
      `productName` VARCHAR(45) NOT NULL,
      `price` VARCHAR(45) NOT NULL,
      PRIMARY KEY (`uid`));

      CREATE TABLE `gfarms`.`orders` (
        `uid` INT NOT NULL,
        `customerId` INT NOT NULL,
        `productId` INT NULL,
        `billId` INT NULL,
        `quantity` INT NULL,
        `orderDate` DATETIME NULL,
        PRIMARY KEY (`uid`));

