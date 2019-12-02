package com.xiaohei.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : WiuLuS
 * @version : v1.12.02.2019
 * @discription :
 * @date : 2019-12-02 16:46:55
 * @email : m13886933623@163.com
 */
//@RestController
//public class GlobalErrorController implements ErrorController {
//    private final static String ERROR_PATH = "/error";
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
//
//    @RequestMapping(value = ERROR_PATH, produces = "text/html")
//    public String errorHtml(HttpServletRequest request) {
//        return "404";
//    }
//    @RequestMapping(value = ERROR_PATH)
//    public Object error(HttpServletRequest request) {
//        return "404";
//    }
//}
