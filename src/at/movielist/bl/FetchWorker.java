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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class FetchWorker extends SwingWorker<String, String> {

    private MainUI mui;
    private LinkedList<Movie> ml, refetch;
    private JLabel lb;
    private JProgressBar loading;
    private ResourceBundle res;
    private boolean isRefetch = false;

    /**
     * Constructor
     *
     * @param mui
     * @param ml
     * @param d
     * @param res ResourceBundle
     */
    public FetchWorker(MainUI mui, LinkedList<Movie> ml, JProgressBar pb, JLabel lab, ResourceBundle res) {
        this.mui = mui;
        this.ml = ml;
        this.res = res;

        this.loading = pb;
        this.lb = lab;
    }

    /**
     * Constructor for refetched
     *
     * @param mui
     * @param ml
     * @param d
     * @param res ResourceBundle
     * @param refetch List with Movies that will be reFetched
     * @param isRefetch a boolean that says if it's a refetch
     */
    public FetchWorker(MainUI mui, LinkedList<Movie> ml, JProgressBar pb, JLabel lab, ResourceBundle res, LinkedList<Movie> refetch, boolean isRefetch) {
        this.mui = mui;
        this.ml = ml;
        this.res = res;
        this.refetch = refetch;

        this.loading = pb;
        this.lb = lab;

        this.isRefetch = isRefetch;
    }

    /**
     * Fetches the posters and additional information from the API
     *
     * @return String-dummy
     * @throws Exception
     */
    @Override
    protected String doInBackground() {
        loading.setVisible(true);
        try {
            ArrayList<TMDBMovie> matches = new ArrayList<>();

            int length = ml.size();
            double inc = 1000000 / length;

            for (Movie m : ml) {
                if (!isCancelled()) {
                    if (isRefetch) {
                        if (refetch.contains(m)) {
                            fetchMovie(m, matches, inc);
                        }
                    } else {
                        fetchMovie(m, matches, inc);
                    }
                } else {
                    break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        loading.setVisible(false);

        return "BLA";
    }

    /**
     * Informs the user wheter it was saved successfully
     *
     */
    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, res.getString("fetch_dlg_text"), res.getString("fetch_dlg_title"), JOptionPane.INFORMATION_MESSAGE);
        mui.setList(ml, false);
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

    /**
     * Sets the tmdb variables into Movie Object
     *
     * @param m current Movie
     * @param tm TMDBMovie Object
     */
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
        m.setT_releaseYear(tm.getReleaseYear());
    }

    /**
     * Generates the TMDBMovie Object and fetchs data
     *
     * @param m current Movie
     * @param matches the matches from tMDb
     * @param inc incrment of the progress bar
     * @throws IOException
     * @throws Exception
     */
    private void fetchMovie(Movie m, ArrayList<TMDBMovie> matches, double inc) throws IOException, Exception {
        String movie = m.getName().replace(".", " ");
        matches = APItmdb.getInstance().doSearch(ConfigUtility.getInstance().getPropLang(), movie);
        if (m.getDBMatch() == false) {
            if (matches.size() > 1) {
                FetchedMoviesDLG dlg = new FetchedMoviesDLG(new JFrame(), true, matches, m.getName());
                dlg.setVisible(true);
                int index = dlg.getSelMatch();
                URL poster = dlg.getPoster();

                createMatch(matches, index, poster, m, inc);

            } else if (matches.size() == 1) {
                URL poster = APItmdb.getInstance().getPoster(matches.get(0).getPoster_url());
                createMatch(matches, 0, poster, m, inc);
            } else {
                loading.setValue(loading.getValue() + (int) inc);
            }
        }
    }

    private void createMatch(ArrayList<TMDBMovie> matches, int index, URL poster, Movie m, double inc) throws IOException {
        System.out.println(m.getFilePath());

        m.setMatch(true);
        m.setName(matches.get(index).getTitle());

        if (ConfigUtility.getInstance().isPropSavePosters()) {
            savePoster(poster, matches.get(index));
        }

        this.setTMDBvars(m, matches.get(index));

        loading.setValue(loading.getValue() + (int) inc);
    }
}
