package dyseggxia.providers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.DerivationAnswersTable;
import dyseggxia.domainModel.Problem;

public class DerivationAnswerProvider extends AbstractAnswerProvider {

	public DerivationAnswerProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public List<String> getAnswersForProblem(Problem problem) {
		SQLiteDatabase database = helper.getWritableDatabase();
		List<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(DerivationAnswersTable.TABLE_NAME, DerivationAnswersTable.ALL_COLUMNS, 
				DerivationAnswersTable.COLUMN_LEVEL_NUMBER + "=" + problem.getLevel() + " AND " + 
				DerivationAnswersTable.COLUMN_PROBLEM_NUMBER + "=" + problem.getNumber(), null, null, null, null);
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
