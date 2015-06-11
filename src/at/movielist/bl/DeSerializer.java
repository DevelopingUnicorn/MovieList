package at.movielist.bl;

import at.movielist.beans.Movie;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import at.movielist.ui.MainUI;

public class DeSerializer {

    private final MainUI mui;

    public DeSerializer(MainUI mui) {
        this.mui = mui;
    }

    /**
     * Loads the movie-array from the file to RAM
     */
    public void deSerialize() {
        LinkedList<Movie> list = new LinkedList<>();

        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MovieList File", "ml");

        fc.setFileFilter(filter);
        int ret = fc.showOpenDialog(new JFrame());

        if (ret == JFileChooser.APPROVE_OPTION) {
            FileInputStream inputFileStream;
            ObjectInputStream objectInputStream;

            try {
                inputFileStream = new FileInputStream(fc.getSelectedFile());
                objectInputStream = new ObjectInputStream(inputFileStream);

                try {
                    while (true) {
                        Movie deserMovie = (Movie) objectInputStream.readObject();
                        list.add(deserMovie);
                    }
                } catch (OptionalDataException e) {
                    if (!e.eof) {
                        throw e;
                    }
                } finally {
                    objectInputStream.close();
                    inputFileStream.close();

                    mui.setList(list, false);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("File Not Found!");
            } catch (IOException ex) {
                //System.err.println("IO EXception"); is Expected
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not Found!");
            }
        }
    }
}
