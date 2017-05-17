package br.com.cwi.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.cwi.bean.QuotationBean;

public class FileUtil {

	public Map<String, QuotationBean> loadDataFromCsv(String fileName) {

		final String DELIMITER = ";";

		Map<String, QuotationBean> map = new HashMap<String, QuotationBean>();

		BufferedReader br = null;

		String line = "";

		try {

			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null) {

				String[] array = line.split(DELIMITER);

				QuotationBean bean = new QuotationBean();
				bean.setData(array[0]);
				bean.setCodigoMoeda(array[1]);
				bean.setTipo(array[2]);
				bean.setMoeda(array[3]);
				bean.setTaxaCompra(array[4]);
				bean.setTaxaVenda(array[5]);
				bean.setParidadeCompra(array[6]);
				bean.setParidadeVenda(array[7]);

				map.put(bean.getData() + "-" + bean.getMoeda(), bean);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return map;

	}
}
