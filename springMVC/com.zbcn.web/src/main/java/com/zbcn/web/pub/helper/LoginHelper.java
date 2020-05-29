package com.zbcn.web.pub.helper;

import com.zbcn.common.utils.ASEUtil;
import com.zbcn.common.utils.CookieUtils;
import com.zbcn.web.entity.dto.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 *  @title LoginHelper
 *  @Description 登录辅助类
 *  @author zbcn8
 *  @Date 2020/5/29 14:50
 */
public class LoginHelper {

    private final static String SECRET_KEY = "wo-shi-test-mi-yao";
    private final static String USER_TOKEN_NAME  = "X-Token";
    private final static String LOGIN_USER_KEY  = "LOGINED-USER";


    /**
     * 获取登录账号信息到COOKIE中
     * @param request
     * @return
     */
    public static String getLoginAccountFromCookie(HttpServletRequest request){
        String encodeLoginToken = CookieUtils.getCookieValue(request, USER_TOKEN_NAME, true);
        if(StringUtils.isEmpty(encodeLoginToken)){
            return null;
        }
        return decodeLoginToken(encodeLoginToken);
    }

    /**
     * 获取登录账号信息从请求对象中
     * @param request
     * @return
     */
    public static String getLoginAccount(HttpServletRequest request){
        String loginAccount = request.getHeader(USER_TOKEN_NAME);
        if(StringUtils.isNotBlank(loginAccount)){
            return decodeLoginToken(loginAccount);
        }
        return getLoginAccountFromCookie(request);

    }

    public static String decodeLoginToken(String loginToken){
        if(StringUtils.isBlank(loginToken)){
            return null;
        }
        return ASEUtil.decrypt(loginToken, SECRET_KEY);
    }

    public static String encodeLoginToken(String loginToken){
        if(StringUtils.isBlank(loginToken)){
            return null;
        }
        return ASEUtil.encrypt(loginToken, SECRET_KEY);
    }


    /**
     * 将用户信息放入请求对象
     * @param request
     * @param user
     */
    public static void setLoginUser2Request(HttpServletRequest request,User user){
        request.setAttribute(LOGIN_USER_KEY,user);
    }

    /**
     *  @title LoginHelper
     *  @Description 获取登录用户信息从请求对象 备注：使用该方法时需要在对应controller类或方法上加{@link com.zbcn.web.pub.annotations.LoginAuth}注解
     *  @author zbcn8
     *  @Date 2020/5/29 15:26
     */
    public static User getLoginUserFromRequest(HttpServletRequest request){
        Object userO  = request.getAttribute(LOGIN_USER_KEY);
        if(Objects.isNull(userO)){
            return null;
        }
        return (User) userO;
    }

    public static void main(String[] args) {
        System.out.println(encodeLoginToken("123456"));

    }
}
