package com.skb.checkservice.config.redis;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfigurationProperties { //spring boot가 설정 파일에서 읽어온 값을 관리할 객체를 나타냄

    List<String> nodes;

    public List<String> getNodes(){
        return nodes;
    }

    public void setNodes(List<String> nodes){
        this.nodes = nodes;
    }

}
