package com.anwesh.uiprojects.linkedbarfromlineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.barfromlineview.BarFromLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarFromLineView.create(this)
    }
}
