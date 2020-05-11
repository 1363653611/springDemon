package com.zbcn.web.pub.intercepter;

import com.zbcn.common.constants.HeaderConstants;
import com.zbcn.common.enums.CallSourceEnum;
import com.zbcn.common.enums.ResultCode;
import com.zbcn.common.exceptions.BusinessException;
import com.zbcn.common.utils.StringUtil;
import org.springframework.transaction.TransactionUsageException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * head参数校验拦截器
 */
public class HeaderParamsCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
        String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
        String appVersion = request.getHeader(HeaderConstants.APP_VERSION);

        if (StringUtil.isAnyBlank(callSource, apiVersion)) {
            throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
        }
        try {
            Double.valueOf(apiVersion);
        } catch (NumberFormatException e) {
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }

        if ((CallSourceEnum.ANDROID.name().equals(callSource) || CallSourceEnum.IOS.name().equals(callSource)) && StringUtil.isEmpty(appVersion)) {
            throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
        }

        if (!CallSourceEnum.isValid(callSource)) {
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
