package com.teamunemployment.lolanalytics.front_page.champ_menu

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.OldChamp
import com.teamunemployment.lolanalytics.data.model.ChampFrequency
import com.teamunemployment.lolanalytics.front_page.champ_menu.Model.ChampSearchCardView
import com.teamunemployment.lolanalytics.front_page.champ_menu.Model.OffSetViewHolder
import junit.framework.Assert.assertTrue

import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.util.*

/**
 * Created by Josiah Kendall
 */

class SearchCardViewTests {

    private val id = 121
    private val games = 21
    val summonerId = "21"
    val random = Random()
    val champ = createChamp()

    private fun createChamp() :Champ{
        return Champ(random.nextInt().toString(),
                id.toString(),
                id.toString(),
                random.toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                games,
                summonerId)
    }

    private fun createChamp(title : String) :Champ{
        return Champ(random.nextInt().toString(),
                id.toString(),
                id.toString(),
                random.toString(),
                title,
                random.nextInt().toString(),
                games,
                "summonerId")
    }

    @Test
    fun EnsureThatWeSetImage() {
        val champUrl = "fhdskfgdlsk"
        val champMenuPresenter = mock(ChampMenuPresenter::class.java)
        val champMenuListAdapter = ChampMenuListAdapter(champMenuPresenter)
        val arrayList = ArrayList<Champ>()
        arrayList.add(champ)
        champMenuListAdapter.setData(arrayList)
        val searchCardView = mock(ChampSearchCardView::class.java)
        champMenuListAdapter.onBindViewHolder(searchCardView, 0)
        verify(searchCardView, times(1)).setChamp(champ)
    }

    @Test(expected = IllegalStateException::class)
    fun EnsureThatWeThrowExceptionIfWeLoadWithoutSettingData() {
        val champUrl = "fhdskfgdlsk"
        val champMenuPresenter = mock(ChampMenuPresenter::class.java)
        val champMenuListAdapter = ChampMenuListAdapter(champMenuPresenter)
        val arrayList = ArrayList<OldChamp>()
        val searchCardView = mock(ChampSearchCardView::class.java)
        champMenuListAdapter.onBindViewHolder(searchCardView, 0)
    }

    @Test // Fails because layout inflater is not mocked
    fun EnsureWeOffSetChampSearchScrollSlightly() {
        //        ChampMenuPresenter champMenuPresenter = mock(ChampMenuPresenter.class);
        //        SearchListAdapter searchListAdapter = new SearchListAdapter(champMenuPresenter);
        //        RecyclerView.ViewHolder viewHolder = searchListAdapter.onCreateViewHolder(mock(ViewGroup.class), Constant.ViewType.OFFSET_VIEW);
        //        Assert.assertTrue(viewHolder instanceof OffSetViewHolder);
        // TODO
    }

    @Test
    fun EnsureWeDontSetChampToBlankItem() {
        val champMenuPresenter = mock(ChampMenuPresenter::class.java)
        val champMenuListAdapter = ChampMenuListAdapter(champMenuPresenter)
        champMenuListAdapter.onBindViewHolder(mock(OffSetViewHolder::class.java), 0)

    }

    @Test
    fun testThatWeCanFilterChamps() {
        val champMenuPresenter = mock(ChampMenuPresenter::class.java)
        val champMenuListAdapter = ChampMenuListAdapter(champMenuPresenter)
        val arrayList = ArrayList<Champ>()
        arrayList.add(createChamp("shyvana"))
        arrayList.add(createChamp("vi"))
        arrayList.add(createChamp("hercules"))
        arrayList.add(createChamp("hero_guy"))
        arrayList.add(createChamp("dogmatix"))
        champMenuListAdapter.setData(arrayList)
        val array = champMenuListAdapter.getChamps()
        assertTrue(array.size == 5)
        champMenuListAdapter.filter("her")
        val array2 = champMenuListAdapter.getChamps()
        assertTrue(array2.size == 2)
    }

    @Test
    fun testThatWeCanFilterChampsWithCaps() {
        val champMenuPresenter = mock(ChampMenuPresenter::class.java)
        val champMenuListAdapter = ChampMenuListAdapter(champMenuPresenter)
        val arrayList = ArrayList<Champ>()
        arrayList.add(createChamp("Shyvana"))
        arrayList.add(createChamp("Vi"))
        arrayList.add(createChamp("Hercules"))
        arrayList.add(createChamp("Hero_guy"))
        arrayList.add(createChamp("Dogmatix"))
        champMenuListAdapter.setData(arrayList)
        val array = champMenuListAdapter.getChamps()
        assertTrue(array.size == 5)
        champMenuListAdapter.filter("her")
        val array2 = champMenuListAdapter.getChamps()
        assertTrue(array2.size == 2)
    }


}
