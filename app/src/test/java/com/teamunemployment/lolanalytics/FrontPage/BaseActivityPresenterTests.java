package com.teamunemployment.lolanalytics.FrontPage;

import com.teamunemployment.lolanalytics.R;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class BaseActivityPresenterTests {

    @Test
    public void handleTabPress_RequestsWinRate() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(baseActivityPersistenceInteractor, times(1)).fetchWinRateForRole(anyLong(), anyString(), any(BaseActivityContract.Presenter.class));
    }

    @Test
    public void onWinRateLoaded_setsWinRate() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        //verify(view, times(1)).setWinRate(anyDouble()); TODO
    }

    @Test
    public void TestThatWeCanSetCorrectFragmentTab() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        verify(view, times(1)).setCorrectTabFragment(1);
    }
}
