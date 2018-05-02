# zealot-spring-boot-starter

[![Build Status](https://secure.travis-ci.org/blinkfox/zealot-spring-boot-starter.svg)](https://travis-ci.org/blinkfox/zealot-spring-boot-starter) [![Javadocs](http://www.javadoc.io/badge/com.blinkfox/zealot-spring-boot-starter.svg)](http://www.javadoc.io/doc/com.blinkfox/zealot-spring-boot-starter) [![HitCount](http://hits.dwyl.io/blinkfox/zealot-spring-boot-starter.svg)](http://hits.dwyl.io/blinkfox/zealot-spring-boot-starter) [![GitHub issues](https://img.shields.io/github/issues/blinkfox/zealot-spring-boot-starter.svg)](https://github.com/blinkfox/zealot-spring-boot-starter/issues) [![GitHub license](https://img.shields.io/github/license/blinkfox/zealot-spring-boot-starter.svg)](https://github.com/blinkfox/zealot-spring-boot-starter/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.blinkfox/zealot-spring-boot-starter.svg)](http://search.maven.org/#artifactdetails%7Ccom.blinkfox%7Czealot-spring-boot-starter%7C1.2.0%7Cjar) [![GitHub forks](https://img.shields.io/github/forks/blinkfox/zealot-spring-boot-starter.svg)](https://github.com/blinkfox/zealot-spring-boot-starter/network) [![GitHub stars](https://img.shields.io/github/stars/blinkfox/zealot-spring-boot-starter.svg)](https://github.com/blinkfox/zealot-spring-boot-starter/stargazers) [![codecov](https://codecov.io/gh/blinkfox/zealot-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/blinkfox/zealot-spring-boot-starter)

Zealot是一个简单、强大的Java动态SQL和参数生成工具库。[Zealot文档地址](https://blinkfox.github.io/zealot/)

`zealot-spring-boot-starter`则是Zealot在SpringBoot中使用的快速集成版，默认不需要任何配置即可使用。

## 获取Zealot Spring Boot Starter

```xml
<dependency>
    <groupId>com.blinkfox</groupId>
    <artifactId>zealot-spring-boot-starter</artifactId>
</dependency>
```

> **注**: 适用于SpringBoot2的项目，JDK1.8及以上。

## 核心配置

在 Spring Boot 中使用Maven集成了`zealot-spring-boot-starter`之后，默认不需要任何配置，Zealot就可以开始工作了，以下是在`application.properties`文件中Zealot的默认配置和说明，供参考配置：

```properties
# 是否开启调试模式，如果开启的话，每次调用都会实时从最新的xml文件中获取sql，默认值为false.
zealot.debug=false

# 是否打印zealot的启动banner，默认为true.
zealot.print-banner=true

# 是否打印zealot的sql日志，默认为true.
zealot.print-sql=true

# zealot的XML文件所在的位置，多个用逗号隔开,可以是目录也可以是具体的xml文件，默认是名为'zealot'的资源目录及子目录下的位置.
# 所以，如果不配置其他目录，你的xml就应该存放在这样的目录和子目录下:'main/resources/zealot/'，才能被扫描到，其他目录的则需要更改此配置项
zealot.xml-locations=zealot

# zealot的自定义handler处理器所在的位置，多个用逗号隔开,可以是目录也可以是具体的java或class文件路径，默认为空，以下是参考示例.
# 如果你有自定义标签的话，则只需在该标签对应的Handler上使用'@Tagger'和'@Taggers'注解即可配置标签了.
#zealot.handler-locations=com.blinkfox.zealotboot.handler
```

## 简单示例

Zealot的详细使用可参考[这里的文档](https://blinkfox.github.io/zealot/)，以下只是最简单的示例展示。

### Zealot的使用

首先，在`resouces`资源目录下，新建`zealot`目录，然后在`zealot`目录中新建`student.xml`的zealot xml文件，内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 查询老师相关的SQL信息，命名空间nameSpace为zealots的根节点属性，各xml文件的nameSpace不能相同. -->
<zealots nameSpace="myTeacher">

    <!-- 根据Id查询学生信息. -->
    <zealot id="queryTeacherById">
        SELECT * FROM t_teacher AS t WHERE
        <equal field="t.c_id" value="id"/>
    </zealot>

</zealots>
```

接下来是在单元测试的Java代码中调用，代码如下：

```java
/**
 * 测试从zealot/teacher.xml中调用生成动态SQL.
 */
@Test
public void testQueryueryTeacherById() {
    SqlInfo sqlInfo = Zealot.getSqlInfo("myTeacher", "queryTeacherById",
            ParamWrapper.newInstance("id", "123").toMap());
    String expectedSql = "SELECT * FROM t_teacher AS t WHERE t.c_id = ?";

    Assert.assertEquals(expectedSql, sqlInfo.getSql());
    Assert.assertArrayEquals(new Object[]{"123"}, sqlInfo.getParamsArr());
}
```

## 许可证

`zealot-spring-boot-starter`类库遵守[Apache License 2.0][6] 许可证。

## 版本更新记录

- v1.0.0(2018-05-03)
  - 新增zealot的默认快速集成和单元测试