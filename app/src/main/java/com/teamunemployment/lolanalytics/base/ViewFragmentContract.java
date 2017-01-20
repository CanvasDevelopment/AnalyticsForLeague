package com.teamunemployment.lolanalytics.Base;

/**
 * @author Josiah Kendall.
 */
public interface ViewFragmentContract {

    void setJungleAdapter(BaseRecyclerAdapter baseRecyclerAdapter);
    void showMessage(String s);
}
