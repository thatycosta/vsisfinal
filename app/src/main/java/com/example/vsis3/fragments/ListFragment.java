package com.example.vsis3.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vsis3.R;
import com.example.vsis3.adapter.Adapter;
import com.example.vsis3.dao.AppDataBase;
import com.example.vsis3.dao.TeamMemberDAO;
import com.example.vsis3.entity.TeamMemberEntity;

import java.util.List;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    public static ListFragment newInstance() {
        return new ListFragment();
    }
    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        new AsyncTask<Void,Void, List<TeamMemberEntity>>() {

            @Override
            protected List<TeamMemberEntity> doInBackground(Void... voids) {
                TeamMemberDAO tarefaDAO = AppDataBase.getInstance(getContext().getApplicationContext()).createTeamMemberDAO();
                return tarefaDAO.getAllMembers();
            }

            @Override
            protected void onPostExecute(List<TeamMemberEntity> teamMembers) {
                super.onPostExecute(teamMembers);
                getAll(teamMembers);
            }

        }.execute();

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);


        return root;
    }

    protected  void getAll(List<TeamMemberEntity> teamMembers){
        recyclerView.setAdapter(new Adapter(teamMembers));
    }
}
