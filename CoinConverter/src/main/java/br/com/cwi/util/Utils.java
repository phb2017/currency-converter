package br.com.cwi.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static boolean isEmpty(String s) {
		if (isNull(s)) {
			return true;
		}
		return "".equals(s) ? true : false;
	}

	public static boolean isEmpty(Object o) {
		return isNull(o) ? true : false;
	}

	public static boolean isEmpty(Number n) {
		return isNull(n) ? true : false;
	}

	public static boolean isNull(Object o) {
		return o == null ? true : false;
	}

	public static BigDecimal convertNumberToBigDecimal(Number value) {
		return new BigDecimal(value.toString());
	}

	public static String getQuotationDate(String quotationDate)
			throws ParseException {

		String[] data = quotationDate.split("/");

		String dia = data[0];
		String mes = data[1];
		String ano = data[2];

		LocalDate localDate = LocalDate.of(Integer.valueOf(ano),
				Integer.valueOf(mes), Integer.valueOf(dia));

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();

		int somaDias = 0;
		if (dayOfWeek == DayOfWeek.SUNDAY) {
			somaDias = -2;
		} else if (dayOfWeek == DayOfWeek.SATURDAY) {
			somaDias = -1;
		}

		LocalDate amanha = localDate.plusDays(somaDias);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return amanha.format(formatter);
	}
}
