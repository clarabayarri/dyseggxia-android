package dyseggxia.providers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.SentenceSeparationProblemTable;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SentenceSeparationProblem;

public class SentenceSeparationProblemProvider extends AbstractProblemProvider {

	public SentenceSeparationProblemProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public int findNumProblemsForLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		String consulta = "SELECT Count(*) FROM " + SentenceSeparationProblemTable.TABLE_NAME + " WHERE " + SentenceSeparationProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber;
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}
	
	@Override
	public Problem findProblem(int levelNumber, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(SentenceSeparationProblemTable.TABLE_NAME, SentenceSeparationProblemTable.ALL_COLUMNS, 
				SentenceSeparationProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				SentenceSeparationProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.isAfterLast()) {
			cursor.close();
			throw new Exception("no problem found");
		}
		return mapProblem(cursor);
	}
	
	protected Problem mapProblem(Cursor cursor) {
		int problemNumber = cursor.getInt(0);
		String problemWord = cursor.getString(1);
		int levelNumber = cursor.getInt(2);
		cursor.close();

		SentenceSeparationProblem problem = new SentenceSeparationProblem(levelNumber,problemNumber,problemWord);

		return problem;
	}

}
