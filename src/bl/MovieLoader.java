package bl;

import beans.Movie;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ProgressbarDLG;

public class MovieLoader {

    private String pathtomov;
    private MovieLoaderThread mlt;
    private ProgressbarDLG dlg;

    public MovieLoader(String pathtomov, ProgressbarDLG pb) {
        this.pathtomov = pathtomov;
        this.dlg = pb;
        mlt = new MovieLoaderThread(this.pathtomov, dlg);
    }

    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> list = new LinkedList<Movie>();

        Thread moviethread = new Thread(mlt);
        moviethread.setName("MovieThread");
        moviethread.start();
                       
        list = mlt.getListe();

        return list;
    }
}
