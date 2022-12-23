package cn.vesns.provider.api.pojo;

import cn.vesns.common.base.po.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class ApiCount extends BasePO {

    @TableId(type = IdType.ID_WORKER_STR)
    private Long id;

    /**
     * api名称
     */
    private String apiName;

    /**
     * api版本
     */
    private String apiVersion;

    /**
     * 统计日期
     */
    private Date countDate;

    /**
     * 总次数
     */
    private Integer totalCount;

    /**
     * 成功次数
     */
    private Integer successCount;

    /**
     * 总耗时
     */
    private Integer totalSpeeds;

    /**
     * 最大耗时
     */
    private Integer maxSpeeds;
}
