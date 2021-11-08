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
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.sql.RowSetListener

/*Inbody Web 개발 지원자 박상수*/

class MainActivity : AppCompatActivity() {

    var btn_addpic1: Button? = null
    var btn_takepic1: Button? = null
    var btn_opnalbum1: Button? = null

    private val OPEN_GALLERY = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        }
    }//fun : 리스너 정리용


    private fun openGallery(){
        var writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
        // 권한 없어서 요청
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), REQ_STORAGE_PERMISSION) }
        else {
        // 권한 있음
        var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(intent, REQ_GALLERY) }
        }

            출처: https://superwony.tistory.com/101 [개발자 키우기]
    }


}