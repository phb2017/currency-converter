package br.com.cwi.bean;

public class QuotationBean {

	private String data;
	private String codigoMoeda;
	private String tipo;
	private String moeda;
	private String taxaCompra;
	private String taxaVenda;
	private String paridadeCompra;
	private String paridadeVenda;

	public QuotationBean() {
	}

	public QuotationBean(String data, String codigoMoeda, String tipo,
			String moeda, String taxaCompra, String taxaVenda,
			String paridadeCompra, String paridadeVenda) {
		super();
		this.data = data;
		this.codigoMoeda = codigoMoeda;
		this.tipo = tipo;
		this.moeda = moeda;
		this.taxaCompra = taxaCompra;
		this.taxaVenda = taxaVenda;
		this.paridadeCompra = paridadeCompra;
		this.paridadeVenda = paridadeVenda;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCodigoMoeda() {
		return codigoMoeda;
	}

	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getTaxaCompra() {
		return taxaCompra;
	}

	public void setTaxaCompra(String taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	public String getTaxaVenda() {
		return taxaVenda;
	}

	public void setTaxaVenda(String taxaVenda) {
		this.taxaVenda = taxaVenda;
	}

	public String getParidadeCompra() {
		return paridadeCompra;
	}

	public void setParidadeCompra(String paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}

	public String getParidadeVenda() {
		return paridadeVenda;
	}

	public void setParidadeVenda(String paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}

}
