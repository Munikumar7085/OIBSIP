package com.example.stopwatch

import android.os.Bundle
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.stopwatch.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val timer=Timer()
    private lateinit var  chronometer:Chronometer
   private lateinit var persistentData: PersistentData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        persistentData=PersistentData(applicationContext)
        binding.resetBtn.setOnClickListener{
            resetButton()
        }
        binding.startBtn.setOnClickListener{
            startButton()
        }
        binding.stopBtn.setOnClickListener{
            stopButton()
        }
        if(persistentData.getCount())
        {
            startTimer()
        }
        else
        {
            stopTimer()
            if(persistentData.getStartTime()!=null&&persistentData.getStopTime()!=null)
            {
                val time= Date().time-calculateRestartTime()!!.time
                binding.timerTxt.text=getTimeInString(time)
            }
        }

        timer.scheduleAtFixedRate(TimeTask(),0,40)
    }
    private inner class TimeTask:TimerTask()
    {
        override fun run() {

            runOnUiThread {

                if(persistentData.getCount())
                {
                    val time=Date().time-persistentData.getStartTime()!!.time
                    binding.timerTxt.text=getTimeInString(time)
                }
            }

        }
    }
    private fun stopButton() {
        if(persistentData.getCount())
        {
            persistentData.setStopTime(Date())
            stopTimer()
        }

    }

    private fun startButton() {
        if(!persistentData.getCount())
        {
            if(persistentData.getStopTime()!=null)
            {
                persistentData.setStartTime(calculateRestartTime())
                persistentData.setStopTime(null)
            }
            else
            {
                persistentData.setStartTime(Date())
            }
           startTimer()
        }

    }

    private fun calculateRestartTime(): Date? {
        val difference=persistentData.getStartTime()!!.time-persistentData.getStopTime()!!.time
        return Date(System.currentTimeMillis()+difference)
    }

    private fun resetButton() {
        persistentData.setStartTime(null)
        persistentData.setStopTime(null)
        stopTimer()
        binding.timerTxt.text=getTimeInString(0)
    }

    private fun getTimeInString(time: Long): String {
        val milliSeconds=(time/10)%100
        val seconds=(time/1000)%60
        val minutes=(time/(1000*60))%60
        val hours=(time/(1000*60*60))%24
        return stringConvertor(seconds,minutes,hours,milliSeconds)
    }

    private fun stringConvertor(seconds: Long, minutes: Long, hours: Long,milliSeconds:Long):
            String {
        return String.format("%02d:%02d:%02d:%02d",hours,minutes,seconds,milliSeconds)
    }

    private fun stopTimer() {
        persistentData.setCountTime(false)
    }
    private fun startTimer()
    {
        persistentData.setCountTime(true)
    }


    }

