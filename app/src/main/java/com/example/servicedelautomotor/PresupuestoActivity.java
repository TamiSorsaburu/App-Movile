package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class PresupuestoActivity extends AppCompatActivity {
    private Switch switchLavadero, switchCambioAceite, switchTapizado, switchNeumaticos;
    private TextView precioTextView;
    private String fechaSeleccionada;

    private String serviciosSeleccionados = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);

        switchLavadero = findViewById(R.id.presupuesto_texto4);
        switchCambioAceite = findViewById(R.id.presupuesto_texto5);
        switchTapizado = findViewById(R.id.presupuesto_texto6);
        switchNeumaticos = findViewById(R.id.presupuesto_texto7);
        precioTextView = findViewById(R.id.presupuesto_texto9); // Agrega un TextView para mostrar el precio

        // Agrega un listener al Switch
        switchLavadero.setOnCheckedChangeListener(new SwitchChangeListener());
        switchCambioAceite.setOnCheckedChangeListener(new SwitchChangeListener());
        switchTapizado.setOnCheckedChangeListener(new SwitchChangeListener());
        switchNeumaticos.setOnCheckedChangeListener(new SwitchChangeListener());

    }
    public void botonConfirmar(View V) {
        // Obtén el precio actual desde el TextView precioTextView
        String precio = precioTextView.getText().toString();

        Intent confirmar = new Intent(this, SolicitarTurno.class);
        // Agrega el precio como dato extra al intent
        confirmar.putExtra("precio", precio);
        // Agrega la fecha seleccionada como dato extra al intent
        confirmar.putExtra("fecha", fechaSeleccionada);

        startActivity(confirmar);
    }
    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }

    private class SwitchChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Actualiza el precio en función del estado de cada Switch
            int precioActual = Integer.parseInt(precioTextView.getText().toString().replace("$", "").replace(".", ""));

            if (isChecked) {
                // El Switch está activado, agrega el precio correspondiente
                if (buttonView == switchLavadero) {
                    precioActual += 5000; // Por ejemplo, $20,000
                } else if (buttonView == switchCambioAceite) {
                    precioActual += 7000; // Por ejemplo, $15,000
                } else if (buttonView == switchTapizado) {
                    precioActual += 10000; // Por ejemplo, $10,000
                } else if (buttonView == switchNeumaticos) {
                    precioActual += 25000; // Por ejemplo, $25,000
                }
                serviciosSeleccionados += buttonView.getText().toString() + "\n";
            } else {
                // El Switch está desactivado, resta el precio correspondiente
                if (buttonView == switchLavadero) {
                    precioActual -= 5000;
                } else if (buttonView == switchCambioAceite) {
                    precioActual -= 7000;
                } else if (buttonView == switchTapizado) {
                    precioActual -= 10000;
                } else if (buttonView == switchNeumaticos) {
                    precioActual -= 25000;
                }
                serviciosSeleccionados = serviciosSeleccionados.replace(buttonView.getText().toString() + "\n", "");
            }

            // Actualiza el TextView con el nuevo precio
            precioTextView.setText("$" + String.format("%,d", precioActual));

            // Guarda la información en SharedPreferences para que pueda ser accedida por la siguiente actividad.
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("serviciosSeleccionados", serviciosSeleccionados);
            editor.apply();
        }
    }
}