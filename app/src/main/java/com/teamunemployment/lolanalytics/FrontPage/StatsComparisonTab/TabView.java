package com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teamunemployment.lolanalytics.DependencyInjection.App;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.StatsComparisonTab.TabRecyclerAdapter;

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
    @Bind(R.id.progress_spinner) ProgressBar progressBar;
    @Bind(R.id.error_message) TextView errorMessageTextBox;

    private int role = -1;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.tab_view_fragment, container, false);

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
        // TODO: Think about giving the presenter an instance of context here. Or using dagger, this is on the base thread and it doesnt need to be.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        jungleRecyclerView.setLayoutManager(layoutManager);
        jungleRecyclerView.setAdapter(tabRecyclerAdapter);
    }

    @Override
    public void setLoadingVisibile(boolean visibile) {
        if (visibile) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void showMessage(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        errorMessageTextBox.setText(errorMessage);
    }

    public void setRole(int role) {
        this.role = role;
    }
}
