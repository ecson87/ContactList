package id.go.jakarta.smartcity.contactlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.go.jakarta.smartcity.contactlist.ContactDetailActivity;
import id.go.jakarta.smartcity.contactlist.R;
import id.go.jakarta.smartcity.contactlist.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

  private Context context;
  private ArrayList<Contact> contactArrayList;

  public ContactAdapter(Context context, ArrayList<Contact> contactArrayList) {
    this.context = context;
    this.contactArrayList = contactArrayList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Contact model = contactArrayList.get(position);
    holder.contactNameTV.setText(model.getLongName());
    holder.contactEmailTV.setText(model.email);
    holder.contactAddressTV.setText(model.getAddress());
    Glide.with(context).load(model.picture.thumbnail).into(holder.contactIV);
    holder.constraintLayout.setOnClickListener(v -> {
      Intent intent = new Intent(context , ContactDetailActivity.class);
      Bundle bundle = new Bundle();
      bundle.putString("contact" , new Gson().toJson(model));
      intent.putExtras(bundle);
      context.startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return contactArrayList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView contactIV;
    private TextView contactNameTV, contactEmailTV, contactAddressTV;
    private ConstraintLayout constraintLayout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      contactIV = itemView.findViewById(R.id.idIVContactImage);
      contactNameTV = itemView.findViewById(R.id.idTVContactName);
      contactEmailTV = itemView.findViewById(R.id.idTVContactEmail);
      contactAddressTV = itemView.findViewById(R.id.idTVContactAddress);
      constraintLayout = itemView.findViewById(R.id.contactLayout);
    }
  }
}
