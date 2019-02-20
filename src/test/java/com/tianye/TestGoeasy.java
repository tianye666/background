package com.tianye;

import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoeasy {
    @Test
    public void testGo(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-66a9ac718fc24524b917d7c9270dce56");
        goEasy.publish("140","Hello, GoEasy!");
    }
}
