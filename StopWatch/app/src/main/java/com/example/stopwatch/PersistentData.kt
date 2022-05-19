package com.example.stopwatch

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class PersistentData(context: Context) {
    private var myPreferences=context.getSharedPreferences(STORED_PREFERENCES,Context.MODE_PRIVATE)
    private val dateFormatter= SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault())
    private var counting=false
    private var start: Date? =null
    private var stop: Date? = null
    companion object
    {
        const val STORED_PREFERENCES="preferences"
        const val START_TIME="start"
        const val STOP_TIME="stop"
        const val COUNTING="counting"
    }
    init {
        counting=myPreferences.getBoolean(COUNTING,false)
       val startTimeString=myPreferences.getString(START_TIME,null)
        if(startTimeString!=null)
        {
            start=dateFormatter.parse(startTimeString)
        }
        val stopTimeString=myPreferences.getString(STOP_TIME,null)
        if(stopTimeString!=null)
        {
            stop=dateFormatter.parse(stopTimeString)
        }


    }

    fun getStartTime():Date?
    {
        return start
    }
    fun setStartTime(date:Date?)
    {
        start=date
        with(myPreferences.edit())
        {
            val dateInString=if (start==null) null else dateFormatter.format(start!!)
            putString(START_TIME,dateInString)
            apply()
        }

    }


    fun getStopTime():Date?
    {
        return stop
    }
    fun setStopTime(date:Date?)
    {
        stop=date
        with(myPreferences.edit())
        {
            val dateInString=if (start==null) null else dateFormatter.format(start!!)
            putString(STOP_TIME,dateInString)
            apply()
        }

    }
    fun getCount():Boolean
    {
        return counting
    }
    fun setCountTime(status:Boolean)
    {
        counting=status
        with(myPreferences.edit())
        {
            putBoolean(COUNTING,status)
            apply()
        }

    }


}