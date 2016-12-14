package com.teamunemployment.lolanalytics.Jungle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.teamunemployment.lolanalytics.DependencyInjection.App;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JungleAdapter;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */
public class JungleView extends Fragment implements JungleViewContract{

    @Inject
    JunglePresenter junglePresenter;

    private LayoutInflater inflater;
    private View rootView;
    private AppCompatActivity activityContext;

    @Bind(R.id.jungle_recycler) RecyclerView jungleRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.jungle_fragment_view, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectJungleView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        // Give a reference of this view to our presenter. Done for presenting information or creating
        // instances of objects must be created on the ui thread.
        junglePresenter.setView(this);

        return rootView;
    }

    @Override
    public BarChart createBarChart() {
        return new BarChart(this.getContext());
    }

    @Override
    public void setJungleAdapter(JungleAdapter jungleAdapter) {
        // TODO: Think about giving the presenter an instance of context here. Or using dagger, this is on the main thread and it doesnt need to be.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        jungleRecyclerView.setLayoutManager(layoutManager);
        jungleRecyclerView.setAdapter(jungleAdapter);
    }


}
