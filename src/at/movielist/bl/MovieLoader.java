package at.movielist.bl;

import at.movielist.ui.MainUI;
import at.movielist.ui.ProgressbarDLG;
import java.util.Locale;

public class MovieLoader {

    private String pathtomov;
    private MovieLoaderWorker mlw;
    private ProgressbarDLG dlg;
    private Locale loc;

    public MovieLoader(String pathtomov, ProgressbarDLG pb, Locale loc) {
        this.pathtomov = pathtomov;
        this.dlg = pb;
        this.loc = loc;
    }

    public void getMovies(MainUI mui) {
        mlw = new MovieLoaderWorker(this.pathtomov, dlg, mui, loc);
        mlw.execute();
    }

    public void setPathtomov(String pathtomov) {
        this.pathtomov = pathtomov;
    }
}
