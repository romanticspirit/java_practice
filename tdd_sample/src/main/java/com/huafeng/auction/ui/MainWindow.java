package com.huafeng.auction.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import static com.huafeng.auction.Constant.STATUS_JOINING;


public class MainWindow extends JFrame {
    public static final String MAIN_WINDOW_NAME = "Auction Sniper Main";
    public static final String SNIPER_STATUS_NAME = "sniper status";
    private final JLabel sniperStatus = createLabel(STATUS_JOINING);

    public MainWindow() {
        super("Auction Sniper");
        setName(MAIN_WINDOW_NAME);
        add(sniperStatus);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public JLabel createLabel(String initialText) {
        JLabel result = new JLabel();
        result.setText(initialText);
        result.setName(SNIPER_STATUS_NAME);
        result.setBorder(new LineBorder(Color.black));
        return result;
    }

    public void showStatus(String statusLost) {
        sniperStatus.setText(statusLost);
    }
}
