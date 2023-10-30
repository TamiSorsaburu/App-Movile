package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoMecanico;
import com.example.servicedelautomotor.entidades.Mecanico;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdaptadorMecanico extends RecyclerView.Adapter<AdaptadorMecanico.viewHolder>{
    List<Mecanico> mecanicos;
    public AdaptadorMecanico(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.desing_lista_mecanico,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtId.setText(String.valueOf(mecanicos.get(position).getIdMecanico()));
        holder.lNombre.setText(mecanicos.get(position).getNombre());
        holder.lApellido.setText(mecanicos.get(position).getApellido());
        holder.lTelefono.setText(String.valueOf(mecanicos.get(position).getTelefono()));
        holder.lDisponible.setText(mecanicos.get(position).getGuardia());
        holder.lSucursal.setText(String.valueOf(mecanicos.get(position).getSucursalMecanicoId()));


        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AppDataBase db=Room.databaseBuilder(holder.txtId.getContext(),
                       AppDataBase.class,"dbServiceAutomotor").allowMainThreadQueries().build();
               DaoMecanico daoMecanico=db.daoMecanico();
               daoMecanico.eliminarMecanico(mecanicos.get(position).getIdMecanico());
               mecanicos.remove(position);

               notifyDataSetChanged();

            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(new Intent(holder.btnEditar.getContext(),ActualizarMecanico.class));
                intent.putExtra("idMecanico",String.valueOf(mecanicos.get(position).getIdMecanico()));

                intent.putExtra("nombre",mecanicos.get(position).getNombre());
                intent.putExtra("apellido",mecanicos.get(position).getApellido());
                intent.putExtra("telefono",String.valueOf(mecanicos.get(position).getTelefono()));
                //intent.putExtra("guardia",mecanicos.get(position).getGuardia());
                intent.putExtra("sucursalMecanicoId",String.valueOf(mecanicos.get(position).getSucursalMecanicoId()));
                holder.btnEditar.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mecanicos.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView lTelefono,lNombre,lApellido,lSucursal,lDisponible,txtId;
        ImageButton btnEliminar,btnEditar;


        //AppDataBase appDataBase;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            lTelefono=itemView.findViewById(R.id.txtTelefono);
            lNombre=itemView.findViewById(R.id.textNombre);
            lApellido=itemView.findViewById(R.id.txtApellido);
            lSucursal=itemView.findViewById(R.id.txtSucursal);
            lDisponible=itemView.findViewById(R.id.txtGuardia);
            btnEditar=itemView.findViewById(R.id.btnEditar);
            btnEliminar=itemView.findViewById(R.id.btnEliminar);
            txtId=itemView.findViewById(R.id.txtId);

            //appDataBase = AppDataBase.getInstance(this);

        }
    }
}
