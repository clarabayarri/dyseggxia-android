package dyseggxia.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.viewControllers.DragHelper;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public abstract class GenericDragDropLayout extends LinearLayout 
	implements DragHelper.DragElementListener, DragHelper.DropElementListener {
	
	protected GenericCubesProblemViewController delegate;
	protected boolean isDraggable;
	
	public GenericDragDropLayout(Context context) {
		super(context);
	}
	
	public void setDelegate(GenericCubesProblemViewController delegate) {
		this.delegate = delegate;
	}
	
	public void setDraggable(boolean draggable) {
		this.isDraggable = draggable;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isDraggable) {
			int position = (int) (event.getX() / getChildAt(0).getMeasuredWidth());
			View child = getChildAt(position);
			return delegate.onTouchChild(event, child);
		}
		return false;
	}
	
	public void onDragStarted(View view) {
		view.setVisibility(View.INVISIBLE);
	}
	
	public void onDragEnded(View view) {
		view.setVisibility(View.VISIBLE);
	}
	
	public void onDrop(View view, MotionEvent event) {
		String contents = (String) view.getTag();
		int position = (int) (event.getX() / getChildAt(0).getMeasuredWidth());
		delegate.viewDroppedOnIndex(position, contents);
	}
}
