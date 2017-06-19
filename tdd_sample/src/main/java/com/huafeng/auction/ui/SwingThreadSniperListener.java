package com.huafeng.auction.ui;

import com.huafeng.auction.SniperListener;
import com.huafeng.auction.SniperSnapshot;
import com.huafeng.auction.ui.MainWindow;

import javax.swing.*;

import static com.huafeng.auction.Constant.*;

/**
 * Created by stephen on 17/6/18.
 */
public class SwingThreadSniperListener implements SniperListener {

    private final SnipersTableModel model;

    public SwingThreadSniperListener(SnipersTableModel model) {
        this.model = model;
    }
    @Override
    public void sniperStateChanged(SniperSnapshot sniperSnapshot) {
        SwingUtilities.invokeLater(()->model.sniperStateChanged(sniperSnapshot));
    }

}
