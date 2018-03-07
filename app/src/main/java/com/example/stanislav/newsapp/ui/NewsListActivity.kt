package com.example.stanislav.newsapp.ui

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import com.example.stanislav.newsapp.R
import com.example.stanislav.newsapp.ui.epoxy.NewsListEpoxyController
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class NewsListActivity : AppCompatActivity(), ViewI {

    @Inject
    lateinit var presenter: NewsListPresenter
    @Inject
    lateinit var epoxyController: NewsListEpoxyController

    private val userActions = PublishSubject.create<UserAction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        initRecyclerView(findViewById(R.id.recycler_view))
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }

    override fun setData(data: ViewData) {
        when (data) {
            ViewData.Empty -> epoxyController.setData(emptyList())
            is ViewData.Ready -> epoxyController.setData(data.items)
        }
    }

    override fun getUserActions(): Observable<UserAction> = userActions

    private fun initRecyclerView(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = epoxyController.adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    userActions.onNext(UserAction.NextPage)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menu.findItem(R.id.search)
                .actionView
                .let { it as SearchView }
                .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String) = true
                    override fun onQueryTextChange(newText: String) = true
                })
        return true
    }
}
