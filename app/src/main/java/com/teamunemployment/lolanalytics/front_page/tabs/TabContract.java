package com.teamunemployment.lolanalytics.front_page.tabs;

import com.teamunemployment.lolanalytics.ViewContract;
import com.teamunemployment.lolanalytics.data.model.Champ;

/**
 * @author Josiah Kendall
 */

public interface TabContract {

    interface View extends ViewContract {
        void setRole(int role);
        void setAdapter(TabAdapter adapter);
        void setLoadingVisible(boolean visible);
        void launchDetailsActivity(String statSummaryUrl);
        void refresh();
    }

    /**
     * We need a setValue() method for each of the tabs, because each of the tabs have a different value
     * package delivered to them from the server. // TODO rewrite value delivery to use a wrapper and one setValue() method on the contract.
     */
    interface TabAdapter {

    }
}
