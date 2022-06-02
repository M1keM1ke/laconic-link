package ru.m1kem1ke.laconic.link;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaconicLinkApplication implements CommandLineRunner {
    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(LaconicLinkApplication.class, args);
    }

    @Override
    public void run(String... args) {
//      initialize first entry of url map for correct id generating
        redissonClient.getMap("url").put(0, "dummy");
    }

}
