package com.example.loterias.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.loterias.R;
import com.example.loterias.utils.NumeroAleatorio;

import java.util.concurrent.atomic.AtomicInteger;

public class Primitiva extends Sorteo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    private int complementario;
    private int reintegro;

    public Primitiva(String fecha) {
        super(atomicInteger.getAndIncrement(), fecha, NumeroAleatorio.getListaNumerosAleatorios(1,49,6), R.drawable.ic_primitiva);

        this.complementario = NumeroAleatorio.numeroAleatorio(1,49);
        this.reintegro = NumeroAleatorio.numeroAleatorio(0,9);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder aux= new StringBuilder("[");
        for (int i = 0; i < super.getCombinacion().size(); i++){
            if (super.getCombinacion().size()-1 != i)
                aux.append(super.getCombinacion().get(i).toString() + ",");
            else
                aux.append(super.getCombinacion().get(i).toString()+"]");
        }
        aux.append("["+complementario+"]");
        aux.append("["+reintegro+"]");
        return aux.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Primitiva && ((Primitiva)obj).getId() == this.getId())
            return true;
        return false;
    }
}
