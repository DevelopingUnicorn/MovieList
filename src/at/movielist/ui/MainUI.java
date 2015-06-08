package at.movielist.ui;

import at.movielist.beans.Movie;
import at.movielist.beans.TMDBMovie;
import at.movielist.bl.ConfigUtility;
import at.movielist.bl.DeSerializer;
import at.movielist.bl.MovieCompare;
import at.movielist.bl.MovieListModel;
import at.movielist.bl.MovieLoader;
import at.movielist.bl.Serializer;
import at.movielist.bl.UtilityClass;
import at.movielist.dal.APItmdb;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainUI extends javax.swing.JFrame {

    private final MovieListModel mlm = new MovieListModel();
    private MovieLoader ml;
    private String[] pathsToMovies;
    private LinkedList<Movie> movielist = new LinkedList<>();
    private final UtilityClass utilityclass = new UtilityClass();
    private final LinkedList<Image> iconlist = new LinkedList<>();

    private ResourceBundle resBundle;

    public MainUI() {
        initComponents();

        try {
            ConfigUtility.getInstance().loadConfig();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);

        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.small.png")).getImage());
        this.setIconImages(iconlist);

        epInfos.setContentType("text/html");
        liMovies.setModel(mlm);

        try {
            pathsToMovies = ConfigUtility.getInstance().getPropPaths();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            setLang();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        setListener();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = new javax.swing.JPanel();
        lbThings = new javax.swing.JLabel();
        pnSearchbar = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        pnListe = new javax.swing.JPanel();
        spList = new javax.swing.JScrollPane();
        liMovies = new javax.swing.JList();
        pnRight = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        epInfos = new javax.swing.JEditorPane();
        mbBar = new javax.swing.JMenuBar();
        meFile = new javax.swing.JMenu();
        miOpen = new javax.swing.JMenuItem();
        miLoad = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        meSettings = new javax.swing.JMenu();
        miPreferences = new javax.swing.JMenuItem();
        meAbout = new javax.swing.JMenu();
        miCredits = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        pnLeft.setLayout(new java.awt.BorderLayout());

        lbThings.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbThings.setForeground(new java.awt.Color(0, 123, 123));
        pnLeft.add(lbThings, java.awt.BorderLayout.PAGE_END);

        pnSearchbar.setLayout(new java.awt.BorderLayout());
        pnSearchbar.add(tfSearch, java.awt.BorderLayout.CENTER);

        btSearch.setText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });
        pnSearchbar.add(btSearch, java.awt.BorderLayout.EAST);

        pnLeft.add(pnSearchbar, java.awt.BorderLayout.NORTH);

        pnListe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnListe.setLayout(new java.awt.BorderLayout());

        liMovies.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spList.setViewportView(liMovies);

        pnListe.add(spList, java.awt.BorderLayout.CENTER);

        pnLeft.add(pnListe, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnLeft);

        pnRight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnRight.setLayout(new java.awt.BorderLayout());

        epInfos.setEditable(false);
        jScrollPane1.setViewportView(epInfos);

        pnRight.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnRight);

        mbBar.setBackground(new java.awt.Color(255, 255, 255));

        meFile.setText("File");
        meFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/fileopen.png"))); // NOI18N
        miOpen.setText("Open MovieList File");
        miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOpenMLFile(evt);
            }
        });
        meFile.add(miOpen);

        miLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        miLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/loadmovies.png"))); // NOI18N
        miLoad.setText("Load Movies");
        miLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadMovies(evt);
            }
        });
        meFile.add(miLoad);

        miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/save.png"))); // NOI18N
        miSave.setText("Save to MovieList File");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSave(evt);
            }
        });
        meFile.add(miSave);

        mbBar.add(meFile);

        meSettings.setText("Settings");
        meSettings.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miPreferences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/settings.png"))); // NOI18N
        miPreferences.setText("Preferences");
        miPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSettings(evt);
            }
        });
        meSettings.add(miPreferences);

        mbBar.add(meSettings);

        meAbout.setText("About");
        meAbout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miCredits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/about.png"))); // NOI18N
        miCredits.setText("Credits");
        miCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCredits(evt);
            }
        });
        meAbout.add(miCredits);

        mbBar.add(meAbout);

        setJMenuBar(mbBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onLoadMovies(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadMovies
        ProgressbarDLG pd = new ProgressbarDLG(this, false);
        ml = new MovieLoader(pathsToMovies, pd, resBundle.getLocale());
        loadMovies();
    }//GEN-LAST:event_onLoadMovies

    private void onSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSave
        safeMovies(false);
    }//GEN-LAST:event_onSave

    private void onOpenMLFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOpenMLFile
        DeSerializer ds = new DeSerializer(this);
        ds.deSerialize();

        if (movielist.size() > 0) {
            String things = utilityclass.getSizeAndNumberOfFiles(movielist, resBundle.getLocale());
            this.lbThings.setText(things);
        }
    }//GEN-LAST:event_onOpenMLFile

    private void onSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSettings
        try {
            SettingsDLG sdlg = new SettingsDLG(this, true);

            ConfigUtility.getInstance().loadConfig();
            setLang();
            pathsToMovies = ConfigUtility.getInstance().getPropPaths();

            if (movielist.size() > 0) {
                String things = utilityclass.getSizeAndNumberOfFiles(movielist, resBundle.getLocale());
                this.lbThings.setText(things);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onSettings

    private void onCredits(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCredits
        new CreditsDLG(this, true, resBundle.getLocale());
        this.setVisible(true);
    }//GEN-LAST:event_onCredits

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        doSearch();
    }//GEN-LAST:event_btSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSearch;
    private javax.swing.JEditorPane epInfos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbThings;
    private javax.swing.JList liMovies;
    private javax.swing.JMenuBar mbBar;
    private javax.swing.JMenu meAbout;
    private javax.swing.JMenu meFile;
    private javax.swing.JMenu meSettings;
    private javax.swing.JMenuItem miCredits;
    private javax.swing.JMenuItem miLoad;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miPreferences;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnListe;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnSearchbar;
    private javax.swing.JScrollPane spList;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

    private void printInformation(int selectedIndex) {
        try {
            Movie m = movielist.get(selectedIndex);
            Locale current = resBundle.getLocale();
            m.setResBundle(current);

            String html = m.toHTMLString();

            APItmdb.getInstance().setProxyUse();
            ArrayList<TMDBMovie> list = APItmdb.getInstance().doSearch(ConfigUtility.getInstance().getPropLang(), m.getName());
            if (!list.isEmpty()) {
                html += list.get(0).toHTMLString();
                System.out.println(list.get(0).toHTMLString());
            }

            epInfos.setText(html);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occured when fetching the TMDB data", "Error", JOptionPane.ERROR_MESSAGE);
            Movie m = movielist.get(selectedIndex);
            Locale current = resBundle.getLocale();
            m.setResBundle(current);
            epInfos.setText(m.toHTMLString());
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMovies() {
        ml.getMovies(this);
    }

    public void setList(LinkedList<Movie> liste, boolean doAskForOverride) {
        if (movielist.size() > 0) {
            int reply = JOptionPane.YES_OPTION;
            if (doAskForOverride) {
                reply = JOptionPane.showConfirmDialog(this, resBundle.getString("main_optionpane_clearlist_message"), resBundle.getString("main_optionpane_clearlist_title"), JOptionPane.YES_NO_OPTION);
            }

            if (reply == JOptionPane.YES_OPTION) {
                movielist = liste;
            } else {
                movielist.addAll(liste);
            }
        } else {
            movielist = liste;
        }

        Collections.sort(movielist, new MovieCompare());

        System.out.println("" + movielist.size());

        mlm.setList(movielist);
        liMovies.updateUI();
    }

    public JLabel getLbThings() {
        return this.lbThings;
    }

    public void clearList() {
        mlm.clear();
        lbThings.setText("");
    }

    public void setLang() throws IOException {
        // Lang support
        Locale currentLocal = Locale.ENGLISH;

        System.out.println(ConfigUtility.getInstance().getPropLang());

        switch (ConfigUtility.getInstance().getPropLang()) {
            case "de":
                currentLocal = Locale.GERMAN;
                break;
            case "es":
                currentLocal = new Locale("es");
                break;
        }

        System.out.println(currentLocal.toString());

        resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", currentLocal);

        this.setTitle("MovieList - Forever Watching | " + resBundle.getString("version"));

        meFile.setText(resBundle.getString("main_menu_file"));
        miOpen.setText(resBundle.getString("main_menu_file_open"));
        miLoad.setText(resBundle.getString("main_menu_file_load"));
        miSave.setText(resBundle.getString("main_menu_file_save"));
        meSettings.setText(resBundle.getString("main_menu_settings"));
        miPreferences.setText(resBundle.getString("main_menu_settings_preferences"));
        meAbout.setText(resBundle.getString("main_menu_about"));
        miCredits.setText(resBundle.getString("main_menu_about_credits"));
        pnListe.setBorder(BorderFactory.createTitledBorder(resBundle.getString("main_left_titel")));
        pnRight.setBorder(BorderFactory.createTitledBorder(resBundle.getString("main_right_titel")));
        // END lang support

    }

    public void removeListEntry() {
        int[] toDel = liMovies.getSelectedIndices();
        int minus = 0;

        for (int i : toDel) {
            if (movielist.size() != 0) {
                movielist.remove(i - minus);
                minus++;
            }
        }

        mlm.setList(movielist);
        liMovies.updateUI();

        if (movielist.size() > 0) {
            String things = utilityclass.getSizeAndNumberOfFiles(movielist, resBundle.getLocale());
            this.lbThings.setText(things);
        } else if (movielist.size() == 0) {
            this.lbThings.setText("");
            this.epInfos.setText("");
        }
    }

    private void setListener() {
        liMovies.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renameMovie(liMovies.getSelectedIndex());
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), JComponent.WHEN_FOCUSED);

        liMovies.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeListEntry();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), JComponent.WHEN_FOCUSED);

        liMovies.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    printInformation(liMovies.getSelectedIndex());
                }
            }
        });

        liMovies.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    renameMovie(index);
                }
            }
        });

        tfSearch.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSearch();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
    }

    public void renameMovie(int index) {
        String ren = JOptionPane.showInputDialog(resBundle.getString("main_option_rename"), ((Movie) mlm.getElementAt(index)).getName());
        if (!(ren == null)) {
            if (!ren.equals("")) {
                ((Movie) mlm.getElementAt(index)).setName(ren);
                liMovies.updateUI();
                printInformation(index);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), resBundle.getString("main_rename_Empty"), resBundle.getString("main_rename_EmptyTitle"), 0);
            }
        }
    }

    public void safeMovies(boolean isAutoSafe) {
        Serializer s = new Serializer();

        if (movielist.size() > 0) {
            try {
                s.safeMovieList(movielist, isAutoSafe);

            } catch (IOException ex) {
                Logger.getLogger(MainUI.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, resBundle.getString("main_save_noMovies"), resBundle.getString("main_save_noMoviesTitle"), 0);
        }
    }

    private void doSearch() {
        String searchstring = tfSearch.getText();
        if (!searchstring.equals("")) {
            String[] searchArguments = searchstring.split(" ");

            LinkedList<Movie> searchList = new LinkedList<>();
            for (int i = 0; i < mlm.getSize(); i++) {
                for (String searchArg : searchArguments) {
                    Movie m = (Movie) mlm.getElementAt(i);
                    if (m.getName().contains(searchArg)) {
                        System.out.println("Contains:\t" + m.getName());
                        searchList.add(m);
                    }
                }
            }

            Collections.sort(searchList, new MovieCompare());
            mlm.setList(searchList);
            liMovies.updateUI();
        } else {
            Collections.sort(movielist, new MovieCompare());
            mlm.setList(movielist);
            liMovies.updateUI();
        }
    }
}
