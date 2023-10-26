package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

public class SolicitarTurno extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView txtHoraSeleccionada, btnHora, fechaTurnoTextView;
    private int horaSeleccionada, minutoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_turno);

        // Recupera la información de los servicios seleccionados desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String serviciosSeleccionados = sharedPreferences.getString("serviciosSeleccionados", "");

        // Muestra la información en el TextView "servicios_seleccionados"
        TextView serviciosSeleccionadosTextView = findViewById(R.id.servicios_seleccionados);
        serviciosSeleccionadosTextView.setText(serviciosSeleccionados);

        // Obtiene referencias a los elementos de la interfaz
        calendarView = findViewById(R.id.calendarView);
        fechaTurnoTextView = findViewById(R.id.fecha_seleccionada);

        // Configura el Listener para el CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Formatea la fecha seleccionada
                String fechaSeleccionada = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);

                // Muestra la fecha en el TextView "fecha_turno"
                fechaTurnoTextView.setText(fechaSeleccionada);
            }
        });

        TextView textoAsubrayado = findViewById(R.id.btnHora);
        // Texto que deseas subrayar
        String texto = "Elegir Horario";

        // Obtén el precio pasado desde la primera actividad
        String precio = getIntent().getStringExtra("precio");

        // Encuentra el TextView "textPrecioTotal" en la segunda actividad
        TextView textPrecioTotal = findViewById(R.id.textPrecioTotal);

        // Establece el precio en el TextView "textPrecioTotal"
        textPrecioTotal.setText(precio);

        // Crear un SpannableString y aplicar el subrayado
        SpannableString spannableString = new SpannableString(texto);
        spannableString.setSpan(new UnderlineSpan(), 0, texto.length(), 0);

        // Establecer el SpannableString en el TextView
        textoAsubrayado.setText(spannableString);

        btnHora = findViewById(R.id.btnHora);
        txtHoraSeleccionada = findViewById(R.id.hora_turno);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene la hora y el minuto actuales
                final java.util.Calendar calendario = java.util.Calendar.getInstance();
                horaSeleccionada = calendario.get(java.util.Calendar.HOUR_OF_DAY);
                minutoSeleccionado = calendario.get(java.util.Calendar.MINUTE);

                // Crea un diálogo para seleccionar la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(SolicitarTurno.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                                // Actualiza la hora seleccionada
                                horaSeleccionada = hora;
                                minutoSeleccionado = minuto;

                                // Actualiza el TextView con la hora seleccionada
                                actualizarTextViewHora();
                            }
                        }, horaSeleccionada, minutoSeleccionado, true);

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });

    }

    // Método para actualizar el TextView con la hora seleccionada
    private void actualizarTextViewHora() {
        String horaFormateada = String.format("%02d:%02d", horaSeleccionada, minutoSeleccionado);
        txtHoraSeleccionada.setText("Hora: " + horaFormateada);
    }


    public void botonConfirmar(View V) {
        Intent confirmar = new Intent(this, ConfirmarTurno.class);
        startActivity(confirmar);
    }
    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, PresupuestoActivity.class);
        startActivity(cancelar);
    }
}