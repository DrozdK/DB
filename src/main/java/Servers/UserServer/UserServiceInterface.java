package Servers.UserServer;

import Tables.UsersTable.User;

public interface UserServiceInterface {

    User addUser(User userSource) throws Exception;

    User selectUser(int userId) throws Exception;

    User selectUserByName(String userName) throws Exception;

    boolean updateUserAddress(User user, String address)throws Exception;

    boolean deleteUser(User user) throws Exception;
}
