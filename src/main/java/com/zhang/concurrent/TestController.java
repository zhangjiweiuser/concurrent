package com.zhang.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-06 下午 15:33
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(path = "/test")
    public String test() {
        return "test";
    }

}
