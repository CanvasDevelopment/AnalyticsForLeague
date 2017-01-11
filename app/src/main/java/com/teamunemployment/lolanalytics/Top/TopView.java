package com.teamunemployment.lolanalytics.Top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Base.Statics;
import com.teamunemployment.lolanalytics.Base.ViewFragmentContract;
import com.teamunemployment.lolanalytics.DependencyInjection.App;
import com.teamunemployment.lolanalytics.Jungle.ViewFragment;
import com.teamunemployment.lolanalytics.R;

import butterknife.ButterKnife;

/**
 * @author Josiah Kendall.
 *
 * Top view. Not yet implemented. May just re-use a base fragment from the jungle view.
 */
public class TopView extends ViewFragment implements ViewFragmentContract {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.jungle_fragment_view, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectJungleView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        // Give a reference of this view to our presenter. Done for presenting information or creating
        // instances of objects must be created on the ui thread.

        presenter.setView(this, Statics.MID);
        return rootView;
    }

}
