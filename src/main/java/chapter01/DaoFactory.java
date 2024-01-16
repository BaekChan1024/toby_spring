package chapter01;

import chapter01.connection.ConnectionMaker;
import chapter01.connection.DConnectionMaker;
import chapter01.dao.AccountDao;
import chapter01.dao.MessageDao;
import chapter01.dao.UserDao;

public class DaoFactory {

    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
