ALTER TABLE `plate` ADD `startTime` DATETIME NULL AFTER `id` ,
ADD `lastChangeTime` DATETIME NULL AFTER `startTime` ;
ALTER TABLE `plate` ADD `startMan` VARCHAR( 50 ) NULL COMMENT '������' AFTER `lastChangeTime` ,
ADD `lastChangeMan` VARCHAR( 50 ) NULL COMMENT '����޸���' AFTER `startMan` ,
ADD `viewTimes` INT( 10 ) NULL COMMENT '�鿴����' AFTER `lastChangeMan` ;
