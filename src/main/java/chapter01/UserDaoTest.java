package chapter01;

import chapter01.connection.DConnectionMaker;
import chapter01.dao.UserDao;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DConnectionMaker dConnectionMaker = new DConnectionMaker();

        UserDao userDao = new DaoFactory().userDao();
    }
}
