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
    holder.contactFirstNameTV.setText(model.getName().getFirst());
    holder.contactLastNameTV.setText(model.getName().getLast());
    Glide.with(context).load(model.getPicture().getThumbnail()).into(holder.contactIV);
    holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context , ContactDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("contact_picture" , model.getPicture().getLarge());
        bundle.putString("contact_name" , model.getName().getFirst() + " " + model.getName().getLast());
        intent.putExtras(bundle);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return contactArrayList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView contactIV;
    private TextView contactFirstNameTV, contactLastNameTV;
    private ConstraintLayout constraintLayout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      contactIV = itemView.findViewById(R.id.idIVContactImage);
      contactFirstNameTV = itemView.findViewById(R.id.idTVContactFirstName);
      contactLastNameTV = itemView.findViewById(R.id.idTVContactLastName);
      constraintLayout = itemView.findViewById(R.id.contactLayout);
    }
  }
}
