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
        TextView district,division;
        TextView docName;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.docName = (TextView) itemView.findViewById(R.id.docName);
            this.designation = (TextView) itemView.findViewById(R.id.designation);
            this.district = (TextView) itemView.findViewById(R.id.district);
           // this.appid = (TextView) itemView.findViewById(R.id.appId);
            itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DoctorAdapter.this.mcontext, Doctor_Details.class);
                    intent.putExtra("appId", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getAppId());
                    intent.putExtra("docName", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getDocName());
                    intent.putExtra("degree", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getDegree());
                    intent.putExtra("designation", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getDesignation());
                    intent.putExtra("specialist_on", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getSpecialist_on());
                    intent.putExtra("mobile", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getMobile());
                    intent.putExtra("email", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getEmail());
                    intent.putExtra("chamber", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getChamber());
                    intent.putExtra("division", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getDivision());
                    intent.putExtra("district", ((Doctor_List) DoctorAdapter.this.docLists.get(UserViewHolder.this.getLayoutPosition())).getDistrict());
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
        ((UserViewHolder) holder).designation.setText(((Doctor_List) this.docLists.get(position)).getDegree());

        String speciality= docLists.get(position).getSpecialist_on();

        if(speciality.contains("অন্যান্য বিশেষজ্ঞ")){

            ((UserViewHolder) holder).district.setText("N/A");

        }else {
            ((UserViewHolder) holder).district.setText(((Doctor_List) this.docLists.get(position)).getSpecialist_on());
        }


       // ((UserViewHolder) holder).appid.setText(((Doctor_List) this.docLists.get(position)).getAppId());
    }

    public int getItemCount() {
        if (this.docLists == null) {
            return 0;
        }
        return this.docLists.size();
    }
}
