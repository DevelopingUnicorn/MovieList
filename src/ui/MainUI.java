package ui;

import beans.Movie;
import bl.ConfigUtility;
import bl.DeSerializer;
import bl.MovieCompare;
import bl.MovieListModel;
import bl.MovieLoader;
import bl.Serializer;
import bl.UtilityClass;
import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainUI extends javax.swing.JFrame {

    private final MovieListModel mlm = new MovieListModel();
    private MovieLoader ml;
    private final String userdocs, pathtomovies;
    private ConfigUtility cu;
    private LinkedList<Movie> movielist = new LinkedList<>();
    private final UtilityClass uc = new UtilityClass();
    private final LinkedList<Image> iconlist = new LinkedList<>();

    private final String pathtoconf;

    private ResourceBundle resBundle;

    public MainUI(String ud) {
        initComponents();

        userdocs = ud;
        pathtoconf = userdocs + "\\movielist.conf";

        cu = new ConfigUtility(pathtoconf);

        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("MovieList - Forever Watching (ALPHA v1.1a)");

        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.small.png")).getImage());

        this.setIconImages(iconlist);

        epInfos.setContentType("text/html");

        liMovies.setModel(mlm);

        cu.getConfig();
        pathtomovies = cu.getPath();
        
        resource();
        
        liMovies.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    printInformation(liMovies.getSelectedIndex());
                }
            }
        });
    }

    private void printInformation(int selectedIndex) {
        Movie m = movielist.get(selectedIndex);
        epInfos.setText(m.toHTMLString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = new javax.swing.JPanel();
        lbThings = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        pnLeft.setLayout(new java.awt.BorderLayout());

        lbThings.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbThings.setForeground(new java.awt.Color(0, 123, 123));
        pnLeft.add(lbThings, java.awt.BorderLayout.PAGE_END);

        pnListe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnListe.setLayout(new java.awt.BorderLayout());

        liMovies.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spList.setViewportView(liMovies);

        pnListe.add(spList, java.awt.BorderLayout.CENTER);

        pnLeft.add(pnListe, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnLeft);

        pnRight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnRight.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(epInfos);

        pnRight.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnRight);

        mbBar.setBackground(new java.awt.Color(255, 255, 255));

        meFile.setText("File");
        meFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fileopen.png"))); // NOI18N
        miOpen.setText("Open MovieList File");
        miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOpenMLFile(evt);
            }
        });
        meFile.add(miOpen);

        miLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        miLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/loadmovies.png"))); // NOI18N
        miLoad.setText("Load Movies");
        miLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadMovies(evt);
            }
        });
        meFile.add(miLoad);

        miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/save.png"))); // NOI18N
        miSave.setText("Save to MovieList File");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSave(evt);
            }
        });
        meFile.add(miSave);

        mbBar.add(meFile);

        meSettings.setText("Settings");

        miPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miPreferences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settings.png"))); // NOI18N
        miPreferences.setText("Preferences");
        miPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSettings(evt);
            }
        });
        meSettings.add(miPreferences);

        mbBar.add(meSettings);

        setJMenuBar(mbBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onLoadMovies(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadMovies
        ProgressbarDLG pd = new ProgressbarDLG(this, false);
        pd.setVisible(true);

        ml = new MovieLoader(pathtomovies, pd);

        loadMovies();
    }//GEN-LAST:event_onLoadMovies

    private void onSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSave
        Serializer s = new Serializer();

        if (movielist.size() > 0) {
            s.safeMovieList(movielist);
        } else {
            JOptionPane.showMessageDialog(this, "No Movies in List!\nPlease load Movies.", "Error!", 0);
        }
    }//GEN-LAST:event_onSave

    private void onOpenMLFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOpenMLFile
        DeSerializer ds = new DeSerializer(this);
        ds.deSerialize();

        if (movielist.size() > 0) {
            String things = uc.getSizeAndNumberOfFiles(movielist);
            this.lbThings.setText(things);
        }
    }//GEN-LAST:event_onOpenMLFile

    private void onSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSettings
        SettingsDLG sdlg = new SettingsDLG(this, true, pathtomovies, cu);
        sdlg.setVisible(true);

        JOptionPane.showMessageDialog(this, "Restart the Program for changes\nto take effect!", "Restart!", 1);
    }//GEN-LAST:event_onSettings

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epInfos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbThings;
    private javax.swing.JList liMovies;
    private javax.swing.JMenuBar mbBar;
    private javax.swing.JMenu meFile;
    private javax.swing.JMenu meSettings;
    private javax.swing.JMenuItem miLoad;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miPreferences;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnListe;
    private javax.swing.JPanel pnRight;
    private javax.swing.JScrollPane spList;
    // End of variables declaration//GEN-END:variables

    private void loadMovies() {
        ml.getMovies(this);
    }

    public void setList(LinkedList<Movie> liste) {
        movielist = liste;

        Collections.sort(movielist, new MovieCompare());

        System.out.println("" + movielist.size());

        clearList();
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
    
    public void resource()
    {
        // Lang support
        Locale currentLocal = Locale.ENGLISH;

        System.out.println(cu.getLang());

        if (cu.getLang().equals("de")) {
            currentLocal = Locale.GERMAN;
        }

        System.out.println(currentLocal.toString());

        resBundle = ResourceBundle.getBundle("src.ResourceBundle", currentLocal);
        meFile.setText(resBundle.getString("main_menu_file"));
        miOpen.setText(resBundle.getString("main_menu_file_open"));
        miLoad.setText(resBundle.getString("main_menu_file_load"));
        miSave.setText(resBundle.getString("main_menu_file_save"));
        meSettings.setText(resBundle.getString("main_menu_settings"));
        miPreferences.setText(resBundle.getString("main_menu_settings_preferences"));
        pnListe.setBorder(BorderFactory.createTitledBorder(resBundle.getString("main_left_titel")));
        pnRight.setBorder(BorderFactory.createTitledBorder(resBundle.getString("main_right_titel")));
        // END lang support

    }
}
