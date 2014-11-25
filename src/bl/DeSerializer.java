package bl;

import beans.Movie;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import ui.MainUI;

public class DeSerializer {

    private MainUI mui;

    public DeSerializer(MainUI mui) {
        this.mui = mui;
    }

    public void deSerialize() {

        LinkedList<Movie> list = new LinkedList<Movie>();

        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MovieList File", "ml");

        fc.setFileFilter(filter);
        int ret = fc.showOpenDialog(new JFrame());

        if (ret == JFileChooser.APPROVE_OPTION) {
            FileInputStream inputFileStream = null;
            ObjectInputStream objectInputStream = null;

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
                    
                    mui.setList(list);
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
