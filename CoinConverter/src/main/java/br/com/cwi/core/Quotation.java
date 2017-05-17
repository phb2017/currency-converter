package br.com.cwi.core;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import br.com.cwi.bean.QuotationBean;
import br.com.cwi.exception.ErrorCalculationException;
import br.com.cwi.exception.InvalidParameterException;
import br.com.cwi.exception.QuotationException;
import br.com.cwi.util.FileUtil;
import br.com.cwi.util.Utils;

public class Quotation {

	private static final int ZERO = 0;

	private static final String PATH = "/bacen.csv";

	public BigDecimal currencyQuotation(String from, String to, Number value,
			String quotation) throws QuotationException,
			ErrorCalculationException, InvalidParameterException, Exception {

		validateParameters(from, to, value, quotation);

		FileUtil file = new FileUtil();

		Map<String, QuotationBean> map = file.loadDataFromCsv(PATH);

		// Replace comma to dot in mask number
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);

		// verify business days
		String quotationDate = Utils.getQuotationDate(quotation);

		// mount key composite to search on Map
		String keyFrom = quotationDate + "-" + from;
		String keyTo = quotationDate + "-" + to;

		BigDecimal txVendaFrom = calculateTxVendaFrom(map, nf, keyFrom);

		// Exception the rule in the BACEN file has no BRL value, so the
		// conversion from USD to BRL will be done directly with simpler formula
		if ("USD".equalsIgnoreCase(from) && "BRL".equalsIgnoreCase(to)) {
			return txVendaFrom.multiply(new BigDecimal(value.toString()))
					.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		BigDecimal txVendaTo = calculateTxVendaTo(map, nf, keyTo);

		BigDecimal vlrConverted = calculateFinal(value, txVendaFrom, txVendaTo);

		/*
		 * DOLAR -> EURO [1 DOLAR - 3,1011] [1 EURO - 3,4050]
		 * 
		 * x - 3,1011 100 - 3,4050
		 * 
		 * 3,4050 x = 3,1011 * 100 => 91,07
		 */

		/*
		 * ZLOTY -> EURO [1 PLN - 0,8113] [1 EURO - 3,4050]
		 * 
		 * x - 0,8113 100 - 3.4050
		 * 
		 * 3,4050 x = 0,8113 * 100 => 23,82
		 */

		/*
		 * EURO -> ZLOTY [1 EURO - 3,4050] [1 PLN - 0,8113]
		 * 
		 * x - 3,4050 100 - 0,8113
		 * 
		 * 0,8113 x = 100 * 3,4050 => 419,69
		 */

		return vlrConverted;

	}

	private BigDecimal calculateFinal(Number value, BigDecimal txVendaFrom,
			BigDecimal txVendaTo) throws ErrorCalculationException {
		BigDecimal vlrConverted;
		try {
			vlrConverted = txVendaFrom.multiply(
					new BigDecimal(value.toString())).divide(txVendaTo, 2,
					BigDecimal.ROUND_HALF_UP);

		} catch (Exception e3) {
			throw new ErrorCalculationException("Error in final calculation.",
					e3);

		}
		return vlrConverted;
	}

	private BigDecimal calculateTxVendaTo(Map<String, QuotationBean> map,
			NumberFormat nf, String keyTo) throws ErrorCalculationException {
		QuotationBean quoBean;
		BigDecimal txVendaTo;
		try {
			quoBean = map.get(keyTo);
			txVendaTo = new BigDecimal(nf.parse(quoBean.getTaxaVenda())
					.toString());

			System.out.println(">>> TO..: " + quoBean.getData() + "-"
					+ quoBean.getMoeda() + "-" + txVendaTo);

		} catch (Exception e2) {
			throw new ErrorCalculationException("Error calculating txVendaTo.",
					e2);
		}
		return txVendaTo;
	}

	private BigDecimal calculateTxVendaFrom(Map<String, QuotationBean> map,
			NumberFormat nf, String keyFrom) throws ErrorCalculationException {
		QuotationBean quoBean;
		BigDecimal txVendaFrom;
		try {
			quoBean = map.get(keyFrom);
			txVendaFrom = new BigDecimal(nf.parse(quoBean.getTaxaVenda())
					.toString());

			System.out.println(">>> FROM: " + quoBean.getData() + "-"
					+ quoBean.getMoeda() + "-" + txVendaFrom);

		} catch (Exception e1) {
			throw new ErrorCalculationException(
					"Error calculating txVendaFrom.", e1);
		}
		return txVendaFrom;
	}

	@SuppressWarnings("unused")
	private void printResult(Map<String, QuotationBean> map) {
		int i = 0;
		for (Map.Entry<String, QuotationBean> entry : map.entrySet()) {
			System.out.println("VALOR: " + ++i + " - " + entry.getKey() + " - "
					+ entry.getValue());
		}
	}

	private void validateParameters(String from, String to, Number value,
			String quotation) throws InvalidParameterException {

		StringBuilder sb = new StringBuilder();

		if (Utils.isEmpty(from)) {
			sb.append("\n[FROM] parameter invalid or null");
		}

		if (Utils.isEmpty(to)) {
			sb.append("\n[TO] parameter invalid or null");
		}

		if (Utils.isEmpty(value)) {
			sb.append("\n[VALUE] parameter invalid or null");
		} else {
			validateValueGreaterThanZero(value, sb);
		}

		if (Utils.isEmpty(quotation)) {
			sb.append("\n[QUOTATION] parameter invalid or null");
		}

		if (!Utils.isEmpty(sb) && sb.length() > 0) {
			throw new InvalidParameterException(sb.toString());
		}

	}

	private void validateValueGreaterThanZero(Number value, StringBuilder sb) {
		BigDecimal valor = Utils.convertNumberToBigDecimal(value);
		if (valor.compareTo(new BigDecimal("0")) < ZERO) {
			sb.append("\n[VALUE] field value must be greater than 0");
		}
	}

}
