package com.huafeng.auction;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.jivesoftware.smack.Chat;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.junit.Test;

import static com.huafeng.auction.SniperState.*;
import static javafx.beans.binding.Bindings.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by stephen on 17/6/18.
 */
public class AuctionSniperTest {
    private final Mockery context = new Mockery();
    private final Auction auction = context.mock(Auction.class);
    private final SniperListener sniperListener = context.mock(SniperListener.class);
    private final String ITEM_ID = "item_id";
    public static final Chat UNUSED_CHAT=null;

    private AuctionSniper sniper = new AuctionSniper (auction, sniperListener, ITEM_ID);

    private final States sniperState = context.states("sniper");

    @Test
    public void bidsHigherAndReportsBiddingWhenNewPriceArrives (){
        final int price = 1001;
        final int increment = 25;
        int bid = price+increment;
        context.checking(new Expectations(){{
            one(auction).bid(bid);
            atLeast(1).of(sniperListener).sniperStateChanged(
                    with(aSniperThatIs(BIDDING)));
            then(sniperState.is("bidding"));
            atLeast(1).of(sniperListener).sniperStateChanged(new SniperSnapshot(ITEM_ID, price, bid, BIDDING)); when(sniperState.is("bidding"));
        }});

        sniper.currentPrice(price, increment, PriceSource.fromOtherBidder);
    }

    private Matcher<SniperSnapshot> aSniperThatIs(final SniperState state){
        return new FeatureMatcher<SniperSnapshot, SniperState>(equalTo(state),
                "sniper that is", "was") {
            @Override
            protected SniperState featureValueOf(SniperSnapshot actual) {
                return actual.state;
            }
        };
    }

    @Test
    public void reportsIsWinningWithCurrentPricesFromSniper (){
        context.checking(new Expectations(){{
            ignoring(auction);
            allowing(sniperListener).sniperStateChanged(with(aSniperThatIs(BIDDING)));
            then(sniperState.is("bidding"));
            atLeast(1).of(sniperListener).sniperStateChanged( new SniperSnapshot(ITEM_ID, 135, 135, SniperState.WINNING)); when(sniperState.is("bidding"));
        }});

        sniper.currentPrice(123, 12, PriceSource.fromOtherBidder);
        sniper.currentPrice(135, 45, PriceSource.fromSniper);
    }

    @Test
    public void reportLostIfAuctionClosesImmediately(){
        context.checking(new Expectations(){{
            one(sniperListener).sniperStateChanged(new SniperSnapshot(ITEM_ID, 0, 0, LOST) );
        }});

        sniper.auctionClosed();
    }

    @Test
    public void reportsLostIfAuctionClosesWhenBidding (){
        context.checking(new Expectations(){{
            ignoring(auction);
            allowing(sniperListener).sniperStateChanged(with(any(SniperSnapshot.class)));
            then(sniperState.is("bidding"));

            atLeast(1).of(sniperListener).sniperStateChanged(new SniperSnapshot(ITEM_ID, 123, 168, LOST));
            when(sniperState.is("bidding"));
        }});

        sniper.currentPrice(123, 45, PriceSource.fromOtherBidder);
        sniper.auctionClosed();
    }

    @Test
    public void reportsWonIfAuctionClosesWhenWinning(){
        context.checking(new Expectations(){{
            ignoring(auction);
            allowing(sniperListener).sniperStateChanged(new SniperSnapshot(ITEM_ID, 135, 135, WINNING)); then(sniperState.is("winning"));
            atLeast(1).of(sniperListener).sniperStateChanged(new SniperSnapshot(ITEM_ID, 135, 135, WON)); when(sniperState.is("winning"));

        }});
        //sniper.currentPrice(123, 12, PriceSource.fromOtherBidder);
        sniper.currentPrice(135, 45, PriceSource.fromSniper);
        sniper.auctionClosed();
    }

}
