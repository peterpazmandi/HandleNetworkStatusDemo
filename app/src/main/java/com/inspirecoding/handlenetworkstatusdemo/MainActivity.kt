package com.inspirecoding.handlenetworkstatusdemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.inspirecoding.handlenetworkstatusdemo.databinding.ActivityMainBinding
import com.inspirecoding.handlenetworkstatusdemo.utils.NetworkUtils
import com.inspirecoding.handlenetworkstatusdemo.utils.getColorRes
import com.inspirecoding.handlenetworkstatusdemo.utils.hide
import com.inspirecoding.handlenetworkstatusdemo.utils.show

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val ANIMATION_DURATION = 1000.toLong()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        handleNetworkChanges()
    }



    private fun handleNetworkChanges()
    {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConncted ->
            if (!isConncted)
            {
                binding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                binding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            }
            else
            {
                binding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                binding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?)
                            {
                                hide()
                            }
                        })
                }
            }
        })
    }





















}