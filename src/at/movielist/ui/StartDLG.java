/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import java.awt.Frame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manu
 */
public class StartDLG extends SettingsDLG {

    public StartDLG(Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();

        System.out.println(ConfigUtility.getInstance().isFileExisting());
        
        if (!ConfigUtility.getInstance().isFileExisting()) {
            this.setVisible(true);
        } else {
            new MainUI();
        }
    }

    private void initComponents() {
        lbTitel.setText("Welcome to the initial setup");
    }

    @Override
    protected boolean onFinish() {
        if (super.onFinish()) {
            new MainUI();
            return true;
        } else {
            return false;
        }
    }

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
            java.util.logging.Logger.getLogger(StartDLG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StartDLG dialog = new StartDLG(null, true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(StartDLG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
