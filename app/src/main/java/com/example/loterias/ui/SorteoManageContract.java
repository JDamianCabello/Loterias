package com.example.loterias.ui;

import com.example.loterias.base.BaseView;
import com.example.loterias.data.model.Sorteo;

public class SorteoManageContract {
    interface View extends BaseView<Presenter> {
        void showDateEmptyError(String error);
        void showFormatDateError(String error);
        void showRepeatDateError(String error);

        void cleanDateEmptyError();
        void cleanFormatDateError();
        void clearRepeatDateError();
    }

    interface Presenter{
        void addSorteo(Sorteo sorteo);
        void modifySorteo(Sorteo sorteo);
    }
}
