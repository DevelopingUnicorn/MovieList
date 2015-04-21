package at.movielist.ui;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.Resource;

public class CreditsDLG extends javax.swing.JDialog {

    public CreditsDLG(java.awt.Frame parent, boolean modal, Locale loc) {
        super(parent, modal);
        initComponents();

        this.setSize(400, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(parent);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });
        
        epCredits.setContentType("text/html");
        
        ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", loc);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>").append(resBundle.getString("credits_developers")).append("</h2>").append("</br>").append("<ul><li>Schni (DU)</li></ul>")
                .append("</br><h2>").append(resBundle.getString("credits_translation")).append("</h2></br>")
                .append("<ul><li>German (Schni)</li><li>English (Schni)</li><li>Espaniol (SamDo)</li></ul>");
        
        epCredits.setText(sb.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLogo = new javax.swing.JLabel();
        pnContent = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        epCredits = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/logo.png"))); // NOI18N
        getContentPane().add(lbLogo, java.awt.BorderLayout.PAGE_START);

        pnContent.setLayout(new java.awt.BorderLayout());

        epCredits.setEditable(false);
        epCredits.setBackground(new java.awt.Color(240, 240, 240));
        jScrollPane1.setViewportView(epCredits);

        pnContent.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epCredits;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JPanel pnContent;
    // End of variables declaration//GEN-END:variables
}
