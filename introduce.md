## 技术核心介绍

一：单例模式

二：工厂模式

三：适配器模式
````text
适配器模式把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
使得两个不相关的接口，可以通过一个适配类完成调用
将一个类的转接口转换成客户希望的另外一个接口，适配器模式使得原来由于接口不兼容的而不能的工作的哪些类可以工作,主要作用就是兼容

````
四：代理模式
```text
代理(Proxy):
    是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.
好处:
    可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
思想:
    不要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法

代理模式是一种设计模式，简单说即是在不改变源码的情况下，实现对目标对象的功能扩展。
```

五：基于JWT的token认证机制
```text
    JSON Web Token（JWT）是一个非常轻巧的规范。这个规范允许我们使用JWT在用户和服务器之间传递安全可靠的信息。
    一个JWT实际上就是一个字符串，它由三部分组成，头部、载荷与签名。
认证机制，主要流程
1、配置拦截器，拦截所有的请求（除了登录，和swagger测试）。拦截器中主要是为了获取请求URL中的token。并解析出token中的用户信息。
2、用户登录时，将用户信息（一般为id、LoginName、Roles）生成token，返给前端。放在浏览器中。
3、鉴权：在请求某一个接口是，会分析request中是否含有角色信息，是否有权限操作。实现鉴权的功能
```
六：Spring Security OAuth2.0认证授权
```text
认证:
    是为了保护系统的隐私数据与资源，用户的身份合法方可访问该系统的资源。
授权：
    授权是用户认证通过根据用户的权限来控制用户访问资源的过程，拥有资源的访问权限则正常访问，
    没有权限则拒绝访问。    
会话：
    用户认证通过后，为了避免用户的每次操作都进行认证可将用户的信息保证在会话中。
    会话就是系统为了保持当前用户的登录状态所提供的机制，常见的有基于session方式、基于token方式等。
session：
     认证方式由Servlet规范定制，服务端要存储session信息需要占用内存资源，客户端需要支持cookie；（服务端需要存储session，浪费资源）
token：
    的方式则一般不需要服务端存储token，并且不限制客户端的存储方式。
    
授权可简单理解为Who对What(which)进行How操作：
    who--主体（Subject);
    what--资源(Resource)
    how--权限/许可（Permission）
RBAC基于角色的访问控制（Role-Based Access Control）是按角色进行授权，
   比如：主体的角色为总经理可以查询企业运营报表，查询员工工资信息等
   缺点：当需要修改角色的权限时就需要修改授权的相关代码，系统可扩展性差。
RBAC基于资源的访问控制（Resource-Based Access Control）是按资源（或权限）进行授权，
   比如：用户必须具有查询工资权限才可以查询员工工资信息等
   优点：系统设计时定义好查询工资的权限标识，即使查询工资所需要的角色变化为总经理和部门经理也不需要修改
        授权代码，系统可扩展性强。
```
七：zookeeper、kafka在windows下启动方式
```text
Zookeeper：安装目录下：cmd下：zkServer
Kafka：安装目录下：cmd下：.\bin\windows\kafka-server-start.bat .\config\server.properties
```
八：异步Async
CompletableFuture

九:rabbitmq在windows下的问题
问题：Error: unable to perform an operation on node 'rabbit@DESKTOP-8VB0JLG'. Please see diagnostics information and suggestions below.
方案：将C:\Users\{用户名}\.erlang.cookie 复制到 C:\Windows\System32\config\systemprofile 目录。
      重启rabbitMQ服务




