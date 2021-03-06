package com.huafeng.auction;

import com.huafeng.auction.ui.MainWindow;
import com.huafeng.auction.ui.SnipersTableModel;
import com.huafeng.auction.ui.SwingThreadSniperListener;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Created by stephen on 17/6/16.
 */
public class Main{
    private static final int ARG_HOSTNAME=0;
    private static final int ARG_USERNAME=1;
    private static final int ARG_PASSWORD=2;
    private static final int ARG_ITEM_ID =3;

    private MainWindow ui;
    private Chat  notToBeGcd;
    public static final String AUCTION_RESOURCE ="Auction";
    public static final String ITEM_ID_AS_LOGIN ="auction-%s";
    public static final String AUCTION_ID_FORMAT = ITEM_ID_AS_LOGIN +"@%s/" + AUCTION_RESOURCE;

    public static final String JOIN_COMMAND_FORMAT = "SOLVersion: 1.1; Command: JOIN;";
    public static final String BID_COMMAND_FORMAT = "SOLVersion: 1.1; Command: BID; Price: %d;";

    private final SnipersTableModel snipers = new SnipersTableModel();

    public Main() throws Exception{
        startUserInterface ();
    }

    private void startUserInterface() throws Exception{
        SwingUtilities.invokeAndWait(()->  ui = new MainWindow(snipers));
    }

    private static XMPPConnection connectTo (String hostName, String userName, String password) throws XMPPException {
        XMPPConnection connection = new XMPPConnection(hostName);
        connection.connect();
        connection.login(userName, password, AUCTION_RESOURCE);
        return connection;
    }

    public static void main (String... args) throws Exception{
        Main main = new Main();
        XMPPConnection connection = connectTo(args[ARG_HOSTNAME], args[ARG_USERNAME], args[ARG_PASSWORD]);

        main.joinAuction(connection, args[ARG_ITEM_ID]);
    }


    private static String auctionId(String itemId, XMPPConnection connection){
        return String.format(AUCTION_ID_FORMAT, itemId, connection.getServiceName());
    }


    private void joinAuction(XMPPConnection connection,  String itemId) throws XMPPException{
        disconnectWhenUICloses (connection);
        Chat chat = connection.getChatManager().createChat(
                auctionId(itemId, connection),
                null);
        this.notToBeGcd = chat;

        Auction auction = new XMPPAuction(chat);

        chat.addMessageListener(new AuctionMessageTranslator(
                connection.getUser(),
                new AuctionSniper(auction, new SwingThreadSniperListener(snipers), itemId)));
        auction.join();
    }

    private void disconnectWhenUICloses(XMPPConnection connection) {
        ui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                connection.disconnect();
            }
        });
    }



}
