package com.main;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class OssUploadUtil {
    /** 权限账户 **/
    public static final  String ACCESS_KEY_ID  = "";
    /** 权限秘钥 **/
    public static final  String ACCESS_KEY_SECRET = "";
    /** OSS区域 **/
    public static String ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com";
    /** bucketName **/
    public static String BUCKETNAME = "xsina-test";
    /** 图片地址 **/
    public static String IMAGE_PATH_UTL = "image/user/";
    /** 图片允许访问时间 **/
    public static Integer OUT_OF_DATE = 3600 * 1000;

    /** 创建Client客户端 **/
    private final static OSS client  = new  OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    /** 上传对象的数据 **/
    private final static ObjectMetadata meta = new ObjectMetadata();


    public static void main(String[] args) throws Exception {
        OssUploadUtil o = new OssUploadUtil();
//        String key = "B7F4A286BEAA17FC3DFF989C15F2F00E";

//        File src = new File("C:\\Users\\Administrator\\Pictures\\Screenshots\\Java运行时数据区.png");
//        InputStream in = new FileInputStream(src);
//        String key = UUID.randomUUID().toString() + "." + src.getName().substring(src.getName().lastIndexOf(".") + 1);
//        System.err.println(o.uploadFile(in,key));


//        System.err.println(o.getFileURl(key));
//        o.deleteFile(key);

        OSS ossClient = new  OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        //上传文件
        File src = new File("C:\\Users\\Administrator\\Pictures\\Screenshots\\Java运行时数据区.png");
        InputStream in = new FileInputStream(src);
        String key = UUID.randomUUID().toString();
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpeg");
        String fileKey = IMAGE_PATH_UTL + key + ".png";

        ossClient.putObject(BUCKETNAME,fileKey,in,meta);

        System.err.println("文件的Key ：" + fileKey);

        //获取访问路径
        System.err.println("一个小时的访问权限路径 ：" +o.getFileURl(fileKey));

        //删除文件
//        o.deleteFile("image/user/0010baf0-c056-4b16-9c97-1a651058a24e.png");
        ossClient.shutdown();
    }

    /**
     * @Description: 上传文件到OSS文件服务器
     * @param content  文件流
     * @param key    唯一标示(ETag)
     * @throws Exception
     * @ReturnType:String   OSSObject的ETag值。
     */
    public static String uploadFile(InputStream content, String key) throws Exception{
        // 设置文件类型
        meta.setContentType(getContentType(key));
        // 上传Object.
        log.info("上传图片到oss服务器开始 ：" + key);
        PutObjectResult result = client.putObject(BUCKETNAME, IMAGE_PATH_UTL + key, content,meta);
        log.info("上传图片到oss服务器结束 ：" + key);
        return result.getETag();
    }

    /**
     * @Description: 根据key获取oss服务器上文件的访问权限
     * @param key 唯一标识符(ETag)
     * @return 文件地址
     */
    public static String getFileURl(String key){
        /** 设置超时时间 **/
        Date expires = new Date (new Date().getTime() + OUT_OF_DATE);
        GeneratePresignedUrlRequest generatePresignedUrlRequest ;
        generatePresignedUrlRequest =new GeneratePresignedUrlRequest(BUCKETNAME, key);
        generatePresignedUrlRequest.setExpiration(expires);
        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }


    /**
     * @Description:删除文件
     * @param key  唯一标识(ETag)
     * @ReturnType:void
     */
    public static void deleteFile(String key){
        client.deleteObject(BUCKETNAME, key);
    }

    /**
     * @Description: 断点上传文件到OSS文件服务器
     * @param content  文件流
     * @param key    唯一标示(ETag)
     * @param position 当前上传的位置
     */
    public static String  appendObjectFile(InputStream content,String key,int position) throws Exception{
        // 必须设置ContentLength
        meta.setContentLength(position);
        meta.setContentType(getContentType(key));
        meta.setCacheControl("no-cache");
        meta.setContentEncoding("utf-8");
        // 上传
        log.info("断点上传图片到oss服务器开始 ：" + key);
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(BUCKETNAME, key, content, meta);
        appendObjectRequest.setPosition(Long.valueOf(position));
        AppendObjectResult appendObjectResult =client.appendObject(appendObjectRequest);
        log.info("断点上传图片到oss服务器结束 ：" + key);
        return appendObjectResult.getNextPosition().toString();
    }

    /**
     * 判断OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名称
     * @return 文件类型
     */
    private static String getContentType(String fileName) {
        if(fileName == null || fileName.length() == 0){return "image/jpeg";}
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (suffix.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (suffix.equalsIgnoreCase("jpeg") ||
                suffix.equalsIgnoreCase("jpg") ||
                suffix.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (suffix.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (suffix.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (suffix.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (suffix.equalsIgnoreCase("pptx") ||
                suffix.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (suffix.equalsIgnoreCase("docx") ||
                suffix.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (suffix.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
    

}
