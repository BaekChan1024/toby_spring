package chapter01;

import chapter01.connection.CountingConnectionMaker;
import chapter01.connection.DConnectionMaker;
import chapter01.entity.User;
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
        User user = new User();
        user.setId("test1");
        user.setName("testName");
        user.setPassword("testPassword");
        dao1.add(user);
        CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println(ccm.getCounter());
    }
}
