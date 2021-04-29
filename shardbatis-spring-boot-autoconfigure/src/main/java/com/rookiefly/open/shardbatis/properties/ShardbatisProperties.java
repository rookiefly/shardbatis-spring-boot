package com.rookiefly.open.shardbatis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * 属性配置类，用于加载配置文件对应的前缀配置项
 **/
@Data
@ConfigurationProperties(ShardbatisProperties.PAGEHELPER_PREFIX)
public class ShardbatisProperties {

    public static final String PAGEHELPER_PREFIX = "shardbatis";

    private Properties properties = new Properties();

    public String getShardingConfig() {
        return properties.getProperty("shardingConfig");
    }

    public void setShardingConfig(String shardingConfig) {
        properties.setProperty("shardingConfig", shardingConfig);
    }
}