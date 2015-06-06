package at.movielist.bl;

import at.movielist.ui.OLDMainUI;
import at.movielist.ui.ProgressbarDLG;
import java.util.Locale;

public class OLDMovieLoader {

    private String pathtomov;
    private OLDMovieLoaderWorker mlw;
    private ProgressbarDLG dlg;
    private Locale loc;

    public OLDMovieLoader(String pathtomov, ProgressbarDLG pb, Locale loc) {
        this.pathtomov = pathtomov;
        this.dlg = pb;
        this.loc = loc;
    }

    public void getMovies(OLDMainUI mui) {
        mlw = new OLDMovieLoaderWorker(this.pathtomov, dlg, mui, loc);
        mlw.execute();
    }

    public void setPathtomov(String pathtomov) {
        this.pathtomov = pathtomov;
    }
}
