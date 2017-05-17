package br.com.cwi.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.cwi.core.Quotation;
import br.com.cwi.exception.ErrorCalculationException;
import br.com.cwi.exception.InvalidParameterException;
import br.com.cwi.exception.QuotationException;

public class CurrencyQuotationTest {

	private static final String FROM_EXCEPTION = "\n[FROM] parameter invalid or null";
	private static final String TO_EXCEPTION = "\n[TO] parameter invalid or null";
	private static final String VALUE_EXCEPTION = "\n[VALUE] parameter invalid or null";
	private static final String QUOTATION_EXCEPTION = "\n[QUOTATION] parameter invalid or null";
	private static final String GREATER_ZERO_EXCEPTION = "\n[VALUE] field value must be greater than 0";

	Quotation quotation;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		quotation = new Quotation();
	}

	@Test
	public void testCreatequotationObject() throws Exception {
		assertNotNull(quotation);
	}

	@Test
	public void testSimpleCurrencyquotation_OnlyFromFilled()
			throws InvalidParameterException, Exception {
		exception.expect(InvalidParameterException.class);
		exception.expectMessage(TO_EXCEPTION + VALUE_EXCEPTION
				+ QUOTATION_EXCEPTION);
		quotation.currencyQuotation("USD", null, null, null);
	}

	@Test
	public void testSimpleCurrencyquotation_OnlyToFilled()
			throws InvalidParameterException, Exception {
		exception.expect(InvalidParameterException.class);
		exception.expectMessage(FROM_EXCEPTION + VALUE_EXCEPTION
				+ QUOTATION_EXCEPTION);
		quotation.currencyQuotation("", "EUR", null, null);
	}

	@Test
	public void testSimpleCurrencyquotation_OnlyValueFilled()
			throws InvalidParameterException, Exception {
		exception.expect(InvalidParameterException.class);
		exception.expectMessage(FROM_EXCEPTION + TO_EXCEPTION
				+ QUOTATION_EXCEPTION);
		quotation.currencyQuotation("", null, 100.00, null);
	}

	@Test
	public void testSimpleCurrencyquotation_OnlyQuotationFilled()
			throws InvalidParameterException, Exception {
		exception.expect(InvalidParameterException.class);
		exception
				.expectMessage(FROM_EXCEPTION + TO_EXCEPTION + VALUE_EXCEPTION);
		quotation.currencyQuotation("", null, null, "20/10/2017");
	}

	@Test
	public void testSimpleCurrencyquotation_AllParametersNull()
			throws InvalidParameterException, Exception {
		exception.expect(InvalidParameterException.class);
		exception.expectMessage(FROM_EXCEPTION + TO_EXCEPTION + VALUE_EXCEPTION
				+ QUOTATION_EXCEPTION);
		quotation.currencyQuotation(null, null, null, null);
	}

	@Test
	public void testSimpleCurrencyquotation_ValueSmallerThanZero()
			throws Exception {
		exception.expect(InvalidParameterException.class);
		exception.expectMessage(GREATER_ZERO_EXCEPTION);
		quotation.currencyQuotation("USD", "EUR", -1, "10/10/2010");
	}

	@Test
	public void testCurrencyQuotation_AllCombinations()
			throws QuotationException, ErrorCalculationException,
			InvalidParameterException, Exception {

		assertEquals(new BigDecimal("91.07"),
				quotation.currencyQuotation("USD", "EUR", 100.00, "15/05/2017"));

		assertEquals(new BigDecimal("23.83"),
				quotation.currencyQuotation("PLN", "EUR", 100.00, "15/05/2017"));

		assertEquals(new BigDecimal("310.11"),
				quotation.currencyQuotation("USD", "BRL", 100.00, "15/05/2017"));
	}

	@Test
	public void testCurrencyQuotation_WithDayOfWeekSaturday()
			throws QuotationException, ErrorCalculationException,
			InvalidParameterException, Exception {

		assertEquals(new BigDecimal("117.83"),
				quotation.currencyQuotation("GBP", "EUR", 100.00, "13/05/2017"));

	}

	@Test
	public void testCurrencyQuotation_WithDayOfWeekSunday()
			throws QuotationException, ErrorCalculationException,
			InvalidParameterException, Exception {

		assertEquals(new BigDecimal("117.83"),
				quotation.currencyQuotation("GBP", "EUR", 100.00, "14/05/2017"));

	}

}
