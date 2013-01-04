package dyseggxia.providers;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.CompletedProblemDataTable;
import dyseggxia.domainModel.Problem;

public class CompletedProblemProvider {

	private DatabaseHelper helper;
	
	public CompletedProblemProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public void saveCompletedProblem(Problem problem, String solutions, int intents) {
		SQLiteDatabase database = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CompletedProblemDataTable.COLUMN_PROBLEM_ID, problem.getId());
		values.put(CompletedProblemDataTable.COLUMN_FAILS, intents);
		values.put(CompletedProblemDataTable.COLUMN_WRONG_SOLUTIONS, solutions);
		values.put(CompletedProblemDataTable.COLUMN_DATE, new Date().getTime());
		database.insert(CompletedProblemDataTable.TABLE_NAME, null, values);
		database.close();
	}
	
	public int getNumCompletedProblems() {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.rawQuery("SELECT Count(*) FROM " + CompletedProblemDataTable.TABLE_NAME, null);
		if (cursor != null) {
			if (!cursor.isAfterLast()) {
				cursor.moveToFirst();
				int count = cursor.getInt(0);
				cursor.close();
				database.close();
				return count;
			}
			cursor.close();
		}
		database.close();
		return 0;
	}
	
}
