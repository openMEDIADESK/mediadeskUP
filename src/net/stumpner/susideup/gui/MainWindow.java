package net.stumpner.susideup.gui; 

import net.stumpner.susideup.MediaDeskUp;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.prefs.Preferences;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;

/**
 * Created by IntelliJ IDEA.
 * User: franz.stumpner
 * Date: 29.07.2007
 * Time: 09:13:47
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow implements ActionListener {
    private JList fileList;
    private JTextField mediaDeskUrl;
    private JTextField username;
    private JPasswordField passwordField;
    private JPanel contentPanel;
    private JButton uploadButton;
    private JProgressBar progressBar;
    MediaDeskUp mediaDeskUp;
    private JRadioButton optionToInbox;
    private JRadioButton optionAutoInsert;
    private JComboBox autoType;
    private JTextField typeName;
    private JProgressBar progressAll;
    private JLabel countView;

    public MainWindow(MediaDeskUp mediaDeskUpClass) {
        mediaDeskUp = mediaDeskUpClass;

        fillPreferences();

        uploadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mediaDeskUp.savePreferences(getMediaDeskUrl(), getUsername(), getPasswordField());
                mediaDeskUp.startUpload();
            }
        });

        optionToInbox.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (optionToInbox.isSelected()) {
                    optionAutoInsert.setSelected(false);
                    typeName.setEnabled(false);
                    autoType.setEnabled(false);
                }
            }
        });
        optionAutoInsert.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (optionAutoInsert.isSelected()) {
                    optionToInbox.setSelected(false);
                    typeName.setEnabled(true);
                    autoType.setEnabled(true);
                }
            }
        });

        optionToInbox.setSelected(true);
        this.formEnabled(true);
        autoType.addItem("Event");
        autoType.addItem("Kategorie");
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void formEnabled(boolean enabled) {
        mediaDeskUrl.setEnabled(enabled);
        username.setEnabled(enabled);
        passwordField.setEnabled(enabled);
        uploadButton.setEnabled(enabled);
        optionToInbox.setEnabled(enabled);
        optionAutoInsert.setEnabled(enabled);
        optionToInbox.setEnabled(enabled);
        if (enabled && optionAutoInsert.isSelected()) {
            typeName.setEnabled(true);
            autoType.setEnabled(true);
        } else {
            typeName.setEnabled(false);
            autoType.setEnabled(false);
        }

    }

    public void setFileList(File[] files) {
        fileList.setListData(files);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed!");
    }

    public void setPercentage(int percentage) {
        progressBar.setMaximum(100);
        progressBar.setValue(percentage);
    }

    public String getMediaDeskUrl() {
        return mediaDeskUrl.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    public boolean isToInbox() {
        return optionToInbox.isSelected();
    }

    public boolean isToCategory() {
        //noinspection RedundantIfStatement
        return ((String) autoType.getSelectedItem()).equalsIgnoreCase("Kategorie") ? true : false;
    }

    public String getTypeName() {
        return typeName.getText();
    }

    public boolean isToFolder() {
        return ((String) autoType.getSelectedItem()).equalsIgnoreCase("Event") ? true : false;
    }

    public void setCountState(int imageCount, int imageDone) {
        this.countView.setText(imageDone + " / " + imageCount);
        this.progressAll.setMinimum(0);
        this.progressAll.setMaximum(imageCount);
        this.progressAll.setValue(imageDone);
    }

    public void fillPreferences() {
        String preferencesPath = "stumpnerMCS/mediadeskUp";
        Preferences mediaDeskPreferences = Preferences.userRoot().node(preferencesPath);
        mediaDeskUrl.setText(mediaDeskPreferences.get("mediaDeskUrl", "mediaDeskUrl"));
        username.setText(mediaDeskPreferences.get("username", "username"));
        passwordField.setText(mediaDeskPreferences.get("password", "password"));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(9, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("MediaDESK");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Hochzuladende Dateien");
        contentPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("MediaDESK-Adresse:");
        panel2.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mediaDeskUrl = new JTextField();
        mediaDeskUrl.setToolTipText("Adresse der SuSIDE-Bilddatenbank eingeben, z.B: demo.suside.net (Achtung: ohne http!)");
        panel2.add(mediaDeskUrl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Benutzername:");
        panel2.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Passwort:");
        panel2.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        username = new JTextField();
        panel2.add(username, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField = new JPasswordField();
        panel2.add(passwordField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        uploadButton = new JButton();
        uploadButton.setHorizontalAlignment(0);
        uploadButton.setText("Upload starten");
        contentPanel.add(uploadButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        optionToInbox = new JRadioButton();
        optionToInbox.setText("in die Inbox laden");
        panel3.add(optionToInbox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        optionAutoInsert = new JRadioButton();
        optionAutoInsert.setText("automatisch zu einem Event/Kategorie geben");
        panel3.add(optionAutoInsert, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setEnabled(true);
        panel4.setVisible(true);
        contentPanel.add(panel4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Automatische Zuordnung"));
        autoType = new JComboBox();
        autoType.setEnabled(true);
        panel4.add(autoType, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setVisible(true);
        panel4.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Name:");
        panel5.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeName = new JTextField();
        typeName.setToolTipText("Name des Ordners oder der Kategorie in den die Bilder geladen werden sollen. Wenn der Ordner oder die Kateogrie noch nicht existieren wird er automatisch angelegt.");
        panel5.add(typeName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fileList = new JList();
        scrollPane1.setViewportView(fileList);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel6, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Datei");
        panel6.add(label7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        countView = new JLabel();
        countView.setText("- / -");
        panel6.add(countView, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        panel6.add(progressBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel7, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Gesamt");
        panel7.add(label8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressAll = new JProgressBar();
        progressAll.setStringPainted(true);
        panel7.add(progressAll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPanel;
    }
}
