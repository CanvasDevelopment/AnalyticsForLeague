package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.*
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory

import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.*

/**
 * Created by Josiah Kendall
 */

class AnalysePresenterTests {

    private val random = Random()

    private lateinit var view: AnalyseTabContract.View
    private lateinit var analyseAdapter: AnalyseAdapter
    private lateinit var interactor: AnalyseInteractor
    private lateinit var analysePresenter: AnalysePresenter
    private lateinit var roleUtils: RoleUtils
    private val network = mock(Network::class.java)
    private val retrofitFactory = mock(RetrofitFactory::class.java)

    /**
     * General flow of the presenter:
     * START
     * set the presenter to our adapter.
     * ensure that our view is set
     * getMatchIds our current role, and champ if set
     * request value
     *
     * HANDLE DATA RESPONSE
     * Set the value to the value list.
     * Set our adapter to the view
     * Notify the adapter that value has changed.
     * If we have no items in our datalist, set the placeholder that says -no value in role.
     * if we have a champ set make sure we add that to our string.
     * if we dont have a champ set make sure that we dont add that to our string.
     *
     * UPDATE ROLE
     * We need to be able to update the role of a user, and refetch the value.
     *
     * UPDATE CHAMP
     * We need to be able to update the role of a champ, and refeetch the value.
     *
     * HANDLE CARD BINDING
     * The presenter needs to handle the card binding - in other words, it recieves the
     * view holder, and sets the appropriate parameters to it.
     *
     * HANDLE CARD CLICK
     * The card needs to be able to redirect clicks on itself to the presenter, and the presenter
     * needs to then open the correct
     */


    @Before
    fun setUpPresenter() {
        roleUtils = mock(RoleUtils::class.java)
        analyseAdapter = mock(AnalyseAdapter::class.java)
        view = mock(AnalyseTabContract.View::class.java)

        interactor = mock(AnalyseInteractor::class.java)
        analysePresenter = AnalysePresenter(interactor, analyseAdapter, roleUtils)
        analysePresenter.setView(view)
    }

    @Test
    fun ensureThatWeSetTheAdapterAndTriggerDisplayWhenWeGetDataReturned() {
        val arrayList = ArrayList<AnalysisStatUrls>()
        arrayList.add(mock(AnalysisStatUrls::class.java))
        val statList = StatList(arrayList, 1)
        view = mock(AnalyseTabContract.View::class.java)
        interactor = AnalyseInteractor(retrofitFactory, network)
        analysePresenter.setView(view)
        analysePresenter.setStatList(statList)
        verify(view, times(1)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeDontSetAdapterWhenWeHaveNoData() {
        val arrayList = ArrayList<AnalysisStatUrls>()
//        arrayList.add(mock(AnalysisStatUrls::class.java))
        val statList = StatList(arrayList, 0)
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setChamp(champ)
        analysePresenter.setStatList(statList)
        verify(view, times(0)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeSetCorrectPlaceholderMessageForRoleAndChamp() {
        val arrayList = ArrayList<AnalysisStatUrls>()
//        arrayList.add(mock(AnalysisStatUrls::class.java))
        val statList = StatList(arrayList, 0)
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setChamp(champ)
        analysePresenter.setRole(4)
        analysePresenter.setStatList(statList)
        verify(view, times(0)).setAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT with Vi"
        verify(view, times(1)).setPlaceHolderString(noResults)
    }

    @Test
    fun ensureThatWeSetCorrectPlaceHolderMessageWithRoleAndNoChamp() {
        val arrayList = ArrayList<AnalysisStatUrls>()
        val statList = StatList(arrayList, 0)
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setRole(4)
        analysePresenter.setStatList(statList)
        verify(view, times(0)).setAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT"
        verify(view, times(1)).setPlaceHolderString(noResults)
    }


    @Test(expected = IllegalStateException::class)
    fun ensureThatIllegalStateExceptionGetsThrownIfWeTryToCreateAMessageWithoutARoleBeingSet() {
        val arrayList = ArrayList<AnalysisStatUrls>()
        val statList = StatList(arrayList, 0)
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.GetRoleName(6)).thenThrow(IllegalStateException())
        analysePresenter.setChamp(champ)
        analysePresenter.setRole(6)
        analysePresenter.setStatList(statList)
        verify(view, times(0)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeShowPlaceholderWhenWeSetPlaceHolderText() {
        analysePresenter.setPlaceHolder("hey there")
        verify(view, times(1)).setPlaceHolderVisible()
    }

    @Test
    fun ensureThatWeHidePlaceholderWhenWeSetTheAdapter() {
        val arrayList = ArrayList<AnalysisStatUrls>()
        val statList = StatList(arrayList, 1)
        analysePresenter.setStatList(statList)
        verify(view, times(1)).setPlaceHolderInvisible()
    }

    @Test
    fun ensureThatWeSetThePresenterToTheAdapterWhenWeStart() {
        analysePresenter.setRole(1)
        analysePresenter.start()
        verify<AnalyseAdapter>(analyseAdapter, times(1)).setPresenter(analysePresenter)
    }

    @Test(expected = IllegalStateException::class)
    fun ensureWeThrowIllegalStateExceptionIfRoleIsNotSet() {
        analysePresenter.start()
    }

    @Test
    fun ensureWeOnlyRequestRoleFilterIfWeHaveNoChampSet() {
        analysePresenter.setRole(4)
        analysePresenter.start()
        verify<AnalyseInteractor>(interactor, times(1)).RequestFilterList(4, analysePresenter)
    }

    @Test
    fun ensureThatWeFetchForChampAndRoleIfWeHaveAChampFilterSet() {
        analysePresenter.setRole(1)
        val champ = mock(Champ::class.java)
        `when`(champ.champId).thenReturn(31)
        analysePresenter.setChamp(champ)
        analysePresenter.start()
        verify<AnalyseInteractor>(interactor, times(1)).RequestFilterList(1, 31, analysePresenter)
    }

    // todo
    @Test
    fun testThatWeSetTitleTextCorrectly() {

        // we want to render a title when we first render the view
        val change = -12.1f
        val hero = 45.8f
        val enemy = 54.2f
        val title = "Early Game"

        val datas = ArrayList<AnalysisStatUrls>()
        val arrayList = ArrayList<AnalysisStatUrls>()
        val analysisData = AnalysisData()
//        datas.add(analysisData)
        val statList = StatList(datas, 0)

        analysePresenter.setStatList(statList)
        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter.onCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).setTitle(title)
    }

    // todo
    @Test
    fun `Test that we set graph correctly when we bind`() {
        val cardData = produceRandomCardData()

        val datas = ArrayList<AnalysisStatUrls>()
        val statUrl = produceRandomAnalysisUrl()
        datas.add(statUrl)
        val statList = StatList(datas,20)
        val viewHolder = mock(AnalyseTabContract.CardView::class.java)

        `when`(interactor.loadIndividualStat(statUrl.card,-1,viewHolder,analysePresenter))
        analysePresenter.setStatList(statList)

        analysePresenter.onCardBinding(viewHolder, 0)
//        verify(viewHolder, times(1)).SetGraph(enemy, hero)

    }

    fun produceRandomAnalysisUrl() : AnalysisStatUrls {
        return AnalysisStatUrls(random.nextInt().toString(), random.nextInt().toString(), "title")
    }

    fun produceRandomCardData() : StatCard{
        return StatCard(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat())
    }
    @Test
    fun testThatWeSetChangeCorrectly() {
        val change = -12.1f
        val hero = 45.8f
        val enemy = 54.2f
        val title = "Early Game"

        val datas = ArrayList<AnalysisData>()
        val analysisData = AnalysisData()
        analysisData.enemyPercentTotal = enemy
        analysisData.heroPercentTotal = hero
        analysisData.title = title
        analysisData.recentChange = change.toDouble()
        datas.add(analysisData)
//        analysePresenter!!.setStatList(datas)

        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter.onCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).setChange(change.toDouble())
    }

    @Test
    fun ensureThatWeSetPositionToViewHolder() {
        val datas = ArrayList<AnalysisData>()
        datas.add(mock(AnalysisData::class.java))
        datas.add(mock(AnalysisData::class.java))
        datas.add(mock(AnalysisData::class.java))
//        analysePresenter!!.setStatList(datas)
        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter.onCardBinding(viewHolder, 2)
//        verify(viewHolder, times(1)).SetItemPosition(2)
    }


    @Test
    fun ensureThatWeCanHandleClicksInPresenter() {
        //        analysePresenter.handleItemClick(3);
    }
}
