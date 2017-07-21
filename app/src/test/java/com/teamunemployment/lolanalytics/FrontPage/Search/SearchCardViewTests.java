package com.teamunemployment.lolanalytics.FrontPage.Search;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.Model.ChampSearchCardView;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Josiah Kendall
 */

public class SearchCardViewTests {

    @Test
    public void EnsureThatWeSetImage() {
        String champUrl = "fhdskfgdlsk";
        SearchPresenter searchPresenter = mock(SearchPresenter.class);
        SearchListAdapter searchListAdapter = new SearchListAdapter(searchPresenter);
        ArrayList<Champ> arrayList = new ArrayList<>();
        Champ champ = new Champ();
        champ.setChampUrl(champUrl);
        arrayList.add(champ);
        searchListAdapter.SetData(arrayList);
        ChampSearchCardView searchCardView = mock(ChampSearchCardView.class);
        searchListAdapter.onBindViewHolder(searchCardView, 0);
        verify(searchCardView, times(1)).SetChamp(champ);
    }

    @Test (expected = IllegalStateException.class)
    public void EnsureThatWeThrowExceptionIfWeLoadWithoutSettingData() {
        String champUrl = "fhdskfgdlsk";
        SearchPresenter searchPresenter = mock(SearchPresenter.class);
        SearchListAdapter searchListAdapter = new SearchListAdapter(searchPresenter);
        ArrayList<Champ> arrayList = new ArrayList<>();
        ChampSearchCardView searchCardView = mock(ChampSearchCardView.class);
        searchListAdapter.onBindViewHolder(searchCardView, 0);
    }



}
