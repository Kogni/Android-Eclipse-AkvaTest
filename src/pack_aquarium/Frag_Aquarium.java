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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressWarnings("unused")
public class Frag_Aquarium extends Fragment {
    int mNum;
    String Topic;
    HashMap<String, Object> hashMap;

    @SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View localview = inflater.inflate(R.layout.layout_aquarium, container, false);

	Bundle bundle = this.getArguments();
	//Log.i("-----Frag_Aquarium", "onCreateView bundle=" + bundle);
	//Serializable myInt = bundle.getSerializable("hashMap");
	//Log.i("-----Frag_Aquarium", "onCreateView myInt=" + myInt);
	//hashMap = (HashMap<String, Object>) bundle.getSerializable("hashMap");
	hashMap = (HashMap) class_Aquarium.getInstance().hashmap;
	//String strtext = getArguments().getString("name");
	//Log.i("Frag_Aquarium", "onCreateView strtext=" + strtext);
	//Log.i("Frag_Aquarium", "onCreateView hashMap=" + hashMap);
	TextView e;

	//Log.i("-----Frag_Aquarium", "onCreateView R.id.field_temp=" + R.id.field_temp);
	//Log.i("-----Frag_Aquarium", "onCreateView hashMap.get('Temp_Optimal')=" + hashMap.get("Temp_Optimal"));
	//Log.i("-----Frag_Aquarium", "onCreateView getView().findViewById(R.id.field_temp)=" + getView().findViewById(R.id.field_temp));
	View localview_item = localview.findViewById(R.id.field_temp);
	((TextView) localview_item).setText((hashMap.get("Temp") + ""));

	localview_item = localview.findViewById(R.id.field_PH);
	((TextView) localview_item).setText((hashMap.get("PH") + ""));

	localview_item = localview.findViewById(R.id.field_KH);
	((TextView) localview_item).setText((hashMap.get("KH") + ""));

	localview_item = localview.findViewById(R.id.field_GH);
	((TextView) localview_item).setText((hashMap.get("GH") + ""));

	localview_item = localview.findViewById(R.id.field_Salinity);
	((TextView) localview_item).setText((hashMap.get("Salinity") + ""));

	localview_item = localview.findViewById(R.id.field_tank_vol);
	((TextView) localview_item).setText((hashMap.get("TankVolume") + ""));

	localview_item = localview.findViewById(R.id.field_tank_length);
	((TextView) localview_item).setText((hashMap.get("TankLength") + ""));

	return localview;

    }
}
