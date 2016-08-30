package net.sistransito.mobile.equipamentos;


public class AutoEquipmentEntry {
	private String DESCRICAO, MARCA, MODELO, VALIDADE,NUMERO_SERIE;

	public String getNUMERO_SERIE() {
		return NUMERO_SERIE;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getMARCA() {
		return MARCA;
	}

	public String getMODELO() {
		return MODELO;
	}

	public String getVALIDADE() {
		return VALIDADE;
	}

	public AutoEquipmentEntry(String dESCRICAO, String mARCA, String mODELO,
			String vALIDADE, String nUMERO_SERIE) {
		super();
		DESCRICAO = dESCRICAO;
		MARCA = mARCA;
		MODELO = mODELO;
		VALIDADE = vALIDADE;
		NUMERO_SERIE=nUMERO_SERIE;
	}

	

}
