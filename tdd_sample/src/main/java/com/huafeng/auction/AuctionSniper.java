package com.huafeng.auction;

import sun.tools.jconsole.ProxyClient;

/**
 * Created by stephen on 17/6/18.
 */
public class AuctionSniper implements AuctionEventListener{

    private final SniperListener sniperListener;
    private final Auction   auction;
    private boolean isWinning =false;
    private final String itemId;
    private SniperSnapshot snapshot;

    public AuctionSniper(Auction auction, SniperListener sniperListener, String itemId) {
        this.auction = auction;
        this.sniperListener = sniperListener;
        this.itemId = itemId;
        this.snapshot = SniperSnapshot.join(itemId);
    }

    @Override
    public void auctionClosed() {
       snapshot = snapshot.closed ();
       notifyChange();
    }

    private void notifyChange (){
        sniperListener.sniperStateChanged(snapshot);
    }

    @Override
    public void currentPrice(int price, int increment, PriceSource source) {
        isWinning = source == PriceSource.fromSniper;
        if (isWinning){
            snapshot = snapshot.winning(price);
        }else{
            final int bid = price+increment;
            auction.bid(bid);
            snapshot = snapshot.bidding (price, bid);
        }
        notifyChange();

    }
}
