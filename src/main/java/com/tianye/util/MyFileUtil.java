package com.tianye.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.text.DecimalFormat;

public class MyFileUtil {
    public static String getFileSize(Long fileLength){
        String fileSizeString = "" ;
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength != null) {
            if (fileLength < 1024) {
                fileSizeString = df.format((double) fileLength) + "B";
            }
            else if (fileLength < 1048576) {
                fileSizeString = df.format((double) fileLength / 1024) + "K";
            }
            else if (fileLength < 1073741824) {
                fileSizeString = df.format((double) fileLength / 1048576) + "M";
            }
            else {
                fileSizeString = df.format((double) fileLength / 1073741824) + "G";
            }
        }

        return fileSizeString;
    }
    public static String getDuration(File source) {

        Encoder encoder = new Encoder();
        Long ls = null;
        String time = null;
        MultimediaInfo m;
        try {
            m = encoder.getInfo(source);
            ls = m.getDuration()/1000;
            if(ls<60){
                time = "00:"+ls;
            }else{
                time = ls/60+":"+ls%60;
            }
        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }

        return time;
    }
}
