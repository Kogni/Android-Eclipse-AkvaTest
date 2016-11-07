package com.pynting.akvaapp_e_20160315;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import android.os.Environment;
import android.util.Log;

public class class_Aquarium {

    private static volatile class_Aquarium instance;
    public static Map<String, Object> hashmap = new HashMap<String, Object>();

    int Temp = 24;
    double PH = 7.5;
    int KH = 2;
    int GH = 10;
    double Salinity = 0.0;
    static int TankVolume = 240;
    static int TankLength = 120;

    String settingsfil = "settings_aquarium";

    private class_Aquarium() {//constructor

	hashmap.put("Temp", Temp);
	Log.i("-----class_Aquarium", "class_Aquarium class_Aquarium hashMap.get('Temp')=" + hashmap.get("Temp"));
	hashmap.put("PH", PH);
	hashmap.put("KH", KH);
	hashmap.put("GH", GH);
	hashmap.put("Salinity", Salinity);
	hashmap.put("TankLength", TankLength);
	hashmap.put("TankVolume", TankVolume);
	saveSettings();
	//loadSettingsFile();
	loadSettingsFile2();
    }

    private void loadSettingsFile2() {
	try {
	    File dir = Environment.getExternalStorageDirectory();

	    File file = new File(dir, "settings_aquarium");

	    if (file.exists()) // check if file exist
	    {
		//Read text from file
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
			Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile line=" + line);
			text.append(line);
			text.append("\n");
		    }
		    br.close();
		} catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		//Set the text
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile text=" + text);
	    } else {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile Sorry file doesn't exist!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @SuppressWarnings({ "deprecation", "unused" })
    private void loadSettingsFile() {
	Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile");

	String line = "";
	try {
	    String state = Environment.getExternalStorageState();
	    Log.i("class_Aquarium", "-----class_Aquarium state=" + state);
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile Read and Write permissions enabled!");
	    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile Read-only permissions enabled");
	    } else {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile File reading refused!");
		return;
	    }
	    //File testFile = new File("");
	    //String currentPath = testFile.getAbsolutePath();
	    //System.out.println("current path is: " + currentPath);

	    //Class_Controller.PrintAction(this.getClass().toString() + " Loading phrases from file " + Filnavn);
	    //Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile E " + filen.exists());

	    /*ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
	    InputStream input1 = classLoader1.getResourceAsStream(settingsfil);
	    input1 = classLoader1.getResourceAsStream("com/pynting/akvaapp/" + settingsfil);
	    Log.i("Array_Fish", "-----Array_Fish loadSettingsFile F " + input1);
	    DataInputStream in1 = new DataInputStream(input1);*/

	    String extr = Environment.getExternalStorageDirectory().toString();
	    File mFolder = new File(extr + "");
	    if (!mFolder.exists()) {
		mFolder.mkdir();
	    }
	    File f = new File(mFolder.getAbsolutePath(), settingsfil);
	    ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
	    InputStream input2 = classLoader2.getResourceAsStream(f.getAbsolutePath());
	    Log.i("Array_Fish", "-----Array_Fish loadSettingsFile G " + input2);
	    DataInputStream in2 = new DataInputStream(input2);
	    Log.i("Array_Fish", "Array_Fish loadFishFile in2=" + in2);

	    //int X = 0;
	    String value = "";
	    while (in2.available() > 0) {
		//SaveWord(in2.readLine(), 100, Filnavn.equals(TopicsFile), Filnavn.equals(RequirementsFile));
		line = in2.readLine();
		if (line.length() > 1) {
		    try {

			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0";
			}
			int Temp_Temp = Temp;
			try {
			    Temp_Temp = Integer.parseInt(value);
			} catch (Exception E) {
			    try {
				Temp_Temp = (int) Math.ceil(Double.parseDouble(value));
			    } catch (Exception E2) {

			    }
			}
			Temp = Temp_Temp;

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			PH = Double.parseDouble(value);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			KH = Integer.parseInt(value);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			GH = Integer.parseInt(value);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			Salinity = Double.parseDouble(value);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			TankVolume = Integer.parseInt(value);

			line = line.substring(line.indexOf("|") + 1);
			value = line.substring(0, line.indexOf("|"));
			if (value.equals("")) {
			    value = "0.0";
			}
			TankLength = Integer.parseInt(value);

			Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile Temp=" + Temp);
			line = line.substring(line.indexOf("|") + 1);
		    } catch (StringIndexOutOfBoundsException T) {
			Log.i("-----class_Aquarium", "class_Aquarium loadSettingsFile FAIL C " + T.getLocalizedMessage() + "\n" + line);
			T.printStackTrace();
		    } catch (Exception T) {
			Log.i("-----class_Aquarium", "class_Aquarium loadSettingsFile FAIL B " + T.getLocalizedMessage() + "\n" + line);
			T.printStackTrace();
		    }

		}
	    }
	} catch (NullPointerException T) {
	    Log.i("-----class_Aquarium", "class_Aquarium loadSettingsFile FAIL A2 NullPointerException " + T.getLocalizedMessage() + "\n"
		    + line);
	    T.printStackTrace();
	} catch (Exception T) {
	    Log.i("-----class_Aquarium", "class_Aquarium loadSettingsFile FAIL A Exception " + T.getLocalizedMessage() + "\n" + line);
	    T.printStackTrace();
	} finally {
	    Log.i("-----class_Aquarium", "class_Aquarium loadSettingsFile finally");
	}

    }

    private void saveSettings() {
	Log.i("class_Aquarium", "-----class_Aquarium saveSettings");

	@SuppressWarnings("unused")
	String line = "";
	try {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
		Log.i("class_Aquarium", "-----class_Aquarium saveSettings read and Write permissions enabled!");
	    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile Read-only permissions enabled");
		return;
	    } else {
		Log.i("class_Aquarium", "-----class_Aquarium loadSettingsFile File reading refused!");
		return;
	    }
	    String fileName = "settings_aquarium";
	    //ByteArrayOutputStream aa = new ByteArrayOutputStream();
	    try {
		File f = new File(Environment.getExternalStorageDirectory(), fileName);
		FileOutputStream fos2 = null;
		fos2 = new FileOutputStream(f);

		OutputStreamWriter myOutWriter = new OutputStreamWriter(fos2);

		String datatxt = "23|7.4|1|11|0.0|241|121|";
		myOutWriter.append(datatxt);
		myOutWriter.close();

		fos2.close();
		Log.i("class_Aquarium", "-----class_Aquarium saveSettings Done writing to file. ");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    /*
	    Log.i("class_Aquarium", "-----class_Aquarium saveSettings 1 " + fileName);
	    //Log.i("class_Aquarium", "-----class_Aquarium saveSettings 2 " + getClass().getResource(fileName));
	    //Log.i("class_Aquarium", "-----class_Aquarium saveSettings 3 " + getClass().getClassLoader().getResource(fileName));
	    BufferedOutputStream bos = null;
	    //DataInputStream bis = null;
	    try {
	    String extr = Environment.getExternalStorageDirectory().toString();
	    File mFolder = new File(extr + "");
	    if (!mFolder.exists()) {
	        mFolder.mkdir();
	    }
	    //File f = new File(mFolder.getAbsolutePath(), fileName);
	    File f = new File(Environment.getExternalStorageDirectory(), fileName);
	    FileOutputStream fos2 = null;
	    fos2 = new FileOutputStream(f);
	    bos = new BufferedOutputStream(fos2, 8192);
	    bos.write("test".getBytes());
	    fos2.flush();
	    fos2.close();
	    Log.i("class_Aquarium", "-----class_Aquarium saveSettings Done writing to file. ");
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	    */
	} finally {

	}
    }

    public static class_Aquarium getInstance() {
	if (instance == null) {
	    synchronized (Controller.class) {
		if (instance == null) {
		    instance = new class_Aquarium();
		}
	    }
	}
	return instance;
    }

    

    public static void setVar(String var, String ny) {
	//Log.i("------class_Aquarium", "class_Aquarium setVar 1 var=" + hashmap.get(var) + " " + hashmap.get(var).getClass());
	if (hashmap.get(var) instanceof Integer) {
	    int temp = Integer.parseInt(hashmap.get(var) + "");
	    try {
		temp = Integer.parseInt(ny);
	    } catch (Exception E) {
		try {
		    temp = (int) Math.ceil(Double.parseDouble(ny));
		} catch (Exception E2) {

		}
	    }
	    hashmap.put(var, temp);
	} else if (hashmap.get(var) instanceof Double) {
	    Double temp = Double.parseDouble(hashmap.get(var) + "");
	    try {
		temp = Double.parseDouble(ny);
	    } catch (Exception E) {
		try {
		    temp = Double.parseDouble(ny);
		} catch (Exception E2) {

		}
	    }
	    hashmap.put(var, temp);
	} else if (hashmap.get(var) instanceof String) {
	    String temp = hashmap.get(var) + "";
	    try {
		temp = ny;
	    } catch (Exception E) {
		try {
		    temp = ny;
		} catch (Exception E2) {

		}
	    }
	    hashmap.put(var, temp);
	}
	//Log.i("------class_Aquarium", "class_Aquarium setVar 2 var=" + hashmap.get(var));
    }

}
