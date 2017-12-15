package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab;

import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class AnalysePresenter implements AnalyseTabContract.Presenter {

    private AnalyseInteractor analyseInteractor;
    private AnalyseAdapter analyseAdapter;
    private AnalyseTabContract.View view;
    private RoleUtils roleUtils;

    private int role = -1;
    private Champ champ = null;
    private ArrayList<AnalysisData> filterList = new ArrayList<>();

    public AnalysePresenter(AnalyseInteractor analyseInteractor, AnalyseAdapter analyseAdapter, RoleUtils roleUtils) {
        this.analyseInteractor = analyseInteractor;
        this.analyseAdapter = analyseAdapter;
        this.roleUtils = roleUtils;
    }

    @Override
    public void SetFilterRequestResponse(ArrayList<AnalysisData> analysisData) {
        this.filterList = analysisData;

        // If we have data
        if (analysisData.size() > 0) {
            view.SetAdapter(analyseAdapter);
            view.SetPlaceHolderInvisible();
        } else {
            // This means we have no value. Show an appropriate message to the user based on their settings.
            if (champ != null) {
                String message = String.format(
                        "No games found playing %s with %s",
                        roleUtils.GetRoleName(role),
                        champ.getChampName()
                );
                setPlaceHolder(message);
            } else {
                String message = String.format(
                        "No games found playing %s",
                        roleUtils.GetRoleName(role)
                );
                setPlaceHolder(message);
            }
        }
    }

    @Override
    public int GetFilterListSize() {
        return filterList.size();
    }

    @Override
    public void SetView(AnalyseTabContract.View view) {
        this.view = view;
    }

    @Override
    public void Start() {
        if (view == null) {
            throw new IllegalStateException("Must call SetView before calling start");
        }

        if (role == -1) {
            throw new IllegalStateException("Must set role before calling start");
        }

        analyseAdapter.SetPresenter(this);

        // If we have a champ, include it. If we dont, don include it.
        if (champ != null) {
            analyseInteractor.RequestFilterList(role, champ.getChampId(), this);
        } else {
            analyseInteractor.RequestFilterList(role, this);
        }

    }

    @Override
    public void SetRole(int role) {
        this.role = role;
    }

    @Override
    public void SetChamp(Champ champ) {
        this.champ = champ;
    }

    @Override
    public void setPlaceHolder(String string) {
        view.SetPlaceHolderVisible();
        view.SetPlaceHolderString(string);
    }

    @Override
    public void OnCardBinding(AnalyseTabContract.CardView viewHolder, int position) {
        AnalysisData data = filterList.get(position);
        viewHolder.SetGraph(data.getEnemyPercentTotal(), data.getHeroPercentTotal());
        viewHolder.SetChange(data.getRecentChange());
        viewHolder.SetTitle(data.getTitle());
        viewHolder.SetItemPosition(position);
    }

    @Override
    public void HandleItemClick(int position) {
        AnalysisData filter = filterList.get(position);
        // launch new activtiy with the value.
    }
}
