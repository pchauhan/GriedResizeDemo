package com.pc.GriedResizeDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.pc.GriedResizeDemo.R;

public class GridViewActivity extends Activity {
	int column = 11 ;//it represent grid column count
	GridView gridView;
	static  String[] str_arr ;
	int displayWidth,displayHeight,statusbar_height ,required_height;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		displayWidth = metrics.widthPixels ;
		displayHeight = metrics.heightPixels;

		statusbar_height = getStatusBarHeight(getApplicationContext());
		required_height = displayHeight - statusbar_height ;

		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setNumColumns(column);// set your  column number what you want
		int arrSize = column*column ;
		str_arr = new String[arrSize];
		for(int i=0;i<arrSize;i++){
			str_arr[i] = String.valueOf(i);
		}
		int column_width = displayWidth/column ;
		int column_height = required_height/column ;
		gridView.setAdapter(new ImageAdapter(this, str_arr,column_width,column_height));
		enableDisableView(gridView, false);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						String.valueOf(position), Toast.LENGTH_SHORT).show();

			}
		});

	}
	public void enableDisableView(View view, boolean enabled) {
		view.setEnabled(enabled);

		if ( view instanceof ViewGroup ) {
			ViewGroup group = (ViewGroup)view;

			for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
				enableDisableView(group.getChildAt(idx), enabled);
			}
		}
	}
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}