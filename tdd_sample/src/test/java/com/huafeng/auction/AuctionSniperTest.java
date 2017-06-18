package com.huafeng.auction;

import org.jivesoftware.smack.Chat;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/**
 * Created by stephen on 17/6/18.
 */
public class AuctionSniperTest {
    private final Mockery context = new Mockery();
    private final Auction auction = context.mock(Auction.class);
    private final SniperListener sniperListener = context.mock(SniperListener.class);
    public static final Chat UNUSED_CHAT=null;

    private AuctionSniper sniper = new AuctionSniper (auction, sniperListener);


    @Test
    public void reportsLostWhenAuctionCloses(){
        context.checking(new Expectations(){{
            one(sniperListener).sniperLost();
        }});

        sniper.auctionClosed();
    }

    @Test
    public void bidsHigherAndReportsBiddingWhenNewPriceArrives (){
        final int price = 1001;
        final int increment = 25;
        context.checking(new Expectations(){{
            one(auction).bid(price+increment);
            atLeast(1).of(sniperListener).sniperBidding();
        }});

        sniper.currentPrice(price, increment);
    }
}
