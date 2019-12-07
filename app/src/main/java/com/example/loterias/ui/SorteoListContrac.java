package com.example.loterias.ui;

import com.example.loterias.base.BaseView;
import com.example.loterias.data.model.Sorteo;

import java.util.ArrayList;

public class SorteoListContrac {
    interface view extends BaseView<Presenter> {
        void refresh(ArrayList<Sorteo> sorteosList);
        void undo(Sorteo sorteo);
        void showProgress();
        void hideProgress();
        void noData();
    }

    interface Presenter{
        void deleteSorteo(Sorteo sorteo);
        void addSorteo(Sorteo sorteo);
        void cargarTodoInicio();
    }
}
