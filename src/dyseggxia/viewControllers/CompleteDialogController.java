package dyseggxia.viewControllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;

public class CompleteDialogController {
	
	private static final int FISH_START_OFFSET = 200;
	private static final int FISH_ANIMATION_STEP = 120;
	
	private static final int targetX = -50;
	private static final int targetY = -80;
	private static final float[] xvalues = {0.0f, 0.2f, 0.6f, 1.0f, 1.4f, 1.6f, 1.8f, 1.8f};
	private static final float[] yoriginvalues = {-1.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
	private static final float[] ytargetvalues = {0.0f, 0.4f, 0.8f, 1.0f, -0.2f, -0.6f, -1.0f, -2.5f};
	
	private CubesActivity context;
	private String text;
	private int score;
	private RelativeLayout view;
	private FrameLayout mainView;
	
	private View fishImage;
	private ImageView scoreImage;
	
	public CompleteDialogController(CubesActivity context, String text, int score) {
		this.context = context;
		this.text = text;
		this.score = score;
	}
	
	public void bindView(View parentView) {
		mainView = (FrameLayout) parentView.findViewById(R.id.completeDialogView);
		
		view = (RelativeLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.completedialog, null);
		mainView.addView(view);
		mainView.setVisibility(View.VISIBLE);
		
		TextView label = (TextView) view.findViewById(R.id.completedialogSolutionLabel);
		label.setText(this.text);
		
		if (score > 0) {
			fishImage = view.findViewById(R.id.completedialogFishImage);
			scoreImage = (ImageView) view.findViewById(R.id.completedialogFishScoreImage);
			
			animateFish();
			animateScore();
		}
	}
	
	private void animateFish() {
		fishImage.setVisibility(View.VISIBLE);
		AnimationSet set = new AnimationSet(true);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.setFillAfter(true);
		
		for (int i = 0; i < xvalues.length; ++i) {
			TranslateAnimation translate = 
					new TranslateAnimation(0.0f, xvalues[i]*targetX, 
											yoriginvalues[i]*targetY, ytargetvalues[i]*targetY);
			translate.setDuration(FISH_ANIMATION_STEP);
			translate.setStartOffset(FISH_START_OFFSET + FISH_ANIMATION_STEP*i);
			set.addAnimation(translate);
		}
		
		fishImage.startAnimation(set);
	}
	
	private void animateScore() {
		if (score == 3) {
			scoreImage.setImageResource(R.drawable.plus3);
		} else if (score == 2) {
			scoreImage.setImageResource(R.drawable.plus2);
		}
		
		AnimationSet set = new AnimationSet(true);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.setFillAfter(true);
		
		scoreImage.setVisibility(View.VISIBLE);
		
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(FISH_ANIMATION_STEP);
		animation.setStartOffset(3 * FISH_ANIMATION_STEP);
		
		AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
		animation2.setDuration(FISH_ANIMATION_STEP);
		animation2.setStartOffset(16 * FISH_ANIMATION_STEP);
		
		set.addAnimation(animation);
		set.addAnimation(animation2);
		scoreImage.startAnimation(set);
	}

}
