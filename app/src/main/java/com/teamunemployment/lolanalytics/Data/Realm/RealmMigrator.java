package com.teamunemployment.lolanalytics.Data.Realm;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * @author Josiah Kendall
 *
 * Handles realm migrations.
 */

public class RealmMigrator implements RealmMigration{

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();

        if (oldVersion == 0) {
            realmSchema.create("MatchSummary")
                    .addField("id", long.class, FieldAttribute.PRIMARY_KEY)
                    .addField("value", double.class)
                    .addField("summonerId", long.class)
                    .addField("role", int.class);

            oldVersion += 1;
        }

        // Not sure what happened to 1
        if (oldVersion ==2) {
            realmSchema.create("MatchHistoryCardData")
                    .addField("matchId", long.class, FieldAttribute.PRIMARY_KEY)
                    .addField("champId", long.class)
                    .addField("champName", String.class)
                    .addField("kills", CardData.class)
                    .addField("deaths", CardData.class)
                    .addField("csFirstTen",CardData.class)
                    .addField("csSecondTen", CardData.class)
                    .addField("csTotal", CardData.class);

        }
    }
}
