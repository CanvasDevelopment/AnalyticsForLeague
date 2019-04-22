package com.teamunemployment.lolanalytics.front_page.champ_menu

import com.teamunemployment.lolanalytics.data.model.Champ

interface ChampLoadResponse {

    fun success(champs: List<Champ>)
    fun failure()
}