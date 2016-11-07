package pack_generic;

import com.pynting.akvaapp_e_20160315.Controller;
import com.pynting.akvaapp_e_20160315.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import pack_aquarium.Frag_Aquarium;
import pack_fishy.Act_ButtonList_Fish;
import pack_fishy.Act_ButtonList_Fish_Match;
import pack_fishy.Act_ButtonList_Fish_Search;
import pack_fishy.Array_Fish_Singleton;
import pack_generic.Act_NPS.TabsAdapter;

public class Act_MainGUI extends Activity {
    ViewPager mViewPager;
    TabsAdapter mTabsAdapter;

    @SuppressWarnings("deprecation")
    ActionBar.Tab Tab1, Tab2, Tab3;
    Frag_Aquarium akvarie = new Frag_Aquarium();

    @SuppressWarnings({ "deprecation", "unused" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	try {

	    mViewPager = new ViewPager(this);
	    mViewPager.setId(R.id.pager);
	    setContentView(mViewPager);

	    final ActionBar bar = getActionBar();
	    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

	    // Intent intent = getIntent();
	    // HashMap<String, Object> hashMap = (HashMap<String, Object>) intent.getSerializableExtra("hashmap");
	    try {
		Controller class_Controller = Controller.getInstance();
	    } catch (Exception e) {
		Log.i("-----Act_MainGUI", "class_Controller failed: " + e.getMessage());
		e.printStackTrace();
	    }
	    // Log.i("-----Act_MainGUI", "onCreateView hashMap=" + class_Aquarium.hashmap); <-- vellykket
	    Bundle bundle = new Bundle();
	    // bundle.putSerializable("hashMap", (Serializable) class_Aquarium.hashmap);
	    // bundle.putString("name", "From Activity");
	    // Frag_Aquarium fragobj = new Frag_Aquarium();
	    // fragobj.setArguments(bundle);

	    // bar.setDisplayShowHomeEnabled(false);
	    // bar.setDisplayShowTitleEnabled(false);
	    Tab1 = bar.newTab().setText("Match list");

	    Array_Fish_Singleton.getInstance();

	    try {
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Search"), Act_ButtonList_Fish_Search.ArrayListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("Match list"), Act_ButtonList_Fish_Match.ArrayListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("Full list"), Act_ButtonList_Fish.ArrayListFragment.class, bundle);

		mTabsAdapter.addTab(bar.newTab().setText("Your tank"), Frag_Aquarium.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("Frag_Tabs"), Frag_Tabs.class, bundle);
	    } catch (Throwable E) {
		E.printStackTrace();
	    }

	    if (savedInstanceState != null) {
		bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
	    }

	    getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 0, 100));
	} catch (Throwable E) {
	    E.printStackTrace();
	}
    }
    /*
     * @Override protected void onSaveInstanceState(Bundle outState) { super.onSaveInstanceState(outState); outState.putInt("tab",
     * getActionBar().getSelectedNavigationIndex()); }
     * 
     * public static class TabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
     * private final Context mContext; private final ActionBar mActionBar; private final ViewPager mViewPager; private final
     * ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
     * 
     * static final class TabInfo { private final Class<?> clss; private final Bundle args;
     * 
     * TabInfo(Class<?> _class, Bundle _args) { clss = _class; args = _args; } }
     * 
     * public TabsAdapter(Activity activity, ViewPager pager) { super(activity.getFragmentManager()); mContext = activity; mActionBar =
     * activity.getActionBar(); mViewPager = pager; mViewPager.setAdapter(this); mViewPager.setOnPageChangeListener(this); }
     * 
     * public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) { TabInfo info = new TabInfo(clss, args); tab.setTag(info);
     * tab.setTabListener(this); mTabs.add(info); mActionBar.addTab(tab); notifyDataSetChanged(); }
     * 
     * @Override public int getCount() { return mTabs.size(); }
     * 
     * @Override public Fragment getItem(int position) { TabInfo info = mTabs.get(position); return Fragment.instantiate(mContext,
     * info.clss.getName(), info.args); }
     * 
     * @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
     * 
     * @Override public void onPageSelected(int position) { mActionBar.setSelectedNavigationItem(position); }
     * 
     * @Override public void onPageScrollStateChanged(int state) { }
     * 
     * @Override public void onTabSelected(Tab tab, FragmentTransaction ft) { Object tag = tab.getTag(); for (int i = 0; i < mTabs.size();
     * i++) { if (mTabs.get(i) == tag) { mViewPager.setCurrentItem(i); } } }
     * 
     * @Override public void onTabUnselected(Tab tab, FragmentTransaction ft) { }
     * 
     * @Override public void onTabReselected(Tab tab, FragmentTransaction ft) { } }
     */
}
