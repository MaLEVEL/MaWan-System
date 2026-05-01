package com.example.donormanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web页面控制器
 * 处理前端页面路由
 */
@Controller
public class WebController {

    /**
     * 首页 - 重定向到登录页
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 仪表板页面
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 捐献者管理页面
     */
    @GetMapping("/donors")
    public String donors() {
        return "donors";
    }

    /**
     * 体检记录页面
     */
    @GetMapping("/medical-checks")
    public String medicalChecks() {
        return "medical-checks";
    }

    /**
     * 捐献记录页面
     */
    @GetMapping("/donations")
    public String donations() {
        return "donations";
    }

    /**
     * 库存管理页面
     */
    @GetMapping("/inventory")
    public String inventory() {
        return "inventory";
    }

    /**
     * 预约管理页面
     */
    @GetMapping("/appointments")
    public String appointments() {
        return "appointments";
    }

    /**
     * 统计报表页面
     */
    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

    /**
     * 用户管理页面
     */
    @GetMapping("/users")
    public String users() {
        return "users";
    }
}

