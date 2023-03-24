//package edu.ntnu.idatt2105.g6.backend.controller;
//
//import edu.ntnu.idatt2105.g6.backend.BackendApplication;
//import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
//import edu.ntnu.idatt2105.g6.backend.exception.ControllerAdvisor;
//import edu.ntnu.idatt2105.g6.backend.model.users.User;
//import edu.ntnu.idatt2105.g6.backend.security.JwtAuthenticationFilter;
//import edu.ntnu.idatt2105.g6.backend.security.SecurityConfig;
//import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
//import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//
////@SpringBootTest(
////        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
////        classes = BackendApplication.class)
////@TestPropertySource(
////        locations = "classpath:application-test.yml")
////@AutoConfigureMockMvc
////@ExtendWith(SpringExtension.class)
//@WebMvcTest
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    UserController userController;
//
//    @MockBean
//    UserService userService;
//
//    @MockBean
//    AuthenticationService authenticationService;
//
//    @MockBean
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
//                .setControllerAdvice(new ControllerAdvisor())
//                .build();
//    }
//
//    @Test
//    void test1() throws Exception {
//        UserUpdateDTO user = new UserUpdateDTO("Trym", null, null,
//                null, null, null, null, null);
//        this.mockMvc.perform(post("/profile/update", user));
//
//    }
//
//}