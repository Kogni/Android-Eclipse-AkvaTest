package pack_fishy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.util.Log;

public class class_Fish {

    public Map<String, Object> hashmap = new HashMap<String, Object>();

    @SuppressWarnings("rawtypes")
    public class_Fish(String Genus, String Species, String Commonnames) {
	hashmap.put("Genus", Genus);
	hashmap.put("Species", Species);
	hashmap.put("Commonnames", Commonnames);

	//fyll inn felter

	//sjekk for tomme felter
	Iterator it = hashmap.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry pairs = (Map.Entry) it.next();
	    try {
		if (pairs.getValue() == null) {
		    Log.i("_____class_Fish", (String) hashmap.get("Genus") + " " + (String) hashmap.get("Species") + " " + pairs.getKey()
			    + " is null!_____");
		}
	    } catch (Exception e) {

	    }
	    try {
		if ((Double) pairs.getValue() <= 0.0) {
		    Log.i("_____class_Fish", (String) hashmap.get("Genus") + " " + (String) hashmap.get("Species") + " " + pairs.getKey()
			    + " is null!_____");
		}
	    } catch (Exception e) {

	    }

	}
    }

    public String get_PH_Survive() {
	return hashmap.get("PH_Survive_Min") + "-" + hashmap.get("PH_Survive_Max");
    }

    public String get_Species() {
	return (String) hashmap.get("Species");
    }

    public String get_Genus() {
	return (String) hashmap.get("Genus");
    }
    
    public String get_Commonnames() {
	return (String) hashmap.get("Commonnames");
    }
}
