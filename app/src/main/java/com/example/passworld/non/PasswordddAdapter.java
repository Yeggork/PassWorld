package com.example.passworld.non;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passworld.R;

import java.util.List;

public class PasswordddAdapter extends RecyclerView.Adapter<PasswordddAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Passworddd> passworddds;
    private final OnDeleteClickListener onDeleteClickListener;
    private final OnCopyClickListener onCopyClickListener;
    private final OnPlayClickListener onPlayClickListener;

    public interface OnCopyClickListener {
        void onCopyClick(Passworddd password, int position);
    }
    public interface OnDeleteClickListener{
        void onDeleteClick(Passworddd password, int position);
    }
    public interface OnPlayClickListener{
        void onPlayClick(Passworddd password, int position);
    }

    public PasswordddAdapter(Context context,
                             List<Passworddd> passworddds,
                             OnDeleteClickListener onDeleteClickListener,
                             OnCopyClickListener onCopyClickListener,
                             OnPlayClickListener onPlayClickListener){
        this.passworddds = passworddds;
        inflater = LayoutInflater.from(context);
        this.onDeleteClickListener = onDeleteClickListener;
        this.onCopyClickListener = onCopyClickListener;
        this.onPlayClickListener = onPlayClickListener;
    }
    @NonNull
    @Override
    public PasswordddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_password,parent,false);
        return new ViewHolder(view);
    }

    public void newList(List<Passworddd> password){
        passworddds = password;
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordddAdapter.ViewHolder holder, int position) {
        Passworddd password = passworddds.get(position);
        holder.idpas.setText(password.getIdpas());
        holder.textpas.setText(password.getTextpassword());
        holder.delete.setOnClickListener(v -> {
            onDeleteClickListener.onDeleteClick(password,holder.getAdapterPosition());
            passworddds.remove(password);
            notifyItemRemoved(holder.getAdapterPosition());
        });
        holder.copy.setOnClickListener(v -> {
            onCopyClickListener.onCopyClick(password,position);
        });
        holder.play.setOnClickListener(v -> {
            onPlayClickListener.onPlayClick(password,position);
        });
    }

    @Override
    public int getItemCount() {
        return passworddds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView delete, copy,play;
        TextView textpas, idpas;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            idpas = itemView.findViewById(R.id.TextViewNumberPassword);
            textpas = itemView.findViewById(R.id.TextViewPasswordSavedInDataBase);
            delete = itemView.findViewById(R.id.imageViewDeletePassword);
            copy = itemView.findViewById(R.id.imageViewCopyPassword);
            play = itemView.findViewById(R.id.imageViewContinuePlay);
        }
    }
}
