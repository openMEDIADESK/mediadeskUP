package net.stumpner.susideup;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: franz.stumpner
 * Date: 28.07.2007
 * Time: 19:07:34
 * To change this template use File | Settings | File Templates.
 */
public class AppMain {

    public static void main(String[] parameter) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        MediaDeskUp mediaDeskUp = new MediaDeskUp();
        mediaDeskUp.showForm();

    }

}
