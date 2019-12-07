package com.example.loterias.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.loterias.R;
import com.example.loterias.utils.NumeroAleatorio;

import java.util.concurrent.atomic.AtomicInteger;

public class Euromillon extends Sorteo {
    private int estrella1;
    private int estrella2;

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public Euromillon(String fecha) {
        super(atomicInteger.getAndIncrement(), fecha, NumeroAleatorio.getListaNumerosAleatorios(1,50,5), R.drawable.ic_euromillon);

        this.estrella1 = NumeroAleatorio.numeroAleatorio(1,12);
        this.estrella2 = NumeroAleatorio.numeroAleatorio(1,12);
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
        aux.append("["+estrella1+"]");
        aux.append("["+estrella2+"]");
        return aux.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Euromillon && ((Euromillon)obj).getId() == this.getId())
            return true;
        return false;
    }
}
