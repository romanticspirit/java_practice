package com.huafeng.auction;

import org.hamcrest.Matcher;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import java.beans.IntrospectionException;

import static com.huafeng.auction.Main.AUCTION_RESOURCE;
import static com.huafeng.auction.Main.ITEM_ID_AS_LOGIN;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by stephen on 17/6/16.
 */
public class FakeAuctionServer {


    public static final String XMPP_HOSTNAME    ="localhost";

    public static final String AUCTION_PASSWORD   ="auction";

    private final String itemId;
    private final XMPPConnection connection;
    private Chat currentChat;

    public FakeAuctionServer(String itemId){
        this.itemId = itemId;
        this.connection = new XMPPConnection(XMPP_HOSTNAME);
    }

    private final SingleMessageListener messageListener = new SingleMessageListener ();
    public void startSellingItem () throws XMPPException
    {
        connection.connect();

        connection.login(format(ITEM_ID_AS_LOGIN, itemId), AUCTION_PASSWORD, AUCTION_RESOURCE);
        connection.getChatManager().addChatListener(
                new ChatManagerListener() {
                    @Override
                    public void chatCreated(Chat chat, boolean b) {
                        currentChat = chat;
                        chat.addMessageListener(messageListener);
                    }
                }
        );
    }

    public String getItemId() {
        return  itemId;
    }

    public void hasReceivedJoinRequestFromSniper (String sniperId) throws InterruptedException {
        receivesAMessageMatching(sniperId, equalTo(Main.JOIN_COMMAND_FORMAT));
    }

    public void announceClosed() throws XMPPException{
        currentChat.sendMessage("SOLVersion: 1.1; Event: CLOSE;");
    }

    public void stop (){
        connection.disconnect();
    }

    private void receivesAMessageMatching (String sniperId, Matcher<? super String> messageMatcher) throws InterruptedException {
        messageListener.receiveAMessage(messageMatcher);
        assertThat(currentChat.getParticipant(), equalTo(sniperId));
    }

    public void reportPrice(int price, int increment, String bidder) throws XMPPException {
        currentChat.sendMessage(
                String.format("SOLVersion: 1.1; Event: PRICE; " +
                        "CurrentPrice: %d; Increment: %d; Bidder: %s;", price, increment, bidder));
    }

    public void hasReceivedBid(int bid, String sniperId) throws InterruptedException {
        receivesAMessageMatching(sniperId, equalTo(format(Main.BID_COMMAND_FORMAT, bid)));
    }
}
