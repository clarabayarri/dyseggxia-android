package dyseggxia.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class AssetsReader {
	
	private Context context;
	private List<WordProblemDataTuple> wordProblems;
	private static final String[] wordProblemTypes = {"insertion", "omission", "substitution", "derivation"};
	private static final String[] sentenceProblemTypes = {"separation"};
	private static final String[] languages = {"es", "en"};
	private static final int numLevels = 3;
	
	public AssetsReader(Context context) {
		this.context = context;
	}
	
	public List<WordProblemDataTuple> getWordProblems() {
		if (wordProblems == null) read();
		return wordProblems;
	}
	
	private void read() {
		wordProblems = new ArrayList<WordProblemDataTuple>();
		for (int level = 0; level < numLevels; ++level) {
			for (String language : languages) {
				wordProblems.addAll(readWordProblems(level, language));
			}
		}
	}
	
	private List<WordProblemDataTuple> readWordProblems(int level, String lang) {
		int trueLevel = level+1;
		List<WordProblemDataTuple> result = new ArrayList<WordProblemDataTuple>();
		int index = 0;
		for (String type : wordProblemTypes) {
			String[] readData = readProblemsFromFile(type + "-" + trueLevel + "-" + lang + ".txt");
			for(int i = 0; i < readData.length; ++i) {
				String[] data = readData[i].split("(\\s)+");
				if(data.length >= 3) {
					WordProblemDataTuple newData = new WordProblemDataTuple();
					newData.word = data[0];
					newData.startIndex = Integer.valueOf(data[1].substring(0, 1));
					newData.endIndex = Integer.valueOf(data[1].substring(data[1].length()-1, data[1].length()));
					newData.answers = data[2].split("\\|");
					newData.levelNumber = level;
					newData.language = lang;
					newData.number = index;
					newData.type = type;
					result.add(newData);
					++index;
				}
			}
		}
		for (String type : sentenceProblemTypes) {
			String[] data = readProblemsFromFile(type + "-" + trueLevel + "-" + lang + ".txt");
			for(int i = 0; i < data.length; ++i) {
				String line = data[i];
				WordProblemDataTuple problem = new WordProblemDataTuple();
				problem.word = line;
				problem.levelNumber = level;
				problem.language = lang;
				problem.number = index;
				problem.type = type;
				problem.startIndex = 0;
				problem.endIndex = 0;
				result.add(problem);
				++index;
			}
		}
		return result;
	}
	
	private String[] readProblemsFromFile(String file) {
		try {
            InputStream is = context.getAssets().open(file);

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);
            return text.split("\\n");

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
	}
	
}
