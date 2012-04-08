package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.OmissionAnswersTable;
import dyseggxia.domainModel.Problem;

public class OmissionAnswerProvider extends AbstractAnswerProvider {

	public OmissionAnswerProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public List<String> getAnswersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(OmissionAnswersTable.TABLE_NAME, OmissionAnswersTable.ALL_COLUMNS, 
				OmissionAnswersTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				OmissionAnswersTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, null);
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
