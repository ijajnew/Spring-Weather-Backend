CREATE DATABASE  IF NOT EXISTS `web_user_tracker`;
USE `web_user_tracker`;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `notifiaction_type` varchar(45) DEFAULT NULL,
  'user_name' varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES 
	(1,'John','Doe','johnDoe@gmail.com', 'Pune', 'email', 'johnDoe');
	
UNLOCK TABLES;