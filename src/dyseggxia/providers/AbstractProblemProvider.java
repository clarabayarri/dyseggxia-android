package dyseggxia.providers;

import android.database.Cursor;
import dyseggxia.domainModel.Problem;

public abstract class AbstractProblemProvider {

	protected DatabaseHelper helper;
	protected AnswerProviderI answerProvider;
	
	public abstract int findNumProblemsForLevel(int levelNumber);
	
	public abstract Problem findProblem(int levelNumber, int problemId) throws Exception;
	
	protected abstract Problem mapProblem(Cursor cursor);
	
}
