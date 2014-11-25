package bl;

import beans.Movie;
import java.util.LinkedList;

public class UtilityClass {
    public String getSizeAndNumberOfFiles(LinkedList<Movie> list)
    {
        double filesize = 0.0;
        int numberoffiles = list.size();
        String gibormib = "";
        
        for (Movie m: list) {
            String[] spl = m.getFilesize().split("\\s+");
            
            filesize+= Double.parseDouble(spl[0]);
            
            if(spl[1].equals("GiB"))
            {
                gibormib = "GiB";
            }else if(spl[1].equals("MiB"))
            {
                gibormib = "MiB";
            }
        }
        
        return "  "+numberoffiles+" Movies - "+filesize+" "+gibormib;
    }
}
