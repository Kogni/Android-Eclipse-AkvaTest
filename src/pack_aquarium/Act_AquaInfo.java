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

package pack_aquarium;

import java.util.HashMap;

import com.pynting.akvaapp_e_20160315.R;
import com.pynting.akvaapp_e_20160315.class_Aquarium;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_AquaInfo extends Activity {

    @SuppressWarnings({ "rawtypes", "unused" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	LinearLayout linLayout = new LinearLayout(this);
	linLayout.setOrientation(LinearLayout.VERTICAL);
	LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	setContentView(linLayout, linLayoutParam);
	setContentView(R.layout.layout_aquarium);

	//LinearLayout linLayout = new LinearLayout(this);
	linLayout.setOrientation(LinearLayout.VERTICAL);
	//LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	setContentView(linLayout, linLayoutParam);
	setContentView(R.layout.layout_aquarium);
	
	Intent intent = getIntent();
	@SuppressWarnings({ "unchecked", "static-access" })
	HashMap<String, Object> hashMap = (HashMap) class_Aquarium.getInstance().hashmap;
	//HashMap<String, Object> hashMap = (HashMap<String, Object>) intent.getSerializableExtra("hashmap");

	TextView e;

	e = (TextView) findViewById(R.id.field_temp_optimal);
	e.setText((hashMap.get("Temp_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_PH_optimal);
	e.setText((hashMap.get("PH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_KH_optimal);
	e.setText((hashMap.get("KH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_GH_optimal);
	e.setText((hashMap.get("GH_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_Salinity_optimal);
	e.setText((hashMap.get("Salinity_Optimal") + ""));

	e = (TextView) findViewById(R.id.field_tank_vol);
	e.setText((hashMap.get("tank_min_liters") + ""));

	e = (TextView) findViewById(R.id.field_tank_length);
	e.setText((hashMap.get("tank_min_length") + ""));
	getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 0, 255));

    }

}
