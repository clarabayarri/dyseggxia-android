package dyseggxia.providers;

import java.util.List;

import android.database.Cursor;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.domainModel.Problem;

public class DerivationProblemProvider extends AbstractProblemProvider {
	
	public DerivationProblemProvider(DatabaseHelper helper, AnswerProviderI answerProvider) {
		this.helper = helper;
		this.answerProvider = answerProvider;
	}
	
	@Override
	protected Problem mapProblem(Cursor cursor) {
		int problemNumber = cursor.getInt(ProblemTable.COLUMN_NUMBER_INDEX);
		String problemWord = cursor.getString(ProblemTable.COLUMN_WORD_INDEX);
		int insertionIndex = cursor.getInt(ProblemTable.COLUMN_INSERTION_INDEX_INDEX);
		int endIndex = problemWord.length()-1;
		int levelNumber = cursor.getInt(ProblemTable.COLUMN_LEVEL_NUMBER_INDEX);
		String language = cursor.getString(ProblemTable.COLUMN_LEVEL_LANGUAGE_INDEX);
		cursor.close();

		DerivationProblem problem = new DerivationProblem(levelNumber, language, problemNumber, 
				problemWord, insertionIndex, endIndex);
		findAnswers(problem);

		return problem;
	}
	
	protected void findAnswers(DerivationProblem problem) {
		List<String> answers = answerProvider.getAnswersForProblem(problem);
		problem.setAnswers(answers);
	}

}
