package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

/**
 * Created by Josiah Kendall
 *
 * Our interactor class. Responsible for fetching our statistics and passing it back to the presenter.
 */

public class StatisticsTabInteractor {

    /**
     * Get the statistics for this summoner in the given role.
     * @param role                      The role that we want the statistics for.
     * @param presenter                 The presenter that we will callback when we have the data.
     * @return                          The statistics for this summoner in the specified role.
     */
    public void getStatistics(int role, StatisticsTabContract.Presenter presenter) {}

    /**
     * Get the statistics for this summoner in the given role with the given champ.
     * @param role                      The role that we want the statistics for.
     * @param champId                   The champ that we want the statistics for.
     * @param presenter                 The presenter that we will callback when we have the data.
     * @return                          The statistics for this summoner in the specified role with
     *                                  the specified champ.
     */
    public void getStatistics(int role, int champId, StatisticsTabContract.Presenter presenter) {}
}
