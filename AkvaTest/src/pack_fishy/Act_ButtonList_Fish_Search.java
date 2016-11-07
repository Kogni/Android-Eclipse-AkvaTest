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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.pynting.akvaapp_aj.R;

public class Act_ButtonList_Fish_Search extends Activity {
    static final int NUM_ITEMS = 10;

    MyAdapter mAdapter;

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_list_search);

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
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 1 onCreate " + savedInstanceState);
	    super.onCreate(savedInstanceState);
	    mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}

	/**
	 * The Fragment's UI is just a simple text view showing its
	 * instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView " + savedInstanceState);
	    View v = inflater.inflate(R.layout.layout_list_search, container, false);
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView v=" + v);
	    final View tv = v.findViewById(R.id.searchbox);
	    //final CharSequence searchphrase;
	    ((TextView) tv).addTextChangedListener(new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 beforeTextChanged ");
		}

		@Override
		public void afterTextChanged(Editable s) {
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 afterTextChanged ");
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		    /*Log.i("Act_ButtonList_Fish_Search", "onCreate onTextChanged " + count + " s=" + s + " start=" + start + " before="
			    + before);*/
		    //CharSequence searchphrase = s;
		    //Søking skal kun skje når man trykker en knapp eller unselecter

		    SpannableString contentText = new SpannableString(((TextView) tv).getText());
		    String searchphrase = Html.toHtml(contentText).toString();
		    searchphrase = Html.fromHtml(searchphrase).toString();
		    searchphrase = searchphrase.replace("\n", "").replace("\r", "");
		    searchphrase = searchphrase.toLowerCase();
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView searchphrase=" + searchphrase);
		    //Array_Fish Array_Fish = new Array_Fish(false, searchphrase);
		    Array_Fish_Singleton.getInstance().updateLists(false, searchphrase);
		    int temp = pack_fishy.Array_Fish_Singleton.Search_FishFilled;
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView species listed=" + temp);
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView tv=" + tv);
		    //((TextView) tv).setText(temp + " species listed");
		    Context c = getActivity();
		    int i = android.R.layout.simple_list_item_1;
		    String[] st = Array_Fish_Singleton.FishNames_Sorted;
		    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView species listed=" + st.length);
		    //Log.i("Act_ButtonList_Fish_Search", "Context=" + c);
		    //Log.i("Act_ButtonList_Fish_Search", "int=" + i);
		    //Log.i("Act_ButtonList_Fish_Search", "String[]=" + s);
		    ArrayAdapter AA = new ArrayAdapter<String>(c, i, st);
		    setListAdapter(AA);

		}

	    });
	    //int temp = pack_fishy.Array_Fish.Total_FishFilled;
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView species listed=" + temp);
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment 2 onCreateView tv=" + tv);
	    //((TextView) tv).setText(temp + " species listed");
	    return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    Context c = getActivity();
	    int i = android.R.layout.simple_list_item_1;
	    //Array_Fish Array_Fish = new Array_Fish(false, "search");
	    Array_Fish_Singleton.getInstance().updateLists(false, "search");
	    String[] s = Array_Fish_Singleton.FishNames_Sorted;
	    //Log.i("Act_ButtonList_Fish_Search","Context="+c);
	    //Log.i("Act_ButtonList_Fish_Search","int="+i);
	    //Log.i("Act_ButtonList_Fish_Search","String[]="+s);
	    ArrayAdapter AA = new ArrayAdapter<String>(c, i, s);
	    setListAdapter(AA);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	    //Log.i("Act_ButtonList_Fish_Search", "ArrayListFragment onListItemClick " + position);
	    Intent intent = new Intent(v.getContext(), Act_FishInfo.class);
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    //Array_Fish Array_Fish = new Array_Fish(true, "");
	    //Array_Fish_Singleton.getInstance().updateLists(true, "");
	    String FishNameSelected = Array_Fish_Singleton.FishNames_Sorted[(int) id];
	    //Log.i("Act_ButtonList_Fish_Match", "ArrayListFragment onListItemClick FishNameSelected " + FishNameSelected);
	    hashmap = Array_Fish_Singleton.getFishHashmap(FishNameSelected);
	    intent.putExtra("hashmap", hashmap);
	    startActivity(intent);
	}
    }

}
