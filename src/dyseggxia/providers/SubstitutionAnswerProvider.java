package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.SubstitutionAnswersTable;
import dyseggxia.domainModel.Problem;

public class SubstitutionAnswerProvider extends AbstractAnswerProvider {

	public SubstitutionAnswerProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public List<String> getAnswersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(SubstitutionAnswersTable.TABLE_NAME, SubstitutionAnswersTable.ALL_COLUMNS, 
				SubstitutionAnswersTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				SubstitutionAnswersTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, null);
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
