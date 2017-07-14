package com.teamunemployment.lolanalytics.FrontPage;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.R;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
public class BaseActivityView extends AppCompatActivity implements BaseActivityContract.View{

    private TabAdapter tabAdapter;

    @Inject
    public BaseActivityPresenter presenter;

    @Bind(R.id.bottomBar) AHBottomNavigation bottomBar;
    @Bind(R.id.win_rate_details) TextView winRateTextView;
    @Bind(R.id.user_name) TextView userNameTextView;
    @Bind(R.id.role_icon) CircleImageView roleIcon;
    @Bind(R.id.collapsable_toolbar_holder) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.container) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

        ((App) getApplication()).getNetComponent().InjectView(this);

        ButterKnife.bind(this);
        presenter.setView(this);

        // Defaults
        setRoleName("Top");
        setTabIcon(R.drawable.top);

        setUpBottomBar();
        setUpTabs();
    }

    /**
     * Set up colors and buttons and click handlers. Somewhat long method but there is no real logic.
     */
    private void setUpBottomBar() {

        // Bottom bar appearance.
        bottomBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bottomBar.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomBar.setInactiveColor(ContextCompat.getColor(this, R.color.bluegrey));
        bottomBar.setBehaviorTranslationEnabled(true);
        presenter.setUpBottomBar(bottomBar);
    }

    private void setUpTabs() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCorrectTabFragment(int tab) {

        // Rather than set correct tab, this shold probably be set correct role.
        // currentTab.setRole(role)
        tabAdapter.setRole(tab);
    }

    @Override
    public void setWinRate(double winRate) {
        String winRateString = winRate + "%";
        winRateTextView.setText(winRateString);
    }

    @Override
    public void setTabIcon(int icon) {
        int color = Color.parseColor("#ffffff");
        roleIcon.setColorFilter(color);
        roleIcon.setImageResource(icon);
    }

    @Override
    public void setRoleName(String string) {
        // Not doin this atm
        //collapsingToolbar.setTitle(string);
    }

}
