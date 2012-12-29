package dyseggxia.databaseTableDefinitions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CompletedProblemDataTable extends DatabaseTable {

	public static String TABLE_NAME = "user_problems_data";
	
	public static final String COLUMN_PROBLEM_ID = "problem_ID";
	public static final String COLUMN_FAILS = "attempts";
	public static final String COLUMN_DURATION = "duration";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_WRONG_SOLUTIONS = "wring_solutions";
	public static final String[] ALL_COLUMNS = {COLUMN_PROBLEM_ID, COLUMN_FAILS, 
		COLUMN_DURATION, COLUMN_DATE, COLUMN_WRONG_SOLUTIONS};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_PROBLEM_ID + " integer not null, " + 
			COLUMN_FAILS + " integer not null default 0, " +
			COLUMN_DURATION + " double, " + 
			COLUMN_DATE + " timestamp, " + 
			COLUMN_WRONG_SOLUTIONS + " text, " + 
			"foreign key (" + COLUMN_PROBLEM_ID + ") " +
			"references " + ProblemTable.TABLE_NAME + " (" + ProblemTable.COLUMN_ID + "));";
	
	public CompletedProblemDataTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public void populateTable(Context context, SQLiteDatabase database) {
		
	}

}
