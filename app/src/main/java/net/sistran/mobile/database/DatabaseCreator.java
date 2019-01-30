package net.sistran.mobile.database;

import android.content.Context;

public class DatabaseCreator {
	public static final int VERSION = 1;
	private static SettingDatabaseAdapter databaseAdapterSetting;
	private static PlacaSearchDatabaseAdapter placaSearchDatabaseAdapter;
	private static AutoInfracaoDatabaseAdapter databaseAdapterAutoInfracao;
	private static PrepopulatedDBOpenHelper prepopulatedDBOpenHelper;
	private static SearchDataSdcard searchDataSdcard;
	private static BalancoDatabaseAdapter balancoDatabaseAdapter;
	private static NumeroDatabaseAdapter numeroDatabaseAdapter;
	private DatabaseCreator(Context context) {
		openAllDatabase(context);
	}

	public static void openAllDatabase(Context context) {
		databaseAdapterSetting = new SettingDatabaseAdapter(context);
		placaSearchDatabaseAdapter = new PlacaSearchDatabaseAdapter(context);
		databaseAdapterAutoInfracao = new AutoInfracaoDatabaseAdapter(context);
		prepopulatedDBOpenHelper = new PrepopulatedDBOpenHelper(context);
		searchDataSdcard = new SearchDataSdcard(context);
		numeroDatabaseAdapter = new NumeroDatabaseAdapter(context);
		balancoDatabaseAdapter = new BalancoDatabaseAdapter(context);

	}

	public static BalancoDatabaseAdapter getBalancoDatabaseAdapter(
			Context context) {

		if (balancoDatabaseAdapter == null)
			balancoDatabaseAdapter = new BalancoDatabaseAdapter(context);
		return balancoDatabaseAdapter;
	}

	public static NumeroDatabaseAdapter getDatabaseAdapterNumero(Context context) {
		if (numeroDatabaseAdapter == null)
			numeroDatabaseAdapter = new NumeroDatabaseAdapter(context);
		return numeroDatabaseAdapter;
	}

	public static AutoInfracaoDatabaseAdapter getDatabaseAdapterAutoInfracao(
			Context context) {
		if (databaseAdapterAutoInfracao == null)
			databaseAdapterAutoInfracao = new AutoInfracaoDatabaseAdapter(
					context);
		return databaseAdapterAutoInfracao;
	}

	public static SettingDatabaseAdapter getDatabaseAdapterSetting(Context cnt) {
		if (databaseAdapterSetting == null) {
			databaseAdapterSetting = new SettingDatabaseAdapter(cnt);
		}
		return databaseAdapterSetting;
	}

	public static PlacaSearchDatabaseAdapter getPlacaSearchDatabaseAdapter(
			Context context) {

		if (placaSearchDatabaseAdapter == null) {
			placaSearchDatabaseAdapter = new PlacaSearchDatabaseAdapter(context);
		}
		return placaSearchDatabaseAdapter;
	}

	public synchronized void close() {
		placaSearchDatabaseAdapter.close();
		databaseAdapterSetting.close();
		databaseAdapterAutoInfracao.close();
		prepopulatedDBOpenHelper.close();
	}

	public static PrepopulatedDBOpenHelper getPrepopulatedDBOpenHelper(
			Context context) {
		if (prepopulatedDBOpenHelper == null) {
			prepopulatedDBOpenHelper = new PrepopulatedDBOpenHelper(context);
		}
		return prepopulatedDBOpenHelper;
	}

	public static SearchDataSdcard getSearchDataSdcard(
			Context context) {
		if (searchDataSdcard == null) {
			searchDataSdcard = new SearchDataSdcard(context);
		}
		return searchDataSdcard;
	}

}
