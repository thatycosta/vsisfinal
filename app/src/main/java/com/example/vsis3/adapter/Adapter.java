package com.example.vsis3.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vsis3.R;
import com.example.vsis3.dao.AppDataBase;
import com.example.vsis3.dao.TeamMemberDAO;
import com.example.vsis3.entity.TeamMemberEntity;
import com.example.vsis3.fragments.ListFragment;
import com.example.vsis3.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<TeamMemberEntity> teamMembers = new ArrayList<>();
    Context context;

    public Adapter(List<TeamMemberEntity> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, final int position) {
        TeamMemberEntity teamMemberEntity = teamMembers.get(position);
        viewHolder.name.setText(teamMemberEntity.getName());
        viewHolder.role.setText(teamMemberEntity.getRole());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("NOME", teamMembers.get(position).getName());
        bundle.putString("FUNCAO", teamMembers.get(position).getRole());
        viewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editPessoa, bundle));
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando membro")
                .setMessage("Tem certeza que deseja deletar esse membro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                TeamMemberDAO teamMemberDAO = AppDataBase.getInstance(context.getApplicationContext()).createTeamMemberDAO();
                                List<TeamMemberEntity> teamMemberEntities = teamMemberDAO.getAllMembers();
                                teamMemberDAO.delete(teamMemberEntities.get(position));
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                updateList(position);
                            }
                        }.execute();

//                        teamMembers.remove(position);
//                        notifyItemRemoved(position);

                    }}).setNegativeButton("Não", null).show();
    }

    public void updateList(int position){
        String mensagem = "Membro excluído com sucesso!";
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
        this.teamMembers.remove(position);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView role;
        Button btnDelete;
        Button btnEdit;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            role = itemView.findViewById(R.id.textViewrole);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit= itemView.findViewById(R.id.btnEdit);
        }
    }
}
