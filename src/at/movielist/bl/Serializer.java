package at.movielist.bl;

import at.movielist.beans.Movie;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Serializer {

    public Serializer() {
    }

    public void safeMovieList(LinkedList<Movie> list) {

        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MovieList File", "ml");

        fc.setFileFilter(filter);

        fc.setSelectedFile(new File("movielist.ml"));
        
        int ret = fc.showSaveDialog(new JFrame());

        if (ret == JFileChooser.APPROVE_OPTION) {
            FileOutputStream fileOut = null;
            try {
                File mlfile = fc.getSelectedFile();
                fileOut = new FileOutputStream(mlfile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                for (Movie m: list) {
                    out.writeObject(m);
                }

                out.close();
                fileOut.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
