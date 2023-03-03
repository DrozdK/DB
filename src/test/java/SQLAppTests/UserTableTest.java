package SQLAppTests;

import Servers.UserServer.UserService;
import Servers.UserServer.UserServiceInterface;
import Tables.UsersTable.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserTableTest {

    UserServiceInterface userTable = new UserService();
    User user = new User();
    String userName = RandomStringUtils.randomAlphabetic(50);

    @Order(1)
    @Test
    public void addUserTest() throws Exception {
        user.setName(userName);
        userTable.addUser(user);
        String factName = userTable.selectUserByName(userName).getName();
        assertEquals(userName, factName);
    }

    @Order(2)
    @Test
    public void updateUserAddress() throws Exception {
        int userId = userTable.selectUserByName(userName).getId();
        user.setId(userId);
        String address = RandomStringUtils.randomAlphabetic(255);
        userTable.updateUserAddress(user, address);
        String factAddress = userTable.selectUser(userId).getAddress();
        assertEquals(address, factAddress);
    }

    @AfterAll
    public void deleteUser() throws Exception {
        int userId = userTable.selectUserByName(userName).getId();
        user.setId(userId);
        userTable.deleteUser(user);
    }
}
