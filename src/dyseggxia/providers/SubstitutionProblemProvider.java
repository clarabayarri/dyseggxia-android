package dyseggxia.providers;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.SubstitutionProblemTable;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SubstitutionProblem;

public class SubstitutionProblemProvider extends AbstractProblemProvider {
	
	public SubstitutionProblemProvider(DatabaseHelper helper, AnswerProviderI answerProvider) {
		this.helper = helper;
		this.answerProvider = answerProvider;
	}
	
	public int findNumProblemsForLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		String consulta = "SELECT Count(*) FROM " + SubstitutionProblemTable.TABLE_NAME + " WHERE " + SubstitutionProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber;
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}
	
	public Problem findProblem(int levelNumber, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(SubstitutionProblemTable.TABLE_NAME, SubstitutionProblemTable.ALL_COLUMNS, 
				SubstitutionProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				SubstitutionProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.isAfterLast()) {
			cursor.close();
			throw new Exception("no problem found");
		}
		return mapProblem(cursor);
	}
	
	@Override
	protected Problem mapProblem(Cursor cursor) {
		int problemNumber = cursor.getInt(0);
		String problemWord = cursor.getString(1);
		int insertionIndex = cursor.getInt(2);
		int levelNumber = cursor.getInt(3);
		cursor.close();

		SubstitutionProblem problem = new SubstitutionProblem(levelNumber,problemNumber,problemWord,insertionIndex);
		findAnswers(problem);

		return problem;
	}
	
	protected void findAnswers(SubstitutionProblem problem) {
		List<String> answers = answerProvider.getAnswersForProblem(problem);
		problem.addAnswers(answers);
	}

}
