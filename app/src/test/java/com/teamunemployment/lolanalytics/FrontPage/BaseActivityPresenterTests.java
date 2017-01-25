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
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setTabIcon(anyInt());
    }

    @Test
    public void handleTabPress_SetsRoleName() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setRoleName(anyString());
    }

    @Test
    public void handleTabPress_RequestsWinRate() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(baseActivityModel, times(1)).fetchWinRateForRole(anyLong(), anyInt(), any(BaseActivityContract.Presenter.class));
    }

    @Test
    public void onWinRateLoaded_setsWinRate() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        baseActivityPresenter.onWinRateLoaded(12);
        verify(view, times(1)).setWinRate(anyDouble());
    }

    @Test
    public void TestThatWeCanSetCorrectFragmentTab() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);
        view.setCorrectTabFragment(1);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_TOP() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(0);

        verify(view, times(1)).setCorrectTabFragment(0);
        verify(view, times(1)).setRoleName("Top");
        verify(view, times(1)).setTabIcon(R.drawable.top);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_JUNGLE() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(1);

        verify(view, times(1)).setCorrectTabFragment(1);
        verify(view, times(1)).setRoleName("Jungle");
        verify(view, times(1)).setTabIcon(R.drawable.jg);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_MID() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(2);

        verify(view, times(1)).setCorrectTabFragment(2);
        verify(view, times(1)).setRoleName("Mid");
        verify(view, times(1)).setTabIcon(R.drawable.mid);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_MARKSMAN() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(3);

        verify(view, times(1)).setCorrectTabFragment(3);
        verify(view, times(1)).setRoleName("Marksman");
        verify(view, times(1)).setTabIcon(R.drawable.bot);
    }

    @Test
    public void TestThatWeSetSpecificsCorrect_SUPPORT() {
        BaseActivityModel baseActivityModel = mock(BaseActivityModel.class);
        BaseActivityPresenter baseActivityPresenter = new BaseActivityPresenter(baseActivityModel);
        BaseActivityView view = mock(BaseActivityView.class);
        baseActivityPresenter.setView(view);
        baseActivityPresenter.handleTabPress(4);

        verify(view, times(1)).setCorrectTabFragment(4);
        verify(view, times(1)).setRoleName("Support");
        verify(view, times(1)).setTabIcon(R.drawable.sup);
    }
}
