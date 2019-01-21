package com.feri.selfcoding_webapp.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 *@Author feri
 *@Date Created in 2019/1/17 10:20
 */
public class FileUtil {
    //重命名
    public static String renameFile(String fn){
        if(fn.length()>20){
            fn=fn.substring(fn.length()-20);
        }
        return UUID.randomUUID().toString().replace("-","")+"_"+fn;
    }
    //存储文件夹
    public static File createDir(String rootPath){
        File dir=new File(rootPath,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if(!dir.exists()){
            dir.mkdirs();
        }

        return dir;
    }
}
