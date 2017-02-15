package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the {@link MatchHistoryTabView} list
 */

public interface MatchHistoryCardViewContract {
    void setGraph1(CardData cardData);
    void setGraph2(CardData cardData);
    void setGraph3(CardData cardData);
    void setGraph4(CardData cardData);
    void setChampName(String champName);
    void setChampIcon(String champIconUrl);
    void setKDA(String kdaString);
}
