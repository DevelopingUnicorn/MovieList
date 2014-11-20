package bl;

import beans.Movie;
import java.util.LinkedList;

public class MovieLoader {
    
    private String pathtomov;

    public MovieLoader(String pathtomov) {
        this.pathtomov = pathtomov;
    }
        
    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> list = new LinkedList<Movie>();
        
        return list;
    }
}
