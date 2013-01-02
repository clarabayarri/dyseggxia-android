package dyseggxia.viewControllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;

public class CompleteDialogController {
	
	private CubesActivity context;
	private String text;
	private RelativeLayout view;
	private FrameLayout mainView;
	
	public CompleteDialogController(CubesActivity context, String text) {
		this.context = context;
		this.text = text;
	}
	
	public void bindView(View parentView) {
		mainView = (FrameLayout) parentView.findViewById(R.id.completeDialogView);
		
		view = (RelativeLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.completedialog, null);
		mainView.addView(view);
		mainView.setVisibility(View.VISIBLE);
		
		TextView label = (TextView) view.findViewById(R.id.completedialogSolutionLabel);
		label.setText(this.text);
	}

}
