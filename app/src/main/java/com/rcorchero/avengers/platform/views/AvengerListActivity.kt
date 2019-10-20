package com.rcorchero.avengers.platform.views

import android.os.Bundle
import com.rcorchero.avengers.R
import com.rcorchero.avengers.presentation.AvengerListView
import com.rcorchero.avengers.presentation.presenters.AvengerListPresenter
import kotlinx.android.synthetic.main.activity_avenger_list.*
import javax.inject.Inject
import androidx.recyclerview.widget.GridLayoutManager

class AvengerListActivity : BaseActivity(), AvengerListView {

    @Inject
    lateinit var presenter: AvengerListPresenter

    private lateinit var adapter: AvengersAdapter

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        setUpActionBar()
        setUpListView()
        setUpRefreshView()

        presenter.attachView(this)
    }

    override fun getActivityLayout(): Int = R.layout.activity_avenger_list

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
    }

    private fun setUpListView() {
        adapter = AvengersAdapter(presenter)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun setUpRefreshView() {
        refreshLayout.setOnRefreshListener { presenter.refresh() }
    }

    override fun refreshList() {
        adapter.refreshData()
    }

    override fun cancelRefreshDialog() {
        refreshLayout.isRefreshing = false
    }

    override fun navigateToDetailScreen(avengerName: String) {
        navigator.navigateToDetail(this, avengerName)
    }
}