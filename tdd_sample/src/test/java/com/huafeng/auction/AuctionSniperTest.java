package com.huafeng.auction;

import org.jivesoftware.smack.Chat;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
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

    private final States sniperState = context.states("sniper");

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

        sniper.currentPrice(price, increment, PriceSource.fromOtherBidder);
    }

    @Test
    public void reportsIsWinningWithCurrentPricesFromSniper (){
        context.checking(new Expectations(){{
            atLeast(1).of(sniperListener).sniperWinning();
        }});

        sniper.currentPrice(123, 45, PriceSource.fromSniper);
    }

    @Test
    public void reportLostIfAuctionClosesImmediately(){
        context.checking(new Expectations(){{
            atLeast(1).of(sniperListener).sniperLost();
        }});

        sniper.auctionClosed();
    }

    @Test
    public void reportsLostIfAuctionClosesWhenBidding (){
        context.checking(new Expectations(){{
            ignoring(auction);
            allowing(sniperListener).sniperBidding();
            then(sniperState.is("bidding"));

            atLeast(1).of(sniperListener).sniperLost();
            when(sniperState.is("bidding"));
        }});

        sniper.currentPrice(123, 45, PriceSource.fromOtherBidder);
        sniper.auctionClosed();
    }

    @Test
    public void reportsWonIfAuctionClosesWhenWinning(){
        context.checking(new Expectations(){{
            ignoring(auction);
            allowing(sniperListener).sniperWinning(); then(sniperState.is("winning"));
            atLeast(1).of(sniperListener).sniperWon(); when(sniperState.is("winning"));

        }});
        sniper.currentPrice(123, 45, PriceSource.fromSniper);
        sniper.auctionClosed();
    }

}
