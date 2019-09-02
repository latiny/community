package cn.latiny.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Latiny
 * @version 1.0
 * @description: IndexController
 * @date 2019/8/30 14:53
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }
}
