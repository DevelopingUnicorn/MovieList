package at.movielist.ui;

import at.movielist.beans.Movie;
import at.movielist.bl.ConfigUtility;
import at.movielist.bl.DeSerializer;
import at.movielist.bl.FetchWorker;
import at.movielist.bl.MovieCompare;
import at.movielist.bl.MovieListModel;
import at.movielist.bl.MovieLoader;
import at.movielist.bl.Serializer;
import at.movielist.bl.UtilityClass;
import static at.movielist.ui.SetupDLG.DEFAULT_FONT;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainUI extends javax.swing.JFrame {

    private final MovieListModel mlm = new MovieListModel();
    private MovieLoader ml;
    private String[] pathsToMovies;
    private LinkedList<Movie> movielist = new LinkedList<>();
    private final UtilityClass utilityclass = new UtilityClass();
    private final LinkedList<Image> iconlist = new LinkedList<>();
    private JMenuItem mi;
    private JPanel renameinput = new JPanel();
    private JTextField filename = new JTextField();
    private JTextField foldername = new JTextField();
    private JPanel p1, p2;

    private ResourceBundle resBundle;

    /**
     * Constructor
     */
    public MainUI() {
        initComponents();

        UIManager.put("Editorpane.font", new Font("Arial", Font.PLAIN, 14));
        
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

        JPopupMenu pm = new JPopupMenu();
        mi = new JMenuItem(resBundle.getString("popup_menu_fetch"));
        pm.add(mi);

        setListener();

        liMovies.setComponentPopupMenu(pm);

        //Reneame field
        BorderLayout b = new BorderLayout();
        GridLayout g = new GridLayout(2, 1);

        p1 = new JPanel();
        p1.setLayout(g);

        p2 = new JPanel();
        p2.setLayout(g);

        renameinput = new JPanel();
        renameinput.setLayout(b);
        //END

        pbLoading.setStringPainted(true);
        pbLoading.setVisible(false);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = new javax.swing.JPanel();
        pnBottom = new javax.swing.JPanel();
        lbThings = new javax.swing.JLabel();
        pbLoading = new javax.swing.JProgressBar();
        lbXOY = new javax.swing.JLabel();
        pnSearchbar = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        btFilter = new javax.swing.JButton();
        pnListe = new javax.swing.JPanel();
        spList = new javax.swing.JScrollPane();
        liMovies = new javax.swing.JList();
        pnSort = new javax.swing.JPanel();
        cbSort = new javax.swing.JComboBox();
        lbSort = new javax.swing.JLabel();
        pnRight = new javax.swing.JPanel();
        spEDitor = new javax.swing.JScrollPane();
        epInfos = new javax.swing.JEditorPane();
        mbBar = new javax.swing.JMenuBar();
        meFile = new javax.swing.JMenu();
        miLoad = new javax.swing.JMenuItem();
        miOpen = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        spSepp = new javax.swing.JPopupMenu.Separator();
        miFetchFromTMDB = new javax.swing.JMenuItem();
        meSettings = new javax.swing.JMenu();
        miPreferences = new javax.swing.JMenuItem();
        miProxy = new javax.swing.JMenuItem();
        meAbout = new javax.swing.JMenu();
        miCredits = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        pnLeft.setLayout(new java.awt.BorderLayout());

        pnBottom.setLayout(new java.awt.BorderLayout());

        lbThings.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbThings.setForeground(new java.awt.Color(0, 123, 123));
        pnBottom.add(lbThings, java.awt.BorderLayout.LINE_START);

        pbLoading.setMaximum(1000000);
        pnBottom.add(pbLoading, java.awt.BorderLayout.CENTER);
        pnBottom.add(lbXOY, java.awt.BorderLayout.LINE_END);

        pnLeft.add(pnBottom, java.awt.BorderLayout.SOUTH);

        pnSearchbar.setLayout(new java.awt.BorderLayout());
        pnSearchbar.add(tfSearch, java.awt.BorderLayout.CENTER);

        lbSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSearch.setText("Search:");
        pnSearchbar.add(lbSearch, java.awt.BorderLayout.LINE_START);

        btFilter.setText("Advanced");
        btFilter.setToolTipText("");
        btFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAdvancedSearch(evt);
            }
        });
        pnSearchbar.add(btFilter, java.awt.BorderLayout.LINE_END);

        pnLeft.add(pnSearchbar, java.awt.BorderLayout.NORTH);

        pnListe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnListe.setLayout(new java.awt.BorderLayout());

        liMovies.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spList.setViewportView(liMovies);

        pnListe.add(spList, java.awt.BorderLayout.CENTER);

        pnSort.setLayout(new java.awt.BorderLayout());

        cbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        pnSort.add(cbSort, java.awt.BorderLayout.CENTER);

        lbSort.setText("Sort:");
        pnSort.add(lbSort, java.awt.BorderLayout.LINE_START);

        pnListe.add(pnSort, java.awt.BorderLayout.PAGE_START);

        pnLeft.add(pnListe, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnLeft);

        pnRight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnRight.setLayout(new java.awt.BorderLayout());

        epInfos.setEditable(false);
        epInfos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spEDitor.setViewportView(epInfos);

        pnRight.add(spEDitor, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnRight);

        mbBar.setBackground(new java.awt.Color(255, 255, 255));

        meFile.setText("File");
        meFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        miLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/loadmovies.png"))); // NOI18N
        miLoad.setText("Load Movies");
        miLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadMovies(evt);
            }
        });
        meFile.add(miLoad);

        miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/fileopen.png"))); // NOI18N
        miOpen.setText("Open MovieList File");
        miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOpenMLFile(evt);
            }
        });
        meFile.add(miOpen);

        miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/save.png"))); // NOI18N
        miSave.setText("Save to MovieList File");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSave(evt);
            }
        });
        meFile.add(miSave);
        meFile.add(spSepp);

        miFetchFromTMDB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        miFetchFromTMDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/fetch.png"))); // NOI18N
        miFetchFromTMDB.setText("Fetch From tMDb");
        miFetchFromTMDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFetch(evt);
            }
        });
        meFile.add(miFetchFromTMDB);

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

        miProxy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/setproxy.png"))); // NOI18N
        miProxy.setText("Set Proxy");
        miProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProxyonSettings(evt);
            }
        });
        meSettings.add(miProxy);

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

        jMenuItem1.setText("Report Bug");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        meAbout.add(jMenuItem1);

        mbBar.add(meAbout);

        setJMenuBar(mbBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Load the movies from the specified paths. A progresbar will be displayed
     * to indicate the progress.
     *
     * @param evt
     */
    private void onLoadMovies(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadMovies
        //ProgressbarDLG pd = new ProgressbarDLG(this, false);
        ml = new MovieLoader(pathsToMovies, pbLoading, lbXOY, resBundle.getLocale());
        loadMovies();
        pbLoading.setValue(0);
    }//GEN-LAST:event_onLoadMovies
    /**
     * Opens a JFileChooser to save the loaded movies and information to an .ml
     * file
     *
     * @param evt
     */
    private void onSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSave
        saveMovies(false);
    }//GEN-LAST:event_onSave
    /**
     * Opens the .ml and load the information
     *
     * @param evt
     */
    private void onOpenMLFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOpenMLFile
        DeSerializer ds = new DeSerializer(this);
        ds.deSerialize();

        if (movielist.size() > 0) {
            String things = utilityclass.getSizeAndNumberOfFiles(movielist, resBundle.getLocale());
            this.lbThings.setText(things);
        }
    }//GEN-LAST:event_onOpenMLFile
    /**
     * Shows the settings dialog
     *
     * @param evt
     */
    private void onSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSettings
        try {
            SettingsDLG sdlg = new SettingsDLG(this, true);
            sdlg.setVisible(true);

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
    /**
     * Shows the settings
     *
     * @param evt
     */
    private void onCredits(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCredits
        new CreditsDLG(this, true, resBundle.getLocale());
    }//GEN-LAST:event_onCredits

    /**
     * Openes the dialog for proxy settings
     *
     * @param evt
     */
    private void miProxyonSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProxyonSettings
        ProxySettingsDLG dlg = new ProxySettingsDLG(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_miProxyonSettings
    /**
     * Fetches additional information from the API (tMDb)
     *
     * @param evt
     */
    private void onFetch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFetch
        FetchWorker fw = new FetchWorker(this, movielist, pbLoading, lbXOY, resBundle);
        fw.execute();
        pbLoading.setValue(0);
    }//GEN-LAST:event_onFetch

    /**
     * Opens the Advanced SettingsDLG
     *
     * @param evt
     */
    private void onAdvancedSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAdvancedSearch
        AdvancedSearchDLG ad = new AdvancedSearchDLG(this, false, resBundle);
        ad.setVisible(true);
    }//GEN-LAST:event_onAdvancedSearch

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("https://github.com/DevelopingUnicorn/MovieList/issues/new"));
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFilter;
    private javax.swing.JComboBox cbSort;
    private javax.swing.JEditorPane epInfos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbSort;
    private javax.swing.JLabel lbThings;
    private javax.swing.JLabel lbXOY;
    private javax.swing.JList liMovies;
    private javax.swing.JMenuBar mbBar;
    private javax.swing.JMenu meAbout;
    private javax.swing.JMenu meFile;
    private javax.swing.JMenu meSettings;
    private javax.swing.JMenuItem miCredits;
    private javax.swing.JMenuItem miFetchFromTMDB;
    private javax.swing.JMenuItem miLoad;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miPreferences;
    private javax.swing.JMenuItem miProxy;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JProgressBar pbLoading;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnListe;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnSearchbar;
    private javax.swing.JPanel pnSort;
    private javax.swing.JScrollPane spEDitor;
    private javax.swing.JScrollPane spList;
    private javax.swing.JPopupMenu.Separator spSepp;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

    /**
     * Displays information in the EditorPane. It displays information about the
     * movie (local) and from the tMDb. If no online data has been saved yet, it
     * will be fetched.
     *
     * @param selectedIndex
     */
    private void printInformation(int selectedIndex) {
        try {
            Movie m = movielist.get(selectedIndex);
            Locale current = resBundle.getLocale();
            m.setResBundle(current);

            String html = m.toHTMLString();
            html += m.getMatch();

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

    /**
     * Loads the movies.
     */
    private void loadMovies() {
        ml.getMovies(this);
    }

    /**
     * Updates the list of movies
     *
     * @param liste The list to be displayed
     * @param doAskForOverride true, if an confirm dialog should appear and ask
     * before overriding
     */
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

            printInformation(0);
            liMovies.setSelectedIndex(0);
        } else {
            movielist = liste;
        }

        Collections.sort(movielist, new MovieCompare(this.getSorting()));

        mlm.setList(movielist);
        liMovies.updateUI();

        try {
            if (ConfigUtility.getInstance().isPropAutoSave()) {
                saveMovies(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(FetchWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Clears the list with the movies
     */
    public void clearList() {
        mlm.clear();
        lbThings.setText("");
    }

    /**
     * removes the selected movie from the list
     */
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

            if (toDel[toDel.length - 1] == movielist.size()) {
                liMovies.setSelectedIndex(movielist.size() - 1);
            }

            printInformation(liMovies.getSelectedIndex());

        } else if (movielist.size() == 0) {
            this.lbThings.setText("");
            this.epInfos.setText("");
        }
    }

    /**
     * Gets the label where additional information is displayed
     *
     * @return the JLabel
     */
    public JLabel getLbThings() {
        return this.lbThings;
    }

    /**
     * Updates all UI elements with the new text from the language set
     *
     * @throws IOException
     */
    public void setLang() throws IOException {
        // Lang support
        Locale currentLocal = Locale.ENGLISH;

        switch (ConfigUtility.getInstance().getPropLang()) {
            case "de":
                currentLocal = Locale.GERMAN;
                break;
            case "es":
                currentLocal = new Locale("es");
                break;
        }

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
        miProxy.setText(resBundle.getString("main_menu_settings_proxy"));
        miFetchFromTMDB.setText(resBundle.getString("main_menu_file_fetch"));
        lbSort.setText(resBundle.getString("main_movie_sort_lable"));
        lbSearch.setText(resBundle.getString("main_search_lable"));
        btFilter.setText(resBundle.getString("main_button_filter"));

        cbSort.removeAllItems();
        cbSort.addItem(resBundle.getString("main_movie_sort_atz"));
        cbSort.addItem(resBundle.getString("main_movie_sort_zta"));
        cbSort.addItem(resBundle.getString("main_movie_sort_voteup"));
        cbSort.addItem(resBundle.getString("main_movie_sort_votedown"));
        cbSort.addItem(resBundle.getString("main_movie_sort_release"));
        // END lang support

    }

    /**
     * sets all the listeners needed
     */
    private void setListener() {
        liMovies.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (movielist.size() > 0) {
                    renameMovie(liMovies.getSelectedIndex());
                }
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
                    if (movielist.size() > 0) {
                        int index = list.locationToIndex(evt.getPoint());
                        renameMovie(index);
                    }

                }
            }
        });

        tfSearch.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent ce) {
                searchMovie();
            }
        });

        mi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] indis = liMovies.getSelectedIndices();

                LinkedList<Movie> refetch = new LinkedList<>();

                for (int i = 0; i < indis.length; i++) {
                    Movie m = movielist.get(indis[i]);
                    m.setMatch(false);
                    refetch.add(m);
                }

                FetchWorker fw = new FetchWorker(MainUI.this, movielist, pbLoading, lbXOY, resBundle, refetch, true);
                fw.execute();
            }
        }
        );

        cbSort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (movielist.size() > 0) {
                    Collections.sort(movielist, new MovieCompare(getSorting()));
                    setList(movielist, false);
                }
            }
        });
    }

    /**
     * Opens an InputDialog to input the new name for the selected movie
     *
     * @param index The selected Movie
     */
    public void renameMovie(int index) {
        //String ren = JOptionPane.showInputDialog(resBundle.getString("main_option_rename"), ((Movie) mlm.getElementAt(index)).getName());
        Movie m = (Movie) mlm.getElementAt(index);
        File mov = new File(m.getFilePath());
        File folder = new File(m.getPath());

        filename.setText(mov.getName());
        foldername.setText(m.getName());

        renameinput.removeAll();
        p1.removeAll();
        p2.removeAll();

        String oldFin = filename.getText();
        String oldFon = foldername.getText();

        if (!oldFon.equals(oldFin)) {
            p1.add(new JLabel(resBundle.getString("main_information_filename") + ":"));
            p2.add(filename);
            p1.add(new JLabel(resBundle.getString("main_information_path") + ":"));
            p2.add(foldername);

            renameinput.add(p1, BorderLayout.WEST);
            renameinput.add(p2, BorderLayout.CENTER);
        } else {
            p1.add(new JLabel(resBundle.getString("main_information_filename") + ":"));
            p2.add(filename);

            renameinput.add(p1, BorderLayout.WEST);
            renameinput.add(p2, BorderLayout.CENTER);
        }

        int result = JOptionPane.showConfirmDialog(null, renameinput,
                resBundle.getString("main_option_rename"), JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String fin = filename.getText();
            String fon = foldername.getText();

            if (!fin.equals("")) {
                if (!fin.equals(mov.getName())) {
                    File f = new File(m.getPath() + File.separator + fin);

                    mov.renameTo(f);
                    m.setFilePath(f.getAbsolutePath());
                }
            }

            if (!fon.equals("")) {
                if (!fon.equals(m.getName())) {
                    File f = new File(new File(m.getPath()).getParent() + File.separator + fon);

                    folder.renameTo(f);
                    m.setPath(f.getAbsolutePath());
                    m.setName(f.getName());
                }
            }
            mlm.setList(movielist);
            printInformation(liMovies.getSelectedIndex());
        }
    }

    /**
     * Saves the movies to an .ml file
     *
     * @param isAutoSafe
     */
    public void saveMovies(boolean isAutoSafe) {
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

    /**
     * performs an search inside the list with the movies
     */
    private void searchMovie() {
        String searchstring = tfSearch.getText().toLowerCase();
        if (!searchstring.equals("")) {
            LinkedList<Movie> searchList = new LinkedList<>();

            for (int i = 0; i < movielist.size(); i++) {
                Movie m = (Movie) movielist.get(i);
                if (m.getName().toLowerCase().contains(searchstring)) {
                    if (!searchList.contains(m)) {
                        searchList.add(m);
                    }
                }
            }

            Collections.sort(searchList, new MovieCompare(this.getSorting()));
            mlm.setList(searchList);
            liMovies.updateUI();
        } else {
            Collections.sort(movielist, new MovieCompare(this.getSorting()));
            mlm.setList(movielist);
            liMovies.updateUI();
        }
    }

    public void AdvancedSearch(String genre) {
        LinkedList<Movie> searchList = new LinkedList<>();

        if (!genre.equals(resBundle.getString("genre_all"))) {
            for (Movie m : movielist) {
                LinkedList<String> genres = m.getT_genres();

                if (genres != null) {
                    if (genres.size() > 0) {
                        if (genres.contains(genre)) {
                            searchList.add(m);
                        }
                    }
                }
            }

            if (searchList.size() > 0) {
                Collections.sort(searchList, new MovieCompare(this.getSorting()));
                mlm.setList(searchList);
                liMovies.updateUI();
            }
        } else {
            Collections.sort(movielist, new MovieCompare(this.getSorting()));
            mlm.setList(movielist);
            liMovies.updateUI();
        }
    }

    /**
     * Filters the Movies based on the AdvancedSearch DLG output
     */
    public void filterMovies() {

    }

    /**
     * Returns how to sort the Movielist
     *
     * @return
     */
    public int getSorting() {
        if (cbSort.getItemCount() > 0) {
            String s = cbSort.getSelectedItem().toString();

            if (s.equals(resBundle.getString("main_movie_sort_atz"))) {
                return 0;
            } else if (s.equals(resBundle.getString("main_movie_sort_zta"))) {
                return 1;
            } else if (s.equals(resBundle.getString("main_movie_sort_voteup"))) {
                return 2;
            } else if (s.equals(resBundle.getString("main_movie_sort_votedown"))) {
                return 3;
            } else if (s.equals(resBundle.getString("main_movie_sort_release"))) {
                return 4;
            }
        }

        return 0;
    }
}
