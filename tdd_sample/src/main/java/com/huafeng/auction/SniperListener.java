package com.huafeng.auction;

import java.util.EventListener;

/**
 * Created by stephen on 17/6/18.
 */
public interface SniperListener extends EventListener{
    void sniperLost ();

    void sniperBidding();

    void sniperWinning();
}
