package com.huafeng.auction;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;

import static com.huafeng.auction.Main.BID_COMMAND_FORMAT;
import static com.huafeng.auction.Main.JOIN_COMMAND_FORMAT;
import static java.lang.String.format;

/**
 * Created by stephen on 17/6/18.
 */
public class XMPPAuction implements Auction {

    private final Chat chat;

    public XMPPAuction(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void bid(int amount) {
        sendMessage(format(BID_COMMAND_FORMAT, amount));
    }

    @Override
    public void join (){
        sendMessage(JOIN_COMMAND_FORMAT);
    }

    private void sendMessage (final String message){
        try {
            chat.sendMessage(message);
        }catch (XMPPException e){
            e.printStackTrace();
        }
    }
}
