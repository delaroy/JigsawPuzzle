package com.delaroystudios.jigsaw;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Custom Share Preferences. Include Set and Get
 * <pre>
 * CustomSharedPreferences(Context)
 * Context is null will return by preferences type
 * 1. String : null
 * 2. Boolean: false
 * 3. Integer, Long, Float: 0
 * </pre>
 * @author DucLH
 * @version 2011:0517
 * @since 2.1
 * 
 */

public class CustomSharedPreferences {
	
	private static SharedPreferences sharedPre;
	private static SharedPreferences.Editor editor;
	private static Context appContext;
	/**
	 * Constructor. Please create new object before using setter and getter
	 * @param context Input from Activity
	 */
/*	public CustomSharedPreferences(final Context context){
		if(null != context){
			sharedPre = context.getSharedPreferences(Constant.CUSTOM_SHAREDPREFERNCES, 0);
			editor = sharedPre.edit();
		}else{
			editor = null;
			sharedPre = null;
		}
	}*/
	public static void init (final Context context){
		appContext = context;
		if(null != context){
			sharedPre = context.getSharedPreferences(Constant.SHAREPRERENCE, 0);
			editor = sharedPre.edit();
		}else{
			editor = null;
			sharedPre = null;
		}
	}
	private static void refresh (){
		if(null != appContext){
			sharedPre = appContext.getSharedPreferences(Constant.SHAREPRERENCE, 0);
			editor = sharedPre.edit();
		}else{
			editor = null;
			sharedPre = null;
		}
	}
	/**
	 * Set data for String
	 * @param preName Preferences name
	 * @param value String input
	 */
	public synchronized static void setPreferences(final String preName, final String value){
		refresh();
		if(null != editor){
			editor.putString(preName, value);
			editor.commit();
		}
	}
	/**
	 * Get data for String
	 * @param preName Preferences name
	 * @param defaultValue
	 * @return String or 0 if Name not existed
	 */
	public static String getPreferences(final String preName, final String defaultValue){
		refresh();
		if(null != sharedPre){
			return sharedPre.getString(preName, defaultValue);
		}else{
			return null;
		}
	}
	
	/**
	 * Set data for boolean
	 * @param preName Preferences name
	 * @param value boolean input
	 */
	public synchronized static void setPreferences(final String preName, final boolean value){
		if(null != editor){
			editor.putBoolean(preName, value);
			editor.commit();
		}
	}
	/**
	 * Get data for boolean
	 * @param preName Preferences name
	 * @param defaultValue
	 * @return boolean or 0 if Name not existed
	 */
	public static boolean getPreferences(final String preName, final boolean defaultValue){
		if(null != sharedPre){
			return sharedPre.getBoolean(preName, defaultValue);
		}else{
			return false;
		}
	}
	
	/**
	 * Set data for Integer
	 * @param preName Preferences name
	 * @param value Integer input
	 */
	public synchronized static void setPreferences(final String preName, final int value){
		if(null != editor){
			editor.putInt(preName, value);
			editor.commit();
		}
	}
	/**
	 * Get data for Integer
	 * @param preName Preferences name
	 * @param defaultValue
	 * @return Integer or 0 if Name not existed
	 */
	public static int getPreferences(final String preName, final int defaultValue){
		if(null != sharedPre){
			return sharedPre.getInt(preName, defaultValue);
		}else{
			return 0;
		}
	}
	
	/**
	 * Set data for Long
	 * @param preName Preferences name
	 * @param value Long input
	 */
	public synchronized static void setPreferences(final String preName, final long value){
		if(null != editor){
			editor.putLong(preName, value);
			editor.commit();
		}
	}
	/**
	 * Get data for Long
	 * @param preName Preferences name
	 * @param defaultValue
	 * @return Long or 0 if Name not existed
	 */
	public static long getPreferences(final String preName, final long defaultValue){
		if(null != sharedPre){
			return sharedPre.getLong(preName, defaultValue);
		}else{
			return 0;
		}
	}
	
	/**
	 * Set data for Float
	 * @param preName Preferences name
	 * @param value Float input
	 */
	public synchronized static void setPreferences(final String preName, final float value){
		if(null != editor){
			editor.putFloat(preName, value);
			editor.commit();
		}
	}
	/**
	 * Get data for Float
	 * @param preName Preferences name
	 * @param defaultValue
	 * @return Float or 0 if Name not existed
	 */
	public static float getPreferences(final String preName, final float defaultValue){
		if(null != sharedPre){
			return sharedPre.getFloat(preName, defaultValue);
		}else{
			return 0;
		}
		
	}
	
}
