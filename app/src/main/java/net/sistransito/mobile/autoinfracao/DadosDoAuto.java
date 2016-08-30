package net.sistransito.mobile.autoinfracao;

import android.content.Context;
import android.database.Cursor;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.AutoInfracaoDatabaseHelper;
import net.sistrnsitomobile.R;

import java.io.Serializable;

public class DadosDoAuto implements Serializable {
	public boolean isStoreFullData;
	public boolean isDataisNull=false;
	public boolean isDataisNull() {
		return isDataisNull;
	}

	public void setDataisNull(boolean isDataisNull) {
		this.isDataisNull = isDataisNull;
	}

	public static final String Id_Auto = "auto_de_id";
	public static final long serialVersionUID = 14393745L;
	public String campo_id, placa, modelo_do_veiculo, pais, chassi,
			cor_do_veiculo, especie, categoria, nome_do_condutor, cnh_ppd,
			tipo_de_documento, numero_documento, uf_cnh, infracao, enquadra,
			desdob, amparo_legal, codigo_do_municipio, municipio, uf, local, data, hora,
			descricao, marca, modelo, numero_de_serie, medicao_realizada,
			valor_considerado, numero_da_amostra, recolhimento, procedimentos,
			observacao, idetificacao_embarcador, cnpj_cpf_embarcador,
			identificacao_do_transportador, cnpj_cpf_transportador,
			numero_auto;

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getTipo_de_documento() {
		return tipo_de_documento;
	}

	public void setTipo_de_documento(String tipo_de_documento) {
		this.tipo_de_documento = tipo_de_documento;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getUf_cnh() {
		return uf_cnh;
	}

	public void setUf_cnh(String uf_cnh) {
		this.uf_cnh = uf_cnh;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNumero_auto() {
		return numero_auto;
	}

	public void setNumero_auto(String numero_auto) {
		this.numero_auto = numero_auto;
	}

	public DadosDoAuto() {
		super();
		initializedAllDAta();
	}

	public boolean isStoreFullData() {
		return isStoreFullData;
	}

	public void setStoreFullData(boolean isStoreFullData) {
		this.isStoreFullData = isStoreFullData;
	}

	public String getId_field() {
		return campo_id;
	}

	public void setId_field(String id_field) {
		this.campo_id = id_field;
	}

	public String getPlate() {
		return placa;
	}

	public void setPlate(String plate) {
		this.placa = plate;
	}

	public String getModel() {
		return modelo_do_veiculo;
	}

	public void setModel(String model) {
		this.modelo_do_veiculo = model;
	}

	public String getCor_do_veiculo() {
		return cor_do_veiculo;
	}

	public void setCor_do_veiculo(String cor_do_veiculo) {
		this.cor_do_veiculo = cor_do_veiculo;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome_do_Condutor() {
		return nome_do_condutor;
	}

	public void setNome_do_Condutor(String nome_do_Condutor) {
		this.nome_do_condutor = nome_do_Condutor;
	}

	public String getCnh_ppd() {
		return cnh_ppd;
	}

	public void setCnh_ppd(String cnh_ppd) {
		this.cnh_ppd = cnh_ppd;
	}

	public String getInfracao() {
		return infracao;
	}

	public void setInfracao(String infracao) {
		this.infracao = infracao;
	}

	public String getEnquadra() {
		return enquadra;
	}

	public void setEnquadra(String enquadra) {
		this.enquadra = enquadra;
	}

	public String getDesdob() {
		return desdob;
	}

	public void setDesdob(String desdob) {
		this.desdob = desdob;
	}

	public String getAmparo_legal() {
		return amparo_legal;
	}

	public void setAmparo_legal(String amparo_legal) {
		this.amparo_legal = amparo_legal;
	}

	public String getCodigo_do_municipio() {
		return codigo_do_municipio;
	}

	public void setCodigo_do_municipio(String codigo_do_municipio) {
		this.codigo_do_municipio = codigo_do_municipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumero_de_serie() {
		return numero_de_serie;
	}

	public void setNumero_de_serie(String numero_de_serie) {
		this.numero_de_serie = numero_de_serie;
	}

	public String getMedicao_realizada() {
		return medicao_realizada;
	}

	public void setMedicao_realizada(String medicao_realizada) {
		this.medicao_realizada = medicao_realizada;
	}

	public String getValor_considerada() {
		return valor_considerado;
	}

	public void setValor_considerada(String valor_considerada) {
		this.valor_considerado = valor_considerada;
	}

	public String getN_da_amostra() {
		return numero_da_amostra;
	}

	public void setN_da_amostra(String n_da_amostra) {
		this.numero_da_amostra = n_da_amostra;
	}

	public String getRecolhimento() {
		return recolhimento;
	}

	public String getProcedimentos() {
		return procedimentos;
	}

	public void setRecolhimento(String recolhimento) {
		this.recolhimento = recolhimento;
	}

	public void setProcedimentos(String procedimentos) {
		this.procedimentos = procedimentos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIdetificacao_embarcador() {
		return idetificacao_embarcador;
	}

	public void setIdetificacao_embarcador(String idetificacao_embarcador) {
		this.idetificacao_embarcador = idetificacao_embarcador;
	}

	public String getCnpj_cpf_embarcador() {
		return cnpj_cpf_embarcador;
	}

	public void setCnpj_cpf_embarcador(String cnpj_cpf_embarcador) {
		this.cnpj_cpf_embarcador = cnpj_cpf_embarcador;
	}

	public String getIdentificacao_do_transportador() {
		return identificacao_do_transportador;
	}

	public void setIdentificacao_do_transportador(
			String identificacao_do_transportador) {
		this.identificacao_do_transportador = identificacao_do_transportador;
	}

	public String getCnpj_cpf_transportador() {
		return cnpj_cpf_transportador;
	}

	public void setCnpj_cpf_transportador(String cnpj_cpf_transportador) {
		this.cnpj_cpf_transportador = cnpj_cpf_transportador;
	}

	public static String getAutoDeId() {
		return Id_Auto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void initializedAllDAta() {
		local = data = hora = campo_id = placa = modelo_do_veiculo = chassi = cor_do_veiculo = especie = categoria = nome_do_condutor = numero_documento = cnh_ppd = uf_cnh = tipo_de_documento = infracao = enquadra = desdob = amparo_legal = codigo_do_municipio = municipio = uf = local = data = hora = descricao = marca = modelo = numero_de_serie = medicao_realizada = valor_considerado = numero_da_amostra = recolhimento = procedimentos = observacao = idetificacao_embarcador = cnpj_cpf_embarcador = identificacao_do_transportador = cnpj_cpf_transportador = "";
	}

	public void setAutoDeDataFromCursor(Cursor myCursor) {

		this.setPlate(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.PLACA)));
		this.setChassi(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CHASSI)));
		this.setPais(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.PAIS)));

		this.setModel(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO)));

		this.setCor_do_veiculo(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.COR_DO_VEICULO)));
		this.setEspecie(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.ESPECIE)));

		this.setCategoria(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CATEGORIA)));

		this.setNome_do_Condutor(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.NOME_DO_CONDUCTOR)));

		this.setCnh_ppd(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CNH_PPD)));

		this.setUf_cnh(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.UF_CNH)));

		this.setTipo_de_documento(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.TIPO_DE_DOCUMENTO)));
		this.setNumero_documento(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.NUMERO_DOCUMENTO)));

		this.setInfracao(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.INFRACAO)));

		this.setEnquadra(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.ENQUADRA)));
		this.setDesdob(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.DESDOB)));

		this.setAmparo_legal(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.ART)));

		this.setCodigo_do_municipio(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CODIGO_DO_MUNICIPIO)));

		this.setMunicipio(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MUNICIPIO)));

		this.setUf(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.UF)));
		this.setLocal(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.LOCAL)));
		this.setData(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.DATA)));
		this.setHora(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.HORA)));
		this.setDescricao(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.DESCRICAO)));
		this.setMarca(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MARCA)));

		this.setModel(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO)));
		this.setNumero_de_serie(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE)));

		this.setMedicao_realizada(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MEDICO_REALIZADA)));

		this.setValor_considerada(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA)));

		this.setN_da_amostra(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.N_DA_AMOSTRA)));

		this.setRecolhimento(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.RECOLHIMENTO)));

		this.setProcedimentos(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.PROCEDIMENTOS)));

		this.setObservacao(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.OBSERVACAO)));

		this.setIdetificacao_embarcador(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR)));

		this.setCnpj_cpf_embarcador(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CNPJ_CPF_EMBARCADOR)));

		this.setIdentificacao_do_transportador(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR)));
		this.setCnpj_cpf_transportador(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.CNPJ_CPF_TRANSPORTADOR)));
		this.setNumero_auto(myCursor.getString(myCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.NUMERO_AUTO)));

		this.setStoreFullData(true);

	}

	public String getAutoDeListViewData(Context context) {
		String autode = context.getResources().getString(
				R.string.numero_do_chassi)
				+ getNewline()
				+ chassi
				+ getNewline_2()

				+ context.getResources().getString(R.string.auto_de_pais)
				+ getNewline()
				+ pais
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.auto_de_cor_do_veiculo)
				+ getNewline()
				+ cor_do_veiculo
				+ getNewline_2()
				+ context.getResources().getString(R.string.especie)
				+ getNewline()
				+ especie
				+ getNewline_2()
				+ context.getResources().getString(R.string.categoria)
				+ getNewline()
				+ categoria
				+ getNewline_2()
				+ context.getResources().getString(R.string.nome_do_condutor)
				+ getNewline()
				+ nome_do_condutor
				+ getNewline_2()
				+ context.getResources().getString(R.string.CNH_PPD)
				+ getNewline()
				+ cnh_ppd
				+ getNewline_2()
				+ context.getResources().getString(R.string.uf_cnh)
				+ getNewline()
				+ uf_cnh
				+ getNewline_2()

				+ context.getResources().getString(
						R.string.tipo_documento_apresentado)
				+ getNewline()
				+ tipo_de_documento
				+ getNewline()
				+ numero_documento
				+ getNewline_2()
				+ context.getResources().getString(R.string.infracao)
				+ getNewline()
				+ infracao
				+ getNewline_2()
				+ context.getResources().getString(R.string.enquadra)
				+ getNewline()
				+ enquadra
				+ getNewline_2()
				+ context.getResources().getString(R.string.desdob)
				+ getNewline()
				+ desdob
				// + getNewline_2()
				// + context.getResources().getString(R.string.amparo_legal)
				// + getNewline()
				// + amparo_legal
				+ getNewline_2()
				+ context.getResources().getString(R.string.auto_de_municipio)
				+ getNewline()
				+ codigo_do_municipio
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.auto_de_codigo_do_municipio)
				+ getNewline()
				+ municipio
				+ getNewline_2()
				+ context.getResources().getString(R.string.auto_de_UF)
				+ getNewline()
				+ uf
				+ getNewline_2()
				+ context.getResources().getString(R.string.local)
				+ getNewline()
				+ local
				+ getNewline_2()
				+ context.getResources().getString(R.string.data)
				+ getNewline()
				+ data
				+ getNewline_2()
				+ context.getResources().getString(R.string.hora)
				+ getNewline()
				+ hora
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.descricao_da_infracao)
				+ getNewline()
				+ descricao
				+ getNewline_2()
				+ context.getResources().getString(R.string.auto_de_marca)
				+ getNewline()
				+ marca
				+ getNewline_2()

				+ context.getResources().getString(R.string.auto_de_modelo)
				+ getNewline()
				+ modelo
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.auto_de_numero_de_serie)
				+ getNewline()
				+ numero_de_serie
				+ getNewline_2()
				+ context.getResources().getString(R.string.medicao_realizada)
				+ getNewline()
				+ medicao_realizada
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.auto_de_valor_considerado)
				+ getNewline()
				+ valor_considerado
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.auto_numero_da_amostra)
				+ getNewline()
				+ numero_da_amostra
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.recolhimento_de_documentos)
				+ getNewline()
				+ recolhimento
				+ getNewline_2()
				+ context.getResources().getString(R.string.procedimentos)
				+ getNewline()
				+ procedimentos
				+ getNewline_2()
				+ context.getResources().getString(R.string.observacao)
				+ getNewline()
				+ observacao
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.identificacao_do_embarcador)
				+ getNewline()
				+ idetificacao_embarcador
				+ getNewline_2()
				+ context.getResources().getString(R.string.CNPJ_CPF)
				+ getNewline()
				+ cnpj_cpf_embarcador
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.identificacao_do_transportador)
				+ getNewline()
				+ identificacao_do_transportador
				+ getNewline_2()
				+ context.getResources().getString(R.string.CNPJ_CPF)
				+ getNewline()
				+ cnpj_cpf_transportador
				+ getNewline_2()
				+ context.getResources().getString(R.string.numero_auto)
				+ getNewline() + numero_auto + getNewline_2();
		return autode;
	}

	public String getAutoDeViewData(Context context) {
		String autode = context.getResources().getString(R.string.placa)
				+ getNewline() + placa + getNewline_2()
				+ context.getResources().getString(R.string.model)
				+ getNewline() + modelo_do_veiculo + getNewline_2()
				+ getAutoDeListViewData(context);
		return autode;
	}

	public String getNewline() {
		return AppConstants.NEW_LINE;
	}

	public String getNewline_2() {
		return AppConstants.NEW_LINE + AppConstants.NEW_LINE;
	}
}
