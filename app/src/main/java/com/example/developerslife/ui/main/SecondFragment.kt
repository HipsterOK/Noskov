package com.example.developerslife.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.developerslife.Post
import com.example.developerslife.R
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SecondFragment : Fragment() {

    private var numberStr: Int = 0
    private var numPost: Int = 0
    private var url: String= ""
    private val client = OkHttpClient()
    var post:Array<Post> = arrayOf(Post(0, "",""),Post(0, "",""),Post(0, "",""),Post(0, "",""),Post(0, "",""))

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_second, container, false)
        val imageView: ImageView = view.findViewById(R.id.section_label2)
        val butNext:ImageButton = view.findViewById(R.id.butNext2)
        val butBack: ImageButton = view.findViewById(R.id.butBack2)
        val textView1:TextView = view.findViewById(R.id.textView2)

        Log.i("dev", "Начало")
        numberStr=0
        numPost=0
        Log.i("dev", "$numberStr $numPost")
        run()
        Log.i("dev", post[numPost].gifURL)

        Glide.with(context!!)
                .asGif()
                .load(post[numPost].gifURL)
                .placeholder(R.drawable.ics_loading)
                .error(R.drawable.ic_error)
                .fitCenter()
                .override(320,480)
                .into(imageView)

        textView1.text = post[numPost].description

        butNext.setOnClickListener {
            if(numPost==4){
                numberStr++
                numPost=0
            }
            else numPost++
            butBack.isEnabled = true
            run()

            Glide.with(context!!)
                    .asGif()
                    .load(post[numPost].gifURL)
                    .placeholder(R.drawable.ics_loading)
                    .error(R.drawable.ic_error)
                    .fitCenter()
                    .override(320,480)
                    .into(imageView)

            textView1.text = post[numPost].description
        }

        butBack.setOnClickListener {
            if(numPost==0 && numberStr>0){
                numberStr--
                numPost=4
            }
            if(numPost==1 && numberStr==0){
                numPost--
                butBack.isEnabled = false
            }
            if(numPost>0){
                numPost--
            }
            run()

            Glide.with(context!!)
                    .asGif()
                    .load(post[numPost].gifURL)
                    .placeholder(R.drawable.ics_loading)
                    .error(R.drawable.ic_error)
                    .fitCenter()
                    .override(320,480)
                    .into(imageView)

            textView1.text = post[numPost].description
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun run() {
        url = "https://developerslife.ru/top/$numberStr?json=true"
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("dev", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val strResponse = response.body()!!.string()
                //creating json object
                val jsonContact = JSONObject(strResponse)
                //creating json array
                val JSONarrInfo:JSONArray= jsonContact.getJSONArray("result")
                val size:Int = JSONarrInfo.length()

                for (i in 0 until size) {
                    val jsonObjectdetail:JSONObject=JSONarrInfo.getJSONObject(i)
                    post[i].id=jsonObjectdetail.getInt("id")
                    post[i].description=jsonObjectdetail.getString("description")
                    post[i].gifURL=jsonObjectdetail.getString("gifURL")
                }

                Log.i("ss", post[0].description)
                Log.i("ss", post[1].description)
                Log.i("ss", post[2].description)
                Log.i("ss", post[3].description)
                Log.i("ss", post[4].description)
            }
        })
    }

}