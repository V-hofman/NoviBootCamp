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
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "student", authorities = "STUDENT")
    void shouldReturnStudentMenuView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/students/student"));
    }

    @Test
    @WithMockUser(value = "student", authorities = "STUDENT")
    void shouldReturnStudentDetailView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Student/ShowDetails"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("/students/showStudentDetails"));
    }

    @Test
    @WithMockUser(value = "student", authorities = "STUDENT")
    void shouldReturnShowRoomView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Student/ShowRooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("roomList"))
                .andExpect(MockMvcResultMatchers.view().name("/classrooms/showClassrooms"));
    }

    @Test
    @WithMockUser(value = "student", authorities = "STUDENT")
    void shouldReturnPDFView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Student/export/pdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }


}
