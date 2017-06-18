package com.huafeng.auction;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;

/**
 * Created by stephen on 17/6/17.
 */

public class AuctionMessageTranslator implements MessageListener{
    private final AuctionEventListener listener;
    private final String sniperId;

    public AuctionMessageTranslator(String sniperId, AuctionEventListener listener) {
        this.sniperId = sniperId;
        this.listener = listener;
    }

    public void processMessage(Chat chat, Message message) {
        AuctionEvent event = AuctionEvent.from (message.getBody());
        String type = event.getType();
        if ("CLOSE".equals(type)){
            listener.auctionClosed();
        }else if ("PRICE".equals(type)){
            listener.currentPrice(event.getCurrentPrice(), event.increment(), event.isFrom(sniperId));
        }
    }

    private HashMap<String, String> unpackEventFrom(Message message){
        HashMap<String, String> event = new HashMap<>();
        for(String element : message.getBody().split(";")){
            String[] pair = element.split(":");
            event.put(pair[0].trim(), pair[1].trim());
        }
        return event;
    }
}
