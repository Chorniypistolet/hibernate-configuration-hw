package mate.academy.dao;

import java.util.Optional;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = getSession();
        Movie movie;
        try {
            movie = session.get(Movie.class, id);
        } catch (HibernateException e) {
            throw new DataProcessingException("Failed to get movie by id", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(movie);
    }

    public Session getSession() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}
