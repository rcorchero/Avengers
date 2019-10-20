package com.rcorchero.avengers.platform.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.rcorchero.avengers.R
import com.rcorchero.avengers.presentation.DetailAvengerView
import com.rcorchero.avengers.presentation.presenters.DetailAvengerPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_avenger.*
import kotlinx.android.synthetic.main.content_detail_avenger.*
import javax.inject.Inject

class DetailAvengerActivity : BaseActivity(), DetailAvengerView {

    companion object {
        private const val EXTRA_AVENGER_ID = "AVENGER_ID"

        fun launch(activity: Activity, superheroId: String) {
            val intent = Intent(activity, DetailAvengerActivity::class.java)
            intent.putExtra(EXTRA_AVENGER_ID, superheroId)
            activity.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: DetailAvengerPresenter

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        setUpActionBar()

        presenter.attachView(this)
    }

    override fun getActivityLayout(): Int = R.layout.activity_detail_avenger

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun getAvengerName(): String = intent.getStringExtra(EXTRA_AVENGER_ID)

    override fun displayImage(url: String) {
        Picasso.with(this)
            .load(url)
            .into(imageAvenger)
    }

    override fun displayName(name: String) {
        toolbar.title = name
    }

    override fun displayRealName(realName: String) {
        textRealName.text = realName
    }

    override fun displayHeight(height: String) {
        textHeight.text = height
    }

    override fun displayAbilities(abilities: String) {
        textAbilities.text = abilities
    }

    override fun displayPowers(powers: String) {
        textPowers.text = powers
    }

    override fun displayGroups(groups: String) {
        textGroups.text = groups
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                presenter.navUpSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}