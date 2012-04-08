package dyseggxia.databaseTableDefinitions;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SubstitutionProblemTable extends DatabaseTable {

public static String TABLE_NAME = "substitution_problems";
	
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_WORD = "word";
	public static final String COLUMN_SUBSTITUTION_INDEX = "omission_index";
	public static final String COLUMN_LEVEL_NUMBER = "level_number";
	public static final String[] ALL_COLUMNS = {COLUMN_NUMBER, COLUMN_WORD, 
		COLUMN_SUBSTITUTION_INDEX, COLUMN_LEVEL_NUMBER};
	
	private static String CREATE_TABLE = "create table " +
			TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NUMBER + " integer not null, " + 
			COLUMN_WORD + " text not null, " +
			COLUMN_SUBSTITUTION_INDEX + " integer not null, " +
			COLUMN_LEVEL_NUMBER + " integer not null, " +
			"unique(" + COLUMN_NUMBER + "," + COLUMN_LEVEL_NUMBER + ")" +
			"foreign key (" + COLUMN_LEVEL_NUMBER + ") " +
			"references " + LevelTable.TABLE_NAME + "(" + LevelTable.COLUMN_NUMBER + ") );";

	public SubstitutionProblemTable() {
		
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
		insertProblem(database,1,"problema",6,1);
		insertProblem(database,2,"inserci—n",4,1);
		insertProblem(database,1,"palabra",5,2);
		insertProblem(database,2,"lectura",3,2);
	}
	
	private void insertProblem(SQLiteDatabase database, int index, String word, int insertionIndex, int levelNumber) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NUMBER, index);
		values.put(COLUMN_WORD, word);
		values.put(COLUMN_SUBSTITUTION_INDEX, insertionIndex);
		values.put(COLUMN_LEVEL_NUMBER, levelNumber);
		database.insert(TABLE_NAME, null, values);
	}

}
