package com.rahul.gifbrowser.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.rahul.gifbrowser.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class SplashActivity : AppCompatActivity(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animateIcon()
        launchDelayedActivity()

    }

    private fun launchDelayedActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        launch {
            delay(1500)
            withContext(Dispatchers.Main) {
                startActivity(intent)
                finish()
            }
        }
    }

    private fun animateIcon() {
        logo_main.animate().apply {
            scaleX(1.1f)
            scaleY(1.1f)
            duration = 500
            interpolator = OvershootInterpolator(25f)
        }.start()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

}