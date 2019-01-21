package com.feri.selfcoding_webapp.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *@Author feri
 *@Date Created in 2019/1/17 14:25
 * 基于阿里与的OSS文件的存储
 */
public class AliyunOSSUtil {
    private static String accessKeyId = "LTAIhTvqTSmlmjeQ";
    private static String accessKeySecret = "X7X9w0Ck5GEIWgP9tl0Q6sgmFjQuMv";
    private static String bucketName = "selfcoding";
    private  static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String preurl="http://selfcoding.oss-cn-beijing.aliyuncs.com/";
    //创建存储空间
    public static void createBucket(String name){
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 创建存储空间。
        ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    //上传对象
    public static String fileUploadObj(String objName, InputStream fileis){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objName, fileis);
        // 关闭OSSClient。
        ossClient.shutdown();
        return preurl+objName;
    }
    //上传文件
    public static String fileUploadFile(String fileName, InputStream fileis){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        //上传
        ossClient.putObject(bucketName, fileName, fileis);
        //获取上传之后生成的url  带上有效期
        String url=ossClient.generatePresignedUrl(bucketName,fileName,calendar.getTime()).toString();

        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }
    //文件下载
    public static String fileDown(String objName,File desFile){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            try {
                IOUtils.copy(content,new FileOutputStream(desFile));
                content.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
// 关闭OSSClient。
        ossClient.shutdown();
        return desFile.getPath();
    }
    //查询文件列表
    public static List<String> list(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        List<String> list=new ArrayList<>();
// ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
// objectListing.getObjectSummaries获取所有文件的描述信息。
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
           list.add(objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }

// 关闭OSSClient。
        ossClient.shutdown();
        return list;
    }
    //删除文件
    public static void del(String objName){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 删除文件。
        ossClient.deleteObject(bucketName, objName);
// 关闭OSSClient。
        ossClient.shutdown();

    }

    /*public static void main(String[] args) throws FileNotFoundException {
        File file=new File("H:\\Class\\ZZ_1806\\资料\\页面2.png");
        System.out.println(file.exists());
       String url=fileUploadFile(file.getName(),new FileInputStream(file));
        System.out.println("访问路径:"+url);
        System.out.println(list());
    }*/

}
