package dyseggxia.utilities;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;


public class AssetsReader {

	private static final String[] types = {"insercion"};
	private static final String[] fileNames = {"insercion.txt"};
	
	private Context context;
	
	public AssetsReader(Context context) {
		this.context = context;
	}
	
	public String[] readWordProblemsFromFile(String type) {
		for(int i = 0; i < types.length; ++i) {
			if (type.equals(types[i])) {
				return readFile(fileNames[i]);
			}
		}
		return new String[0];
	}
	
	private String[] readFile(String fileName) {
		try {
            InputStream is = context.getAssets().open(fileName);

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
