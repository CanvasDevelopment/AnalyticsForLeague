package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class PresenterTests {

    @Test(expected = IllegalStateException.class)
    public void Start_ThrowsExceptionIfViewNotSet() {
        Context context = mock(Context.class);
        CoachInteractor interactor = new CoachInteractor(context);
        CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter = mock(CoachHorizontalScrollerAdapter.class);
        CoachPresenter coachPresenter = new CoachPresenter(interactor, context, coachHorizontalScrollerAdapter);
        coachPresenter.startLoadInfo(-1);
    }

    @Test
    public void Start_DoesNotThrowExceptionIfViewSet() {
        Context context = mock(Context.class);
        CoachInteractor coachInteractor = new CoachInteractor(context);
        CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter = mock(CoachHorizontalScrollerAdapter.class);
        CoachPresenter coachPresenter = new CoachPresenter(coachInteractor, context, coachHorizontalScrollerAdapter);
        CoachContract.View view = mock(CoachContract.View.class);
        coachPresenter.setView(view);
        coachPresenter.startLoadInfo(-1);
    }

    @Test
    public void LoadWinRates_SetsWinRateGraph() {
        Context context = mock(Context.class);
        CoachInteractor interactor = new CoachInteractor(context);
        CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter = mock(CoachHorizontalScrollerAdapter.class);
        CoachPresenter coachPresenter = new CoachPresenter(interactor, context, coachHorizontalScrollerAdapter);
        CoachContract.View view = mock(CoachContract.View.class);
        coachPresenter.setView(view);
        coachPresenter.LoadWinRates(-1);
        verify(view, times(1)).setWinRateGraph(any(ArrayList.class), any(ArrayList.class));
    }

    @Test
    public void LoadCards_SetsAdapter() {
        Context context = mock(Context.class);
        CoachInteractor coachInteractor = new CoachInteractor(context);
        CoachContract.View view = mock(CoachContract.View.class);
        CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter = mock(CoachHorizontalScrollerAdapter.class);
        CoachPresenter coachPresenter = new CoachPresenter(coachInteractor, context, coachHorizontalScrollerAdapter);
        coachPresenter.setView(view);
        coachPresenter.LoadCards(-1);
        verify(view,times(1)).setRecylcerViewAdapter(any(CoachHorizontalScrollerAdapter.class));
    }



    @Test
    public void NoNetwork_TestWeNotifyUser() {

    }
}
