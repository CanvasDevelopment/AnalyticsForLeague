package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards;

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the
 */

public interface MatchHistoryCardViewContract {
    void setGraph1(CardData cardData);
    void setGraph2(CardData cardData);
    void setGraph3(CardData cardData);
    void setGraph4(CardData cardData);
    void setHeroChampIcon(String champIconUrl);
    void setEnemyChampIcon(String champIconUrl);
    void setTimeStamp(String timeStamp);
    void setResult(boolean win);
    void setChamp(String champName);
}
