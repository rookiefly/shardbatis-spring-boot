package com.rookiefly.open.shardbatis.configuration;

import com.rookiefly.open.shardbatis.plugin.ShardPlugin;
import com.rookiefly.open.shardbatis.properties.ShardbatisProperties;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 自动装配类
 **/
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ShardbatisProperties.class)
@ConditionalOnBean(SqlSessionFactory.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@Lazy(false)
public class ShardbatisAutoConfiguration {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Resource
    private ShardbatisProperties properties;

    @PostConstruct
    public void addPageInterceptor() {
        ShardPlugin interceptor = new ShardPlugin();
        interceptor.setProperties(properties.getProperties());
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 是否已经存在相同的拦截器
     *
     * @param configuration
     * @param interceptor
     * @return
     */
    private boolean containsInterceptor(org.apache.ibatis.session.Configuration configuration, Interceptor interceptor) {
        try {
            // getInterceptors since 3.2.2
            return configuration.getInterceptors().contains(interceptor);
        } catch (Exception e) {
            return false;
        }
    }
}