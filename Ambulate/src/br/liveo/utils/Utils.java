package br.liveo.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

	public static void toastShort(Context context, int text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void toastLong(Context context, int text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	} 	
}
