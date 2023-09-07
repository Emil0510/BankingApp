package com.emilabdurahmanli.bankingapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.emilabdurahmanli.bankingapp.databinding.ActivitySecurityBinding

class SecurityActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecurityBinding
    private lateinit var numberButton : List<ImageView>
    private lateinit var pinCode : MutableList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pinCode = mutableListOf()
        numberButton = listOf(binding.zeroButton, binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton, binding.fiveButton, binding.sixButton, binding.sevenButton, binding.eightButton, binding.nineButton )
        setUpClickListeners()
    }
    private fun setUpClickListeners(){

        for (i in numberButton.indices){
            numberButton[i].setOnClickListener {
                binding.pinView.currentPin++
                pinCode.add(i)
                if(binding.pinView.currentPin == 4){
                    checkPin()
                }
            }
        }

        binding.backspaceButton.setOnClickListener {
            binding.pinView.currentPin--
            if(pinCode.size!=0){
                pinCode.removeLast()
            }
        }
    }

    private fun checkPin(){
        var check : List<Int> = listOf(1, 1, 1, 1)
        pinCode.forEach {
            Log.d("Checkkk", it.toString())
        }
        pinCode = mutableListOf()
        binding.pinView.currentPin = 0

        val intent = Intent(this@SecurityActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()

    }
}