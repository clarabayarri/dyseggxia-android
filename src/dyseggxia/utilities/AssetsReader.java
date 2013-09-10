package dyseggxia.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;


public class AssetsReader {
	
	private Context context;
	private List<WordProblemDataTuple> problems;
	private static final String FILE_NAME = "problems-v3.csv";
	
	public AssetsReader(Context context) {
		this.context = context;
	}
	
	public List<WordProblemDataTuple> getProblems() {
		if (problems == null) read();
		return problems;
	}
	
	private void read() {
		problems = readProblems();
	}
	
	@SuppressLint("DefaultLocale")
	private List<WordProblemDataTuple> readProblems() {
		List<WordProblemDataTuple> result = new ArrayList<WordProblemDataTuple>();
		String[] readData = readProblemsFromFile(FILE_NAME);
		for(int i = 1; i < readData.length; ++i) {
			String[] fields = readData[i].split(",");
			if (fields.length >= 11) {
				WordProblemDataTuple newData = new WordProblemDataTuple();
				newData.number = Integer.valueOf(fields[0]);
				newData.language = fields[1].trim().toLowerCase();
				newData.levelNumber = Integer.valueOf(fields[2]);
				newData.type = fields[3].trim();
				//letters
				newData.letters = new ArrayList<String>();
				String base = fields[4].trim();
				while (base.length() > 0) {
					if (base.startsWith("[")) {
						base = base.substring(1);
						String[] parts = base.split("]");
						String[] parts2 = parts[0].split("\\|");
						for (String let : parts2) {
							newData.letters.add(let);
						}
						base = base.substring(parts[0].length()+1);
					} else {
						String let = base.substring(0, 1);
						newData.letters.add(let);
						base = base.substring(1);
					}
				}
				for (int j = 0; j < newData.letters.size(); ++j) {
					if (newData.letters.get(j).equals("_")) {
						newData.letters.set(j, " ");
					}
				}
				newData.word = fields[5].trim();
				newData.answers = new ArrayList<String>();
				for (int j = 6; j < 12; ++j) {
					String answer = fields[j].trim();
					if (answer.length() > 0) {
						newData.answers.add(answer);
					}
				}
				
				result.add(newData);
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
            String text = new String(buffer, "UTF-8");
            return text.split("\\n");

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
	}
	
}
