package com.huafeng.auction;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.huafeng.auction.ApplicationRunner.SNIPER_ID;


/**
 * Created by stephen on 17/6/17.
 */
@RunWith(JMock.class)
public class AuctionMessageTranslatorTest {
    private final Mockery context = new Mockery();
    private final AuctionEventListener listener = context.mock(AuctionEventListener.class);
    public static final Chat UNUSED_CHAT=null;
    private final AuctionMessageTranslator translator = new AuctionMessageTranslator(SNIPER_ID, listener);

    @Test
    public void notifiesAuctionCloseWhenCloseMessageReceived (){
        context.checking(new Expectations(){{
            oneOf(listener).auctionClosed();
        }});
        Message message = new Message();
        message.setBody("SOLVersion: 1.1; Event: CLOSE:");
        translator.processMessage(UNUSED_CHAT, message);
    }

    @Test
    public void notifiesBidDetailsWhenCurrrentPriceMessageReceivedFromOtherBidder (){
        context.checking(new Expectations(){{
            exactly(1).of(listener).currentPrice(192, 7, PriceSource.fromOtherBidder);
        }});

        Message message = new Message();
        message.setBody(
                "SOLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;"
        );
        translator.processMessage(UNUSED_CHAT, message);
    }

    @Test
    public void notifiesBidDetailsWhenCurrrentPriceMessageReceivedFromSniper (){
        context.checking(new Expectations(){{
            exactly(1).of(listener).currentPrice(234, 5, PriceSource.fromSniper);
        }});

        Message message = new Message();
        message.setBody(
                "SOLVersion: 1.1; Event: PRICE; CurrentPrice: 234; Increment: 5; Bidder: "
                + SNIPER_ID + ";");
        translator.processMessage(UNUSED_CHAT, message);
    }
}
