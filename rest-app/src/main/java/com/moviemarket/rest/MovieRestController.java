package com.moviemarket.rest;

import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import com.moviemarket.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@Controller
public class MovieRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieService movieService;


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String getAllMovies(Model model) {
        LOGGER.debug("getAllMovies()");
        List<MovieDTO> movieList = movieService.getAllMovies();
        model.addAttribute(movieList);
        return "movies";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String getMovieById(@PathVariable(value = "id") Integer movieId, Model model) {
        LOGGER.debug("getMovieById({})", movieId);
        List<MovieDTO> movieList = new ArrayList<MovieDTO>();
        movieList.add(movieService.getMovieById(movieId));
        model.addAttribute(movieList);
        return "movies";
    }

    @RequestMapping(value = "/movie/title/{title}", method = RequestMethod.GET)
    public String getMovieByTitle(@PathVariable(value = "title") String title, Model model) {
        LOGGER.debug("getMovieByTitle({})", title);
        List<MovieDTO> movieList = new ArrayList<MovieDTO>();
        movieList.add(movieService.getMovieByTitle(title));
        model.addAttribute(movieList);
        return "movies";
    }

    @RequestMapping(value = "/category/{id}/movies", method = RequestMethod.GET)
    public String getMoviesByCategoryId(@PathVariable(value = "id") Integer categoryId, Model model) {
        LOGGER.debug("getMoviesByCategoryId({})", categoryId);
        List<MovieDTO> movieList = movieService.getMoviesByCategoryId(categoryId);
        model.addAttribute(movieList);
        return "movies";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addMovie(@RequestParam String title,
      @RequestParam(name = "releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
      @RequestParam Integer rating, @RequestParam Double price, @RequestParam Integer categoryId,
                           Model model) {
        LOGGER.debug("addMovie(title={}, releaseDate={}, rating={}, price={}, categoryId={})",
                title, releaseDate, rating, price, categoryId);
        Movie movie = new Movie(null, title, releaseDate, rating, price, categoryId);
        movieService.addMovie(movie);
        model.addAttribute(movieService.getAllMovies());
        return "movies";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateMovie(@RequestParam Integer movieId, @RequestParam String title,
      @RequestParam(name = "releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
      @RequestParam Integer rating, @RequestParam Double price, @RequestParam Integer categoryId,
                              Model model) {
        LOGGER.debug("updateMovie(movieId={}, title={}, releaseDate={}, rating={}, price={}, " +
            "categoryId={})", movieId, title, releaseDate, rating, price, categoryId);
        Movie movie = new Movie(movieId, title, releaseDate, rating, price, categoryId);
        movieService.updateMovie(movie);
        model.addAttribute(movieService.getAllMovies());
        return "movies";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteMovie(@PathVariable(value = "id") Integer movieId, Model model) {
        LOGGER.debug("deleteMovie({})", movieId);
        movieService.deleteMovie(movieId);
        model.addAttribute(movieService.getAllMovies());
        return "movies";
    }
}
