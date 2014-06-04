package com.ec.common;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Util {
	
	public static void makeDialog(Context context, String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		        dialog.cancel();
		    }
		});
		builder.show();
	}

}
