package dyseggxia.databaseTableDefinitions;

import dyseggxia.activities.R;
import android.content.ContentValues;
import android.content.Context;
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
	public void populateTable(Context context, SQLiteDatabase database) {
		insertLevel(database,1,context.getResources().getString(R.string.easy));
		insertLevel(database,2,context.getResources().getString(R.string.medium));
		insertLevel(database,3,context.getResources().getString(R.string.hard));
	}
	
	private void insertLevel(SQLiteDatabase database, int number, String description) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, number);
		values.put(COLUMN_DESCRIPTION, description);
		database.insert(TABLE_NAME, null, values);
	}

}
