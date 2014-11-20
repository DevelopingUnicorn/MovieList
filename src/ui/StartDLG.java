package ui;

import bl.ConfigUtility;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StartDLG extends javax.swing.JDialog {

    private String userdocs = System.getProperty("user.home") + "\\Documents\\MovieList";
    private String pathtoconf = userdocs + "\\movielist.conf";
    private String pathtomovies;
    private ConfigUtility cu = new ConfigUtility(pathtoconf);
    private File conf = new File(pathtoconf);

    public StartDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        
        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        
        checkConf();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUe = new javax.swing.JLabel();
        lbVers = new javax.swing.JLabel();
        pnThings = new javax.swing.JPanel();
        lbCLUE = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        lbUEPM = new javax.swing.JLabel();
        pnPath = new javax.swing.JPanel();
        lbPath = new javax.swing.JLabel();
        btChoosePath = new javax.swing.JButton();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbUe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUe.setText("Welcome to the Setup!");
        getContentPane().add(lbUe, java.awt.BorderLayout.PAGE_START);

        lbVers.setText("v1.0");
        getContentPane().add(lbVers, java.awt.BorderLayout.PAGE_END);

        pnThings.setLayout(new java.awt.GridLayout(5, 0));

        lbCLUE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCLUE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCLUE.setText("Choose Language");
        pnThings.add(lbCLUE);

        cbLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Deutsch" }));
        pnThings.add(cbLang);

        lbUEPM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUEPM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUEPM.setText("Path to movies");
        pnThings.add(lbUEPM);

        pnPath.setLayout(new java.awt.GridLayout(1, 2));

        lbPath.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPath.setText("Not choosen yet!");
        pnPath.add(lbPath);

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
        
        String lang = cbLang.getSelectedItem().toString();
        
        cu.createConfigFile(lang, pathtomovies);
        
        MainUI mui = new MainUI(userdocs);
        this.dispose();
        mui.setVisible(true);
    }//GEN-LAST:event_onFinish

    private void onChooseFolder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onChooseFolder
        JFileChooser fc = new JFileChooser();
        FileFilter directoryFilter = new FileFilter() {
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
            lbPath.setText(pathtomovies);
        } else {
            JOptionPane.showMessageDialog(this, "Error!", "No folder choosen!", 0);
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
    private javax.swing.JLabel lbCLUE;
    private javax.swing.JLabel lbPath;
    private javax.swing.JLabel lbUEPM;
    private javax.swing.JLabel lbUe;
    private javax.swing.JLabel lbVers;
    private javax.swing.JPanel pnPath;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables

    private void checkConf() {
        File movielistdir = new File(userdocs);
        File movielist = new File(userdocs + "\\mvl.ml");

        if (movielistdir.exists() && movielistdir.isDirectory()) {
            if (!movielist.exists() || !conf.exists()) {
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
}
