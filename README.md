# CourseManagementApi
用Springboot写的课程信息的Api



## 数据库设计



### tbl_user

| 字段名称      | 键   | 数据类型    | 是否允许为空 | 注释 |
| ------------- | ---- | ----------- | ------------ | ---- |
| id            | 主键 | int         | false        |      |
| account       |      | decimal(13) | false        |      |
| password      |      | varchar(64) | false        |      |
| password_salt |      | varchar(64) | false        |      |



### tbl_course

| 字段名称 | 键   | 数据类型    | 是否允许为空 | 注释         |
| -------- | ---- | ----------- | ------------ | ------------ |
| id       | 主键 | int         | false        |              |
| user_id  | 外键 | int         | false        |              |
| name     |      | varchar(64) | false        |              |
| type     |      | tinyint     | false        | 使用常数映射 |
| teacher  |      | varchar(64) | false        |              |
| location |      | varchar(64) | false        |              |



### tbl_course_time

| 字段名称   | 键   | 数据类型 | 是否允许为空 | 注释     |
| ---------- | ---- | -------- | ------------ | -------- |
| id         | 主键 | Int      | false        |          |
| course_id  | 外键 | int      | false        |          |
| begin_week |      | tinyint  | fasle        |          |
| end_week   |      | tinyint  | fasle        |          |
| begin_time |      | tinyint  | false        | 常数映射 |
| end_time   |      | tinyint  | false        | 常数映射 |



## 接口设计



## bug记录

*  **<modules>** 会在创建新的模块后自动添加，不用手动添加。如果手动添加会导致重复pom报错
* **mapper** 要加 **@Mapper**注解，不然注入失败
* Springboot主配置类要加 **@MapperScan("com.mapper")**注解，不然mapper无法注入
* 想要在页面显示字符串要加 **@ResponseBody**注解