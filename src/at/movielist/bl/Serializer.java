package at.movielist.bl;

import at.movielist.beans.Movie;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Serializer {

    /**
     * Constructor
     */
    public Serializer() {
    }

    /**
     * Serializes the movie-array and saves it
     *
     * @param list The movies
     * @param isAutoSafe If it is an autosave --> not asking where to save it to
     * @throws IOException
     */
    public void safeMovieList(LinkedList<Movie> list, boolean isAutoSafe) throws IOException {
        File mlfile = null;
        if (!isAutoSafe) {
            JFileChooser fc = new JFileChooser();
            fc.setPreferredSize(new Dimension(700, 500));

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "MovieList File", "ml");
            fc.setFileFilter(filter);

            fc.setSelectedFile(new File("movielist.ml"));

            int ret = fc.showSaveDialog(new JFrame());

            if (ret == JFileChooser.APPROVE_OPTION) {
                mlfile = fc.getSelectedFile();
            }
        } else {
            mlfile = new File(ConfigUtility.getInstance().getUserdocs() + File.separator + "autosave.ml");
        }

        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(mlfile);
            ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);

            for (Movie m : list) {
                objOutStream.writeObject(m);

            }
            objOutStream.flush();
            objOutStream.close();
            outStream.close();
        } finally {
            outStream.close();
        }
    }
}
