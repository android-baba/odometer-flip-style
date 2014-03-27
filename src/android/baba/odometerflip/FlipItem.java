package android.baba.odometerflip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class FlipItem extends RelativeLayout {

	private Context context;
	private View FlipItem = null;

	private int mCurrentDigit;

	private Flip flip = null;

	public FlipItem(Context context) {
		super(context);
		this.context = context;
		initialize();
	}

	public FlipItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initialize();
	}

	public FlipItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initialize();
	}

	private void inflateLayout() {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		FlipItem = layoutInflater.inflate(R.layout.view_flipmeter_spinner, this);

	}

	public View getFlipItem() {
		return FlipItem;
	}

	public void setDigit(int animateTo, boolean withAnimation) {
		flip.setDigit(animateTo, withAnimation);
	}

	private void initialize() {
		inflateLayout();

		flip = new Flip(context, getId(), FlipItem, null);

	}

	public int getCurrentDigit() {
		return mCurrentDigit;
	}

}
