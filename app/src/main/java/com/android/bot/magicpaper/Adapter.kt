/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.bot.magicpaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val context: Context,
    private val imageArray: ArrayList<Int>,
    private val booleanItemClicked: ArrayList<Boolean>
) : RecyclerView.Adapter<Adapter.NumberViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.number_list_item, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = imageArray.size

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val listItemNumberView: TextView = itemView.findViewById(R.id.tv_item_number)
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        private val imgVisible: ImageView = itemView.findViewById(R.id.imgvisible)

        init {
            itemView.setOnClickListener(this)
            imgVisible.setOnClickListener(this)
        }

        fun bind(listIndex: Int) {
            listItemNumberView.text = listIndex.toString()
            if (booleanItemClicked[listIndex]) {
                imgVisible.setImageResource(imageArray[listIndex])
            } else {
                imgVisible.setImageResource(R.drawable.ic_action_name)
            }
        }

        override fun onClick(view: View) {
            val adapterPosition = adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (pastIndex!=0) {
                    booleanItemClicked[pastIndex] = false
                    notifyItemChanged(pastIndex)

                }
                booleanItemClicked[adapterPosition] = true
                notifyItemChanged(adapterPosition)
                pastIndex= adapterPosition
            }
        }
    }

    public companion object {
        private val TAG = Adapter::class.java.simpleName
        public var pastIndex:Int = 0
    }
}
