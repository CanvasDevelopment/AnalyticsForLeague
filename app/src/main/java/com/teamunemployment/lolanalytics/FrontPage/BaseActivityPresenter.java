package com.teamunemployment.lolanalytics.FrontPage;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.ncapdevi.fragnav.FragNavController;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.ViewContract;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */

public class BaseActivityPresenter implements BaseActivityContract.Presenter{

    private BaseActivityView view;
    private static final int TOP = 0;
    private static final int JUNGLE = 1;
    private static final int MID = 2;
    private static final int MARKSMAN = 3;
    private static final int SUPPORT = 4;

    @Inject
    public BaseActivityModel baseActivityModel;

    public BaseActivityPresenter(BaseActivityModel baseActivityModel) {
        this.baseActivityModel = baseActivityModel;
    }
    public void setView(BaseActivityView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void handleError(Throwable e) {

    }


    @Override
    public void handleTabPress(int tab) {

        long summonerId = -1; // TODO
        view.setCorrectTabFragment(tab);
        switch (tab) {
            case TOP:
                baseActivityModel.fetchWinRateForRole(-1,"TOP",this);
                view.setRoleName("Top");
                view.setTabIcon(R.drawable.top);
                break;
            case JUNGLE:
                baseActivityModel.fetchWinRateForRole(-1,"JUNGLE",this);
                view.setRoleName("Jungle");
                view.setTabIcon(R.drawable.jg);
                break;
            case MID:
                baseActivityModel.fetchWinRateForRole(-1,"MID",this);
                view.setRoleName("Mid");
                view.setTabIcon(R.drawable.mid);
                break;
            case MARKSMAN:
                baseActivityModel.fetchWinRateForRole(-1,"MARKSMAN",this);
                view.setRoleName("Marksman");
                view.setTabIcon(R.drawable.bot);
                break;
            case SUPPORT:
                baseActivityModel.fetchWinRateForRole(-1,"PLAYMAKER",this);
                view.setRoleName("Support");
                view.setTabIcon(R.drawable.sup);
                break;
        }
    }

    @Override
    public void onWinRateLoaded(double winRate) {
        view.setWinRate(winRate);
    }


}
