package com.huafeng.auction.ui;

import com.huafeng.auction.Column;
import com.huafeng.auction.SniperListener;
import com.huafeng.auction.SniperSnapshot;
import com.huafeng.auction.SniperState;

import javax.swing.table.AbstractTableModel;

/**
 * Created by stephen on 17/6/18.
 */
public class SnipersTableModel extends AbstractTableModel implements SniperListener{
    private final static SniperSnapshot STARTING_UP = new SniperSnapshot("item-54321", 0, 0, SniperState.JOINNING );
    private final static String[] STATUS_TEXT={
        "Joining", "Bidding", "Winning", "Lost", "Won"
    };

    private SniperSnapshot snapShot = STARTING_UP;

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Column.at(columnIndex).valueIn(snapShot);
    }

    @Override
    public String getColumnName(int column) {
        return Column.at(column).name;
    }

    public static String textFor(SniperState state){
        return STATUS_TEXT[state.ordinal()];
    }

    @Override
    public void sniperStateChanged(SniperSnapshot sniperSnapshot) {
        snapShot = sniperSnapshot;
        fireTableRowsUpdated(0, 0);
    }
}
