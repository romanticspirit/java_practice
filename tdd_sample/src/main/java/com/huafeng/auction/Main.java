package com.huafeng.auction;

import com.huafeng.auction.ui.MainWindow;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by stephen on 17/6/16.
 */
public class Main {
    private static final int ARG_HOSTNAME=0;
    private static final int ARG_USERNAME=1;
    private static final int ARG_PASSWORD=2;
    private static final int ARG_ITEM_ID =3;

    private MainWindow ui;
    private Chat  notToBeGcd;
    public static final String AUCTION_RESOURCE ="Auction";
    public static final String ITEM_ID_AS_LOGIN ="auction-%s";
    public static final String AUCTION_ID_FORMAT = ITEM_ID_AS_LOGIN +"@%s/" + AUCTION_RESOURCE;

    public Main() throws Exception{
        startUserInterface ();
    }

    private void startUserInterface() throws Exception{
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                ui = new MainWindow();
            }
        });
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
        notToBeGcd = connection.getChatManager().createChat(
                auctionId(itemId, connection),
                new MessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                ui.showStatus(Constant.STATUS_LOST);
                            }
                        });
                    }
                }
        );

        notToBeGcd.sendMessage(new Message());
    }

}
