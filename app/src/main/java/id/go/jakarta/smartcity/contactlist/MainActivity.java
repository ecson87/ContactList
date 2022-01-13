package id.go.jakarta.smartcity.contactlist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.go.jakarta.smartcity.contactlist.adapter.ContactAdapter;
import id.go.jakarta.smartcity.contactlist.model.Contact;
import id.go.jakarta.smartcity.contactlist.model.DataResult;
import id.go.jakarta.smartcity.contactlist.service.APIService;

public class MainActivity extends AppCompatActivity {

  private RecyclerView contactRV;
  private SwipeRefreshLayout contactSRL;
  private ArrayList<Contact> contactModelArrayList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    contactRV = findViewById(R.id.idRVContact);
    contactSRL = findViewById(R.id.idSRLContact);
    contactSRL.setOnRefreshListener(() -> {
      contactSRL.setRefreshing(false);
      fetchContacts();
    });
    fetchContacts();
  }

  private void fetchContacts() {
    String url = "https://randomuser.me/api?results=5&exc=login,registered,id,nat&nat=us&noinfo";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
    response -> {
      DataResult dataResult = new Gson().fromJson(response.toString(), DataResult.class);
      contactModelArrayList.clear();
      contactModelArrayList.addAll(dataResult.results);
      ContactAdapter contactAdapter = new ContactAdapter(this, contactModelArrayList);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
      contactRV.setLayoutManager(linearLayoutManager);
      contactRV.setAdapter(contactAdapter);
    }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());
    APIService.getInstance(this).addToRequestQueue(jsonObjectRequest);
  }
}