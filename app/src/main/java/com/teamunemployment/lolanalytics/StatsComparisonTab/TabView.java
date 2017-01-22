package com.teamunemployment.lolanalytics.StatsComparisonTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.DependencyInjection.App;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */
public class TabView extends Fragment implements TabContract.View {

    @Inject
    public TabPresenter presenter;

    public android.view.View rootView;

    @Bind(R.id.jungle_recycler) RecyclerView jungleRecyclerView;
   // @Bind(R.id.collapsable_toolbar_holder) CollapsingToolbarLayout collapsingToolbarLayout;

    private int role = -1;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.jungle_fragment_view, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        // Set our view. This starts the loading of data.
        presenter.setView(this, role);

        return rootView;
    }

    @Override
    public void setAdapter(TabRecyclerAdapter tabRecyclerAdapter) {
        // TODO: Think about giving the presenter an instance of context here. Or using dagger, this is on the main thread and it doesnt need to be.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        jungleRecyclerView.setLayoutManager(layoutManager);
        jungleRecyclerView.setAdapter(tabRecyclerAdapter);
    }

    @Override
    public void showMessage(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_LONG).show();
    }

    public void setRole(int role) {
        this.role = role;
    }
}
