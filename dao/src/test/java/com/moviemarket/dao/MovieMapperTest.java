package com.moviemarket.dao;

import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-mybatis-spring.xml"})
@Transactional
public class MovieMapperTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieMapper movieMapper;


    private static final int MOVIES_LENGTH = 14;
    private static final Integer TEST_ID = 4;
    private static final String TEST_TITLE = "Gladiator";
    private static final int FIRST_CATEGORY_LENGTH = 2;
    private static final MovieDTO TEST_MOVIE = new MovieDTO(1, "Terminator",
            new Date(84, 9, 26), 8, 5D, 1, "Thriller");

    @Test
    public void getAllMoviesTest() throws Exception {
        LOGGER.debug("getAllMoviesTest()");
        List<MovieDTO> movies = movieMapper.getAllMovies();

        Assert.assertEquals(MOVIES_LENGTH, movies.size());
        Assert.assertEquals(TEST_MOVIE, movies.get(0));
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        LOGGER.debug("getMovieByIdTest()");
        MovieDTO movie = movieMapper.getMovieById(TEST_ID);
        Assert.assertNotNull(movie);
        Assert.assertEquals(TEST_TITLE, movie.getTitle());
    }

    @Test
    public void getMovieByTitleTest() throws Exception {
        LOGGER.debug("getMovieByTitleTest()");
        MovieDTO movie = movieMapper.getMovieByTitle(TEST_TITLE);
        Assert.assertNotNull(movie);
        Assert.assertEquals(TEST_ID, movie.getMovieId());
    }

    @Test
    public void getMoviesByCategoryIdTest() throws Exception {
        LOGGER.debug("getMoviesByCategoryIdTest()");
        List<MovieDTO> movies = movieMapper.getMoviesByCategoryId(1);
        Assert.assertNotNull(movies);
        Assert.assertTrue(movies.size() == FIRST_CATEGORY_LENGTH);
        MovieDTO movie = movies.get(0);
        Assert.assertNotNull(movie);
        Assert.assertEquals(TEST_MOVIE, movie);
    }

    @Test
    public void addMovieTest() throws Exception {
        LOGGER.debug("addMovieTest()");

        Movie newMovie = new Movie();
        String testTitle = "testTitle";
        Date testReleaseDate = new Date(1000, 10, 1);
        Double testPrice = 10D;
        Integer testRating = 2;
        Integer testCategoryId = 1;
        newMovie.setTitle(testTitle);
        newMovie.setReleaseDate(testReleaseDate);
        newMovie.setPrice(testPrice);
        newMovie.setRating(testRating);
        newMovie.setCategoryId(testCategoryId);
        movieMapper.addMovie(newMovie);

        MovieDTO addedMovie = movieMapper.getMovieByTitle(testTitle);
        Assert.assertNotNull(addedMovie);
        Assert.assertEquals(testTitle, addedMovie.getTitle());
        Assert.assertEquals(testReleaseDate, addedMovie.getReleaseDate());
        Assert.assertEquals(testPrice, addedMovie.getPrice());
        Assert.assertEquals(testRating, addedMovie.getRating());
        Assert.assertEquals(testCategoryId, addedMovie.getCategoryId());

        List<MovieDTO> movies = movieMapper.getAllMovies();
        int quantityAfterAdding = movies.size();
        Assert.assertEquals(MOVIES_LENGTH + 1, quantityAfterAdding);
    }

    @Test
    public void updateMovieTest() throws Exception {
        LOGGER.debug("updateMovieTest()");
        Movie movie = new Movie(TEST_ID, "newTitle",
                new Date(1000, 10, 1), 10, 100D, 3);

        movieMapper.updateMovie(movie);
        MovieDTO updatedMovie = movieMapper.getMovieById(TEST_ID);
        Assert.assertNotNull(updatedMovie);
        Assert.assertEquals(movie.getTitle(), updatedMovie.getTitle());
        Assert.assertEquals(movie.getReleaseDate(), updatedMovie.getReleaseDate());
        Assert.assertEquals(movie.getRating(), updatedMovie.getRating());
        Assert.assertEquals(movie.getPrice(), updatedMovie.getPrice());
        Assert.assertEquals(movie.getCategoryId(), updatedMovie.getCategoryId());
    }

    @Test
    public void deleteMovieTest() throws Exception {
        LOGGER.debug("deleteMovieTest()");
        movieMapper.deleteMovie(1);
        int quantityAfterDeleting = movieMapper.getAllMovies().size();
        Assert.assertEquals(MOVIES_LENGTH - 1, quantityAfterDeleting);
    }
}
