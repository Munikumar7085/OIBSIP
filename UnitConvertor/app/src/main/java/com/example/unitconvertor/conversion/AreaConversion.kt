package com.example.unitconvertor.conversion

class AreaConversion(private val from:String, private val to:String,
                     private val unitsValue:Double) {
    fun getConversion(): Double {
        if(from == to)
        {
            return unitsValue
        }
        val squareMeter = getSquareMeters(unitsValue)
        return getFinal(squareMeter)
    }

    private fun getFinal(value: Double): Double {
        when(to)
        {
            "Acre"->
            {
                return value*0.000247105
            }
            "Hectare"->
            {
                return value*0.0001
            }
            "Square inch"->
            {
                return value*1550
            }
            "square foot"->
            {
                return value*10.7639
            }
            "Square yard"->
            {
                return value*1.19599
            }
            "Square mile"->
            {
                return value*0.0000003861
            }
            "Square kilometer"->
            {
                return value*0.000001
            }
        }
        return value
    }

    private fun getSquareMeters(value: Double): Double {

        when(from)
        {
            "Acre"->
            {
                return value*4046.86
            }
            "Hectare"->
            {
                return value*10000
            }
            "Square inch"->
            {
                return value*0.00064516
            }
            "square foot"->
            {
                return value*0.092903
            }
            "Square yard"->
            {
                return value*0.836127
            }
            "Square mile"->
            {
                return value*2589988.11
            }
            "Square kilometer"->
            {
                return value*1000000
            }
        }
        return value
    }

}