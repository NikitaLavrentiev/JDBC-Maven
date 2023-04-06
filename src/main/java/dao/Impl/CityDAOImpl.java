package dao.Impl;

import dao.CityDAO;
import models.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public City findCityById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
    }

    @Override
    public List<City> getAllCities() {
        return (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM City").list();
    }

    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }
@Override
    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }
@Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}