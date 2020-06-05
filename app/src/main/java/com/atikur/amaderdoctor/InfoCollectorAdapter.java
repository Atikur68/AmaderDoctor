package com.atikur.amaderdoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoCollectorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Doctor_List> docLists = new ArrayList();
    Context mcontext;
    View view;

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView infoCollectorName;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.infoCollectorName = (TextView) itemView.findViewById(R.id.infoCollectorName);
        }
    }


    public InfoCollectorAdapter(Context context, List<Doctor_List> docLists2) {
        this.mcontext = context;
        this.docLists = docLists2;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        this.view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_about_us, parent, false);
        return new InfoCollectorAdapter.UserViewHolder(this.view);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((InfoCollectorAdapter.UserViewHolder) holder).infoCollectorName.setText(((Doctor_List) this.docLists.get(position)).getInfoCollectorName());
    }

    public int getItemCount() {
        if (this.docLists == null) {
            return 0;
        }
        return this.docLists.size();
    }
}