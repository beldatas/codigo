package net.sistransito.mobile.autoinfracao;

public class AutoObjeto {
	private static DadosDoAuto dadosDoAuto;

	private AutoObjeto() {
		dadosDoAuto = new DadosDoAuto();
	}

	public static DadosDoAuto getAutoDeOject() {
		return dadosDoAuto;
	}

	public static void setAutoDeObject(DadosDoAuto data) {
		dadosDoAuto = data;
	}
}
