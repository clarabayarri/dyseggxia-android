package dyseggxia.databaseTableDefinitions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AnswerTable extends DatabaseTable {

	public static final String TABLE_NAME = "answers";
	
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_LEVEL_LANGUAGE = "level_language";
	public static final String COLUMN_PROBLEM_NUMBER = "problem_number";
	public static final String COLUMN_ANSWER = "answer";
	public static final String[] ALL_COLUMNS = {COLUMN_LEVEL_NUMBER, COLUMN_LEVEL_LANGUAGE, 
		COLUMN_PROBLEM_NUMBER, COLUMN_ANSWER};
	
	public static final int COLUMN_ANSWER_INDEX = 3;
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			COLUMN_LEVEL_LANGUAGE + " varchar(10) not null, " + 
			COLUMN_PROBLEM_NUMBER + " integer not null, " + 
			COLUMN_ANSWER + " text not null, " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ", " +COLUMN_LEVEL_LANGUAGE + ", " + COLUMN_PROBLEM_NUMBER + ") " +
			"references " + ProblemTable.TABLE_NAME + 
				" (" + ProblemTable.COLUMN_LEVEL_NUMBER + ", " + 
				ProblemTable.COLUMN_LEVEL_LANGUAGE + ", " + 
				ProblemTable.COLUMN_NUMBER + "));";
	
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
