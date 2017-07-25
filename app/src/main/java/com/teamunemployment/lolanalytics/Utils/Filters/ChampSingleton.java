package com.teamunemployment.lolanalytics.Utils.Filters;

import com.teamunemployment.lolanalytics.Data.model.Champ;

import javax.inject.Singleton;

/**
 * Created by Josiah Kendall
 *
 * This is a singleton used to control the current champ set in our champ filter. This is used to
 * convey the same information across multiple fragments in our main screen activity.
 */

@Singleton
public class ChampSingleton {
    private Champ currentChamp;

    public Champ getCurrentChamp() {
        return currentChamp;
    }

    public void setCurrentChamp(Champ currentChamp) {
        this.currentChamp = currentChamp;
    }

}
