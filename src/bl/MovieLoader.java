package bl;

import beans.Movie;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieLoader {

    private String pathtomov;
    private MovieLoaderThread mlt;

    public MovieLoader(String pathtomov) {
        this.pathtomov = pathtomov;
        mlt = new MovieLoaderThread(pathtomov);
    }

    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> list = new LinkedList<Movie>();

        Thread moviethread = new Thread(mlt);
        moviethread.start();
        try {
            moviethread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        list = mlt.getListe();

        return list;
    }
}
