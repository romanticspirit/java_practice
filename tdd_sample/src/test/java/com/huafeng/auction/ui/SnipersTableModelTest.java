package com.huafeng.auction.ui;

import com.huafeng.auction.Column;
import com.huafeng.auction.SniperSnapshot;
import com.huafeng.auction.SniperState;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import static com.huafeng.auction.Constant.STATUS_BIDDING;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by stephen on 17/6/18.
 */
public class SnipersTableModelTest {
    private final Mockery context = new Mockery();
    private TableModelListener listener = context.mock(TableModelListener.class);
    private final SnipersTableModel model = new SnipersTableModel();

    @Before
    public void attachModelListener (){
        model.addTableModelListener(listener);
    }

    @Test
    public void hasEnoughColumns(){
        assertThat(model.getColumnCount(), equalTo(Column.values().length));
    }

    @Test
    public void setSniperValuesInColumns (){
        context.checking(new Expectations(){{
            one(listener).tableChanged(with(aRowChangedEvent()));
        }});

        model.sniperStateChanged(new SniperSnapshot("item id", 555, 666, SniperState.BIDDING));
        assertColumnEquals (Column.ITEM_IDENTIFIER, "item id");
        assertColumnEquals (Column.LAST_PRICE, 555);
        assertColumnEquals (Column.LAST_BID, 666);
        assertColumnEquals (Column.SNIPER_STATE, STATUS_BIDDING);
    }

    @Test
    public void setsUpColumnHeadings(){
        for (Column column : Column.values()){
            assertEquals(column.name, model.getColumnName(column.ordinal()));
        }
    }

    private void assertColumnEquals(Column column, Object obj) {
        final int rowIndex =0;
        final int columnIndex = column.ordinal();
        assertEquals (obj, model.getValueAt(rowIndex, columnIndex));
    }

    private Matcher<TableModelEvent> aRowChangedEvent() {
        return samePropertyValuesAs(new TableModelEvent(model, 0));
    }
}
