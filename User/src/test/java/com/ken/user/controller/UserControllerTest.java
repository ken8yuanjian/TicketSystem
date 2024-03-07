package com.ken.user.controller;

import com.mysql.cj.protocol.x.XMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserControllerTest {

    @ParameterizedTest
    @ValueSource(strings = {"xx","yy","zzz"})
    void login(String name) {
        //assertEquals(2,name.length(),"ok");
    }

    @Test
    void insert() {
    }

    @Test
    void thread() {
    }

    @Test
    void session() {
    }
}