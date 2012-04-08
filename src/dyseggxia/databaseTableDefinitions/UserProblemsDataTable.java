package dyseggxia.databaseTableDefinitions;

import android.database.sqlite.SQLiteDatabase;

public class UserProblemsDataTable extends DatabaseTable {

	public static String TABLE_NAME = "user_problems_data";
	
	public static final String COLUMN_PROBLEM_NUMBER = "problem_number";
	public static final String COLUMN_ATTEMPTS = "attempts";
	public static final String COLUMN_ACCOMPLISHMENTS = "accomplishments";
	public static final String[] ALL_COLUMNS = {COLUMN_PROBLEM_NUMBER, COLUMN_ATTEMPTS, COLUMN_ACCOMPLISHMENTS};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_PROBLEM_NUMBER + " integer unique not null, " + 
			COLUMN_ATTEMPTS + " integer not null default 0, " +
			COLUMN_ACCOMPLISHMENTS + " integer not null default 0, " +
			"foreign key (" + COLUMN_PROBLEM_NUMBER + ") " +
			"references " + InsertionProblemTable.TABLE_NAME + " (" + InsertionProblemTable.COLUMN_NUMBER + "));";
	
	public UserProblemsDataTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public void populateTable(SQLiteDatabase database) {
		// TODO Auto-generated method stub

	}

}
