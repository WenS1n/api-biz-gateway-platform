package cn.vesns.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class MetaHandler  implements MetaObjectHandler {

    /**
     * 新增数据执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            this.setFieldValByName("createTime", new DateTime(), metaObject);
            this.setFieldValByName("updateTime", new DateTime(), metaObject);
        }
    }

    /**
     * 更新数据执行
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            this.setFieldValByName("updateTime", new DateTime(), metaObject);
        }
    }



}
