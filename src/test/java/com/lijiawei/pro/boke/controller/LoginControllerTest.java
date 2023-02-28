package com.lijiawei.pro.boke.controller;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

class LoginControllerTest {

    @Test
    public void test1() {
        String s = DigestUtils.md5DigestAsHex(("123" + "1").getBytes());
        System.out.println(s);
    }

    @Test
    public void test2() {
    }
}