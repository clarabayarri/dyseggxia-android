package dyseggxia.viewControllers;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.views.CubeLayoutView;
import dyseggxia.views.CubeLetterView;

public abstract class CubeController {

	protected CubesActivity context;
	protected CubeLayoutView wordLayout;
	protected LinearLayout movingLayout;
	protected CubeLetterView movingImage;
	protected CubeLetterView invisibleImage;
	private Handler handler;
	
	public CubesActivity getContext() {
		return context;
	}
	
	public abstract void initializeInterface();
	
	public boolean onTouch(View view, MotionEvent event) {
		switch(event.getAction()) {
        case MotionEvent.ACTION_DOWN:
        	startMovingView(view, event);
        	break;
        case MotionEvent.ACTION_MOVE:
        	moveMovingImage(event);
            break;
        case MotionEvent.ACTION_UP:
            itemSelected(event);
            returnViewToPlace();
        	break;
        default:
            break;
        }
        return true;
	}
	
	private void startMovingView(View view, MotionEvent event){
		CubeLetterView cubeView = (CubeLetterView) view;
		if(cubeView != null) {
			CubeLetterView copyView = new CubeLetterView(cubeView.getCubeLayout(),cubeView.getTextContents(),true);
			FrameLayout.LayoutParams newParams = new FrameLayout.LayoutParams(cubeView.getLayoutParams().width, cubeView.getLayoutParams().height);
			copyView.setLayoutParams(newParams);
			copyView.setVisibility(View.INVISIBLE);
			movingLayout.addView(copyView);
			movingImage = copyView;
			invisibleImage = cubeView;
		}
	}
	
	private void moveMovingImage(MotionEvent event){
		invisibleImage.setVisibility(View.INVISIBLE);
		movingImage.setVisibility(View.VISIBLE);
		setTouchCoordinatesToMovingImageLayout(event);
	}
	
	private void setTouchCoordinatesToMovingImageLayout(MotionEvent event){
		LayoutParams layoutParams = (LayoutParams) movingImage.getLayoutParams();
		int x_cord = (int)event.getRawX();
    	int y_cord = (int)event.getRawY();
        layoutParams.leftMargin = x_cord - movingImage.getWidth()/2;
        layoutParams.topMargin = y_cord - movingImage.getHeight()/2;
        movingImage.setLayoutParams(layoutParams);
	}
	
	protected abstract void itemSelected(MotionEvent event);

	public void viewDroppedOnIndex(int index) {
		
	}
	
	private void returnViewToPlace(){
		invisibleImage.setVisibility(View.VISIBLE);
		invisibleImage = null;
		movingImage.setVisibility(View.GONE);
	}
	
	protected void success() {
		wordLayout.animateCorrectAnswer();
		System.out.println("YES!!");
		if(handler == null) handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				context.finish();
			}}, 1000);
	}
}
