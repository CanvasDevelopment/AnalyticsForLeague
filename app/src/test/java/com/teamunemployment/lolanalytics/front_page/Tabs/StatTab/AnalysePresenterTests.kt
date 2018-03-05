package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.*
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.StatCardView
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
        val arrayList = ArrayList<StatSummary>()
        arrayList.add(mock(StatSummary::class.java))
//        val statList = StatList(arrayList, 1)
        view = mock(AnalyseTabContract.View::class.java)
        interactor = AnalyseInteractor(retrofitFactory, network)
        analysePresenter.setView(view)
        analysePresenter.setStatList(arrayList)
        verify(view, times(1)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeDontSetAdapterWhenWeHaveNoData() {
        val arrayList = ArrayList<StatSummary>()
//        arrayList.add(mock(AnalysisStatUrls::class.java))
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.getRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setChamp(champ)
        analysePresenter.setStatList(arrayList)
        verify(view, times(0)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeSetCorrectPlaceholderMessageForRoleAndChamp() {
        val arrayList = ArrayList<StatSummary>()
//        arrayList.add(mock(AnalysisStatUrls::class.java))
//        val statList = StatList(arrayList, 0)
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.getRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setChamp(champ)
        analysePresenter.setRole(4)
        analysePresenter.setStatList(arrayList)
        verify(view, times(0)).setAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT with Vi"
        verify(view, times(1)).setPlaceHolderString(noResults)
    }

    @Test
    fun ensureThatWeSetCorrectPlaceHolderMessageWithRoleAndNoChamp() {
        val arrayList = ArrayList<StatSummary>()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.getRoleName(4)).thenReturn("SUPPORT")
        analysePresenter.setRole(4)
        analysePresenter.setStatList(arrayList)
        verify(view, times(0)).setAdapter(analyseAdapter)
        val noResults = "No games found playing SUPPORT"
        verify(view, times(1)).setPlaceHolderString(noResults)
    }


    @Test(expected = IllegalStateException::class)
    fun ensureThatIllegalStateExceptionGetsThrownIfWeTryToCreateAMessageWithoutARoleBeingSet() {
        val arrayList = ArrayList<StatSummary>()
        val champ = Champ()
        champ.champName = "Vi"
        `when`(roleUtils.getRoleName(6)).thenThrow(IllegalStateException())
        analysePresenter.setChamp(champ)
        analysePresenter.setRole(6)
        analysePresenter.setStatList(arrayList)
        verify(view, times(0)).setAdapter(analyseAdapter)
    }

    @Test
    fun ensureThatWeShowPlaceholderWhenWeSetPlaceHolderText() {
        analysePresenter.setPlaceHolder("hey there")
        verify(view, times(1)).setPlaceHolderVisible()
    }

    @Test
    fun ensureThatWeHidePlaceholderWhenWeSetTheAdapter() {
        val arrayList = ArrayList<StatSummary>()
        arrayList.add(produceRandomStatSummary())
        analysePresenter.setStatList(arrayList)
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
        verify<AnalyseInteractor>(interactor, times(1)).loadStatList(4, analysePresenter, "OCE", -1)
    }

    @Test
    fun ensureThatWeFetchForChampAndRoleIfWeHaveAChampFilterSet() {
        analysePresenter.setRole(1)
        val champ = mock(Champ::class.java)
        `when`(champ.champId).thenReturn(31)
        analysePresenter.setChamp(champ)
        analysePresenter.start()
        verify<AnalyseInteractor>(interactor, times(1)).loadStatList(1, 31, analysePresenter, "OCE")
    }

    // todo
    @Test
    fun testThatWeSetTitleTextCorrectly() {

        // we want to render a title when we first render the view
        val change = -12.1f
        val hero = 45.8f
        val enemy = 54.2f
        val title = "Early Game"

        val datas = ArrayList<StatSummary>()
        val statSummary = StatSummary(0,"card", "detail", title)
        datas.add(statSummary)
        analysePresenter.setStatList(datas)
        val viewHolder = mock(StatCardView::class.java)
        analysePresenter.onCardBinding(viewHolder, 0)
        verify(viewHolder, times(1)).setTitle(title)
    }

    @Test
    fun `Test that we can set placeholder when champ is null` () {
        val arrayList = ArrayList<StatSummary>()
        analysePresenter.setStatList(arrayList)
    }

    @Test
    fun `Make sure that we set up the correct view for the given view type`() {

    }

    fun produceRandomStatUrl() : StatSummary {

        return StatSummary(random.nextInt(), random.nextInt().toString(), "title", "title")
    }

    fun produceRandomCardData() : FullStatCard {
        return FullStatCard(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat())
    }

    private fun produceRandomStatSummary(): StatSummary {
        return StatSummary(random.nextInt(),random.nextInt().toString(), random.nextInt().toString(), random.nextInt().toString())
    }

    @Test
    fun `Ensure That We Set Position To ViewHolder`() {
        val datas = ArrayList<StatSummary>()
        datas.add(mock(StatSummary::class.java))
        datas.add(mock(StatSummary::class.java))
        datas.add(mock(StatSummary::class.java))
        analysePresenter.setStatList(datas)
        val viewHolder = mock(StatCardView::class.java)
        analysePresenter.onCardBinding(viewHolder, 2)
        verify(viewHolder, times(1)).setCardPosition(2)
    }


    @Test
    fun ensureThatWeCanHandleClicksInPresenter() {
        //        analysePresenter.handleItemClick(3);
    }

    @Test
    fun `Make sure that the presenter throws illegal state exception if the position is -1`() {

    }
}
