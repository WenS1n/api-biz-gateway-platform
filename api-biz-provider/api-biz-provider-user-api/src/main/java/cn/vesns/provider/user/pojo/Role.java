package cn.vesns.provider.user.pojo;

import cn.vesns.common.base.po.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role")
public class Role extends BasePO {

    @TableId(type = IdType.ID_WORKER_STR)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限key
     */
    private String roleKey;

    /**
     * 角色状态 1停用 0正常
     */
    private Integer roleStatus;

    /**
     * 数据范围 1全部 2自定义
     */
    private Integer dataScope;


}
