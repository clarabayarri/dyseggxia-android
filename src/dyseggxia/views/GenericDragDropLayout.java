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
	
	public abstract void onClick(View view);
	
	public void onDragBecameClick(View view) {
		this.onClick(view);
	}
	
	public void onDrop(View view, float[] position) {
		String contents = ((GenericDraggableCube) view.getTag()).getDisplayedText();
		int x = (int) position[0] - this.getLeft();
		int y = (int) position[1] - this.getTop();
		if (y < this.getHeight()) {
			int childPosition = (int) (x / getChildAt(0).getMeasuredWidth());
			delegate.viewDroppedOnIndex(childPosition, contents);
		}
		
	}
}
