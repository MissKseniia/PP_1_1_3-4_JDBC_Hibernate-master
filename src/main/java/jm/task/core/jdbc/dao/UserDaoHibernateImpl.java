package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final String tableName = Util.getTableName();
    private final Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        String sql = String.format("create table if not exists %s " +
                " (id bigint not null auto_increment primary key," +
                " name varchar(10)," +
                " lastName varchar(15)," +
                " age TINYINT)", tableName);
        doTransaction(sql);
    }

    @Override
    public void dropUsersTable() {
        String sql = String.format("drop table %s",tableName);
        doTransaction(sql);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        User user;
        try {
            user = session.load(User.class,id);
            session.delete(user);
            session.clear();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            users = session.createQuery(criteria).getResultList();
            session.clear();
        } catch (Exception e) {
            System.err.println(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = String.format("delete from %s",tableName);
        doTransaction(sql);
    }

    @Override
    public void close() {

        try {
            session.close();
        } catch (HibernateException e) {
            System.err.println(e);
        }
    }

    private void doTransaction(String sql) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e);
        }
    }

}
