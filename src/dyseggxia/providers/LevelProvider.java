package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.LevelTable;
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
			Level level = mapRow(cursor);
			levels.add(level);
			cursor.moveToNext();
		}
		cursor.close();
		return levels;
	}
	
	public Level findLevel(int levelNumber) {
		Level level = getBasicLevel(levelNumber);
		return level;
	}

	private Level getBasicLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(table.getTableName(), LevelTable.ALL_COLUMNS, 
				LevelTable.COLUMN_NUMBER + "=" + levelNumber, null, null, null, null);
		cursor.moveToFirst();
		Level level = mapRow(cursor);
		cursor.close();
		database.close();
		return level;
	}

	private Level mapRow(Cursor cursor) {
		Level level = new Level(cursor.getInt(0),cursor.getString(1));
		return level;
	}

}
