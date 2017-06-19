package com.huafeng.auction;

import com.huafeng.auction.ui.MainWindow;

import static com.huafeng.auction.Constant.*;
import static com.huafeng.auction.FakeAuctionServer.XMPP_HOSTNAME;

/**
 * Created by stephen on 17/6/16.
 */
public class ApplicationRunner {
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";

    public static final String SNIPER_XMPP_ID = SNIPER_ID + "@" + XMPP_HOSTNAME + "/Auction";

    private String itemId;


    private AuctionSniperDriver driver;

    public void startBiddingIn(final FakeAuctionServer auction){
        itemId = auction.getItemId();
        Thread thread = new Thread ("Test Application"){
            @Override
            public void run (){
                try{
                    Main.main(XMPP_HOSTNAME,
                            SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        };

        thread.setDaemon(true);
        thread.start();

        driver = new AuctionSniperDriver(1000);
        driver.hasTitle(MainWindow.APPLICATION_TITLE);
        driver.hasColmnTitles ();
        driver.showSniperStatus(itemId, 0, 0, STATUS_JOINING);
    }

    public void showSniperHasLostAuction(int lastPrice, int bid){
        driver.showSniperStatus(itemId, lastPrice, bid, STATUS_LOST);
    }

    public void stop(){

        if (driver != null){
            driver.dispose ();
        }
    }


    public void hasShownSniperIsBidding(int lastPrice, int lastBid) {
        driver.showSniperStatus(itemId, lastPrice, lastBid, STATUS_BIDDING);
    }

    public void hasShownSniperIsWinning(int winningBid) {
        driver.showSniperStatus(itemId, winningBid, winningBid,STATUS_WINNING);
    }

    public void showSniperHasWonAuction(int lastPrice) {
        driver.showSniperStatus(itemId, lastPrice, lastPrice, STATUS_WON);
    }
}
