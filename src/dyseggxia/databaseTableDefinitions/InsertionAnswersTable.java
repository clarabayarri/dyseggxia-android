package dyseggxia.databaseTableDefinitions;

import java.util.List;

import dyseggxia.utilities.AssetsReader;
import dyseggxia.utilities.WordProblemDataTuple;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class InsertionAnswersTable extends WordProblemDatabaseTable {

	public static final String TABLE_NAME = "insertion_answers";
	
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
			"references " + InsertionProblemTable.TABLE_NAME + 
				" (" + InsertionProblemTable.COLUMN_LEVEL_NUMBER + ", " + 
						InsertionProblemTable.COLUMN_NUMBER + "));";
	
	public InsertionAnswersTable() {
		
	}
	
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected void loadLevelData(AssetsReader reader, SQLiteDatabase database, int level) {
		List<WordProblemDataTuple> data = reader.readWordProblems("insertion", level);
		for(int i = 0; i < data.size(); ++i) {
			WordProblemDataTuple word = data.get(i);
			for(String answer : word.answers) {
				insertAnswer(database,level,i,answer);
			}
		}
	}
	
	private void insertAnswer(SQLiteDatabase database, int levelNumber, int problemIndex, String answer) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(COLUMN_PROBLEM_NUMBER, problemIndex);
		values.put(COLUMN_ANSWER, answer);
		database.insert(TABLE_NAME, null, values);
	}
	
	
}
