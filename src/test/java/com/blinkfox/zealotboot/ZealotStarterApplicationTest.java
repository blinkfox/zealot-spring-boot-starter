package com.blinkfox.zealotboot;

import com.blinkfox.zealot.config.AbstractZealotConfig;
import com.blinkfox.zealot.config.ZealotConfigManager;
import com.blinkfox.zealot.config.entity.NormalConfig;
import com.blinkfox.zealot.config.entity.TagHandler;
import com.blinkfox.zealot.config.entity.XmlContext;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用于测试在SpringBoot中使用该Zealot starter的测试类.
 *
 * @author blinkfox on 2018-05-02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZealotStarterApplicationTest {

    /**
     * 测试zealot的配置加载情况是否正确.
     */
    @Test
    public void testZealotConfig() {
        // 测试 normalConfig.
        NormalConfig normalConfig = NormalConfig.getInstance();
        Assert.assertFalse(normalConfig.isDebug());
        Assert.assertTrue(normalConfig.isPrintBanner());
        Assert.assertTrue(normalConfig.isPrintSqlInfo());

        // 测试扫描位置的配置
        ZealotConfigManager configManager = ZealotConfigManager.getInstance();
        Assert.assertEquals("zealot, myxmldir", configManager.getXmlLocations());
        Assert.assertEquals("com.blinkfox.zealotboot.handler", configManager.getHandlerLocations());

        // 测试 xml 和 handler 的扫描的结果是否正确存储到了内存中.
        Map<String, String> xmlPathMap = XmlContext.INSTANCE.getXmlPathMap();
        Assert.assertTrue(xmlPathMap.size() == 2);
        Map<String, TagHandler> tagHandlerMap = AbstractZealotConfig.getTagHandlerMap();
        Assert.assertTrue(tagHandlerMap.size() == 46);
    }

}