package bl;

import beans.Movie;
import java.text.Collator;
import java.util.Comparator;

public class MovieCompare implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
        
        Movie m1 = (Movie)t;
        Movie m2 = (Movie)t1;
        
        return Collator.getInstance().compare(m1.getName(), m2.getName());      
    }
    
}
