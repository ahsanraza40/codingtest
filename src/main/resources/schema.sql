--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `hospital_provider`;
CREATE TABLE `hospital_provider` (
  `hospital_id` int NOT NULL,
  `provider_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_hospital_provider_provider_idx` (`provider_id`),
  KEY `fk_hospital_provider_hospital_id_idx` (`hospital_id`),
  CONSTRAINT `fk_hospital_provider_hospital_id` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `fk_hospital_provider_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`)
);

DROP TABLE IF EXISTS `patient_provider`;
CREATE TABLE `patient_provider` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `hospital_provider_id` int NOT NULL,
  PRIMARY KEY (`patient_id`,`hospital_provider_id`),
  KEY `fk_patient_hospital_provider_id_idx` (`hospital_provider_id`),
  CONSTRAINT `fk_patient_hospital_provider_id` FOREIGN KEY (`hospital_provider_id`) REFERENCES `hospital_provider` (`id`),
  CONSTRAINT `fk_patient_provider_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
);
