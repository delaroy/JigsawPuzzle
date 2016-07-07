/**
 * 
 */
package com.delaroystudios.jigsaw;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.delaroystudios.jigsaw.R;

/**
 * @author ThangTB
 *
 */
public class FisrtScreen extends Activity implements OnClickListener{
	ImageButton btnHelp;
	Button btnAbout;
	Button btnAboutBack;
	Button btnAboutMore;
	Button btnAboutWWW;
	Button btnSetting;
	Button btnSettingBack;
	Button btnSettingMore;
	Button btnSettingWWW;
	Button btnPlay;
	RelativeLayout rlContentAbout;
	RelativeLayout rlContentSetting;
	
	Animation animDown ;
	Animation animUp ;
	
	int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
	Button btnSetting_1;
	Button btnSetting_2;
	Button btnSetting_3;
	Button btnSetting_4;
	Button btnSetting_5;
	Button btnSetting_6;
	Button btnSetting_7;
	Button btnSetting_8;
	
	boolean isClicked = false;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.fisrt_screen_activity);
		CustomSharedPreferences.init(getApplicationContext());
		
		btnHelp = (ImageButton)findViewById(R.id.helpBtn);
		btnAbout = (Button)findViewById(R.id.btnAbout);
		btnAboutBack = (Button)findViewById(R.id.btnAboutBack);
		btnAboutMore = (Button)findViewById(R.id.btnAboutMore);
		btnAboutWWW = (Button)findViewById(R.id.btnAboutWWW);
		
		btnSetting = (Button)findViewById(R.id.btnSettings);
		btnSettingBack = (Button)findViewById(R.id.btnSettingsBack);
		btnSettingMore = (Button)findViewById(R.id.btnSettingsMore);
		btnSettingWWW = (Button)findViewById(R.id.btnSettingsWWW);
		
		btnPlay = (Button)findViewById(R.id.btnPlay);
		rlContentAbout = (RelativeLayout)findViewById(R.id.ltAbout);
		rlContentSetting = (RelativeLayout)findViewById(R.id.ltSettings);
		
		animDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
		animUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);
		
		btnSetting_1 = (Button)findViewById(R.id.btn1);
		btnSetting_2 = (Button)findViewById(R.id.btn2);
		btnSetting_3 = (Button)findViewById(R.id.btn3);
		btnSetting_4 = (Button)findViewById(R.id.btn4);
		btnSetting_5 = (Button)findViewById(R.id.btn5);
		btnSetting_6 = (Button)findViewById(R.id.btn6);
		btnSetting_7 = (Button)findViewById(R.id.btn7);
		btnSetting_8 = (Button)findViewById(R.id.btn8);
		
		btnHelp.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		btnAboutBack.setOnClickListener(this);
		btnAboutMore.setOnClickListener(this);
		btnAboutWWW.setOnClickListener(this);
		btnSetting.setOnClickListener(this);
		btnSettingBack.setOnClickListener(this);
		btnSettingMore.setOnClickListener(this);
		btnSettingWWW.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		
		btnSetting_1.setOnClickListener(this);
		btnSetting_2.setOnClickListener(this);
		btnSetting_3.setOnClickListener(this);
		btnSetting_4.setOnClickListener(this);
		btnSetting_5.setOnClickListener(this);
		btnSetting_6.setOnClickListener(this);
		btnSetting_7.setOnClickListener(this);
		btnSetting_8.setOnClickListener(this);
		
		a = CustomSharedPreferences.getPreferences(Constant.SP_DISPLAY_GRID, 1);
		b = CustomSharedPreferences.getPreferences(Constant.SP_EASY_PIE, 1);
		c = CustomSharedPreferences.getPreferences(Constant.SP_ENABLE_SOUND, 1);
		d = CustomSharedPreferences.getPreferences(Constant.SP_DISPLAY_WORDs, 1);
		e = CustomSharedPreferences.getPreferences(Constant.SP_VIBRATE_DRAG, 1);
		f = CustomSharedPreferences.getPreferences(Constant.SP_VIBRATE_PIE, 1);
		g = CustomSharedPreferences.getPreferences(Constant.SP_ENABLE_WORD_SOUND, 1);
		h = CustomSharedPreferences.getPreferences(Constant.SP_RESTART_PUZZLE, 1);
		//1
		if (a==0) {
			btnSetting_1.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_1.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//2
		if (b==0) {
			btnSetting_2.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_2.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//3
		if (c==0) {
			btnSetting_3.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_3.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//4
		if (d==0) {
			btnSetting_4.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_4.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//5
		if (e==0) {
			btnSetting_5.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_5.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//6
		if (f==0) {
			btnSetting_6.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_6.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//7
		if (g==0) {
			btnSetting_7.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_7.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		//8
		if (h==0) {
			btnSetting_8.setBackgroundResource(R.drawable.settings_button_star_off);
		}else{
			btnSetting_8.setBackgroundResource(R.drawable.settings_button_star_on);
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		isClicked = false;
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (isClicked) {
			return;
		}else{
			isClicked = true;
		}
		
		rlContentAbout.clearAnimation();
		rlContentSetting.clearAnimation();
		if (v==btnAbout ) {
			
			rlContentAbout.setVisibility(View.VISIBLE);
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
					isClicked = false;
				}
			});
			rlContentAbout.startAnimation(animDown);
		}else if (v==btnAboutBack ) {
			animUp.setAnimationListener(new AnimationListener() {
				
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
					rlContentAbout.setVisibility(View.GONE);
					isClicked = false;
				}
			});
			rlContentAbout.startAnimation(animUp);
		}else if (v==btnAboutMore ) {
			Uri uri = Uri.parse(FisrtScreen.this.getString(R.string.uri_more_app));
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
				startActivity(goToMarket);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(FisrtScreen.this.getApplicationContext(),"Couldn't launch the market",
						Toast.LENGTH_LONG).show();
			}finally{
				isClicked = false;
			}
		}else if (v==btnAboutWWW ) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(FisrtScreen.this.getString(R.string.uri_app_link)));
			startActivity(i);
			isClicked = false;
		}else if (v==btnSetting ) {
			rlContentSetting.setVisibility(View.VISIBLE);
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
					isClicked = false;
				}
			});
			rlContentSetting.startAnimation(animDown);
		}else if (v==btnSettingBack ) {
			animUp.setAnimationListener(new AnimationListener() {
				
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
					rlContentSetting.setVisibility(View.GONE);
					isClicked = false;
				}
			});
			rlContentSetting.startAnimation(animUp);
		}else if (v==btnSettingMore ) {
			
			Uri uri = Uri.parse(FisrtScreen.this.getString(R.string.uri_more_app));
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
				startActivity(goToMarket);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(FisrtScreen.this.getApplicationContext(),"Couldn't launch the market",
						Toast.LENGTH_LONG).show();
			}finally{
				isClicked = false;
			}
	
		}else if (v==btnSettingWWW ) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(FisrtScreen.this.getString(R.string.uri_app_link)));
			startActivity(i);
			isClicked = false;
		}else if (v==btnPlay ) {
			Intent i = new Intent( getApplicationContext(), MainActivity.class);
			startActivity(i);
			isClicked = false;
		}else if (v==btnSetting_1 ) {
			if (a==0) {
				a=1;
				CustomSharedPreferences.setPreferences(Constant.SP_DISPLAY_GRID, a);
				btnSetting_1.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				a=0;
				CustomSharedPreferences.setPreferences(Constant.SP_DISPLAY_GRID, a);
				btnSetting_1.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_2 ) {
			if (b==0) {
				b=1;
				CustomSharedPreferences.setPreferences(Constant.SP_EASY_PIE, b);
				btnSetting_2.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				b=0;
				CustomSharedPreferences.setPreferences(Constant.SP_EASY_PIE, b);
				btnSetting_2.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_3 ) {
			if (c==0) {
				c=1;
				CustomSharedPreferences.setPreferences(Constant.SP_ENABLE_SOUND, c);
				btnSetting_3.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				c=0;
				CustomSharedPreferences.setPreferences(Constant.SP_ENABLE_SOUND, c);
				btnSetting_3.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_4 ) {
			if (d==0) {
				d=1;
				CustomSharedPreferences.setPreferences(Constant.SP_DISPLAY_WORDs, d);
				btnSetting_4.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				d=0;
				CustomSharedPreferences.setPreferences(Constant.SP_DISPLAY_WORDs, d);
				btnSetting_4.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_5 ) {
			if (e==0) {
				e=1;
				CustomSharedPreferences.setPreferences(Constant.SP_VIBRATE_DRAG, e);
				btnSetting_5.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				e=0;
				CustomSharedPreferences.setPreferences(Constant.SP_VIBRATE_DRAG, e);
				btnSetting_5.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_6 ) {
			if (f==0) {
				f=1;
				CustomSharedPreferences.setPreferences(Constant.SP_VIBRATE_PIE, f);
				btnSetting_6.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				f=0;
				CustomSharedPreferences.setPreferences(Constant.SP_VIBRATE_PIE, f);
				btnSetting_6.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_7 ) {
			if (g==0) {
				g=1;
				CustomSharedPreferences.setPreferences(Constant.SP_ENABLE_WORD_SOUND, g);
				btnSetting_7.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				g=0;
				CustomSharedPreferences.setPreferences(Constant.SP_ENABLE_WORD_SOUND, g);
				btnSetting_7.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnSetting_8 ) {
			if (h==0) {
				h=1;
				CustomSharedPreferences.setPreferences(Constant.SP_RESTART_PUZZLE, h);
				btnSetting_8.setBackgroundResource(R.drawable.settings_button_star_on);
			}else{
				h=0;
				CustomSharedPreferences.setPreferences(Constant.SP_RESTART_PUZZLE, h);
				btnSetting_8.setBackgroundResource(R.drawable.settings_button_star_off);
			}
			isClicked = false;
		}else if (v==btnHelp ) {
			Uri uri = Uri.parse(FisrtScreen.this.getString(R.string.uri_app_rate));
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
				startActivity(goToMarket);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(FisrtScreen.this.getApplicationContext(),"Couldn't launch the market",
						Toast.LENGTH_LONG).show();
			}finally{
				isClicked = false;
			}
		}
	}
	
	
}
