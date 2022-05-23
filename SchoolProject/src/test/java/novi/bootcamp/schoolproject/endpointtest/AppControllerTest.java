package novi.bootcamp.schoolproject.endpointtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc()
@SpringBootTest
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnIndexView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnLoginView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldRedirectToAdminPage() throws Exception
    {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Redirect"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/Admin"));
    }

    @Test
    @WithMockUser(value = "student", authorities = "STUDENT")
    void shouldRedirectToStudentPage() throws Exception
    {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Redirect"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/Student"));
    }

}
