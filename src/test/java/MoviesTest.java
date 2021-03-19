import helpers.MoviesServiceHelper;
import model.MovieDetailsResponse;
import model.Result;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

public class MoviesTest {

    MoviesServiceHelper helper;
    private String movieId;

    @BeforeClass
    public void init() {
        helper = new MoviesServiceHelper();
    }

    @Test(priority = 1, description = "get all popular Movies")
    public void getPopularMovies() {
        List<Result> moviesList = helper.getPopularMovies();
        assertNotNull(moviesList, "Movies List is not Empty");
        assertNotNull(moviesList.get(0).getId(), "Movie id is not null");
        movieId = moviesList.get(0).getId().toString();
    }

    @Test(priority = 2, description = "get movie details")
    public void getMovieDetailsById() {
        MovieDetailsResponse movieDetailsResponse = helper.getMovieById(movieId);
        String originalTitle = movieDetailsResponse.getOriginalTitle();
        assertNotNull(originalTitle);
    }
}
