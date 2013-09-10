package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.AnswerTable;
import dyseggxia.domainModel.Problem;

public class AnswerProvider implements AnswerProviderI {
	
	private DatabaseHelper helper;
	
	public AnswerProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public List<String> getAnswersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(AnswerTable.TABLE_NAME, AnswerTable.ALL_COLUMNS, 
				AnswerTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				AnswerTable.COLUMN_LEVEL_LANGUAGE + "='" + problem.getLanguage() + "' AND " + 
				AnswerTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			try {
				String answer = new String(cursor.getString(AnswerTable.COLUMN_ANSWER_INDEX).getBytes(), "UTF-8");
				answers.add(answer);
				cursor.moveToNext();
			} catch (Exception e) {}
		}
		cursor.close();
		database.close();
		return answers;
	}

}
