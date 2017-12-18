package com.teamunemployment.lolanalytics.front_page.Search.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.front_page.Search.SearchPresenter;
import com.teamunemployment.lolanalytics.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by Josiah Kendall
 */

public class ChampSearchCardView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SearchPresenter searchPresenter;
    private Champ champ;
    private Context context;

    @BindView(R.id.champ_circle_icon) CircularImageView champImage;

    public ChampSearchCardView(View itemView, SearchPresenter searchPresenter, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.searchPresenter = searchPresenter;
        this.context = context;
    }

    /**
     * Set the champ that this card/item relates to.
     * @param champ
     */
    public void SetChamp(Champ champ) {
        this.champ = champ;
        if (champ.getChampUrl().isEmpty()) {
            // set our clear filter
            champImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
            return;
        }
        Picasso.with(context).load(champ.getChampId()).into(champImage);
    }

    @Override
    public void onClick(View view) {
        //searchPresenter.
        searchPresenter.HandleChampClick(champ);
    }
}