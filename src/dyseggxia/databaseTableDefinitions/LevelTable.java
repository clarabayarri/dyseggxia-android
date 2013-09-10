package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LevelTable extends DatabaseTable {

	public static String TABLE_NAME = "levels";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_LANGUAGE = "language";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_LANGUAGE};
	
	public static final int COLUMN_NUMBER_INDEX = 0;
	public static final int COLUMN_LANGUAGE_INDEX = 1;
	
	public static final String[] LOCALES = {"es", "en"};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_LANGUAGE + " varchar(10) not null, " + 
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LANGUAGE + "));";
	
	
	public LevelTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public void populateTable(Context context, SQLiteDatabase database) {
		for (int i = 0; i < 5; ++i) {
			for (String lang : LOCALES) {
				insertLevel(database, i, lang);
			}
		}
	}
	
	private void insertLevel(SQLiteDatabase database, int number, String language) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, number);
		values.put(COLUMN_LANGUAGE, language);
		database.insert(TABLE_NAME, null, values);
	}

}
