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



### 用户注册



#### 总述

| 项目    | 内容                              |
| ------- | --------------------------------- |
| 接口URL | http://47.113.97.26/user/register |
| 描述    | 用户注册                          |
| 方法    | POST                              |



#### Request

| 参数名   | 类型   | 是否必须 | 描述   |
| -------- | ------ | -------- | ------ |
| account  | string | true     | 用户名 |
| password | string | true     | 密码   |

```json
{
    "account": "2018302110326",
    "password": "123456"
}
```



#### respond

| 参数名  | 类型               | 是否必须 | 描述 |
| ------- | ------------------ | -------- | ---- |
| code    | string             | true     |      |
| message | string             | true     |      |
| data    | Map<String,Object> | false    |      |

```json
{
    "code": "200",
    "message": "成功",
    "data": null
}
```

### 用户登录



#### 总述

| 项目    | 内容                           |
| ------- | ------------------------------ |
| 接口URL | http://47.113.97.26/user/login |
| 描述    | 用户登录                       |
| 方法    | GET                            |



#### Request

| 参数名   | 类型   | 是否必须 | 描述   |
| -------- | ------ | -------- | ------ |
| account  | string | true     | 用户名 |
| password | string | true     | 密码   |

```json
{
    "account": "2018302110326",
    "password": "123456"
}
```



#### respond

| 参数名  | 类型               | 是否必须 | 描述 |
| ------- | ------------------ | -------- | ---- |
| code    | string             | true     |      |
| message | string             | true     |      |
| data    | Map<String,Object> | false    |      |

```json
{
    "code": "200",
    "message": "成功",
    "data": null
}
```



## bug记录

* **<modules>** 会在创建新的模块后自动添加，不用手动添加。如果手动添加会导致重复pom报错

* **mapper** 要加 **@Mapper **注解，不然注入失败

* Springboot主配置类要加 **@MapperScan("com.mapper") **注解，不然mapper无法注入

* 想要在页面显示字符串要加 **@ResponseBody** 注解，但如果返回 **CommonResult **就不用加

* java的包名不能是关键字如 **const**，不然无法创建java文件，必须改成 **constant**

* CommonResult的 **sucess **和 **fail** 是用类名调用 **static **静态； **add** 是对象调用 **非静态**

* **enum**类的编写要顺序 **枚举**，**属性**，**构造方法**，**getter**。不能先写枚举\

* shiro必须要加一个配置类一层一层的配，把各个类都方法容器。如果只一次性配置Realm部分，则有些部分未放入容器

* 想要获得json格式的返回结果必须加上 **@ResponseBody** 注解，不然默认返回的是页面。

## 知识点记录

* 获取请求域的方式
  * **@PathVariable** —— **/izumi/3**
  * **HttpServletRequest request** ——  **?name=izumi&age=3**
  * **@RequstParma("name")  String name** —— **?name=izumi**
  * **@RequestBody Student student** —— **json**
  * **@RequestBody Map<String,Object> map** —— **json**