package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserLevelsDataTable extends DatabaseTable{

	public static String TABLE_NAME = "user_levels_data";
	
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_ACCOMPLISHED_PROBLEMS = "accomplished_problems";
	public static final String COLUMN_POINTS = "points";
	public static final String[] ALL_COLUMNS = {COLUMN_LEVEL_NUMBER, COLUMN_ACCOMPLISHED_PROBLEMS, COLUMN_POINTS};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_LEVEL_NUMBER + " integer unique not null, " + 
			COLUMN_ACCOMPLISHED_PROBLEMS + " integer not null default 0, " +
			COLUMN_POINTS + " integer not null default 0, " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ") " +
			"references " + LevelTable.TABLE_NAME + " (" + LevelTable.COLUMN_NUMBER + "));";
	
	public UserLevelsDataTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public void populateTable(Context context, SQLiteDatabase database) {
		insertData(database,1);
	}

	private void insertData(SQLiteDatabase database, int levelNumber) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		database.insert(TABLE_NAME, null, values);
	}

}
