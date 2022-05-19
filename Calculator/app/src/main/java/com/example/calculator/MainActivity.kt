package com.example.calculator

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.reflect.Method


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var inputText=StringBuilder("")
    private var check=0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.primaryText.movementMethod=ScrollingMovementMethod()
        binding.primaryText.isActivated=true

        binding.primaryText.showSoftInputOnFocus = false
        binding.resultText.showSoftInputOnFocus=false

        binding.btnOne.setOnClickListener{

            inputText.append("1")
            binding.primaryText.setText(binding.primaryText.text.toString()+"1")
            result(inputText)

        }
        binding.btnTwo.setOnClickListener{
            inputText.append("2")
            binding.primaryText.setText(binding.primaryText.text.toString()+"2")
            result(inputText)
        }
        binding.btnThree.setOnClickListener{
            inputText.append("3")
            binding.primaryText.setText(binding.primaryText.text.toString()+"3")
            result(inputText)
        }
        binding.btnFour.setOnClickListener{
            inputText.append("4")
            binding.primaryText.setText(binding.primaryText.text.toString()+"4")
            result(inputText)
        }
        binding.btnFive.setOnClickListener{
            inputText.append("5")
            binding.primaryText.setText(binding.primaryText.text.toString()+"5")
            result(inputText)
        }
        binding.btnSix.setOnClickListener{
            inputText.append("6")
            binding.primaryText.setText(binding.primaryText.text.toString()+"6")
            result(inputText)
        }
        binding.btnSeven.setOnClickListener{
            inputText.append("7")
            binding.primaryText.setText(binding.primaryText.text.toString()+"7")
            result(inputText)
        }
        binding.btnEight.setOnClickListener{
            inputText.append("8")
            binding.primaryText.setText(binding.primaryText.text.toString()+"8")
            result(inputText)
        }
        binding.btnNine.setOnClickListener{
            inputText.append("9")
            binding.primaryText.setText(binding.primaryText.text.toString()+"9")
            result(inputText)
        }
        binding.btnZero.setOnClickListener{
            inputText.append("0")
            binding.primaryText.setText(binding.primaryText.text.toString()+"0")
            result(inputText)
        }
        binding.btnMinus.setOnClickListener{
            if(inputText.isNotEmpty()&&checkValid(inputText[inputText.length-1]))
            {
                binding.primaryText.setText(binding.primaryText.text.toString()+resources.getString(R
                    .string.minus))
                inputText.append("-")
                check++
            }


        }
        binding.btnPlus.setOnClickListener{
            if(inputText.isNotEmpty()&&checkValid(inputText[inputText.length-1]))
            {
                binding.primaryText.setText(binding.primaryText.text.toString()+resources.getString(R
                    .string.plus))
                inputText.append("+")
                check++
            }



        }
        binding.btnOpenParentheses.setOnClickListener{
            binding.primaryText.setText(binding.primaryText.text.toString()+"(")
            inputText.append("(")

        }
        binding.btnCloseParentheses.setOnClickListener{
            binding.primaryText.setText(binding.primaryText.text.toString()+")")
            inputText.append(")")
            result(inputText)

        }
        binding.btnDivision.setOnClickListener{

            if(inputText.isNotEmpty()&&checkValid(inputText[inputText.length-1]))
            {
                binding.primaryText.setText(binding.primaryText.text.toString()+resources.getString(R
                    .string.division))

                inputText.append("/")
                check++
            }

        }
        binding.btnMultiply.setOnClickListener{
            if(inputText.isNotEmpty()&&checkValid(inputText[inputText.length-1]))
            {
                binding.primaryText.setText(binding.primaryText.text.toString()+resources.getString(R
                    .string.multiply))
                inputText.append("*")
                check++
            }


        }
        binding.btnDot.setOnClickListener{

            if(inputText.isEmpty() ||inputText[inputText.length-1] =='+'||inputText[inputText
                    .length-1] =='-'||inputText[inputText.length-1] =='*'||inputText[inputText
                    .length-1] =='/')
            {
                binding.primaryText.setText(binding.primaryText.text.toString()+"0.")
                inputText.append("0.")
            }
            else if(inputText[inputText.length-1] !='.')
            {
                inputText.append(".")
                binding.primaryText.setText(binding.primaryText.text.toString()+".")
            }


        }
        binding.btnAc.setOnClickListener{
            binding.primaryText.setText("")
            binding.resultText.setText("")
            inputText.clear()

        }
        binding.btnBack.setOnClickListener{
            val text=binding.primaryText.text.toString()
            if(text.isNotEmpty())
            {
                if(checkValid(inputText[inputText.length-1]))
                {
                    check--
                }
                binding.primaryText.setText(text.substring(0,text.length-1))
                inputText.deleteCharAt(inputText.length-1)
                binding.resultText.setText(binding.primaryText.text.toString())
            }


        }
        binding.btnEqual.setOnClickListener{
            result(inputText)
            binding.primaryText.setText(binding.resultText.text.toString())
            binding.resultText.setText("")
            inputText.clear()
            inputText.append(binding.primaryText.text.toString())
        }


    }


    private fun result(inputText: StringBuilder) {
        if(check>0) {


            try {
                val expression = ExpressionBuilder(inputText.toString()).build()
                val doubleResult = expression.evaluate()
                val longResult = doubleResult.toLong()
                if (doubleResult == longResult.toDouble()) {

                    binding.resultText.setText(longResult.toString())
                } else
                    binding.resultText.setText(doubleResult.toString())

            } catch (e: Exception) {
                Log.i("exception",e.message!!)
               }
        }
        else
        {
            binding.resultText.setText(inputText.toString())
        }
    }

    private fun checkValid(char:Char):Boolean
    {
        return when(char) {
            '+'-> false
            '-'-> false
            '*'-> false
            '/'->false
            else -> {
                true
            }
        }
    }

}