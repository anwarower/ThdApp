package com.example.thdapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class NetworkingThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.d("Networking Thread: ", "Working!");
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("email", "user@gmail.com"));
        nameValuePair.add(new BasicNameValuePair("message",
                "Hi, trying Android HTTP post!"));
        

		String queryResponse = getPage("http://www.example.com/login", nameValuePair);
		Log.d("response", queryResponse);
	}

	private String getPage(String url, List <NameValuePair> params) {
    	String str = "***";

        try
    	{
    		HttpClient hc = new DefaultHttpClient();
    		HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(params));
          
            
    		HttpResponse rp = hc.execute(post);

    		if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
    		{
    			str = EntityUtils.toString(rp.getEntity());
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}  
    	
    	return str;
    }
}
