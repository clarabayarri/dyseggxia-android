package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class LevelTable extends DatabaseTable {

	public static String TABLE_NAME = "levels";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_DESCRIPTION};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer unique not null, " + 
			COLUMN_DESCRIPTION + " text not null);";
	
	
	public LevelTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public void populateTable(SQLiteDatabase database) {
		insertLevel(database,1,"Easy");
		insertLevel(database,2,"Medium");
		insertLevel(database,3,"Hard");
	}
	
	private void insertLevel(SQLiteDatabase database, int number, String description) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, number);
		values.put(COLUMN_DESCRIPTION, description);
		database.insert(TABLE_NAME, null, values);
	}

}
