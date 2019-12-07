package com.example.loterias.data;

import androidx.annotation.Nullable;

import com.example.loterias.data.model.Bonoloto;
import com.example.loterias.data.model.Euromillon;
import com.example.loterias.data.model.Primitiva;
import com.example.loterias.data.model.Sorteo;

import java.util.ArrayList;
import java.util.List;

public class SorteoRepository {
    private List<Sorteo> sorteoList;
    public static SorteoRepository getInstance;

    static {
        getInstance = new SorteoRepository();
    }

    public SorteoRepository() {
        this.sorteoList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        sorteoList.add(new Euromillon("12/02/2012"));
        sorteoList.add(new Primitiva("12/02/2012"));
        sorteoList.add(new Primitiva("03/03/2012"));
        sorteoList.add(new Primitiva("24/02/2015"));
        sorteoList.add(new Euromillon("05/02/2012"));
        sorteoList.add(new Euromillon("06/02/2012"));
        sorteoList.add(new Bonoloto("07/02/2012"));
    }

    public List<Sorteo> getList(){
        return sorteoList;
    }

    public boolean deleteSorteo(Sorteo sorteo){
        return sorteoList.remove(sorteo);
    }

    public boolean addSorteo(Sorteo sorteo){
        return sorteoList.add(sorteo);
    }

    public boolean modify(Sorteo sorteo){
        for(Sorteo s : sorteoList){
            if(s.equals(sorteo)){
                s.setFecha(sorteo.getFecha());
                return true;
            }
        }
        return false;
    }
}
