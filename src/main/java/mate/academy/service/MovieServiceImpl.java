package mate.academy.service;

import mate.academy.dao.MovieDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

    @Inject
    private final MovieDao movieDao;

    @Inject
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public MovieServiceImpl() {
        this.movieDao = null;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElse(null);
    }
}
