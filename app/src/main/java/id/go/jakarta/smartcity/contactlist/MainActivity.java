package id.go.jakarta.smartcity.contactlist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.go.jakarta.smartcity.contactlist.adapter.ContactAdapter;
import id.go.jakarta.smartcity.contactlist.model.Contact;
import id.go.jakarta.smartcity.contactlist.model.DataResult;
import id.go.jakarta.smartcity.contactlist.service.APIHelper;

public class MainActivity extends AppCompatActivity {

  private SwipeRefreshLayout contactSRL;
  private RecyclerView contactRV;
  private RequestQueue requestQueue;
  private ArrayList<Contact> contactModelArrayList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    contactSRL = findViewById(R.id.idSRLContact);
    contactRV = findViewById(R.id.idRVContact);
    fetchContacts();
    contactSRL.setOnRefreshListener(() -> {
      contactSRL.setRefreshing(false);
      fetchContacts();
    });
  }

  private void fetchContacts() {
    contactModelArrayList.clear();
    requestQueue = APIHelper.getInstance(this).getRequestQueue();
    String url = "https://randomuser.me/api?results=5&exc=login,registered,i";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
      response -> {
        DataResult dataResult = new Gson().fromJson(response.toString(), DataResult.class);
        contactModelArrayList.addAll(dataResult.results);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactRV.setLayoutManager(linearLayoutManager);
        contactRV.setAdapter(contactAdapter);
      }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());
    requestQueue.add(jsonObjectRequest);
  }
}