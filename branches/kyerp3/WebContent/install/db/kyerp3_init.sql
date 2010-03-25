
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;




CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ED033D469BEF610` (`region_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `logopath` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameSpell` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `parentBrand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3D75B677DE24E72` (`parentBrand_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `accountReceivable` decimal(19,2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `helpCode` varchar(255) DEFAULT NULL,
  `logopath` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameSpell` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `www` varchar(255) DEFAULT NULL,
  `clientType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7877DFEB7A7D8451` (`clientType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `clienttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentClientType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB887F5C5CF475FE7` (`parentClientType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `client_contact` (
  `clients_id` bigint(20) NOT NULL,
  `contacts_id` bigint(20) NOT NULL,
  KEY `FKF17A27AC2854EE94` (`clients_id`),
  KEY `FKF17A27ACF37C4770` (`contacts_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `contact_address` (
  `Contact_id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  UNIQUE KEY `address_id` (`address_id`),
  KEY `FK29D39695F954D483` (`Contact_id`),
  KEY `FK29D39695F893F24` (`address_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `contact_phone` (
  `Contact_id` bigint(20) NOT NULL,
  `phones_id` bigint(20) NOT NULL,
  UNIQUE KEY `phones_id` (`phones_id`),
  KEY `FK66E76C4F83D200AD` (`phones_id`),
  KEY `FK66E76C4FF954D483` (`Contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `datadic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `dateDicType` int(11) DEFAULT NULL,
  `dictAllSpell` varchar(255) DEFAULT NULL,
  `dictOrder` int(11) NOT NULL,
  `dictSpell` varchar(255) DEFAULT NULL,
  `dictText` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deptOrder` int(11) DEFAULT NULL,
  `deptStatus` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentDepartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA9601F72DEAD8FE1` (`parentDepartment_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `department_employee` (
  `Department_id` bigint(20) NOT NULL,
  `employees_id` bigint(20) NOT NULL,
  UNIQUE KEY `employees_id` (`employees_id`),
  KEY `FKE0699CFB89E3B44B` (`Department_id`),
  KEY `FKE0699CFBD36B1AD4` (`employees_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `department_role` (
  `Department_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  UNIQUE KEY `roles_id` (`roles_id`),
  KEY `FKE0C5654389E3B44B` (`Department_id`),
  KEY `FKE0C565431B705D1A` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bianZhi` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `contractMatureDate` datetime DEFAULT NULL,
  `contractRemark` varchar(255) DEFAULT NULL,
  `contractSignDate` datetime DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `disabilityIdcard` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `empNo` varchar(255) DEFAULT NULL,
  `graduteDate` datetime DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `infoStatus` int(11) DEFAULT NULL,
  `insurance` varchar(255) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `jobChanges` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `msn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `nativeplace` varchar(255) DEFAULT NULL,
  `participateDate` datetime DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `physical` varchar(255) DEFAULT NULL,
  `polity` varchar(255) DEFAULT NULL,
  `qq` int(11) DEFAULT NULL,
  `recognizor` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `rewardsAndPunishiment` varchar(255) DEFAULT NULL,
  `rpraddress` varchar(255) DEFAULT NULL,
  `rprtel` varchar(255) DEFAULT NULL,
  `rprtype` int(11) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `selfDesc` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `totp` varchar(255) DEFAULT NULL,
  `weeding` varchar(255) DEFAULT NULL,
  `workStatus` int(11) DEFAULT NULL,
  `workTel` varchar(255) DEFAULT NULL,
  `workTrain` varchar(255) DEFAULT NULL,
  `zhiChen` varchar(255) DEFAULT NULL,
  `zhiChenDate` datetime DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4AFD4ACE89E3B44B` (`department_id`),
  KEY `FK4AFD4ACE2282A8A1` (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `employeefamily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `familyOrder` int(11) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `polity` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF49EBFD270DD670B` (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `employeeresume` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `resumeOrder` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9542C9B70DD670B` (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `inouttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `inOutMark` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentInOutType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB82E2A3EBEF4F2` (`parentInOutType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `instock` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `arriveDate` datetime DEFAULT NULL,
  `billCost` decimal(19,2) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `writeDate` datetime DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `Supplier_id` bigint(20) DEFAULT NULL,
  `checkEmployee_id` bigint(20) DEFAULT NULL,
  `checkUser_id` bigint(20) DEFAULT NULL,
  `inOutType_id` bigint(20) DEFAULT NULL,
  `keeper_id` bigint(20) DEFAULT NULL,
  `purchaseOrder_id` bigint(20) DEFAULT NULL,
  `writeEmployee_id` bigint(20) DEFAULT NULL,
  `writeUser_id` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `inputMan_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `taker_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD62400D1FCDBDCC` (`writeEmployee_id`),
  KEY `FKD62400D15374F129` (`inputMan_id`),
  KEY `FKD62400D1BBAC8A99` (`checkUser_id`),
  KEY `FKD62400D1CB1E6` (`buyer_id`),
  KEY `FKD62400D160E687E2` (`writeUser_id`),
  KEY `FKD62400D1D28B4C47` (`keeper_id`),
  KEY `FKD62400D1BDD58D03` (`checkEmployee_id`),
  KEY `FKD62400D17CF19CFE` (`customer_id`),
  KEY `FKD62400D1775D9B48` (`inOutType_id`),
  KEY `FKD62400D1FD32900C` (`Supplier_id`),
  KEY `FKD62400D114015FCE` (`taker_id`),
  KEY `FKD62400D1EAAF4FE8` (`purchaseOrder_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `instockdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `batchNumber` varchar(255) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `inStock_id` bigint(20) DEFAULT NULL,
  `inStockDetail_id` bigint(20) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54E96482F784FBE8` (`inStockDetail_id`),
  KEY `FK54E96482F3350F48` (`warehouse_id`),
  KEY `FK54E96482C40817E8` (`inStock_id`),
  KEY `FK54E9648294038E0C` (`unit_id`),
  KEY `FK54E96482F6A00E2C` (`material_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `material` (
  `material_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `materialName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `specification` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `paperHeight` int(11) DEFAULT NULL,
  `paperName` varchar(255) DEFAULT NULL,
  `paperWeight` int(11) DEFAULT NULL,
  `paperWidth` int(11) DEFAULT NULL,
  `pricePrePage` decimal(19,2) DEFAULT NULL,
  `squareMetrePrice` decimal(19,2) DEFAULT NULL,
  `tonnePrice` decimal(19,2) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `materialCategory_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK15ADC947F3350F48` (`warehouse_id`),
  KEY `FK15ADC947FB4D8FC8` (`brand_id`),
  KEY `FK15ADC94794038E0C` (`unit_id`),
  KEY `FK15ADC947FD32900C` (`supplier_id`),
  KEY `FK15ADC9479541D78C` (`materialCategory_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `materialcategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `parentMaterialCategory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF9ACB36562E22AA2` (`parentMaterialCategory_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `orderafterpress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemRemark` varchar(255) DEFAULT NULL,
  `pay` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA382C995C4407D7C` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `orderother` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(255) DEFAULT NULL,
  `itemRemark` varchar(255) DEFAULT NULL,
  `pay` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA3064482C4407D7C` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `orderpaper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(255) DEFAULT NULL,
  `paperAddAmount` int(11) NOT NULL,
  `paperAmount` int(11) NOT NULL,
  `paperFrom` int(11) DEFAULT NULL,
  `paperName` varchar(255) DEFAULT NULL,
  `paperStandAmount` int(11) NOT NULL,
  `payTotal` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `tonnePrice` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA30BD6FEC4407D7C` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `orderprepress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemRemark` varchar(255) DEFAULT NULL,
  `pay` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4E2E1CAEC4407D7C` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `orderpress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `colors` varchar(255) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemRemark` varchar(255) DEFAULT NULL,
  `pay` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `specialColors` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA31369B5C4407D7C` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `afterPressesAddFee` decimal(19,2) DEFAULT NULL,
  `afterPressesPay` decimal(19,2) DEFAULT NULL,
  `afterPressesStandPay` decimal(19,2) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `auditingTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deliverFee` decimal(19,2) DEFAULT NULL,
  `finishDate` datetime DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `orderNo` varchar(255) DEFAULT NULL,
  `othersPay` decimal(19,2) DEFAULT NULL,
  `paperAddFee` decimal(19,2) DEFAULT NULL,
  `paperPay` decimal(19,2) DEFAULT NULL,
  `paperStandPay` decimal(19,2) DEFAULT NULL,
  `payablefee` decimal(19,2) DEFAULT NULL,
  `paymentWay` int(11) DEFAULT NULL,
  `paymentstate` bit(1) DEFAULT NULL,
  `perPressAddFee` decimal(19,2) DEFAULT NULL,
  `perPressPay` decimal(19,2) DEFAULT NULL,
  `perPressStandPay` decimal(19,2) DEFAULT NULL,
  `pressPay` decimal(19,2) DEFAULT NULL,
  `pressesAddFee` decimal(19,2) DEFAULT NULL,
  `pressesStandPay` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sampleDate` datetime DEFAULT NULL,
  `sizeString` varchar(255) DEFAULT NULL,
  `sizeType` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `totalPrice` decimal(19,2) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `auditingEmployee_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `createOrderEmployee_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `orderEmployee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3DF62E52C71AE84` (`auditingEmployee_id`),
  KEY `FKC3DF62E5F954D483` (`contact_id`),
  KEY `FKC3DF62E5DB9E0BF9` (`createOrderEmployee_id`),
  KEY `FKC3DF62E57CF19CFE` (`customer_id`),
  KEY `FKC3DF62E5441E2FD` (`orderEmployee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `outstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `billCost` decimal(19,2) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `outDate` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `writeDate` datetime DEFAULT NULL,
  `checkEmployee_id` bigint(20) DEFAULT NULL,
  `checkUser_id` bigint(20) DEFAULT NULL,
  `inOutType_id` bigint(20) DEFAULT NULL,
  `keeper_id` bigint(20) DEFAULT NULL,
  `receiveDepartment_id` bigint(20) DEFAULT NULL,
  `receiveEmployee_id` bigint(20) DEFAULT NULL,
  `writeEmployee_id` bigint(20) DEFAULT NULL,
  `writeUser_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6701C8819455848` (`receiveEmployee_id`),
  KEY `FK6701C88FCDBDCC` (`writeEmployee_id`),
  KEY `FK6701C88BBAC8A99` (`checkUser_id`),
  KEY `FK6701C8860E687E2` (`writeUser_id`),
  KEY `FK6701C88B8144A48` (`receiveDepartment_id`),
  KEY `FK6701C88D28B4C47` (`keeper_id`),
  KEY `FK6701C88BDD58D03` (`checkEmployee_id`),
  KEY `FK6701C88775D9B48` (`inOutType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `outstockdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `batchNumber` varchar(255) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `outStock_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35099AF9F3350F48` (`warehouse_id`),
  KEY `FK35099AF994038E0C` (`unit_id`),
  KEY `FK35099AF9F6A00E2C` (`material_id`),
  KEY `FK35099AF9495E566C` (`outStock_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `pressmachine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `presswork` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `auditingTime` datetime DEFAULT NULL,
  `deliverWay` varchar(255) DEFAULT NULL,
  `finishDate` datetime DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `packageEveryAmount` int(11) NOT NULL,
  `packageType` varchar(255) DEFAULT NULL,
  `paperRemark` varchar(255) DEFAULT NULL,
  `prePressRemark` varchar(255) DEFAULT NULL,
  `pressRemark` varchar(255) DEFAULT NULL,
  `pressworkNo` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sampleDate` datetime DEFAULT NULL,
  `sizeString` varchar(255) DEFAULT NULL,
  `sizeType` varchar(255) DEFAULT NULL,
  `viewTimes` int(11) NOT NULL,
  `auditingEmployee_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `createPressworkEmployee_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `pressworkEmployee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4147B1B42C71AE84` (`auditingEmployee_id`),
  KEY `FK4147B1B49DB028B7` (`pressworkEmployee_id`),
  KEY `FK4147B1B4F954D483` (`contact_id`),
  KEY `FK4147B1B4751305F1` (`client_id`),
  KEY `FK4147B1B4C4407D7C` (`order_id`),
  KEY `FK4147B1B4A89773B3` (`createPressworkEmployee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `pressworkafterpress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemRemark` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `presswork_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36D8F49BAD466992` (`presswork_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `pressworkpaper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `cutHight` int(11) NOT NULL,
  `cutSize` int(11) NOT NULL,
  `cutWidth` int(11) NOT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `paper` tinyblob,
  `paperAddAmount` int(11) NOT NULL,
  `paperAmount` int(11) NOT NULL,
  `paperFrom` int(11) DEFAULT NULL,
  `paperFullAmount` int(11) NOT NULL,
  `paperStandAmount` int(11) NOT NULL,
  `presswork_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB49C6038AD466992` (`presswork_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `pressworkpress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `colors` varchar(255) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `plate` tinyblob,
  `plateAmount` int(11) NOT NULL,
  `plateSeriesAmount` int(11) NOT NULL,
  `pressMachine` tinyblob,
  `pressType` varchar(255) DEFAULT NULL,
  `specialColors` varchar(255) DEFAULT NULL,
  `presswork_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4A3F2EFAD466992` (`presswork_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `purchaseorder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `arriveDate` datetime DEFAULT NULL,
  `billCost` decimal(19,2) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `writeDate` datetime DEFAULT NULL,
  `Supplier_id` bigint(20) DEFAULT NULL,
  `applicant_id` bigint(20) DEFAULT NULL,
  `applicationDepartment_id` bigint(20) DEFAULT NULL,
  `checkEmployee_id` bigint(20) DEFAULT NULL,
  `checkUser_id` bigint(20) DEFAULT NULL,
  `inOutType_id` bigint(20) DEFAULT NULL,
  `writeEmployee_id` bigint(20) DEFAULT NULL,
  `writeUser_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDFD4BAADFCDBDCC` (`writeEmployee_id`),
  KEY `FKDFD4BAADC1FED0FB` (`applicationDepartment_id`),
  KEY `FKDFD4BAADBBAC8A99` (`checkUser_id`),
  KEY `FKDFD4BAAD60E687E2` (`writeUser_id`),
  KEY `FKDFD4BAADBDD58D03` (`checkEmployee_id`),
  KEY `FKDFD4BAAD775D9B48` (`inOutType_id`),
  KEY `FKDFD4BAADFD32900C` (`Supplier_id`),
  KEY `FKDFD4BAAD71EE5E37` (`applicant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `purchaseorderdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `billCount` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `recvCount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `purchaseOrder_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3146495E94038E0C` (`unit_id`),
  KEY `FK3146495EF6A00E2C` (`material_id`),
  KEY `FK3146495EEAAF4FE8` (`purchaseOrder_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26F49689E3B44B` (`department_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `role_systemresource` (
  `Role_id` bigint(20) NOT NULL,
  `systemResources_id` bigint(20) NOT NULL,
  KEY `FKCF5EC2467D57E4C1` (`Role_id`),
  KEY `FKCF5EC246FA83A688` (`systemResources_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `role_user` (
  `Role_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FK8B6B91F47D57E4C1` (`Role_id`),
  KEY `FK8B6B91F41B738144` (`users_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `cost` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4C806F694038E0C` (`unit_id`),
  KEY `FK4C806F6F6A00E2C` (`material_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `stockdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `batchNumber` varchar(255) DEFAULT NULL,
  `cost` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `stock_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3377AE7F3350F48` (`warehouse_id`),
  KEY `FK3377AE7634AB168` (`stock_id`),
  KEY `FK3377AE794038E0C` (`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `helpCode` varchar(255) DEFAULT NULL,
  `logopath` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameSpell` varchar(255) DEFAULT NULL,
  `payCost` decimal(19,2) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `qualified` bit(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `www` varchar(255) DEFAULT NULL,
  `supplierType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0B65DEC754488EC` (`supplierType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `suppliertype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentSupplierType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81658B46BF05D702` (`parentSupplierType_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `supplier_brand` (
  `Supplier_id` bigint(20) NOT NULL,
  `brands_id` bigint(20) NOT NULL,
  KEY `FKCDEEFB54CDF1D363` (`brands_id`),
  KEY `FKCDEEFB54FD32900C` (`Supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `supplier_material` (
  `Supplier_id` bigint(20) NOT NULL,
  `materials_id` bigint(20) NOT NULL,
  KEY `FKC8E9863AFD32900C` (`Supplier_id`),
  KEY `FKC8E9863A114A9907` (`materials_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `systemfunctions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `systemModule_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB5E3720C5FC633C1` (`systemModule_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `systemfunctions_role` (
  `SystemFunctions_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FK224FA2E91B705D1A` (`roles_id`),
  KEY `FK224FA2E941F1DCF3` (`SystemFunctions_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `systemmodule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `chineseName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;



CREATE TABLE IF NOT EXISTS `systemresource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `systemModule_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54E7C77D5FC633C1` (`systemModule_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `systemresource_role` (
  `SystemResource_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FK128C0C18278A72C1` (`SystemResource_id`),
  KEY `FK128C0C181B705D1A` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `systemsettings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `varValue` varchar(255) DEFAULT NULL,
  `variableName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameSpell` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentUnit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK284DA44DE75222` (`parentUnit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `lastLoginIp` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `loginTimes` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK285FEB70DD670B` (`employee_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;



CREATE TABLE IF NOT EXISTS `user_role` (
  `User_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`roles_id`),
  KEY `FK8B9F886A2282A8A1` (`User_id`),
  KEY `FK8B9F886A1B705D1A` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `parentWarehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK615AA38A9668F2` (`parentWarehouse_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
