package com.blinkfox.zealotboot.zealot;

import com.blinkfox.zealot.bean.ParamWrapper;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.Zealot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Zealot的测试类.
 *
 * @author blinkfox on 2018-05-02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZealotTest {

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

    /**
     * 测试从扫描的handler注解中生成的标签sql.
     */
    @Test
    public void testScanHandlerSql() {
        SqlInfo sqlInfo = Zealot.getSqlInfo("myCourse", "testTaggerHanderSql");
        String expectedSql = "Hello Tagger hello blinkfox hi blinkfox hello world";

        Assert.assertEquals(expectedSql, sqlInfo.getSql());
        Assert.assertArrayEquals(new Object[]{}, sqlInfo.getParamsArr());
    }

}