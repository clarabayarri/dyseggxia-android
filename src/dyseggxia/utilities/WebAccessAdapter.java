package dyseggxia.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

public class WebAccessAdapter {

static WebAccessAdapter singleton;
	
	private WebAccessAdapter(){
		
	}
	
	public static WebAccessAdapter getInstance(){
		if(singleton == null){
			singleton = new WebAccessAdapter();
		}
		return singleton;
	}
	
	public String sendData(String url, JSONObject data){
		//preparem dades a enviar
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("data", data.toString()));
		    
		//http post
		InputStream is = null;
		try{
			HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(url);
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
		}
		catch(Exception e){
			e.printStackTrace();
			Log.e("log_tag", "Error in http connection "+e.toString());
		}
		String recievedData = "";
		//convert response to string
		try{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line + "\n");
		    }
		    is.close();
		 
		    recievedData=sb.toString();
		}
		catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		
		return recievedData;
	}
}
