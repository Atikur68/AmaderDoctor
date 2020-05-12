package com.atikur.amaderdoctor;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView.Adapter;
//import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Doctor_List> docLists = new ArrayList();
    Context mcontext;
    View view;

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView appid;
        TextView designation;
        TextView district;
        TextView docName;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.docName = (TextView) itemView.findViewById(R.id.docName);
            this.designation = (TextView) itemView.findViewById(R.id.designation);
            this.district = (TextView) itemView.findViewById(R.id.district);
            this.appid = (TextView) itemView.findViewById(R.id.appId);
            itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DoctorAdapter.this.mcontext, Doctor_Details.class);
                    intent.putExtra("appId", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getAppId());
                    DoctorAdapter.this.mcontext.startActivity(intent);
                }
            });
        }
    }

    public DoctorAdapter(Context context, List<Doctor_List> docLists2) {
        this.mcontext = context;
        this.docLists = docLists2;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        this.view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design, parent, false);
        return new UserViewHolder(this.view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserViewHolder) holder).docName.setText(((Doctor_List) this.docLists.get(position)).getDocName());
        ((UserViewHolder) holder).designation.setText(((Doctor_List) this.docLists.get(position)).getDesignation());
        ((UserViewHolder) holder).district.setText(((Doctor_List) this.docLists.get(position)).getDistrict());
        ((UserViewHolder) holder).appid.setText(((Doctor_List) this.docLists.get(position)).getAppId());
    }

    public int getItemCount() {
        if (this.docLists == null) {
            return 0;
        }
        return this.docLists.size();
    }
}
