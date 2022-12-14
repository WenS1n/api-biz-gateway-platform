package cn.vesns.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author arctique
 * @date 2020/9/17 14:38
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录参数")
public class LoginParam implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
