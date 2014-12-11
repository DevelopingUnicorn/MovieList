package bl;

import beans.Movie;
import java.util.LinkedList;

public class UtilityClass {

    public String getSizeAndNumberOfFiles(LinkedList<Movie> list) {
        double filesize = 0.0;
        int numberoffiles = list.size();
        String gibormib = "GiB";

        for (Movie m : list) {
            String[] spl = m.getFilesize().split("\\s+");
            spl[0] = spl[0].replaceAll(",",".");
            
            if(spl[1].equals("MiB"))
            {
                filesize += (Double.parseDouble(spl[0])/1024.0);
            }else {
                filesize += Double.parseDouble(spl[0]);
            }
        }

        if (filesize < 1.0) {
            gibormib = "MiB";
            filesize *= 1024.0;
        }
       
        String ret = String.format("  %d Movies - %4.2f %s",numberoffiles, filesize, gibormib);
        ret = ret.replaceAll(",",".");
        
        return ret;
    }
}
