package net.sistran.mobile.placa.dados;

import android.content.Context;

import net.sistran.mobile.timeandime.TimeAndIme;

public class DataFromPlate {

	TimeAndIme time;

	public DataFromPlate(Context context) {
		time = new TimeAndIme(context);
		setDate(time.getDate() + "\n" + time.getTime());

	}

	private String plate, uf, municipio, pais, marca, model, color, chassi, ranavam, especie, type, category, licenceYear, licenceData,
			licenceStatus, ipva, insurance, status, infractions, restrictions, indicadorRetencao, numeroMotor,
			idProprietario, nomeProprietario, numeroLacre, duble, anoModelo, anoFabricacao,
			date, latitude, longitude, registroImpedimento, registroFurto;

	public String getRegistroImpedimento() {
		return registroImpedimento;
	}

	public void setRegistroImpedimento(String registroImpedimento) {
		this.registroImpedimento = registroImpedimento;
	}

	public String getRegistroFurto() {
		return registroFurto;
	}

	public void setRegistroFurto(String registroFurto) {
		this.registroFurto = registroFurto;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getRanavam() {
		return ranavam;
	}

	public void setRanavam(String ranavam) {
		this.ranavam = ranavam;
	}

	public String getIndicadorRetencao() {
		return indicadorRetencao;
	}

	public void setIndicadorRetencao(String indicadorRetencao) {
		this.indicadorRetencao = indicadorRetencao;
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	public String getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(String idProprietario) {
		this.idProprietario = idProprietario;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}

	public String getDuble() {
		return duble;
	}

	public void setDuble(String duble) {
		this.duble = duble;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLicenceYear() {
		return licenceYear;
	}

	public void setLicenceYear(String licenseYear) {
		this.licenceYear = licenseYear;
	}

	public String getLicenceData() {
		return licenceData;
	}

	public void setLicenceData(String licenseDate) {
		this.licenceData = licenseDate;
	}

	public String getLicenceStatus() {
		return licenceStatus;
	}

	public void setLicenceStatus(String licenseStatus) {
		this.licenceStatus = licenseStatus;
	}

	public String getIpva() {
		return ipva;
	}

	public void setIpva(String ipva) {
		this.ipva = ipva;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfractions() {
		return infractions;
	}

	public void setInfractions(String infractions) {
		this.infractions = infractions;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
