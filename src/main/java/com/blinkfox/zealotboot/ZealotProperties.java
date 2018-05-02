package com.blinkfox.zealotboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用于在 application.properties 文件中的配置属性类，定义前缀为'zealot'.
 *
 * @author blinkfox on 2018-05-02.
 */
@ConfigurationProperties("zealot")
public class ZealotProperties {

    /** 是否开启调试模式，如果开启的话，每次调用都会实时从最新的xml文件中获取sql，默认值为false. */
    private boolean debug;

    /** 是否打印zealot的启动banner，默认为true. */
    private boolean printBanner;

    /** 是否打印zealot的sql日志，默认为true. */
    private boolean printSql;

    /** zealot的XML文件所在的位置，多个用逗号隔开,可以是目录也可以是具体的xml文件，默认是zealot资源目录及子目录下的位置. */
    private String xmlLocations;

    /** zealot的自定义handler处理器所在的位置，多个用逗号隔开,可以是目录也可以是具体的java或class文件路径. */
    private String handlerLocations;

    /**
     * 无参构造方法，构造对象时赋予默认值.
     */
    public ZealotProperties() {
        this.printBanner = true;
        this.printSql = true;
    }

    /* 以下是 getter 和 setter 方法. */

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isPrintBanner() {
        return printBanner;
    }

    public void setPrintBanner(boolean printBanner) {
        this.printBanner = printBanner;
    }

    public boolean isPrintSql() {
        return printSql;
    }

    public void setPrintSql(boolean printSql) {
        this.printSql = printSql;
    }

    public String getXmlLocations() {
        return xmlLocations;
    }

    public void setXmlLocations(String xmlLocations) {
        this.xmlLocations = xmlLocations;
    }

    public String getHandlerLocations() {
        return handlerLocations;
    }

    public void setHandlerLocations(String handlerLocations) {
        this.handlerLocations = handlerLocations;
    }

}