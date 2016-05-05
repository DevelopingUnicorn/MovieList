package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class SetupDLG extends javax.swing.JDialog {

    public static String[] DEFAULT_FONT = new String[]{
        "ProgressBar.font", "List.font", "EditorPane.font", "ComboBox.font"
    };

    private ArrayList<String> pathsToMovies = new ArrayList<String>();
    private LinkedList<Image> iconlist = new LinkedList<>();
    private ResourceBundle resBundle;

    private final String userdocs = new StringBuilder()
            .append(System.getProperty("user.home"))
            .append(File.separator)
            .append("Documents")
            .append(File.separator)
            .append("MovieList").toString();

    private final String pathToConfFile = new StringBuilder()
            .append(userdocs)
            .append(File.separator)
            .append("movielist.properties").toString();

    private final String pathToImages = new StringBuilder()
            .append(userdocs)
            .append(File.separator)
            .append("posters").toString();

    /**
     * Constructor
     *
     * @param parent
     * @param modal
     */
    public SetupDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            for (String s : DEFAULT_FONT) {
                UIManager.put(s, new Font("Arial", Font.PLAIN, 14));
            }

            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            //TODO exception
        }

        cbLang.setModel(new DefaultComboBoxModel(new String[]{"English", "Deutsch", "Espaniol"}));

        cbLang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String lang = cbLang.getSelectedItem().toString();
                switch (lang) {
                    case "Deutsch":
                        setLang("de");
                        break;
                    case "English":
                        setLang("en");
                        break;
                    case "Espaniol":
                        setLang("es");
                        break;
                    default:
                        setLang("en");
                }
            }
        });

        cbAutoSave.setSelected(true);
        cbSavePosters.setSelected(true);

        listPathsToMovies.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
//                    int index = list.locationToIndex(evt.getPoint());
                    int index = list.getSelectedIndex();
                    pathsToMovies.remove(index);
                    listPathsToMovies.setListData(pathsToMovies.toArray(new String[pathsToMovies.size()]));
                    listPathsToMovies.updateUI();
                    System.out.println(index);
                }
            }
        });

        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.small.png")).getImage());

        this.setIconImages(iconlist);

        this.setSize(750, 400);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setResizable(true);

        setLang("en");

        checkConf();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnUe = new javax.swing.JPanel();
        lbTitel = new javax.swing.JLabel();
        btOk = new javax.swing.JButton();
        pnThings = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbCLUE = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        lbUEPM = new javax.swing.JLabel();
        btChoosePath = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPathsToMovies = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        cbAutoSave = new javax.swing.JCheckBox();
        cbSavePosters = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(null);

        pnUe.setLayout(new java.awt.BorderLayout());

        lbTitel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/settingsBig.png"))); // NOI18N
        lbTitel.setText("Settings");
        pnUe.add(lbTitel, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnUe, java.awt.BorderLayout.PAGE_START);

        btOk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btOk.setText("Change settings!");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFinish(evt);
            }
        });
        getContentPane().add(btOk, java.awt.BorderLayout.SOUTH);

        pnThings.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        lbCLUE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCLUE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCLUE.setText("Language:");
        jPanel1.add(lbCLUE);

        cbLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English" }));
        jPanel1.add(cbLang);

        lbUEPM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUEPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUEPM.setText("Paths to movies:");
        jPanel1.add(lbUEPM);

        btChoosePath.setText("Choose folder");
        btChoosePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onChooseFolder(evt);
            }
        });
        jPanel1.add(btChoosePath);

        pnThings.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(listPathsToMovies);

        pnThings.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        cbAutoSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbAutoSave.setText("Auto save to loaded movies to a file?");
        jPanel2.add(cbAutoSave);

        cbSavePosters.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbSavePosters.setText("Download Posters for Movies?");
        jPanel2.add(cbSavePosters);

        pnThings.add(jPanel2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnThings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * triggered when the OK btn has been pressed
     *
     * @param evt
     */
    private void onFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFinish
        onFinish();
    }//GEN-LAST:event_onFinish

    private void onChooseFolder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onChooseFolder
        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileFilter directoryFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Movie Folder";
            }
        };

        fc.setFileFilter(directoryFilter);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pathsToMovies.add(fc.getSelectedFile().toString());
            listPathsToMovies.setListData(pathsToMovies.toArray(new String[pathsToMovies.size()]));
            listPathsToMovies.updateUI();
        }
    }//GEN-LAST:event_onChooseFolder


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoosePath;
    private javax.swing.JButton btOk;
    private javax.swing.JCheckBox cbAutoSave;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JCheckBox cbSavePosters;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCLUE;
    protected javax.swing.JLabel lbTitel;
    private javax.swing.JLabel lbUEPM;
    private javax.swing.JList listPathsToMovies;
    private javax.swing.JPanel pnThings;
    private javax.swing.JPanel pnUe;
    // End of variables declaration//GEN-END:variables
/**
     * Upadtes the UI elements to use the given Language
     *
     * @param lang
     */
    public void setLang(String lang) {

        switch (lang) {
            case "de":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.GERMAN);
                break;
            case "en":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
                break;
            case "es":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", new Locale("es"));
                break;
        }

        // Lang support
        lbTitel.setText(resBundle.getString("setup_title"));
        lbUEPM.setText(resBundle.getString("setup_path"));
        lbCLUE.setText(resBundle.getString("setup_chooseLang"));
        btOk.setText(resBundle.getString("setup_finish"));
        btChoosePath.setText(resBundle.getString("setup_choosePath"));
        cbAutoSave.setText(resBundle.getString("settings_autoSave"));
        cbSavePosters.setText(resBundle.getString("settings_dlposters"));
        // END lang support
    }

    /**
     * Called when the OK btn has been clicked
     *
     * @return true if all went right; false on error
     */
    protected boolean onFinish() {

        if (pathsToMovies.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select at least one path", "Attention", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        String lang = cbLang.getSelectedItem().toString();

        switch (lang) {
            case "Deutsch":
                lang = "de";
                break;
            case "English":
                lang = "en";
                break;
            case "Espaniol":
                lang = "es";
                break;
            default:
                lang = "en";
        }

        try {
            ConfigUtility.getInstance().saveConfigToFile(lang, cbAutoSave.isSelected(), cbSavePosters.isSelected(), pathsToMovies.toArray(new String[pathsToMovies.size()]));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occured! Maybe the config file is missing.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SetupDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
        new MainUI();
        return true;
    }

    /**
     * Checks the ConfigFile if any exists
     */
    private void checkConf() {
        File userdir = new File(userdocs);
        File conf = new File(pathToConfFile);
        if (userdir.exists() && userdir.isDirectory()) {
            if (!conf.exists() || !conf.isFile()) {
                this.setVisible(true);
            } else {
                try {
                    if (!ConfigUtility.getInstance().isFileExisting()) {
                        this.setVisible(true);
                    } else {
                        new MainUI();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SetupDLG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            userdir.mkdir();
            this.setVisible(true);
        }
    }

    /**
     * Starts the programm :)
     *
     * @param args
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetupDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SetupDLG dialog = new SetupDLG(null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
            }
        });
    }
}
