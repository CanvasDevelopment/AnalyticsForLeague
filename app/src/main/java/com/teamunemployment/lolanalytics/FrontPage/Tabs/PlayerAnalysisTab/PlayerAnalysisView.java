package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;

/**
 * @author Josiah Kendall
 */
public class PlayerAnalysisView extends Fragment implements PlayerAnalysisContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setStatCollectionData(StatCollection statCollection) {

    }
}
