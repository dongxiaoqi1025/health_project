package dxq.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class AlibabaUtils {

    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
    private static final String accessKeyId = "LTAI4G7sQodjWNvEq6d9SZrL";
    private static final String accessKeySecret = "g335kAZSDPG1Z3iDHe9X80DmVcb46r";
    private static final String bucket ="dxq-health-project1";
    public static String upLoadFileByInputStream(InputStream inputStream,String fileName)
    {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        ossClient.putObject(bucket, fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucket, fileName, expiration).toString();
        return url;
    }

    public static void deleteFile(String objectName)
    {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucket, objectName);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    public static String uploadByFile(File file)
    {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, file.getName(), file);


        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucket, file.getName(), expiration).toString();
        return url;
    }

}
