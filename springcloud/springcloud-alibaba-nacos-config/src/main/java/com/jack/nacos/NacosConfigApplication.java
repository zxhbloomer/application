package com.jack.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  NacosServer:
 *  dataId = nacos-config-example.properties ({spring.applicatin.name}.{spring.cloud.nacos.config.file-extension}),目前yml方式的配置好像有问题
 *  group = DEFAULT_GROUP
 *  content =
 *  	user.id=1
 *  	user.name=james
 *  	user.age=17
 */
@SpringBootApplication
public class NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}

@Component
class SampleRunner implements ApplicationRunner {

    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int userAge;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userName);
        System.out.println(userAge);
    }
}

@RestController
@RefreshScope
class SampleController {

    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int age;

    @RequestMapping("/user")
    public String simple() {
        return "Hello Nacos Config!" + "Hello " + userName + " " + age + "!";
    }
}