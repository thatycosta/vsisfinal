package com.example.vsis3.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vsis3.R;

public class ImplicityFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "http://www.poa.ifrs.edu.br"));
        startActivity(intent);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}
