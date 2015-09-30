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

    /**
     * Fetches the posters and additional information from the API
     *
     * @return String-dummy
     * @throws Exception
     */
    @Override
    protected String doInBackground() throws Exception {
        try {
            ArrayList<TMDBMovie> matches = new ArrayList<>();

            for (Movie m : ml) {
                String movie = m.getName().replace(".", " ");
                matches = APItmdb.getInstance().doSearch(ConfigUtility.getInstance().getPropLang(), movie);
                if (m.getDBMatch() == false) {
                    if (matches.size() > 1) {
                        FetchedMoviesDLG dlg = new FetchedMoviesDLG(new JFrame(), true, matches, m.getName());
                        dlg.setVisible(true);
                        int index = dlg.getSelMatch();
                        URL poster = dlg.getPoster();

                        m.setMatch(true);
                        m.setName(matches.get(index).getTitle());

                        if (ConfigUtility.getInstance().isPropSavePosters()) {
                            savePoster(poster, matches.get(index));
                        }

                        this.setTMDBvars(m, matches.get(index));

                    } else if (matches.size() == 1) {
                        m.setMatch(true);
                        m.setName(matches.get(0).getTitle());

                        URL poster = APItmdb.getInstance().getPoster(matches.get(0).getPoster_url());
                        if (ConfigUtility.getInstance().isPropSavePosters()) {
                            savePoster(poster, matches.get(0));
                        }

                        this.setTMDBvars(m, matches.get(0));
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "BLA";
    }

    /**
     * Informs the user wheter it was saved successfully
     *
     */
    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, "Finished le Fetch", "Finished!", JOptionPane.INFORMATION_MESSAGE);
        mui.setList(ml, false);

        try {
            if (ConfigUtility.getInstance().isPropAutoSave()) {
                mui.safeMovies(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(FetchWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * saves the poster on the HardDrive
     *
     * @param poster The URL of the poster to be saved
     * @param tm The movie to which it belongs
     */
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

    private void setTMDBvars(Movie m, TMDBMovie tm) {
        m.setT_genres(tm.getGenres());
        m.setT_id(tm.getId());
        m.setT_original_title(tm.getOriginalTitle());
        m.setT_overview(tm.getOverview());
        m.setT_posterPathOnFilesystem(tm.getPosterPathOnFilesystem());
        m.setT_releaseDate(tm.getReleaseDate());
        m.setT_title(tm.getTitle());
        m.setT_voteAverage(tm.getVoteAverage());
        m.setT_voteCount(tm.getVoteCount());
    }
}
