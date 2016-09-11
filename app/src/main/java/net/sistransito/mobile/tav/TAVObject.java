package net.sistransito.mobile.tav;

public class TAVObject {
	private static TAVData data;

	private TAVObject() {
		data = new TAVData();
	}

	public static TAVData getTAVObject() {

		if (data == null)
			data = new TAVData();
		
		return data;
	}

	public static void setTAVObject(TAVData tcaData) {

		data = tcaData;
	}
}
