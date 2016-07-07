package com.delaroystudios.jigsaw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;

import com.delaroystudios.jigsaw.R;

public class ReadCSVtwo {
	Context mContext;
	
	public ReadCSVtwo(Context context) {
		// TODO Auto-generated constructor stub\
		this.mContext = context;
	}
	
	public ArrayList<PuzzEntity> getPuzzList(){
		ArrayList<PuzzEntity> puzzList = new ArrayList<PuzzEntity>();
		InputStream is = mContext.getResources().openRawResource
				
	            (R.raw.datatwo);
	                BufferedReader reader = new BufferedReader(new InputStreamReader
	            (is));
	                try {
	                    String line;
	                    while ((line = reader.readLine()) != null) {
	                        // do something with "line"
//	                    	Log.d("ThangTB", "line:"+line);
	                        String[] RowData = line.split(";");
	                        ArrayList<String> parts = new ArrayList<String>();
	                        ArrayList<Point> points = new ArrayList<Point>();
	                        
	                        for (int i = 4; i < 13; i++) {
								parts.add(RowData[i]);
							}
	                        
	                        Point p;
	                        int f =0;
	                        int x =0;
	                        int y=0;
	                        
	                        for (int i = 13; i< RowData.length; i++) {
	                        	if (f%2==0) {
	                        		x = Integer.parseInt(RowData[i]);
								}else if (f%2==1){
									y = Integer.parseInt(RowData[i]);
									p = new Point(x, y);
									points.add(p);
									
								}
	                        	f++;
							}
	                         PuzzEntity entity = new PuzzEntity(RowData[0], RowData[1], RowData[2], RowData[3], parts, points);
	                         
	                         puzzList.add(entity);
	                    }
	                }catch (IOException ex) {
	                    // handle exception
	                }finally {
	                    try {
	                        is.close();
	                    }
	                    catch (IOException e) {
	                        // handle exception
	                    }
	                }
		return puzzList;
	}

}
