package dyseggxia.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import dyseggxia.activities.R;

public class AnswerCube extends GenericDraggableCube {

	private View view;
	private TextView label;
	private boolean vertical;
	
	private Context context;
	
	public AnswerCube(Context context, String letter, boolean vertical) {
		this.context = context;
		this.displayLetter = letter;
		this.vertical = vertical;
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	public View getView() {
		if (view == null) {
			if (vertical) {
				view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.answerverticalcube, null);
			} else {
				view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.answercube, null);
			}
			view.setTag(this);
			label = (TextView) view.findViewById(R.id.cube_contents);
			label.setText(displayLetter);
		}
		return view;
	}
}
