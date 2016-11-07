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
package pack_generic;

import com.pynting.akvaapp_e_20160315.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Frag_Page extends Fragment {
    int mNum;
    String Topic;

    public Frag_Page() {

    }

    /**
     * Create a new instance, providing "num"
     * as an argument.
     */
    static Frag_Page newInstance(int num) {
	Frag_Page f = new Frag_Page();

	// Supply num input as an argument.
	Bundle args = new Bundle();
	args.putInt("num", num);
	f.setArguments(args);

	return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.layout_page, container, false);
	View tv = v.findViewById(R.id.text);
	((TextView) tv).setText("Frag_Page #" + mNum);
	return v;
    }
}
