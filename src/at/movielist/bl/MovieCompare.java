package at.movielist.bl;

import at.movielist.beans.Movie;
import java.text.Collator;
import java.util.Comparator;

public class MovieCompare implements Comparator {

    private int option = 0;

    /**
     * Constructor
     * 
     * @param op 
     */
    public MovieCompare(int op) {
        this.option = op;
    }

    /**
     * Called whenever you have to sort the Movies
     * 
     * @param t
     * @param t1
     * @return 
     */
    @Override
    public int compare(Object t, Object t1) {

        //0 - atz | 1 - zta | 2 - voteup | 3 - votedown | 4 - release
        if (option == 0) {
            Movie m1 = (Movie) t;
            Movie m2 = (Movie) t1;

            return Collator.getInstance().compare(m1.getName(), m2.getName());
        } else if (option == 1) {
            Movie m1 = (Movie) t;
            Movie m2 = (Movie) t1;

            return Collator.getInstance().compare(m2.getName(), m1.getName());
        } else if (option == 2) {
            Movie m1 = (Movie) t;
            Movie m2 = (Movie) t1;

            return Collator.getInstance().compare(m1.getT_voteAverage().toString(), m2.getT_voteAverage().toString());
        } else if (option == 3) {
            Movie m1 = (Movie) t;
            Movie m2 = (Movie) t1;

            return Collator.getInstance().compare(m2.getT_voteAverage().toString(), m1.getT_voteAverage().toString());
        } else if (option == 4) {
            Movie m1 = (Movie) t;
            Movie m2 = (Movie) t1;

            return Collator.getInstance().compare(m1.getT_releaseYear(), m2.getT_releaseYear());
        }

        return 0;
    }

}
