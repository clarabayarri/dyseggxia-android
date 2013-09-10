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
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String COLUMN_LEVEL_LANGUAGE = "level_language";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_TYPE, COLUMN_WORD, 
		COLUMN_LEVEL_NUMBER, COLUMN_LEVEL_LANGUAGE, COLUMN_ID};
	public static final int COLUMN_NUMBER_INDEX = 0;
	public static final int COLUMN_TYPE_INDEX = 1;
	public static final int COLUMN_WORD_INDEX = 2;
	public static final int COLUMN_LEVEL_NUMBER_INDEX = 3;
	public static final int COLUMN_LEVEL_LANGUAGE_INDEX = 4;
	public static final int COLUMN_ID_INDEX = 5;
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_TYPE + " text not null, " +
			COLUMN_WORD + " text not null, " +
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
		List<WordProblemDataTuple> data = reader.getProblems();
		for (WordProblemDataTuple word : data) {
			insertProblem(database, word.number, word.type, word.word, 
					word.levelNumber, word.language);
			for (int i = 0; i < word.letters.size(); ++i) {
				insertLetter(database, word.levelNumber, word.language, word.number, i, word.letters.get(i));
			}
			if (word.answers != null) {
				for(String answer : word.answers) {
					insertAnswer(database, word.levelNumber, word.language, word.number, answer);
				}
			}
		}
	}

	private void insertProblem(SQLiteDatabase database, int number, String type, String word, 
			int levelNumber, String language) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TYPE, type);
		values.put(COLUMN_NUMBER, number);
		values.put(COLUMN_WORD, word);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(COLUMN_LEVEL_LANGUAGE, language);
		database.insert(TABLE_NAME, null, values);
	}
	
	private void insertAnswer(SQLiteDatabase database, int levelNumber, String lang, int problemIndex, String answer) {
		ContentValues values = new ContentValues();
		values.put(AnswerTable.COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(AnswerTable.COLUMN_LEVEL_LANGUAGE, lang);
		values.put(AnswerTable.COLUMN_PROBLEM_NUMBER, problemIndex);
		values.put(AnswerTable.COLUMN_ANSWER, answer);
		database.insert(AnswerTable.TABLE_NAME, null, values);
	}
	
	private void insertLetter(SQLiteDatabase database, int levelNumber, String lang, int problemIndex, int letterIndex, String answer) {
		ContentValues values = new ContentValues();
		values.put(LetterTable.COLUMN_LEVEL_NUMBER, levelNumber);
		values.put(LetterTable.COLUMN_LEVEL_LANGUAGE, lang);
		values.put(LetterTable.COLUMN_PROBLEM_NUMBER, problemIndex);
		values.put(LetterTable.COLUMN_LETTER_INDEX, letterIndex);
		values.put(LetterTable.COLUMN_LETTER, answer);
		database.insert(LetterTable.TABLE_NAME, null, values);
	}

}
