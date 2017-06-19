package com.huafeng.auction.ui;

import com.huafeng.auction.SniperSnapshot;

import javax.swing.*;

import java.awt.*;


public class MainWindow extends JFrame {
    public static final String MAIN_WINDOW_NAME = "Auction Sniper Main";
    private static final String SNIPERS_TABLE_NAME = "Snipers Table";
    public static final String APPLICATION_TITLE = "Auction Sniper";
    private final SnipersTableModel snipers;

    public MainWindow(SnipersTableModel snipers) {
        super("Auction Sniper");
        this.snipers = snipers;
        setName(MAIN_WINDOW_NAME);
        fillContenntPane(makeSniperTable());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void fillContenntPane(JTable snipersTable){
        final Container contentPage = getContentPane();
        contentPage.setLayout(new BorderLayout());
        contentPage.add(new JScrollPane(snipersTable), BorderLayout.CENTER);
    }

    private JTable makeSniperTable (){
        final JTable snipersTable = new JTable(snipers);
        snipersTable.setName(SNIPERS_TABLE_NAME);
        return snipersTable;
    }

}
