package net.sistransito.mobile.tav;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.TAVDatabaseHelper;
import net.sistrnsitomobile.R;

import java.io.Serializable;

public class TAVData implements Serializable {

	private static final String TAV_ID = "tav_id";
	private static final long serialVersionUID = 18393745L;

	private String placa, numero_do_auto, nome_do_proprietario, cpf_cnpj,
			numero_do_renavam, numero_do_chassi, cabeca_de_alavanca,
			carroceria, forro, lataria_capo, lataria_lado_direito,
			lataria_lado_esquerdo, lataria_tapa_porta_mala, lataria_teto,
			motor, painel, pintura_capo, pintura_lado_direito,
			pintura_lado_esquerdo, pintura_porta_mala, pintura_teto, radiador,
			vidros_laterais, vidro_para_brisa, vidro_traseiro, antena_de_radio,
			bagageiro, bancos, bateria, calota, condicionador_de_ar,
			extintor_de_incendio, farolete_dianteiro, farolete_traseiro,
			macaco, para_choque_dianteiro, para_choque_traseiro,
			para_sol_do_condutor, pneus, pneus_estepe, radio,
			retrovisor_interno, retrovisor_externo_direito, tapete, triangulo,
			volante, guidam, odometro, marcador_de_conbutivel,
			remocao_atraves_de, nome_da_empresa, nome_do_condutor_do_guincho,
			observacao, numero_tav;

	public String getNumero_tav() {
		return numero_tav;
	}

	public void setNumero_tav(String numero_tav) {
		this.numero_tav = numero_tav;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNome_da_empresa() {
		return nome_da_empresa;
	}

	public String getNome_do_condutor_do_guincho() {
		return nome_do_condutor_do_guincho;
	}

	public void setNome_da_empresa(String nome_da_empresa) {
		this.nome_da_empresa = nome_da_empresa;
	}

	public void setNome_do_condutor_do_guincho(
			String nome_do_condutor_do_guincho) {
		this.nome_do_condutor_do_guincho = nome_do_condutor_do_guincho;
	}

	public String getNumero_do_auto() {
		return numero_do_auto;
	}

	public String getNome_do_proprietario() {
		return nome_do_proprietario;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public String getNumero_do_renavam() {
		return numero_do_renavam;
	}

	public String getNumero_do_chassi() {
		return numero_do_chassi;
	}

	public String getCabeca_de_alavanca() {
		return cabeca_de_alavanca;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public String getForro() {
		return forro;
	}

	public String getLataria_capo() {
		return lataria_capo;
	}

	public String getLataria_lado_direito() {
		return lataria_lado_direito;
	}

	public String getLataria_lado_esquerdo() {
		return lataria_lado_esquerdo;
	}

	public String getLataria_tapa_porta_mala() {
		return lataria_tapa_porta_mala;
	}

	public String getLataria_teto() {
		return lataria_teto;
	}

	public String getMotor() {
		return motor;
	}

	public String getPainel() {
		return painel;
	}

	public String getPintura_capo() {
		return pintura_capo;
	}

	public String getPintura_lado_direito() {
		return pintura_lado_direito;
	}

	public String getPintura_lado_esquerdo() {
		return pintura_lado_esquerdo;
	}

	public String getPintura_porta_mala() {
		return pintura_porta_mala;
	}

	public String getPintura_teto() {
		return pintura_teto;
	}

	public String getRadiador() {
		return radiador;
	}

	public String getVidros_laterais() {
		return vidros_laterais;
	}

	public String getVidro_para_brisa() {
		return vidro_para_brisa;
	}

	public String getVidro_traseiro() {
		return vidro_traseiro;
	}

	public String getAntena_de_radio() {
		return antena_de_radio;
	}

	public String getBagageiro() {
		return bagageiro;
	}

	public String getBancos() {
		return bancos;
	}

	public String getBateria() {
		return bateria;
	}

	public String getCalota() {
		return calota;
	}

	public String getCondicionador_de_ar() {
		return condicionador_de_ar;
	}

	public String getExtintor_de_incendio() {
		return extintor_de_incendio;
	}

	public String getFarolete_dianteiro() {
		return farolete_dianteiro;
	}

	public String getFarolete_traseiro() {
		return farolete_traseiro;
	}

	public String getMacaco() {
		return macaco;
	}

	public String getPara_choque_dianteiro() {
		return para_choque_dianteiro;
	}

	public String getPara_choque_traseiro() {
		return para_choque_traseiro;
	}

	public String getPara_sol_do_condutor() {
		return para_sol_do_condutor;
	}

	public String getPneus() {
		return pneus;
	}

	public String getPneus_estepe() {
		return pneus_estepe;
	}

	public String getRadio() {
		return radio;
	}

	public String getRetrovisor_interno() {
		return retrovisor_interno;
	}

	public String getRetrovisor_externo_direito() {
		return retrovisor_externo_direito;
	}

	public String getTapete() {
		return tapete;
	}

	public String getTriangulo() {
		return triangulo;
	}

	public String getVolante() {
		return volante;
	}

	public String getGuidam() {
		return guidam;
	}

	public String getOdometro() {
		return odometro;
	}

	public String getMarcador_de_conbutivel() {
		return marcador_de_conbutivel;
	}

	public String getRemocao_atraves_de() {
		return remocao_atraves_de;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setNumero_do_auto(String numero_do_auto) {
		this.numero_do_auto = numero_do_auto;
	}

	public void setNome_do_proprietario(String nome_do_proprietario) {
		this.nome_do_proprietario = nome_do_proprietario;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public void setNumero_do_renavam(String numero_do_renavam) {
		this.numero_do_renavam = numero_do_renavam;
	}

	public void setNumero_do_chassi(String numero_do_chassi) {
		this.numero_do_chassi = numero_do_chassi;
	}

	public void setCabeca_de_alavanca(String cabeca_de_alavanca) {
		this.cabeca_de_alavanca = cabeca_de_alavanca;
	}

	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}

	public void setForro(String forro) {
		this.forro = forro;
	}

	public void setLataria_capo(String lataria_capo) {
		this.lataria_capo = lataria_capo;
	}

	public void setLataria_lado_direito(String lataria_lado_direito) {
		this.lataria_lado_direito = lataria_lado_direito;
	}

	public void setLataria_lado_esquerdo(String lataria_lado_esquerdo) {
		this.lataria_lado_esquerdo = lataria_lado_esquerdo;
	}

	public void setLataria_tapa_porta_mala(String lataria_tapa_porta_mala) {
		this.lataria_tapa_porta_mala = lataria_tapa_porta_mala;
	}

	public void setLataria_teto(String lataria_teto) {
		this.lataria_teto = lataria_teto;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public void setPainel(String painel) {
		this.painel = painel;
	}

	public void setPintura_capo(String pintura_capo) {
		this.pintura_capo = pintura_capo;
	}

	public void setPintura_lado_direito(String pintura_lado_direito) {
		this.pintura_lado_direito = pintura_lado_direito;
	}

	public void setPintura_lado_esquerdo(String pintura_lado_esquerdo) {
		this.pintura_lado_esquerdo = pintura_lado_esquerdo;
	}

	public void setPintura_porta_mala(String pintura_porta_mala) {
		this.pintura_porta_mala = pintura_porta_mala;
	}

	public void setPintura_teto(String pintura_teto) {
		this.pintura_teto = pintura_teto;
	}

	public void setRadiador(String radiador) {
		this.radiador = radiador;
	}

	public void setVidros_laterais(String vidros_laterais) {
		this.vidros_laterais = vidros_laterais;
	}

	public void setVidro_para_brisa(String vidro_para_brisa) {
		this.vidro_para_brisa = vidro_para_brisa;
	}

	public void setVidro_traseiro(String vidro_traseiro) {
		this.vidro_traseiro = vidro_traseiro;
	}

	public void setAntena_de_radio(String antena_de_radio) {
		this.antena_de_radio = antena_de_radio;
	}

	public void setBagageiro(String bagageiro) {
		this.bagageiro = bagageiro;
	}

	public void setBancos(String bancos) {
		this.bancos = bancos;
	}

	public void setBateria(String bateria) {
		this.bateria = bateria;
	}

	public void setCalota(String calota) {
		this.calota = calota;
	}

	public void setCondicionador_de_ar(String condicionador_de_ar) {
		this.condicionador_de_ar = condicionador_de_ar;
	}

	public void setExtintor_de_incendio(String extintor_de_incendio) {
		this.extintor_de_incendio = extintor_de_incendio;
	}

	public void setFarolete_dianteiro(String farolete_dianteiro) {
		this.farolete_dianteiro = farolete_dianteiro;
	}

	public void setFarolete_traseiro(String farolete_traseiro) {
		this.farolete_traseiro = farolete_traseiro;
	}

	public void setMacaco(String macaco) {
		this.macaco = macaco;
	}

	public void setPara_choque_dianteiro(String para_choque_dianteiro) {
		this.para_choque_dianteiro = para_choque_dianteiro;
	}

	public void setPara_choque_traseiro(String para_choque_traseiro) {
		this.para_choque_traseiro = para_choque_traseiro;
	}

	public void setPara_sol_do_condutor(String para_sol_do_condutor) {
		this.para_sol_do_condutor = para_sol_do_condutor;
	}

	public void setPneus(String pneus) {
		this.pneus = pneus;
	}

	public void setPneus_estepe(String pneus_estepe) {
		this.pneus_estepe = pneus_estepe;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void setRetrovisor_interno(String retrovisor_interno) {
		this.retrovisor_interno = retrovisor_interno;
	}

	public void setRetrovisor_externo_direito(String retrovisor_externo_direito) {
		this.retrovisor_externo_direito = retrovisor_externo_direito;
	}

	public void setTapete(String tapete) {
		this.tapete = tapete;
	}

	public void setTriangulo(String triangulo) {
		this.triangulo = triangulo;
	}

	public void setVolante(String volante) {
		this.volante = volante;
	}

	public void setGuidam(String guidam) {
		this.guidam = guidam;
	}

	public void setOdometro(String odometro) {
		this.odometro = odometro;
	}

	public void setMarcador_de_conbutivel(String marcador_de_conbutivel) {
		this.marcador_de_conbutivel = marcador_de_conbutivel;
	}

	public void setRemocao_atraves_de(String remocao_atraves_de) {
		this.remocao_atraves_de = remocao_atraves_de;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TAVData() {

		numero_do_auto = nome_do_proprietario = cpf_cnpj = numero_do_renavam = numero_do_chassi = cabeca_de_alavanca = carroceria = forro = lataria_capo = lataria_lado_direito = lataria_lado_esquerdo = lataria_tapa_porta_mala = lataria_teto = motor = painel = pintura_capo = pintura_lado_direito = pintura_lado_esquerdo = pintura_porta_mala = pintura_teto = radiador = vidros_laterais = vidro_para_brisa = vidro_traseiro = antena_de_radio = bagageiro = bancos = bateria = calota = condicionador_de_ar = extintor_de_incendio = farolete_dianteiro = farolete_traseiro = macaco = para_choque_dianteiro = para_choque_traseiro = para_sol_do_condutor = pneus = pneus_estepe = radio = retrovisor_interno = retrovisor_externo_direito = tapete = triangulo = volante = guidam = odometro = marcador_de_conbutivel = remocao_atraves_de = nome_da_empresa = nome_do_condutor_do_guincho = observacao = numero_tav = "";

	}

	public static String getTavId() {
		return TAV_ID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTAVListerView(Context context) {
		String tav = "";
		tav = context.getResources().getString(R.string.numero_do_auto)
				+ getNewline()
				+ numero_do_auto
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.nome_do_proprietario) + getNewline()
				+ nome_do_proprietario + getNewline_2() + getviewData(context);

		return tav;

	}

	private String getNewline() {
		return AppConstants.NEW_LINE;
	}

	private String getNewline_2() {
		return AppConstants.NEW_LINE + AppConstants.NEW_LINE;
	}

	public void setTAVDataFromCursor(Cursor myCursor) {

		placa = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PLACA));
		
		
		Log.d("pppppp", placa);
		numero_do_auto = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_DO_AUTO));
		nome_do_proprietario = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NOME_DO_PROPRIETARIO));
		cpf_cnpj = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.CPF_CNPJ));
		numero_do_renavam = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_DO_RENAVAM));
		numero_do_chassi = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_DO_CHASSI));
		cabeca_de_alavanca = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.CABECA_DE_ALAVANCA));
		carroceria = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.CARROCERIA));
		forro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.FORRO));
		lataria_capo = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.LATARIA_CAPO));

		lataria_lado_direito = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.LATARIA_LADO_DIREITO));
		lataria_lado_esquerdo = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.LATARIA_LADO_ESQUERDO));
		lataria_tapa_porta_mala = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.LATARIA_TAPA_PORTA_MALA));
		lataria_teto = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.LATARIA_TETO));

		motor = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.MOTOR));
		painel = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PAINEL));
		pintura_capo = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PINTURA_CAPO));
		pintura_lado_direito = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PINTURA_LADO_DIREITO));
		pintura_lado_esquerdo = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PINTURA_LADO_ESQUERDO));
		pintura_porta_mala = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PINTURA_PORTA_MALA));

		pintura_teto = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PINTURA_TETO));
		radiador = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.RADIADOR));
		vidros_laterais = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.VIDROS_LATERAIS));

		vidro_para_brisa = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.VIDRO_PARA_BRISA));
		vidro_traseiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.VIDRO_TRASEIRO));

		antena_de_radio = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.ANTENA_DE_RADIO));
		bagageiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.BAGAGEIRO));
		bancos = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.BANCOS));
		bateria = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.BATERIA));
		calota = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.CALOTA));

		condicionador_de_ar = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.CONDICIONADOR_DE_AR));

		extintor_de_incendio = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.EXTINTOR_DE_INCENDIO));
		farolete_dianteiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.FAROLETE_DIANTEIRO));
		farolete_traseiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.FAROLETE_TRASEIRO));
		macaco = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.MACACO));

		para_choque_dianteiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PARA_CHOQUE_DIANTEIRO));
		para_choque_traseiro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PARA_CHOQUE_TRASEIRO));
		para_sol_do_condutor = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PARA_SOL_DO_CONDUTOR));
		pneus = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PNEUS));

		pneus_estepe = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.PNEUS_ESTEPE));
		radio = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.RADIO));
		retrovisor_interno = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.RETROVISOR_INTERNO));

		retrovisor_externo_direito = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.RETROVISOR_EXTERNO_DIREITO));
		tapete = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.TAPETE));
		triangulo = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.TRIANGULO));

		volante = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.VOLANTE));
		guidam = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.GUIDAM));
		odometro = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.ODOMETRO));

		marcador_de_conbutivel = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.MARCADOR_DE_CONBUTIVEL));
		remocao_atraves_de = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.REMOCAO_ATRAVES_DE));
		nome_da_empresa = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NOME_DA_EMPRESA));
		nome_do_condutor_do_guincho = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NOME_DO_CONDUTOR_DO_GUINCHO));
		observacao = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.OBSERVACAO));
		numero_tav = myCursor.getString(myCursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_TAV));

	}

	public CharSequence getTCAListViewData(Context mycontext) {

		return getviewData(mycontext);
	}

	private String getviewData(Context context) {

		String data = context.getResources().getString(R.string.CPF_CNPJ)
				+ getNewline()
				+ cpf_cnpj
				+ getNewline_2()
				+ context.getResources().getString(R.string.numero_do_renavam)
				+ getNewline()
				+ numero_do_renavam
				+ getNewline_2()
				+ context.getResources().getString(R.string.numero_do_chassi)
				+ getNewline()
				+ numero_do_chassi
				+ getNewline_2()
				+ context.getResources().getString(R.string.cabeca_de_alavanca)
				+ getNewline()
				+ cabeca_de_alavanca
				+ getNewline_2()
				+ context.getResources().getString(R.string.carroceria)
				+ getNewline()
				+ carroceria
				+ getNewline_2()
				+ context.getResources().getString(R.string.forro)
				+ getNewline()
				+ forro
				+ getNewline_2()
				+ context.getResources().getString(R.string.lataria_capo)
				+ getNewline()
				+ lataria_capo
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.lataria_lado_direito)
				+ getNewline()
				+ lataria_lado_direito
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.lataria_lado_esquerdo)
				+ getNewline()
				+ lataria_lado_esquerdo
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.lataria_tapa_porta_mala)
				+ getNewline()
				+ lataria_tapa_porta_mala
				+ getNewline_2()
				+ context.getResources().getString(R.string.lataria_teto)
				+ getNewline()
				+ lataria_teto
				+ getNewline_2()
				+ context.getResources().getString(R.string.motor)
				+ getNewline()
				+ motor
				+ getNewline_2()
				+ context.getResources().getString(R.string.painel)
				+ getNewline()
				+ painel
				+ getNewline_2()
				+ context.getResources().getString(R.string.pintura_capo)
				+ getNewline()
				+ pintura_capo
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.pintura_lado_direito)
				+ getNewline()
				+ pintura_lado_direito
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.pintura_lado_esquerdo)
				+ getNewline()
				+ pintura_lado_esquerdo
				+ getNewline_2()
				+ context.getResources().getString(R.string.pintura_porta_mala)
				+ getNewline()
				+ pintura_porta_mala
				+ getNewline_2()
				+ context.getResources().getString(R.string.pintura_teto)
				+ getNewline()
				+ pintura_teto
				+ getNewline_2()
				+ context.getResources().getString(R.string.radiador)
				+ getNewline()
				+ radiador
				+ getNewline_2()
				+ context.getResources().getString(R.string.vidros_laterais)
				+ getNewline()
				+ vidros_laterais
				+ getNewline_2()
				+ context.getResources().getString(R.string.vidro_para_brisa)
				+ getNewline()
				+ vidro_para_brisa
				+ getNewline_2()
				+ context.getResources().getString(R.string.vidro_traseiro)
				+ getNewline()
				+ vidro_traseiro
				+ getNewline_2()
				+ context.getResources().getString(R.string.antena_de_radio)
				+ getNewline()
				+ antena_de_radio
				+ getNewline_2()
				+ context.getResources().getString(R.string.bagageiro)
				+ getNewline()
				+ bagageiro
				+ getNewline_2()
				+ context.getResources().getString(R.string.bancos)
				+ getNewline()
				+ bancos
				+ getNewline_2()
				+ context.getResources().getString(R.string.bateria)
				+ getNewline()
				+ bateria
				+ getNewline_2()
				+ context.getResources().getString(R.string.calota)
				+ getNewline()
				+ calota
				+ getNewline_2()
				+ context.getResources()
						.getString(R.string.condicionador_de_ar)
				+ getNewline()
				+ condicionador_de_ar
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.extintor_de_incendio)
				+ getNewline()
				+ extintor_de_incendio
				+ getNewline_2()
				+ context.getResources().getString(R.string.farolete_dianteiro)
				+ getNewline()
				+ farolete_dianteiro
				+ getNewline_2()
				+ context.getResources().getString(R.string.farolete_traseiro)
				+ getNewline()
				+ farolete_traseiro
				+ getNewline_2()
				+ context.getResources().getString(R.string.macaco)
				+ getNewline()
				+ macaco
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.para_choque_dianteiro)
				+ getNewline()
				+ para_choque_dianteiro
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.para_choque_traseiro)
				+ getNewline()
				+ para_choque_traseiro
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.para_sol_do_condutor)
				+ getNewline()
				+ para_sol_do_condutor
				+ getNewline_2()
				+ context.getResources().getString(R.string.pneus)
				+ getNewline()
				+ pneus
				+ getNewline_2()
				+ context.getResources().getString(R.string.pneus_estepe)
				+ getNewline()
				+ pneus_estepe
				+ getNewline_2()
				+ context.getResources().getString(R.string.radio)
				+ getNewline()
				+ radio
				+ getNewline_2()
				+ context.getResources().getString(R.string.retrovisor_interno)
				+ getNewline()
				+ retrovisor_interno
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.retrovisor_externo_direito)
				+ getNewline()
				+ retrovisor_externo_direito
				+ getNewline_2()
				+ context.getResources().getString(R.string.tapete)
				+ getNewline()
				+ tapete
				+ getNewline_2()
				+ context.getResources().getString(R.string.triangulo)
				+ getNewline()
				+ triangulo
				+ getNewline_2()
				+ context.getResources().getString(R.string.volante)
				+ getNewline()
				+ volante
				+ getNewline_2()
				+ context.getResources().getString(R.string.guidam)
				+ getNewline()
				+ guidam
				+ getNewline_2()
				+ context.getResources().getString(R.string.odometro)
				+ getNewline()
				+ odometro
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.marcador_de_combustivel)
				+ getNewline()
				+ marcador_de_conbutivel
				+ getNewline_2()
				+ context.getResources().getString(R.string.remocao_atraves_de)
				+ getNewline()
				+ remocao_atraves_de
				+ getNewline_2()
				+ context.getResources().getString(R.string.nome_da_empresa)
				+ getNewline()
				+ nome_da_empresa
				+ getNewline_2()
				+ context.getResources().getString(
						R.string.nome_do_condutor_do_guincho) + getNewline()
				+ nome_do_condutor_do_guincho + getNewline_2()
				+ context.getResources().getString(R.string.observacao)
				+ getNewline() + observacao + getNewline_2()
				+ context.getResources().getString(R.string.numero_tav)
				+ getNewline() + numero_tav + getNewline_2();

		return data;

	}

}
