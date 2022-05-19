package com.example.unitconvertor.conversion

class TemperatureConversion(private val from:String, private val to:String,
                            private val unitsValue:Double) {
    fun getConversion(): Double {
        if(from == to)
        {
            return unitsValue
        }
        val squareMeter = getCelsius(unitsValue)
        return getFinal(squareMeter)
    }

    private fun getFinal(value:Double): Double {
        when(from)
        {
            "Fahrenheit"->
            {
                return (value*(5/9.0))+32
            }
            "kelvin"->
            {
                return value+273.1
            }
        }
        return value
    }

    private fun getCelsius(value: Double): Double {
        when(from)
        {
            "Fahrenheit"->
            {
                return (value-32)*(5.0/9)
            }
            "kelvin"->
            {
                return value-273.1
            }
        }
        return value
    }

}