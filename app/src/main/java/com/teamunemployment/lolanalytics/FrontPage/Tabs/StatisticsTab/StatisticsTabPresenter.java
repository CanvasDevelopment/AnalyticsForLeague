package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsCardDataObject;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;
import com.teamunemployment.lolanalytics.R;
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
    private ArrayList<StatisticsCardDataObject> listData;

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
        StatisticsCardDataObject gameStageStatistic = listData.get(position);
        holder.setTitle(gameStageStatistic.getTitle());
        holder.setEarlyGame(gameStageStatistic.getEarlyGameChartData(), gameStageStatistic.getPerformancePercentage());
        holder.setMidGame(gameStageStatistic.getMidGameChartData(), gameStageStatistic.getPerformancePercentage());
        holder.setLateGame(gameStageStatistic.getLateGameChartData(), gameStageStatistic.getPerformancePercentage());

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
    public void handleDataResponse(ArrayList<StatisticsCardDataObject> data) {
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

    /**
     * Style a pie chart. This method gives the pie chart certain style features.
     *  - No Title
     *  - No Description
     *  - No slice values
     *  - No Key
     *  - Visible center text set to the accent color in the theme
     *  - tap-able slices - which can be tapped to show the raw values
     * @param pieChart
     * @return The pie chart with the updated styles
     */
    @Override
    public PieChart prepClickableDetailChart(PieChart pieChart) {

        pieChart.setDrawMarkers(false);
        pieChart.setUsePercentValues(false);
        pieChart.setDrawSliceText(false);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.setCenterTextColor(R.color.colorAccent);
        pieChart.setDrawEntryLabels(false);

        // Dont want to display a description.
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.getDescription().setEnabled(false);

        // Hide the legend. The reason that we set the legend is because
        pieChart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        pieChart.getLegend().setEnabled(false);
        return pieChart;
    }
}
