package id.go.jakarta.smartcity.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import id.go.jakarta.smartcity.contactlist.model.Contact;

public class ContactDetailActivity extends AppCompatActivity {

  private Contact contact;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_detail);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    ImageButton copyName = findViewById(R.id.contact_name_copy);
    ImageView contactPictureIV = findViewById(R.id.contact_picture);
    TextView contactNameTV = findViewById(R.id.contact_name);
    TextView dob = findViewById(R.id.idTVContactDOB);
    TextView gender = findViewById(R.id.idTVContactGender);
    TextView surel = findViewById(R.id.idTVContactSurel);
    TextView telpon = findViewById(R.id.idTVContactTelpon);
    TextView seluler = findViewById(R.id.idTVContactSeluler);
    TextView alamat = findViewById(R.id.idTVContactAlamat);
    TextView lokasi = findViewById(R.id.idTVContactLokasi);
    String jsonContact = getIntent().getExtras().getString("contact");
    contact = new Gson().fromJson(jsonContact, Contact.class);
    String contactName = contact.name.first + " " + contact.name.last;
    Glide.with(this).load(contact.picture.large).into(contactPictureIV);
    contactNameTV.setText(contactName);
    dob.setText("Lahir : " + contact.dob.date);
    gender.setText("Jenis Kelamin : " + contact.gender);
    surel.setText("Surel : " + contact.email);
    surel.setOnClickListener(v -> composeEmail(new String[]{contact.email}, "Halo"));
    telpon.setText("Telpon : " + contact.phone);
    telpon.setOnClickListener(v -> dialPhoneNumber(contact.phone));
    seluler.setText("Seluler : " + contact.cell);
    seluler.setOnClickListener(v -> dialPhoneNumber(contact.cell));
    alamat.setText("Alamat : " + contact.location.street.name + " " + contact.location.street.number + ", " + contact.location.city + ", " + contact.location.state + ", " + contact.location.postcode);
    lokasi.setText("Lokasi : " + contact.location.coordinates.latitude + ", " + contact.location.coordinates.longitude);
    lokasi.setOnClickListener(v -> showMap(Uri.parse("geo:0,0?q=" + contact.location.coordinates.latitude + "," + contact.location.coordinates.longitude + "(Treasure)")));
    copyName.setOnClickListener(v -> {
      ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
      ClipData clip = ClipData.newPlainText("Contact Name", contactName);
      clipboard.setPrimaryClip(clip);
      Toast.makeText(getApplicationContext(), "Nama telah tersalin", Toast.LENGTH_SHORT).show();
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_share:
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, contact.name.first + " " + contact.name.last + ", " + contact.email + ", " + contact.cell);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  public void dialPhoneNumber(String phoneNumber) {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + phoneNumber));
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  public void composeEmail(String[] addresses, String subject) {
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, addresses);
    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  public void showMap(Uri geoLocation) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(geoLocation);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }
}