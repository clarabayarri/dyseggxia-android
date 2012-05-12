package dyseggxia.databaseTableDefinitions;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.utilities.AssetsReader;

public class SentenceSeparationProblemTable extends WordProblemDatabaseTable {

public static String TABLE_NAME = "sentence_separation_problems";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_SENTENCE = "sentence";
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_SENTENCE, 
		COLUMN_LEVEL_NUMBER};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_SENTENCE + " text not null, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LEVEL_NUMBER + ") " +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ") " +
			"references " + LevelTable.TABLE_NAME + "(" + LevelTable.COLUMN_NUMBER + ") );";
	
	public SentenceSeparationProblemTable() {
		
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
		List<String> data = reader.readSentenceProblems("separation", level);
		for(int i = 0; i < data.size(); ++i) {
			String sentence = data.get(i);
			insertProblem(database,i,sentence,level);
		}
	}
	
	private void insertProblem(SQLiteDatabase database, int index, String sentence, int levelNumber) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, index);
		values.put(COLUMN_SENTENCE, sentence);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		database.insert(TABLE_NAME, null, values);
	}

}
