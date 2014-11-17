package ui;

public class StartDLG extends javax.swing.JDialog {

    public StartDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setSize(500, 150);
        this.setLocationRelativeTo(null);        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUe = new javax.swing.JLabel();
        lbVers = new javax.swing.JLabel();
        pnThings = new javax.swing.JPanel();
        lbLangUe = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbUe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUe.setText("Welcome to the Setup!");
        getContentPane().add(lbUe, java.awt.BorderLayout.PAGE_START);

        lbVers.setText("v1.0");
        getContentPane().add(lbVers, java.awt.BorderLayout.PAGE_END);

        pnThings.setLayout(new java.awt.GridLayout(3, 0));

        lbLangUe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLangUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLangUe.setText("Choose your language");
        pnThings.add(lbLangUe);

        pnThings.add(cbLang);

        btOk.setText("Next");
        pnThings.add(btOk);

        getContentPane().add(pnThings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JLabel lbLangUe;
    private javax.swing.JLabel lbUe;
    private javax.swing.JLabel lbVers;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables
}
