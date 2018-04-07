package net.stumpner.susideup;

import net.stumpner.susideup.gui.MainWindow;
import net.stumpner.upload.suside.SusideUploadService;
import net.stumpner.upload.UploadObserver;
import net.stumpner.upload.UploadException;

import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.prefs.*;

import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * Created by IntelliJ IDEA.
 * User: franz.stumpner
 * Date: 29.07.2007
 * Time: 11:34:48
 * To change this template use File | Settings | File Templates.
 */
public class MediaDeskUp {

    private JFrame frame;
    private MainWindow mainWindow;
    private File[] filesToUpload;
    private boolean hasErrors = false;

    public void showForm() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.showOpenDialog(null);

        filesToUpload = fileChooser.getSelectedFiles();

        /*
        for (int a = 0; a < filesToUpload.length; a++) {
            System.out.println(filesToUpload[a].getAbsolutePath());
        }*/
        if (filesToUpload.length==0) {
            showDialog("Keine Dateien ausgewählt","Es wurden keine Dateien für den Upload ausgewählt. Das Programm wird beendet.");
            System.exit(0);
        } else {
            mainWindow = new MainWindow(this);
            mainWindow.setFileList(filesToUpload);

            frame = new JFrame();
            frame.setTitle("MediaDESK Up V-1.0.1 by stumpner.MCS");
            frame.setContentPane(mainWindow.getContentPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(new Rectangle(300, 500));
            frame.pack();
            frame.setVisible(true);
        }

    }

    public void startUpload() {
        mainWindow.formEnabled(false);
        mainWindow.setCountState(filesToUpload.length,0);
        hasErrors=false;
        doUpload(0);
    }

    private void doUpload(int index) {

        final int indexNumber = index;

        SusideUploadService sus = new SusideUploadService(
                mainWindow.getMediaDeskUrl(), mainWindow.getUsername(), mainWindow.getPasswordField());
        sus.setFile(filesToUpload[index]);
        if (!mainWindow.isToInbox()) {
            if (mainWindow.isToFolder()) {
                sus.addStringPart(new StringPart("FOLDERNAME", mainWindow.getTypeName()));
            }
            if (mainWindow.isToCategory()) {
                sus.addStringPart(new StringPart("CATEGORYNAME", mainWindow.getTypeName()));
            }
        }
        sus.setObserver(new UploadObserver() {
            public void uploadFinished(File file, String string) {
                mainWindow.setCountState(filesToUpload.length,indexNumber+1);
                if (indexNumber + 1 < filesToUpload.length) {
                    doUpload(indexNumber + 1);
                } else {
                    uploadDone();
                }
            }

            public void uploadFailed(File file, Exception exception) {

                hasErrors=true;

                if (exception instanceof UploadException) {
                    UploadException uploadException = (UploadException)exception;
                    switch (uploadException.getError()) {
                        case UploadException.HTTP_ERROR:
                            showDialog("Allgemeiner HTTP Fehler", "Der Upload für die Datei " + file.getAbsolutePath() + " ist fehlgeschlagen.\nEs liegt ein HTTP-Fehler vor, überprüfen Sie die eingegebene Adresse der Bilddatenbank\nSie sollte in der Form: demo.mediadesk.net sein");
                            break;
                        case UploadException.AUTH_ERROR:
                            showDialog("Anmeldung fehlgeschlagen", "Die Anmeldung an der MediaDESK-Datenbank ist fehlgeschlagen.\nÜberprüfen Sie Benutzername und Passwort.");

                    }
                } else {
                    showDialog("Upload fehlgeschlagen", "Der Upload für die Datei " + file.getAbsolutePath() + " ist fehlgeschlagen");
                }

                uploadDone();


                /*
                Bei Fehlern nicht fortfahren...
                if (indexNumber + 1 < filesToUpload.length) {
                    doUpload(indexNumber + 1);
                } else {
                    uploadDone();
                }
                */
            }

            public void percentage(int percentage) {
                mainWindow.setPercentage(percentage);
            }
        });
        sus.start();
    }

    private void uploadDone() {

        if (hasErrors) {
            showDialog("Upload abgebrochen", "Der Upload wurde wegen eines Fehlers nicht abgeschlossen");
        } else {
            showDialog("Upload abgeschlossen", "Upload abgeschlossen");
        }
        mainWindow.formEnabled(true);
        //mainWindow.setPercentage(0);
    }

    private void showDialog(String title, String message) {

        final JDialog dialog = new JDialog(frame, title, true);
        final JOptionPane optionPane = new JOptionPane(
                message,
                JOptionPane.INFORMATION_MESSAGE);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(optionPane);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                dialog.setVisible(false);
            }
        });
        dialog.setVisible(true);

    }


    public void savePreferences(String url, String username, String password){
        String preferencesPath = "stumpnerMCS/mediadeskUp";
        Preferences mediaDeskPreferences = Preferences.userRoot().node(preferencesPath);
        mediaDeskPreferences.put("mediaDeskUrl", url);
        mediaDeskPreferences.put("username", username);
        mediaDeskPreferences.put("password", password);
    }

}
