package at.movielist.bl;

import at.movielist.ui.MainUI;
import at.movielist.ui.ProgressbarDLG;

public class MovieLoader {

    private String pathtomov;
    private MovieLoaderWorker mlw;
    private ProgressbarDLG dlg;

    public MovieLoader(String pathtomov, ProgressbarDLG pb) {
        this.pathtomov = pathtomov;
        this.dlg = pb;
    }

    public void getMovies(MainUI mui) {   
        mlw = new MovieLoaderWorker(this.pathtomov, dlg, mui);
        mlw.execute();
    }
    
    public void getMoviesFromFile()
    {
        
    }
}
