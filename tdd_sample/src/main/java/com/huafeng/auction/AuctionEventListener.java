package com.huafeng.auction;

/**
 * Created by stephen on 17/6/17.
 */
public interface AuctionEventListener {
        void auctionClosed() ;

        void currentPrice(int price, int increment);
}
