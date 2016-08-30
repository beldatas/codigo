package net.sistransito.mobile.rrd.lister;


import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.rrd.RRDData;

public interface RrdPrintListener {
	public void print(RRDData rrdData, DadosDoAuto dadosDoAuto);

}
