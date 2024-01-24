package chapter02;

import chapter02.connection.CountingConnectionMaker;
import chapter02.connection.DConnectionMaker;
import chapter02.dao.UserDao;
import chapter02.entity.User;
import chapter02.factory.DaoFactory;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = ac.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);
        User user1 = new User();
        user1.setId("user");
        user1.setName("BaekSeungChan");
        user1.setPassword("married");

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        User user2 = new User();
        user2.setId("user2");
        user2.setName("BaekSeungChan2");
        user2.setPassword("married");

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User user3 = new User();
        user3.setId("user3");
        user3.setName("BaekSeungChan3");
        user3.setPassword("married");

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);

        User user4 = dao.get(user1.getId());
        assertThat(user4.getName()).isEqualTo(user1.getName());
    }

    @Test
    public void getUserFailure() throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = ac.getBean("userDao", UserDao.class);
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unknown");
        });
    }

}
