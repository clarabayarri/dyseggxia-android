package dyseggxia.viewControllers;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.views.CubeWordLayoutView;

public class SentenceSeparationCubeController extends CubeController {

	private SentenceSeparationProblem problem;
	private List<Integer> solutionsProposed = new ArrayList<Integer>();
	private float originalTouchX;
	
	public SentenceSeparationCubeController(CubesActivity context, SentenceSeparationProblem problem) {
		this.context = context;
		this.problem = problem;
		context.findViewById(R.id.direccion).setVisibility(View.VISIBLE);
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}
	
	private void loadViews() {
		wordLayout = new CubeWordLayoutView(this,problem.getWord(),true);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
		context.findViewById(R.id.cubeAnswerLayout).setVisibility(View.GONE);
	}
	
	@Override
	protected void startMovingView(View view, MotionEvent event) {
		super.startMovingView(view, event);
		this.originalTouchX = event.getRawX();
	}

	@Override
	protected void moveMovingImage(MotionEvent event) {
		super.moveMovingImage(event);
		if(movingLayout.getChildCount() == 1) {
			//if(event.getRawX() > originalTouchX) {
				int numSpaces = getNumSpacesBefore(movingImage.getIndex());
				wordLayout.moveRightLettersToLayout(movingImage.getIndex()+numSpaces, movingLayout);
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
		wordLayout.makeAllVisible();
		//wordLayout.addSpace(index);
		solutionsProposed.add(index);
		if(problem.getNumAnswers() <= solutionsProposed.size()) {
			String wrongWord = problem.getWord();
			for(Integer i : solutionsProposed) {
				wrongWord = wrongWord.substring(0, i) + " " + wrongWord.substring(i,wrongWord.length());
			}
			System.out.println("guess: " + wrongWord);
			if(problem.isCorrectAnswer(wrongWord)) {
				//wordLayout.removeChild(invisibleImage);
				success();
			}
			else {
				String answer = "";
				for(Integer i : solutionsProposed) answer = answer + i.toString() + " ";
				System.out.println(answer);
				fail(answer);
			}
		}
	}
	
	@Override
	protected void fail(String chosenAnswer) {
		super.fail(chosenAnswer);
		solutionsProposed.clear();
		initializeInterface();
	}

}
