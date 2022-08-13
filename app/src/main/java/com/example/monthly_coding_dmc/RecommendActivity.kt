package com.example.monthly_coding_dmc

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import org.w3c.dom.Text
import kotlin.random.Random

class RecommendActivity : AppCompatActivity() {

    private val np: NumberPicker by lazy { findViewById(R.id.np) }

    private val dataList: MutableList<String> by lazy { mutableListOf() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent //인텐트 받기

        setData()
        initListener()
    }

    private fun initListener() {
        findViewById<Button>(R.id.button).setOnClickListener {
            // Do Random Picker
            onRandomPick()
        }
    }

    private fun setData() {
        for (idx in 0 until 30) {
            dataList.add("숨숨 $idx")
        }

        np.wrapSelectorWheel = true
        np.minValue = 0
        np.maxValue = dataList.size.minus(1)
        np.displayedValues = dataList.toTypedArray()
    }

    private fun onRandomPick() {
        val ran = Random.nextInt(0, 30)
        ObjectAnimator.ofInt(np.value, np.value.plus(ran)).apply {
            interpolator = CustomInterpolator()
            duration = 600
            addUpdateListener {
                np.value = it.animatedValue as Int
            }
            start()
        }
    }

    inner class CustomInterpolator : FastOutLinearInInterpolator() {

        override fun getInterpolation(input: Float): Float {
            return super.getInterpolation(input)
        }
    }
}