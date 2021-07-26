package com.example.vsis3.fragments;

import android.content.DialogInterface;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditMemberFragment extends Fragment {
    private TextInputEditText txtNome;
    private TextInputEditText txtDtNasc;
    private TextInputEditText txtEnd;
    private Button btnEditar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editmember, container, false);
        Bundle bundle = getArguments();
        String nome = bundle.getString("NOME");
        txtNome = root.findViewById(R.id.txtNome);
        txtNome.setText(nome);
        txtDtNasc = root.findViewById(R.id.txtDtNasc);
        txtDtNasc.setText(bundle.getString("DTNASC"));
        btnEditar = root.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    public void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Deletando produto")
                .setMessage("Tem certeza que deseja editar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(getView(), "item editado!!!", Snackbar.LENGTH_LONG).show();
                        Navigation.createNavigateOnClickListener(R.id.nav_list, null);
                    }
                }).setNegativeButton("Não", null).show();
    }
}
