package net.sistransito.mobile.tca;

public class TCAObgect {
	private static TcaData tcaData;

	private TCAObgect() {
		tcaData = new TcaData();
	}

	public static TcaData getTCAOject() {
		return tcaData;
	}

	public static void setTCAObject(TcaData data) {
		tcaData = data;
	}
}
