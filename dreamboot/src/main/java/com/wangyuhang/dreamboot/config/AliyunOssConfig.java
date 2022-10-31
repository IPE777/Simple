package com.wangyuhang.dreamboot.config;



import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 阿里云OSS基本配置，写入之前在阿里云注册的一些内容
 */
// 声明配置类，放入Spring容器
@Configuration
// 指定配置文件位置
@PropertySource(value = {"classpath:application.yml"})
// 指定配置文件中自定义属性前缀
@ConfigurationProperties(prefix = "aliyun")
@Data
// 开启链式调用  例如 StringBuilder builder = new StringBuilder();
//        builder.append("blake").append("bob").append("alice").append("linese").append("eve");
@Accessors(chain = true)
public class AliyunOssConfig {
    private String endPoint;// 地域节点
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;// OSS的Bucket名称
    private String urlPrefix;// Bucket 域名
    private String fileHost;// 目标文件夹


    /**
     * 将OSSClient放入spring上下文中
     * @return
     */
    @Bean
    public OSS OSSClient(){
        return new OSSClient(endPoint,accessKeyId,getAccessKeySecret());
    }


}

