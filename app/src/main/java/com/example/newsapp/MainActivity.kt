package com.example.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var newAdapter: news_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        fetchData()
        newAdapter = news_adapter(this)
        recycler_view.adapter = newAdapter


    }

    private fun fetchData() {
//        you can use various news api here
//        you can make changes according to the api
//        hindi and english news
//        val url ="https://newsdata.io/api/1/news?apikey=pub_867481b47ec5a8062d45b3b0400734b63eee&q=sports&country=in&language=hi&category=sports "
//  val url ="https://newsdata.io/api/1/news?apikey=pub_867481b47ec5a8062d45b3b0400734b63eee&q=sports&country=au,in&language=hi&category=business,sports "
//   val url ="https://newsdata.io/api/1/news?apikey=pub_867479410f03e9eb11aa9fd5f77e06a45d20&q=cryptocurrency"

//   val url ="https://newsdata.io/api/1/news?apikey=pub_867479410f03e9eb11aa9fd5f77e06a45d20&q=cricket&language=en,hi "
// val url ="https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=4ae3b58a2ec74470b9d5644b29401c7b"

        val url = "https://newsapi.in/newsapi/news.php?key=9kwGQiaMUSjHpFCoe9X4tj5oULsjII&category=hindi_state"

//        val url= "https://newsdata.io/api/1/news?apikey=pub_867479410f03e9eb11aa9fd5f77e06a45d20&country=in&language=en&category=business,entertainment,politics,sports,technology "

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("News")

//                newJsonArray is array of news articles

                val newsArray = ArrayList<News>()

//                newsArray basically collects all information of news

                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("image"),
                        newsJsonObject.getString("published_date")
                    )
                    newsArray.add(news)
                }

                newAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun onItemClicked(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
//        for custom tab browser
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }


}