package id.go.jakarta.smartcity.contactlist;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import id.go.jakarta.smartcity.contactlist.model.Contact;

public class ContactDetailActivity extends AppCompatActivity {

  private Contact contact;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_detail);
    String jsonContact = getIntent().getExtras().getString("contact");
    contact = new Gson().fromJson(jsonContact, Contact.class);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    ImageButton copyName = findViewById(R.id.contact_name_copy);
    ImageView contactPictureIV = findViewById(R.id.contact_picture);
    TextView contactNameTV = findViewById(R.id.contact_name);
    TextView dob = findViewById(R.id.idTVContactDOB);
    TextView gender = findViewById(R.id.idTVContactGender);
    TextView email = findViewById(R.id.idTVContactSurel);
    TextView phone = findViewById(R.id.idTVContactTelpon);
    TextView mobile = findViewById(R.id.idTVContactSeluler);
    TextView address = findViewById(R.id.idTVContactAlamat);
    TextView location = findViewById(R.id.idTVContactLokasi);
    Glide.with(this).load(contact.picture.large).into(contactPictureIV);
    contactNameTV.setText(contact.getLongName());
    copyName.setOnClickListener(v -> copyContactName(contact.getLongName()));
    dob.setText(contact.dob.date);
    gender.setText(contact.gender);
    email.setText(contact.email);
    email.setOnClickListener(v -> composeEmail(new String[]{contact.email}, "Halo"));
    phone.setText(contact.phone);
    phone.setOnClickListener(v -> dialPhoneNumber(contact.phone));
    mobile.setText(contact.cell);
    mobile.setOnClickListener(v -> dialPhoneNumber(contact.cell));
    address.setText(contact.getAddress());
    location.setText(contact.getPosition());
    location.setOnClickListener(v -> showMap(Uri.parse(contact.getMapLocationURI())));
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
        sendIntent.putExtra(Intent.EXTRA_TEXT, contact.getShareContent());
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

  public void copyContactName(String contactName) {
    ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText("Contact Name", contactName);
    clipboard.setPrimaryClip(clip);
    Toast.makeText(getApplicationContext(), "Nama telah tersalin", Toast.LENGTH_SHORT).show();
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