package at.movielist.bl;

import at.movielist.beans.Movie;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

public class MovieListModel extends AbstractListModel {

    private LinkedList<Movie> movies = new LinkedList<Movie>();

    @Override
    public int getSize() {
        return movies.size();
    }

    @Override
    public Object getElementAt(int index) {
        return movies.get(index);
    }

    public void add(Movie m) {
        movies.add(m);
    }

    public void clear() {
        movies.clear();
    }

    public void setList(LinkedList<Movie> movlist) {
        movies.clear();
        movies.addAll(movlist);
    }
}
