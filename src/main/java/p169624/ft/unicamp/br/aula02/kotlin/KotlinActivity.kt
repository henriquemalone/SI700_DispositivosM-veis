package p169624.ft.unicamp.br.aula02.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import p169624.ft.unicamp.br.aula02.R

class KotlinActivity : AppCompatActivity() {

    private lateinit var memoria : String
    private lateinit var editText : EditText
    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val btnPull = findViewById<Button>(R.id.btnPull)
        btnPull.setOnClickListener{
            textView.setText(memoria)
            Toast.makeText(this,"Valor Resgatado", Toast.LENGTH_SHORT).show()
            }

        if (savedInstanceState != null){
            memoria = savedInstanceState.getString("chave", "")
            textView.setText(memoria)
        }
    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        outState.putString("chave", memoria);
    }

    fun pushVariable(view: View){
        memoria = editText.text.toString()
        Toast.makeText(this,"Valor Inserido", Toast.LENGTH_SHORT).show()
    }

    override fun onStart(){
        super.onStart()
        Log.i("Kotlin","onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.i("Kotlin","onResume")
    }

    override fun onRestart(){
        super.onRestart()
        Log.i("Kotlin","onRestart")
    }

    override fun onPause(){
        super.onPause()
        Log.i("Kotlin","onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.i("Kotlin","onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("Kotlin","onDestroy")
    }
}




