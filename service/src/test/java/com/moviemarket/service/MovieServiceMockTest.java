package com.moviemarket.service;

import com.moviemarket.dao.MovieMapper;
import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class MovieServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper mockMovieMapper;


    private static final MovieDTO TEST_MOVIE = new MovieDTO(1, "Terminator",
            new Date(84, 9, 26), 8, 5D, 1, "Thriller");

    @After
    public void clean() {
        LOGGER.debug("clean()");
        EasyMock.verify(mockMovieMapper);
        EasyMock.reset(mockMovieMapper);
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        LOGGER.debug("getAllMoviesTest()");
        List<MovieDTO> movies = new ArrayList<MovieDTO>();
        EasyMock.expect(mockMovieMapper.getAllMovies()).andReturn(movies);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(movies, movieService.getAllMovies());
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        LOGGER.debug("getMovieByIdTest()");
        EasyMock.expect(mockMovieMapper.getMovieById(TEST_MOVIE.getMovieId()))
                .andReturn(TEST_MOVIE);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(TEST_MOVIE, movieService.getMovieById(TEST_MOVIE.getMovieId()));
    }

    @Test
    public void getMovieByTitleTest() throws Exception {
        LOGGER.debug("getMovieByTitleTest()");
        EasyMock.expect(mockMovieMapper.getMovieByTitle(TEST_MOVIE.getTitle()))
                .andReturn(TEST_MOVIE);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(TEST_MOVIE, movieService.getMovieByTitle(TEST_MOVIE.getTitle()));
    }

    @Test
    public void getMoviesByCategoryIdTest() throws Exception {
        LOGGER.debug("getMoviesByCategoryIdTest()");
        List<MovieDTO> movies = new ArrayList<MovieDTO>();
        EasyMock.expect(mockMovieMapper.getMoviesByCategoryId(1)).andReturn(movies);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(movies, movieService.getMoviesByCategoryId(1));
    }

    @Test
    public void addMovieTest() throws Exception {
        LOGGER.debug("addMovieTest()");
        Movie movie = new Movie();
        movie.setTitle(TEST_MOVIE.getTitle());
        movie.setReleaseDate(new Date(2000, 1, 1));
        movie.setRating(1);
        movie.setPrice(10D);
        movie.setCategoryId(1);
        EasyMock.expect(mockMovieMapper.addMovie(movie)).andReturn(TEST_MOVIE.getMovieId());
        EasyMock.expect(mockMovieMapper.getMovieById(TEST_MOVIE.getMovieId()))
                .andReturn(TEST_MOVIE);
        EasyMock.replay(mockMovieMapper);
        Integer newId = movieService.addMovie(movie);
        MovieDTO addedMovie = movieService.getMovieById(newId);
        Assert.assertEquals(TEST_MOVIE.getTitle(), addedMovie.getTitle());
    }

    @Test
    public void updateMovieTest() throws Exception {
        LOGGER.debug("updateMovieTest()");
        Movie movie = new Movie(1, "title",
                new Date(2000, 1, 1), 1, 1D, 1);
        EasyMock.expect(mockMovieMapper.updateMovie(movie)).andReturn(1);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(1, movieService.updateMovie(movie).intValue());
    }

    @Test
    public void deleteMovieTest() throws Exception {
        LOGGER.debug("deleteMovieTest()");
        EasyMock.expect(mockMovieMapper.deleteMovie(1)).andReturn(1);
        EasyMock.replay(mockMovieMapper);
        Assert.assertEquals(1, movieService.deleteMovie(1).intValue());
    }
}
