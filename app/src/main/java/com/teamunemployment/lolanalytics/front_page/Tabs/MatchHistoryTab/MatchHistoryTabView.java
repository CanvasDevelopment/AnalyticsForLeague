package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab;

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
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryTabView extends Fragment implements TabContract.View {

   // @Bind(R.id.root) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recycler) RecyclerView recycler;
    //@Bind(R.id.progress_spinner) ProgressBar progressBar;
    @BindView(R.id.error_message) TextView errorMessageTextBox;

    private View rootView;
    private int role;

    @Inject
    public MatchHistoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.tab_view_fragment, container, false);

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
       // Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setRole(int role) {
        this.role = role;
        presenter.setRole(role);
        presenter.start();
    }

    @Override
    public void setAdapter(TabContract.TabAdapter adapter) {
        MatchHistoryAdapter matchHistoryAdapter = (MatchHistoryAdapter) adapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(matchHistoryAdapter);
    }

    @Override
    public void setLoadingVisible(boolean visible) {

    }
}
