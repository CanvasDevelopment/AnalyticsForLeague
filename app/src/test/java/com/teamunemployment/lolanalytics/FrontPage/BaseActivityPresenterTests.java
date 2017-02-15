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
    public void handleTabPress_SetsTabIcon() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setTabIcon(anyInt());
    }

    @Test
    public void handleTabPress_SetsRoleName() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setRoleName(anyString());
    }

    @Test
    public void handleTabPress_RequestsWinRate() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(baseActivityPersistenceInteractor, times(1)).fetchWinRateForRole(anyLong(), anyString(), any(BaseActivityContract.BasePresenter.class));
    }

    @Test
    public void onWinRateLoaded_setsWinRate() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setWinRate(anyDouble());
    }

    @Test
    public void TestThatWeCanSetCorrectFragmentTab() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        verify(view, times(1)).setCorrectTabFragment(1);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_TOP() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(0);

        verify(view, times(1)).setCorrectTabFragment(0);
        verify(view, times(1)).setRoleName("Top");
        verify(view, times(1)).setTabIcon(R.drawable.top);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_JUNGLE() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);

        verify(view, times(1)).setCorrectTabFragment(1);
        verify(view, times(1)).setRoleName("Jungle");
        verify(view, times(1)).setTabIcon(R.drawable.jg);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_MID() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(2);

        verify(view, times(1)).setCorrectTabFragment(2);
        verify(view, times(1)).setRoleName("Mid");
        verify(view, times(1)).setTabIcon(R.drawable.mid);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_MARKSMAN() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(3);

        verify(view, times(1)).setCorrectTabFragment(3);
        verify(view, times(1)).setRoleName("Marksman");
        verify(view, times(1)).setTabIcon(R.drawable.bot);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_SUPPORT() {
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityBasePresenter baseActivityPresenter = new BaseActivityBasePresenter(baseActivityPersistenceInteractor);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(4);

        verify(view, times(1)).setCorrectTabFragment(4);
        verify(view, times(1)).setRoleName("Support");
        verify(view, times(1)).setTabIcon(R.drawable.sup);
    }
}
