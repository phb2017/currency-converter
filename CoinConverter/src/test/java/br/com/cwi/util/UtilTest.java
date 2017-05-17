package br.com.cwi.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testQuotationDate_WithSaturday() throws Exception {
		assertEquals("12/05/2017", Utils.getQuotationDate("13/05/2017"));
	}

	@Test
	public void testQuotationDate_WithSunday() throws Exception {
		assertEquals("12/05/2017", Utils.getQuotationDate("14/05/2017"));
	}

	@Test
	public void testQuotationDate_BetweenMondayAndFriday() throws Exception {
		assertEquals("15/05/2017", Utils.getQuotationDate("15/05/2017"));
		assertEquals("16/05/2017", Utils.getQuotationDate("16/05/2017"));
		assertEquals("17/05/2017", Utils.getQuotationDate("17/05/2017"));
		assertEquals("18/05/2017", Utils.getQuotationDate("18/05/2017"));
		assertEquals("19/05/2017", Utils.getQuotationDate("19/05/2017"));
	}

}
