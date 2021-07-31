package com.example.vsis3.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.vsis3.R;
import com.example.vsis3.dao.AppDataBase;
import com.example.vsis3.dao.TeamMemberDAO;
import com.example.vsis3.entity.TeamMemberEntity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class AddMemberFragment extends Fragment {
    private TextInputEditText name;
    private TextInputEditText role;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addmember, container, false);
        name = root.findViewById(R.id.name);
        role = root.findViewById(R.id.role);
        btnAdd = root.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        TeamMemberDAO teamMemberDAO = AppDataBase.getInstance(getContext().getApplicationContext()).createTeamMemberDAO();
                        TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
                        teamMemberEntity.setName(name.getText().toString());
                        teamMemberEntity.setRole(role.getText().toString());
                        teamMemberDAO.insert(teamMemberEntity);

                        return teamMemberEntity.getId();
                    }
                }.execute();

                Snackbar snackbar = Snackbar.make(view, "Membro adicionado com sucesso!", Snackbar.LENGTH_LONG);
                snackbar.show();
                Navigation.findNavController(view).navigate(R.id.action_nav_add_to_nav_list);
            }
        });
        return root;
    }
}
