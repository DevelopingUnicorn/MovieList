package at.movielist.bl;

import at.movielist.beans.Movie;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

public class MovieListModel extends AbstractListModel {

    private LinkedList<Movie> movies = new LinkedList<Movie>();

    /**
     * Returns how many Elements are in the List
     * @return 
     */
    @Override
    public int getSize() {
        return movies.size();
    }

    /**
     * Returns the List element at the given index
     * @param index
     * @return 
     */
    @Override
    public Object getElementAt(int index) {
        return movies.get(index);
    }

    /**
     * Adds an Item to the List
     * @param m 
     */
    public void add(Movie m) {
        movies.add(m);
    }

    /**
     * Clears the List
     */
    public void clear() {
        movies.clear();
    }

    /**
     * Resets the List with movlist
     * @param movlist 
     */
    public void setList(LinkedList<Movie> movlist) {
        movies.clear();
        movies.addAll(movlist);
    }
}
