/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pack_fishy;

import java.util.HashMap;

import com.pynting.akvaapp_e_20160315.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_FishInfo extends Activity {

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	LinearLayout linLayout = new LinearLayout(this);
	linLayout.setOrientation(LinearLayout.VERTICAL);
	LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	setContentView(linLayout, linLayoutParam);
	setContentView(R.layout.layout_fish);

	getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 128, 0));

	Intent intent = getIntent();
	HashMap<String, Object> hashMap = (HashMap<String, Object>) intent.getSerializableExtra("hashmap");

	TextView e = (TextView) findViewById(R.id.field_fishname);
	String clickedfish = (String) hashMap.get("Genus") + " " + (String) hashMap.get("Species");
	e.setText(clickedfish);

	e = (TextView) findViewById(R.id.field_commonnames);
	e.setText((String) hashMap.get("Commonnames"));

	e = (TextView) findViewById(R.id.field_temp_survive);
	e.setText((hashMap.get("Temp_survive_Min") + "") + "-" + (hashMap.get("Temp_survive_Max") + ""));

	e = (TextView) findViewById(R.id.field_temp_optimal);
	e.setText((hashMap.get("Temp_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_PH_survive);
	e.setText((hashMap.get("PH_survive_Min") + "") + "-" + (hashMap.get("PH_survive_Max") + ""));

	e = (TextView) findViewById(R.id.field_PH_optimal);
	e.setText((hashMap.get("PH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_KH_survive);
	e.setText((hashMap.get("KH_survive_Min") + "") + "-" + (hashMap.get("KH_survive_Max") + ""));

	e = (TextView) findViewById(R.id.field_KH_optimal);
	e.setText((hashMap.get("KH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_GH_survive);
	e.setText((hashMap.get("GH_survive_Min") + "") + "-" + (hashMap.get("GH_survive_Max") + ""));

	e = (TextView) findViewById(R.id.field_GH_optimal);
	e.setText((hashMap.get("GH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_Salinity_survive);
	e.setText((hashMap.get("Salt_survive_Min") + "") + "-" + (hashMap.get("Salt_survive_Max") + ""));

	e = (TextView) findViewById(R.id.field_Salinity_optimal);
	e.setText((hashMap.get("Salinity_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_size);
	e.setText((hashMap.get("size") + ""));

	e = (TextView) findViewById(R.id.field_diet);
	e.setText((hashMap.get("diet") + ""));

	e = (TextView) findViewById(R.id.field_predator);
	String predator = (hashMap.get("predator") + "");
	predator = predator.toLowerCase();
	e.setText(predator);
	if (predator.contains("yes") || predator.contains("true")) {
	    getWindow().getDecorView().setBackgroundColor(Color.rgb(170, 150, 0));
	}

	e = (TextView) findViewById(R.id.field_level);
	e.setText((hashMap.get("level") + ""));

	e = (TextView) findViewById(R.id.field_fe_male_ratio);
	e.setText((hashMap.get("Fe_M_ratio") + ""));

	e = (TextView) findViewById(R.id.field_sex_diff);
	e.setText((hashMap.get("Sex_diff") + ""));

	e = (TextView) findViewById(R.id.field_behavior);
	e.setText((hashMap.get("Behavior") + ""));

	e = (TextView) findViewById(R.id.field_tank_vol);
	e.setText((hashMap.get("tank_min_liters") + ""));

	e = (TextView) findViewById(R.id.field_tank_length_min);
	e.setText((hashMap.get("tank_min_length") + ""));

	e = (TextView) findViewById(R.id.field_difficulty);
	String difficulty = (hashMap.get("difficulty") + "");
	difficulty = difficulty.toLowerCase();
	e.setText(difficulty);
	if (difficulty.contains("hard")) {
	    getWindow().getDecorView().setBackgroundColor(Color.rgb(170, 150, 0));
	    //Log.i("Act_FishInfo", "Act_FishInfo onCreate difficulty is hard");
	}
	//Log.i("Act_FishInfo", "Act_FishInfo onCreate difficulty=" + difficulty + " difficulty.contains(hard)="+ difficulty.contains("hard")+" clickedfish=" + clickedfish);

	//View someView = findViewById(R.layout.layout_fish);
	//View root = someView.getRootView();

	class_Fish fisken = Array_Fish_Singleton.getFish(clickedfish);
	boolean match = Array_Fish_Singleton.match_checkfishmatch(fisken);
	if (!match) { //feil stats overkjører alle andre farger
	    getWindow().getDecorView().setBackgroundColor(Color.rgb(128, 0, 0));
	}
    }
}
