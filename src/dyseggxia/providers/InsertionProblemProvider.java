package dyseggxia.providers;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.InsertionProblemTable;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.domainModel.Problem;

public class InsertionProblemProvider extends AbstractProblemProvider {
	
	public InsertionProblemProvider(DatabaseHelper helper, AnswerProviderI answerProvider) {
		this.helper = helper;
		this.answerProvider = answerProvider;
	}
	
	@Override
	public int findNumProblemsForLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		String consulta = "SELECT Count(*) FROM " + InsertionProblemTable.TABLE_NAME + " WHERE " + InsertionProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber;
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}
	
	@Override
	public Problem findProblem(int levelNumber, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(InsertionProblemTable.TABLE_NAME, InsertionProblemTable.ALL_COLUMNS, 
				InsertionProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				InsertionProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber, null, null, null, null);
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
		int endIndex = cursor.getInt(3);
		int levelNumber = cursor.getInt(4);
		cursor.close();

		InsertionProblem problem = new InsertionProblem(levelNumber,problemNumber,problemWord,insertionIndex,endIndex);
		findAnswers(problem);

		return problem;
	}
	
	protected void findAnswers(InsertionProblem problem) {
		List<String> answers = answerProvider.getAnswersForProblem(problem);
		problem.addAnswers(answers);
	}
}
