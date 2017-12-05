package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamunemployment.lolanalytics.App;

import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 *
 * View for a role tab fragment.
 */
public class TabView extends Fragment implements TabContract.View {

    @Inject
    public TabPresenter presenter;

    public android.view.View rootView;

    @BindView(R.id.recycler) RecyclerView recycler;
    //@Bind(R.id.progress_spinner) ProgressBar progressBar;
    @BindView(R.id.error_message) TextView errorMessageTextBox;

    private int role = 0;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.tab_view_fragment, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        // Set our view. This starts the loading of value.
        presenter.setView(this, role);

        return rootView;
    }


    @Override
    public void setLoadingVisible(boolean visibile) {
        if (visibile) {
         //   progressBar.setVisibility(View.VISIBLE);
        } else {
          //  progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void showMessage(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_LONG).show();
    }


    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public void setAdapter(TabContract.TabAdapter adapter) {
        TabRecyclerAdapter tabRecyclerAdapter = (TabRecyclerAdapter) adapter;
        // TODO: Think about giving the presenter an instance of context here. Or using dagger, this is on the base thread and it doesnt need to be.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(tabRecyclerAdapter);
    }
}
