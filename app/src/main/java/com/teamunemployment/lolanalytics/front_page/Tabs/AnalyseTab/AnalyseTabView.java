package com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab;

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
import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.Utils.Constant;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Josiah Kendall
 */

public class AnalyseTabView extends Fragment implements AnalyseTabContract.View {

    @Inject
    AnalysePresenter presenter;

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.error_message) TextView errorMessageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.tab_view_fragment, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        //
        Bundle args = getArguments();
        int role = args.getInt(Constant.ROLE_KEY, -1);

        // Set our view. This starts the loading of value.
        presenter.SetView(this);
        presenter.SetRole(role);
        presenter.Start();

        return rootView;
    }

    @Override
    public void SetAdapter(AnalyseAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void SetPlaceHolderVisible() {
        errorMessageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void SetPlaceHolderInvisible() {
        errorMessageView.setVisibility(View.GONE);
    }

    @Override
    public void SetPlaceHolderString(String message) {
        errorMessageView.setText(message);
    }

    @Override
    public void SetChamp(Champ champ) {
        presenter.SetChamp(champ);
        presenter.Start();
    }

    @Override
    public void setRole(int role) {
        presenter.SetRole(role);
    }

    // TODO remove this shit
    @Override
    public void setAdapter(TabContract.TabAdapter adapter) {

    }

    @Override
    public void setLoadingVisible(boolean visible) {

    }

    @Override
    public void showMessage(String message) {

    }
}
