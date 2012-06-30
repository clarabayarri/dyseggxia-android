package dyseggxia.viewControllers;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.views.CubeWordLayoutView;
import dyseggxia.views.ProblemWordLayout;

public class SentenceSeparationCubeController extends GenericCubesProblemViewController {

	private SentenceSeparationProblem problem;
	private List<Integer> solutionsProposed = new ArrayList<Integer>();
	//private float originalTouchX;
	
	public SentenceSeparationCubeController(CubesActivity context, SentenceSeparationProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.sentenceseparation));
		wordLayout = new ProblemWordLayout(this,problem.getDisplayedText());
		this.view.setOrientation(LinearLayout.VERTICAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
		context.findViewById(R.id.cubeAnswerLayout).setVisibility(View.GONE);*/
	}
	
	@Override
	protected void startMovingView(View view, MotionEvent event) {
		super.startMovingView(view, event);
		//this.originalTouchX = event.getRawX();
	}

	@Override
	protected void moveMovingImage(MotionEvent event) {
		super.moveMovingImage(event);
		if(movingLayout.getChildCount() == 1) {
			//if(event.getRawX() > originalTouchX) {
				int numSpaces = getNumSpacesBefore(movingImage.getIndex());
				//wordLayout.moveRightLettersToLayout(movingImage.getIndex()+numSpaces, movingLayout);
//			}
//			else if(event.getRawX() < originalTouchX) {
//				wordLayout.moveLeftLettersToLayout(movingImage.getIndex(), movingLayout);
//			}
		}
	}
	
	private int getNumSpacesBefore(int index) {
		int count = 0;
		for(Integer answer : solutionsProposed) {
			if(answer < index) ++count;
		}
		return count;
	}

	@Override
	protected void setTouchCoordinatesToMovingImageLayout(MotionEvent event){
		LayoutParams layoutParams = (LayoutParams) movingImage.getLayoutParams();
		int x_cord = (int)event.getRawX();
		int y_cord = (int)event.getRawY();
        layoutParams.leftMargin = x_cord - movingImage.getWidth()/2;
        layoutParams.topMargin = y_cord - movingImage.getHeight()/2;
        movingImage.setLayoutParams(layoutParams);
	}
	
	@Override
	protected void itemSelected(MotionEvent event) {
		int index = movingImage.getIndex();
		movingLayout.removeAllViews();
		//wordLayout.makeAllVisible();
		//wordLayout.addSpace(index);
		solutionsProposed.add(index);
		/*if(problem.getNumAnswers() <= solutionsProposed.size()) {
			String wrongWord = wordLayout.getDisplayedText();
			System.out.println("guess: " + wrongWord);
			if(problem.isCorrectAnswer(wrongWord)) {
				//wordLayout.removeChild(invisibleImage);
				success();
			}
			else {
				fail(wrongWord);
			}
		}*/
	}
	
	@Override
	protected void fail(String chosenAnswer) {
		super.fail(chosenAnswer);
		solutionsProposed.clear();
		initLayout();
	}

}
