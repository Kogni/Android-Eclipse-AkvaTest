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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Act_ButtonList_Fish extends Activity {
    static final int NUM_ITEMS = 10;

    MyAdapter mAdapter;

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_buttonlist);

	mAdapter = new MyAdapter(getFragmentManager());

	mPager = (ViewPager) findViewById(R.id.pager);
	mPager.setAdapter(mAdapter);
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
	public MyAdapter(FragmentManager fm) {
	    super(fm);
	}

	@Override
	public int getCount() {
	    return NUM_ITEMS;
	}

	@Override
	public Fragment getItem(int position) {
	    return ArrayListFragment.newInstance(position);
	}

    }

    public static class ArrayListFragment extends ListFragment {
	int mNum;

	/**
	 * Create a new instance, providing "num"
	 * as an argument.
	 */
	static ArrayListFragment newInstance(int num) {
	    //Log.i("Act_ButtonList_Fish", "ArrayListFragment newInstance " + num);
	    ArrayListFragment f = new ArrayListFragment();

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
	    //Log.i("Act_ButtonList_Fish", "ArrayListFragment 1 onCreate " + savedInstanceState);
	    super.onCreate(savedInstanceState);
	    mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}

	/**
	 * The Fragment's UI is just a simple text view showing its
	 * instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    //Log.i("Act_ButtonList_Fish", "ArrayListFragment 2 onCreateView " + savedInstanceState);
	    View v = inflater.inflate(R.layout.layout_fragment_pager_list, container, false);
	    View tv = v.findViewById(R.id.text);
	    Array_Fish_Singleton.getInstance().updateLists(true, "");
	    //Array_Fish Array_Fish = new Array_Fish(true,"");
	    ((TextView) tv).setText(pack_fishy.Array_Fish_Singleton.Total_FishFilled + " species listed");
	    return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
		    Array_Fish_Singleton.FishNames_Sorted));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	    //Log.i("Act_ButtonList_Fish", "ArrayListFragment onListItemClick " + position);
	    Intent intent = new Intent(v.getContext(), Act_FishInfo.class);
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    //Array_Fish Array_Fish = new Array_Fish(true,"");
	    Array_Fish_Singleton.getInstance().updateLists(true, "");
	    String FishNameSelected = Array_Fish_Singleton.FishNames_Sorted[(int) id];
	    hashmap = Array_Fish_Singleton.getFishHashmap(FishNameSelected);
	    intent.putExtra("hashmap", hashmap);
	    startActivity(intent);
	}
    }

}
