/**
 * 
 */
package com.HS.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author hemasundar
 *
 */
public class ApacheHttpRequest {
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * @param args
     */
    public static void main(String[] args) {
	ApacheHttpRequest request = new ApacheHttpRequest();
	try {
	    request.sendGet("http://www.google.com/search?q=httpClient");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	try {
	    request.sendPost("https://selfsolve.apple.com/wcResults.do", "client", "Logs/client.log");
	} catch (UnsupportedOperationException | IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @throws IOException
     * @throws ClientProtocolException
     * 
     */
    public void sendGet(String url) throws ClientProtocolException, IOException {
	// String url = "http://www.google.com/search?q=httpClient";

	HttpClient client = HttpClientBuilder.create().build();
	HttpGet request = new HttpGet(url);

	// add request header
	request.addHeader("User-Agent", USER_AGENT);
	HttpResponse response = client.execute(request);

	System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

	// StringBuffer result = new StringBuffer();
	String result = null;
	String line = "";
	while ((line = rd.readLine()) != null) {
	    // result.append(line);
	    result = result.concat(line);
	}

	System.out.println(result);
	new UtilityMethods().writeFile(new File("Logs/Sample.log"), result);

    }

    /**
     * @throws IOException
     * @throws UnsupportedOperationException
     * 
     */
    public void sendPost(String url, String logType, String outPutFile)
	    throws UnsupportedOperationException, IOException {
	// String url = "https://selfsolve.apple.com/wcResults.do";

	HttpClient client = HttpClientBuilder.create().build();
	HttpPost post = new HttpPost(url);

	// add header
	post.setHeader("User-Agent", USER_AGENT);

	/*
	 * List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	 * urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
	 * urlParameters.add(new BasicNameValuePair("cn", ""));
	 * urlParameters.add(new BasicNameValuePair("locale", ""));
	 * urlParameters.add(new BasicNameValuePair("caller", ""));
	 * urlParameters.add(new BasicNameValuePair("num", "12345"));
	 * 
	 * post.setEntity(new UrlEncodedFormEntity(urlParameters));
	 */

	JSONObject json = new JSONObject();
	try {
	    json.put("type", logType);
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	String message = json.toString();

	StringEntity stringEntity = null;
	try {
	    stringEntity = new StringEntity(message);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	post.setEntity(stringEntity);
	post.setHeader("Content-type", "application/json");

	HttpResponse response = client.execute(post);

	System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

	// StringBuffer result = new StringBuffer();
	String result = "";
	String line = "";
	while ((line = rd.readLine()) != null) {
	    // result.append(line);
	    try {
		result = result.concat(line);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	System.out.println(result);
	new UtilityMethods().writeFile(new File(outPutFile), result);
    }

}
