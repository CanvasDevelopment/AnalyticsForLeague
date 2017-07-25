package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.ThreeGameStageStatistic;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;
import com.teamunemployment.lolanalytics.Utils.Filters.ChampSingleton;
import com.teamunemployment.lolanalytics.Utils.Filters.RoleSingleton;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class StatisticsTabPresenter implements StatisticsTabContract.Presenter {

    private StatisticsTabAdapter adapter;
    private StatisticsTabInteractor interactor;
    private ChampSingleton champSingleton;
    private RoleSingleton roleSingleton;
    private RoleUtils roleUtils;
    private StatisticsTabContract.View view;
    private ArrayList<ThreeGameStageStatistic> listData;

    public StatisticsTabPresenter(
            StatisticsTabInteractor statisticsTabInteractor,
            StatisticsTabAdapter statisticsTabAdapter,
            ChampSingleton champSingleton,
            RoleSingleton roleSingleton,
            RoleUtils roleUtils) {

        this.roleUtils = roleUtils;
        this.interactor = statisticsTabInteractor;
        this.adapter = statisticsTabAdapter;
        this.champSingleton = champSingleton;
        this.roleSingleton = roleSingleton;
    }

    @Override
    public void onCardBinding(StatisticsGameStageComparisonViewHolder holder, int position) {
        // handle binding of the card
        ThreeGameStageStatistic gameStageStatistic = listData.get(position);
        holder.setTitle(gameStageStatistic.getTitle());
        holder.setEarlyGame(gameStageStatistic.getEarlyGame());
        holder.setMidGame(gameStageStatistic.getMidGame());
        holder.setLateGame(gameStageStatistic.getLateGame());
    }

    @Override
    public int getFilterListSize() {
        return listData.size();
    }

    @Override
    public void setView(StatisticsTabContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        Champ champ = champSingleton.getCurrentChamp();
        int role = roleSingleton.getRole();
        if (champ == null) {
            interactor.getStatistics(role, this);
            return;
        }

        interactor.getStatistics(role, champ.getChampId(), this);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void handleDataResponse(ArrayList<ThreeGameStageStatistic> data) {
        this.listData = data;
        // when we set the adapter, it updates the data. Should probably find a better solution to this.
        if (listData.size() > 0) {
            view.setListVisible();
            view.setNoDataPlaceholderHidden();
            view.setAdapter(adapter);
        } else {
            view.setNoDataPlaceholderVisible();
            String placeholdermessage = String.format(
                                        "No games found as %s",
                                        roleUtils.GetRoleName(roleSingleton.getRole()));

            Champ champ = champSingleton.getCurrentChamp();
            if (champ != null) {
                placeholdermessage = String.format(
                                        "No games found as %s with %s",
                                        roleUtils.GetRoleName(roleSingleton.getRole()),
                                        champ.toString());
            }
            view.setListHidden();
            view.setPlaceholderString(placeholdermessage);
        }
    }
}
