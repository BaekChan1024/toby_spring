package chapter01.factory;

import chapter01.connection.ConnectionMaker;
import chapter01.connection.CountingConnectionMaker;
import chapter01.connection.DConnectionMaker;
import chapter01.connection.SimpleConnectionMaker;
import chapter01.dao.AccountDao;
import chapter01.dao.MessageDao;
import chapter01.dao.UserDao;
import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/springbook");
        dataSource.setUsername("root");
        dataSource.setPassword("test");
        return dataSource;
    }
}
