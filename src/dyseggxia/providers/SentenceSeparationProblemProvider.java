package dyseggxia.providers;

import android.database.Cursor;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.Problem;
import dyseggxia.domainModel.SentenceSeparationProblem;

public class SentenceSeparationProblemProvider extends AbstractProblemProvider {

	public SentenceSeparationProblemProvider(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	protected Problem mapProblem(Cursor cursor) {
		int problemNumber = cursor.getInt(ProblemTable.COLUMN_NUMBER_INDEX);
		String problemWord = cursor.getString(ProblemTable.COLUMN_WORD_INDEX);
		int levelNumber = cursor.getInt(ProblemTable.COLUMN_LEVEL_NUMBER_INDEX);
		String language = cursor.getString(ProblemTable.COLUMN_LEVEL_LANGUAGE_INDEX);
		cursor.close();

		SentenceSeparationProblem problem = new SentenceSeparationProblem(levelNumber, language,
				problemNumber, problemWord);

		return problem;
	}

}
