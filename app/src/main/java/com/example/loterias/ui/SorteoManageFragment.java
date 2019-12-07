package com.example.loterias.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.loterias.R;
import com.example.loterias.data.model.Bonoloto;
import com.example.loterias.data.model.Euromillon;
import com.example.loterias.data.model.Primitiva;
import com.example.loterias.data.model.Sorteo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SorteoManageFragment extends Fragment implements SorteoManageContract.View{

    public static final String TAG = "SorteoManageFragment";
    private OnFragmentInteractionListener mListener;
    private SorteoManageContract.Presenter presenter;
    private TextInputEditText date;
    private TextInputLayout tilDate;
    private FloatingActionButton fabSave;
    private Spinner spinner;

    public static SorteoManageFragment newInstance(Bundle b) {
        SorteoManageFragment fragment = new SorteoManageFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sorteo_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date = view.findViewById(R.id.tiledDate);
        fabSave = view.findViewById(R.id.fabSave);
        spinner = view.findViewById(R.id.spinner);
        tilDate = view.findViewById(R.id.tilDate);


        if(getArguments()!= null){
            date.setText(((Sorteo)getArguments().getParcelable(Sorteo.Key)).getFecha());
            spinner.setSelection(getSpinnerPosition((Sorteo) getArguments().getParcelable(Sorteo.Key)));
            spinner.setEnabled(false);

        }


        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments() == null) {
                    Sorteo s = null;
                    switch (spinner.getSelectedItem().toString()) {
                        case "Primitiva":
                            s = new Primitiva(date.getText().toString());
                            break;
                        case "Bonoloto":
                            s = new Bonoloto(date.getText().toString());
                            break;
                        case "Euromillón":
                            s = new Euromillon(date.getText().toString());
                            break;
                    }
                    presenter.addSorteo(s);
                }
                else {
                    Sorteo s = getArguments().getParcelable(Sorteo.Key);
                    s.setFecha(date.getText().toString());
                    presenter.modifySorteo(s);
                }
            }
        });


    }

    private int getSpinnerPosition(Sorteo sorteo) {
        if(sorteo instanceof Bonoloto)
            return 0;
        if(sorteo instanceof Euromillon)
            return 1;
        if(sorteo instanceof Primitiva)
            return 2;
        return -1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void setPresenter(SorteoManageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void genericError(String error) {

    }

    @Override
    public void onSuccess() {
        mListener.onModifyOrAddDone();
    }

    @Override
    public void showDateEmptyError(String error) {
        tilDate.setError(error);
    }

    @Override
    public void showFormatDateError(String error) {
        tilDate.setError(error);
    }

    @Override
    public void showRepeatDateError(String error) {
        tilDate.setError(error);
    }

    @Override
    public void cleanDateEmptyError() {
        tilDate.setError(null);
    }

    @Override
    public void cleanFormatDateError() {
        tilDate.setError(null);
    }

    @Override
    public void clearRepeatDateError() {
        tilDate.setError(null);
    }


    public interface OnFragmentInteractionListener {
        void onModifyOrAddDone();
    }
}
