package dyseggxia.viewControllers;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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
import dyseggxia.domainControllers.AchievementController;
import dyseggxia.domainModel.GenericAchievement;
import dyseggxia.factories.ControllerFactory;

public class CompleteDialogController {
	
	private static final int FISH_START_OFFSET = 200;
	private static final int FISH_ANIMATION_STEP = 120;
	private static final int PENGUIN_ENTER_TIME = 200;
	
	private static final int targetX = -50;
	private static final int targetY = -80;
	private static final float[] xvalues = {0.0f, 0.2f, 0.6f, 1.0f, 1.4f, 1.6f, 1.8f, 1.8f};
	private static final float[] yoriginvalues = {-1.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
	private static final float[] ytargetvalues = {0.0f, 0.4f, 0.8f, 1.0f, -0.2f, -0.6f, -1.0f, -2.5f};
	
	private CubesActivity context;
	private AchievementController achievementController;
	private String text;
	private int score;
	private RelativeLayout view;
	private FrameLayout mainView;
	
	private View fishImage;
	private ImageView scoreImage;
	private ImageView penguinImage;
	
	public CompleteDialogController(CubesActivity context, String text, int score) {
		this.context = context;
		this.text = text;
		this.score = score;
		this.achievementController = ControllerFactory.getInstance(context).getAchievementController();
	}
	
	public void bindView(View parentView) {
		mainView = (FrameLayout) parentView.findViewById(R.id.completeDialogView);
		
		view = (RelativeLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.completedialog, null);
		mainView.addView(view);
		mainView.setVisibility(View.VISIBLE);
		penguinImage = (ImageView) view.findViewById(R.id.completedialogPenguinImage);
		
		TextView label = (TextView) view.findViewById(R.id.completedialogSolutionLabel);
		label.setText(this.text);
		
		List<GenericAchievement> toAnimate = achievementController.achievementsWithValueChange();
		int delay = 0;
		for (GenericAchievement achievement : toAnimate) {
			delay += PENGUIN_ENTER_TIME*2 + achievement.animationDuration() + 2*FISH_START_OFFSET;
			animateAchievement(achievement);
		}
		
		if (score > 0) {
			fishImage = view.findViewById(R.id.completedialogFishImage);
			scoreImage = (ImageView) view.findViewById(R.id.completedialogFishScoreImage);
			
			animateFish(delay);
			animateScore(delay);
		}
	}
	
	private void animateFish(int delay) {
		fishImage.postDelayed(new Runnable() {
			public void run() {
				fishImage.setVisibility(View.VISIBLE);
			}}, delay + FISH_START_OFFSET);
		
		AnimationSet set = new AnimationSet(true);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.setFillAfter(true);
		
		for (int i = 0; i < xvalues.length; ++i) {
			TranslateAnimation translate = 
					new TranslateAnimation(0.0f, xvalues[i]*targetX, 
											yoriginvalues[i]*targetY, ytargetvalues[i]*targetY);
			translate.setDuration(FISH_ANIMATION_STEP);
			translate.setStartOffset(delay + FISH_START_OFFSET + FISH_ANIMATION_STEP*i);
			set.addAnimation(translate);
		}
		
		fishImage.startAnimation(set);
	}
	
	private void animateScore(int delay) {
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
		animation.setStartOffset(delay + 3 * FISH_ANIMATION_STEP);
		
		AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
		animation2.setDuration(FISH_ANIMATION_STEP);
		animation2.setStartOffset(delay + 16 * FISH_ANIMATION_STEP);
		
		set.addAnimation(animation);
		set.addAnimation(animation2);
		scoreImage.startAnimation(set);
	}
	
	private void animateAchievement(GenericAchievement achievement) {
		penguinImage.setImageResource(achievement.animationImages());
		penguinImage.setVisibility(View.VISIBLE);
		final AnimationDrawable animation = (AnimationDrawable) penguinImage.getDrawable();
		penguinImage.postDelayed(new Runnable() {
		    public void run() {
		        if ( animation != null ) animation.start();
		      }
		}, PENGUIN_ENTER_TIME + FISH_START_OFFSET);
		
		AnimationSet set = new AnimationSet(true);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.setFillAfter(true);
		
		TranslateAnimation translate1 = 
				new TranslateAnimation(0.0f, 0.0f, 
										300.0f, 0.0f);
		translate1.setDuration(PENGUIN_ENTER_TIME);
		translate1.setStartOffset(FISH_START_OFFSET);
		set.addAnimation(translate1);
		
		TranslateAnimation translate2 = 
				new TranslateAnimation(0.0f, 0.0f, 
										0.0f, 500.0f);
		translate2.setDuration(PENGUIN_ENTER_TIME);
		translate2.setStartOffset(PENGUIN_ENTER_TIME + 2*FISH_START_OFFSET + achievement.animationDuration());
		set.addAnimation(translate2);
		
		AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
		alpha.setDuration(PENGUIN_ENTER_TIME);
		alpha.setStartOffset(PENGUIN_ENTER_TIME + 2*FISH_START_OFFSET + achievement.animationDuration());
		set.addAnimation(alpha);
		
		penguinImage.postDelayed(new Runnable() {
		    public void run() {
		        penguinImage.setVisibility(View.GONE);
		      }
		}, PENGUIN_ENTER_TIME*2 + achievement.animationDuration() + 2*FISH_START_OFFSET);
		
		penguinImage.startAnimation(set);
	}

}
