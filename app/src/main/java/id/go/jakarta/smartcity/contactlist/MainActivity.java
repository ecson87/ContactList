package id.go.jakarta.smartcity.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.go.jakarta.smartcity.contactlist.adapter.ContactAdapter;
import id.go.jakarta.smartcity.contactlist.model.Contact;
import id.go.jakarta.smartcity.contactlist.model.Name;
import id.go.jakarta.smartcity.contactlist.model.Picture;
import id.go.jakarta.smartcity.contactlist.service.APIData;

public class MainActivity extends AppCompatActivity {

  private RecyclerView contactRV;
  private RequestQueue requestQueue;
  private ArrayList<Contact> contactModelArrayList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    contactRV = findViewById(R.id.idRVContact);
    fetchContacts();
  }

  private void fetchContacts() {
    requestQueue = APIData.getInstance(this).getRequestQueue();
    String url = "https://randomuser.me/api?results=5&exc=login,registered,i";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
      response -> {
        try {
          JSONArray jsonArrContact = response.getJSONArray("results");
          for (int i = 0 ; i < jsonArrContact.length() ; i ++) {
            try {
              JSONObject jsonObject = jsonArrContact.getJSONObject(i);
              JSONObject jsonObjectName = jsonObject.getJSONObject("name");
              JSONObject jsonObjectPicture = jsonObject.getJSONObject("picture");
              String firstName = jsonObjectName.getString("first");
              String lastName = jsonObjectName.getString("last");
              String thumbnail = jsonObjectPicture.getString("thumbnail");
              Contact contact = new Contact(new Name(firstName, lastName), new Picture(thumbnail));
              contactModelArrayList.add(contact);
            } catch (JSONException e) {
              e.printStackTrace();
            }
            ContactAdapter contactAdapter = new ContactAdapter(this, contactModelArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            contactRV.setLayoutManager(linearLayoutManager);
            contactRV.setAdapter(contactAdapter);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());
    requestQueue.add(jsonObjectRequest);
  }
}