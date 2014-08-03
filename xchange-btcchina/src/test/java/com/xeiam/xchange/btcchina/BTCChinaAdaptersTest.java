package com.xeiam.xchange.btcchina;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeiam.xchange.btcchina.dto.trade.BTCChinaTransaction;
import com.xeiam.xchange.btcchina.dto.trade.response.BTCChinaGetMarketDepthResponse;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.trade.LimitOrder;

public class BTCChinaAdaptersTest {

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testAdaptTransaction() {

    BTCChinaTransaction btcChinaTransaction = new BTCChinaTransaction(12158242, // id
        "sellbtc", // type
        new BigDecimal("-0.37460000"), // btc_amount
        new BigDecimal("0.00000000"), // ltc_amount
        new BigDecimal("1420.09151800"), // cny_amount
        1402922707L, // id
        "btccny" // market
    );
    Trade trade = BTCChinaAdapters.adaptTransaction(btcChinaTransaction);

    assertEquals(OrderType.ASK, trade.getType());
    assertEquals(new BigDecimal("0.37460000"), trade.getTradableAmount());
    assertEquals(CurrencyPair.BTC_CNY, trade.getCurrencyPair());
    assertEquals(new BigDecimal("3790.95"), trade.getPrice());
    assertEquals(1402922707000L, trade.getTimestamp().getTime());
    assertEquals("12158242", trade.getId());
  }

  @Test
  public void testAdaptOrderBook() throws IOException {

    BTCChinaGetMarketDepthResponse response = mapper.readValue(getClass().getResourceAsStream("dto/trade/response/getMarketDepth2.json"), BTCChinaGetMarketDepthResponse.class);
    OrderBook orderBook = BTCChinaAdapters.adaptOrderBook(response.getResult().getMarketDepth(), CurrencyPair.BTC_CNY);
    List<LimitOrder> bids = orderBook.getBids();
    List<LimitOrder> asks = orderBook.getAsks();
    assertEquals(2, bids.size());
    assertEquals(2, asks.size());

    assertEquals(new BigDecimal("99"), bids.get(0).getLimitPrice());
    assertEquals(new BigDecimal("1"), bids.get(0).getTradableAmount());
    assertEquals(CurrencyPair.BTC_CNY, bids.get(0).getCurrencyPair());
    assertEquals(OrderType.BID, bids.get(0).getType());
    
    assertEquals(new BigDecimal("98"), bids.get(1).getLimitPrice());
    assertEquals(new BigDecimal("2"), bids.get(1).getTradableAmount());
    assertEquals(CurrencyPair.BTC_CNY, bids.get(1).getCurrencyPair());
    assertEquals(OrderType.BID, bids.get(1).getType());

    assertEquals(new BigDecimal("100"), asks.get(0).getLimitPrice());
    assertEquals(new BigDecimal("0.997"), asks.get(0).getTradableAmount());
    assertEquals(CurrencyPair.BTC_CNY, asks.get(0).getCurrencyPair());
    assertEquals(OrderType.ASK, asks.get(0).getType());

    assertEquals(new BigDecimal("101"), asks.get(1).getLimitPrice());
    assertEquals(new BigDecimal("2"), asks.get(1).getTradableAmount());
    assertEquals(CurrencyPair.BTC_CNY, asks.get(1).getCurrencyPair());
    assertEquals(OrderType.ASK, asks.get(1).getType());

    assertEquals(1407060232000L, orderBook.getTimeStamp().getTime());
  }

}
