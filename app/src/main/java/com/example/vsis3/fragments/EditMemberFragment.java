package com.example.vsis3.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.vsis3.R;
import com.example.vsis3.dao.AppDataBase;
import com.example.vsis3.dao.TeamMemberDAO;
import com.example.vsis3.entity.TeamMemberEntity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditMemberFragment extends Fragment {
    private TextInputEditText name;
    private TextInputEditText role;
    private Button btnEditar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editmember, container, false);
        Bundle bundle = getArguments();
        String nome = bundle.getString("NOME");
        name = root.findViewById(R.id.txtNome);
        name.setText(nome);
        role = root.findViewById(R.id.txtRole);
        role.setText(bundle.getString("FUNCAO"));
        btnEditar = root.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        TeamMemberDAO teamMemberDAO = AppDataBase.getInstance(getContext().getApplicationContext()).createTeamMemberDAO();
                        TeamMemberEntity teamMemberEntity = teamMemberDAO.getTeamMemberByName(nome);

                        teamMemberEntity.setName(name.getText().toString());
                        teamMemberEntity.setRole(role.getText().toString());
                        teamMemberDAO.update(teamMemberEntity);


                        return teamMemberEntity.getId();
                    }
                }.execute();

                Snackbar snackbar = Snackbar.make(view, "Membro atualizado com sucesso!", Snackbar.LENGTH_LONG);
                snackbar.show();
                Navigation.findNavController(view).navigate(R.id.action_nav_edit_to_nav_list);
            }
        });
        return root;
    }
}
