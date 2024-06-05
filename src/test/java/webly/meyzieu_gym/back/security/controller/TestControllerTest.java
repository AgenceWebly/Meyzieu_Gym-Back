package webly.meyzieu_gym.back.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import webly.meyzieu_gym.back.security.TestSecurityConfig;

@WebMvcTest(TestController.class)
@Import(TestSecurityConfig.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllAccess() throws Exception {
        mockMvc.perform(get("/api/test/all"))
            .andExpect(status().isOk())
            .andExpect(content().string("Public Content."));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testUserAccess_withUser() throws Exception {
        mockMvc.perform(get("/api/test/user"))
            .andExpect(status().isOk())
            .andExpect(content().string("User Content."));
    }

    @Test
    @WithMockUser(roles = {"GUARDIAN"})
    void testGuardianAccess_withGuardian() throws Exception {
        mockMvc.perform(get("/api/test/guardian"))
            .andExpect(status().isOk())
            .andExpect(content().string("Guardian Board."));
    }

    @Test
    @WithMockUser(roles = {"TRAINER"})
    void testTrainerAccess_withTrainer() throws Exception {
        mockMvc.perform(get("/api/test/trainer"))
            .andExpect(status().isOk())
            .andExpect(content().string("Trainer Board."));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testAdminAccess_withAdmin() throws Exception {
        mockMvc.perform(get("/api/test/admin"))
            .andExpect(status().isOk())
            .andExpect(content().string("Admin Board."));
    }

}