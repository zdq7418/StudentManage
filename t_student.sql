/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.49-community : Database - db_student
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_student` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_student`;

/*Table structure for table `class_cour` */

DROP TABLE IF EXISTS `class_cour`;

CREATE TABLE `class_cour` (
  `Class_Id` int(11) NOT NULL,
  `Class_No` int(11) DEFAULT NULL,
  `Course_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Class_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_cour` */

/*Table structure for table `class_from` */

DROP TABLE IF EXISTS `class_from`;

CREATE TABLE `class_from` (
  `Class_NO` int(11) NOT NULL AUTO_INCREMENT,
  `Class_Name` varchar(50) DEFAULT NULL,
  `Class_Teacher` varchar(50) DEFAULT NULL,
  `Class_Remarks` varchar(100) DEFAULT NULL,
  `Teacher_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Class_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_from` */

/*Table structure for table `course_form` */

DROP TABLE IF EXISTS `course_form`;

CREATE TABLE `course_form` (
  `Course _Id` int(11) NOT NULL,
  `Course_Name` varchar(50) DEFAULT NULL,
  `Course_rem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Course _Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course_form` */

/*Table structure for table `prize_form` */

DROP TABLE IF EXISTS `prize_form`;

CREATE TABLE `prize_form` (
  `Prize_Id` int(11) NOT NULL,
  `Prize_Name` varchar(20) DEFAULT NULL,
  `Prize_Rem` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Prize_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prize_form` */

/*Table structure for table `prize_stud` */

DROP TABLE IF EXISTS `prize_stud`;

CREATE TABLE `prize_stud` (
  `Prize_NO` varchar(14) NOT NULL,
  `Prize_Stu` varchar(20) DEFAULT NULL,
  `Prize_Dat` datetime DEFAULT NULL,
  `Prize_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Prize_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prize_stud` */

/*Table structure for table `score_form` */

DROP TABLE IF EXISTS `score_form`;

CREATE TABLE `score_form` (
  `score_No` varchar(14) NOT NULL,
  `score_Per` varchar(20) DEFAULT NULL,
  `score_Cls` varchar(20) DEFAULT NULL,
  `score_Stu` varchar(20) DEFAULT NULL,
  `score_cou` varchar(20) DEFAULT NULL,
  `score_Sco` double DEFAULT NULL,
  `student_No` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`score_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score_form` */

/*Table structure for table `student_form` */

DROP TABLE IF EXISTS `student_form`;

CREATE TABLE `student_form` (
  `student_NO` varchar(20) NOT NULL,
  `student_Name` varchar(10) DEFAULT NULL,
  `student_Sex` char(2) DEFAULT NULL,
  `student_Bir` datetime DEFAULT NULL,
  `student_Cla` varchar(20) DEFAULT NULL,
  `student_Tel` varchar(13) DEFAULT NULL,
  `student_Esd` datetime DEFAULT NULL,
  `student_Add` varchar(60) DEFAULT NULL,
  `student_Rem` varchar(100) DEFAULT NULL,
  `student_Accout` varchar(20) DEFAULT NULL,
  `student_Urlimage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`student_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_form` */

/*Table structure for table `teacher_form` */

DROP TABLE IF EXISTS `teacher_form`;

CREATE TABLE `teacher_form` (
  `Teacher_Id` int(11) NOT NULL,
  `Teacher_Name` varchar(20) DEFAULT NULL,
  `Teacher_Account` varchar(20) DEFAULT NULL,
  `Teacher_tel` varchar(20) DEFAULT NULL,
  `Teacher_Urlimage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Teacher_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher_form` */

/*Table structure for table `user_form` */

DROP TABLE IF EXISTS `user_form`;

CREATE TABLE `user_form` (
  `UserAcct` varchar(10) NOT NULL,
  `Passwd` varchar(15) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `Depart` varchar(100) DEFAULT NULL,
  `ConPhone` varchar(50) DEFAULT NULL,
  `GrpSeq` int(11) DEFAULT NULL,
  `GrpName` varchar(50) DEFAULT NULL,
  `RoleSeq` int(11) DEFAULT NULL,
  `RoleName` varchar(50) DEFAULT NULL,
  `UserStatus` char(2) DEFAULT NULL,
  `CrtTime` datetime DEFAULT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  `LoginStatus` char(2) DEFAULT NULL,
  PRIMARY KEY (`UserAcct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_form` */

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `GrpSeq` int(11) NOT NULL AUTO_INCREMENT,
  `GrpName` varchar(50) DEFAULT NULL,
  `GrpStatus` char(2) DEFAULT NULL,
  `OperUer` varchar(30) DEFAULT NULL,
  `OperTime` datetime DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`GrpSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_group` */

/*Table structure for table `user_menu` */

DROP TABLE IF EXISTS `user_menu`;

CREATE TABLE `user_menu` (
  `MenuSeq` int(11) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(50) DEFAULT NULL,
  `FmenuSeq` int(11) DEFAULT NULL,
  `MenuLayer` int(11) DEFAULT NULL,
  `MenuPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MenuSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_menu` */

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*Table structure for table `user_rome` */

DROP TABLE IF EXISTS `user_rome`;

CREATE TABLE `user_rome` (
  `RoMeSeq` int(11) NOT NULL AUTO_INCREMENT,
  `RoleSeq` int(11) DEFAULT NULL,
  `MenuSeq` int(11) DEFAULT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RoMeSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_rome` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
