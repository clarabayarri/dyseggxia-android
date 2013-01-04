package dyseggxia.providers;

import java.util.List;
import java.util.Random;

import android.database.Cursor;
import dyseggxia.databaseTableDefinitions.ProblemTable;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.domainModel.Problem;

public class OmissionProblemProvider extends AbstractProblemProvider{
	
	public OmissionProblemProvider(AnswerProviderI answerProvider) {
		this.answerProvider = answerProvider;
	}
	
	@Override
	protected Problem mapProblem(Cursor cursor) {
		int id = cursor.getInt(ProblemTable.COLUMN_ID_INDEX);
		int problemNumber = cursor.getInt(ProblemTable.COLUMN_NUMBER_INDEX);
		String problemWord = cursor.getString(ProblemTable.COLUMN_WORD_INDEX);
		int omissionIndex = cursor.getInt(ProblemTable.COLUMN_INSERTION_INDEX_INDEX);
		int endIndex = cursor.getInt(ProblemTable.COLUMN_END_INDEX_INDEX);
		int levelNumber = cursor.getInt(ProblemTable.COLUMN_LEVEL_NUMBER_INDEX);
		String language = cursor.getString(ProblemTable.COLUMN_LEVEL_LANGUAGE_INDEX);
		cursor.close();
		
		OmissionProblem problem = new OmissionProblem(id, levelNumber, language, problemNumber,
				problemWord, omissionIndex, endIndex);
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
