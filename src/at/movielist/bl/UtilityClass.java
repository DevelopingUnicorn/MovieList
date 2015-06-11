package at.movielist.bl;

import at.movielist.beans.Movie;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class UtilityClass {

    /**
     * Returns the size as String as well as the number of files from the movies
     *
     * @param list An array of movies to compute the sizes and num of files
     * @param loc Which language should be used to display the Size-Format
     * @return
     */
    public String getSizeAndNumberOfFiles(LinkedList<Movie> list, Locale loc) {

        ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", loc);

        double filesize = 0.0;
        int numberoffiles = list.size();
        String gibormib = "GiB";

        for (Movie m : list) {
            String[] spl = m.getFilesize().split("\\s+");
            spl[0] = spl[0].replaceAll(",", ".");

            if (spl[1].equals("MiB")) {
                filesize += (Double.parseDouble(spl[0]) / 1024.0);
            } else {
                filesize += Double.parseDouble(spl[0]);
            }
        }

        if (filesize < 1.0) {
            gibormib = "MiB";
            filesize *= 1024.0;
        }

        String ret = String.format("  %d %s - %4.2f %s", numberoffiles, resBundle.getString("main_bottom_info"), filesize, gibormib);
        ret = ret.replaceAll(",", ".");

        return ret;
    }
}
