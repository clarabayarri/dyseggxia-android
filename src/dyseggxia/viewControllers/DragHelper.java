package dyseggxia.viewControllers;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import dyseggxia.views.AnswerCube;
import dyseggxia.views.WordCube;

public class DragHelper {

	public interface DragElementListener {
		public void onDragStarted(View view);
		public void onDragEnded(View view);
	}
	
	public interface DropElementListener {
		public void onDrop(View view, MotionEvent event);
	}
	
	private DragElementListener dragListener;
	private DropElementListener dropListener;
	
	private View originalDraggedView;
	private FrameLayout movingView;
	private float[] initialTouchPosition;
	private float[] initialTouchDelta;
	
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
		Log.i("CLARA", "on touch!");
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			originalDraggedView = view;
			initialTouchPosition = getPosition(event);
			initMovingView();
			dragListener.onDragStarted(view);
			break;
		case MotionEvent.ACTION_MOVE:
			updateMovingView(event);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_OUTSIDE:
			dropListener.onDrop(view, event);
			dragListener.onDragEnded(originalDraggedView);
			originalDraggedView = null;
			movingView.setVisibility(View.GONE);
			movingView.removeAllViews();
			break;
		}
		return false;
	}
	
	private float[] getPosition(MotionEvent event) {
		return new float[] {event.getX(), event.getY()};
	}
	
	private void initMovingView() {
		if (originalDraggedView.getTag().getClass().equals(WordCube.class)) {
			WordCube child = new WordCube(originalDraggedView.getContext(), 
					((WordCube) originalDraggedView.getTag()).getDisplayedText());
			movingView.addView(child.getView());
		} else {
			AnswerCube child = new AnswerCube(originalDraggedView.getContext(),
					((AnswerCube) originalDraggedView.getTag()).getDisplayedText(), false);
			movingView.addView(child.getView());
		}
		// TODO: set initial translation
		
		movingView.setVisibility(View.VISIBLE);
	}
	
	private void updateMovingView(MotionEvent event) {
		// TODO: update moving view translation
	}
}
