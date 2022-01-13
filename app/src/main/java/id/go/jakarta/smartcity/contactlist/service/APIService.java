package id.go.jakarta.smartcity.contactlist.service;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIService {
  private static APIService instance;
  private RequestQueue requestQueue;
  private static Context ctx;

  private APIService(Context context) {
    ctx = context;
    requestQueue = getRequestQueue();
  }

  public static synchronized APIService getInstance(Context context) {
    if (instance == null) {
      instance = new APIService(context);
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
    }
    return requestQueue;
  }

  public <T> void addToRequestQueue(Request<T> req) {
    getRequestQueue().add(req);
  }
}

