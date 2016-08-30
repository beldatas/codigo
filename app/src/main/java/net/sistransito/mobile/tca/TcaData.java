package net.sistransito.mobile.tca;

import android.content.Context;
import android.database.Cursor;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.TCADatabaseHelper;
import net.sistrnsitomobile.R;

import java.io.Serializable;

public class TcaData implements Serializable {

	// NOME, CNH_PPD, CPF, ENDERECO, BAIRRO, MUNICIPIO,MUNICIPIO_UF, PLACA,
	// PLACA_UF, MARCA_MODELO, LOCAL_DA_OCORRENCIA,DATA, HORA, N_AUTO,
	// CONDUTOR_ENVOLVEU_SE_EM_ACIDENTE_DE_TRANSITO,CONDUTOR_DECLARA_TER_INGERIDO_BEBIDA_ALCOOLICA,CONDUTOR_DECLARA_TER_FEITO_USO_DE_SUBSTANCIA_TOXICA,
	// O_CONDUTOR_APRESENTA_SINAIS_DE,EM_SUA_ATITUDE_OCORRE, SABE_ONDE_ESTA,
	// SABE_A_DATA_E_A_HORA,SABE_SEU_ENDERECO,
	// LEMBRA_DOS_ATOS_COMETIDOS,EM_RELACO_A_SUA_CAPACIDADE_MOTORA_E_VERBAL_OCORRE,
	// CONCLUSAO;

	private static final String tca_id = "tca_id";
	private static final long serialVersionUID = 14393860L;

	public static String getTcaId() {
		return tca_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String nome_do_condutor, cnh_ppd, cpf, endereco, bairro, municipio,
			municipio_uf, placa, placa_uf, marca_modelo,
			condutor_envolveu_se_em_acidente_de_transito,
			condutor_declara_ter_ingerido_bebida_alcoolica,
			condutor_declara_ter_feito_uso_de_substancia_toxica,
			o_condutor_apresenta_sinais_de, em_sua_atitude_ocorre,
			sabe_onde_esta, sabe_a_data_e_a_hora, sabe_seu_endereco,
			lembra_dos_atos_cometidos,
			em_relaco_a_sua_capacidade_motora_e_verbal_ocorre, conclusao,
			numero_auto, numero_tca, hora_ingeriu_alcool, data_ingeriu_alcool,
			hora_ingeriu_substancia, data_ingeriu_substancia;

	public String getHora_ingeriu_alcool() {
		return hora_ingeriu_alcool;
	}

	public String getData_ingeriu_alcool() {
		return data_ingeriu_alcool;
	}

	public String getHora_ingeriu_substancia() {
		return hora_ingeriu_substancia;
	}

	public String getData_ingeriu_substancia() {
		return data_ingeriu_substancia;
	}

	public void setHora_ingeriu_alcool(String hora_ingeriu_alcool) {
		this.hora_ingeriu_alcool = hora_ingeriu_alcool;
	}

	public void setData_ingeriu_alcool(String data_ingeriu_alcool) {
		this.data_ingeriu_alcool = data_ingeriu_alcool;
	}

	public void setHora_ingeriu_substancia(String hora_ingeriu_substancia) {
		this.hora_ingeriu_substancia = hora_ingeriu_substancia;
	}

	public void setData_ingeriu_substancia(String data_ingeriu_substancia) {
		this.data_ingeriu_substancia = data_ingeriu_substancia;
	}

	public String getNumero_auto() {
		return numero_auto;
	}

	public String getNumero_tca() {
		return numero_tca;
	}

	public void setNumero_auto(String numero_auto) {
		this.numero_auto = numero_auto;
	}

	public void setNumero_tca(String numero_tca) {
		this.numero_tca = numero_tca;
	}

	public String getNome_do_condutor() {
		return nome_do_condutor;
	}

	public String getCnh_ppd() {
		return cnh_ppd;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getMunicipio_uf() {
		return municipio_uf;
	}

	public String getPlaca() {
		return placa;
	}

	public String getPlaca_uf() {
		return placa_uf;
	}

	public String getMarca_modelo() {
		return marca_modelo;
	}

	public String getCondutor_envolveu_se_em_acidente_de_transito() {
		return condutor_envolveu_se_em_acidente_de_transito;
	}

	public String getCondutor_declara_ter_ingerido_bebida_alcoolica() {
		return condutor_declara_ter_ingerido_bebida_alcoolica;
	}

	public String getCondutor_declara_ter_feito_uso_de_substancia_toxica() {
		return condutor_declara_ter_feito_uso_de_substancia_toxica;
	}

	public String getO_condutor_apresenta_sinais_de() {
		return o_condutor_apresenta_sinais_de;
	}

	public String getEm_sua_atitude_ocorre() {
		return em_sua_atitude_ocorre;
	}

	public String getSabe_onde_esta() {
		return sabe_onde_esta;
	}

	public String getSabe_a_data_e_a_hora() {
		return sabe_a_data_e_a_hora;
	}

	public String getSabe_seu_endereco() {
		return sabe_seu_endereco;
	}

	public String getLembra_dos_atos_cometidos() {
		return lembra_dos_atos_cometidos;
	}

	public String getEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre() {
		return em_relaco_a_sua_capacidade_motora_e_verbal_ocorre;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setNome_do_condutor(String nome_do_condutor) {
		this.nome_do_condutor = nome_do_condutor;
	}

	public void setCnh_ppd(String cnh_ppd) {
		this.cnh_ppd = cnh_ppd;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public void setMunicipio_uf(String municipio_uf) {
		this.municipio_uf = municipio_uf;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setPlaca_uf(String placa_uf) {
		this.placa_uf = placa_uf;
	}

	public void setMarca_modelo(String marca_modelo) {
		this.marca_modelo = marca_modelo;
	}

	public void setCondutor_envolveu_se_em_acidente_de_transito(
			String condutor_envolveu_se_em_acidente_de_transito) {
		this.condutor_envolveu_se_em_acidente_de_transito = condutor_envolveu_se_em_acidente_de_transito;
	}

	public void setCondutor_declara_ter_ingerido_bebida_alcoolica(
			String condutor_declara_ter_ingerido_bebida_alcoolica) {
		this.condutor_declara_ter_ingerido_bebida_alcoolica = condutor_declara_ter_ingerido_bebida_alcoolica;
	}

	public void setCondutor_declara_ter_feito_uso_de_substancia_toxica(
			String condutor_declara_ter_feito_uso_de_substancia_toxica) {
		this.condutor_declara_ter_feito_uso_de_substancia_toxica = condutor_declara_ter_feito_uso_de_substancia_toxica;
	}

	public void setO_condutor_apresenta_sinais_de(
			String o_condutor_apresenta_sinais_de) {
		this.o_condutor_apresenta_sinais_de = o_condutor_apresenta_sinais_de;
	}

	public void setEm_sua_atitude_ocorre(String em_sua_atitude_ocorre) {
		this.em_sua_atitude_ocorre = em_sua_atitude_ocorre;
	}

	public void setSabe_onde_esta(String sabe_onde_esta) {
		this.sabe_onde_esta = sabe_onde_esta;
	}

	public void setSabe_a_data_e_a_hora(String sabe_a_data_e_a_hora) {
		this.sabe_a_data_e_a_hora = sabe_a_data_e_a_hora;
	}

	public void setSabe_seu_endereco(String sabe_seu_endereco) {
		this.sabe_seu_endereco = sabe_seu_endereco;
	}

	public void setLembra_dos_atos_cometidos(String lembra_dos_atos_cometidos) {
		this.lembra_dos_atos_cometidos = lembra_dos_atos_cometidos;
	}

	public void setEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre(
			String em_relaco_a_sua_capacidade_motora_e_verbal_ocorre) {
		this.em_relaco_a_sua_capacidade_motora_e_verbal_ocorre = em_relaco_a_sua_capacidade_motora_e_verbal_ocorre;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public TcaData() {

		nome_do_condutor = cnh_ppd = cpf = endereco = bairro = municipio = municipio_uf = placa = placa_uf = marca_modelo = condutor_envolveu_se_em_acidente_de_transito = condutor_declara_ter_ingerido_bebida_alcoolica = condutor_declara_ter_feito_uso_de_substancia_toxica = o_condutor_apresenta_sinais_de = em_sua_atitude_ocorre = sabe_onde_esta = sabe_a_data_e_a_hora = sabe_seu_endereco = lembra_dos_atos_cometidos = em_relaco_a_sua_capacidade_motora_e_verbal_ocorre = conclusao = numero_auto = numero_tca = hora_ingeriu_alcool = data_ingeriu_alcool = hora_ingeriu_substancia = data_ingeriu_substancia = "";

	}

	public String getTCAViewData(Context context) {
		String tca_data = getNomeViewData(context)
				+ getCNH_PPDViewData(context)
				+ getFilterViewData(context, false);
		return tca_data;

	}

	private String getNewline() {
		return AppConstants.NEW_LINE;
	}

	private String getNewline_2() {
		return AppConstants.NEW_LINE + AppConstants.NEW_LINE;
	}

	public void setTCADataFromCursor(Cursor myCursor) {

		setNome_do_condutor(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.NOME_DO_CONDUTOR)));
		setCnh_ppd(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.CNH_PPD)));
		setCpf(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.CPF)));

		setEndereco(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.ENDERECO)));

		setBairro(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.BAIRRO)));

		setMunicipio(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.MUNICIPIO)));

		setMunicipio_uf(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.MUNICIPIO_UF)));

		setPlaca(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.PLACA)));

		setPlaca_uf(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.PLACA_UF)));

		setMarca_modelo(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.MARCA_MODELO)));

		setCondutor_envolveu_se_em_acidente_de_transito(myCursor
				.getString(myCursor
						.getColumnIndex(TCADatabaseHelper.CONDUTOR_ENVOLVEU_SE_EM_ACIDENTE_DE_TRANSITO)));

		setCondutor_declara_ter_ingerido_bebida_alcoolica(myCursor
				.getString(myCursor
						.getColumnIndex(TCADatabaseHelper.CONDUTOR_DECLARA_TER_INGERIDO_BEBIDA_ALCOOLICA)));

		setData_ingeriu_alcool(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.DATA_INGERIU_ALCOOL)));

		setHora_ingeriu_alcool(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.HORA_INGERIU_ALCOOL)));

		setCondutor_declara_ter_feito_uso_de_substancia_toxica(myCursor
				.getString(myCursor
						.getColumnIndex(TCADatabaseHelper.CONDUTOR_DECLARA_TER_FEITO_USO_DE_SUBSTANCIA_TOXICA)));

		
		setData_ingeriu_substancia(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.DATA_INGERIU_SUBSTANCIA)));

		setHora_ingeriu_substancia(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.HORA_INGERIU_SUBSTANCIA)));
		
		
		setO_condutor_apresenta_sinais_de(myCursor
				.getString(myCursor
						.getColumnIndex(TCADatabaseHelper.O_CONDUTOR_APRESENTA_SINAIS_DE)));

		setEm_sua_atitude_ocorre(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.EM_SUA_ATITUDE_OCORRE)));

		setSabe_onde_esta(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.SABE_ONDE_ESTA)));

		setSabe_a_data_e_a_hora(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.SABE_A_DATA_E_A_HORA)));

		setSabe_seu_endereco(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.SABE_SEU_ENDERECO)));

		setLembra_dos_atos_cometidos(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.LEMBRA_DOS_ATOS_COMETIDOS)));

		setEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre(myCursor
				.getString(myCursor
						.getColumnIndex(TCADatabaseHelper.EM_RELACO_A_SUA_CAPACIDADE_MOTORA_E_VERBAL_OCORRE)));
		setConclusao(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.CONCLUSAO)));

		setNumero_tca(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.NUMERO_TCA)));
		setNumero_auto(myCursor.getString(myCursor
				.getColumnIndex(TCADatabaseHelper.NUMERO_AUTO)));

	}

	public String getTCAListViewData(Context context) {
		return getFilterViewData(context, true);

	}

	public String getPlacaViewData(Context context) {
		return context.getResources().getString(R.string.placa) + getNewline()
				+ placa + getNewline_2();

	}

	public String getNomeViewData(Context context) {
		return context.getResources().getString(R.string.tca_nome)
				+ getNewline() + nome_do_condutor + getNewline_2();

	}

	public String getCNH_PPDViewData(Context context) {
		return context.getResources().getString(R.string.tca_CNH_PPD)
				+ getNewline() + cnh_ppd + getNewline_2();

	}

	private String getFilterViewData(Context context, boolean isLister) {

		String tca = context.getResources().getString(R.string.tca_CPF)
				+ getNewline() + cpf + getNewline_2()
				+ context.getResources().getString(R.string.tca_endereco)
				+ getNewline() + endereco + getNewline_2()
				+ context.getResources().getString(R.string.tca_bairro)
				+ getNewline() + bairro + getNewline_2()
				+ context.getResources().getString(R.string.tca_municipio)
				+ getNewline() + municipio + getNewline_2()
				+ context.getResources().getString(R.string.tca_UF)
				+ getNewline() + municipio_uf + getNewline_2();

		// if (!isLister) {
		tca += getPlacaViewData(context);
		// }

		tca += context.getResources().getString(R.string.tca_UF)
				+ getNewline()
				+ placa_uf
				+ getNewline_2()
				+ context.getResources().getString(R.string.tca_condutor_1)
				+ getNewline()
				+ condutor_envolveu_se_em_acidente_de_transito
				+ getNewline_2()
				+ context.getResources().getString(R.string.tca_condutor_2)
				+ getNewline()
				+ condutor_declara_ter_ingerido_bebida_alcoolica
				+ getNewline()
				+ context.getResources().getString(R.string.data)
				+ getNewline()
				+ data_ingeriu_alcool
				+ getNewline()
				+ context.getResources().getString(R.string.hora)
				+ getNewline()
				+ hora_ingeriu_alcool
				+ getNewline_2()
				+ context.getResources().getString(R.string.tca_condutor_3)
				+ getNewline()
				+ condutor_declara_ter_feito_uso_de_substancia_toxica
				+ getNewline()
				+ context.getResources().getString(R.string.data)
				+ getNewline()
				+ data_ingeriu_substancia
				+ getNewline()
				+ context.getResources().getString(R.string.hora)
				+ getNewline()
				+ hora_ingeriu_substancia
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_o_condutor_apresenta)
				+ getNewline()
				+ o_condutor_apresenta_sinais_de
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_em_sua_atitude_ocorre)
				+ getNewline()
				+ em_sua_atitude_ocorre
				+ getNewline_2()
				+ context.getResources().getString(R.string.tca_sabe_onde_esta)
				+ getNewline()
				+ sabe_onde_esta
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_sabe_a_data_e_a_hora)
				+ getNewline()
				+ sabe_a_data_e_a_hora
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_sabe_seu_enderecao)
				+ getNewline()
				+ sabe_seu_endereco
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_lembra_dos_atos_cometidos)
				+ getNewline()
				+ lembra_dos_atos_cometidos
				+ getNewline_2()
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.tca_em_relacao_a_sua_verbal_ocorre)
				+ getNewline()
				+ em_relaco_a_sua_capacidade_motora_e_verbal_ocorre
				+ getNewline_2() + getNewline_2()
				+ context.getResources().getString(R.string.tca_se_constatei)
				+ getNewline() + conclusao + getNewline_2()
				+ context.getResources().getString(R.string.numero_tca)
				+ getNewline() + numero_tca + getNewline_2();

		return tca;

	}

}
