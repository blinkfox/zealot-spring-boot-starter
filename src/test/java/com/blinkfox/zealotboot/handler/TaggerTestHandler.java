package com.blinkfox.zealotboot.handler;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.config.annotation.Tagger;
import com.blinkfox.zealot.core.IConditHandler;

/**
 * 测试Tagger注解的Handler.
 *
 * @author blinkfox on 2018/4/28.
 */
@Tagger(value = "helloTagger", prefix = "Hello", symbol = "Tagger")
public class TaggerTestHandler implements IConditHandler {

    /**
     * 由于只是用来测试注解，所以这里只做简单的拼接.
     * @param source 构建所需的资源对象
     * @return sqlInfo
     */
    @Override
    public SqlInfo buildSqlInfo(BuildSource source) {
        source.getSqlInfo().getJoin().append(source.getPrefix())
                .append(" ").append(source.getSuffix());
        return source.getSqlInfo();
    }

}