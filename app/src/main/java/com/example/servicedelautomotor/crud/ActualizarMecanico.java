package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.servicedelautomotor.Contacto;
import com.example.servicedelautomotor.Dashboard;
import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoMecanico;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActualizarMecanico extends AppCompatActivity {
    Button btnEditar;
    EditText eNombre,eApellido,eTelefono,eSucursal;
    TextView guardiaE;

    Switch aSwitchG;
    int idMecanico;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mecanico);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(ActualizarMecanico.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


        eNombre=(EditText)findViewById(R.id.textNombre);
        eApellido=(EditText)findViewById(R.id.txtApellido);
        eTelefono=(EditText)findViewById(R.id.txtTelefono);
        eSucursal=(EditText)findViewById(R.id.txtSucursal);
        aSwitchG = (Switch) findViewById(R.id.switchGuardia);
        guardiaE=(TextView) findViewById(R.id.txtGuardia);

        idMecanico=Integer.parseInt(getIntent().getStringExtra("idMecanico"));
        eNombre.setText(getIntent().getStringExtra("nombre"));
        eApellido.setText(getIntent().getStringExtra("apellido"));
        eTelefono.setText(getIntent().getStringExtra("telefono"));
        guardiaE.setText(getIntent().getStringExtra("guardia"));
        eSucursal.setText(getIntent().getStringExtra("sucursalMecanicoId"));

        btnEditar=(Button) findViewById(R.id.botonActualizar);
        btnEditar.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) {
               AppDataBase db=Room.databaseBuilder(getApplicationContext(),
                       AppDataBase.class,"dbServiceAutomotor").allowMainThreadQueries().build();
               DaoMecanico daoMecanico=db.daoMecanico();
               daoMecanico.actualizarMecanico(idMecanico,eNombre.getText().toString(),eApellido.getText().toString()
               ,Integer.parseInt(eTelefono.getText().toString()),Integer.parseInt(eSucursal.getText().toString()),guardiaE.getText().toString());
               startActivity(new Intent(getApplicationContext(),ActualizarMecanico.class));
               finish();
                Toast.makeText(ActualizarMecanico.this,"Se actualizo exitosamente!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),ListaMecanicos.class));
                finish();
            }
        });


    }
    public void onclick(View view){
        if(view.getId()== R.id.switchGuardia){
            if (aSwitchG.isChecked()) {
                guardiaE.setText("Disponible");
            }else{
                guardiaE.setText("No disponible");
            }
        }
    }

}
