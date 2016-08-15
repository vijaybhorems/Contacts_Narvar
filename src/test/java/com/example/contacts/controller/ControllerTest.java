package com.example.contacts.controller;

import com.example.contacts.ContactsApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactsApplication.class)
@WebAppConfiguration
@IntegrationTest({
        "server.contextPath:",
        "server.port:8080"
})
@ActiveProfiles("test")
public class ControllerTest {
}
