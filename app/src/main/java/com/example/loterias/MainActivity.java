package com.example.loterias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;

import com.example.loterias.data.model.Sorteo;
import com.example.loterias.ui.SorteoListFragment;
import com.example.loterias.ui.SorteoListPresenter;
import com.example.loterias.ui.SorteoManageFragment;
import com.example.loterias.ui.SorteoManegePresenter;

public class MainActivity extends AppCompatActivity implements SorteoListFragment.OnFragmentInteractionListener, SorteoManageFragment.OnFragmentInteractionListener{
    private SorteoListFragment sorteoListFragment;
    private SorteoManageFragment sorteoManageFragment;

    private SorteoListPresenter sorteoListPresenter;
    private SorteoManegePresenter sorteoManegePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sorteoListFragment = (SorteoListFragment) getSupportFragmentManager().findFragmentByTag(SorteoListFragment.TAG);

        if(sorteoListFragment == null){
            sorteoListFragment = SorteoListFragment.newInstance(null);

            getSupportFragmentManager().beginTransaction().add(android.R.id.content,sorteoListFragment,SorteoListFragment.TAG).addToBackStack(null).commit();
        }


        sorteoListPresenter = new SorteoListPresenter(sorteoListFragment);
        sorteoListFragment.setPresenter(sorteoListPresenter);
    }

    @Override
    public void onAddOrModifySorteo(Sorteo sorteo) {
        sorteoManageFragment = (SorteoManageFragment) getSupportFragmentManager().findFragmentByTag(SorteoManageFragment.TAG);

        if(sorteoManageFragment == null){
            Bundle bundle = null;

            if(sorteo != null) {
                bundle = new Bundle();
                bundle.putParcelable(Sorteo.Key, sorteo);
            }
            sorteoManageFragment = SorteoManageFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content,sorteoManageFragment,SorteoManageFragment.TAG).addToBackStack(null).commit();

            sorteoManegePresenter = new SorteoManegePresenter(sorteoManageFragment);
            sorteoManageFragment.setPresenter(sorteoManegePresenter);
        }
    }

    @Override
    public void onModifyOrAddDone() {
        onBackPressed();
    }
}
