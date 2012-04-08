package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DerivationAnswersTable extends DatabaseTable {

public static final String TABLE_NAME = "derivation_answers";
	
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_PROBLEM_NUMBER = "problem_number";
	public static final String COLUMN_ANSWER = "answer";
	public static final String[] ALL_COLUMNS = {COLUMN_LEVEL_NUMBER, COLUMN_PROBLEM_NUMBER, COLUMN_ANSWER};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " + 
			COLUMN_PROBLEM_NUMBER + " integer not null, " + 
			COLUMN_ANSWER + " text not null, " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ", " + COLUMN_PROBLEM_NUMBER + ") " +
			"references " + DerivationProblemTable.TABLE_NAME + 
				" (" + DerivationProblemTable.COLUMN_LEVEL_NUMBER + ", " + 
					DerivationProblemTable.COLUMN_NUMBER + "));";
	
	@Override
	public void populateTable(SQLiteDatabase database) {
		insertAnswer(database,1,1,"cer");
		insertAnswer(database,1,1,"ci—n");
		insertAnswer(database,1,2,"ero");
		insertAnswer(database,1,2,"tido");
		insertAnswer(database,2,1,"ing");
		insertAnswer(database,2,1,"cito");
		insertAnswer(database,2,2,"ero");
		insertAnswer(database,2,2,"ing");
	}
	
	private void insertAnswer(SQLiteDatabase database, int levelNumber, int problemIndex, String answer) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(COLUMN_PROBLEM_NUMBER, problemIndex);
		values.put(COLUMN_ANSWER, answer);
		database.insert(TABLE_NAME, null, values);
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
