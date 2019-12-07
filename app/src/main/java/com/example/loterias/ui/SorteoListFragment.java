package com.example.loterias.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.loterias.R;
import com.example.loterias.adapter.SorteoAdapter;
import com.example.loterias.data.model.Sorteo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class SorteoListFragment extends Fragment implements SorteoListContrac.view{

    public static final String TAG = "SorteoListFragment";
    private OnFragmentInteractionListener mListener;
    private SorteoListContrac.Presenter presenter;
    private SorteoAdapter adapter;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private SorteoAdapter.OnViewHolderClick listener;

    public static SorteoListFragment newInstance(Bundle bundle) {
        SorteoListFragment fragment = new SorteoListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orderByDate:
                adapter.sortByDate(new Sorteo.orderByDate());
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Date order",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sorteo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SorteoAdapter(new SorteoAdapter.OnViewHolderClick() {
            @Override
            public void onModify(Sorteo sorteo) {
                mListener.onAddOrModifySorteo(sorteo);
            }

            @Override
            public void onDelete(final Sorteo sorteo) {
                new AlertDialog.Builder(getContext())
                        .setMessage("¿Borrar el sorteo del día "+sorteo.getFecha()+"?")
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteSorteo(sorteo);
                            }
                        })
                .show();
            }
        });
        recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingActionButton = view.findViewById(R.id.fabAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddOrModifySorteo(null);
            }
        });


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
    public void onStart() {
        super.onStart();
        presenter.cargarTodoInicio();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh(ArrayList<Sorteo> sorteosList) {
        adapter.clear();
        adapter.addAll(sorteosList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void undo(final Sorteo sorteo) {
        Snackbar.make(getView(),"Restablecer el sorteo del dia " + sorteo.getFecha()+"?",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addSorteo(sorteo);
            }
        }).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void noData() {

    }

    @Override
    public void setPresenter(SorteoListContrac.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void genericError(String error) {

    }

    @Override
    public void onSuccess() {

    }

    public interface OnFragmentInteractionListener {
        void onAddOrModifySorteo(Sorteo sorteo);
    }
}
