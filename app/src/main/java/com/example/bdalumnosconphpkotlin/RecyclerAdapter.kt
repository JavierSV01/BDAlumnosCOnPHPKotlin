package com.example.bdalumnosconphpkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.alumnos_row.view.*
import java.lang.IllegalArgumentException

class RecyclerAdapter(
    val context:Context,
    val listaAlumnos:List<Alumnos>):RecyclerView.Adapter<BaseViewHolder<*>>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        // inflamos vista
        return AlumnosViewHolder(LayoutInflater.from(context).inflate(R.layout.alumnos_row,parent,false))

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //carga datos en lista
        if (holder is AlumnosViewHolder)
            holder.bind(listaAlumnos[position],position)
        else
            throw IllegalArgumentException("Error viewHolder erroneo")
    }

    override fun getItemCount(): Int =  listaAlumnos.size          //número de items



    inner class AlumnosViewHolder(itemView:View):BaseViewHolder<Alumnos>(itemView)// nos aseguramos de que cuando la clase padre muera, muera esta también
    {
        override fun bind(item: Alumnos, position: Int) {

            itemView.txNombre.text = item.nombre;
            itemView.txId.text = item.id;
            itemView.txCurso.text = item.curso;
            itemView.txEdad.text = item.edad.toString();

        }
    }

}