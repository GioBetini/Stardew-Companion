package com.example.trabalhosemestral.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhosemestral.R;
import com.example.trabalhosemestral.controller.CropController;
import com.example.trabalhosemestral.model.Crop;
import com.example.trabalhosemestral.persistence.CropDao;

import java.sql.SQLException;
import java.util.List;


public class GrimoireFragment extends Fragment {

    private View view;

    private TextView tvGrimoire;
    private Button btnListGrimoire;
    private CropController cropCont;

    public GrimoireFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_grimoire, container, false);
        tvGrimoire = view.findViewById(R.id.tvGrimoireBody);
        btnListGrimoire = view.findViewById(R.id.btnListGrimoire);

        cropCont = new CropController(new CropDao(view.getContext()));

        btnListGrimoire.setOnClickListener(op -> acaoListar());
        return view;
    }

    private void acaoListar(){
        try {
            List<Crop> crops = cropCont.findAll();
            StringBuffer buffer = new StringBuffer();
            for (Crop c : crops){
                buffer.append(c.toString() +"\n");
            }
            tvGrimoire.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}