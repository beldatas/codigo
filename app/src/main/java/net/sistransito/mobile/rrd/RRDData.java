package net.sistransito.mobile.rrd;

import android.content.Context;
import android.database.Cursor;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.RRDDatabaseHelper;
import net.sistrnsitomobile.R;

import java.io.Serializable;

public class RRDData implements Serializable {

	private static final String rrd_id = "rrd_id";
	private static final long serialVersionUID = 14393750L;
	private String id_field, n_auto_de_infracao, nome_do_condutor, documento,
			n_crlv, placa, uf, qtd_de_dias_para_regularizacao,
			motivo_para_recolhimento, numero_rrd, n_registro, validade;

	public String getNumero_rrd() {
		return numero_rrd;
	}

	public void setNumero_rrd(String numero_rrd) {
		this.numero_rrd = numero_rrd;
	}

	public static String getRRDId() {
		return rrd_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId_field() {
		return id_field;
	}

	public String getN_auto_de_infracao() {
		return n_auto_de_infracao;
	}

	public String getNome_do_condutor() {
		return nome_do_condutor;
	}

	public String getDocumento() {
		return documento;
	}

	public String getN_crlv() {
		return n_crlv;
	}

	public String getPlaca() {
		return placa;
	}

	public String getUf() {
		return uf;
	}

	public String getQtd_de_dias_para_regularizacao() {
		return qtd_de_dias_para_regularizacao;
	}

	public String getMotivo_para_recolhimento() {
		return motivo_para_recolhimento;
	}

	public void setId_field(String id_field) {
		this.id_field = id_field;
	}

	public void setN_auto_de_infracao(String n_auto_de_infracao) {
		this.n_auto_de_infracao = n_auto_de_infracao;
	}

	public void setNome_do_condutor(String nome_do_condutor) {
		this.nome_do_condutor = nome_do_condutor;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setN_crlv(String n_crlv) {
		this.n_crlv = n_crlv;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setQtd_de_dias_para_regularizacao(
			String qtd_de_dias_para_regularizacao) {
		this.qtd_de_dias_para_regularizacao = qtd_de_dias_para_regularizacao;
	}

	public void setMotivo_para_recolhimento(String motivo_para_recolhimento) {
		this.motivo_para_recolhimento = motivo_para_recolhimento;
	}

	public RRDData() {
		id_field = n_auto_de_infracao = nome_do_condutor = documento = n_crlv = placa = n_registro = validade = uf = qtd_de_dias_para_regularizacao = motivo_para_recolhimento = numero_rrd = "";

	}

	public String getN_registro() {
		return n_registro;
	}

	public String getValidade() {
		return validade;
	}

	public void setN_registro(String n_registro) {
		this.n_registro = n_registro;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	private String getNewline() {
		return AppConstants.NEW_LINE;
	}

	private String getNewline_2() {
		return AppConstants.NEW_LINE + AppConstants.NEW_LINE;
	}

	public void setRRDDataFromCursor(Cursor myCursor) {

		setN_auto_de_infracao(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.N_AUTO_DE_INFRACAO)));

		setNome_do_condutor(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.NOME_DO_CONDUTOR)));
		
		setDocumento(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.DOCUMENTO)));
		setN_crlv(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.N_CRLV)));
		
		setPlaca(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.PLACA)));

		setN_registro(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.N_REGISTRO)));

		setValidade(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.VALIDADE)));

		setUf(myCursor.getString(myCursor.getColumnIndex(RRDDatabaseHelper.UF)));
		setMotivo_para_recolhimento(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.MOTIVO_PARA_RECOLHIMENTO)));
		setQtd_de_dias_para_regularizacao(myCursor
				.getString(myCursor
						.getColumnIndex(RRDDatabaseHelper.QTD_DE_DISA_PARA_REGULARIZACAO)));
		setNumero_rrd(myCursor.getString(myCursor
				.getColumnIndex(RRDDatabaseHelper.NUMERO_RRD)));
	}

	public String getRRDViewData(Context context) {
		String rrd = "";
		return rrd;
	}

	public String getRRDListViewData(Context context) {
		String rrd =

		context.getResources().getString(R.string.rrd_numero_auto)
				+ getNewline()
				+ n_auto_de_infracao
				+ getNewline_2()
				+ context.getResources().getString(R.string.rrd_recebemos_de)
				+ getNewline()
				+ nome_do_condutor
				+ getNewline_2()
				+ context.getResources().getString(R.string.rrd_numero_crlv)
				+ getNewline()
				+ n_crlv
				+ getNewline_2()
				+ context.getResources().getString(R.string.rrd_numero_registro)
				+ getNewline()
				+ n_registro
				+ getNewline_2()
				+ context.getResources().getString(R.string.rrd_validade)
				+ getNewline()
				+ validade
				+ getNewline_2()
				+ context.getResources().getString(R.string.rrd_uf)
				+ getNewline()
				+ uf
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.rrd_qtd_de_dias_para_regularizacao)
				+ getNewline()
				+ qtd_de_dias_para_regularizacao
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.rrd_motivo_para_recolhimento) + getNewline()
				+ motivo_para_recolhimento + getNewline_2()
				+ context.getResources().getString(R.string.numero_rrd)
				+ getNewline() + numero_rrd;

		return rrd;

	}
}
