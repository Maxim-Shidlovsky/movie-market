package com.moviemarket.service;

import com.moviemarket.model.Movie;
import com.moviemarket.model.MovieDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */
public interface MovieService {

    public List<MovieDTO> getAllMovies() throws DataAccessException;

    public MovieDTO getMovieById(Integer movieId) throws DataAccessException;

    public MovieDTO getMovieByTitle(String title) throws DataAccessException;

    public List<MovieDTO> getMoviesByCategoryId(Integer categoryId) throws DataAccessException;

    public Integer addMovie(Movie movie) throws DataAccessException;

    public Integer updateMovie(Movie movie) throws DataAccessException;

    public Integer deleteMovie(Integer movieId) throws DataAccessException;
}
