package com.xiaohei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : WiuLuS
 * @version : v1.11.19.2019
 * @discription :
 * @date : 2019-11-19 11:26:29
 * @email : m13886933623@163.com
 */
@SpringBootApplication(scanBasePackages = "com.xiaohei")
@MapperScan(basePackages = {"com.xiaohei.dao"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
