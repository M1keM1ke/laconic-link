package ru.m1kem1ke.laconic.link.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient(Config config) {
        return Redisson.create(config);
    }

    @Bean
    public Config config() {
        Config config = new Config();
        JsonJacksonCodec jsonJacksonCodec = new JsonJacksonCodec(new ObjectMapper());

        config.setCodec(jsonJacksonCodec)
                .useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
        ;

        return config;
    }
}
