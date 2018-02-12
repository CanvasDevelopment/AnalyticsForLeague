package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards;

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the
 */

public interface MatchHistoryCardViewContract {
    void setGraph1(MatchHistoryGameStageData matchHistoryGameStageData);
    void setGraph2(MatchHistoryGameStageData matchHistoryGameStageData);
    void setGraph3(MatchHistoryGameStageData matchHistoryGameStageData);
    void setGraph4(MatchHistoryGameStageData matchHistoryGameStageData);
    void setHeroChampIcon(String champIconUrl);
    void setEnemyChampIcon(String champIconUrl);
    void setTimeStamp(String timeStamp);
    void setResult(boolean win);
    void setChamp(String champName);
    void setSummaryChart(MatchHistoryGameStageData matchHistoryGameStageData);
}
