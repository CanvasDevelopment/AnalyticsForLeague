package com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab;

import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab.Model.AnalyseCardViewHolder;
import com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab.Model.AnalyseData;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Josiah Kendall
 */

public class AnalysePresenterTests {

    private AnalyseTabContract.View view;
    private AnalyseAdapter analyseAdapter;
    private AnalyseInteractor interactor;
    private AnalysePresenter analysePresenter;
    private RoleUtils roleUtils;

    /**
     * General flow of the presenter:
     * START
     *      set the presenter to our adapter.
     *      ensure that our view is set
     *      get our current role, and champ if set
     *      request value
     *
     * HANDLE DATA RESPONSE
     *      Set the value to the value list.
     *      Set our adapter to the view
     *      Notify the adapter that value has changed.
     *      If we have no items in our datalist, set the placeholder that says -no value in role.
     *      if we have a champ set make sure we add that to our string.
     *      if we dont have a champ set make sure that we dont add that to our string.
     *
     * UPDATE ROLE
     *      We need to be able to update the role of a user, and refetch the value.
     *
     * UPDATE CHAMP
     *      We need to be able to update the role of a champ, and refeetch the value.
     *
     * HANDLE CARD BINDING
     *      The presenter needs to handle the card binding - in other words, it recieves the
     *      view holder, and sets the appropriate parameters to it.
     *
     * HANDLE CARD CLICK
     *      The card needs to be able to redirect clicks on itself to the presenter, and the presenter
     *      needs to then open the correct
     */


    @Before
    public void setUpPresenter() {
        roleUtils = mock(RoleUtils.class);
        analyseAdapter = mock(AnalyseAdapter.class);
        view = mock(AnalyseTabContract.View.class);

        interactor = mock(AnalyseInteractor.class);
        analysePresenter = new AnalysePresenter(interactor, analyseAdapter, roleUtils);
        analysePresenter.SetView(view);
    }

    @Test
    public void EnsureThatWeSetTheAdapterAndTriggerDisplayWhenWeGetDataReturned() {
        ArrayList<AnalyseData> arrayList = new ArrayList();
        arrayList.add(mock(AnalyseData.class));
        view = mock(AnalyseTabContract.View.class);
        interactor = new AnalyseInteractor();
        analysePresenter.SetView(view);
        analysePresenter.SetFilterRequestResponse(arrayList);
        verify(view, times(1)).SetAdapter(analyseAdapter);
    }


    @Test
    public void EnsureThatWeDontSetAdapterWhenWeHaveNoData() {
        ArrayList<AnalyseData> arrayList = new ArrayList();
        Champ champ = new Champ();
        champ.setChampName("Vi");
        when(roleUtils.GetRoleName(4)).thenReturn("SUPPORT");
        analysePresenter.SetChamp(champ);
        analysePresenter.SetFilterRequestResponse(arrayList);
        verify(view, times(0)).SetAdapter(analyseAdapter);
    }

    @Test
    public void EnsureThatWeSetCorrectPlaceholderMessageForRoleAndChamp() {
        ArrayList<AnalyseData> arrayList = new ArrayList();
        Champ champ = new Champ();
        champ.setChampName("Vi");
        when(roleUtils.GetRoleName(4)).thenReturn("SUPPORT");
        analysePresenter.SetChamp(champ);
        analysePresenter.SetRole(4);
        analysePresenter.SetFilterRequestResponse(arrayList);
        verify(view, times(0)).SetAdapter(analyseAdapter);
        String noResults = "No games found playing SUPPORT with Vi";
        verify(view, times(1)).SetPlaceHolderString(noResults);
    }

    @Test
    public void EnsureThatWeSetCorrectPlaceHolderMessageWithRoleAndNoChamp() {
        ArrayList<AnalyseData> arrayList = new ArrayList();
        Champ champ = new Champ();
        champ.setChampName("Vi");
        when(roleUtils.GetRoleName(4)).thenReturn("SUPPORT");
        analysePresenter.SetChamp(null);
        analysePresenter.SetRole(4);
        analysePresenter.SetFilterRequestResponse(arrayList);
        verify(view, times(0)).SetAdapter(analyseAdapter);
        String noResults = "No games found playing SUPPORT";
        verify(view, times(1)).SetPlaceHolderString(noResults);
    }


    @Test(expected = IllegalStateException.class)
    public void EnsureThatIllegalStateExceptionGetsThrownIfWeTryToCreateAMessageWithoutARoleBeingSet() {
        ArrayList<AnalyseData> arrayList = new ArrayList();
        Champ champ = new Champ();
        champ.setChampName("Vi");
        when(roleUtils.GetRoleName(6)).thenThrow(new IllegalStateException());
        analysePresenter.SetChamp(champ);
        analysePresenter.SetRole(6);
        analysePresenter.SetFilterRequestResponse(arrayList);
        verify(view, times(0)).SetAdapter(analyseAdapter);
        String noResults = "No games found playing TOP with Vi";
    }

    @Test
    public void EnsureThatWeShowPlaceholderWhenWeSetPlaceHolderText() {
        analysePresenter.SetPlaceHolder("hey there");
        verify(view, times(1)).SetPlaceHolderVisible();
    }

    @Test
    public void EnsureThatWeHidePlaceholderWhenWeSetTheAdapter() {
        ArrayList<AnalyseData> analyseDatas = new ArrayList<>();
        analyseDatas.add(mock(AnalyseData.class));
        analysePresenter.SetFilterRequestResponse(analyseDatas);
        verify(view, times(1)).SetPlaceHolderInvisible();
    }

    @Test
    public void EnsureThatWeSetThePresenterToTheAdpaterWhenWeStart() {
        analysePresenter.SetRole(1);
        analysePresenter.Start();
        verify(analyseAdapter, times(1)).SetPresenter(analysePresenter);
    }

    @Test (expected = IllegalStateException.class)
    public void EnsureWeThrowIllegalStateExceptionIfRoleIsNotSet() {
        analysePresenter.Start();
    }

    @Test
    public void EnsureWeOnlyRequestRoleFilterIfWeHaveNoChampSet() {
        analysePresenter.SetRole(4);
        analysePresenter.Start();
        verify(interactor, times(1)).RequestFilterList(4,  analysePresenter);
    }

    @Test
    public void EnsureThatWeFetchForChampAndRoleIfWeHaveAChampFilterSet() {
        analysePresenter.SetRole(1);
        Champ champ = mock(Champ.class);
        when(champ.getChampId()).thenReturn(31);
        analysePresenter.SetChamp(champ);
        analysePresenter.Start();
        verify(interactor, times(1)).RequestFilterList(1, 31, analysePresenter);
    }

    @Test
    public void TestThatWeSetTitleTextCorrectly() {
        float change = -12.1f;
        float hero = 45.8f;
        float enemy = 54.2f;
        String title = "Early Game";

        ArrayList<AnalyseData> datas = new ArrayList<>();
        AnalyseData analyseData = new AnalyseData();
        analyseData.setEnemyPercentTotal(enemy);
        analyseData.setHeroPercentTotal(hero);
        analyseData.setTitle(title);
        analyseData.setRecentChange(change);
        datas.add(analyseData);
        analysePresenter.SetFilterRequestResponse(datas);
        AnalyseCardViewHolder viewHolder = mock(AnalyseCardViewHolder.class);
        analysePresenter.OnCardBinding(viewHolder, 0);
        verify(viewHolder, times(1)).SetTitle(title);
    }

    @Test
    public void TestThatWeSetGraphCorrectlyWhenWeBind() {
        float change = -12.1f;
        float hero = 45.8f;
        float enemy = 54.2f;
        String title = "Early Game";

        ArrayList<AnalyseData> datas = new ArrayList<>();
        AnalyseData analyseData = new AnalyseData();
        analyseData.setEnemyPercentTotal(enemy);
        analyseData.setHeroPercentTotal(hero);
        analyseData.setTitle(title);
        analyseData.setRecentChange(change);
        datas.add(analyseData);
        analysePresenter.SetFilterRequestResponse(datas);

        AnalyseCardViewHolder viewHolder = mock(AnalyseCardViewHolder.class);
        analysePresenter.OnCardBinding(viewHolder, 0);
        verify(viewHolder, times(1)).SetGraph(enemy, hero);

    }

    @Test
    public void TestThatWeSetChangeCorrectly() {
        float change = -12.1f;
        float hero = 45.8f;
        float enemy = 54.2f;
        String title = "Early Game";

        ArrayList<AnalyseData> datas = new ArrayList<>();
        AnalyseData analyseData = new AnalyseData();
        analyseData.setEnemyPercentTotal(enemy);
        analyseData.setHeroPercentTotal(hero);
        analyseData.setTitle(title);
        analyseData.setRecentChange(change);
        datas.add(analyseData);
        analysePresenter.SetFilterRequestResponse(datas);

        AnalyseCardViewHolder viewHolder = mock(AnalyseCardViewHolder.class);
        analysePresenter.OnCardBinding(viewHolder, 0);
        verify(viewHolder, times(1)).SetChange(change);

    }

    @Test
    public void EnsureThatWeSetPositionToViewHolder() {
        ArrayList<AnalyseData> datas = new ArrayList<>();
        datas.add(mock(AnalyseData.class));
        datas.add(mock(AnalyseData.class));
        datas.add(mock(AnalyseData.class));
        analysePresenter.SetFilterRequestResponse(datas);
        AnalyseCardViewHolder viewHolder = mock(AnalyseCardViewHolder.class);
        analysePresenter.OnCardBinding(viewHolder, 2);
        verify(viewHolder, times(1)).SetItemPosition(2);

    }


    @Test
    public void EnsureThatWeCanHandleClicksInPresenter() {
//        analysePresenter.HandleItemClick(3);
    }
}
