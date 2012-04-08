package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SentenceSeparationProblemTable extends DatabaseTable {

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
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LEVEL_NUMBER + ")" +
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
	public void populateTable(SQLiteDatabase database) {
		insertProblem(database,1,"al medico",1);
		insertProblem(database,2,"a la hora",1);
		insertProblem(database,1,"al medico",2);
		insertProblem(database,2,"la taza",2);
		insertProblem(database,1,"en la casa",3);
		insertProblem(database,2,"por la acera",3);
	}
	
	private void insertProblem(SQLiteDatabase database, int index, String sentence, int levelNumber) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, index);
		values.put(COLUMN_SENTENCE, sentence);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		database.insert(TABLE_NAME, null, values);
	}

}
