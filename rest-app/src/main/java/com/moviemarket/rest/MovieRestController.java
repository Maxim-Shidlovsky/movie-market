package com.moviemarket.rest;

import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import com.moviemarket.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/movies")
public class MovieRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieService movieService;


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<MovieDTO> getAllMovies() {
        LOGGER.debug("getAllMovies()");
        return movieService.getAllMovies();
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public @ResponseBody MovieDTO getMovieById(@PathVariable(value = "id") Integer movieId) {
        LOGGER.debug("getMovieById({})", movieId);
        return movieService.getMovieById(movieId);
    }

    @RequestMapping(value = "/movie/title/{title}", method = RequestMethod.GET)
    public @ResponseBody MovieDTO getMovieByTitle(@PathVariable(value = "title") String title) {
        LOGGER.debug("getMovieByTitle({})", title);
        return movieService.getMovieByTitle(title);
    }

    @RequestMapping(value = "/category/{id}/movies", method = RequestMethod.GET)
    public @ResponseBody List<MovieDTO> getMoviesByCategoryId(
            @PathVariable(value = "id") Integer categoryId) {
        LOGGER.debug("getMoviesByCategoryId({})", categoryId);
        return movieService.getMoviesByCategoryId(categoryId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMovie(@RequestBody Movie movie) {
        LOGGER.debug("addMovie({})", movie);
        movieService.addMovie(movie);
    }

    @RequestMapping(value = "/movie", method = RequestMethod.PUT,
            headers = "Content-Type=application/json")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateMovie(@RequestBody Movie movie) {
        LOGGER.debug("updateMovie({})", movie);
        movieService.updateMovie(movie);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMovie(@PathVariable(value = "id") Integer movieId) {
        LOGGER.debug("deleteMovie({})", movieId);
        movieService.deleteMovie(movieId);
    }
}
