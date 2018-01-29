package com.teamunemployment.lolanalytics.front_page.Search;

import com.teamunemployment.lolanalytics.data.model.Champ;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public interface SearchContract {
    interface Presenter {
        void SetChampRequestResponse(ArrayList<Champ> champs);
    }

    interface View {
        void showOverlay();
        void hideOverlay();
        void setChampFabIconAsACross();
        void setChampFabIconAsNone();
        void setChampFabIconAsSelectedChamp(String champIconUrl);
        void showChampList();
        void hideChampList();
        void showSearchBar();
        void hideSearchBar();
        void setChampList(ArrayList<Champ> champs);
        void ensureKeyboardIsHidden();
        void clearSearchField();
        void showClearSearchTextButton();
        void hideClearSearchTextButton();
    }
}
