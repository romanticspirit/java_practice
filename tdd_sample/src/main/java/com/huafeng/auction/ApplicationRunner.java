package com.huafeng.auction;

import static com.huafeng.auction.Constant.STATUS_JOINING;
import static com.huafeng.auction.Constant.STATUS_LOST;

/**
 * Created by stephen on 17/6/16.
 */
public class ApplicationRunner {
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";


    private AuctionSniperDriver driver;

    public void startBiddingIn(final FakeAuctionServer auction){
        Thread thread = new Thread ("Test Application"){
            @Override
            public void run (){
                try{
                    Main.main(FakeAuctionServer.XMPP_HOSTNAME,
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
        driver.showSniperStatus(STATUS_JOINING);
    }

    public void showSniperHasLostAuction(){
        driver.showSniperStatus(STATUS_LOST);
    }

    public void stop(){

        if (driver != null){
            driver.dispose ();
        }
    }


}