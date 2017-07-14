package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.content.Context;
import android.util.Log;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatDefinitionWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.Model.Contracts.StatLoadingContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Josiah Kendall.
 */
public class PlayerAnalysisPresenter implements PlayerAnalysisContract.Presenter,
        StatLoadingContract.StatCollectionContract {
    // TODO REFACTOR THIS CLASS
    private Context context;
    private TabContract.View view;
    private PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter;
    private int ROLE = -1;
    private long summonerId = -1;

    @Inject
    public PlayerAnalysisPresenter(PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter,
                                   Context context) {
        this.playerAnalysisPersistanceInteracter = playerAnalysisPersistanceInteracter;
        this.context = context;

    }

    /**
     * Logic to how we load the initial data for the view.
     */
    @Override
    public void start() {
        if (view == null) {
            throw new IllegalStateException("View must be set before calling start()");
        }

        if (ROLE == -1) {
            throw new IllegalStateException("Role must be set before calling start()");
        }

        playerAnalysisPersistanceInteracter.LoadStatTypes(ROLE,-1, getStatDefinitionObserver());
    }

    @Override
    public void handleError(Throwable e) {
        Log.d("Analysis", "Error: " + e.getMessage());
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

    }

    public void setRole(int role) {
        ROLE = role;
    }

    @Override
    public void LoadStatDetails(int statId, long summonerId, PlayerAnalysisCardViewContract cardViewContract) {
        Observer<StatCollection> statCollectionSubscriber = getStatCollectionObserver(cardViewContract);
        playerAnalysisPersistanceInteracter.LoadStatAnalysisCardObjects(ROLE, summonerId, statId, statCollectionSubscriber);
    }


    /**
     * Load the details that we see before opening the card.
     * @param id
     */
    public void LoadFaceDetails(int id) {

    }

    private Observer<StatCollection> getStatCollectionObserver(PlayerAnalysisCardViewContract cardViewContract) {
        return new Observer<StatCollection>() {

            @Override
            public void onError(Throwable e) {
                // do error
                Log.d("Analysis", "Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatCollection statData) {
                cardViewContract.setStatName(statData.getStatSummary().getStatName());
                cardViewContract.setStatSubTitle(statData.getStatSummary().getSubName());
                cardViewContract.setImprovementValue(statData.getStatSummary().getImprovementValue());
                //cardViewContract.setStatCollection(statData.getCollection());
                cardViewContract.setStatIcon("NOTDONEYET");
                cardViewContract.setStatCollection(statData.getCollection(),statData.getEnemyCollection() );
            }
        };
    }

    // TODO fix thius shit
    private Observer<StatDefinitionWrapper> getStatDefinitionObserver() {
        PlayerAnalysisPresenter thisPresenter = this;
        return new Observer<StatDefinitionWrapper>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatDefinitionWrapper value) {
                // set adapter
                PlayerAnalysisAdapter playerAnalysisAdapter = new PlayerAnalysisAdapter(context, thisPresenter);
                playerAnalysisAdapter.setPlayerAnalysisAdapterData(value.getDefinitions());
                view.setAdapter(playerAnalysisAdapter);
            }

            @Override
            public void onError(Throwable e) {
                thisPresenter.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        };
    }



    @Override
    public void setView(TabContract.View view) {
        this.view = view;
    }

    @Override
    public void onStatCollectionLoaded(StatCollection collection) {

    }

    @Override
    public void onStatCollectionFailed(String message) {
        view.showMessage(message);
    }
}
