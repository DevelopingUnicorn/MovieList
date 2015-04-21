package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class StartDLG extends javax.swing.JDialog {

    private String userdocs = new StringBuilder().append(System.getProperty("user.home")).append(File.separator).append("Documents").append(File.separator).append("MovieList").toString();
    private String pathtoconf = new StringBuilder().append(userdocs).append(File.separator).append("movielist.conf").toString();
    private String pathtomovies;
    private ConfigUtility cu = new ConfigUtility(pathtoconf);
    private File conf = new File(pathtoconf);
    private LinkedList<Image> iconlist = new LinkedList<Image>();
    private ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);

    public StartDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        cbLang.setModel(new DefaultComboBoxModel(new String[]{"English", "Deutsch", "Espaniol"}));
        cbLang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String lang = cbLang.getSelectedItem().toString();

                switch (lang) {
                    case "Deutsch":
                        language("de");
                        break;
                    case "English":
                        language("en");
                        break;
                    case "Espaniol":
                        language("es");
                        break;
                    default:
                        language("en");
                }
            }
        });

        // Lang support
        lbTitel.setText(resBundle.getString("setup_titel"));
        lbPath.setText(resBundle.getString("setup_path"));
        lbChooseLang.setText(resBundle.getString("setup_chooseLang"));
        lbPathFeedback.setText(resBundle.getString("setup_noPath"));
        btOk.setText(resBundle.getString("setup_finish"));
        btChoosePath.setText(resBundle.getString("setup_choosePath"));
        // END lang support

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.small.png")).getImage());

        this.setIconImages(iconlist);

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        checkConf();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitel = new javax.swing.JLabel();
        lbVers = new javax.swing.JLabel();
        pnThings = new javax.swing.JPanel();
        lbChooseLang = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        lbPath = new javax.swing.JLabel();
        pnPath = new javax.swing.JPanel();
        lbPathFeedback = new javax.swing.JLabel();
        btChoosePath = new javax.swing.JButton();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbTitel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitel.setText("Welcome to the Setup!");
        getContentPane().add(lbTitel, java.awt.BorderLayout.PAGE_START);

        lbVers.setText("version");
        getContentPane().add(lbVers, java.awt.BorderLayout.PAGE_END);

        pnThings.setLayout(new java.awt.GridLayout(5, 0));

        lbChooseLang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbChooseLang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbChooseLang.setText("Choose Language");
        pnThings.add(lbChooseLang);

        cbLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English" }));
        pnThings.add(cbLang);

        lbPath.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPath.setText("Path to movies");
        pnThings.add(lbPath);

        pnPath.setLayout(new java.awt.GridLayout(1, 2));

        lbPathFeedback.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPathFeedback.setText("Not choosen yet!");
        pnPath.add(lbPathFeedback);

        btChoosePath.setText("Choose folder");
        btChoosePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onChooseFolder(evt);
            }
        });
        pnPath.add(btChoosePath);

        pnThings.add(pnPath);

        btOk.setText("Finish!");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFinish(evt);
            }
        });
        pnThings.add(btOk);

        getContentPane().add(pnThings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFinish
        if (!lbPathFeedback.getText().equals(resBundle.getString("setup_noPath"))) {
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

            cu.createConfigFile(lang, pathtomovies);

            MainUI mui = new MainUI(userdocs);
            this.dispose();
            mui.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, resBundle.getString("setup_error_path"), resBundle.getString("setup_error"), 0);
        }
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
            pathtomovies = fc.getSelectedFile().toString();
            lbPathFeedback.setText(pathtomovies);
        } else {
            JOptionPane.showMessageDialog(this, resBundle.getString("setup_error_path"), resBundle.getString("setup_error"), 0);
        }
    }//GEN-LAST:event_onChooseFolder

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StartDLG dialog = new StartDLG(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoosePath;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JLabel lbChooseLang;
    private javax.swing.JLabel lbPath;
    private javax.swing.JLabel lbPathFeedback;
    private javax.swing.JLabel lbTitel;
    private javax.swing.JLabel lbVers;
    private javax.swing.JPanel pnPath;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables

    private void checkConf() {
        File movielistdir = new File(userdocs);
        File movielist = new File(userdocs + "\\mvl.ml");

        if (movielistdir.exists() && movielistdir.isDirectory()) {
            if (!conf.exists()) { //!movielist.exists() ||
                this.setVisible(true);
            } else {
                MainUI mu = new MainUI(userdocs);
                mu.setVisible(true);
            }
        } else {
            movielistdir.mkdir();
            this.setVisible(true);
        }
    }

    public void language(String lang) {

        if (lang.equals("de")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.GERMAN);
        } else if (lang.equals("en")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
        } else if (lang.equals("es")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", new Locale("es"));
        }

        // Lang support
        lbTitel.setText(resBundle.getString("setup_titel"));
        lbPath.setText(resBundle.getString("setup_path"));
        lbVers.setText(resBundle.getString("version"));
        lbChooseLang.setText(resBundle.getString("setup_chooseLang"));
        lbPathFeedback.setText(resBundle.getString("setup_noPath"));
        btOk.setText(resBundle.getString("setup_finish"));
        btChoosePath.setText(resBundle.getString("setup_choosePath"));
        // END lang support
    }
}
