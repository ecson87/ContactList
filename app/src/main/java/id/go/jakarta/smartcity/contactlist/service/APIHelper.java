package id.go.jakarta.smartcity.contactlist.service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIHelper {
  private RequestQueue requestQueue;
  private static APIHelper apiHelper;

  private APIHelper(Context context){
    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
  }

  public static synchronized APIHelper getInstance(Context context){
    if (apiHelper == null){
      apiHelper = new APIHelper(context);
    }
    return apiHelper;
  }

  public RequestQueue getRequestQueue(){
    return requestQueue;
  }
}
