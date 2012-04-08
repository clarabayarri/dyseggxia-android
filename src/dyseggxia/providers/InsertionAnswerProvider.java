package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.InsertionAnswersTable;
import dyseggxia.domainModel.Problem;

public class InsertionAnswerProvider extends AbstractAnswerProvider {

	public InsertionAnswerProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public List<String> getAnswersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(InsertionAnswersTable.TABLE_NAME, InsertionAnswersTable.ALL_COLUMNS, 
				InsertionAnswersTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				InsertionAnswersTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			String answer = cursor.getString(2);
			answers.add(answer);
			cursor.moveToNext();
		}
		cursor.close();
		return answers;
	}
}
