package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.LevelTable;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.Level;

public class LevelProvider implements LevelProviderI {

	private DatabaseHelper helper;
	private LevelTable table = new LevelTable();
	
	public LevelProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public List<Level> findAllLevelBasicData() {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<Level> levels = new ArrayList<Level>();
		Cursor cursor = database.query(table.getTableName(), LevelTable.ALL_COLUMNS,
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Level level = mapRow(database, cursor);
			levels.add(level);
			cursor.moveToNext();
		}
		cursor.close();
		return levels;
	}
	
	public Level findLevel(int levelNumber, String language) {
		Level level = getBasicLevel(levelNumber, language);
		return level;
	}
	
	private int findNumProblemsForLevel(SQLiteDatabase database, int levelNumber, String language) {
		String consulta = "SELECT Count(*) FROM " + ProblemTable.TABLE_NAME + " WHERE " + 
				ProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber +
				" AND " + ProblemTable.COLUMN_LEVEL_LANGUAGE + " = '" + language + "'";
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}

	private Level getBasicLevel(int levelNumber, String language) {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(table.getTableName(), LevelTable.ALL_COLUMNS, 
				LevelTable.COLUMN_NUMBER + " = " + levelNumber + " AND " + 
						LevelTable.COLUMN_LANGUAGE + " = '" + language + "'", null, null, null, null);
		cursor.moveToFirst();
		Level level = mapRow(database, cursor);
		database.close();
		return level;
	}

	private Level mapRow(SQLiteDatabase database, Cursor cursor) {
		int number = cursor.getInt(LevelTable.COLUMN_NUMBER_INDEX);
		String language = cursor.getString(LevelTable.COLUMN_LANGUAGE_INDEX);
		cursor.close();
		int numProblems = findNumProblemsForLevel(database, number, language);
		Level level = new Level(number,language, numProblems);
		return level;
	}

}
