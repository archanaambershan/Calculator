package com.tutoriials.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    var resnum:Boolean=true
      var lastnum:Boolean=false
    var lastdec:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buttonClicked(view:View){
        val input=findViewById(R.id.input)as TextView
        if(resnum) {
            input.append((view as Button).text)

            lastnum = true
        }
        else{
            input.text=""
            input.append((view as Button).text)
            resnum=true
        }
    }
    fun onClear(view:View){
        val input=findViewById(R.id.input)as TextView
        input.text=""
        lastnum=false
        lastdec=false
        resnum=true
    }
    fun decimals(view:View) {
        val input = findViewById(R.id.input) as TextView

        if (lastnum && !lastdec) {
            input.append(".")
            lastnum = false
            lastdec = true
        }
    }
        fun isContains(st:String):Boolean{
            return if(st.startsWith("-")){
                false
            }else{
                st.contains("+")||st.contains("*")||st.contains("-")||st.contains("/")
            }
        }

        fun isoperator(view:View){
            val input=findViewById(R.id.input)as TextView
            if (!lastnum && (view as Button).text=="-" ){
                input.append((view as Button).text)
                lastnum=false
                lastdec=false
            }
            if(lastnum && !isContains(input.text.toString())){
                input.append((view as Button).text)
                lastnum=false
                lastdec=false
                resnum=true
            }
        }

         fun isEqual(view:View){
             val input=findViewById(R.id.input) as TextView
             var ans=input.text
             var prefix=""
             try {
                 if(ans.startsWith("-")){
                     prefix="-"
                     ans=ans.substring(1)


                 }
                 resnum=false
                 if(ans.contains("-")) {

                     var out = ans.split("-")

                     var one=out[0]
                     var two=out[1]
                     if(prefix!=null){
                         one=prefix+one
                     }
                     input.text=removeZero((one.toDouble()-two.toDouble()).toString())


                 }
                else if(ans.contains("+")) {

                     var out = ans.split("+")

                     var one=out[0]
                     var two=out[1]
                     if(prefix!=null){
                         one=prefix+one
                     }
                     input.text=removeZero((one.toDouble()+two.toDouble()).toString())


                 }
                else  if(ans.contains("*")) {

                     var out = ans.split("*")

                     var one=out[0]
                     var two=out[1]
                     if(prefix!=null){
                         one=prefix+one
                     }
                     input.text=removeZero((one.toDouble()*two.toDouble()).toString())


                 }
                else  if(ans.contains("/")) {

                     var out = ans.split("/")

                     var one=out[0]
                     var two=out[1]
                     if(prefix!=null){
                         one=prefix+one
                     }
                     input.text=removeZero((one.toDouble()/two.toDouble()).toString())


                 }



             }
             catch(e:ArithmeticException){
                 e.printStackTrace()
             }
         }
  private fun removeZero(s:String):String{
      var s=s
      if(s.contains(".0")){
          s=s.substring(0,s.length-2)
      }
      return s
  }



}