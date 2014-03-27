package android.baba.odometerflip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class FlipMain extends LinearLayout {
	private static final int NO = 4;

	private int currentVal;
	private int animCompleteCount = 0;

	private FlipItem[] spinners;

	public FlipMain(Context context) {
		super(context);

		initialize();
	}

	public FlipMain(Context context, AttributeSet attrs) {
		super(context, attrs);

		initialize();
	}

	private void initialize() {
		spinners = new FlipItem[NO];

		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.widget_flipmeter, this, true);

		spinners[0] = (FlipItem) findViewById(R.id.widget_flip_item_1);
		spinners[1] = (FlipItem) findViewById(R.id.widget_flip_item_10);
		spinners[2] = (FlipItem) findViewById(R.id.widget_flip_item_100);
		spinners[3] = (FlipItem) findViewById(R.id.widget_flip_item_1k);
		/*spinners[4] = (FlipMainSpinner) findViewById(R.id.widget_flip_item_10k);
		spinners[5] = (FlipMainSpinner) findViewById(R.id.widget_flip_item_100k);*/

	}

	public void setValue(int value, boolean withAnimation) {

		currentVal = value;
		int tempValue = value;

		for (int i=NO-1; i>0; --i) {
			int factor = (int) Math.pow(10, i);

			int digitVal = (int) Math.floor(tempValue / factor);
			tempValue -= (digitVal * factor);
			spinners[i].setDigit(digitVal, withAnimation);
			changeanimCompleteCount(withAnimation);
		}

		spinners[0].setDigit(tempValue, withAnimation);
		changeanimCompleteCount(withAnimation);

	}

	private synchronized int changeanimCompleteCount(Boolean increment) {
		if (increment == null)
			return animCompleteCount;
		else if (increment)
			return ++animCompleteCount;
		else
			return --animCompleteCount;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return currentVal;
	}

	public interface OnValueChangeListener {
		abstract void onValueChange(FlipMain sender, int newValue);
	}

}
