package dyseggxia.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import dyseggxia.databaseTableDefinitions.DatabaseTable;
import dyseggxia.databaseTableDefinitions.DerivationAnswersTable;
import dyseggxia.databaseTableDefinitions.DerivationProblemTable;
import dyseggxia.databaseTableDefinitions.InsertionAnswersTable;
import dyseggxia.databaseTableDefinitions.InsertionProblemTable;
import dyseggxia.databaseTableDefinitions.LevelTable;
import dyseggxia.databaseTableDefinitions.OmissionAnswersTable;
import dyseggxia.databaseTableDefinitions.OmissionProblemTable;
import dyseggxia.databaseTableDefinitions.SubstitutionAnswersTable;
import dyseggxia.databaseTableDefinitions.SubstitutionProblemTable;
import dyseggxia.databaseTableDefinitions.UserLevelsDataTable;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "dyseggxia.db";
	private static final int DATABASE_VERSION = 1;
	private static DatabaseTable[] tables = {new LevelTable(), 
		new InsertionProblemTable(), new InsertionAnswersTable(), 
		new OmissionProblemTable(), new OmissionAnswersTable(), 
		new SubstitutionProblemTable(), new SubstitutionAnswersTable(),
		new DerivationProblemTable(), new DerivationAnswersTable(),
		new UserLevelsDataTable()};
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		for(DatabaseTable table : tables) {
			database.execSQL(table.getCreateTableSentence());
			table.populateTable(database);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		for(DatabaseTable table : tables) {
			database.execSQL("DROP TABLE IF EXISTS " + table.getTableName());
		}
		
		onCreate(database);
	}

}
