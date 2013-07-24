package dyseggxia.viewControllers.howto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import dyseggxia.activities.R;

public class InsertionHowToViewController implements HowToViewControllerI {

	private Context context;
	private View view;
	private View hand;
	private View movingCube;
	private View blankCube;
	
	private final static int STEP_DURATION = 500;
	
	public InsertionHowToViewController(Context context) {
		this.context = context;
	}
	
	public void animate() {
		AnimationSet set = new AnimationSet(true);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.setFillAfter(true);
		
		int startX = context.getResources().getDisplayMetrics().widthPixels;
		int startY = context.getResources().getDisplayMetrics().heightPixels;
		int targetX = startX / 2;
		int targetY = startY * 2 / 3;
		TranslateAnimation translate1 = new TranslateAnimation(startX, targetX, startY, targetY);
		translate1.setDuration(STEP_DURATION);
		set.addAnimation(translate1);
		
		int targetX2 = targetX;
		int targetY2 = targetY / 3;
		TranslateAnimation translate2 = new TranslateAnimation(targetX, targetX2, targetY, targetY2);
		translate2.setStartOffset(STEP_DURATION);
		translate2.setDuration(STEP_DURATION);
		set.addAnimation(translate2);
		
		hand.startAnimation(set);
	}

	public void bindView(ViewGroup parentView) {
		if (view == null) {
			view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.insertionhowto, null);
			hand = view.findViewById(R.id.insertionhowtohand);
			blankCube = view.findViewById(R.id.insertionhowtoblankcube);
			movingCube = view.findViewById(R.id.insertionhowtomovingcube);
			parentView.addView(view);
		}
		view.setVisibility(View.VISIBLE);
	}

	public int getDuration() {
		// TODO Auto-generated method stub
		return STEP_DURATION*5;
	}

	public void hide() {
		view.setVisibility(View.GONE);
	}
}
