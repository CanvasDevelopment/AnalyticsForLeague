package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards;

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the
 */

public interface MatchHistoryCardViewContract {
    void setGraph1(HeadToHeadStat HeadToHeadStat);
    void setGraph2(HeadToHeadStat HeadToHeadStat);
    void setGraph3(HeadToHeadStat HeadToHeadStat);
    void setGraph4(HeadToHeadStat HeadToHeadStat);
    void setHeroChampIcon(String champIconUrl);
    void setEnemyChampIcon(String champIconUrl);
    void setTimeStamp(String timeStamp);
    void setResult(boolean win);
    void setChamp(String champName);
    void setSummaryChart(HeadToHeadStat HeadToHeadStat);
}
