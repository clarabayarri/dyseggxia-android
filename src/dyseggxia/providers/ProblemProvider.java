package dyseggxia.providers;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ProviderFactory;

public class ProblemProvider implements ProblemProviderI {
	
	private ProviderFactory providerFactory;
	private DatabaseHelper helper;
	
	public ProblemProvider(Context context, DatabaseHelper helper) {
		providerFactory = ProviderFactory.getInstance(context);
		this.helper = helper;
	}
	
	public Problem findProblem(int levelNumber, String language, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(ProblemTable.TABLE_NAME, ProblemTable.ALL_COLUMNS, 
				ProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				ProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber +
				" AND " + ProblemTable.COLUMN_LEVEL_LANGUAGE + " = '" + language + "'", null, null, null, null);
		cursor.moveToFirst();
		if(cursor.isAfterLast()) {
			cursor.close();
			throw new Exception("no problem found");
		}
		Problem problem = mapProblem(cursor);
		database.close();
		return problem;
	}

	private Problem mapProblem(Cursor cursor) {
		String type = cursor.getString(ProblemTable.COLUMN_TYPE_INDEX);
		int id = cursor.getInt(ProblemTable.COLUMN_ID_INDEX);
		int problemNumber = cursor.getInt(ProblemTable.COLUMN_NUMBER_INDEX);
		String problemWord = cursor.getString(ProblemTable.COLUMN_WORD_INDEX);
		int levelNumber = cursor.getInt(ProblemTable.COLUMN_LEVEL_NUMBER_INDEX);
		String language = cursor.getString(ProblemTable.COLUMN_LEVEL_LANGUAGE_INDEX);
		cursor.close();

		Problem problem = new Problem(id, levelNumber, language, problemNumber, type,
				problemWord);
		findLetters(problem);
		findAnswers(problem);

		return problem;
	}
	
	private void findLetters(Problem problem) {
		List<String> answers = providerFactory.getLetterProvider().getLettersForProblem(problem);
		problem.setDisplayedText(answers);
	}
	
	private void findAnswers(Problem problem) {
		List<String> answers = providerFactory.getAnswerProvider().getAnswersForProblem(problem);
		problem.setAnswers(answers);
	}

}
