package com.xiaohei.config.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xiaohei.constant.GlobalConstant;
import com.xiaohei.entity.table.UserEntity;
import com.xiaohei.entity.table.UserTokenEntity;
import com.xiaohei.matcher.AcceptTicket;
import com.xiaohei.service.admin.UserTokenService;
import com.xiaohei.utils.HttpContextUtils;
import com.xiaohei.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author : WiuLuS
 * @version : v1.11.28.2019
 * @discription :
 * @date : 2019-11-28 16:30:37
 * @email : m13886933623@163.com
 */
@Configuration
public class UserAuthenticatingFilter extends AuthenticatingFilter {
    @Resource
    private UserTokenService tokenService ;
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = HttpContextUtils.getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            return null;
        }
        return new AcceptTicket(token);
    }

    /**
     * 检查用户是否允许访问
     * @param request ：
     * @param response ：
     * @param mappedValue ：
     * @return ：
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        boolean authenticated = subject.isAuthenticated();
        if (authenticated){
            Date now = new Date();
            UserEntity user = (UserEntity)subject.getPrincipal();
            UserTokenEntity token = new UserTokenEntity();
            token.setId(user.getId());
            token.setToken(HttpContextUtils.getHeader(request,"token"));
            token.setExpireTime(new Date(now.getTime() + GlobalConstant.DEFAULT_USER_TOKEN_EXPIRE_TIME  * 1000));
            token.setUpdateTime(now);
            tokenService.updateById(token);
        }
        return authenticated;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = HttpContextUtils.getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.getWriter().print(JSON.toJSONString(R.error(401,"invalid token")));
            return false;
        }
        return executeLogin(request, response);
    }
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(401, throwable.getMessage());
            httpResponse.getWriter().print(JSON.toJSONString(r));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
