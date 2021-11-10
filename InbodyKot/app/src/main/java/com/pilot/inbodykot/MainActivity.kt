package com.pilot.inbodykot

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.R.attr
import android.app.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import android.R.attr.bitmap
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


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


    var ImnageData2: Uri? = null
    var ImnageData3: Uri? = null

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String

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
            var bit0 : Bitmap? = null
            var bit1 = img_view1?.drawable?.toBitmap()
            // var bit1 = img_view1?.let { it1 -> viewToBitmap(it1) }
            var bit2 = img_view2?.drawable?.toBitmap()

            bit0 = combineBitmaps(bit1!!,bit2!!)


           /* var width : Int = 0
            var height : Int = 0

            if (bit1 != null && bit2!=null) {
                if(bit1.width>bit2.width){
                    width = bit1.width + bit2.width
                    height = bit1.width
                }else{
                    width = bit1.width + bit2.width
                    height = bit2.width
                }
            }

            bit0 = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            val comboImage = Canvas(bit0)
            if (bit1 != null) {
                comboImage.drawBitmap(bit1, 0f, 0f, null)
            };
            if (bit2 != null && bit1 != null) {
                comboImage.drawBitmap(bit2, bit1.width.toFloat(), 0f, null)
            };*/

            img_view2?.setImageBitmap(bit0)

            MediaStore.Images.Media.insertImage(
                this.contentResolver,
                bit0,
              "BeforeNAfter_0",""
            )


           /* var storage = Environment.getExternalStorageDirectory().absolutePath + "/path/"
            var fileName : String = "BeforeNAfter_0.jpg"
            var imagePath = storage+fileName

            var imgFile = File(imagePath)

            try{
                imgFile.createNewFile()
                var out : FileOutputStream = FileOutputStream(imgFile)
                bit0.compress(Bitmap.CompressFormat.JPEG,100,out)
                out.close()

            }catch(e: IOException){

            }*/

        }



    }//fun : 리스너 정리용

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
            REQUEST_IMAGE_CAPTURE)
    }

    // 카메라 권한 체크
    private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    // 권한요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "Permission: " + permissions[0] + "was " + grantResults[0] + "카메라 승인")
        }else{
            Log.d("TAG","카메라 실패")
        }
    }
    private fun combineBitmaps(left: Bitmap, right: Bitmap): Bitmap? {
        // Get the size of the images combined side by side.
        val width = left.width + right.width
        val height = if (left.height > right.height) left.height else right.height

        val combined = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(combined)

        canvas.drawBitmap(left, 0f, 0f, null)
        canvas.drawBitmap(right, left.width.toFloat(), 0f, null)
        return combined
    }

    // 카메라 열기
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                // 찍은 사진을 그림파일로 만들기
                val photoFile: File? =
                    try {
                        createImageFile()
                    } catch (ex: IOException) {
                        Log.d("TAG", "그림파일 만드는도중 에러생김")
                        null
                    }

                // 그림파일을 성공적으로 만들었다면 onActivityForResult로 보내기
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this, "com.pilot.inbodykot.fileprovider", it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }


    // 카메라로 촬영한 이미지를 파일로 저장해준다
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY1) {
                var ImnageData: Uri? = data?.data
                ImnageData2 = ImnageData

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
                ImnageData3 = ImnageData
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