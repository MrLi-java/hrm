package top.lmqstudy.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lmqstudy.dto.FileRemoveDto;
import top.lmqstudy.util.AjaxResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/01/16:20
 * @Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public AjaxResult fileUpload(MultipartFile file){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G6Njg7Fksvb5hZuVf87";
        String accessKeySecret = "3ZUO6tUKytvQAoucx6BAaszysODA6o";
        String bucketName = "hrm-lmq-file";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传Byte数组。
        byte[] content = new byte[0];
        try {
            content = file.getBytes();
            String suffix = StringUtils.substringAfter(file.getOriginalFilename(),".");
            String fileName = getNowDateStr()
                    + StringUtils.replace(UUID.randomUUID().toString(), "-", "") + "." + suffix;
            ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content));
            // 关闭OSSClient。
            ossClient.shutdown();
            return AjaxResult.me().setResultObj(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("文件上传失败");
        }

    }

    @GetMapping("/getFileUrlByName")
    public AjaxResult getFileUrlByName(String fileName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G6Njg7Fksvb5hZuVf87";
        String accessKeySecret = "3ZUO6tUKytvQAoucx6BAaszysODA6o";


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置URL过期时间为1小时.作用：为了防止图片的url地址被盗用（防盗链）
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl("hrm-lmq-file", fileName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return AjaxResult.me().setResultObj(url);
    }

    @PostMapping("/remove")
    public AjaxResult fileRemove(@RequestBody FileRemoveDto dto){
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G6Njg7Fksvb5hZuVf87";
        String accessKeySecret = "3ZUO6tUKytvQAoucx6BAaszysODA6o";
        String bucketName = "hrm-lmq-file";
        String objectName = dto.getPath();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
        return AjaxResult.me();
    }

    private String getNowDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        return format;
    }

}
