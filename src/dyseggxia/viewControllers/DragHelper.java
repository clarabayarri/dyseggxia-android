package dyseggxia.viewControllers;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import dyseggxia.views.AnswerCube;
import dyseggxia.views.WordCube;

public class DragHelper {

	public interface DragElementListener {
		public void onDragStarted(View view);
		public void onDragEnded(View view);
		public void onDragBecameClick(View view);
	}
	
	public interface DropElementListener {
		public void onDrop(View view, float[] position);
	}
	
	private DragElementListener dragListener;
	private DropElementListener dropListener;
	
	private View originalDraggedView;
	private FrameLayout movingView;
	private float[] initialTouchDelta;
	private float[] initialTouchPosition;
	private float totalWidthMargin;
	private float totalHeightMargin;
	private int top;
	private int left;
	private int movingViewWidth = 0;
	private int movingViewHeight = 0;
	
	public DragHelper(View movingView) {
		this.movingView = (FrameLayout) movingView;
	}
	
	public void setDragElementListener(DragElementListener listener) {
		this.dragListener = listener;
	}
	
	public void setDropElementListener(DropElementListener listener) {
		this.dropListener = listener;
	}
	
	public boolean onTouch(MotionEvent event, View view) {
		if (view == null) return false;
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			initVariables(event, view);
			initMovingView();
			updateMovingView(event);
			dragListener.onDragStarted(view);
			break;
		case MotionEvent.ACTION_MOVE:
			updateMovingView(event);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_OUTSIDE:
			dragListener.onDragEnded(originalDraggedView);
			if (isWithinClickDistance(event)) {
				Log.i("CLARA", "click!");
				dragListener.onDragBecameClick(originalDraggedView);
			} else {
				dropListener.onDrop(originalDraggedView, getPosition(event));
			}
			clean();
			break;
		}
		return true;
	}
	
	private boolean isWithinClickDistance(MotionEvent event) {
		float[] position = getPosition(event);
		return (position[0] >= (initialTouchPosition[0]-10) && 
				position[1] >= (initialTouchPosition[1]-10) &&
				position[0] <= (initialTouchPosition[0] + originalDraggedView.getWidth() + 10) &&
				position[1] <= (initialTouchPosition[1] + originalDraggedView.getHeight() + 10));
	}
	
	private float[] getPosition(MotionEvent event) {
		return new float[] {event.getX() + left, event.getY() + top};
	}
	
	private float[] getDelta(MotionEvent event, View view) {
		return new float[] {event.getX() - view.getLeft(), event.getY() - view.getTop()};
	}
	
	private void initVariables(MotionEvent event, View view) {
		originalDraggedView = view;
		top = ((View) originalDraggedView.getParent()).getTop();
		left = ((View) originalDraggedView.getParent()).getLeft();
		
		if (movingViewWidth == 0) {
			movingViewWidth = movingView.getWidth();
			movingViewHeight = movingView.getHeight();
		}
		totalWidthMargin = movingViewWidth - view.getMeasuredWidth();
		totalHeightMargin = movingViewHeight - view.getMeasuredHeight();
		initialTouchDelta = getDelta(event, view);
		initialTouchPosition = getPosition(event);
	}
	
	private void clean() {
		originalDraggedView = null;
		LayoutParams params = (LayoutParams) movingView.getLayoutParams();
		params.setMargins(0, 0, 0, 0);
		movingView.setLayoutParams(params);
		movingView.removeAllViews();
		movingView.setVisibility(View.GONE);
		top = 0;
		left = 0;
		totalWidthMargin = 0;
		totalHeightMargin = 0;
	}
	
	private void initMovingView() {
		if (originalDraggedView.getTag().getClass().equals(WordCube.class)) {
			WordCube child = new WordCube(originalDraggedView.getContext(), 
					((WordCube) originalDraggedView.getTag()).getDisplayedText());
			movingView.addView(child.getView());
		} else {
			AnswerCube child = new AnswerCube(originalDraggedView.getContext(),
					((AnswerCube) originalDraggedView.getTag()).getDisplayedText(), 
					((AnswerCube) originalDraggedView.getTag()).isVertical());
			movingView.addView(child.getView());
		}
		
		movingView.setVisibility(View.VISIBLE);
	}
	
	private void updateMovingView(MotionEvent event) {
		float[] position = getPosition(event);
		LayoutParams params = (LayoutParams) movingView.getLayoutParams();
		params.setMargins((int) (position[0] - initialTouchDelta[0]), 
				(int) (position[1] - initialTouchDelta[1]), 
				(int) (totalWidthMargin - position[0] + initialTouchDelta[0]), 
				(int) (totalHeightMargin - position[1] + initialTouchDelta[1]));
		movingView.setLayoutParams(params);
	}
}
