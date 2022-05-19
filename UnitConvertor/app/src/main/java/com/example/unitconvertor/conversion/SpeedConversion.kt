package com.example.unitconvertor.conversion

class SpeedConversion(private val from:String, private val to:String,
                      private val unitsValue:Double) {
    fun getConversion(): Double {
        if(from == to)
        {
            return unitsValue
        }
        val meterPerSecond = getMeterPerSecond(unitsValue)
        return getFinal(meterPerSecond)
    }

    private fun getFinal(value: Double): Double {
        when(to)
        {
            "Miles per hour"->
            {
                return value*2.23694
            }
            "Foot per Second"->
            {
                return value*3.28084
            }
            "Kilometer per hour"->
            {
                return value*3.6
            }
        }
        return value
    }

    private fun getMeterPerSecond(value: Double): Double {
        when(from)
        {
            "Miles per hour"->
            {
                return value*0.44704
            }
            "Foot per Second"->
            {
                return value*0.3048
            }
            "Kilometer per hour"->
            {
                return value*0.277778
            }
        }
        return value
    }

}