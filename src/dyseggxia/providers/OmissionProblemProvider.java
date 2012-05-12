package dyseggxia.providers;

import java.util.List;
import java.util.Random;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dyseggxia.databaseTableDefinitions.OmissionProblemTable;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.Problem;

public class OmissionProblemProvider extends AbstractProblemProvider{
	
	public OmissionProblemProvider(DatabaseHelper helper, AnswerProviderI answerProvider) {
		this.helper = helper;
		this.answerProvider = answerProvider;
	}
	
	public int findNumProblemsForLevel(int levelNumber) {
		SQLiteDatabase database = helper.getWritableDatabase();
		String consulta = "SELECT Count(*) FROM " + OmissionProblemTable.TABLE_NAME + " WHERE " + OmissionProblemTable.COLUMN_LEVEL_NUMBER + " = " + levelNumber;
		Cursor cursor = database.rawQuery(consulta, null);
		cursor.moveToFirst();
		int numRows = cursor.getInt(0);
		cursor.close();
		return numRows;
	}
	
	public Problem findProblem(int levelNumber, int problemId) throws Exception {
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = database.query(OmissionProblemTable.TABLE_NAME, OmissionProblemTable.ALL_COLUMNS, 
				OmissionProblemTable.COLUMN_NUMBER + "=" + problemId + " AND " + 
				OmissionProblemTable.COLUMN_LEVEL_NUMBER + "=" + levelNumber, null, null, null, null);
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
		int omissionIndex = cursor.getInt(2);
		int endIndex = cursor.getInt(3);
		int levelNumber = cursor.getInt(4);
		cursor.close();
		
		OmissionProblem problem = new OmissionProblem(levelNumber,problemNumber,problemWord, omissionIndex, endIndex);
		findInsertedLetter(problem);
		
		return problem;
	}
	
	private void findInsertedLetter(OmissionProblem problem) {
		List<String> answers = answerProvider.getAnswersForProblem(problem);
		int index = getRandomIndex(answers.size());
		problem.setInsertedLetter(answers.get(index));
	}

	private int getRandomIndex(int size) {
		Random random = new Random();
		int index = random.nextInt(size);
		return Math.abs(index);
	}
}
