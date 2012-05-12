package dyseggxia.databaseTableDefinitions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TrackingData extends DatabaseTable {

	public static String TABLE_NAME = "tracking_data";
	
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_PROBLEM_NUMBER = "number";
	public static final String COLUMN_PROBLEM_TYPE = "type";
	public static final String COLUMN_WORD = "word";
	public static final String COLUMN_ANSWER = "answer";
	public static final String COLUMN_CORRECT = "correct";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_USER_ID = "userid";
	public static final String COLUMN_TIMESTAMP = "timestamp";
	
	public static final String[] ALL_COLUMNS = {COLUMN_LEVEL_NUMBER, COLUMN_PROBLEM_NUMBER, COLUMN_PROBLEM_TYPE, 
		COLUMN_WORD, COLUMN_ANSWER, COLUMN_TIME, COLUMN_USER_ID, COLUMN_TIMESTAMP};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			COLUMN_PROBLEM_NUMBER + " integer not null, " + 
			COLUMN_PROBLEM_TYPE + " text not null, " + 
			COLUMN_WORD + " text not null, " +
			COLUMN_CORRECT + " text not null, "  +
			COLUMN_ANSWER + " text, " +
			COLUMN_TIME + " integer, " +
			COLUMN_USER_ID + " text " + 
			COLUMN_TIMESTAMP + " timestamp default SYSDATE );";

	
	@Override
	public void populateTable(Context context, SQLiteDatabase database) {
		
	}

	@Override
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

}
