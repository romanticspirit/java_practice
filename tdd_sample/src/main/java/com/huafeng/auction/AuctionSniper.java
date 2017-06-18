package com.huafeng.auction;

/**
 * Created by stephen on 17/6/18.
 */
public class AuctionSniper implements AuctionEventListener{

    private final SniperListener sniperListener;
    private final Auction   auction;
    private boolean isWinning =false;

    public AuctionSniper(Auction auction, SniperListener sniperListener) {
        this.auction = auction;
        this.sniperListener = sniperListener;
    }

    @Override
    public void auctionClosed() {
        if(isWinning){
            sniperListener.sniperWon();
        }else {
            sniperListener.sniperLost();
        }
    }

    @Override
    public void currentPrice(int price, int increment, PriceSource source) {
        isWinning = source == PriceSource.fromSniper;
        if (isWinning){
            sniperListener.sniperWinning();
        }else{
            auction.bid(price+increment);
            sniperListener.sniperBidding();
        }

    }
}
