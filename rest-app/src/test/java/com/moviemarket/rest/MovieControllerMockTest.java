package com.moviemarket.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import com.moviemarket.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Maxim on 14.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:rest-mock-test.xml"})
public class MovieControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private MovieRestController movieController;

    private MockMvc mockMvc;

    @Autowired
    private MovieService mockMovieService;


    private static final MovieDTO testMovie = new MovieDTO(1, "Terminator",
            new Date(84, 9, 26), 8, 5D, 1, "Thriller");

    @Before
    public void setUp() {
        LOGGER.debug("setUp()");
        mockMvc = standaloneSetup(movieController).build();
    }

    @After
    public void tearDown() {
        LOGGER.debug("tearDown()");
        verify(mockMovieService);
        reset(mockMovieService);
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        LOGGER.debug("getAllMoviesTest()");
        expect(mockMovieService.getAllMovies()).andReturn(
                Arrays.<MovieDTO>asList(
                        new MovieDTO(8, "testTitle",
                                new Date(2000, 10, 1), 10, 1D,
                                1, "")));
        replay(mockMovieService);

        mockMvc.perform(
                get("/movies"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        LOGGER.debug("getMovieByIdTest()");
        expect(mockMovieService.getMovieById(testMovie.getMovieId()))
                .andReturn(testMovie);
        replay(mockMovieService);

        String movieString = new ObjectMapper().writeValueAsString(testMovie);

        mockMvc.perform(
                get("/movies/movie/" + testMovie.getMovieId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(movieString));
    }

    @Test
    public void getMovieByTitleTest() throws Exception {
        LOGGER.debug("getMovieByTitleTest()");
        expect(mockMovieService.getMovieByTitle(testMovie.getTitle()))
                .andReturn(testMovie);
        replay(mockMovieService);

        String movieString = new ObjectMapper().writeValueAsString(testMovie);

        mockMvc.perform(
                get("/movies/movie/title/" + testMovie.getTitle())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(movieString));
    }

    @Test
    public void getMoviesByCategoryIdTest() throws Exception {
        LOGGER.debug("getMoviesByCategoryIdTest()");
        expect(mockMovieService.getMoviesByCategoryId(testMovie.getCategoryId()))
                .andReturn(Arrays.<MovieDTO>asList(testMovie));
        replay(mockMovieService);

        String movieString = new ObjectMapper().writeValueAsString(testMovie);

        mockMvc.perform(
                get("/movies/category/" + testMovie.getCategoryId() + "/movies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addMovieTest() throws Exception {
        LOGGER.debug("addMovieTest()");
        expect(mockMovieService.addMovie(anyObject(Movie.class)));
        replay(mockMovieService);

        String movieString = new ObjectMapper().writeValueAsString(
                new Movie(9, "testTitle",
                        new Date(2000, 1, 1), 1, 1D, 1));

        mockMvc.perform(
                post("/movies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieString))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateMovieTest() throws Exception {
        LOGGER.debug("updateMovieTest()");
        expect(mockMovieService.updateMovie(anyObject(Movie.class)));
        replay(mockMovieService);

        String movieString = new ObjectMapper().writeValueAsString(
                new Movie(9, "testTitle",
                        new Date(2000, 1, 1), 1, 1D, 1));

        mockMvc.perform(
                put("/movies/movie")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieString))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteMovieTest() throws Exception {
        LOGGER.debug("deleteMovieTest()");
        expect(mockMovieService.deleteMovie(anyObject(Integer.class)));
        replay(mockMovieService);

        mockMvc.perform(
                delete("/movies/movie/8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}
