package com.teamunemployment.lolanalytics.front_page.champ_menu;

import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.data.model.ChampFrequency;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public interface ChampMenuContract {

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

        void filter(@NotNull String searchText);

        void refresh();
    }
}
