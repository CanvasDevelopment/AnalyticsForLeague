package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryTabView extends Fragment implements MatchHistoryTabContract.View {

    @Bind(R.id.root) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.recycler) RecyclerView recycler;
    @Bind(R.id.progress_spinner) ProgressBar progressBar;
    @Bind(R.id.error_message) TextView errorMessageTextBox;

    private View rootView;

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
        presenter.setRole(Statics.TOP);
        presenter.start();
        return rootView;
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setAdapter(MatchHistoryAdapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    @Override
    public void updateActivityRole(int role) {
        presenter.setRole(role);
        presenter.start();
    }
}
