package mx.edu.ittepic.tarea3_programa

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val siPermiso=3

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CALL_LOG)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CALL_LOG),siPermiso)
        }

        leerLlamadasEntrantes()
        leerLlamadasPerdidas()
    }

    private fun leerLlamadasPerdidas() {
        var cursor=contentResolver.query(Uri.parse("content://call_log/calls"),null,null,null,null)
        var resultado=""
        if(cursor!!.moveToFirst()){
            var Celular=cursor.getColumnIndex("NUMBER")
            var Tipo=cursor.getColumnIndex("TYPE")
            var Fecha=cursor.getColumnIndex("DATE")
            do{
                if(cursor.getInt(Tipo)==3){
                    val fechaMensaje=cursor.getString(Fecha)
                    resultado+="Numero origen: "+cursor.getString(Celular)+
                            "\nTipo llamada: "+cursor.getString(Tipo)+
                            "\nFecha: "+fechaMensaje+"\n----------------------------\n"
                }
            }while (cursor.moveToNext())
        }else{
            resultado="No hay llamadas perdidas"
        }
        textView3.setText(resultado)
    }

    fun leerLlamadasEntrantes(){
        var cursor=contentResolver.query(Uri.parse("content://call_log/calls"),null,null,null,null)
        var resultado=""
        if(cursor!!.moveToFirst()){
            var Celular=cursor.getColumnIndex("NUMBER")
            var Tipo=cursor.getColumnIndex("TYPE")
            var Fecha=cursor.getColumnIndex("DATE")
            do{
                if(cursor.getInt(Tipo)==1){
                    val fechaMensaje=cursor.getString(Fecha)
                    resultado+="Numero origen: "+cursor.getString(Celular)+
                            "\nTipo llamada: "+cursor.getString(Tipo)+
                            "\nFecha: "+fechaMensaje+"\n----------------------------\n"
                }
            }while (cursor.moveToNext())
        }else{
            resultado="No hay llamadas entrantes"
        }
        textView6.setText(resultado)
    }
    /*
    1 - entrante
    2 - saliente
    3 - perdida
    */





}
