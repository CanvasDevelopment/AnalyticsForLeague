package com.teamunemployment.lolanalytics.FrontPage.Search.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchPresenter;
import com.teamunemployment.lolanalytics.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by Josiah Kendall
 */

public class ChampSearchCardView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SearchPresenter searchPresenter;
    private Champ champ;
    private Context context;

    @Bind(R.id.champ_circle_icon) CircularImageView champImage;

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
        // set champ image with url
        Picasso.with(context).load(champ.getChampId()).into(champImage);
    }

    @Override
    public void onClick(View view) {
        //searchPresenter.
        searchPresenter.HandleChampClick(champ);
    }
}
