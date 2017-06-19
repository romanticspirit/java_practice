package com.huafeng.auction;

import com.objogate.exception.Defect;

/**
 * Created by stephen on 17/6/18.
 */
public enum SniperState {
    JOINNING{
        @Override
        public SniperState whenAuctionCLosed (){
            return LOST;
        }
    },
    BIDDING{
        @Override
        public SniperState whenAuctionCLosed (){
            return LOST;
        }
    },
    WINNING{
        @Override
        public SniperState whenAuctionCLosed (){
            return WON;
        }
    },
    LOST,
    WON;

    public SniperState whenAuctionCLosed() {
        throw new Defect("Auction is already closed.");
    }
}
