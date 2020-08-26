# CourseManagementApi
用Springboot写的课程信息的Api



## 数据库设计



### tbl_user

| 字段名称      | 键   | 数据类型    | 是否允许为空 | 注释 |
| ------------- | ---- | ----------- | ------------ | ---- |
| id            | 主键 | int         | false        |      |
| account       |      | varchar(64) | false        |      |
| password      |      | varchar(64) | false        |      |
| password_salt |      | varchar(64) | false        |      |



### tbl_course

| 字段名称 | 键   | 数据类型    | 是否允许为空 | 注释 |
| -------- | ---- | ----------- | ------------ | ---- |
| id       | 主键 | int         | false        |      |
| user_id  | 外键 | int         | false        |      |
| name     |      | varchar(64) | false        |      |
| type     |      | varchar(64) | false        |      |
| teacher  |      | varchar(64) | false        |      |
| location |      | varchar(64) | false        |      |



### tbl_course_time

| 字段名称   | 键   | 数据类型 | 是否允许为空 | 注释 |
| ---------- | ---- | -------- | ------------ | ---- |
| id         | 主键 | Int      | false        |      |
| course_id  | 外键 | int      | false        |      |
| weekday    |      | tinyint  | false        |      |
| begin_week |      | tinyint  | fasle        |      |
| end_week   |      | tinyint  | fasle        |      |
| begin_time |      | tinyint  | false        |      |
| end_time   |      | tinyint  | false        |      |



## 接口设计



### 用户模块

#### 用户注册



##### 总述

| 项目    | 内容                              |
| ------- | --------------------------------- |
| 接口URL | http://47.113.97.26/user/register |
| 描述    | 用户注册                          |
| 方法    | POST                              |



##### Request

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



##### respond

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

#### 用户登录



##### 总述

| 项目    | 内容                           |
| ------- | ------------------------------ |
| 接口URL | http://47.113.97.26/user/login |
| 描述    | 用户登录                       |
| 方法    | POST                           |



##### Request

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



##### respond

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



### 课程模块

#### 添加课程



##### 总述

| 项目    | 内容                       |
| ------- | -------------------------- |
| 接口URL | http://47.113.97.26/course |
| 描述    | 添加课程                   |
| 方法    | POST                       |



##### Request

| 参数名    | 类型   | 是否必须 | 描述 |
| --------- | ------ | -------- | ---- |
| userId    | int    | true     |      |
| name      | string | true     |      |
| type      | string | true     |      |
| teacher   | string | true     |      |
| location  | string | true     |      |
| beginWeek | int    | true     |      |
| endWeek   | int    | true     |      |
| beginTime | int    | true     |      |
| endTime   | int    | true     |      |

```json
{
    "course": {
        "userId": 2,
        "name": "高等数学",
        "type": "公共必修",
        "teacher": "jxy",
        "location": "3-1-304"
    },
    "courseTimes": [
        {
           "beginWeek": 1,
           "endWeek": 17,
           "weekday": 3,
           "beginTime": 6,
           "endTime":8
        },
        {
            "beginWeek": 1,
            "endWeek": 17,
            "weekday": 5,
            "beginTime": 1,
            "endTime": 3
        }
    ]
}
```



##### respond

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

#### 查询课程



##### 总述

| 项目    | 内容                              |
| ------- | --------------------------------- |
| 接口URL | http://47.113.97.26/course/userId |
| 描述    | 查询课程                          |
| 方法    | GET                               |



##### Request

| 参数名 | 类型 | 是否必须 | 描述 |
| ------ | ---- | -------- | ---- |
| userId | int  | true     |      |

```json
{
    "userId": 13
}
```



##### respond

| 参数名  | 类型               | 是否必须 | 描述 |
| ------- | ------------------ | -------- | ---- |
| code    | string             | true     |      |
| message | string             | true     |      |
| data    | Map<String,Object> | false    |      |

```json
{
    "code": "200",
    "message": "成功",
    "data": {
        "courses": [
            {
                "course":{
                    "id": 4,
                    "userId": 2,
                    "name": "高等数学",
                    "type": "公共必修",
                    "teacher": "jxy",
                    "location": "3-1-304"
                },
                "courseTimes":[
                    {
                        "id": 1,
                        "courseId": 13,
                        "beginWeek": 1,
                        "endWeek": 17,
                        "weekday": 3,
                        "beginTime": 6,
                        "endTime":8
                    },
                    {
                        "id": 2,
                        "courseId": 13,
                        "beginWeek": 1,
                        "endWeek": 17,
                        "weekday": 5,
                        "beginTime": 1,
                        "endTime":3
                    }
                ]
            }
        ]
    }
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
* shiro框架就会自动弹出说Realm没有注入，要在主配置注解添加 **exclude**  排除注入

## 知识点记录

* 获取请求域的方式
  * **@PathVariable** —— **/izumi/3**
  * **HttpServletRequest request** ——  **?name=izumi&age=3**
  * **@RequstParma("name")  String name** —— **?name=izumi**
  * **@RequestBody Student student** —— **json**
  * **@RequestBody Map<String,Object> map** —— **json**
  
* mybatisPlus获取insert数据的id

  * ```java
    Integer id = employee.getId();
    ```





## 成功跑通的springcloud  pom文件

* parent
* modules
* 打包插件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>CourseManagementAPI</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>cloud-user8001</module>
        <module>cloud-pojo</module>
        <module>cloud-course8002</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.2.RELEASE</version>
    </parent>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>


    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR3-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR3</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud 阿里巴巴-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--mysql-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <!-- druid-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>1.1.14</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.3.2</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-generator</artifactId>
                <version>3.3.2</version>
            </dependency>
            <dependency>
                <groupId>org.apache.velocity</groupId>
                <artifactId>velocity</artifactId>
                <version>1.7</version>
            </dependency>
            <!--HuTool-->
            <dependency>
                <groupId>cn.hutool</groupId>
                <artifactId>hutool-all</artifactId>
                <version>5.4.0</version>
            </dependency>
            <!--shiro-->
            <dependency>
                <groupId>org.apache.shiro</groupId>
                <artifactId>shiro-spring-boot-starter</artifactId>
                <version>1.5.3</version>
            </dependency>
            <!--junit-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <!--log4j-->
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <classifier>exec</classifier>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

