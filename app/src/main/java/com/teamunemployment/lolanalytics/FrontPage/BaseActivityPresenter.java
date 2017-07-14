package com.teamunemployment.lolanalytics.FrontPage;

import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ncapdevi.fragnav.FragNavController;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityView view;
    private static final int TOP = 0;
    private static final int JUNGLE = 1;
    private static final int MID = 2;
    private static final int MARKSMAN = 3;
    private static final int SUPPORT = 4;

    private BaseActivityPersistenceInteractor baseActivityPersistenceInteractor;

    private AHBottomNavigation bottomBar;

    @Inject
    public BaseActivityPresenter(BaseActivityPersistenceInteractor baseActivityPersistenceInteractor) {
        this.baseActivityPersistenceInteractor = baseActivityPersistenceInteractor;
    }

    public void setView(BaseActivityView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void handleError(Throwable e) {
        Log.e("BaseActivityPresenter", "Error : " + e.getMessage());
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
    public void handleTabPress(int tab) {

        long summonerId = -1; // TODO
        switch (tab) {
            case TOP:
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1,"TOP",this);
                view.setRoleName("Top");
                view.setTabIcon(R.drawable.top);
                view.setCorrectTabFragment(FragNavController.TAB1);
                break;
            case JUNGLE:
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1,"JUNGLE",this);
                view.setRoleName("Jungle");
                view.setTabIcon(R.drawable.jg);
                view.setCorrectTabFragment(FragNavController.TAB2);
                break;
            case MID:
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1,"MID",this);
                view.setRoleName("Mid");
                view.setTabIcon(R.drawable.mid);
                view.setCorrectTabFragment(FragNavController.TAB3);
                break;
            case MARKSMAN:
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1,"MARKSMAN",this);
                view.setRoleName("Marksman");
                view.setTabIcon(R.drawable.bot);
                view.setCorrectTabFragment(FragNavController.TAB4);
                break;
            case SUPPORT:
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1,"PLAYMAKER",this);
                view.setRoleName("Support");
                view.setTabIcon(R.drawable.sup);
                view.setCorrectTabFragment(FragNavController.TAB5);
                break;
        }
    }

    @Override
    public void onWinRateLoaded(double winRate) {
        view.setWinRate(winRate);
    }

    @Override
    public void setUpTabFragments() {
        // Do fragment setup

    }

    @Override
    public void setUpBottomBar(AHBottomNavigation bottomBar) {
        this.bottomBar = bottomBar;


        // Bottom bar items.
        AHBottomNavigationItem topItem = new AHBottomNavigationItem("Top", R.drawable.top);
        AHBottomNavigationItem jgItem = new AHBottomNavigationItem("Jungle", R.drawable.jg);
        AHBottomNavigationItem midItem = new AHBottomNavigationItem("Mid", R.drawable.mid);
        AHBottomNavigationItem adcItem = new AHBottomNavigationItem("Bottom", R.drawable.bot);
        AHBottomNavigationItem supItem = new AHBottomNavigationItem("Support", R.drawable.sup);

        // Add items to bottom bar.
        bottomBar.addItem(topItem);
        bottomBar.addItem(jgItem);
        bottomBar.addItem(midItem);
        bottomBar.addItem(adcItem);
        bottomBar.addItem(supItem);


        // Click listener for the bottom bar buttons.
        bottomBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                handleTabPress(position);
                return true;
            }
        });
    }

}
