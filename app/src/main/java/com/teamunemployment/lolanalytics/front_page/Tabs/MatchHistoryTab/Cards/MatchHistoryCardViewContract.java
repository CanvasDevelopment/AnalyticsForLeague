package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards;

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the
 */

public interface MatchHistoryCardViewContract {
    void setGraph1(GameStageStat gameStageStat);
    void setGraph2(GameStageStat gameStageStat);
    void setGraph3(GameStageStat gameStageStat);
    void setGraph4(GameStageStat gameStageStat);
    void setHeroChampIcon(String champIconUrl);
    void setEnemyChampIcon(String champIconUrl);
    void setTimeStamp(String timeStamp);
    void setResult(boolean win);
    void setChamp(String champName);
    void setSummaryChart(GameStageStat gameStageStat);
}
