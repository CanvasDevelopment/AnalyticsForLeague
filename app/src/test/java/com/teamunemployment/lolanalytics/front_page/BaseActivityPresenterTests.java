package com.teamunemployment.lolanalytics.front_page;

import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.Utils.Role;

import org.junit.Test;

import static org.mockito.Matchers.any;
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
        verify(baseActivityPersistenceInteractor, times(1)).fetchWinRateForRole(
                                                                                            anyString(),
                                                                                            anyString(),
                                                                                            anyString(),
                                                                                            any(BaseActivityContract.Presenter.class));
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

    @Test
    public void testThatWeCanSendCorrectVariablesToTheViewWhenARoleIsChanged() {
        BaseActivityView view = mock(BaseActivityView.class);
        BaseActivityPersistenceInteractor baseActivityPersistenceInteractor = mock(BaseActivityPersistenceInteractor.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityPersistenceInteractor);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleChangeInRole(R.id.action_mid);
        Role role = new Role();
        verify(view, times(1)).setCorrectTabFragment(role.getMID());
    }
}
