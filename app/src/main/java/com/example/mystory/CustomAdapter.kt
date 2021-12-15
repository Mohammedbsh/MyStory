package com.example.mystory

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.util.Random

class CustomAdapter (val stories:ArrayList<Story>,val context: Context):RecyclerView.Adapter<CustomAdapter.DataHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val dataHolder = DataHolder (LayoutInflater.from(parent.context).inflate(R.layout.custom,parent,false))

        return dataHolder

    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val story = stories[position]
        holder.title.text = story.title
        holder.subTitle.text = story.Subtitle
        holder.storyLetter.text = story.title.first().toString()

        generateColors(holder,position)

        holder.itemView.setOnClickListener{
            val i = Intent (context,Story_DetailsActiviy::class.java)
            i.putExtra("title", story.title)
            i.putExtra("desc", story.description)
            context.startActivity(i)
        }

    }

    private fun generateColors(holder: DataHolder,position: Int){


        val r = Random()
        val red = r.nextInt(255+position)
        val green = r.nextInt(255-position+1)
        val blue = r.nextInt(255+position+1)

        holder.cardViewLetter.setCardBackgroundColor(Color.rgb(red,green,blue))


    }







    override fun getItemCount(): Int {
        return stories.size


    }

    class DataHolder (item: View) : RecyclerView.ViewHolder(item){
        val title:TextView = item.findViewById(R.id.title)
        val subTitle:TextView = item.findViewById(R.id.subtitle)
        val storyLetter:TextView = item.findViewById(R.id.StoryLetter)
        val cardViewLetter:CardView = item.findViewById(R.id.circle)
    }


}
