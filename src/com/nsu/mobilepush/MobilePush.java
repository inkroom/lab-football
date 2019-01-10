package com.nsu.mobilepush;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nsu.mobilepush.android.AndroidBroadcast;
import com.nsu.mobilepush.android.AndroidCustomizedcast;
import com.nsu.mobilepush.android.AndroidFilecast;
import com.nsu.mobilepush.android.AndroidGroupcast;
import com.nsu.mobilepush.android.AndroidUnicast;

public class MobilePush {
	private String appkey = null;
	private String appMasterSecret = null;
	private String timestamp = null;
	private PushClient client = new PushClient();
	
	public MobilePush(String key, String secret) {
		try {
			appkey = key;
			appMasterSecret = secret;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public void sendAndroidUnicast(String title,String text,String deviceToken) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken( deviceToken);
		unicast.setTicker( "Android unicast ticker");
		unicast.setTitle(  title);
		unicast.setText(   text);
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		unicast.setExtraField("test", "helloworld");
		client.send(unicast);
	}
	
	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		MobilePush demo = new MobilePush("58ca2ea2734be43a3f001290", "3hky10dj25lkuww8yhzwzowk8yrhxc6r");
		try {
			//demo.sendAndroidUnicast();
			/* TODO these methods are all available, just fill in some fields and do the test
			 * demo.sendAndroidCustomizedcastFile();
			 * demo.sendAndroidBroadcast();
			 * demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast();
			 * demo.sendAndroidFilecast();
			 * 
			 * demo.sendIOSBroadcast();
			 * demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast();
			 * demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
