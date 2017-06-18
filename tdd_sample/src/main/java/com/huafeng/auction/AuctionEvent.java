package com.huafeng.auction;

import sun.plugin2.message.Message;

import java.util.HashMap;

/**
 * Created by stephen on 17/6/18.
 */
public class AuctionEvent {
    HashMap<String, String> fields = new HashMap<>();
    public String getType(){
        return get("Event");
    }

    public int getCurrentPrice (){
        return getInt("CurrentPrice");
    }

    public int increment (){
        return getInt("Increment");
    }

    private int getInt (String fieldName){
       return Integer.parseInt(get(fieldName));
    }
    private String get(String fieldName) {
        return fields.get(fieldName);
    }

    private void addField(String field){
        String[] pair = field.split(":");
        fields.put(pair[0].trim(), pair[1].trim());
    }

    static AuctionEvent from(String messageBody){
        AuctionEvent event = new AuctionEvent();
        for(String field : fieldsIn(messageBody)){
            event.addField(field);
        }

        return event;
    }

    private static String[] fieldsIn(String messageBody) {
        return messageBody.split(";");
    }

    public PriceSource isFrom(String sniperId) {
        return sniperId.equals(bidder()) ? PriceSource.fromSniper: PriceSource.fromOtherBidder;
    }

    private String bidder (){
        return get("Bidder");
    }
}
