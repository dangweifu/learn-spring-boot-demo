package com.xiaohei.aspekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author : WiuLuS
 * @version : v1.01.13.2020
 * @discription :  AOP 操作定义类
 *                 主要完成功能：日志记录、统计、参数校验等等操作。
 * @date : 2020-01-13 17:49:49
 * @email : m13886933623@163.com
 */
@Component
@Aspect
public class DefaultInterceptorDemo {
    /** 定义日志操作属性 */
    private static final Logger logger = LoggerFactory.getLogger(DefaultInterceptorDemo.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /** 定义切入点表达式
     *  可参考地址 ： https://www.iteye.com/blog/zhuchengzzcc-1504014
     **/
    private final String POINT_CUT = "execution(public * com.xiaohei.service.admin.*.*(..))";


    @Pointcut(POINT_CUT)
    public void pointCut(){}


    /**
     * 前置通知 ：在方法调用之前先调用此方法。
     * */
    // @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        logger.info("@Before通知执行");
        //获取目标方法参数信息
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg->{
            try {
                // Mapped to com.xiaohei.controller.BusinessModeController#addBusinessBy(BusinessModeDto)
                // {"id":"4","businessName":"业务名称四","businessType":"4","businessNo":"1000004"}
                logger.info(OBJECT_MAPPER.writeValueAsString(arg));
            } catch (JsonProcessingException e) {
                logger.info(arg.toString());
            }
        });


        //aop代理对象
        Object aThis = joinPoint.getThis();
        //com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl@532b147c
        logger.info(aThis.toString());

        //被代理对象
        Object target = joinPoint.getTarget();
        // com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl@532b147c
        logger.info(target.toString());

        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        // public com.xiaohei.utils.R com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl.addBusinessMode(com.xiaohei.entity.param.BusinessModeDto)
        logger.info(signature.toLongString());
        // LocalBusinessModeServiceImpl.addBusinessMode(..)
        logger.info(signature.toShortString());
        // R com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl.addBusinessMode(BusinessModeDto)
        logger.info(signature.toString());
        //获取方法名 addBusinessMode
        logger.info(signature.getName());
        //获取声明类型名 com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl
        logger.info(signature.getDeclaringTypeName());
        //获取声明类型  方法所在类的class对象 class com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl
        logger.info(signature.getDeclaringType().toString());
        //和getDeclaringTypeName()一样 com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl
        logger.info(signature.getDeclaringType().getName());

        //连接点类型 method-execution
        String kind = joinPoint.getKind();
        logger.info(kind);

        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        //org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@3f99efe4
        logger.info(sourceLocation.toString());
        ///返回连接点静态部分
        // execution(public com.xiaohei.utils.R com.xiaohei.service.admin.impl.LocalBusinessModeServiceImpl.addBusinessMode(com.xiaohei.entity.param.BusinessModeDto))
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        logger.info(staticPart.toLongString());
        //attributes可以获取request信息 session信息等
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // http://localhost:8088/admin/business/add
        logger.info(request.getRequestURL().toString());
        // 0:0:0:0:0:0:0:1
        logger.info(request.getRemoteAddr());
        // POST
        logger.info(request.getMethod());
        logger.info("before通知执行结束");
    }


    /**
     * 后置通知  ======================================= 开  始 =======================================
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     *
     */
//    @AfterReturning(value = POINT_CUT,returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object result){
        logger.info("第一个后置返回通知的返回值："+result);
    }

//    @AfterReturning(value = POINT_CUT,returning = "result",argNames = "result")
    public void doAfterReturningAdvice2(String result) {
        logger.info("第二个后置返回通知的返回值：" + result);
    }
//    @AfterReturning(value = POINT_CUT,returning = "result")
    public void doAfterReturningAdvice3() {
        logger.info("第二个后置返回通知的返回值：3");
    }
    /** 后置通知  ======================================= 结  束 ======================================= */


    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，
     *  将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
//    @AfterThrowing(value = POINT_CUT)
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        logger.info(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            logger.info("发生了空指针异常!!!!!");
        }
    }


    /**
     * 后置通知 ：
     * @param joinPoint ：
     */
//    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置通知执行了!");
    }

    /**
     * 环绕通知：
     *   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     *
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
//    @Around(value = POINT_CUT)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("@Around环绕通知："+proceedingJoinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed();
            logger.info(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("@Around环绕通知执行结束");
        return obj;
    }



}
