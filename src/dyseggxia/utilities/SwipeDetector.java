package dyseggxia.utilities;

import android.view.GestureDetector;
import android.view.MotionEvent;
import dyseggxia.views.ProblemWordLayout;

public class SwipeDetector extends GestureDetector.SimpleOnGestureListener {
	
	private ProblemWordLayout delegate;
	private int childHeight;
	
	public SwipeDetector(ProblemWordLayout delegate, int childHeight) {
		this.delegate = delegate;
		this.childHeight = childHeight;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (Math.abs(e1.getY() - e2.getY()) >= childHeight) {
			delegate.swipeOnCoordinate((e1.getX() + e2.getX())/2);
			return true;
		}
		return false;
	}
}