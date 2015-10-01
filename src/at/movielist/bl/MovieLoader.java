package at.movielist.bl;

import at.movielist.ui.MainUI;
import at.movielist.ui.ProgressbarDLG;
import java.util.Locale;

public class MovieLoader {

    private String[] pathtomov;
    private MovieLoaderWorker mlw;
    private final ProgressbarDLG dlg;
    private final Locale loc;

    /**
     * Constructor
     * 
     * @param pathsToMovies
     * @param pb
     * @param loc 
     */
    public MovieLoader(String[] pathsToMovies, ProgressbarDLG pb, Locale loc) {
        this.pathtomov = pathsToMovies;
        this.dlg = pb;
        this.loc = loc;
    }

    /**
     * Loads the movies and sets them in the UI(-obj)
     *
     * @param mui
     */
    public void getMovies(MainUI mui) {
        mlw = new MovieLoaderWorker(this.pathtomov, dlg, mui, loc);
        mlw.execute();
    }

    public void setPathtomov(String[] pathtomov) {
        this.pathtomov = pathtomov;
    }
}
