package com.huafeng.auction;

import com.huafeng.auction.ui.MainWindow;

import javax.swing.*;

import static com.huafeng.auction.Constant.*;

/**
 * Created by stephen on 17/6/18.
 */
public class SniperStateDisplayer implements SniperListener {

    private final MainWindow ui;

    public SniperStateDisplayer(MainWindow ui) {
        this.ui = ui;
    }

    @Override
    public void sniperLost() {
        showStatus(STATUS_LOST);
    }

    @Override
    public void sniperBidding() {
        showStatus(STATUS_BIDDING);
    }

    @Override
    public void sniperWinning(){
        showStatus(STATUS_WINNING);
    }

    @Override
    public void sniperWon() {
        showStatus(STATUS_WON);
    }

    private void showStatus (final String status){
        SwingUtilities.invokeLater(()->ui.showStatus(status));
    }
}
