package helpers;

import model.MovieDetailsResponse;
import model.Result;
import utils.BaseResponse;
import utils.Constants;

import java.util.List;

public class MoviesServiceHelper extends BaseServiceHelper {

    public List<Result> getPopularMovies() {
        BaseResponse response = createGetResourceStatusOK(Constants.POPULAR_MOVIES, setupAuthorizedRequest()).as(BaseResponse.class);
        return response.getResults();
    }

    public MovieDetailsResponse getMovieById(String id) {
        return createGetResourceStatusOK(Constants.MOVIE, "movieId", id, setupAuthorizedRequest()).as(MovieDetailsResponse.class);
    }
}
