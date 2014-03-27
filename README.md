odometer-flip-style
===================

Odometer styled flip meter to show values

Import project to workspace and add OdometerFlip as library in desired project

add the following to your layout xml : 

  	<android.baba.odometerflip.FlipMain
    	android:id="@+id/flip"
    	android:layout_width="wrap_content"
    	android:layout_height="wrap_content" />
  
add following lines to java code : 

	FlipMain flip = (FlipMain)findViewById(R.id.flip);
	flip.setValue(p.points, true); <br>
