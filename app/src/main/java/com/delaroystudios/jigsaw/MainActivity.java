package com.delaroystudios.jigsaw;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
//import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.delaroystudios.jigsaw.R;

public class MainActivity extends Activity {

	ImageView imgTouch;
	ImageView imgFix;
	LinearLayout ll_content;
	CTFrameLayout fr;
	RelativeLayout rootView;
	Button btnPre;
	Button btnNext;
	LinearLayout btnPre1;
	LinearLayout btnNext1;
	public int distancesPie =0;
	// anim vars
	public static int animT = 150;
	private boolean enabled = false, touching = false;
	ArrayList<ImageView> arrListImage;
	DisplayMetrics dm;
	
	private int SCREENW , SCREENH;
	
	int LF,TF;
	int IMGLEFT,IMGTOP;
	
	int imgFixW;
	int imgFixH;
	int curLayout=0;
	int numHasAddToView=0;
	Double scale;

	ArrayList<String> imagelist;
	ArrayList<PuzzEntity> puzzList;
	ArrayList<Point> pointList;
	ArrayList<Integer> RandomList;
	
	PuzzEntity puzzEntity;
	
	int curX;int curY;
	Point curpoint;
	public MediaPlayer mp;
	private boolean isClicked = false;
	ImageView imgComplete1;
	ImageView imgHome;
	ImageView imgHome1;
	
	int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
	Vibrator vib;
	Animation animDown ;
	Animation animUp ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		   
		imgFix = (ImageView)findViewById(R.id.fix_layout);
		rootView = (RelativeLayout)findViewById(R.id.root_view);
		fr = (CTFrameLayout) findViewById(R.id.fr_layout);
		ll_content = (LinearLayout)findViewById(R.id.ll_layout);
		btnPre = (Button)findViewById(R.id.btnPrev);
		btnPre1 = (LinearLayout)findViewById(R.id.btnPrev1);
		btnNext = (Button)findViewById(R.id.btnNext);
		btnNext1 = (LinearLayout)findViewById(R.id.btnNext1);
		imgComplete1 = (ImageView)findViewById(R.id.img_showWord);
		imgHome = (ImageView)findViewById(R.id.imageView_home);
		imgHome1 = (ImageView)findViewById(R.id.imageView_home1);
		imgHome1.setVisibility(View.GONE);
		
		
		curpoint = new Point();
		dm = new DisplayMetrics();
		
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Display display = getWindowManager().getDefaultDisplay(); 
		SCREENW = display.getWidth();  // deprecated
		SCREENH = display.getHeight();  // deprecated
		
		arrListImage = new ArrayList<ImageView>();
		pointList = new ArrayList<Point>();
		imagelist = new ArrayList<String>();
		RandomList = new ArrayList<Integer>();
		puzzList = new ArrayList<PuzzEntity>();
		mp = new MediaPlayer();
		
		
		//getData
		Configuration conf = getResources().getConfiguration();
	    int screenLayout = 1; // application default behavior
	    try {
	        Field field = conf.getClass().getDeclaredField("screenLayout");
	        screenLayout = field.getInt(conf);
	    } catch (Exception e) {
	        // NoSuchFieldException or related stuff
	    }

	    // Configuration.SCREENLAYOUT_SIZE_MASK == 15
	    int screenType = screenLayout & 15;

	    // Configuration.SCREENLAYOUT_SIZE_SMALL == 1
	    // Configuration.SCREENLAYOUT_SIZE_NORMAL == 2
	    // Configuration.SCREENLAYOUT_SIZE_LARGE == 3
	    // Configuration.SCREENLAYOUT_SIZE_XLARGE == 4

	    
	    if (screenType == 1) {ReadCSV r = new ReadCSV(getApplicationContext());puzzList = r.getPuzzList();
	    } else if (screenType == 2) {ReadCSV r = new ReadCSV(getApplicationContext());puzzList = r.getPuzzList();
	    } else if (screenType == 3) {ReadCSVtwo r = new ReadCSVtwo(getApplicationContext());puzzList = r.getPuzzList();
	    } else if (screenType == 4) {ReadCSVtwo r = new ReadCSVtwo(getApplicationContext());puzzList = r.getPuzzList();
	    } else { // undefined
	    }
		
		
		
		
		
		
		//get sharepreference
		a = CustomSharedPreferences.getPreferences(Constant.SP_DISPLAY_GRID, 1);
		b = CustomSharedPreferences.getPreferences(Constant.SP_EASY_PIE, 1);
		c = CustomSharedPreferences.getPreferences(Constant.SP_ENABLE_SOUND, 1);
		d = CustomSharedPreferences.getPreferences(Constant.SP_DISPLAY_WORDs, 1);
		e = CustomSharedPreferences.getPreferences(Constant.SP_VIBRATE_DRAG, 1);
		f = CustomSharedPreferences.getPreferences(Constant.SP_VIBRATE_PIE, 1);
		g = CustomSharedPreferences.getPreferences(Constant.SP_ENABLE_WORD_SOUND, 1);
		h = CustomSharedPreferences.getPreferences(Constant.SP_RESTART_PUZZLE, 1);
		//Vibrator
		vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE); 
		animDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
//		animUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);
		if (b==1) {
			distancesPie = Constant.ST_SUPPORT_PIE;
		}else{
			distancesPie = Constant.ST_NOSUPPORT_PIE;
		}
		if (h==1) {
		    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		    mAccel = 0.00f;
		    mAccelCurrent = SensorManager.GRAVITY_EARTH;
		    mAccelLast = SensorManager.GRAVITY_EARTH;
		}
		
		initPuzzles();
		
		fr.setOnInterceptTouchEvent(new CTFrameLayout.OnInterceptTouch() {

			@Override
			public void OnIntercept(MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					touching = true;
					Configuration conf = getResources().getConfiguration();
				    int screenLayout = 1; // application default behavior
				    try {
				        Field field = conf.getClass().getDeclaredField("screenLayout");
				        screenLayout = field.getInt(conf);
				    } catch (Exception e) {
				        // NoSuchFieldException or related stuff
				    }

				    // Configuration.SCREENLAYOUT_SIZE_MASK == 15
				    int screenType = screenLayout & 15;

				    // Configuration.SCREENLAYOUT_SIZE_SMALL == 1
				    // Configuration.SCREENLAYOUT_SIZE_NORMAL == 2
				    // Configuration.SCREENLAYOUT_SIZE_LARGE == 3
				    // Configuration.SCREENLAYOUT_SIZE_XLARGE == 4

				    if (screenType == 1) {scale = (double) (((double)imgFix.getHeight())/(double)320);
				    } else if (screenType == 2) {scale = (double) (((double)imgFix.getHeight())/(double)320);
				    } else if (screenType == 3) {scale = (double) (((double)imgFix.getHeight())/(double)520);
				    } else if (screenType == 4) {scale = (double) (((double)imgFix.getHeight())/(double)520);
				    } else { // undefined
				    }
					
					IMGLEFT =getLeftChildInParent(imgFix, rootView);
					IMGTOP = getTopChildInParent(imgFix, rootView);
					Log.d("ThangTB", "--move = ");
//					Log.d("ThangTB", "--leftImage = "+IMGLEFT +"---TopImage:"+IMGTOP);
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX(),
					y = (int) event.getY();
					if (enabled && touching && imgTouch!=null) {
						curX = x;
						curY =y;
						curpoint.setX(curX);
						curpoint.setY(curY);
//						Log.d("ThangTB", "touch move - x="+x+" y="+y+" id ="+imgTouch.getId());
						// change draw location of dragged visual
						if (x < (imgTouch.getWidth() / 2)) {
							x = (imgTouch.getWidth() / 2);
						}else if(x > SCREENW-(imgTouch.getWidth() / 2)){
							x = SCREENW-(imgTouch.getWidth() / 2);
						}

						if (y < (imgTouch.getHeight() / 2)) {
							y = (imgTouch.getHeight() / 2);
						}else if(y > SCREENH-(imgTouch.getHeight() / 2)){
							y = SCREENH-(imgTouch.getHeight() / 2);
						}

						int l = x - (imgTouch.getWidth() / 2), 
								t = y - (imgTouch.getHeight() / 2), 
								r = l + imgTouch.getWidth(), 
								b = t + imgTouch.getHeight();

						imgTouch.layout(l, t, r, b);

						imgTouch.invalidate();
						
						addToLayout(imgTouch.getId());
					}

					break;
				case MotionEvent.ACTION_UP:
					enabled = false;
					break;
				}
			}
		});

		
		btnPre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isClicked) {
					return;
				}else{
					isClicked =true;
				}
				if (curLayout==0) {
					isClicked = false;
					return;
				}
				if (null != imgComplete1) {
					imgComplete1.setVisibility(View.GONE);
				}
				
				
				if (curLayout>0) {
					curLayout = curLayout-1;
					initPuzzles();
					
				}else{
					curLayout=0;
					isClicked = false;
				}
				 if (null!= imgHome) {
					 if(imgHome.getVisibility() == View.GONE){
						 imgHome.setVisibility(View.VISIBLE); 
						 imgHome1.setVisibility(View.GONE);
					 }
					 imgHome.bringToFront();
				}
			}
		});
		btnPre1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isClicked) {
					return;
				}else{
					isClicked =true;
				}
				if (curLayout==0) {
					isClicked = false;
					return;
				}
				if (null != imgComplete1) {
					imgComplete1.setVisibility(View.GONE);
				}
				
				
				if (curLayout>0) {
					curLayout = curLayout-1;
					initPuzzles();
				}else{
					curLayout=0;
					isClicked = false;
				}
				 if (null!= imgHome) {
					 if(imgHome.getVisibility() == View.GONE){
						 imgHome.setVisibility(View.VISIBLE); 
						 imgHome1.setVisibility(View.GONE);
					 }
					 imgHome.bringToFront();
				}
			}
		});
		
		
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isClicked) {
					return;
				}else{
					isClicked =true;
				}
				if(curLayout==(puzzList.size()-1)){
					isClicked = false;
					return;
				}
				if (null != imgComplete1) {
					imgComplete1.setVisibility(View.GONE);
				}
				if (curLayout<(puzzList.size()-1)) {
					btnNext.setVisibility(View.VISIBLE);
					curLayout = curLayout+1;
					initPuzzles();
					
				}else {
					isClicked = false;
				}
				if (null!= imgHome) {
					 if(imgHome.getVisibility() == View.GONE){
						 imgHome.setVisibility(View.VISIBLE); 
						 imgHome1.setVisibility(View.GONE);
					 }
					 imgHome.bringToFront();
				}
			}
		});
		
		btnNext1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isClicked) {
					return;
				}else{
					isClicked =true;
				}
				if(curLayout==(puzzList.size()-1)){
					isClicked = false;
					return;
				}
				if (null != imgComplete1) {
					imgComplete1.setVisibility(View.GONE);
				}
				if (curLayout<(puzzList.size()-1)) {
					btnNext.setVisibility(View.VISIBLE);
					curLayout = curLayout+1;
					initPuzzles();
					
				}else {
					isClicked = false;
				}
				if (null!= imgHome) {
					 if(imgHome.getVisibility() == View.GONE){
						 imgHome.setVisibility(View.VISIBLE); 
						 imgHome1.setVisibility(View.GONE);
					 }
					 imgHome.bringToFront();
				}
			}
		});
		
		imgHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		imgHome1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	/**
	 * init View
	 */
	public void initPuzzles(){
		numHasAddToView=0;
		RandomList = randomList();
		puzzEntity = puzzList.get(curLayout);
		if (a==1) {
			imgFix.setImageResource(getResId(puzzEntity.getLayout()));
		}else{
			imgFix.setImageResource(getResId(puzzEntity.getOutline()));
		}
		
		imagelist = puzzEntity.getParts();
		pointList = puzzEntity.getPoints();
		try {
			arrListImage.clear();
			for (int i = 0; i < 9; i++) {
				ImageView v = (ImageView)findViewById(i);
				fr.removeView(v);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		GeneralImage(imagelist);
		
		 btnPre.bringToFront();
    	 btnNext.bringToFront();
    	 btnPre1.bringToFront();
    	 btnNext1.bringToFront();
	}
	
	/**
	 * must has childView
	 * @param child
	 * @param parent
	 * @return
	 */
	int getLeftChildInParent(View child, View parent){
		int left = 0;
		View v = child;
		while(!v.equals(parent)){
			left+= v.getLeft();
			v = (View) v.getParent();
		}
		return left;
	}
	
	
	/**
	 * must has childView
	 * @param child
	 * @param parent
	 * @return
	 */
	int getTopChildInParent(View child, View parent){
		int left = 0;
		View v = child;
		while(!v.equals(parent)){
			left+= v.getTop();
			v = (View) v.getParent();
		}
		return left;
	}

	/**
	 * add image pie to ... 
	 * @param id
	 */
	private void addToLayout(int id){
		Point p = null;
		LF =IMGLEFT+(int)(pointList.get(id).x*scale);
		TF=IMGTOP+(int)(pointList.get(id).y*scale);
		if (p==null) {
			p = new Point(LF+(imgTouch.getWidth() / 2), TF+(imgTouch.getHeight() / 2));
		}
		
		if (distance(curpoint, p)<distancesPie) {
			fr.findViewById(id).setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			
			int l = LF, 
			t = TF, 
			r = l + imgTouch.getWidth(), 
			b = t + imgTouch.getHeight();

			imgTouch.layout(l, t, r, b);
			imgTouch.setOnTouchListener(null);
			touching = false;
			p=null;
			if (c==1) {
				mp = MediaPlayer.create(getApplicationContext(), R.raw.fix_sound);
				mp.start();
			}
			numHasAddToView = numHasAddToView+1;
			if (f==1) {
				try {
					vib.vibrate(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (numHasAddToView==9) {
				CompleteAddPuzz();
			}
		} 
		
	}
	
	// EVENT HELPERS
	/**
	 * set animation for image
	 * @param img
	 */
	protected void animateDragged(ImageView img) {

		int y = img.getTop();
		int x = img.getLeft();

		int l = x;
		int t = y;
		int r = l + img.getWidth();
		int b = t + img.getHeight();
		img.layout(l, t, r, b);
		AnimationSet animSet = new AnimationSet(true);
		ScaleAnimation scale = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,x/2, y/2);
		scale.setDuration(animT);
		AlphaAnimation alpha = new AlphaAnimation(1, .8f);
		alpha.setDuration(animT);

		animSet.setFillEnabled(true);
		animSet.setFillAfter(true);

		img.clearAnimation();
		img.startAnimation(animSet);
	}
	
	/**
	 * general Image from...
	 */
	private void GeneralImage(ArrayList<String> list){
		int id;
		for (int i = 0; i < list.size(); i++) {
			id = RandomList.get(i);
			String imageName = list.get(id);//"a_part_"+(i+1);
			final ImageView imageView = new ImageView(getApplicationContext());
			try {
				imageView.setImageResource(getResId(imageName));
			} catch (Exception e) {
				// TODO: handle exception
			}
			imageView.setId(id);
			imageView.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int action = event.getAction();
					switch (action) {
					case MotionEvent.ACTION_DOWN:
						Log.d("ThangTB", "--Image Touch ---");
						animateDragged(imageView);
						enabled = true;
						imgTouch = imageView;
						imgTouch.bringToFront();
						if (e==1) {
							try {
								vib.vibrate(50);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
				        if (null!= imgHome && (imgHome.getVisibility() == View.VISIBLE)) {
				        	imgHome.setVisibility(View.GONE);
				        	 imgHome1.setVisibility(View.VISIBLE);
				        	 imgHome1.bringToFront();
				        	 
				        	 btnPre.setVisibility(View.INVISIBLE);
				        	 btnNext.setVisibility(View.INVISIBLE);
				        	 
				        	 btnPre1.setVisibility(View.INVISIBLE);
				        	 btnNext1.setVisibility(View.INVISIBLE);
						}
						break;
					case MotionEvent.ACTION_MOVE:

						break;
					case MotionEvent.ACTION_UP:
						break;
					}
					return false;
				}
			});
			imageView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					animateDragged(imageView);
					enabled = true;
					imgTouch = imageView;
					imgTouch.bringToFront();
					if (e==1) {
						try {
							vib.vibrate(50);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
			        if (null!= imgHome && (imgHome.getVisibility() == View.VISIBLE)) {
			        	imgHome.setVisibility(View.GONE);
			        	 imgHome1.setVisibility(View.VISIBLE);
			        	 imgHome1.bringToFront();
			        	 
			        	 btnPre.setVisibility(View.INVISIBLE);
			        	 btnNext.setVisibility(View.INVISIBLE);
			        	 
			        	 btnPre1.setVisibility(View.INVISIBLE);
			        	 btnNext1.setVisibility(View.INVISIBLE);
					}
					return false;
				}
			});
			
			fr.addView(imageView,new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			arrListImage.add(imageView);
			Random r = new Random();
			int a = r.nextInt(SCREENH/2);
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(50+a, 50+a, 0, 0);
			imageView.setLayoutParams(lp);
		}
		fr.invalidate();
		
		isClicked =false;
	}

	/**
	 * get random list
	 * @return
	 */
	private ArrayList<Integer> randomList(){
		ArrayList<Integer> listi = new ArrayList<Integer>();
		ArrayList<Integer> listNew = new ArrayList<Integer>(); 
		for (int i = 0; i < 9; i++) {
			listi.add(i);
		}
		
		Random randomGenerator = new Random();
		for (int i = 0; i < 9; i++) {
			int a = randomGenerator.nextInt(9-i);
			
			listNew.add(listi.get(a));
			listi.remove(a);
		}
		return listNew;
	}
	
	/**
     * @param variableName - name of drawable, e.g R.drawable.<b>image</b>
     * @param - class of resource, e.g R.drawable, of R.raw
     * @return integer id of resource
     */
    public static int getResId(String imageName) {

        int resId = 0;
        try {
        	 @SuppressWarnings("rawtypes")
			Class res = R.drawable.class;
     	    Field field = res.getField(imageName);
     	   resId = field.getInt(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
       
        return resId;

    }
    
    /**
     * @param variableName - name of drawable, e.g R.drawable.<b>image</b>
     * @param - class of resource, e.g R.drawable, of R.raw
     * @return integer id of resource
     */
    public static int getRawId(String mName) {

        int resId = 0;
        try {
        	 @SuppressWarnings("rawtypes")
			Class raw = R.raw.class;
     	    Field field = raw.getField(mName);
     	   resId = field.getInt(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
       
        return resId;

    }
    
	/**
	 * get distance between Point a and b 
	 * @param a
	 * @param b
	 * @return
	 */
	private int distance(Point a, Point b){
		return (int) Math.sqrt(Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2));
	}
	
	private void CompleteAddPuzz(){
		if (g==1) {
			mp = MediaPlayer.create(getApplicationContext(), getRawId(puzzEntity.getAlphabet()));
			if (mp.isPlaying()) {
				mp.stop();
			}
		}
		
		numHasAddToView =0;
		imgComplete1.setVisibility(View.VISIBLE);
		imgComplete1.setImageResource(getResId(puzzEntity.getAlphabet()));
		
		animDown.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (g==1 && mp!=null) {
					mp.start();
				}
				 btnPre.setVisibility(View.VISIBLE);
	        	 btnNext.setVisibility(View.VISIBLE);
	        	 btnPre1.setVisibility(View.VISIBLE);
	        	 btnNext1.setVisibility(View.VISIBLE);
	        	 
	        	 btnPre.bringToFront();
	        	 btnNext.bringToFront();
	        	 btnPre1.bringToFront();
	        	 btnNext1.bringToFront();
	        	 
	        	 imgHome.setVisibility(View.VISIBLE);
	        	 imgHome1.setVisibility(View.GONE);
			}
		});
		imgComplete1.startAnimation(animDown);
		imgComplete1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (g==1) {
					if (mp!=null) {
						mp.start();
					}
				}
			}
		});
		for (int i = 0; i < 9; i++) {
			fr.removeView(fr.findViewById(i));
		}
		
	}
	
	 /* put this into your activity class */
	  private SensorManager mSensorManager;
	  private float mAccel; // acceleration apart from gravity
	  private float mAccelCurrent; // current acceleration including gravity
	  private float mAccelLast; // last acceleration including gravity
	  private long time = 0L;
	  private final SensorEventListener mSensorListener = new SensorEventListener() {

	    public void onSensorChanged(SensorEvent se) {
	      float x = se.values[0];
	      float y = se.values[1];
	      float z = se.values[2];
	      mAccelLast = mAccelCurrent;
	      mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
	      float delta = mAccelCurrent - mAccelLast;
	      mAccel = mAccel * 0.9f + delta; // perform low-cut filter
	      if (mAccel > 15) {
	    	  long curtime = System.currentTimeMillis();
			if ((curtime - time) > 2000) {
				initPuzzles();
//				Log.d("ThangTB", "mAccel:"+mAccel);
			}
	      }
	      
	      
	    }

	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }
	  };

	  @Override
	  protected void onResume() {
	    super.onResume();
	    if (h==1) {
	    	 mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		}
	  }

	  @Override
	  protected void onPause() {
		  if (mSensorManager!=null) {
			  mSensorManager.unregisterListener(mSensorListener);
		}
	    
	    super.onPause();
	  }
}