package novi.bootcamp.schoolproject.endpointtest;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;


    User tempUser = createTestUser();
    Classrooms tempClass = createTestRoom();

    //region userPages

    //region getRequests
    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnAdminView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnRegisterUserView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/RegisterUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/RegisterUser"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnRemoveUserView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/RemoveUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/RemoveUser"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnUpdateUserView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/UpdateUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/UpdateUser"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnShowUsersView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/ShowUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/ShowUsers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userlist"));
    }
    //endregion

    //region postRequests

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldRegisterUserAndReturnAdminView() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/RegisterUser")
                                .param("username", tempUser.getUsername())
                                .param("password", tempUser.getPassword())
                                .param("personName", tempUser.getPersonName())
                                .param("role", tempUser.getRole())
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldRemoveUserAndReturnAdminView() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/RemoveUser")
                        .param("username", tempUser.getUsername())
                        .param("password", tempUser.getPassword())
                        .param("personName", tempUser.getPersonName())
                        .param("role", tempUser.getRole())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldUpdateUserAndReturnAdminView() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/UpdateUser")
                        .param("username", tempUser.getUsername())
                        .param("password", tempUser.getPassword())
                        .param("personName", tempUser.getPersonName())
                        .param("role", tempUser.getRole())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/RemoveUser")
                        .param("username", tempUser.getUsername())
                        .param("password", tempUser.getPassword())
                        .param("personName", tempUser.getPersonName())
                        .param("role", tempUser.getRole())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();
    }

    //endregion

    //endregion

    //region classroom pages

    //region GetRequests
    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnShowRoomsView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/ShowRooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/classrooms/showClassrooms"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("roomList"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnRegisterClassView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/RegisterClass"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/classrooms/RegisterClass"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teacher"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("classroom"));
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldReturnRemoveClassView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Admin/RemoveClass"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/classrooms/RemoveClass"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("classroom"));
    }
    //endregion

    //region PostRequests

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldRegisterClassRoomAndReturnAdminView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/RegisterClass")
                        .flashAttr("classroom", tempClass))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();
    }

    @Test
    @WithMockUser(value = "admin", authorities = "ADMIN")
    void shouldRemoveClassRoomAndReturnAdminView() throws Exception
    {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/Admin/RemoveClass")
                        .flashAttr("room", tempClass))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/admin"))
                .andReturn();

    }
    //endregion

    //endregion

    //region testObjects
    public User createTestUser()
    {
        User user = new User();
        user.setPassword("Password");
        user.setUsername("Derik12");
        user.setPersonName("Pietje");
        user.setRole("Admin");
        return user;
    }


    public Classrooms createTestRoom()
    {
        Classrooms room = new Classrooms.roomBuilder()
                .classRoom("12")
                .className("Math")
                .classDate("12-11-2021")
                .classTeacher("Pieter")
                .build();
        return room;
    }
    //endregion
}
