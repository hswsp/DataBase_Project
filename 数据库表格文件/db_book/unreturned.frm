TYPE=VIEW
query=select `db_book`.`t_borrow`.`ID` AS `ID`,`db_book`.`t_borrow`.`userID` AS `UserID`,`db_book`.`t_book`.`bookName` AS `BookName`,`db_book`.`t_borrow`.`dueTime` AS `dueTime` from `db_book`.`t_borrow` join `db_book`.`t_book` where ((`db_book`.`t_borrow`.`bookId` = `db_book`.`t_book`.`id`) and (`db_book`.`t_borrow`.`IsReturn` = 0))
md5=5917da70189c217edb84d92d602aff08
updatable=1
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2017-05-03 02:07:09
create-version=1
source=SELECT t_borrow.`ID`,t_borrow.`userID`,t_book.`bookName`,t_borrow.`dueTime`\n FROM t_borrow,t_book WHERE t_borrow.`bookId`=t_book.`id` AND t_borrow.`IsReturn`=0
