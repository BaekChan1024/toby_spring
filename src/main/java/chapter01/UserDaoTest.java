package chapter01;

import chapter01.connection.DConnectionMaker;
import chapter01.factory.DaoFactory;
import chapter01.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DConnectionMaker dConnectionMaker = new DConnectionMaker();

        UserDao userDao1 = new DaoFactory().userDao();
        UserDao userDao2 = new DaoFactory().userDao();
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao1 = ac.getBean("userDao", UserDao.class);
        UserDao dao2 = ac.getBean("userDao", UserDao.class);
        System.out.println(userDao1 == userDao2);
        System.out.println(dao1 == dao2);
    }
}
