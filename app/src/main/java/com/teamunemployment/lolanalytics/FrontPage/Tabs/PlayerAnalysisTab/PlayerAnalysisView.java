package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */
public class PlayerAnalysisView extends Fragment implements TabContract.View {

    // @Bind(R.id.root) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.recycler) RecyclerView recycler;
    //@Bind(R.id.progress_spinner) ProgressBar progressBar;
    @Bind(R.id.error_message) TextView errorMessageTextBox;

    private int role;

    @Inject
    public PlayerAnalysisPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_view_fragment, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        presenter.setView(this);
        // Top is default role
        presenter.setRole(role);
        presenter.start();
        return rootView;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setRole(int role) {
       this.role = role;

        // DO REFRESH TODO
    }

    @Override
    public void setAdapter(TabContract.TabAdapter adapter) {
        PlayerAnalysisAdapter adapter1 = (PlayerAnalysisAdapter) adapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter1);
    }

    @Override
    public void setLoadingVisible(boolean visible) {

    }
}
