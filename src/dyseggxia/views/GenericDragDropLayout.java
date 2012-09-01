package dyseggxia.views;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.LinearLayout;

public abstract class GenericDragDropLayout extends LinearLayout implements OnDragListener {
	
	public GenericDragDropLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onDrag(View view, DragEvent event) {
		if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
			return true;
		} else if (event.getAction() == DragEvent.ACTION_DROP) {
			onDrop(view, event);
			return true;
		}
		return false;
	}
	
	protected abstract void onDrop(View view, DragEvent event);

}
