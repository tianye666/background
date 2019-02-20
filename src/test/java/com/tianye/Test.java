package com.tianye;

import java.util.UUID;

public class Test {
    @org.junit.Test
    public void test(){
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
