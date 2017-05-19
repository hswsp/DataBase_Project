DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `db_book`.`neworder` 
    AFTER 
    UPDATE
    ON `db_book`.`t_booktype`
    FOR EACH ROW BEGIN
    UPDATE t_book SET bookTypeId=new.id WHERE bookTypeId =old.id;
    UPDATE t_recommand SET `bookTypeID`=new.id WHERE `bookTypeID`=old.id;
    END$$
DELIMITER ;

