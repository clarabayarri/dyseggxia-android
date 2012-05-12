package dyseggxia.databaseTableDefinitions;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.utilities.AssetsReader;
import dyseggxia.utilities.WordProblemDataTuple;

public class InsertionProblemTable extends WordProblemDatabaseTable {
	
	public static String TABLE_NAME = "insertion_problems";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_WORD = "word";
	public static final String COLUMN_INSERTION_INDEX = "insertion_index";
	public static final String COLUMN_END_INDEX = "end_index";
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_WORD, 
		COLUMN_INSERTION_INDEX, COLUMN_END_INDEX, COLUMN_LEVEL_NUMBER};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_WORD + " text not null, " +
			COLUMN_INSERTION_INDEX + " integer not null, " +
			COLUMN_END_INDEX + " integer not null, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LEVEL_NUMBER + ") " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ") " +
			"references " + LevelTable.TABLE_NAME + "(" + LevelTable.COLUMN_NUMBER + ") );";

	public InsertionProblemTable() {
		
	}
	
	@Override
	public String getCreateTableSentence() {
		return CREATE_TABLE;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected void loadLevelData(AssetsReader reader, SQLiteDatabase database, int level) {
		List<WordProblemDataTuple> data = reader.readWordProblems("insertion", level);
		for(int i = 0; i < data.size(); ++i) {
			WordProblemDataTuple word = data.get(i);
			insertProblem(database,i,word.word,word.startIndex,word.endIndex,level);
		}
	}

	private void insertProblem(SQLiteDatabase database, int index, String word, int insertionIndex, int endIndex, int levelNumber) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, index);
		values.put(COLUMN_WORD, word);
		values.put(COLUMN_INSERTION_INDEX, insertionIndex);
		values.put(COLUMN_END_INDEX, endIndex);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		database.insert(TABLE_NAME, null, values);
	}
}
