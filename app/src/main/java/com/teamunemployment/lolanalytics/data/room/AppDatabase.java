package com.teamunemployment.lolanalytics.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.teamunemployment.lolanalytics.data.HeadToHeadStatConverter;
import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.data.model.SimpleChamp;
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao;
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao;
import com.teamunemployment.lolanalytics.data.room.champ.SimpleChampDao;
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner;
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao;
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier;

@Database(entities = {
        Summoner.class,
        MatchIdentifier.class,
        MatchHistoryCardData.class,
        Champ.class,
        SimpleChamp.class}, version = 6)
@TypeConverters(HeadToHeadStatConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SummonerDao summonerDao();
    public abstract MatchHistoryDao matchHistoryDao();
    public abstract ChampDao champDao();
    public abstract SimpleChampDao simpleChampDao();
}
