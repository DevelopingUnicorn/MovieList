package at.movielist.bl;

import at.movielist.ui.MainUI;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class MovieLoader {

    private String[] pathtomov;
    private MovieLoaderWorker mlw;
    private final JProgressBar progressbar;
    private final Locale loc;
    private JLabel lab;

    /**
     * Constructor
     *
     * @param pathsToMovies
     * @param pb
     * @param loc
     */
    public MovieLoader(String[] pathsToMovies, JProgressBar pb, JLabel xoy, Locale loc) {
        this.pathtomov = pathsToMovies;
        this.progressbar = pb;
        this.loc = loc;
        this.lab = xoy;
    }

    /**
     * Loads the movies and sets them in the UI(-obj)
     *
     * @param mui
     */
    public void getMovies(MainUI mui) {
        mlw = new MovieLoaderWorker(this.pathtomov, progressbar, lab, mui, loc);
        mlw.execute();
    }

    public void setPathtomov(String[] pathtomov) {
        this.pathtomov = pathtomov;
    }
}
