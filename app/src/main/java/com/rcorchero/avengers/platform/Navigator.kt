package com.rcorchero.avengers.platform

import android.app.Activity
import com.rcorchero.avengers.platform.views.DetailAvengerActivity

class Navigator {

    fun navigateToDetail(activity: Activity, superheroId: String) =
        DetailAvengerActivity.launch(activity, superheroId)
}