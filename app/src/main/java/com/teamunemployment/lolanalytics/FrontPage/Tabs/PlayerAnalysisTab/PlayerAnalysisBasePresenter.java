package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatSummary;
import com.teamunemployment.lolanalytics.Model.Contracts.StatLoadingContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Josiah Kendall.
 */
public class PlayerAnalysisBasePresenter implements PlayerAnalysisContract.BasePresenter,
        StatLoadingContract.StatCollectionContract {

    private PlayerAnalysisContract.View view;
    private PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter;
    private int ROLE = 1;
    private long summonerId = -1;

    @Inject
    public PlayerAnalysisBasePresenter(PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter) {
        this.playerAnalysisPersistanceInteracter = playerAnalysisPersistanceInteracter;
    }

    /**
     * Logic to how we load the initial data for the view.
     */
    @Override
    public void start() {
        if (view == null) {
            throw new IllegalStateException("View must be set before calling start()");
        }

        Observer<StatCollection> statCollectionObserver = getStatCollectionObserver();
        playerAnalysisPersistanceInteracter.LoadStatAnalysisCardObjects(ROLE, summonerId, statCollectionObserver);
    }

    @Override
    public void handleError(Throwable e) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void LoadStatList(int role, long summonerId) {
        Observer<StatCollection> statCollectionSubscriber = getStatCollectionObserver();
        playerAnalysisPersistanceInteracter.LoadStatAnalysisCardObjects(ROLE, summonerId, statCollectionSubscriber);
    }

    @Override
    public void LoadStatDetails(long statId, long summonerId) {

        Observer<StatSummary> statDataObserver = new Observer<StatSummary>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatSummary statData) {

            }
        };

        playerAnalysisPersistanceInteracter.LoadStatHistory(statId, summonerId, statDataObserver);
    }

    private Observer<StatCollection> getStatCollectionObserver() {
        Observer<StatCollection> statCollectionObserver = new Observer<StatCollection>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatCollection statData) {

            }
        };

        return statCollectionObserver;
    }

    @Override
    public void setView(PlayerAnalysisContract.View view) {
        this.view = view;
    }

    @Override
    public void onStatCollectionLoaded(StatCollection collection) {
        view.setStatCollectionData(collection);
    }

    @Override
    public void onStatCollectionFailed(String message) {
        view.showMessage(message);
    }
}
