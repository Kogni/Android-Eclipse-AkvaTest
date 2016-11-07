package pack_fishy;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

import com.pynting.akvaapp_e_20160315.Controller;
import com.pynting.akvaapp_e_20160315.class_Aquarium;

import android.os.Environment;
import android.util.Log;

public class Array_Fish_Singleton {

    static int Total_ArraySize = 9999;
    static String Fiskefil = "Fisker2";
    static class_Fish newFish;

    public static int Total_FishFilled = 0;
    public static class_Fish[] Total_FishArray = new class_Fish[Total_ArraySize];
    private static String[] Total_FishNames_Nonpruned;// = createFish(true);
    public static String[] Total_FishNames_Sorted;

    public static int Match_FishFilled = 0;
    public static class_Fish[] Match_FishArray = new class_Fish[Match_FishFilled];
    private static String[] Match_FishNames_Nonpruned;// = createFish(false);
    public static String[] Match_FishNames_Sorted;

    public static int Search_FishFilled = 0;
    public static class_Fish[] Search_FishArray = new class_Fish[Match_FishFilled];
    private static String[] Search_FishNames_Nonpruned;// = createFish(false);
    public static String[] Search_FishNames_Sorted;

    static boolean Listtype = true;
    public static String[] FishNames_Sorted;// = createFish(Listtype);
    private static String searchphrase = "";

    private static volatile Array_Fish_Singleton instance;

    private Array_Fish_Singleton() {// constructor
	Total_FishArray = new class_Fish[Total_ArraySize];
	Match_FishArray = new class_Fish[Total_ArraySize];
	Search_FishArray = new class_Fish[Total_ArraySize];

	Total_FishNames_Nonpruned = new String[Total_ArraySize];
	Match_FishNames_Nonpruned = new String[Total_ArraySize];
	Search_FishNames_Nonpruned = new String[Total_ArraySize];

	Total_FishFilled = 0;
	Match_FishFilled = 0;
	Search_FishFilled = 0;

	loadFishFile();

	Total_FishNames_Sorted = new String[Total_FishFilled];
	for (int x = 0; x < Total_FishNames_Nonpruned.length; x++) {
	    if (Total_FishNames_Nonpruned[x] != null) {
		Total_FishNames_Sorted[x] = Total_FishNames_Nonpruned[x];
	    } else {
		break;
	    }
	}
	Arrays.sort(Total_FishNames_Sorted);
    }

    public static Array_Fish_Singleton getInstance() {
	if (instance == null) {
	    synchronized (Controller.class) {
		if (instance == null) {
		    instance = new Array_Fish_Singleton();
		}
	    }
	}
	return instance;
    }

    public void updateLists(boolean returnall, String searchphrase) {
	// Log.i("-----Array_Fish", "Array_Fish updateLists returnall=" + returnall + " searchphrase=" + searchphrase);
	try {
	    Array_Fish_Singleton.Listtype = returnall;
	    // this.searchphrase = searchphrase;

	    Match_FishArray = new class_Fish[Total_ArraySize];
	    Search_FishArray = new class_Fish[Total_ArraySize];

	    Match_FishNames_Nonpruned = new String[Total_ArraySize];
	    Search_FishNames_Nonpruned = new String[Total_ArraySize];

	    Match_FishFilled = 0;
	    Search_FishFilled = 0;

	    // Log.i("-----Array_Fish", "Array_Fish updateLists returnall=" + Listtype);
	    if (Listtype) {
		// Log.i("-----Array_Fish", "Array_Fish updateLists returning all fishes: " + Total_FishNames_Sorted.length);
		String[] sortedNames = new String[Total_FishNames_Sorted.length];
		for (int x = 0; x < Total_FishNames_Sorted.length; x++) {
		    if (Total_FishNames_Sorted[x] != null) {
			// sortedNames[x] = Total_FishNames_Sorted[x].get_Genus() + " " + Total_FishNames_Sorted[x].get_Species();
			sortedNames[x] = Total_FishNames_Sorted[x];
		    } else {
			break;
		    }
		}
		FishNames_Sorted = sortedNames;
	    } else {
		// Log.i("-----Array_Fish", "Array_Fish updateLists searchphrase.length()=" + searchphrase.length());
		if (searchphrase.length() > 0) {
		    checkFishesAgainstSearch();

		    Search_FishNames_Sorted = new String[Search_FishFilled];
		    for (int x = 0; x < Search_FishNames_Nonpruned.length; x++) {
			if (Search_FishNames_Nonpruned[x] != null) {
			    Search_FishNames_Sorted[x] = Search_FishNames_Nonpruned[x];
			    // Log.i("Array_Fish", "Array_Fish updateLists "+x+" Search_FishNames_Sorted[x]=" + Search_FishNames_Sorted[x]);
			} else {
			    break;
			}
		    }
		    Arrays.sort(Search_FishNames_Sorted);

		    String[] sortedNames = new String[Search_FishNames_Sorted.length];
		    for (int x = 0; x < Search_FishNames_Sorted.length; x++) {
			if (Search_FishNames_Sorted[x] != null) {
			    sortedNames[x] = Search_FishNames_Sorted[x];
			    // Log.i("Array_Fish", "Array_Fish updateLists "+x+" sortedNames[x]=" + sortedNames[x]);
			} else {
			    break;
			}
		    }
		    FishNames_Sorted = sortedNames;
		} else {
		    checkFishesAgainstMatch();

		    Match_FishNames_Sorted = new String[Match_FishFilled];
		    for (int x = 0; x < Match_FishNames_Nonpruned.length; x++) {
			if (Match_FishNames_Nonpruned[x] != null) {
			    Match_FishNames_Sorted[x] = Match_FishNames_Nonpruned[x];
			} else {
			    break;
			}
		    }
		    Arrays.sort(Match_FishNames_Sorted);

		    String[] sortedNames = new String[Match_FishNames_Sorted.length];
		    for (int x = 0; x < Match_FishNames_Sorted.length; x++) {
			if (Match_FishNames_Sorted[x] != null) {
			    sortedNames[x] = Match_FishNames_Sorted[x];
			} else {
			    break;
			}
		    }
		    FishNames_Sorted = sortedNames;
		}
	    }
	} catch (Throwable e) {
	    Log.i("Array_Fish", "Array_Fish updateLists failed" + e.getMessage());
	}
	// FishNames_Sorted = sortLists(Listtype);
	// Log.i("Array_Fish", "Array_Fish updateLists FishNames_Sorted=" + FishNames_Sorted.length + " searchphrase=" + searchphrase);

    }

    @SuppressWarnings({ "deprecation", "unused" })
    private void loadFishFile() {
	// Log.i("Array_Fish", "Array_Fish loadFishFile");

	String line = "";
	try {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
		// Log.i("Array_Fish", "Array_Fish loadFishFile Write permissions enabled!");
	    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		// Log.i("Array_Fish", "Array_Fish loadFishFile Read-only permissions enabled");
	    } else {
		// Log.i("Array_Fish", "Array_Fish loadFishFile File reading refused!");
		return;
	    }
	    // File testFile = new File("");
	    // String currentPath = testFile.getAbsolutePath();
	    // System.out.println("current path is: " + currentPath);

	    // Class_Controller.PrintAction(this.getClass().toString() + " Loading phrases from file " + Filnavn);
	    // File filen = new File("\\files\\assets\\" + Fiskefil);
	    // Log.i("Array_Fish", "Array_Fish loadFishFile E " + filen.exists());

	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream input = classLoader.getResourceAsStream(Fiskefil);
	    input = classLoader.getResourceAsStream("com/pynting/akvaapp_e_20160215/" + Fiskefil);
	    // Log.i("Array_Fish", "Array_Fish loadFishFile F " + input);

	    DataInputStream in2 = new DataInputStream(input);
	    // int X = 0;
	    String value = "";
	    while (in2.available() > 0) {
		// SaveWord(in2.readLine(), 100, Filnavn.equals(TopicsFile), Filnavn.equals(RequirementsFile));
		line = in2.readLine();
		// Log.i("Array_Fish", "Array_Fish loadFishFile H "+line);
		// String line = input.read();
		if (line.length() > 1) {
		    try {
			String genus = "";
			String species = "";
			try {
			    genus = line.substring(0, line.indexOf("|"));
			    genus = genus.toLowerCase();
			    line = line.substring(line.indexOf("|") + 1);

			    species = genus.substring(genus.indexOf(" ") + 1);
			    species = species.toLowerCase();
			    genus = genus.substring(0, genus.indexOf(" "));
			} catch (Throwable e) {

			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile genus=" + genus + ".");
			// Log.i("Array_Fish", "Array_Fish loadFishFile species=" + species + ".");
			//
			value = line.substring(0, line.indexOf("|"));
			String commons = value;
			commons = commons.toLowerCase();
			// Log.i("Array_Fish", "Array_Fish loadFishFile commons=" + commons);
			// Log.i("Array_Fish", "Array_Fish loadFishFile genus.indexOf(searchphrase)=" + genus.indexOf(searchphrase) + ".");
			line = line.substring(line.indexOf("|") + 1);

			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Temp_survive_Min = 0;
			try {
			    Temp_survive_Min = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				Temp_survive_Min = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Temp_survive_Min=" + Temp_survive_Min);
			//
			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Temp_survive_Max = 0;
			try {
			    Temp_survive_Max = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				Temp_survive_Max = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Temp_survive_Max=" + Temp_survive_Max);
			line = line.substring(line.indexOf("|") + 1);

			value = line.substring(0, line.indexOf("|"));
			String Temp_Optimal = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile Temp_Optimal=" + Temp_Optimal);
			//
			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			double PH_survive_Min = 0;
			try {
			    PH_survive_Min = Double.parseDouble(value);
			} catch (Throwable E) {

			}
			//
			double PH_survive_Max = 0.0;
			try {
			    line = line.substring(line.indexOf("|") + 1);
			    value = line.substring(0, line.indexOf("|"));
			    if (value.equals("")) {
				value = "0.0";
			    }
			    PH_survive_Max = Double.parseDouble(value);
			} catch (Throwable e) {

			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile PH_survive_Max=" + PH_survive_Max);
			//
			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String PH_Optimal = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile PH_Optimal=" + PH_Optimal);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int GH_survive_Min = 0;
			try {
			    GH_survive_Min = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				GH_survive_Min = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile GH_survive_Min=" + GH_survive_Min);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int GH_survive_Max = 0;
			try {
			    GH_survive_Max = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				GH_survive_Max = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile GH_survive_Max=" + GH_survive_Max);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String GH_Optimal = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile GH_Optimal=" + GH_Optimal);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int KH_survive_Min = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile KH_survive_Min=" + KH_survive_Min);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int KH_survive_Max = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile KH_survive_Max=" + KH_survive_Max);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String KH_Optimal = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile KH_Optimal=" + KH_Optimal);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Salt_survive_Min = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Salt_survive_Min=" + Salt_survive_Min);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Salt_survive_Max = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Salt_survive_Max=" + Salt_survive_Max);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String Salt_Optimal = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile Salt_Optimal=" + Salt_Optimal);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int tank_min_liters = 0;
			try {
			    tank_min_liters = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				tank_min_liters = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile tank_min_liters=" + tank_min_liters);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int tank_min_length = 0;
			try {
			    tank_min_length = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				tank_min_length = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile tank_min_length=" + tank_min_length);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String size = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile size=" + size);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String diet = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile diet=" + diet);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			boolean algaeeater = value.contains("yes");
			// Log.i("Array_Fish", "Array_Fish loadFishFile algaeeater=" + algaeeater);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			boolean predator = value.contains("yes");
			// Log.i("Array_Fish", "Array_Fish loadFishFile predator=" + predator);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String eatsFry = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile eatsFry=" + eatsFry);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String temperament = value;
			// Log.i("Array_Fish", "Array_Fish loadFishFile temperament=" + temperament);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String level = line.substring(0, line.indexOf("|"));
			// Log.i("Array_Fish", "Array_Fish loadFishFile level=" + level);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String Sex_diff = line.substring(0, line.indexOf("|"));
			// Log.i("Array_Fish", "Array_Fish loadFishFile Sex_diff=" + Sex_diff);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Fe_M_ratio = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Fe_M_ratio=" + Fe_M_ratio);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String difficulty = line.substring(0, line.indexOf("|"));
			// Log.i("Array_Fish", "Array_Fish loadFishFile difficulty=" + difficulty);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Min_group_nmbr = Integer.parseInt(value);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Min_group_nmbr=" + Min_group_nmbr);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			String Behavior = line.substring(0, line.indexOf("|"));
			// Log.i("Array_Fish", "Array_Fish loadFishFile Behavior=" + Behavior);

			line = line.substring(line.indexOf("|") + 1);

			// ---------------------

			newFish = findFishOrMakeNewFish(genus, species, commons);

			// Log.i("Array_Fish", "Array_Fish loadFishFile old Temp_survive_Min=" + newFish.hashmap.get("Temp_survive_Min"));
			// Log.i("Array_Fish", "Array_Fish loadFishFile Temp_survive_Min=" + Temp_survive_Min);

			// ------
			int tempAI = (Integer) newFish.hashmap.get("Temp_survive_Min");
			if (tempAI == 0) {
			    tempAI = Temp_survive_Min;
			} else {
			    tempAI = Math.min(Temp_survive_Min, tempAI);
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile new Temp_survive_Min=" + Temp_survive_Min);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Temp_survive_Min=" + newFish.hashmap.get("Temp_survive_Min"));
			newFish.hashmap.put("Temp_survive_Min", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Updated Temp_survive_Min=" +
			// newFish.hashmap.get("Temp_survive_Min"));
			if ((Integer) newFish.hashmap.get("Temp_survive_Max") < Temp_survive_Max) {
			    newFish.hashmap.put("Temp_survive_Max", Temp_survive_Max);
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Temp_survive_Max=" + newFish.hashmap.get("Temp_survive_Max"));

			// Log.i("Array_Fish", "Array_Fish loadFishFile old Temp_Optimal=" + newFish.hashmap.get("Temp_Optimal"));
			String tempS = (String) (newFish.hashmap.get("Temp_Optimal"));
			if (newFish.hashmap.get("Temp_Optimal").equals("")) {
			    newFish.hashmap.put("Temp_Optimal", Temp_Optimal);

			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Temp_Optimal=" + newFish.hashmap.get("Temp_Optimal"));

			// -----------
			double tempAD = (Double) newFish.hashmap.get("PH_survive_Min");
			if (tempAD == 0) {
			    tempAD = PH_survive_Min;
			} else {
			    tempAD = Math.min(PH_survive_Min, tempAD);
			}
			newFish.hashmap.put("PH_survive_Min", tempAD);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved PH_survive_Min=" + newFish.hashmap.get("PH_survive_Min"));

			if ((Double) newFish.hashmap.get("PH_survive_Max") < PH_survive_Max) {
			    newFish.hashmap.put("PH_survive_Max", PH_survive_Max);
			}
			// String temp = (String) newFish.hashmap.get("PH_Optimal");
			if (newFish.hashmap.get("PH_Optimal").equals("")) {
			    newFish.hashmap.put("PH_Optimal", PH_Optimal);
			}

			// ----
			tempAI = (Integer) newFish.hashmap.get("KH_survive_Min");
			if (tempAI == 0) {
			    tempAI = KH_survive_Min;
			} else {
			    tempAI = Math.min(KH_survive_Min, tempAI);
			}
			newFish.hashmap.put("KH_survive_Min", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved KH_survive_Min=" + newFish.hashmap.get("KH_survive_Min"));

			if ((Integer) newFish.hashmap.get("KH_survive_Max") < KH_survive_Max) {
			    newFish.hashmap.put("KH_survive_Max", KH_survive_Max);
			}
			// temp = (String) newFish.hashmap.get("KH_Optimal");
			if (newFish.hashmap.get("KH_Optimal").equals("")) {
			    newFish.hashmap.put("KH_Optimal", KH_Optimal);
			}

			// -----------
			tempAI = (Integer) newFish.hashmap.get("GH_survive_Min");
			if (tempAI == 0) {
			    tempAI = GH_survive_Min;
			} else {
			    tempAI = Math.min(GH_survive_Min, tempAI);
			}
			newFish.hashmap.put("GH_survive_Min", tempAI);

			if ((Integer) newFish.hashmap.get("GH_survive_Max") < GH_survive_Max) {
			    newFish.hashmap.put("GH_survive_Max", GH_survive_Max);
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved GH_survive_Max=" + newFish.hashmap.get("GH_survive_Max"));
			// temp = (String) newFish.hashmap.get("GH_Optimal");
			if (newFish.hashmap.get("GH_Optimal").equals("")) {
			    newFish.hashmap.put("GH_Optimal", GH_Optimal);
			}

			// -----
			tempAI = (Integer) newFish.hashmap.get("Salt_survive_Min");
			if (tempAI == 0) {
			    tempAI = Salt_survive_Min;
			} else {
			    tempAI = Math.min(Salt_survive_Min, tempAI);
			}
			newFish.hashmap.put("Salt_survive_Min", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Salt_survive_Min=" + newFish.hashmap.get("Salt_survive_Min"));

			if ((Integer) newFish.hashmap.get("Salt_survive_Max") < Salt_survive_Max) {
			    newFish.hashmap.put("Salt_survive_Max", Salt_survive_Max);
			}
			tempS = (String) (newFish.hashmap.get("Salt_Optimal"));
			if (tempS.equals("")) {
			    if (!Salt_Optimal.equals("")) {
				newFish.hashmap.put("Salt_Optimal", Salt_Optimal);
			    }
			}

			if (newFish.hashmap.get("size").equals("")) {
			    newFish.hashmap.put("size", size);
			}

			tempAI = (Integer) newFish.hashmap.get("tank_min_liters");
			if (tempAI == 0) {
			    tempAI = tank_min_liters;
			} else {
			    tempAI = Math.min(tank_min_liters, tempAI);
			}
			newFish.hashmap.put("tank_min_liters", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved tank_min_liters=" + newFish.hashmap.get("tank_min_liters"));

			tempAI = (Integer) newFish.hashmap.get("tank_min_length");
			if (tempAI == 0) {
			    tempAI = tank_min_length;
			} else {
			    tempAI = Math.min(tank_min_length, tempAI);
			}
			newFish.hashmap.put("tank_min_length", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved tank_min_length=" + newFish.hashmap.get("tank_min_length"));

			if (newFish.hashmap.get("diet").equals("")) {
			    newFish.hashmap.put("diet", diet);
			}

			if (newFish.hashmap.get("algaeeater").equals("false")) {
			    newFish.hashmap.put("algaeeater", algaeeater);
			}

			if (newFish.hashmap.get("predator").equals("false")) {
			    newFish.hashmap.put("predator", predator);
			}

			if (newFish.hashmap.get("level").equals("")) {
			    newFish.hashmap.put("level", level);
			}

			if (newFish.hashmap.get("Fe_M_ratio").equals("")) {
			    newFish.hashmap.put("Fe_M_ratio", Fe_M_ratio);
			}
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Fe_M_ratio=" + newFish.hashmap.get("Fe_M_ratio"));

			tempAI = (Integer) newFish.hashmap.get("Min_group_nmbr");
			if (tempAI == 0) {
			    tempAI = Min_group_nmbr;
			} else {
			    tempAI = Math.min(Min_group_nmbr, tempAI);
			}
			newFish.hashmap.put("Min_group_nmbr", tempAI);
			// Log.i("Array_Fish", "Array_Fish loadFishFile Saved Min_group_nmbr=" + newFish.hashmap.get("Min_group_nmbr"));

			if (newFish.hashmap.get("Sex_diff").equals("")) {
			    newFish.hashmap.put("Sex_diff", Sex_diff);
			}

			if (newFish.hashmap.get("difficulty").equals("")) {
			    newFish.hashmap.put("difficulty", difficulty);
			}

			if (newFish.hashmap.get("Behavior").equals("")) {
			    newFish.hashmap.put("Behavior", Behavior);
			}

			// total_insertSpecies(newFish);
			if (getFish(genus + " " + species) == null) {
			    total_insertSpecies(newFish);
			    search_insertSpecies(newFish);
			}
			// }

		    } catch (NullPointerException T) {
			Log.i("Array_Fish", "Array_Fish loadFishFile NullPointerException" + T.getCause() + "\n" + line);
			T.printStackTrace();
		    } catch (ClassCastException T) {
			Log.i("Array_Fish", "Array_Fish loadFishFile ClassCastException" + T.getCause() + "\n" + line);
			T.printStackTrace();
		    } catch (NumberFormatException T) {
			Log.i("Array_Fish", "Array_Fish loadFishFile NumberFormatException" + T.getCause() + "\n" + line);
			T.printStackTrace();
		    } catch (StringIndexOutOfBoundsException T) {
			Log.i("Array_Fish",
				"Array_Fish loadFishFile StringIndexOutOfBoundsException (something wrong with this string value)"
					+ T.getCause() + "\n" + line);
			T.printStackTrace();
		    } catch (Exception T) {
			Log.i("-----Array_Fish", "Array_Fish loadFishFile I FAIL " + T.getClass() + "\n" + line);
			// Class_Controller.CastErrors(T);
		    }
		}
	    }

	    Log.i("-----Array_Fish", this.getClass().toString() + " Done loading from file ");
	} catch (NullPointerException T) {
	    Log.i(this.getClass().toString(), "loadFishFile J1 FAIL fant ikke datafilen.\n" + line);
	} catch (Exception T) {
	    Log.i(this.getClass().toString(), "loadFishFile J2 FAIL " + T.getLocalizedMessage() + "\n" + line);
	    T.printStackTrace();
	}

    }

    private static class_Fish findFishOrMakeNewFish(String genus, String species, String commons) {
	class_Fish old = getFish(genus + " " + species);
	if (old != null) {
	    return old;
	}
	newFish = new class_Fish(genus, species, commons);
	newFish.hashmap.put("Temp_survive_Min", 0);
	newFish.hashmap.put("Temp_survive_Max", 0);
	newFish.hashmap.put("Temp_Optimal", "0");
	newFish.hashmap.put("PH_survive_Min", 0.0);
	newFish.hashmap.put("PH_survive_Max", 0.0);
	newFish.hashmap.put("PH_Optimal", "0");
	newFish.hashmap.put("KH_survive_Min", 0);
	newFish.hashmap.put("KH_survive_Max", 0);
	newFish.hashmap.put("KH_Optimal", "0");
	newFish.hashmap.put("GH_survive_Min", 0);
	newFish.hashmap.put("GH_survive_Max", 0);
	newFish.hashmap.put("GH_Optimal", "0");
	newFish.hashmap.put("Salt_survive_Min", 0);
	newFish.hashmap.put("Salt_survive_Max", 0);
	newFish.hashmap.put("Salt_Optimal", "0");
	newFish.hashmap.put("size", "");
	newFish.hashmap.put("tank_min_liters", 0);
	newFish.hashmap.put("tank_min_length", 0);
	newFish.hashmap.put("diet", "");
	newFish.hashmap.put("algaeeater", false);
	newFish.hashmap.put("predator", false);
	newFish.hashmap.put("level", "");
	newFish.hashmap.put("Fe_M_ratio", 0);
	newFish.hashmap.put("Min_group_nmbr", 0);
	newFish.hashmap.put("Sex_diff", "");
	newFish.hashmap.put("difficulty", "");
	newFish.hashmap.put("Behavior", "");
	return newFish;
    }

    private void total_insertSpecies(class_Fish newspecies) {
	//Log.i("Array_Fish", "Array_Fish total_insertSpecies " + newspecies.get_Genus() + " " + newspecies.get_Species() + ".");
	for (int X = 0; X < Total_FishArray.length; X++) {
	    if (Total_FishArray[X] == null) {
		Total_FishArray[X] = newspecies;
		Total_FishFilled++;
		Total_FishNames_Nonpruned[X] = newspecies.get_Genus() + " " + newspecies.get_Species();
		if (match_checkfishmatch(newspecies)) {
		    match_insertSpecies(newspecies);
		}
		return;
	    }
	}
	Log.i("-----Array_Fish", "!!!!  Total_FishArray is full! !!!");
	System.err.println("!!!!  Total_FishArray is full! !!!");
    }

    private void checkFishesAgainstSearch() {
	// Log.i("Array_Fish", "checkFishesAgainstSearch searchphrase="+searchphrase);
	for (int X = 0; X < Total_FishArray.length; X++) {
	    if (Total_FishArray[X] == null) {
		break;
	    } else {
		String genus = Total_FishArray[X].get_Genus();
		String species = Total_FishArray[X].get_Species();
		String commons = Total_FishArray[X].get_Commonnames();
		if ((genus.toLowerCase().indexOf(searchphrase) > -1) || (species.toLowerCase().indexOf(searchphrase) > -1)
			|| (commons.toLowerCase().indexOf(searchphrase) > -1)) {
		    search_insertSpecies(Total_FishArray[X]);
		}
	    }
	}
    }

    private static void search_insertSpecies(class_Fish newspecies) {
	// Log.i("Array_Fish", "Array_Fish search_insertSpecies " + newspecies.get_Genus() + " " + newspecies.get_Species() + "
	// searchphrase="+searchphrase);
	for (int X = 0; X < Search_FishArray.length; X++) {
	    if (Search_FishArray[X] == null) {
		Search_FishArray[X] = newspecies;
		Search_FishFilled++;
		Search_FishNames_Nonpruned[X] = newspecies.get_Genus() + " " + newspecies.get_Species();
		/*
		 * Log.i("Array_Fish", "Array_Fish search_insertSpecies " + X + " " + Search_FishNames_Nonpruned[X] + " " +
		 * Search_FishNames_Nonpruned);
		 */
		return;
	    }
	}
	Log.i("-----Array_Fish", "!!!!  Search_FishArray is full! !!!");
	System.err.println("!!!!  Search_FishArray is full! !!!");
    }

    private void checkFishesAgainstMatch() {
	// Log.i("-----Array_Fish", "checkFishesAgainstMatch ");
	for (int X = 0; X < Total_FishArray.length; X++) {
	    if (Total_FishArray[X] == null) {
		break;
	    } else {
		// String genus = Total_FishArray[X].get_Genus();
		// String species = Total_FishArray[X].get_Species();
		// String commons = Total_FishArray[X].get_Commonnames();
		if (match_checkfishmatch(Total_FishArray[X])) {
		    match_insertSpecies(Total_FishArray[X]);
		}
	    }
	}
    }

    public static boolean match_checkfishmatch(class_Fish newspecies) {
	// Log.i("-----Array_Fish", "Array_Fish match_checkfishmatch " + newspecies.get_Genus() + " " + newspecies.get_Species() + ".");
	// class_Aquarium class_Aquarium = class_Aquarium.getInstance();
	// TankVolume
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch class_Aquarium2.TankVolume=" + class_Aquarium.hashmap.get("TankVolume"));
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch newspecies.tank_min_liters=" + newspecies.hashmap.get("tank_min_liters"));
	@SuppressWarnings("static-access")
	int localTankVolume = (Integer) class_Aquarium.getInstance().hashmap.get("TankVolume");
	// int localTankVolume = 240;
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch tank_min_liters=" + newspecies.hashmap.get("tank_min_liters"));
	if ((Integer) newspecies.hashmap.get("tank_min_liters") > localTankVolume) {
	    return false;
	}

	// TankLength
	// int localTankLength = (Integer) class_Aquarium.hashmap.get("TankLength");
	int localTankLength = 120;
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch tank_min_length=" + newspecies.hashmap.get("tank_min_length"));
	if ((Integer) newspecies.hashmap.get("tank_min_length") > localTankLength) {
	    return false;
	}

	// Salinity
	// int localSalinity = (Integer) class_Aquarium.hashmap.get("Salinity");
	int localSalinity = 0;
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch Salt_survive_Min=" + newspecies.hashmap.get("Salt_survive_Min"));
	if ((Integer) newspecies.hashmap.get("Salt_survive_Min") > localSalinity) {
	    return false;
	}
	if ((Integer) newspecies.hashmap.get("Salt_survive_Max") < localSalinity) {
	    return false;
	}

	// GH
	@SuppressWarnings("static-access")
	int localGH = (Integer) class_Aquarium.getInstance().hashmap.get("GH");
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch GH_survive_Min=" + newspecies.hashmap.get("GH_survive_Min"));
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch GH_survive_Max=" + newspecies.hashmap.get("GH_survive_Max"));
	if ((Integer) newspecies.hashmap.get("GH_survive_Min") > localGH) {
	    return false;
	}
	if ((Integer) newspecies.hashmap.get("GH_survive_Max") < localGH) {
	    return false;
	}

	// KH
	@SuppressWarnings("static-access")
	int localKH = (Integer) class_Aquarium.getInstance().hashmap.get("KH");
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch KH_survive_Min=" + newspecies.hashmap.get("KH_survive_Min"));
	if ((Integer) newspecies.hashmap.get("KH_survive_Min") > localKH) {
	    return false;
	}
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch KH_survive_Max=" + newspecies.hashmap.get("KH_survive_Max"));
	if ((Integer) newspecies.hashmap.get("KH_survive_Max") < localKH) {
	    // return false;
	}

	// PH
	@SuppressWarnings("static-access")
	Double localPH = (Double) class_Aquarium.getInstance().hashmap.get("PH");
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch PH_survive_Min=" + newspecies.hashmap.get("PH_survive_Min"));
	if ((Double) newspecies.hashmap.get("PH_survive_Min") > localPH) {
	    return false;
	}
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch PH_survive_Max=" + newspecies.hashmap.get("PH_survive_Max"));
	if ((Double) newspecies.hashmap.get("PH_survive_Max") < localPH) {
	    return false;
	}

	// temp
	@SuppressWarnings("static-access")
	int localtemp = (Integer) class_Aquarium.getInstance().hashmap.get("Temp");
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch Temp_survive_Min=" + newspecies.hashmap.get("Temp_survive_Min"));
	if ((Integer) newspecies.hashmap.get("Temp_survive_Min") > localtemp) {
	    return false;
	}
	// Log.i("Array_Fish", "Array_Fish match_checkfishmatch Temp_survive_Max=" + newspecies.hashmap.get("Temp_survive_Max"));
	if ((Integer) newspecies.hashmap.get("Temp_survive_Max") < localtemp) {
	    return false;
	}
	// Log.i("Array_Fish", "Array_Fish Adding species");
	return true;
    }

    private static void match_insertSpecies(class_Fish newspecies) {
	// Log.i("Array_Fish", "Array_Fish match_insertSpecies " + newspecies.get_Species() + ".");
	for (int X = 0; X < Match_FishArray.length; X++) {
	    if (Match_FishArray[X] == null) {
		Match_FishArray[X] = newspecies;
		Match_FishFilled++;
		// Match_FishNames_Nonpruned[X] = newspecies;
		Match_FishNames_Nonpruned[X] = newspecies.get_Genus() + " " + newspecies.get_Species();
		return;
	    }
	}
	Log.i("Array_Fish", "!!!!  Match_FishArray is full! !!!");
	System.err.println("!!!!  Match_FishArray is full! !!!");
    }

    public static class_Fish getFish(String clickedfish) {
	clickedfish = clickedfish.toLowerCase();
	if (searchphrase.length() == 0) {
	    for (int X = 0; X < Total_FishArray.length; X++) {
		if (Total_FishArray[X] == null) {
		    return null;
		}
		String temp = Total_FishArray[X].get_Genus() + " " + Total_FishArray[X].get_Species();
		temp = temp.toLowerCase();
		if (temp.equals(clickedfish)) {
		    return Total_FishArray[X];
		}
	    }
	} else {
	    for (int X = 0; X < Search_FishArray.length; X++) {
		if (Search_FishArray[X] == null) {
		    return null;
		}
		if ((Search_FishArray[X].get_Genus() + " " + Search_FishArray[X].get_Species()).equals(clickedfish)) {
		    return Search_FishArray[X];
		}
	    }
	}
	return null;
    }

    public static HashMap<String, Object> getFishHashmap(String clickedfish) {
	for (int X = 0; X < Total_FishArray.length; X++) {
	    if (Total_FishArray[X] == null) {
		return null;
	    } else if ((Total_FishArray[X].get_Genus() + " " + Total_FishArray[X].get_Species()).equals(clickedfish)) {
		return (HashMap<String, Object>) Total_FishArray[X].hashmap;
	    }
	}
	return null;
    }
}
