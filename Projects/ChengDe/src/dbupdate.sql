ALTER TABLE `plate` ADD `startTime` DATETIME NULL AFTER `id` ,
ADD `lastChangeTime` DATETIME NULL AFTER `startTime` ;
ALTER TABLE `plate` ADD `startMan` VARCHAR( 50 ) NULL COMMENT '开单人' AFTER `lastChangeTime` ,
ADD `lastChangeMan` VARCHAR( 50 ) NULL COMMENT '最后修改人' AFTER `startMan` ,
ADD `viewTimes` INT( 10 ) NULL COMMENT '查看次数' AFTER `lastChangeMan` ;
