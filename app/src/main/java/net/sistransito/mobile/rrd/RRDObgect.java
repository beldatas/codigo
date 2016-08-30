package net.sistransito.mobile.rrd;

public class RRDObgect {
	private static RRDData rrdData;

	private RRDObgect() {
		rrdData = new RRDData();
	}

	public static RRDData getRRDDeOject() {
		return rrdData;
	}

	public static void setRRDObject(RRDData data) {
		rrdData = data;
	}
}
