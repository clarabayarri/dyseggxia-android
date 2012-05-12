package dyseggxia.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class AssetsReader {
	
	private Context context;
	
	public AssetsReader(Context context) {
		this.context = context;
	}

	public List<String> readSentenceProblems(String type, int level) {
		String[] data = readProblemsFromFile(type + "-" + level + ".txt");
		List<String> result = new ArrayList<String>();
		for(String line : data) {
			result.add(line);
		}
		return result;
	}
	
	public List<WordProblemDataTuple> readWordProblems(String type, int level) {
		String[] readData = readProblemsFromFile(type + "-" + level + ".txt");
		List<WordProblemDataTuple> result = new ArrayList<WordProblemDataTuple>();
		for(int i = 0; i < readData.length; ++i) {
			String[] data = readData[i].split("(\\s)+");
			if(data.length >= 3) {
				WordProblemDataTuple newData = new WordProblemDataTuple();
				newData.word = data[0];
				newData.startIndex = Integer.valueOf(data[1].substring(0, 1));
				newData.endIndex = Integer.valueOf(data[1].substring(data[1].length()-1, data[1].length()));
				System.out.println(data[2]);
				newData.answers = data[2].split("\\|");
				System.out.println(newData.answers);
				result.add(newData);
			}
		}
		return result;
	}
	
	public String[] readProblemsFromFile(String file) {
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
