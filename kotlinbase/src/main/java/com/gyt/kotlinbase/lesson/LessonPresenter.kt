package com.example.lesson

import com.example.core.http.HttpClient
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import com.gyt.kotlinbase.http.EntityCallback
import com.gyt.kotlinbase.lesson.LessonActivity
import com.gyt.kotlinbase.utils.toast

class LessonPresenter(activity: LessonActivity) {
    private val activity: LessonActivity
    private var lessons: List<Lesson> = ArrayList()
    private val type = object : TypeToken<List<Lesson?>?>() {}.type

    init {
        this.activity = activity
    }

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread(Runnable { toast(message) })
            }

        })
    }

    fun showPlayback() {
        activity.showResult(lessons.filter { it.state === Lesson.State.PLAYBACK })
    }

    companion object {
        private const val LESSON_PATH = "lessons"
    }
}