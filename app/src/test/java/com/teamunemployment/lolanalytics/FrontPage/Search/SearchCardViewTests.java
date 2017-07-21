package com.teamunemployment.lolanalytics.FrontPage.Search;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.Model.ChampSearchCardView;
import com.teamunemployment.lolanalytics.FrontPage.Search.Model.OffSetViewHolder;
import com.teamunemployment.lolanalytics.Utils.Constant;

import junit.framework.Assert;

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

    @Test // Fails because layout inflater is not mocked
    public void EnsureWeOffSetChampSearchScrollSlightly() {
        SearchPresenter searchPresenter = mock(SearchPresenter.class);
        SearchListAdapter searchListAdapter = new SearchListAdapter(searchPresenter);
        RecyclerView.ViewHolder viewHolder = searchListAdapter.onCreateViewHolder(mock(ViewGroup.class), Constant.ViewType.OFFSET_VIEW);
        Assert.assertTrue(viewHolder instanceof OffSetViewHolder);

    }

    @Test
    public void EnsureWeDontSetChampToBlankItem() {
        SearchPresenter searchPresenter = mock(SearchPresenter.class);
        SearchListAdapter searchListAdapter = new SearchListAdapter(searchPresenter);
        searchListAdapter.onBindViewHolder(mock(OffSetViewHolder.class), 0);

    }



}
