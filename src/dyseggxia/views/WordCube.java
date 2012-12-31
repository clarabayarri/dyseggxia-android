package dyseggxia.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import dyseggxia.activities.R;

public class WordCube extends GenericDraggableCube {

	private View view;
	private TextView label;
	
	private Context context;
	
	public WordCube(Context context, String letter) {
		this.context = context;
		this.displayLetter = letter;
	}
	
	public View getView() {
		if (view == null) {
			view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.wordcube, null);
			view.setTag(this);
			label = (TextView) view.findViewById(R.id.cube_contents);
			label.setText(displayLetter);
		}
		return view;
	}
	
	public void changeDisplayedText(String newText) {
	    displayLetter = newText;
	    label.setText(displayLetter);
	}
}
