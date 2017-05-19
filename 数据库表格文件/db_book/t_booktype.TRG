TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `db_book`.`neworder` \n    AFTER \n    update\n    ON `db_book`.`t_booktype`\n    FOR EACH ROW BEGIN\n    update t_book set bookTypeId=new.id where bookTypeId =old.id;\n   -- update t_recommand set `bookTypeID`=new.id where `bookTypeID`=old.id\n    END'
sql_modes=1344274432
definers='root@localhost'
