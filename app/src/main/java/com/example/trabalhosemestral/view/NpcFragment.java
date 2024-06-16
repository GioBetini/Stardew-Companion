package com.example.trabalhosemestral.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhosemestral.R;
import com.example.trabalhosemestral.controller.NpcController;
import com.example.trabalhosemestral.model.Npc;
import com.example.trabalhosemestral.persistence.NpcDao;

import java.sql.SQLException;


public class NpcFragment extends Fragment {
    private View view;
    private EditText etIdNpc, etNameNpc;
    private RadioButton rbYesNpc, rbNoNpc;
    private Button btnInsertNpc, btnDeleteNpc, btnUpdateNpc, btnReadNpc;

    private NpcController npcCont;

    public NpcFragment() {
       super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_npc, container, false);
        etIdNpc = view.findViewById(R.id.etIdNpc);
        etNameNpc = view.findViewById(R.id.etNameNpc);
        rbYesNpc = view.findViewById(R.id.rbSimNpc);
        rbNoNpc = view.findViewById(R.id.rbNaoNpc);
        btnInsertNpc = view.findViewById(R.id.btnInserirNpc);
        btnDeleteNpc = view.findViewById(R.id.btnDeletarNpc);
        btnUpdateNpc = view.findViewById(R.id.btnAtualizarNpc);
        btnReadNpc = view.findViewById(R.id.btnConsultarNpc);


        npcCont = new NpcController(new NpcDao(view.getContext()));

        btnInsertNpc.setOnClickListener(op -> acaoInserir());
        btnDeleteNpc.setOnClickListener(op -> acaoExcluir());
        btnUpdateNpc.setOnClickListener(op -> acaoModificar());
        btnReadNpc.setOnClickListener(op -> acaoBuscar());

        return view;
    }

    private void acaoInserir() {
        try{
            Npc npc = montaNpc();
            npcCont.insert(npc);
            Toast.makeText(view.getContext(), "NPC cadastrado com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        try{
            Npc npc = montaBusca();
            npcCont.delete(npc);
            Toast.makeText(view.getContext(), "NPC removido com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoModificar() {
        try{
            Npc npc = montaNpc();
            npcCont.update(npc);
            Toast.makeText(view.getContext(), "NPC atualizado com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        try{
            Npc npc = montaBusca();
            npc = npcCont.findOne(npc);
            if (npc.getName() != null){
                preencheCampos(npc);
            } else{
                Toast.makeText(view.getContext(), "NPC não encontrado.",
                        Toast.LENGTH_LONG).show();
                limpaCampos();
            }
            Toast.makeText(view.getContext(), "NPC Encontrado!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private Npc montaNpc(){
        Npc n = new Npc();
        n.setId(Integer.parseInt((etIdNpc.getText().toString())));
        n.setName(etNameNpc.getText().toString());
        if (rbYesNpc.isChecked()){
            n.setSolteiro(true);
        } else {
            n.setSolteiro(false);
        }
        return n;
    }

    private Npc montaBusca(){
        Npc n = new Npc();
        n.setId(Integer.parseInt((etIdNpc.getText().toString())));
        return n;
    }

    private void preencheCampos(Npc n){
        if (n.isSolteiro()){
            rbYesNpc.setChecked(true);
        } else {
            rbNoNpc.setChecked(true);
        }
        etNameNpc.setText(String.valueOf(n.getName()));
        etIdNpc.setText(String.valueOf(n.getId()));
    }

    private void limpaCampos(){
        etIdNpc.setText("");
        etNameNpc.setText("");
    }
}
