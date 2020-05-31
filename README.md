Courses
--------------------------------------------
### 2020.05.18
添加基于github actions的持续部署    
修改上传文件尺寸为无限大，有前端负责限制  
添加上传实验文件尺寸属性声明。默认10M  
Bug  
原cookie中仅加密封装了用户账号，当用户修改密码后依然可登录。修改为加密封装用户账号及密码

### 2019.04.18
#### New Feature   
实现作业下一个查询   
添加全局地址栏参数类型转换异常处理。都是通过鼠标点击，参数怎么会错呢    

### 2019.02.04
#### New Feature  
分离开发/生产环境配置，生产环境启动时通过命令声明使用生产环境配置启动     
统一后端与数据库时区为Asia/Shanghai   
引入docker-compose编排管理3个docker容器    
前端入口启用HTTP2与HTTPS   
前端部署在nginx服务器，通过反向代理实现跨域请求转发       
创建3个容器部署应用            
Docker-CE: 17.09
- nginx: 1.15.8    
- opendjk: 11.0.1  
- MySQL: 8.0.14  
#### Refactoring  
优化查询语句   
基于NIO2优化文件操作      
从容器取回容器默认创建的Jackson ObjectMapper对象操作Token序列化   
自定义ResponseStatusException(Spring-Web 5.0)
异常，支持直接声明响应状态码/消息，结合Spring-Web默认错误返回规范(300-500)，替代@RestControllerAdvice全局异常处理    
前端基于XLSX读取分析excel表格，后端取消POI实现   
前后端互交接口参考github API V3标准重新设计     
基于Java11重构，新的集合方法很好用      
引入Jackson Hibernate Module判断处理延迟加载对象    
基于spring-security重写用户密码的数据库存储与token   
- 基于bcrypt算法保存用户密码，随机生成salt值并附在hash结果避免单独记录，实现即使密码相同但hash值不同    
- 基于256bits AES-GCM加密算法生成token，实现即使数据相但同密文不同   
- 基于加密/解密算法，重写请求拦截校验    
#### Bug  
MySQL8连接，需声明useSSL/allowPublicKeyRetrieval/serverTimezone属性    
Lombok @Data会重写toString()方法，致使操作双向关联对象时死循环，改为@Getter/@Setter注解      

[前端源码](https://github.com/bwhyman/courses-vue)    

2018.11.17
------------------
前后端分离   
后端基于IDEA重写      
后端基于springboot(2.1.0)重写     
基于spring-data-jpa(2.1.0)重写dao，为什么不提供JPA标准方法refresh()？   
基于lombok(1.18.4)简化代码   
将工具类转为组件设计  

----------------------
2018.07.04
-------------------------------
基于NIO重写文件操作    
添加作业功能，在线提交java代码，代码语法点亮     
又添加重写了很多东西，都不记得了。。。。    
用Vue重写前端，写了1个月也没写完，都不记得怎么设计的互交接口，实现了什么，欲实现什么。。。  最后后端回滚     

2017.12.11
------------------------------  
bug    
修复查询未提交学生HQL语句错误   
修复由于在内存中生成压缩文件流，并转为字符数组下载，当生成的压缩文件过大时内存溢出OOM；   
解决方法，不使用SpringMVC封装的方法，通过原生response输出流+NIO基于缓冲区实现         
   
   
     
Production Environment
-------------------------------
OS: CentOS 7.4    
RAM: 1024 MB; Storage: 25GB SSD    
Docker-CE: 17.09; Tomcat: 8.5.23; MySQL: 5.7     
              
Development Environment
-----------------------------------
Server: Tomcat(9.0.0.26M)  
Database: MySQL(5.7)  
Persistence Layer: JPA; Hibernate(5.2.10)  
Business Layer: Spring(5.0.RC3)  
UI: JQuery(2.1.4); Bootstrap(3.3.5); Flat-ui;
POI; log4j; hibernate-validator(6.0.0.RC3)   
Git; Github; Maven;   
