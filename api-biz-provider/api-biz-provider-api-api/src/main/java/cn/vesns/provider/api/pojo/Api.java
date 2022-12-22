package cn.vesns.provider.api.pojo;

import cn.vesns.common.base.po.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_api")
public class Api extends BasePO {


    /**
     * <pre>
     * 主键ID
     * </pre>
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private Integer apiId;

    /**
     * <pre>
     * 0:草稿 1:已发布 2:已停用
     * </pre>
     */
    private Integer state;

    /**
     * <pre>
     * 创建人
     * </pre>
     */
    private String creator;

    /**
     * <pre>
     * 修改人
     * </pre>
     */
    private String updater;

    /**
     * <pre>
     * API名
     * </pre>
     */
    private String apiName;

    /**
     * <pre>
     * API描述
     * </pre>
     */
    private String apiDesc;

    /**
     * <pre>
     * API分组ID
     * </pre>
     */
    private Integer apiGroup;

    /**
     * <pre>
     * API版本号
     * </pre>
     */
    private String apiVersion;

    /**
     * <pre>
     * 远程接口名
     * </pre>
     */
    private String rpcInterface;

    /**
     * <pre>
     * 远程方法名
     * </pre>
     */
    private String rpcMethod;

    /**
     * <pre>
     * 远程接口版本号
     * </pre>
     */
    private String rpcVersion;

    /**
     * <pre>
     * API超时时间
     * </pre>
     */
    private Integer timeout;

    /**
     * <pre>
     * 格式化方式
     * </pre>
     */
    private String format;

    /**
     * <pre>
     * 签名方式
     * </pre>
     */
    private String signType;

    /**
     * <pre>
     * 限流次数, -1:不限流
     * </pre>
     */
    private Integer callLimit;

    /**
     * <pre>
     * 限流方式 1计数器 2滑动窗口 3漏桶算法 4令牌桶
     * </pre>
     */
    private Integer limitType;

    /**
     * <pre>
     * 是否需要登录 0-需要,1-不需要
     * </pre>
     */
    private Integer needLogin;

    /**
     * <pre>
     * 是否需要访问权限 0-需要 1-不需要
     * </pre>
     */
    private Integer needAuth;

    /**
     * <pre>
     * 请求类型 GET或POST
     * </pre>
     */
    private String httpMethod;

    /**
     * <pre>
     * API类型 0-普通API,1-回调API
     * </pre>
     */
    private Integer apiType;


}
