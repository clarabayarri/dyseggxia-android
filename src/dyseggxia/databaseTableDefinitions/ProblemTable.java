package dyseggxia.databaseTableDefinitions;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.utilities.AssetsReader;
import dyseggxia.utilities.WordProblemDataTuple;

public class ProblemTable extends DatabaseTable {

	public static String TABLE_NAME = "problems";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_WORD = "word";
	public static final String COLUMN_INSERTION_INDEX = "insertion_index";
	public static final String COLUMN_END_INDEX = "end_index";
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_LEVEL_LANGUAGE = "level_language";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_TYPE, COLUMN_WORD, 
		COLUMN_INSERTION_INDEX, COLUMN_END_INDEX, COLUMN_LEVEL_NUMBER, COLUMN_LEVEL_LANGUAGE, COLUMN_ID};
	public static final int COLUMN_NUMBER_INDEX = 0;
	public static final int COLUMN_TYPE_INDEX = 1;
	public static final int COLUMN_WORD_INDEX = 2;
	public static final int COLUMN_INSERTION_INDEX_INDEX = 3;
	public static final int COLUMN_END_INDEX_INDEX = 4;
	public static final int COLUMN_LEVEL_NUMBER_INDEX = 5;
	public static final int COLUMN_LEVEL_LANGUAGE_INDEX = 6;
	public static final int COLUMN_ID_INDEX = 7;
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_TYPE + " text not null, " +
			COLUMN_WORD + " text not null, " +
			COLUMN_INSERTION_INDEX + " integer not null, " +
			COLUMN_END_INDEX + " integer not null, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			COLUMN_LEVEL_LANGUAGE + " varchar(10) not null, " + 
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LEVEL_NUMBER + "," + COLUMN_LEVEL_LANGUAGE + ") " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + "," +  COLUMN_LEVEL_LANGUAGE + ") " +
			"references " + LevelTable.TABLE_NAME + 
			"(" + LevelTable.COLUMN_NUMBER + "," + LevelTable.COLUMN_LANGUAGE + ") );";
	
	private AssetsReader reader;
	
	public ProblemTable(AssetsReader reader) {
		this.reader = reader;
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
	public void populateTable(Context context, SQLiteDatabase database) {
		List<WordProblemDataTuple> data = reader.getWordProblems();
		for (WordProblemDataTuple word : data) {
			insertProblem(database, word.number, word.type, word.word, 
					word.startIndex, word.endIndex, word.levelNumber, word.language);
		}
	}

	private void insertProblem(SQLiteDatabase database, int number, String type, String word, 
			int insertionIndex, int endIndex, int levelNumber, String language) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TYPE, type);
		values.put(COLUMN_NUMBER, number);
		values.put(COLUMN_WORD, word);
		values.put(COLUMN_INSERTION_INDEX, insertionIndex);
		values.put(COLUMN_END_INDEX, endIndex);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(COLUMN_LEVEL_LANGUAGE, language);
		database.insert(TABLE_NAME, null, values);
	}

}
