package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import at.movielist.tmdb.APItmdb;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProxySettingsDLG extends javax.swing.JDialog {

    private ResourceBundle resBundle;

    public ProxySettingsDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setSize(600, 300);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        try {
            switch (ConfigUtility.getInstance().getPropLang()) {
                case "de":
                    setLang("de");
                    break;
                case "en":
                    setLang("en");
                    break;
                case "es":
                    setLang("es");
                    break;
                default:
                    setLang("en");
            }
        } catch (IOException ex) {
            Logger.getLogger(FetchedMoviesDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.tfServer.setText(ConfigUtility.getInstance().getPropProxyHost());
            this.tfPort.setText(ConfigUtility.getInstance().getPropProxyPort() + "");
            this.tfUser.setText(ConfigUtility.getInstance().getPropProxyUsername());
            this.pfPw.setText(ConfigUtility.getInstance().getPropProxyPassword());
        } catch (IOException ex) {
            Logger.getLogger(ProxySettingsDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUe = new javax.swing.JLabel();
        btSet = new javax.swing.JButton();
        pnFields = new javax.swing.JPanel();
        pnServer = new javax.swing.JPanel();
        lbServer = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        tfServer = new javax.swing.JTextField();
        cbAuth = new javax.swing.JCheckBox();
        pnUser = new javax.swing.JPanel();
        lbUser = new javax.swing.JLabel();
        tfUser = new javax.swing.JTextField();
        lbPassword = new javax.swing.JLabel();
        pfPw = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbUe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/proxysettings.png"))); // NOI18N
        lbUe.setText("ProxySettings");
        getContentPane().add(lbUe, java.awt.BorderLayout.PAGE_START);

        btSet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSet.setText("Set");
        btSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOK(evt);
            }
        });
        getContentPane().add(btSet, java.awt.BorderLayout.PAGE_END);

        pnFields.setLayout(new java.awt.GridLayout(3, 0));

        lbServer.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbServer.setText("Server:");

        tfPort.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tfServer.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnServerLayout = new javax.swing.GroupLayout(pnServer);
        pnServer.setLayout(pnServerLayout);
        pnServerLayout.setHorizontalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbServer)
                .addGap(25, 25, 25)
                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfPort, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnServerLayout.setVerticalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbServer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnFields.add(pnServer);

        cbAuth.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbAuth.setText("Authentication");
        cbAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAuthToggle(evt);
            }
        });
        pnFields.add(cbAuth);

        pnUser.setLayout(new java.awt.GridLayout(2, 2));

        lbUser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbUser.setText("User:");
        pnUser.add(lbUser);

        tfUser.setEditable(false);
        tfUser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        pnUser.add(tfUser);

        lbPassword.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbPassword.setText("Password");
        pnUser.add(lbPassword);

        pfPw.setEditable(false);
        pfPw.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        pfPw.setText("jPasswordField1");
        pnUser.add(pfPw);

        pnFields.add(pnUser);

        getContentPane().add(pnFields, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onAuthToggle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAuthToggle
        if (cbAuth.isSelected()) {
            tfUser.setEditable(true);
            pfPw.setEditable(true);
        } else {
            tfUser.setEditable(false);
            pfPw.setEditable(false);
        }
    }//GEN-LAST:event_onAuthToggle

    private void onOK(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOK
        if (tfServer.getText().equals("")) {
            try {
                APItmdb.getInstance().resetProxy();
                JOptionPane.showMessageDialog(null, resBundle.getString("settings_proxy_message"), resBundle.getString("settings_proxy_messageTitle"), JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ProxySettingsDLG.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tfPort.getText().equals("")) {
            JOptionPane.showMessageDialog(null, resBundle.getString("settings_proxy_portError"), resBundle.getString("settings_proxy_portErrorTitle"), JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int port = Integer.parseInt(tfPort.getText());
                if (cbAuth.isSelected()) {
                    APItmdb.getInstance().setProxyUseWithAuth(tfServer.getText(), port, tfUser.getText(), pfPw.getPassword());
                    ConfigUtility.getInstance().saveConfigToFile(tfUser.getText(), new String(pfPw.getPassword()), tfServer.getText(), Integer.parseInt(tfPort.getText()), cbAuth.isSelected());
                } else {
                    APItmdb.getInstance().setProxyUse(tfServer.getText(), port);
                    ConfigUtility.getInstance().saveConfigToFile("", "", tfServer.getText(), Integer.parseInt(tfPort.getText()), cbAuth.isSelected());
                }
                JOptionPane.showMessageDialog(null, resBundle.getString("settings_proxy_message"), resBundle.getString("settings_proxy_messageTitle"), JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ProxySettingsDLG.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException | HeadlessException pe) {
                JOptionPane.showMessageDialog(null, resBundle.getString("settings_proxy_portParseError"), resBundle.getString("settings_proxy_portErrorTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_onOK

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSet;
    private javax.swing.JCheckBox cbAuth;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbServer;
    private javax.swing.JLabel lbUe;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPasswordField pfPw;
    private javax.swing.JPanel pnFields;
    private javax.swing.JPanel pnServer;
    private javax.swing.JPanel pnUser;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfServer;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables

    private void setLang(String lang) {

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
        lbUe.setText(resBundle.getString("settings_proxy_ue"));
        lbServer.setText(resBundle.getString("settings_proxy_server"));
        cbAuth.setText(resBundle.getString("settings_proxy_auth"));
        lbUser.setText(resBundle.getString("settings_proxy_user"));
        lbPassword.setText(resBundle.getString("settings_proxy_pw"));
        btSet.setText(resBundle.getString("settings_proxy_btn"));
        // END lang support
    }

}
