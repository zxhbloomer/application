package com.mina;

import com.main.GatewayApplication;
import com.main.feign.AuthorizationServerServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApplication.class)
@Slf4j
public class GatewayApplicationTest {

}
