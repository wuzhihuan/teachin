package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author ameng
 * @Date 2019/4/12
 */
@Slf4j
@Component
public class QiniuUtil2 {

    private static String BUCKET = "zhidian";

    private static String DOMAIN;

    private static String ACCESS_KEY;

    private static String SECRET_KEY;

    @Value("${qiniu.domain}")
    public void setDOMAIN(String domain){
        QiniuUtil2.DOMAIN = domain;
    }

    @Value("${qiniu.accessKey}")
    public  void setAccessKey(String accessKey) {
        QiniuUtil2.ACCESS_KEY = accessKey;
    }

    @Value("${qiniu.secretKey}")
    public  void setSecretKey(String secretKey) {
        QiniuUtil2.SECRET_KEY = secretKey;
    }

    // 生成上传授权uptoken
    public static ResponseVo getUpToken() {
        Auth auth = Auth.create(ACCESS_KEY, ACCESS_KEY);
        // 请确保该bucket已经存在
        String upToken = auth.uploadToken(BUCKET);
        return ResponseVo.success(upToken);
    }


    // 文件上传
    public static ResponseVo uploadFile(MultipartFile[] attach) {
        try {
            String[] putObject = QiniuUtil2.uploadToQiNiuYun(attach);
            if (putObject.length <= 0)
                throw new ResponseException(ResponseEnum.ERROR);
            return ResponseVo.success(putObject[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResponseException(ResponseEnum.ERROR);
    }


    /**
     * 文件上传
     *
     * @param file
     * @return 文件地址
     * @throws IOException
     */
    public static String[] uploadToQiNiuYun(MultipartFile[] file) throws IOException {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        String[] imgpath = new String[file.length];
        int i = 0;
        for (MultipartFile myfile : file) {
            if (myfile == null || myfile.isEmpty()) {
                imgpath[i] = null;
                i++;
                continue;
            }
            String filename = myfile.getOriginalFilename();
            InputStream inputStream = myfile.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] uploadBytes = swapStream.toByteArray();
            String upToken = auth.uploadToken(BUCKET);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                imgpath[i] = DOMAIN + "/" + putRet.hash;
                i++;
            } catch (QiniuException ex) {
                log.error("文件上传失败：", ex);
            }
        }
        return imgpath;
    }


    /**
     * 字节数组输出流
     *
     * @param swapStream
     * @return
     */
    public static String uploadToQiNiuYun(ByteArrayOutputStream swapStream) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration(Zone.zone0());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        byte[] uploadBytes = swapStream.toByteArray();
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return DOMAIN + "/" + putRet.hash;
        } catch (QiniuException ex) {
            log.error("文件上传失败：", ex);
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(QiniuUtil2.getUpToken().getData());
    }
}
