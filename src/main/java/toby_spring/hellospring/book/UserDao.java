package toby_spring.hellospring.book;

import java.sql.*;

public abstract class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into users values(?,?,?)");
        statement.setString(1, user.getId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getPassword());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));

        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        User user = new User();
//        user.setId("1");
//        user.setName("baekchan");
//        user.setPassword("123456");
//        UserDao userDao = new UserDao();
//
//        userDao.add(user);
//
//        User userTest = userDao.get("1");
//        System.out.println(userTest);
//    }
}
