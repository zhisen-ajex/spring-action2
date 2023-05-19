# spring-action2
## 1 使用JSON的好处
> 1 与其他协议（如基于XML的SOAP（Simple Object Access Protocol，简单对象访问协议））相比，JSON是非常轻量级的

> 2 JSON易于人类阅读和使用

> 3 JSON是JavaScript中使用的默认序列化协议。由于JavaScript作为编程语言的急剧增长以及严重依赖JavaScript的单页互联网应用程序（Single Page Internet Application，SPIA）的同样快速增长，JSON已经天然适用于构建基于REST的应用程序，因为前端Web客户端用它来调用服务。

> 4 但是，其他机制和协议能够比JSON更有效地在服务之间进行通信。Apache Thrift框架允许你构建使用二进制协议相互通信的多语言服务。Apache Avro协议是一种数据序列化协议，可在客户端和服务器调用之间将数据来回转换为二进制格式。如果你需要最小化通过线路发送的数据的大小，我们建议你查看这些协议。但是根据我们的经验，在你的微服务中使用直接的JSON就可以有效地工作，并且不用在你的服务消费者和服务客户端之间再插入一层通信用来调试

## 2 bootstrap.yml文件
```text
bootstrap文件是特定的Spring Cloud文件类型，它在application.properties或application. yml文件之前加载。
bootstrap文件用于指定Spring应用程序名称、Spring Cloud配置的Git位置、加密/解密信息等。
具体来说，bootstrap文件是由父Spring ApplicationContext加载的，
并且该父ApplicationContext在加载使用应用程序属性或YAML文件的ApplicationContext之前加载。
```

## 3 配置优先级，以端口为例测试
**SpringBoot配置文件存在一个特性，优先级较高的配置加载顺序比较靠后，相同名称的配置优先级较高的会覆盖掉优先级较低的内容**
```text
1 命令行等级最高,可以覆盖配置,比如命令行加上-Dserverport=8000.最终不管配置文件填什么都以命令行为准
2 本地配置文件,bootstrap优先于application加载,所以bootstrap配置会被application覆盖
3 相同名称的配置,properties优先级会高于yml，所以properties配置会将yml内的配置覆盖掉。
4 本地配置文件+远程配置,远程配置覆盖本地已有的配置
```


**如下验证**
>项目本身有bootstrap.yml和application.yml
***最终启动结果是application.yml文件配置的8888***

***application.yml***
```yaml
server:
  port: 8888
```
***bootstrap.yml***
```yaml
server:
  port: 8887
```


>项目本身有bootstrap.yml和application.yml,且使用配置中心(application.yml 和 application-dev.yml)
***最终启动结果是配置中心的application-dev.yml文件配置的8885***

***本地application.yml***
```yaml
server:
  port: 8888
```
***本地bootstrap.yml***
```yaml
server:
  port: 8887
spring:
  profiles:
    #    指定服务应该运行的默认profile。profile映射到某个环境
    active: dev
```
***配置中心application.yml***
```yaml
server:
  port: 8886
```
***配置中心application-dev.yml***
```yaml
server:
  port: 8885
```