package com.teamunemployment.lolanalytics.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.teamunemployment.lolanalytics.DependencyInjection.App;

/**
 * @author Josiah Kendall.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getNetComponent().InjectBaseView(this);


    }
}
