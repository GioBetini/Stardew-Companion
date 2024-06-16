package com.example.trabalhosemestral.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trabalhosemestral.R;
import com.example.trabalhosemestral.model.Crop;
import com.example.trabalhosemestral.model.Fruit;
import com.example.trabalhosemestral.model.Vegetable;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Button btnVoltar;
    private Button btnConfirmar;
    private RadioButton rbFruit;
    private RadioButton rbVegetable;
    private Crop crop;
    private RadioButton rbSeason;
    private EditText etName;
    private EditText etPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            carregaFragment(bundle);
        } else {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            fragTransaction.replace(R.id.fragment, new InitialFragment());
            fragTransaction.commit();
        }

    }

    private void carregaFragment(Bundle bundle){
        String tipo = bundle.getString("tipo");
        if(tipo.equals("crop")){
            fragment = new CropFragment();
        } else if (tipo.equals("npc")) {
            fragment = new NpcFragment();
        } else{
            fragment = new GrimoireFragment();
        }
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.fragment, fragment);
        fragTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.menu_lavoura){
            bundle.putString("tipo", "crop");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        } else if (id == R.id.menu_npc){
            bundle.putString("tipo", "npc");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        } else {
            bundle.putString("tipo", "grimorio");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }

    }
    private void createCrop() {
        if (rbFruit.isChecked()) {
            crop = new Fruit();
        }
        if (rbVegetable.isChecked()){
            crop = new Vegetable();
        }
        //btnVoltar.setOnClickListener(op -> voltar());
    }


}