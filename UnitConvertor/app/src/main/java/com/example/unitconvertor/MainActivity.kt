package com.example.unitconvertor

import android.annotation.SuppressLint
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.unitconvertor.R.array.unitTypes
import com.example.unitconvertor.conversion.AreaConversion
import com.example.unitconvertor.conversion.LengthConversion
import com.example.unitconvertor.conversion.SpeedConversion
import com.example.unitconvertor.conversion.TemperatureConversion
import com.example.unitconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var unitTypeAdapter: ArrayAdapter<String>
    private lateinit var binding: ActivityMainBinding
    var unitType = 0
    var result: Double = 0.0
    var from =""
    var to=""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main,)
        val unitsTypeList =
            arrayListOf("select unit type", "Length", "Area", "speed", "Temperature")
        unitTypeAdapter = ArrayAdapter(this, R.layout.units_textview_layout, unitsTypeList)
        binding.spinnerType.adapter = unitTypeAdapter
        val lengthUnitList = arrayListOf(
            "Milli meters", "centi meter", "Inch", "Foot", "meter",
            "kilometer", "Mile"
        )

        val areaUnitList = arrayListOf(
            "Acre", "Hectare", "Square inch", "square foot", "Square yard",
            "Square mile", "Square meter", "Square kilometer"
        )
        val speedUnitList = arrayListOf(
            "Miles per hour", "Foot per Second", "Meter per Second",
            "Kilometer per hour"
        )
        val temperatureUnitList = arrayListOf("Celsius", "Fahrenheit", "kelvin")
        Log.i("saved", savedInstanceState.toString())
        if (savedInstanceState != null) {

            result = savedInstanceState.getString("result")!!.toDouble()
            from=savedInstanceState.getString("from")!!
            to=savedInstanceState.getString("to")!!

            binding.result.text="$result ${to}"

        }

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                unitType = p2
                when (p2) {
                    0 -> {
                        binding.spinnerFrom.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, arrayListOf("select convert units from")
                        )
                        binding.spinnerTo.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, arrayListOf("select convert units to")
                        )
                    }
                    1 -> {
                        binding.spinnerFrom.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, lengthUnitList
                        )
                        binding.spinnerTo.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, lengthUnitList
                        )

                    }
                    2 -> {
                        binding.spinnerFrom.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, areaUnitList
                        )
                        binding.spinnerTo.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, areaUnitList
                        )
                    }
                    3 -> {
                        binding.spinnerFrom.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, speedUnitList
                        )
                        binding.spinnerTo.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, speedUnitList
                        )
                    }
                    4 -> {
                        binding.spinnerFrom.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, temperatureUnitList
                        )
                        binding.spinnerTo.adapter = ArrayAdapter(
                            this@MainActivity, R.layout
                                .units_textview_layout, temperatureUnitList
                        )
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }
        binding.btnConvert.setOnClickListener {

            if (checkSelection()) {
                val units = binding.inputUnits.text.toString().toDoubleOrNull()
                 from = binding.spinnerFrom.selectedItem.toString()
                 to = binding.spinnerTo.selectedItem.toString()


                when (unitType) {
                    1 -> {
                        val length = LengthConversion(from, to, units!!)
                        result = length.getConversion()
                    }
                    2 -> {
                        val area = AreaConversion(from, to, units!!)
                        result = area.getConversion()
                    }
                    3 -> {
                        val speed = SpeedConversion(from, to, units!!)
                        result = speed.getConversion()
                    }
                    4 -> {
                        val temperature = TemperatureConversion(from, to, units!!)
                        result = temperature.getConversion()
                    }
                }
                binding.result.text = "$result ${to}s"
            }
        }
    }

    private fun checkSelection(): Boolean {
        if (binding.inputUnits.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please Enter units to convert", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.spinnerType.selectedItem.toString() == "select unit type") {
            Toast.makeText(this, "Please select units type to convert", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.spinnerFrom.selectedItem.toString() == "select convert units from") {
            Toast.makeText(this, "Please select source units type", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        if (binding.spinnerTo.selectedItem.toString() == "select convert units to") {
            Toast.makeText(this, "Please select Destination units type", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result",result.toString())
        outState.putString("from",from)
        outState.putString("to",to)
    }
}