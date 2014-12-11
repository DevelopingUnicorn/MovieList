package ui;

import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class ProgressbarDLG extends javax.swing.JDialog {

    private SwingWorker movieworker;
    private LinkedList<Image> iconlist = new LinkedList<Image>();

    public ProgressbarDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setSize(400, 100);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.small.png")).getImage());

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                movieworker.cancel(true);
                System.out.println("Worker Cancled!");
                dispose();
            }
        });

        this.pbLoading.setStringPainted(true);
    }

    public JProgressBar getProgBar() {
        return this.pbLoading;
    }

    public JLabel getLabel() {
        return this.lbxfromy;
    }

    public void setMovieWorker(SwingWorker mw) {
        this.movieworker = mw;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbLoading = new javax.swing.JProgressBar();
        lbxfromy = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 0));

        pbLoading.setMaximum(1000000);
        getContentPane().add(pbLoading);
        getContentPane().add(lbxfromy);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbxfromy;
    private javax.swing.JProgressBar pbLoading;
    // End of variables declaration//GEN-END:variables
}
