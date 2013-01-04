package dyseggxia.providers;

import android.database.Cursor;
import dyseggxia.domainModel.Problem;

public abstract class AbstractProblemProvider {

	protected AnswerProviderI answerProvider;
	
	protected abstract Problem mapProblem(Cursor cursor);
	
}
