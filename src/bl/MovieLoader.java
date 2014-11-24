package bl;

import beans.Movie;
import java.util.LinkedList;
import ui.ProgressbarDLG;

public class MovieLoader {

    private String pathtomov;
    private MovieLoaderWorker mlw;
    private ProgressbarDLG dlg;

    public MovieLoader(String pathtomov, ProgressbarDLG pb) {
        this.pathtomov = pathtomov;
        this.dlg = pb;
        mlw = new MovieLoaderWorker(this.pathtomov, dlg);
    }

    public LinkedList<Movie> getMovies() {
        LinkedList<Movie> list = new LinkedList<Movie>();        
        mlw.execute();
        
        return list;
    }
}
