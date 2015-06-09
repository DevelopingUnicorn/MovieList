package at.movielist.bl;

import at.movielist.beans.Movie;
import at.movielist.beans.TMDBMovie;
import at.movielist.tmdb.APItmdb;
import at.movielist.ui.FetchedMoviesDLG;
import at.movielist.ui.MainUI;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class FetchWorker extends SwingWorker<String, String> {

    private MainUI mui;
    private LinkedList<Movie> ml;

    public FetchWorker(MainUI mui, LinkedList<Movie> ml) {
        this.mui = mui;
        this.ml = ml;
    }

    @Override
    protected String doInBackground() throws Exception {

        try {
            ArrayList<TMDBMovie> matches = new ArrayList<TMDBMovie>();

            for (Movie m : ml) {
                matches = APItmdb.getInstance().doSearch(ConfigUtility.getInstance().getPropLang(), m.getName());

                if (matches.size() > 1) {
                    FetchedMoviesDLG dlg = new FetchedMoviesDLG(new JFrame(), true, matches, m.getName());
                    dlg.setVisible(true);
                    int index = dlg.getSelMatch();
                    URL poster = dlg.getPoster();
                    m.setMatch(matches.get(index));
                    m.setName(matches.get(index).getTitle());

                    if (ConfigUtility.getInstance().isPropSavePosters()) {
                        savePoster(poster, matches.get(index));
                    }

                } else if (matches.size() == 1) {
                    m.setMatch(matches.get(0));
                    m.setName(matches.get(0).getTitle());
                    URL poster = APItmdb.getInstance().getPoster(matches.get(0).getPoster_url());
                    if (ConfigUtility.getInstance().isPropSavePosters()) {
                        savePoster(poster, matches.get(0));
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "BLA";
    }

    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, "Finished le Fetch", "Finished!", JOptionPane.INFORMATION_MESSAGE);
        mui.setList(ml, false);
        mui.safeMovies(true);
    }

    private void savePoster(URL poster, TMDBMovie tm) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(poster.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            String ppofs = ConfigUtility.getInstance().getPathToImages() + File.separator + tm.getPoster_url().replace("/", "");
            tm.setPosterPathOnFilesystem(ppofs);

            FileOutputStream fos = new FileOutputStream(ppofs);
            fos.write(response);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(FetchWorker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FetchWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
