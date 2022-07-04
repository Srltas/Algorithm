package com.github.prgrms.socialserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prgrms.socialserver.domain.User;
import com.github.prgrms.socialserver.domain.dto.UserJoinDTO;
import com.github.prgrms.socialserver.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8",true))
                .build();
    }

    @DisplayName("유저 목록 조회 성공")
    @Test
    public void findUsers() throws Exception {
        //given
        List<User> users = getUserEntityList();
        Mockito.when(userServiceImpl.findUsers()).thenReturn(users);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn()
                .getResponse();

        //then
        JSONObject responseJson = new JSONObject(response.getContentAsString());
        JSONArray listJson = new JSONArray(responseJson.get("response").toString());
        assertThat(listJson.length()).isEqualTo(users.size());
    }

    @DisplayName("유저 조회 성공")
    @Test
    public void findUser() throws Exception {
        //given
        User user = getUserEntity();
        Mockito.when(userServiceImpl.findById(1L)).thenReturn(Optional.of(user));

        //then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.seq", is(user.getSeq().intValue())))
                .andExpect(jsonPath("$.response.loginCount", is(user.getLoginCount())))
                .andExpect(jsonPath("$.response.lastLoginAt", is(user.getLastLoginAt())))
                .andExpect(jsonPath("$.response.createAt", is(user.getCreateAt())));
    }

    @DisplayName("유저 등록 성공")
    @Test
    public void save() throws Exception {
        //given
        UserJoinDTO request = UserJoinDTO.builder()
                .principal("test@gmail.com")
                .credentials("test123")
                .build();
        User parameterUser = User.builder()
                .email("test@gmail.com")
                .password("test123")
                .build();
        User resultUser = getUserEntity();

        Mockito.when(userServiceImpl.save(parameterUser)).thenReturn(Optional.of(resultUser));

        //then
        mockMvc.perform(post("/api/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response", is("가입완료")));
    }

    private User getUserEntity() {
        return User.builder()
                .seq(1L)
                .email("test@gmail.com")
                .password("test123")
                .loginCount(0)
                .lastLoginAt(null)
                .createAt(null)
                .build();
    }

    private List<User> getUserEntityList() {
        User user1 = User.builder()
                .seq(1L)
                .email("test@gmail.com")
                .password("test123")
                .loginCount(0)
                .lastLoginAt(null)
                .createAt(null)
                .build();

        User user2 = User.builder()
                .seq(2L)
                .email("test2@gmail.com")
                .password("test123")
                .loginCount(0)
                .lastLoginAt(null)
                .createAt(null)
                .build();

        return Arrays.asList(user1, user2);

    }
}