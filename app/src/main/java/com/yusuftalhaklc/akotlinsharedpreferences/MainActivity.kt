package com.yusuftalhaklc.akotlinsharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // lateinit = daha sonra tanımlanacak
    lateinit var sharedPreferences: SharedPreferences
    var userNumber : Int? = null
    var highScore : Int? = null
    private var randomNumber : Int = 0
    var status = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sharedPreferences oluşturma
        sharedPreferences = this.getSharedPreferences("package com.yusuftalhaklc.akotlinsharedpreferences",MODE_PRIVATE)
        //değer oluşturma
        sharedPreferences
        //değer alma
        highScore= sharedPreferences.getInt("highScore", 0)
        textView.text = "En Yüksek Skor : " + highScore
        //ramdom sayıyı üretme
        randomNumber = getRandomNumber()
        //bilgilendirme
        textView2.text = "İpucu : " + "Sayını gir ve tahmin et."
    }

    fun guessButtonClicked(view : View){
        //editText i tanımlama
        userNumber = numberText.text.toString().toIntOrNull()
        infoText.visibility = View.GONE

            if (userNumber == null) {
                textView2.text = "İpucu : " + "Bir sayı girmelisiniz."
            }
            else if (userNumber == randomNumber) {
                textView2.text = "TEBRİKLER! (+5p)"

                highScore = highScore?.plus(5)
                sharedPreferences.edit().putInt("highScore", highScore!!).apply()

                textView.text = "En Yüksek Skor : " + highScore

                infoText.text = "tekrar oynamak için sayını gir ve tahmin et butonuna bas."
                infoText.visibility = View.VISIBLE

                numberText.text.clear()
                randomNumber = getRandomNumber()
            }
            else if (userNumber!! > randomNumber) {
                textView2.text = "İpucu : " + "Daha Küçük"
                numberText.text.clear()
            }
            else if (userNumber!! < randomNumber) {
                textView2.text = "İpucu : " + "Daha Büyük"
                numberText.text.clear()
            }
    }
    fun getRandomNumber(): Int {
        randomNumber = (0..100).random()
        println(" sayı : "+randomNumber)
        return randomNumber
    }
}