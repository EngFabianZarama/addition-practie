package zarama.fabian.additiontrainer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {


    private lateinit var txtQuestion : TextView
    private lateinit var btnTimer : Button
    private var corretAnswer : Int? = null // Correct answer of addition
    private var selCorrect : Int = 0 // Correct selection of user
    private var selWrong : Int = 0   // Wrong selection of user
    val startTimer = object :CountDownTimer(30000+100,1000 ){

        override fun onTick(timeUntilDoneInMilliseconds: Long) {
            btnTimer.setText((timeUntilDoneInMilliseconds/1000).toString())
        }

        override fun onFinish() {
            btnTimer.setText("Done!")
            btnDone.visibility = View.VISIBLE
            btnStart1.visibility = View.INVISIBLE
            btnStart2.visibility = View.INVISIBLE
            btnStart3.visibility = View.INVISIBLE
            btnStart4.visibility = View.INVISIBLE
            txtQuestion.visibility = View.INVISIBLE
            btnTimer.visibility = View.INVISIBLE
            btnNumberOfAnswers.visibility = View.INVISIBLE
            btnDone.text = "Done! \n Ur score is \n$selCorrect / $selWrong "
        }

    }

    fun btnStart(view : View){
        selCorrect = 0
        selWrong = 0
        btnNumberOfAnswers.text = "0 / 0"

        startTimer.cancel()
        btnDone.visibility = View.INVISIBLE
        btnStart.visibility = View.INVISIBLE
        btnStart1.visibility = View.VISIBLE
        btnStart2.visibility = View.VISIBLE
        btnStart3.visibility = View.VISIBLE
        btnStart4.visibility = View.VISIBLE
        btnReset.visibility = View.VISIBLE
        txtQuestion.visibility = View.VISIBLE
        btnTimer.visibility = View.VISIBLE
        btnNumberOfAnswers.visibility = View.VISIBLE

        startTimer()
        question()
        setAnswer()
    }

    fun setAnswer(){

        val list = arrayListOf<Button>(btnStart1,btnStart2,btnStart3,btnStart4)

        list.shuffle()

        val first = Integer.parseInt(txtQuestion.text.first().toString()) // First digit of addition
        val second = Integer.parseInt(txtQuestion.text.last().toString()) // Second digit of addition

        corretAnswer = first + second

        list.get(0).setText(corretAnswer.toString()) // Correct answer
        list.get(1).setText((Math.random() * (18 - 0) + 0).roundToInt().toString()) // Random answer from 0 to 18
        list.get(2).setText((Math.random() * (18 - 0) + 0).roundToInt().toString()) // Random answer from 0 to 18
        list.get(3).setText((Math.random() * (18 - 0) + 0).roundToInt().toString()) // Random answer from 0 to 18



    }




    fun btnAnswer(view: View){

        val btnPressed = view as Button

        if(corretAnswer.toString() == btnPressed.text) {
            Toast.makeText(this@MainActivity, "Correct", Toast.LENGTH_SHORT).show()
            selCorrect++
        }else{
            Toast.makeText(this@MainActivity, "Incorrect", Toast.LENGTH_SHORT).show()
            selWrong++
        }

        btnNumberOfAnswers.setText(selCorrect.toString() + " / " + selWrong.toString())

        // Make a new addition
        question()
        setAnswer()
    }

    fun startTimer(){

        startTimer.start()

    }

    // Retuns a string with two values between 0 and 9 like this..
    // 4 + 5
    fun question(){
        // Math.random() * (max - min) + min
        val fistDigit = Math.random() * (9 - 0) + 0
        val secondDigit = Math.random() * (9 - 0) + 0
        txtQuestion.text = "${fistDigit.roundToInt()} + ${secondDigit.roundToInt()}"

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtQuestion = findViewById(R.id.txtQuestion)
        btnTimer = findViewById(R.id.btnTimer)










    }
}
