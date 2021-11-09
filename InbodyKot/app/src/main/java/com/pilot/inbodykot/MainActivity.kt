package com.pilot.inbodykot

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/*Inbody Web 개발 지원자 박상수*/

class MainActivity : AppCompatActivity() {

    var btn_addpic1: Button? = null
    var btn_takepic1: Button? = null
    var btn_opnalbum1: Button? = null

    private val GALLERY = 1
    private var imageView: ImageView? = .profilemodifyProfileIV



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filterActivityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == RESULT_OK && it.data !=null) {
                    var currentImageUri = it.data?.data
                    try {
                        currentImageUri?.let {
                            if(Build.VERSION.SDK_INT < 28) {

                            } else {

                            }
                        }


                    }catch(e:Exception) {
                        e.printStackTrace()
                    }
                } else if(it.resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
                }else{
                    Log.d("ActivityResult","something wrong")
                }
            }


        this.InitializeView()
        this.SetListener()



    }//onCreate

    fun InitializeView() {
        btn_addpic1 = findViewById<Button>(R.id.btnAddPic1)
        btn_takepic1 = findViewById<Button>(R.id.btnTakePic1)
        btn_opnalbum1 = findViewById<Button>(R.id.btnOpnAlbum1)

    }//onCreate 초기화

    fun SetListener(){
        btn_addpic1?.setOnClickListener {
            //Toast.makeText(this@MainActivity, "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT).show()
            btn_addpic1!!.visibility = View.INVISIBLE
            btn_takepic1!!.visibility = View.VISIBLE
            btn_opnalbum1!!.visibility = View.VISIBLE
        }
        btn_takepic1?.setOnClickListener {

        }
        btn_opnalbum1?.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            activityResultLauncher.launch(intent)



        }
    }//fun : 리스너 정리용



}