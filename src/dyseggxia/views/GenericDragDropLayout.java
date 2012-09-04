package dyseggxia.views;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.LinearLayout;

public abstract class GenericDragDropLayout extends LinearLayout implements OnDragListener {
	
	public GenericDragDropLayout(Context context) {
		super(context);
	}

	@Override
	public boolean onDrag(View view, DragEvent event) {
		if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
			return true;
		} else if (event.getAction() == DragEvent.ACTION_DROP) {
			((GenericDragDropLayout) view).onDrop(event);
			return true;
		}
		return false;
	}
	
	private void onDrop(DragEvent event) {
		String answer = event.getClipData().getItemAt(0).getText().toString();
		int position = (int) (event.getX() / getChildAt(0).getMeasuredWidth());
		onDrop(position, answer);
	}
	
	protected abstract void onDrop(int position, String contents);

	public void onChildTouched(View child) {
		ClipData dragData = ClipData.newPlainText("", (String) child.getTag());
	    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(child);
	    startDrag(dragData, myShadow, null, 0);
	}
}
