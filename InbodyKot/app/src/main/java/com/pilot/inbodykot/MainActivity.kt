package com.pilot.inbodykot

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    var btn_addpic2: Button? = null
    var btn_takepic2: Button? = null
    var btn_opnalbum2: Button? = null

    var btn_savepic: Button? = null

    var img_view1: ImageView? = null
    var img_view2: ImageView? = null
    var text_before: TextView? = null
    var text_after: TextView? = null
    private val GALLERY1 = 1
    private val GALLERY2 = 2


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

        btn_addpic2 = findViewById<Button>(R.id.btnAddPic2)
        btn_takepic2 = findViewById<Button>(R.id.btnTakePic2)
        btn_opnalbum2 = findViewById<Button>(R.id.btnOpnAlbum2)

        img_view1 = findViewById(R.id.imageView1)
        img_view2 = findViewById<ImageView>(R.id.imageView2)

        text_before = findViewById<TextView>(R.id.textBefore)
        text_after = findViewById<TextView>(R.id.textAfter)
        btn_savepic = findViewById<Button>(R.id.btnSavePic)

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
            startActivityForResult(intent,GALLERY1)
        }


        btn_addpic2?.setOnClickListener {
            //Toast.makeText(this@MainActivity, "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT).show()
            btn_addpic2!!.visibility = View.INVISIBLE
            btn_takepic2!!.visibility = View.VISIBLE
            btn_opnalbum2!!.visibility = View.VISIBLE
        }
        btn_takepic2?.setOnClickListener {

        }
        btn_opnalbum2?.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,GALLERY2)
        }

        btn_savepic?.setOnClickListener {


        }



    }//fun : 리스너 정리용

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY1) {
                var ImnageData: Uri? = data?.data
                //Toast.makeText(this,ImnageData.toString(), Toast.LENGTH_SHORT ).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    img_view1?.setImageBitmap(bitmap)
                    btn_takepic1!!.visibility = View.INVISIBLE
                    btn_opnalbum1!!.visibility = View.INVISIBLE
                    text_before!!.visibility=View.VISIBLE

                    if((text_after!!.visibility==View.VISIBLE) && (text_before!!.visibility==View.VISIBLE)){
                        btn_savepic!!.setBackgroundColor(titleColor)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }else if(requestCode == GALLERY2){
                var ImnageData: Uri? = data?.data
                //Toast.makeText(this,ImnageData.toString(), Toast.LENGTH_SHORT ).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    img_view2?.setImageBitmap(bitmap)
                    btn_takepic2!!.visibility = View.INVISIBLE
                    btn_opnalbum2!!.visibility = View.INVISIBLE
                    text_after!!.visibility=View.VISIBLE
                    if((text_after!!.visibility==View.VISIBLE) && (text_before!!.visibility==View.VISIBLE)){
                        btn_savepic!!.setBackgroundColor(Color.BLUE)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
    }




}