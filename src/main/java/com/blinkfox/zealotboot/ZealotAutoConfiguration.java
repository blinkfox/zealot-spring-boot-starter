package com.blinkfox.zealotboot;

import com.blinkfox.zealot.config.ZealotConfigManager;
import com.blinkfox.zealot.config.entity.NormalConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Zealot自动配置类.
 *
 * @author blinkfox on 2018-05-02.
 */
@Configuration
@EnableConfigurationProperties(ZealotProperties.class)
public class ZealotAutoConfiguration {

    /** ZealotProperties属性配置类的实例. */
    private ZealotProperties properties;

    /**
     * 构造方法.
     * @param properties Zealot的属性配置对象
     */
    @Autowired
    public ZealotAutoConfiguration(ZealotProperties properties) {
        this.properties = properties;
        this.doConfig();
    }

    /**
     * 根据properties中的配置项做zealot的配置.
     */
    private void doConfig() {
        // 普通类型的配置
        NormalConfig.getInstance()
                .setDebug(this.properties.isDebug())
                .setPrintBanner(this.properties.isPrintBanner())
                .setPrintSqlInfo(this.properties.isPrintSql());

        // 配置xml和handler扫描的路径.
        ZealotConfigManager.getInstance()
                .initLoadXmlLocations(this.properties.getXmlLocations())
                .initLoadHandlerLocations(this.properties.getHandlerLocations());
    }

}