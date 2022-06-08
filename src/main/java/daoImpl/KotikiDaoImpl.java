package daoImpl;

import dao.KotikiDao;
import hibernate.HibernateSessionFactoryUtil;
import model.Kotiki;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KotikiDaoImpl implements KotikiDao {
    @Override
    public Kotiki findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotiki.class, id);
    }

    @Override
    public void save(Kotiki kotiki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(kotiki);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Kotiki kotiki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(kotiki);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Kotiki kotiki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(kotiki);
        tx1.commit();
        session.close();
    }

    @Override
    public Kotiki findOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotiki.class, id);
    }

    @Override
    public List findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Kotiki").list();
    }
}
