package android.baba.odometerflip;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

class Flip implements AnimationListener {

	private ImageView flip_backup = null;
	private ImageView flipI_backlow = null;
	private ImageView flip_frontup = null;
	private ImageView flip_frontlow = null;

	private Context context;
	private Animation anim1;
	private Animation anim2;
	private int id;
	private OnAnimationComplete animComplete;

	private int animTo = 0;
	private int animFrom = 0;

	public interface OnAnimationComplete {
		public void onComplete(int id);
	}

	public Flip(Context act, int id, View view, OnAnimationComplete animComplete) {
		super();
		this.context = act;
		this.id = id;
		this.animComplete = animComplete;

		flip_backup = (ImageView) view.findViewById(R.id.flipitem_image_back_upper);
		flipI_backlow = (ImageView) view.findViewById(R.id.flipitem_image_back_lower);
		flip_frontup = (ImageView) view.findViewById(R.id.flipitem_image_front_upper);
		flip_frontlow = (ImageView) view.findViewById(R.id.flipitem_image_front_lower);

		init();
	}

	public void setDigit(int digit, boolean withAnimation) {

		if (digit < 0)
			digit = 0;
		if (digit > 9)
			digit = 9;

		animTo = digit;

		if (withAnimation)
			animateDigit(true);
		else
			setDigitImageToAll(digit);
	}

	private void animateDigit(boolean isUpper) {

		animFrom = getLastDigit(isUpper);
		startAnimation();

	}

	private void init() {

		flip_backup.setTag(0);
		flipI_backlow.setTag(0);
		flip_frontup.setTag(0);
		flip_frontlow.setTag(0);

		anim1 = AnimationUtils.loadAnimation(context, R.anim.flip_point_to_middle);
		anim1.setAnimationListener(this);
		anim2 = AnimationUtils.loadAnimation(context, R.anim.flip_point_from_middle);
		anim2.setAnimationListener(this);

	}

	private void startAnimation() {

		if (animTo == animFrom) {
			if (animComplete != null)
				animComplete.onComplete(id);
		} else {
			startDigitAnimation(true);
		}

	}

	private void startDigitAnimation(boolean isUpper) {

		if (isUpper) {
			flip_frontup.clearAnimation();
			flip_frontup.setAnimation(anim1);
			flip_frontup.startAnimation(anim1);

		} else {
			flip_frontlow.clearAnimation();
			flip_frontlow.setAnimation(anim2);
			flip_frontlow.startAnimation(anim2);

		}
	}

	private void incrementFromCode() {
		animFrom++;
		if (animFrom < 0)
			animFrom = 0;

		if (animFrom > 9)
			animFrom = 9;

	}

	private int getLastDigit(boolean isUpper) {
		int digit = 0;
		try {
			if (isUpper)
				digit = (Integer) flip_frontup.getTag();
			else
				digit = (Integer) flip_frontlow.getTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (digit > 9)
			digit = 0;

		return digit;
	}

	private int getDigitToShow() {
		if (animFrom + 1 > 9)
			return 0;
		else
			return animFrom + 1;
	}

	private void setDigitImageToAll(int digit) {
		setDigitImage(digit, true, flip_backup);
		setDigitImage(digit, false, flipI_backlow);
		setDigitImage(digit, true, flip_frontup);
		setDigitImage(digit, false, flip_frontlow);

	}

	private void setDigitImage(int digitToShow, boolean isUpper, ImageView image) {
		image.setTag(digitToShow);
		int resource = 0;
		switch (digitToShow) {
		case 0:
			if (isUpper)
				resource = R.drawable.upper_0;
			else
				resource = R.drawable.lower_0;

			break;

		case 1:
			if (isUpper)
				resource = R.drawable.upper_1;
			else
				resource = R.drawable.lower_1;

			break;

		case 2:
			if (isUpper)
				resource = R.drawable.upper_2;
			else
				resource = R.drawable.lower_2;

			break;

		case 3:
			if (isUpper)
				resource = R.drawable.upper_3;
			else
				resource = R.drawable.lower_3;

			break;

		case 4:
			if (isUpper)
				resource = R.drawable.upper_4;
			else
				resource = R.drawable.lower_4;

			break;

		case 5:
			if (isUpper)
				resource = R.drawable.upper_5;
			else
				resource = R.drawable.lower_5;

			break;

		case 6:
			if (isUpper)
				resource = R.drawable.upper_6;
			else
				resource = R.drawable.lower_6;

			break;

		case 7:
			if (isUpper)
				resource = R.drawable.upper_7;
			else
				resource = R.drawable.lower_7;

			break;

		case 8:
			if (isUpper)
				resource = R.drawable.upper_8;
			else
				resource = R.drawable.lower_8;

			break;

		case 9:
			if (isUpper)
				resource = R.drawable.upper_9;
			else
				resource = R.drawable.lower_9;

			break;

		}

		image.setImageResource(resource);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == anim1) {
			flip_frontup.setVisibility(View.INVISIBLE);
			startDigitAnimation(false);

		} else if (animation == anim2) {

			flip_frontup.setVisibility(View.VISIBLE);
			setDigitImage(getDigitToShow(), true, flip_frontup);
			setDigitImage(getDigitToShow(), false, flipI_backlow);
			incrementFromCode();
			animateDigit(false);
		}

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation animation) {

		if (animation == anim1) {

			flip_frontlow.setVisibility(View.INVISIBLE);
			flip_frontup.setVisibility(View.VISIBLE);

			setDigitImage(getDigitToShow(), false, flip_frontlow);
			setDigitImage(getDigitToShow(), true, flip_backup);

		} else if (animation == anim2) {
			flip_frontlow.setVisibility(View.VISIBLE);
		}

	}

}
