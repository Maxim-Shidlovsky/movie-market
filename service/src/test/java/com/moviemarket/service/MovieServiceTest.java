package com.moviemarket.service;

import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-service-spring.xml"})
@Transactional
public class MovieServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieService movieService;


    private static final int MOVIES_LENGTH = 14;
    private static final Integer TEST_ID = 4;
    private static final String TEST_TITLE = "Gladiator";
    private static final int FIRST_CATEGORY_LENGTH = 2;
    private static final MovieDTO TEST_MOVIE = new MovieDTO(1, "Terminator",
            new Date(84, 9, 26), 8, 5D, 1, "Thriller");

    @Test
    public void getAllMoviesTest() throws Exception {
        LOGGER.debug("getAllMoviesTest()");
        List<MovieDTO> movies = movieService.getAllMovies();

        Assert.assertNotNull(movies);
        Assert.assertEquals(MOVIES_LENGTH, movies.size());
        Assert.assertEquals(TEST_MOVIE, movies.get(0));
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        LOGGER.debug("getMovieByIdTest()");
        MovieDTO movie = movieService.getMovieById(TEST_ID);
        Assert.assertNotNull(movie);
        Assert.assertEquals(TEST_TITLE, movie.getTitle());
    }

    @Test
    public void getMovieByTitleTest() throws Exception {
        LOGGER.debug("getMovieByTitleTest()");
        MovieDTO movie = movieService.getMovieByTitle(TEST_TITLE);
        Assert.assertNotNull(movie);
        Assert.assertTrue(movie.getMovieId() == TEST_ID);
    }

    @Test
    public void getMoviesByCategoryIdTest() throws Exception {
        LOGGER.debug("getMoviesByCategoryIdTest()");
        List<MovieDTO> movies = movieService.getMoviesByCategoryId(1);
        Assert.assertNotNull(movies);
        Assert.assertTrue(movies.size() == FIRST_CATEGORY_LENGTH);
        MovieDTO movie = movies.get(0);
        Assert.assertNotNull(movie);
        Assert.assertEquals(TEST_MOVIE.getTitle(), movie.getTitle());
        Assert.assertEquals(TEST_MOVIE.getReleaseDate(), movie.getReleaseDate());
        Assert.assertEquals(TEST_MOVIE.getRating(), movie.getRating());
        Assert.assertEquals(TEST_MOVIE.getPrice(), movie.getPrice());
        Assert.assertEquals(TEST_MOVIE.getCategoryId(), movie.getCategoryId());
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
        movieService.addMovie(newMovie);

        MovieDTO addedMovie = movieService.getMovieByTitle(testTitle);
        Assert.assertNotNull(addedMovie);
        Assert.assertEquals(testTitle, addedMovie.getTitle());
        Assert.assertEquals(testReleaseDate, addedMovie.getReleaseDate());
        Assert.assertEquals(testPrice, addedMovie.getPrice());
        Assert.assertEquals(testRating, addedMovie.getRating());
        Assert.assertEquals(testCategoryId, addedMovie.getCategoryId());

        List<MovieDTO> movies = movieService.getAllMovies();
        int quantityAfter = movies.size();
        Assert.assertEquals(MOVIES_LENGTH + 1, quantityAfter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMovieWithNotNullIdTest() throws Exception {
        LOGGER.debug("addMovieWithNotNullIdTest()");
        Movie movie = new Movie(33, "title",
                new Date(2000, 1, 1), 1, 1D, 1);
        movieService.addMovie(movie);
    }

    @Test
    public void updateMovieTest() throws Exception {
        LOGGER.debug("updateMovieTest()");
        Movie movie = new Movie(TEST_ID, "newTitle",
                new Date(1000, 10, 1), 10, 100D, 3);

        movieService.updateMovie(movie);
        MovieDTO updatedMovie = movieService.getMovieById(TEST_ID);
        Assert.assertNotNull(updatedMovie);
        Assert.assertEquals(movie.getTitle(), updatedMovie.getTitle());
        Assert.assertEquals(movie.getReleaseDate(), updatedMovie.getReleaseDate());
        Assert.assertEquals(movie.getRating(), updatedMovie.getRating());
        Assert.assertEquals(movie.getPrice(), updatedMovie.getPrice());
        Assert.assertEquals(movie.getCategoryId(), updatedMovie.getCategoryId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateMovieWithNullIdTest() throws Exception {
        LOGGER.debug("updateMovieWithNullIdTest()");
        Movie movie = new Movie(null, "newTitle",
                new Date(1000, 10, 1), 10, 100D, 3);
        movieService.updateMovie(movie);
    }

    @Test
    public void deleteMovieTest() throws Exception {
        LOGGER.debug("deleteMovieTest()");
        movieService.deleteMovie(1);
        int quantityAfterDeleting = movieService.getAllMovies().size();
        Assert.assertEquals(MOVIES_LENGTH - 1, quantityAfterDeleting);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMovieByNullIdTest() throws Exception {
        LOGGER.debug("deleteMovieByNullIdTest()");
        movieService.deleteMovie(null);
    }
}
