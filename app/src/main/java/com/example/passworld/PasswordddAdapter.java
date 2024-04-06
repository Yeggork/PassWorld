package com.example.passworld;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PasswordddAdapter extends RecyclerView.Adapter<PasswordddAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Passworddd> passworddds;
    private final OnDeleteClickListener onDeleteClickListener;
    private final OnCopyClickListener onCopyClickListener;

    public interface OnDeleteClickListener{
        void onDeleteClick(Passworddd password, int position);
    }
    public interface OnCopyClickListener{
        void onCopyClick(Passworddd password, int position);
    }

    public PasswordddAdapter(Context context,List<Passworddd> passworddds, OnDeleteClickListener onDeleteClickListener, OnCopyClickListener onCopyClickListener){
        this.passworddds = passworddds;
        inflater = LayoutInflater.from(context);
        this.onDeleteClickListener = onDeleteClickListener;
        this.onCopyClickListener = onCopyClickListener;
    }
    @NonNull
    @Override
    public PasswordddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_password,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordddAdapter.ViewHolder holder, int position) {
        Passworddd password = passworddds.get(position);
        holder.textpas.setText(password.getTextpassword());
        holder.delete.setOnClickListener(v -> {
            onDeleteClickListener.onDeleteClick(password,holder.getAdapterPosition());
            passworddds.remove(password);
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return passworddds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView delete, copy;
        TextView textpas;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textpas = itemView.findViewById(R.id.TextViewPasswordSavedInDataBase);
            delete = itemView.findViewById(R.id.imageViewDeletePassword);
            copy = itemView.findViewById(R.id.imageViewCopyPassword);
        }
    }
}
