package com.example.trabalhosemestral.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.trabalhosemestral.R;
import com.example.trabalhosemestral.controller.CropController;
import com.example.trabalhosemestral.model.Crop;
import com.example.trabalhosemestral.model.Fruit;
import com.example.trabalhosemestral.model.Vegetable;
import com.example.trabalhosemestral.persistence.CropDao;

import java.sql.SQLException;
import java.util.List;


public class CropFragment extends Fragment {

    private View view;
    private EditText etNameCrop, etIdCrop, etDaysCrop, etPriceCrop;
    private Button btnInsertCrop, btnDeleteCrop, btnUpdateCrop, btnReadCrop;
    private RadioButton rbFruitCrop, rbVegetableCrop, rbSpgCrop, rbSumCrop, rbAutCrop, rbWinCrop;
    private CropController cropCont;

    public CropFragment() {
        super();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crop, container, false);
        etNameCrop = view.findViewById(R.id.etNameCrop);
        etIdCrop = view.findViewById(R.id.etIdCrop);
        etDaysCrop = view.findViewById(R.id.etDaysCrop);
        etPriceCrop = view.findViewById(R.id.etPrice);
        btnInsertCrop = view.findViewById(R.id.btnInserirCrop);
        btnDeleteCrop = view.findViewById(R.id.btnDeletarCrop);
        btnUpdateCrop = view.findViewById(R.id.btnAtualizarCrop);
        btnReadCrop = view.findViewById(R.id.btnConsultarCrop);
        rbFruitCrop = view.findViewById(R.id.rbFruit);
        rbVegetableCrop = view.findViewById(R.id.rbVeg);
        rbSpgCrop = view.findViewById(R.id.rbSpgCrop);
        rbSumCrop = view.findViewById(R.id.rbSumCrop);
        rbAutCrop = view.findViewById(R.id.rbAutCrop);
        rbWinCrop = view.findViewById(R.id.rbWinCrop);

        cropCont = new CropController(new CropDao(view.getContext()));

        btnInsertCrop.setOnClickListener(op -> acaoInserir());
        btnDeleteCrop.setOnClickListener(op -> acaoExcluir());
        btnUpdateCrop.setOnClickListener(op -> acaoModificar());
        btnReadCrop.setOnClickListener(op -> acaoBuscar());

        return view;
    }
    private void acaoInserir(){
        try {
            Crop crop = montaCrop();
            cropCont.insert(crop);
            Toast.makeText(view.getContext(), "Lavoura cadastrada com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();

    }
    private void acaoModificar(){
        try {
            Crop crop = montaCrop();
            cropCont.update(crop);
            Toast.makeText(view.getContext(), "Lavoura atualizada com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }
    private void acaoExcluir(){
        try {
            Crop crop = montaBuscaCrop();
            cropCont.delete(crop);
            Toast.makeText(view.getContext(), "Lavoura removida com sucesso!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }
    private void acaoBuscar(){
        try {
            Crop crop = montaBuscaCrop();
            crop = cropCont.findOne(crop);
            if(crop.getName() != null){
                preencheCampos(crop);
            } else {
                Toast.makeText(view.getContext(), "Lavoura não encontrada.",
                        Toast.LENGTH_LONG).show();
                limpaCampos();
            }
            Toast.makeText(view.getContext(), "Lavoura Encontrada!",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }



    private Crop montaCrop(){
        Crop c = null;
        c = tipoLavoura(c);
        c.setId(Integer.parseInt(etIdCrop.getText().toString()));
        c.setName(etNameCrop.getText().toString());
        c.setDays(Integer.parseInt(etDaysCrop.getText().toString()));
        c.setPrice(Integer.parseInt(etPriceCrop.getText().toString()));
        if (rbSpgCrop.isChecked()){
            c.setSeason("primavera");
        } else if (rbSumCrop.isChecked()){
            c.setSeason("verao");
        } else if (rbAutCrop.isChecked()){
            c.setSeason("outuno");
        } else {
            c.setSeason("inverno");
        }
        return c;
    }

    private Crop montaBuscaCrop(){
        Crop c = null;
        c = tipoLavoura(c);
        c.setId(Integer.parseInt(etIdCrop.getText().toString()));
        return c;
    }

    private void preencheCampos(Crop c){
        if (c instanceof Fruit){
            rbFruitCrop.setChecked(true);
        } else {
            rbVegetableCrop.setChecked(true);
        }
        etIdCrop.setText(String.valueOf(c.getId()));
        etNameCrop.setText(c.getName());
        etDaysCrop.setText(String.valueOf(c.getDays()));
        etPriceCrop.setText(String.valueOf(c.getPrice()));
        switch (c.getSeason()) {
            case "primavera":
                rbSpgCrop.setChecked(true);
                break;
            case "verao":
                rbSumCrop.setChecked(true);
                break;
            case "outono":
                rbAutCrop.setChecked(true);
                break;
            default:
                rbWinCrop.setChecked(true);
                break;
        }
    }
    private void limpaCampos(){
        etIdCrop.setText("");
        etNameCrop.setText("");
        etPriceCrop.setText("");
        etDaysCrop.setText("");
    }

    private Crop tipoLavoura(Crop c){
        if (rbFruitCrop.isChecked()){
            c = new Fruit();
            return c;
        } else {
            c = new Vegetable();
            return c;
        }
    }
}