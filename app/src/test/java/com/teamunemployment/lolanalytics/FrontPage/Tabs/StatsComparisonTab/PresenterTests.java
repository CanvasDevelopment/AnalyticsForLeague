package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class PresenterTests {

    @Test
    public void TEstThatWeShowMessageToUserWhenWeFailToFetchStats() {
        // TODO subscriber tests
    }

    @Test(expected = IllegalStateException.class)
    public void TestThatWeThrowIllegalStatExceptionIfStartIsCalledBeforeViewSet() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.start();
    }

    @Test(expected = IllegalStateException.class)
    public void TestThatWeThrowIllegalStateExceptionIfWeGiveIllegalLaneValueTooLow() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(null, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void TestThatWeGiveillegalStateExceptionIfWeGiveIllegalLaneValueTooHigh() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(null, 5);
    }

    @Test
    public void TestThatStartFetchesCachedData() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabView tabView = mock(TabView.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(tabView, 2);
        verify(tabModel, times(1)).CreateCachedDataObservable(anyLong(), anyInt());
    }

    @Test
    public void TestThatStartFetchesNetworkData() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabView tabView = mock(TabView.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(tabView, 2);
        verify(tabModel, times(1)).CreateLaneDataObservable(anyLong(), anyInt());
    }

    @Test
    public void TestThatWeCanHideTheProgressBar() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabView tabView = mock(TabView.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(tabView, 1);
        presenter.addDataToAdapter(null);
        verify(tabView, times(1)).setLoadingVisibile(false);
    }

    @Test
    public void TestThatWeShowErrorMessageWhenWeRecieveAnError() {
        TabModel tabModel = mock(TabModel.class);
        TabRecyclerAdapter tabRecyclerAdapter = mock(TabRecyclerAdapter.class);
        TabView tabView = mock(TabView.class);
        TabBasePresenter presenter = new TabBasePresenter(tabModel, tabRecyclerAdapter);
        presenter.setView(tabView, 1);
        presenter.handleError(mock(Throwable.class));
        verify(tabView, times(1)).setErrorMessage(anyString());
    }
}
