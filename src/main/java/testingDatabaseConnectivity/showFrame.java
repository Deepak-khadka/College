package testingDatabaseConnectivity;

import javax.swing.*;

public class showFrame {

    public static void main(String[] args) {
      AuthorFrame authorFrame = new AuthorFrame();
      authorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      authorFrame.setSize(800,500);
      authorFrame.setVisible(true);
    }
}
