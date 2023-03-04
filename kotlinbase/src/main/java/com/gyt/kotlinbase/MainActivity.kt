package com.gyt.kotlinbase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.gyt.kotlinbase.entity.User
import com.gyt.kotlinbase.lesson.LessonActivity
import com.gyt.kotlinbase.utils.CacheUtils
import com.gyt.kotlinbase.utils.toast
import com.gyt.kotlinbase.widget.CodeView

class MainActivity : AppCompatActivity(), OnClickListener {

    private val usernameKey = "username"
    private val passwordKey = "password"

    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val bt_login = findViewById<Button>(R.id.btn_login)
        val img_code = findViewById<CodeView>(R.id.code_view)

        bt_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val passsword = et_password.text.toString()
        val code = et_code.text.toString()

        val user = User(username, passwordKey, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, passsword)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        if ((user.username?.length ?: 0) < 4) {
            toast("用戶名不合法")
            return false
        }
        if ((user.password?.length ?: 0) < 4){
            toast("密碼不合法")
            return false
        }
        return true
    }


}