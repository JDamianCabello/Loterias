package com.example.loterias.ui;

import com.example.loterias.data.SorteoRepository;
import com.example.loterias.data.model.Sorteo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SorteoManegePresenter implements SorteoManageContract.Presenter{
    private SorteoManageContract.View view;

    public SorteoManegePresenter(SorteoManageContract.View view) {
        this.view = view;
    }

    @Override
    public void addSorteo(Sorteo sorteo) {
        if(validaDate(sorteo)) {
            SorteoRepository.getInstance.addSorteo(sorteo);
            view.onSuccess();
        }
    }

    @Override
    public void modifySorteo(Sorteo sorteo) {
        if(validaDate(sorteo)) {
            SorteoRepository.getInstance.modify(sorteo);
            view.onSuccess();
        }
    }

    private boolean validaDate(Sorteo sorteo){
        view.cleanDateEmptyError();
        view.cleanFormatDateError();
        view.clearRepeatDateError();
        if(validateDateEmty(sorteo.getFecha())){
            if(correctFormat(sorteo.getFecha())){
                if(repeatDateAndLottery(sorteo)){
                    return true;
                }
                else {
                    view.showRepeatDateError("Ya existe un sorteo de " + sorteo.getClass().getSimpleName() + " con esa fecha");
                    return false;
                }
            }
            else {
                view.showFormatDateError("La fecha no tiene un formato correcto");
                return false;
            }

        }
        else {
            view.showDateEmptyError("La fecha está vacía");
            return false;
        }
    }

    private boolean validateDateEmty(String date){
         return !date.isEmpty();
    }

    private boolean correctFormat(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            simpleDateFormat.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean repeatDateAndLottery(Sorteo sorteo){
        for (Sorteo s : SorteoRepository.getInstance.getList()){
            if(s.getClass() == sorteo.getClass() && sorteo.getFecha().equals(s.getFecha()))
                return false;
        }
        return true;
    }
}
