package com.example.loterias.ui;

import com.example.loterias.data.SorteoRepository;
import com.example.loterias.data.model.Sorteo;

import java.util.ArrayList;

public class SorteoListPresenter implements SorteoListContrac.Presenter {
    SorteoListContrac.view view;

    public SorteoListPresenter(SorteoListContrac.view view) {
        this.view = view;
    }

    @Override
    public void deleteSorteo(Sorteo sorteo) {
        if(SorteoRepository.getInstance.deleteSorteo(sorteo)) {
            view.refresh((ArrayList<Sorteo>) SorteoRepository.getInstance.getList());
            view.undo(sorteo);
        }
    }

    @Override
    public void addSorteo(Sorteo sorteo) {
        if(SorteoRepository.getInstance.addSorteo(sorteo))
            view.refresh((ArrayList<Sorteo>) SorteoRepository.getInstance.getList());
    }

    @Override
    public void cargarTodoInicio() {
        view.refresh((ArrayList<Sorteo>) SorteoRepository.getInstance.getList());
    }
}
