/**
 * The MIT License
 * Copyright (c) 2012 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.xeiam.xchange.btcchina.dto.trade.request;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * @author Joe Zhou
 */
public class BTCChinaSellOrderRequestTest {

  @Test
  public void testLimitPriceOrder() {

    BTCChinaSellOrderRequest request = new BTCChinaSellOrderRequest(new BigDecimal("0.01"), new BigDecimal("1"), "BTCCNY");
    assertEquals("sellOrder2", request.getMethod());
    assertEquals("[0.01,1,\"BTCCNY\"]", request.getParams());
  }

  @Test
  public void testMarketOrder() {

    BTCChinaSellOrderRequest request = new BTCChinaSellOrderRequest(null, new BigDecimal("1"), "BTCCNY");
    assertEquals("sellOrder2", request.getMethod());
    assertEquals("[null,1,\"BTCCNY\"]", request.getParams());
  }

}