package net.sistransito.mobile.database;

import android.content.Context;

/**
 * 
 * @author Gazi
 * 
 *         The database create all database single tone object so the app run
 *         falster
 */
public class DatabaseCreator {
	public static final int VERSION = 1;
	private static SettingDatabaseAdapter databaseAdapterSetting;
	private static PlacaSearchDatabaseAdapter placaSearchDatabaseAdapter;
	private static AutoInfracaoDatabaseAdapter databaseAdapterAutoInfracao;
	private static PrepopulatedDBOpenHelper prepopulatedDBOpenHelper;
	private static RRDDatabaseAdapter rrddatabaseAdapter;
	private static TCADatabaseAdapter tcaDatabaseAdapter;
	private static TAVDatabaseAdapter tavDatabaseAdapter;
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
		rrddatabaseAdapter = new RRDDatabaseAdapter(context);
		tcaDatabaseAdapter = new TCADatabaseAdapter(context);
		tavDatabaseAdapter = new TAVDatabaseAdapter(context);
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

	public static TAVDatabaseAdapter getDatabaseAdapterTAV(Context context) {
		if (tavDatabaseAdapter == null)
			tavDatabaseAdapter = new TAVDatabaseAdapter(context);
		return tavDatabaseAdapter;
	}

	public static TCADatabaseAdapter getDatabaseAdapterTCA(Context context) {
		if (tcaDatabaseAdapter == null)
			tcaDatabaseAdapter = new TCADatabaseAdapter(context);
		return tcaDatabaseAdapter;
	}

	public static RRDDatabaseAdapter getDatabaseAdapterRRD(Context context) {
		if (rrddatabaseAdapter == null)
			rrddatabaseAdapter = new RRDDatabaseAdapter(context);
		return rrddatabaseAdapter;
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
		tcaDatabaseAdapter.close();
	}

	public static PrepopulatedDBOpenHelper getPrepopulatedDBOpenHelper(
			Context context) {
		if (prepopulatedDBOpenHelper == null) {
			prepopulatedDBOpenHelper = new PrepopulatedDBOpenHelper(context);
		}
		return prepopulatedDBOpenHelper;
	}
}
