package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalyseCardViewHolder
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisStatUrls
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory

import org.junit.Before
import org.junit.Test

import java.util.ArrayList

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Created by Josiah Kendall
 */

class AnalysePresenterTests {

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
     * get our current role, and champ if set
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
        analysePresenter = AnalysePresenter(interactor!!, analyseAdapter!!, roleUtils!!)
        analysePresenter!!.SetView(view)
    }

    @Test
    fun ensureThatWeSetTheAdapterAndTriggerDisplayWhenWeGetDataReturned() {
        val arrayList = ArrayList<AnalysisStatUrls>()
        arrayList.add(mock(AnalysisStatUrls::class.java))
        val statList = StatList(arrayList, 1)
        view = mock(AnalyseTabContract.View::class.java)
        interactor = AnalyseInteractor(retrofitFactory, network)
        analysePresenter.SetView(view)
        analysePresenter.setStatList(statList)
        verify(view, times(1)).SetAdapter(analyseAdapter)
    }


    @Test
    fun EnsureThatWeDontSetAdapterWhenWeHaveNoData() {
        val arrayList = ArrayList()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.SetChamp(champ)
        analysePresenter.setStatList(arrayList)
        verify(view, times(0)).SetAdapter(analyseAdapter)
    }

    @Test
    fun EnsureThatWeSetCorrectPlaceholderMessageForRoleAndChamp() {
        val arrayList = ArrayList()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils!!.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter!!.SetChamp(champ)
        analysePresenter!!.SetRole(4)
        analysePresenter!!.setStatList(arrayList)
        verify<View>(view, times(0)).SetAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT with Vi"
        verify<View>(view, times(1)).SetPlaceHolderString(noResults)
    }

    @Test
    fun EnsureThatWeSetCorrectPlaceHolderMessageWithRoleAndNoChamp() {
        val arrayList = ArrayList()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils!!.GetRoleName(4)).thenReturn("SUPPORT")
        analysePresenter!!.SetChamp(null!!)
        analysePresenter!!.SetRole(4)
        analysePresenter!!.setStatList(arrayList)
        verify<View>(view, times(0)).SetAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT"
        verify<View>(view, times(1)).SetPlaceHolderString(noResults)
    }


    @Test(expected = IllegalStateException::class)
    fun EnsureThatIllegalStateExceptionGetsThrownIfWeTryToCreateAMessageWithoutARoleBeingSet() {
        val arrayList = ArrayList()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils!!.GetRoleName(6)).thenThrow(IllegalStateException())
        analysePresenter!!.SetChamp(champ)
        analysePresenter!!.SetRole(6)
        analysePresenter!!.setStatList(arrayList)
        verify<View>(view, times(0)).SetAdapter(analyseAdapter)
        val noResults = "No games found playing TOP with Vi"
    }

    @Test
    fun EnsureThatWeShowPlaceholderWhenWeSetPlaceHolderText() {
        analysePresenter!!.setPlaceHolder("hey there")
        verify<View>(view, times(1)).SetPlaceHolderVisible()
    }

    @Test
    fun EnsureThatWeHidePlaceholderWhenWeSetTheAdapter() {
        val analysisData = ArrayList<AnalysisData>()
        analysisData.add(mock(AnalysisData::class.java))
        analysePresenter!!.setStatList(analysisData)
        verify<View>(view, times(1)).SetPlaceHolderInvisible()
    }

    @Test
    fun EnsureThatWeSetThePresenterToTheAdpaterWhenWeStart() {
        analysePresenter!!.SetRole(1)
        analysePresenter!!.Start()
        verify<AnalyseAdapter>(analyseAdapter, times(1)).SetPresenter(analysePresenter)
    }

    @Test(expected = IllegalStateException::class)
    fun EnsureWeThrowIllegalStateExceptionIfRoleIsNotSet() {
        analysePresenter!!.Start()
    }

    @Test
    fun EnsureWeOnlyRequestRoleFilterIfWeHaveNoChampSet() {
        analysePresenter!!.SetRole(4)
        analysePresenter!!.Start()
        verify<AnalyseInteractor>(interactor, times(1)).RequestFilterList(4, analysePresenter!!)
    }

    @Test
    fun EnsureThatWeFetchForChampAndRoleIfWeHaveAChampFilterSet() {
        analysePresenter!!.SetRole(1)
        val champ = mock(Champ::class.java)
        `when`(champ.champId).thenReturn(31)
        analysePresenter!!.SetChamp(champ)
        analysePresenter!!.Start()
        verify<AnalyseInteractor>(interactor, times(1)).RequestFilterList(1, 31, analysePresenter!!)
    }

    @Test
    fun TestThatWeSetTitleTextCorrectly() {
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
        analysePresenter!!.setStatList(datas)
        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter!!.OnCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).SetTitle(title)
    }

    @Test
    fun TestThatWeSetGraphCorrectlyWhenWeBind() {
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
        analysePresenter!!.setStatList(datas)

        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter!!.OnCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).SetGraph(enemy, hero)

    }

    @Test
    fun TestThatWeSetChangeCorrectly() {
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
        analysePresenter!!.setStatList(datas)

        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter!!.OnCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).SetChange(change.toDouble())

    }

    @Test
    fun EnsureThatWeSetPositionToViewHolder() {
        val datas = ArrayList<AnalysisData>()
        datas.add(mock(AnalysisData::class.java))
        datas.add(mock(AnalysisData::class.java))
        datas.add(mock(AnalysisData::class.java))
        analysePresenter!!.setStatList(datas)
        val viewHolder = mock(AnalyseCardViewHolder::class.java)
        analysePresenter!!.OnCardBinding(viewHolder, 2)
        verify(viewHolder, times(1)).SetItemPosition(2)

    }


    @Test
    fun EnsureThatWeCanHandleClicksInPresenter() {
        //        analysePresenter.HandleItemClick(3);
    }
}
