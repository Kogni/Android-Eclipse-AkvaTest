package com.pynting.akvaapp_e_20160315;

import java.util.HashMap;

import com.pynting.akvaapp_e_20160315.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_Settings extends Activity {

    @SuppressWarnings({ "rawtypes", "static-access", "unused" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	LinearLayout linLayout = new LinearLayout(this);
	linLayout.setOrientation(LinearLayout.VERTICAL);
	LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	setContentView(linLayout, linLayoutParam);
	try {
	    setContentView(R.layout.layout_settings);
	} catch (Exception e) {
	    Log.i("Act_Settings", "onCreate failed to set view: "+e.getMessage());
	}

	getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 0, 256));

	// Serializable myInt = bundle.getSerializable("hashMap");
	Log.i("-----Act_Settings", "Act_Settings onCreate ");
	HashMap hashMap = (HashMap) class_Aquarium.getInstance().hashmap;
	// String strtext = getArguments().getString("name");
	// Log.i("Frag_Aquarium", "onCreateView strtext=" + strtext);
	// Log.i("Frag_Aquarium", "onCreateView hashMap=" + hashMap);
	TextView e;

	Log.i("-----Act_Settings", "Act_Settings onCreate R.id.field_temp=" + R.id.field_temp);
	Log.i("-----Act_Settings", "Act_Settings onCreate hashMap.get('Temp')=" + hashMap.get("Temp"));
	// Log.i("-----Frag_Aquarium", "onCreateView getView().findViewById(R.id.field_temp)=" + getView().findViewById(R.id.field_temp));
	final View localview_item1 = findViewById(R.id.field_temp);
	((TextView) localview_item1).setText((hashMap.get("Temp") + ""));
	((TextView) localview_item1).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		TextChanged(localview_item1, s, start, before, count, "Temp");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item2 = findViewById(R.id.field_PH);
	((TextView) localview_item2).setText((hashMap.get("PH") + ""));
	((TextView) localview_item2).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		TextChanged(localview_item2, s, start, before, count, "PH");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item3 = findViewById(R.id.field_KH);
	((TextView) localview_item3).setText((hashMap.get("KH") + ""));
	((TextView) localview_item3).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		TextChanged(localview_item3, s, start, before, count, "KH");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item4 = findViewById(R.id.field_GH);
	((TextView) localview_item4).setText((hashMap.get("GH") + ""));
	((TextView) localview_item4).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		TextChanged(localview_item4, s, start, before, count, "GH");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item5 = findViewById(R.id.field_Salinity);
	((TextView) localview_item5).setText((hashMap.get("Salinity") + ""));
	((TextView) localview_item5).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		/*
		 * Log.i("-----Act_Settings", "Act_Settings onCreate onTextChanged " + count + " s=" + s + " start=" + start + " before=" +
		 * before); //CharSequence searchphrase = s; //Søking skal kun skje når man trykker en knapp eller unselecter
		 * 
		 * SpannableString contentText = new SpannableString(((TextView) localview_item5).getText()); String ny =
		 * Html.toHtml(contentText).toString(); ny = Html.fromHtml(ny).toString(); ny = ny.replace("\n", "").replace("\r", ""); ny =
		 * ny.toLowerCase(); Log.i("-----Act_Settings", "Act_Settings onCreate onTextChanged ny=" + ny);
		 * class_Aquarium.getInstance().setSalinity(ny);
		 */
		TextChanged(localview_item5, s, start, before, count, "Salinity");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item6 = findViewById(R.id.field_tank_vol);
	((TextView) localview_item6).setText((hashMap.get("TankVolume") + ""));
	((TextView) localview_item6).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		/*
		 * Log.i("-----Act_Settings", "Act_Settings onCreate onTextChanged " + count + " s=" + s + " start=" + start + " before=" +
		 * before); //CharSequence searchphrase = s; //Søking skal kun skje når man trykker en knapp eller unselecter
		 * 
		 * SpannableString contentText = new SpannableString(((TextView) localview_item6).getText()); String ny =
		 * Html.toHtml(contentText).toString(); ny = Html.fromHtml(ny).toString(); ny = ny.replace("\n", "").replace("\r", ""); ny =
		 * ny.toLowerCase(); Log.i("-----Act_Settings", "Act_Settings onCreate onTextChanged ny=" + ny);
		 * class_Aquarium.getInstance().setVolum(ny);
		 */
		TextChanged(localview_item6, s, start, before, count, "TankVolume");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

	final View localview_item7 = findViewById(R.id.field_tank_length);
	((TextView) localview_item7).setText((hashMap.get("TankLength") + ""));
	((TextView) localview_item7).addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		/*
		 * Log.i("-----Act_Settings", "Act_Settings onTextChanged " + count + " s=" + s + " start=" + start + " before=" + before);
		 * //CharSequence searchphrase = s; //Søking skal kun skje når man trykker en knapp eller unselecter
		 * 
		 * SpannableString contentText = new SpannableString(((TextView) localview_item7).getText()); String ny =
		 * Html.toHtml(contentText).toString(); ny = Html.fromHtml(ny).toString(); ny = ny.replace("\n", "").replace("\r", ""); ny =
		 * ny.toLowerCase(); Log.i("-----Act_Settings", "Act_Settings onTextChanged ny=" + ny); class_Aquarium.setLength(ny);
		 */
		TextChanged(localview_item7, s, start, before, count, "TankLength");
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	});

    }

    private void TextChanged(View localview_item7, CharSequence s, int start, int before, int count, String var) {
	Log.i("-----Act_Settings", "Act_Settings onTextChanged " + count + " s=" + s + " start=" + start + " before=" + before);
	// CharSequence searchphrase = s;
	// Søking skal kun skje når man trykker en knapp eller unselecter

	SpannableString contentText = new SpannableString(((TextView) localview_item7).getText());
	String ny = Html.toHtml(contentText).toString();
	ny = Html.fromHtml(ny).toString();
	ny = ny.replace("\n", "").replace("\r", "");
	ny = ny.toLowerCase();
	Log.i("-----Act_Settings", "Act_Settings onTextChanged ny=" + ny);
	class_Aquarium.setVar(var, ny);
    }
}
