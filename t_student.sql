/*
SQLyog Ultimate v11.33 (64 bit)
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

CREATE TABLE `class_cour` (
  `Class_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Class_No` int(11) DEFAULT NULL,
  `Course_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Class_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_cour` */

/*Table structure for table `class_from` */

CREATE TABLE `class_from` (
  `Class_NO` int(11) NOT NULL AUTO_INCREMENT,
  `Class_Name` varchar(50) DEFAULT NULL,
  `Class_Teacher` varchar(50) DEFAULT NULL,
  `Class_Remarks` varchar(100) DEFAULT NULL,
  `Teacher_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Class_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `class_from` */

insert  into `class_from`(`Class_NO`,`Class_Name`,`Class_Teacher`,`Class_Remarks`,`Teacher_Id`) values (43,'软件140212','曾家岩',NULL,2),(44,'软件1088班','扎那地方',NULL,3);

/*Table structure for table `course_form` */

CREATE TABLE `course_form` (
  `Course _Id` int(11) NOT NULL AUTO_INCREMENT,
  `Course_Name` varchar(50) DEFAULT NULL,
  `Course_rem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Course _Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `course_form` */

insert  into `course_form`(`Course _Id`,`Course_Name`,`Course_rem`) values (5,'英语',NULL),(6,'加瓦',NULL),(8,'策士',NULL),(15,'哦哦空间',NULL),(16,'尴尬了',NULL),(17,'你问一下',NULL);

/*Table structure for table `prize_form` */

CREATE TABLE `prize_form` (
  `Prize_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Prize_Name` varchar(20) DEFAULT NULL,
  `Prize_Rem` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Prize_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prize_form` */

/*Table structure for table `prize_stud` */

CREATE TABLE `prize_stud` (
  `Prize_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Prize_NO` varchar(14) DEFAULT NULL,
  `Prize_Stu` varchar(20) DEFAULT NULL,
  `Prize_Dat` datetime DEFAULT NULL,
  PRIMARY KEY (`Prize_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prize_stud` */

/*Table structure for table `score_form` */

CREATE TABLE `score_form` (
  `score_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分数主键',
  `score_Cls` int(11) DEFAULT NULL COMMENT '学生班级ID',
  `score_Sco` double DEFAULT NULL COMMENT '考试分数',
  `student_No` varchar(20) DEFAULT NULL COMMENT '学生学号',
  `test_id` int(11) DEFAULT NULL COMMENT '考试ID',
  `student_Id` int(11) DEFAULT NULL COMMENT '学生Id',
  PRIMARY KEY (`score_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `score_form` */

insert  into `score_form`(`score_Id`,`score_Cls`,`score_Sco`,`student_No`,`test_id`,`student_Id`) values (1,44,96.2,'201463280852',1,17),(2,44,96.2,'201463280852',1,17),(3,44,96.2,'201463280852',1,17),(4,44,96.2,'201463280852',1,17),(5,44,96.2,'201463280852',1,17),(6,44,96.2,'201463280852',1,17),(7,44,96.2,'201463280852',1,17),(8,44,96.2,'201463280852',1,17),(9,44,96.2,'201463280852',1,17),(10,44,96.2,'201463280852',1,17),(11,44,96.2,'201463280852',1,17),(12,44,96.2,'201463280852',1,17),(13,44,96.2,'201463280852',1,17),(14,44,96.2,'201463280852',1,17),(15,44,63,'201463280852',2,17),(16,43,92,NULL,2,19),(17,43,92,NULL,2,19),(18,44,96,'201463280852',2,17),(19,44,119,'201463280852',2,17),(20,44,120,'201463280852',2,17);

/*Table structure for table `student_form` */

CREATE TABLE `student_form` (
  `student_Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_NO` varchar(20) DEFAULT NULL,
  `student_Name` varchar(10) DEFAULT NULL,
  `student_Sex` char(2) DEFAULT NULL,
  `student_Bir` datetime DEFAULT NULL,
  `student_Cla` int(20) DEFAULT NULL,
  `student_Tel` varchar(13) DEFAULT NULL,
  `student_Esd` datetime DEFAULT NULL,
  `student_Add` varchar(60) DEFAULT NULL,
  `student_Rem` varchar(100) DEFAULT NULL,
  `student_Accout` varchar(20) DEFAULT NULL,
  `student_Urlimage` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`student_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `student_form` */

insert  into `student_form`(`student_Id`,`student_NO`,`student_Name`,`student_Sex`,`student_Bir`,`student_Cla`,`student_Tel`,`student_Esd`,`student_Add`,`student_Rem`,`student_Accout`,`student_Urlimage`) values (17,'201463280852','中兴','1','1991-05-07 00:00:00',44,'',NULL,'',NULL,'zte',NULL),(19,NULL,'努比亚呀','1','1997-05-07 00:00:00',43,'15675426524',NULL,'湖南邵阳',NULL,'nubia','upload/nubia.jpg');

/*Table structure for table `teacher_form` */

CREATE TABLE `teacher_form` (
  `Teacher_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Teacher_Name` varchar(20) DEFAULT NULL,
  `Teacher_Account` varchar(20) DEFAULT NULL,
  `Teacher_tel` varchar(20) DEFAULT NULL,
  `Teacher_Urlimage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Teacher_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `teacher_form` */

insert  into `teacher_form`(`Teacher_Id`,`Teacher_Name`,`Teacher_Account`,`Teacher_tel`,`Teacher_Urlimage`) values (2,'曾家岩','teacher','15675654532','upload/teacher.jpg'),(3,'扎那地方',NULL,'16854954455',NULL);

/*Table structure for table `test_from` */

CREATE TABLE `test_from` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试ID',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `test_time` datetime DEFAULT NULL COMMENT '考试时间',
  `test_qihao` varchar(20) DEFAULT NULL COMMENT '考试期号',
  `test_name` varchar(20) DEFAULT NULL COMMENT '考试名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `test_from` */

insert  into `test_from`(`id`,`course_id`,`test_time`,`test_qihao`,`test_name`) values (1,6,'2017-05-08 00:00:00','2017-05-08','模拟考试'),(2,6,'2017-05-08 00:00:00','20170508期','模拟考试');

/*Table structure for table `user_form` */

CREATE TABLE `user_form` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserAcct` varchar(10) NOT NULL,
  `Passwd` varchar(100) DEFAULT NULL,
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
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `user_form` */

insert  into `user_form`(`UserId`,`UserAcct`,`Passwd`,`UserName`,`Depart`,`ConPhone`,`GrpSeq`,`GrpName`,`RoleSeq`,`RoleName`,`UserStatus`,`CrtTime`,`Remark`,`LoginStatus`) values (15,'teacher','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(21,'zte','388ec3e3fa4983032b4f3e7d8fcb65ad',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL),(23,'nubia','21218cca77804d2ba1922c33e0151105',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_group` */

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

CREATE TABLE `user_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`roleId`,`roleName`) values (1,'老师'),(2,'学生'),(3,'管理员');

/*Table structure for table `user_rome` */

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
