package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class PresenterTests {

    @Test
    public void LoadMatchList_LoadsMatchListFromNetwork() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);

        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryModel, times(1)).LoadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchList_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryModel, times(1)).LoadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchSummary_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        presenter.LoadMatchSummary(-1);
    }

    @Test
    public void LoadMatchSummary_LoadsMatchSummaryFromNetwork() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadMatchSummary(123456);
        verify(matchHistoryModel, times(1)).LoadFreshMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_AttemptsToLoadMatchListFromTheCache() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryModel, times(1)).LoadCachedMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test
    public void LoadMatchSummary_AttemptsToLoadMatchFromCache() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadMatchSummary(123456);
        verify(matchHistoryModel, times(1)).LoadCachedMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_DoesntHideLoadingSpinnerIfWeDontFindAnythingInTheCache() {

    }

    @Test
    public void OnMatchesLoaded_TriggersSetAdapterWhenLoadedSuccessfully() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);

        MatchHistoryData matchHistoryData = new MatchHistoryData();
        matchHistoryData.setRole(Statics.TOP);
        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
        verify(tabView, times(1)).setAdapter(any(MatchHistoryAdapter.class));
    }

    @Test
    public void OnMatchesLoadError_TriggersErrorMessage() {
        MatchHistoryModel matchHistoryModel = mock(MatchHistoryModel.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryModel);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);

        presenter.onError(new Throwable());
        verify(tabView, times(1)).showMessage("An error occurred. Please try again.");
    }
}
