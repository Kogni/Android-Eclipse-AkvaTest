package pack_generic;

import com.pynting.akvaapp_e_20160315.R;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Frag_Tabs extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	mTabHost = new FragmentTabHost(getActivity());
	mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.pager);

	mTabHost.addTab(mTabHost.newTabSpec("Frag_Page").setIndicator("Frag_Page"), Frag_Page.class, null);

	return mTabHost;
    }

    @Override
    public void onDestroyView() {
	super.onDestroyView();
	mTabHost = null;
    }
}
