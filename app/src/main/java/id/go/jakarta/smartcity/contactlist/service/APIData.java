package id.go.jakarta.smartcity.contactlist.service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIData {
  private RequestQueue requestQueue;
  private static APIData apiData;

  private APIData(Context context){
    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
  }

  public static synchronized APIData getInstance(Context context){
    if (apiData == null){
      apiData = new APIData(context);
    }
    return apiData;
  }

  public RequestQueue getRequestQueue(){
    return requestQueue;
  }
}
