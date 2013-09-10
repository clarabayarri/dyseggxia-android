package dyseggxia.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import dyseggxia.databaseTableDefinitions.AnswerTable;
import dyseggxia.databaseTableDefinitions.CompletedProblemDataTable;
import dyseggxia.databaseTableDefinitions.DatabaseTable;
import dyseggxia.databaseTableDefinitions.LetterTable;
import dyseggxia.databaseTableDefinitions.LevelTable;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.databaseTableDefinitions.TrackingData;
import dyseggxia.utilities.AssetsReader;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "dyseggxia.db";
	private static final int DATABASE_VERSION = 1;
	private static DatabaseTable[] tables;
	
	private Context context;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		AssetsReader reader = new AssetsReader(context);
		tables = new DatabaseTable[] {new LevelTable(), 
				new ProblemTable(reader), new LetterTable(), new AnswerTable(), 
				new CompletedProblemDataTable(), new TrackingData()};
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		for(DatabaseTable table : tables) {
			database.execSQL(table.getCreateTableSentence());
		}
		for(DatabaseTable table : tables) {
			table.populateTable(context, database);
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
