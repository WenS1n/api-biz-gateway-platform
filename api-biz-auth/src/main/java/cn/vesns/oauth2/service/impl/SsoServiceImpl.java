package cn.vesns.oauth2.service.impl;

import cn.vesns.common.IdUtils;
import cn.vesns.oauth2.dto.LoginInfo;
import cn.vesns.oauth2.dto.LoginParam;
import cn.vesns.oauth2.dto.RegisterUserDTO;
import cn.vesns.oauth2.service.SsoService;
import cn.vesns.common.JsonUtil;
import cn.vesns.common.OkHttpUtil;
import cn.vesns.common.Result;
import cn.vesns.common.utils.constant.CommonConstants;
import cn.vesns.provider.user.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: vesns vip865047755@126.com
 * @Title: SsoServiceImpl
 * @ProjectName: cloud-xxx-api
 * @Description:
 * @date: 2022-10-24 22:02
 */
@Service
@Slf4j
public class SsoServiceImpl implements SsoService {

    private final String PORT = "9999";

    @Value("${server.servlet.context-path}")
    private String name;

    private final String grantType = "password";

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private TokenStore tokenStore;

    @Override
    public Result register(RegisterUserDTO registerUserDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, registerUserDTO.getUsername());


        return null;
    }

    /**
     * ??????
     *
     * @param loginParam
     * @return {@link Result}
     */
    @Override
    public Result login(LoginParam loginParam) {
        Map<String, String> token = Maps.newHashMap();
        // ?????????????????????
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        log.info("password------------>{}",userDetails.getPassword());
        if (bCryptPasswordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            // ??????token
            token.put(CommonConstants.AUTHORIZATION, getToken(loginParam));
        }
        return bCryptPasswordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())
                ? Result.success(HttpStatus.OK.value(), "????????????", token)
                : Result.failure(CommonConstants.REST_FAIL, "??????/????????????", null);
    }

    /**
     * ????????????????????????
     *
     * @return {@link Result}
     */
    @Override
    public Result getInfo() {
        // ??????????????????
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication==========>{}",authentication);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(authentication.getName());

        return Result.success(HttpStatus.OK.value(), "??????????????????", loginInfo);
    }


    /**
     * ??????
     *
     * @param request
     * @return {@link Result}
     */
    @Override
    public Result logout(HttpServletRequest request) {
        String token = request.getParameter(CommonConstants.AUTHORIZATION);
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

        // ??????token
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return Result.success(HttpStatus.OK.value(), "????????????");
    }

    /**
     * ??????token
     *
     * @param loginParam
     * @return {@link String}
     */
    private String getToken(LoginParam loginParam) {
        final String URL = String.format("http://localhost:%s%s%s", PORT, name, "/oauth/token");

        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", grantType);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        log.info("URL---------->{}", URL);
        String post = OkHttpUtil.post(URL, params);

        return Objects.requireNonNull(JsonUtil.toMap(post)).get(CommonConstants.AUTHORIZATION).toString();
    }

}
