package com.moviemarket.service;

import com.moviemarket.dao.MovieMapper;
import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieMapper movieMapper;

    public void setMovieMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDTO> getAllMovies() throws DataAccessException {
        LOGGER.debug("getAllMovies()");
        return movieMapper.getAllMovies();
    }

    @Override
    public MovieDTO getMovieById(Integer movieId) throws DataAccessException {
        LOGGER.debug("getMovieById({})", movieId);
        Assert.notNull(movieId, "Movie's ID mustn't be null.");

        return movieMapper.getMovieById(movieId);
    }

    @Override
    public MovieDTO getMovieByTitle(String title) throws DataAccessException {
        LOGGER.debug("getMovieByTitle({})", title);
        Assert.notNull(title, "Movie's title mustn't be null.");
        Assert.hasText(title, "Movie's title must have text.");

        return movieMapper.getMovieByTitle(title);
    }

    @Override
    public List<MovieDTO> getMoviesByCategoryId(Integer categoryId) throws DataAccessException {
        LOGGER.debug("getMoviesByCategoryId({})", categoryId);
        Assert.notNull(categoryId, "Category's ID must not be null.");

        return movieMapper.getMoviesByCategoryId(categoryId);
    }

    @Override
    public Integer addMovie(Movie movie) throws DataAccessException {
        LOGGER.debug("addMovie({})", movie);
        Assert.notNull(movie, "Movie mustn't be null.");
        Assert.isNull(movie.getMovieId(), "Movie's ID must be null.");
        Assert.notNull(movie.getTitle(), "Movie's title mustn't be null.");
        Assert.hasText(movie.getTitle(), "Movie's title must have text.");
        Assert.notNull(movie.getReleaseDate(), "Movie's release date mustn't be null.");
        Assert.notNull(movie.getPrice(), "Movie's price mustn't be null.");
        Assert.notNull(movie.getRating(), "Movie's rating mustn't be null.");
        Assert.notNull(movie.getCategoryId(), "Category's ID mustn't be null.");

        return movieMapper.addMovie(movie);
    }

    @Override
    public Integer updateMovie(Movie movie) throws DataAccessException {
        LOGGER.debug("updateMovie({})", movie);
        Assert.notNull(movie, "Movie mustn't be null.");
        Assert.notNull(movie.getMovieId(), "Movie's ID mustn't be null.");
        Assert.notNull(movie.getTitle(), "Movie's title mustn't be null.");
        Assert.hasText(movie.getTitle(), "Movie's title must have text.");
        Assert.notNull(movie.getReleaseDate(), "Movie's release date mustn't be null.");
        Assert.notNull(movie.getPrice(), "Movie's price mustn't be null.");
        Assert.notNull(movie.getRating(), "Movie's rating mustn't be null.");
        Assert.notNull(movie.getCategoryId(), "Category's ID mustn't be null.");

        return movieMapper.updateMovie(movie);
    }

    @Override
    public Integer deleteMovie(Integer movieId) throws DataAccessException {
        LOGGER.debug("deleteMovie({})", movieId);
        Assert.notNull(movieId, "Movie's ID mustn't be null.");

        return movieMapper.deleteMovie(movieId);
    }
}
