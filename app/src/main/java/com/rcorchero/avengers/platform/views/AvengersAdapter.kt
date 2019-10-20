package com.rcorchero.avengers.platform.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rcorchero.avengers.R
import com.rcorchero.avengers.presentation.AvengerCellView
import com.rcorchero.avengers.presentation.presenters.AvengerListPresenter
import com.squareup.picasso.Picasso


class AvengersAdapter(private val presenter: AvengerListPresenter) :
        RecyclerView.Adapter<AvengersAdapter.AvengerViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AvengerViewHolder {
        if (viewGroup is RecyclerView) {
            val view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_avenger, viewGroup, false)
            return AvengerViewHolder(view)
        } else {
            throw RuntimeException("Not bound to RecyclerView")
        }
    }

    override fun onBindViewHolder(avengerViewHolder: AvengerViewHolder, position: Int) {
        presenter.configureCell(avengerViewHolder, position)
    }

    override fun getItemCount(): Int = presenter.getItemsCount()

    fun refreshData() {
        notifyDataSetChanged()
    }

    inner class AvengerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        AvengerCellView, View.OnClickListener {

        private val imageView = itemView.findViewById(R.id.image) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        override fun displayImage(url: String) {
            Picasso.with(imageView.context)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView)
        }

        override fun onClick(view: View) {
            presenter.onItemClick(adapterPosition)
        }
    }
}