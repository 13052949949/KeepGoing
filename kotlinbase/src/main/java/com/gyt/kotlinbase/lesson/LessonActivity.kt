package com.gyt.kotlinbase.lesson

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat.VERTICAL
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lesson.LessonAdapter
import com.example.lesson.LessonPresenter
import com.example.lesson.entity.Lesson
import com.gyt.kotlinbase.R
import com.gyt.kotlinbase.base.BaseView
import com.gyt.kotlinbase.utils.CacheUtils
import kotlin.reflect.KProperty

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {

    private val lessonAdapter = LessonAdapter()
    lateinit var refreshLayout: SwipeRefreshLayout

    var token: String by Saver("token")

    class Saver(var key: String) {
        operator fun getValue(activity: LessonActivity, property: KProperty<*>): String {
            println("getValue called!")
            return CacheUtils.get(key) !!
        }

        operator fun setValue(lessonActivity: LessonActivity,
                              property: KProperty<*>,
                              value: String) {
            println("setValue called!")
            CacheUtils.save(key, value)
        }
    }

    override val presenter: LessonPresenter by lazy { LessonPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, VERTICAL))
        }
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
        }
        presenter.fetchData()
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }

}