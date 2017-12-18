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
        void ShowOverlay();
        void HideOverlay();
        void SetChampFabIconAsACross();
        void SetChampFabIconAsNone();
        void SetChampFabIconAsSelectedChamp(String champIconUrl);
        void ShowChampList();
        void HideChampList();
        void ShowSearchBar();
        void HideSearchBar();
        void SetChampList(ArrayList<Champ> champs);
        void ensureKeyboardIsHidden();
        void ClearSearchText();
        void ShowClearSearchTextButton();
        void HideClearSearchTextButton();
    }
}