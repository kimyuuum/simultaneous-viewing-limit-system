package com.skb.connectservice;

import com.skb.connectservice.adapter.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConnectServiceApplicationTests {

    @Autowired
    private MessageSender messageSender;

}
