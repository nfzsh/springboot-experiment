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
-------------6.16--4--------------
7.在examrepository,examservice,admincontroller中添加查询所有考试信息的方法
   在select.http中实现
-------------6.17--1--------------
将exam实体中的flag改为int型，123表示未分配，已分配，已完成
1.添加userexamrepository
2.添加userexamservice，adduserexamservice
3.手动增加userexam
4.添加查询所有userexam的方法，
5.在select.http中测试
6.添加updateuserexamservice,添加修改考试安排的方法
在updateuserexam.http中测试