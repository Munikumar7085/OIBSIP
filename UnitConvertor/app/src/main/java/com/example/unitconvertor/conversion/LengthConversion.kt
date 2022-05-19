package com.example.unitconvertor.conversion

class LengthConversion(private val from:String, private val to:String,
                       private val unitsValue:Double) {
    fun getConversion(): Double {
        if(from == to)
        {
            return unitsValue
        }
        val meters = getMeters(unitsValue)
        return getFinal(meters)
    }

    private fun getFinal(value: Double): Double {
        when(to)
        {
            "Milli meters"->
            {
                return value*1000
            }
            "centi meter"->
            {
                return value*100
            }
            "Inch"->
            {
                return value*39.3701
            }
            "Foot"->
            {
                return value*3.28084
            }
            "meter"->
            {
                return value
            }
            "kilometer"->
            {
                return value*0.001
            }
            "Mile"->
            {
                return value*0.000621371
            }
        }
        return value
    }

    private fun getMeters(value: Double): Double {
        when(from)
        {
            "Milli meters"->
            {
                return value*0.001
            }
            "centi meter"->
            {
                return value*0.01
            }
            "Inch"->
            {
                return value*0.0254
            }
            "Foot"->
            {
                return value*0.3048
            }
            "meter"->
            {
                return value
            }
            "kilometer"->
            {
                return value*1000
            }
            "Mile"->
            {
                return value*1069.34
            }
        }
        return value
    }

}