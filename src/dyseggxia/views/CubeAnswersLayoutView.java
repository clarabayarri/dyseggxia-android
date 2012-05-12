package dyseggxia.views;

import java.util.List;

import android.widget.LinearLayout;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.CubeController;

public class CubeAnswersLayoutView extends CubeLayoutView {
	
	public CubeAnswersLayoutView(CubeController controller, List<String> letters, boolean draggable) {
		super(controller, draggable);
		this.letters = letters;
		this.letterResourceDrawable = R.drawable.cubosficha;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		initializeBox();
	}
	
	private void initializeBox() {
		int padding = (int)controller.getContext().getResources().getDimension(R.dimen.margin_cubes_box);
		int layoutMaxHeight = 0;
		int layoutMaxWidth = 0;
		if(this.layout.getOrientation() == LinearLayout.HORIZONTAL) {
			layoutMaxHeight = layout.getChildAt(0).getLayoutParams().height + 2*padding;
			layoutMaxWidth = layout.getChildAt(0).getLayoutParams().width * letters.size() + 2*padding;
		}
		else {
			layoutMaxWidth = layout.getChildAt(0).getLayoutParams().width + 4*padding;
			layoutMaxHeight = layout.getChildAt(0).getLayoutParams().height * letters.size() + 2*padding;
		}

		layout.getLayoutParams().height = layoutMaxHeight;
		layout.getLayoutParams().width = layoutMaxWidth;
		layout.setPadding(padding, padding, padding, padding);
	}
}
