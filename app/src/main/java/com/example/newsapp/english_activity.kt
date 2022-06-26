package com.example.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_english.*

class english_activity : AppCompatActivity() {

    private lateinit var newAdapter: news_adapter_english
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english)

        recycler_view_english.layoutManager = LinearLayoutManager(this)
        findData()
        newAdapter = news_adapter_english(this)
        recycler_view_english.adapter = newAdapter

    }

    private fun findData() {
//     url ="https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=4ae3b58a2ec74470b9d5644b29401c7b"

//        val url = "https://newsapi.in/newsapi/news.php?key=9kwGQiaMUSjHpFCoe9X4tj5oULsjII&category=hindi_state"
//        https://newsdata.io/api/1/news?apikey=pub_867479410f03e9eb11aa9fd5f77e06a45d20&country=in&language=en&category=business,entertainment,politics,sports,technology

        val url = "https://newsdata.io/api/1/news?apikey=pub_867479410f03e9eb11aa9fd5f77e06a45d20&country=in&language=en&category=business,entertainment,politics,sports,technology"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("results")

//                newJsonArray is array of news articles

                val newsLIST = ArrayList<News>()

//                newsArray basically collects all information of news

                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("link"),
                        newsJsonObject.getString("image_url"),
                        newsJsonObject.getString("pubDate")
                    )
                    newsLIST.add(news)
                }

                newAdapter.updateNews(newsLIST)
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