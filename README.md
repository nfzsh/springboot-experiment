1.添加考试实体及相关属性
2.在user实体中添加与exam实体的关系
----------6.14------------
3.在service中添加addexamservice 
5.在admincontroller中添加增加exam的方法
6.在repository中增加examrepository
----------6.15--1---------
7.在service中添加examservice，编写通过考试科目名或考试id查询考试安排的方法
8.在admincontroller中添加查询exam的方法
9.在select.http中增加测试方法
-------------6.15---大改------
1.更改user与exam关系为多对多，取消原实体中的关系
2.增加userexam实体，添加多对多关系
3.将exam实体中的studentnum改为usernum，为考试监考老师数量
-------------6.15--3--------------
4.在repository中增加updateexamrepository,增加更新方法
5.在service中添加updateexamservice
6.在test中增加updateexam调试
调试存在问题：
Cannot deserialize value of type `
java.time.LocalDateTime` from String \"2019-06-20T10:00\"