package Tables.UsersTable;

import Helper.MySQLException;
import Connection.DBConnection;

import java.sql.*;

import static enums.Error.*;
import static enums.Command.*;

public class UsersTableMethods implements UsersTableInterface {

    Connection connection;

    @Override
    public void insert(User user) throws MySQLException {
        try {
            connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(USERS_TABLE_INSERT.getCommand());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new MySQLException(USERS_TABLE_ERROR_INSERT, e);
        }
    }

    @Override
    public User select(Integer userId) throws MySQLException {
        User user;
        try{
            connection = new DBConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(USERS_TABLE_SELECT_BY_USERID.getCommand() + userId);
            user = User.builder()
                    .id(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
            statement.close();
            connection.close();
        }catch(SQLException e){
            throw new MySQLException(USERS_TABLE_ERROR_SELECT_BY_USERID, e);
        }
        return user;
    }

    @Override
    public User selectByName(String name) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(USERS_TABLE_SELECT_BY_NAME.getCommand() + name + "'");
            User user = User.builder()
                    .id(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
            statement.close();
            connection.close();
            return user;
        } catch(SQLException e){
            throw new MySQLException(USERS_TABLE_ERROR_SELECT_BY_NAME, e);
        }
    }

    @Override
    public void update(User user, String address) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USERS_TABLE_UPDATE.getCommand());
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            throw new MySQLException(USERS_TABLE_ERROR_UPDATE, e);
        }
    }

    @Override
    public void delete(Integer userId) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USERS_TABLE_DELETE_BY_USERID.getCommand());
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            throw new MySQLException(USERS_TABLE_ERROR_DELETE_BY_USERID, e);
        }
    }
    }
