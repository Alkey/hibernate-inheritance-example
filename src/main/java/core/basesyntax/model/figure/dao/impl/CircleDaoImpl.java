package core.basesyntax.model.figure.dao.impl;

import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.dao.CircleDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CircleDaoImpl implements CircleDao {
    @Override
    public Circle save(Circle circle) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(circle);
            transaction.commit();
            return circle;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert " + circle.getClass().getSimpleName()
                    + " entity - " + circle, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Circle> getByColor(String color) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Circle> query = session.createQuery(
                    "from Circle c where c.color = :color");
            query.setParameter("color", color);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Circles which have color  - "
                    + color, e);
        }
    }
}
