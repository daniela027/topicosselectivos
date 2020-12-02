package mx.udg.cuvalles.tablascool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_estudia.*
import java.lang.reflect.Array
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import java.util.*


class EstudiaActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AdapterView.OnItemLongClickListener{
    var tts: TextToSpeech? = null
    var listaElementos: MutableList<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudia)

       listaElementos = mutableListOf<String>()

        tts = TextToSpeech(this, this)
        Log.i("lenguajes",Locale.getAvailableLocales().toString() )

        listaTablas.setOnItemClickListener(this)

        seekBar2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listaElementos!!.clear() //aqui limpio la lista de elementos
                if (p1 < 1) {
                    seekBar2.setProgress(1) //si el seekbar baja a cero se restalece por defaul en 1
                }
                if (p1 > 0) {

                    //Log.d("seek", "p1")
                    for (i in 0..10) {
                        val texto = "$p1 x $i = ${p1 * i}"
                        listaElementos!!.add(texto) //nuevo elemento
                         }
                }
                     val adaptador = ArrayAdapter(application, android.R.layout.simple_list_item_1, listaElementos!!)
                    listaTablas.adapter = adaptador

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })


    }

    override fun onInit(status: Int) {
        //TODO("Not yet implemented")
        if(status==TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if (result != TextToSpeech.LANG_NOT_SUPPORTED && result!=TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "Lenguaje soportado exitosamente ", Toast.LENGTH_LONG).show()
             }else{
                Toast.makeText(this, "Lenguaje no soportado", Toast.LENGTH_LONG).show()

            }
        }


    }

    override fun onDestroy() {
        if(tts!! !=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    fun onItemClick (p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long){
        val textoALeer = listaElementos!!.get(p2)
        tts!!.speak(textoALeer,TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        TODO("Not yet implemented")
    }
}
//para quitar el error del (this)
private fun ListView.setOnItemClickListener(estudiaActivity: EstudiaActivity) {

}
