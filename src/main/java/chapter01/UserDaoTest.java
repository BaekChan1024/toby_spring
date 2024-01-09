package chapter01;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DConnectionMaker dConnectionMaker = new DConnectionMaker();

        UserDao userDao = new UserDao(dConnectionMaker);
    }
}
