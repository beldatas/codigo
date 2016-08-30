package net.sistransito.mobile.tav;

public class TAVObgect {
	private static TAVData data;

	private TAVObgect() {
		data = new TAVData();
	}

	public static TAVData getTAVOject() {

		if (data == null)
			data = new TAVData();
		
		return data;
	}

	public static void setTAVObject(TAVData tcaData) {
		data = tcaData;
	}
}
