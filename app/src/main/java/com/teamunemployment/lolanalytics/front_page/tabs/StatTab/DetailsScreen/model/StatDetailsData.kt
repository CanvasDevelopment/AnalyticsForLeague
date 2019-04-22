package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

class StatDetailsData(val statHistory : ArrayList<HeadToHeadStat>,
                      val statMax : Float,
                      val statMin : Float,
                      val statAvg : Float)