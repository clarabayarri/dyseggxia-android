package dyseggxia.providers;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.DerivationProblemTable;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.Problem;

public class DerivationProblemProvider extends AbstractProblemProvider {
	
	public DerivationProblemProvider(DatabaseHelper helper, AnswerProviderI answerProvider) {
		this.helper = helper;
		this.answerProvider = answerProvider;
	}
	
	@Override
	public int findNumProblemsForLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		String consulta = "SELECT Count(*) FROM " + DerivationProblemTable.TABLE_NAME + " WHERE " + DerivationProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber;
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}
	
	@Override
	public Problem findProblem(int levelNumber, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(DerivationProblemTable.TABLE_NAME, DerivationProblemTable.ALL_COLUMNS, 
				DerivationProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				DerivationProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber, null, null, null, null);
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
		int endIndex = problemWord.length()-1;
		int levelNumber = cursor.getInt(3);
		cursor.close();

		DerivationProblem problem = new DerivationProblem(levelNumber, problemNumber, problemWord, insertionIndex, endIndex);
		findAnswers(problem);

		return problem;
	}
	
	protected void findAnswers(DerivationProblem problem) {
		List<String> answers = answerProvider.getAnswersForProblem(problem);
		problem.addAnswers(answers);
	}

}
