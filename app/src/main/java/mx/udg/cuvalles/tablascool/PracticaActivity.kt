package mx.udg.cuvalles.tablascool

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_practica.*
import kotlin.random.Random

class PracticaActivity : AppCompatActivity() {
    var resultado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica)

        generaOperacion()


        btnEnviar.setOnClickListener {
            val resText = tvRespuesta.text.toString() //setResultado
            if (!resText.equals("")) {
                val respuesta = resText.toInt()
                if (respuesta == resultado) {
                    Log.d("resultado", "le atinaste")
                    crearDialogoOk()
                } else {
                    Log.d("resultado", "No le atinaste")
                    crearDialogoError()
                }

            }
            generaOperacion()
        }

    }
    fun crearDialogoOk() {
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.activity_dialogo_ok, null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView).setTitle("Muy bien!!")
        val midialogo_ok = mBuilder.create()
        midialogo_ok.show()
        //val miPlayer:MediaPlayer?= MediaPlayer.create(this)//R.raw.aplauso 1:14
       // miPlayer?.start()

    }

    fun crearDialogoError() {
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.activity_dialogo_error, null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView).setTitle("Mul Mal!!")
        val midialogo_error = mBuilder.create()
        midialogo_error.show()


    }

    fun generaOperacion() {
        val operando1 = Random.nextInt(1, 10)
        val operando2 = Random.nextInt(1, 10)
        resultado = operando1 * operando2

        tvPregunta.text = "$operando1 x $operando2 = ?"
        tvRespuesta.text.clear() //nos borra el resultado

                  }
    }

    }