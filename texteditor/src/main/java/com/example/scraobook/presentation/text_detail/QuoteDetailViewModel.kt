package com.example.scraobook.presentation.text_detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResultRegistry
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abner.stickerdemo.view.BubbleTextView
import com.example.abner.stickerdemo.view.StickerView
import com.example.scraobook.BuildConfig
import com.example.scraobook.data.model.StickerFramesDTO
import com.example.scraobook.domain.repository.StickerFrameRepository
import com.example.scraobook.domain.use_case.quoteDetailsUC.QuoteDetailsUseCases
import com.example.scraobook.util.UrlToBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.scraobook.util.SaveViewAsBitmap
import kotlinx.coroutines.flow.*

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(private val useCases: QuoteDetailsUseCases,
                            private val stickerFrameRepository: StickerFrameRepository,
                            private val registry: ActivityResultRegistry,
                            private val appCompatActivity: AppCompatActivity,
                            private val colorPicker: ColorPicker,
                            val saveViewAsBitmap: SaveViewAsBitmap) : ViewModel() {

    private val _selectedQuoteText = MutableStateFlow("This is default text")
    val selectedQuoteText: StateFlow<String> = _selectedQuoteText

    private var stickerFrameLiveData: MutableLiveData<StickerFramesDTO> = MutableLiveData()
    lateinit var cameraImgUri: Uri

    private val _sharedFlowAddAny = MutableSharedFlow<Any>()
    val sharedFlowAddAny = _sharedFlowAddAny.asSharedFlow()

    private val _sharedFlowDeleteView = MutableSharedFlow<View>()
    val sharedFlowDeleteView = _sharedFlowDeleteView.asSharedFlow()

    private val contractGallery = registry.register("",ActivityResultContracts.GetContent()){
        addStickerToCardView(appCompatActivity.applicationContext,it)
    }
    private val contractCamera = registry.register("",ActivityResultContracts.TakePicture()){
        addStickerToCardView(appCompatActivity.applicationContext,cameraImgUri)
    }

    init {
        GlobalScope.launch {
            val data = stickerFrameRepository.stickerFrameList()
            stickerFrameLiveData.postValue(data)
        }
    }
    private fun addNewItemOnView(any: Any){
        viewModelScope.launch {
            _sharedFlowAddAny.emit(any)
        }
    }
    private fun removeAddedView(view: View){
        viewModelScope.launch {
            _sharedFlowDeleteView.emit(view)
        }
    }
    fun updateSeletedQuotedText(currentQuote: String){
        _selectedQuoteText.value = currentQuote
    }

    fun addText(view: View){
        useCases.addText(view.context){it->
            addStringToView(view.context,it)
        }
    }
    fun addImage(view: View){
        cameraImgUri = createImageURI(view.context)
        useCases.addImage(view.context,contractGallery,contractCamera,cameraImgUri){}
    }
    fun addStickerToCardView(context:Context,uri:Uri){
        val stickerView = StickerView(context)
        stickerView.setImageURI(uri)
        addNewItemOnView(stickerView)
    }
    fun addFrame(view: View){
        useCases.addFrame(view.context){
            changeURLToBitmap(view.context,it)
        }
    }
    fun addSticker(view: View) {
        useCases.addSticker(view.context) { it ->
            changeURLToBitmap(view.context,it)
        }
    }

    fun changeQuote(view: View){
        useCases.changeQuote(){
            addStringToView(view.context,it)
        }
    }
    fun changeBackgroundColor(view: View){
        colorPicker.showColorPickerDialog(view.context){
            addNewItemOnView(it)
        }
    }
    fun changeBackgroundImage(view: View){
        useCases.changeBackgroundImage() { it ->
            changeURLToBitmap(view.context,it)
        }
    }
    private fun createImageURI(context: Context): Uri {
        val image = File(context.filesDir,"camera_photo.jpg")
        return FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID.toString() + ".provider",
            image
        )
    }
    private fun changeURLToBitmap(context: Context,imageURL:String){
        UrlToBitmap.downloadBitmap(context,imageURL){ bitmap ->
            addBitmapToView(context,bitmap)
        }
    }
    private fun addBitmapToView(context: Context,bitmap: Bitmap?){
        bitmap.let {
            val sticker = StickerView(context)
            sticker.setOperationListener(object : StickerView.OperationListener{
                override fun onDeleteClick() {
                    removeAddedView(sticker)
                }
                override fun onEdit(stickerView: StickerView?) {
                }
                override fun onTop(stickerView: StickerView?) {
                }
            })
            sticker.setBitmap(bitmap)
            addNewItemOnView(sticker)
        }
    }
    private fun addStringToView(context: Context,string: String){
        val tv_sticker = BubbleTextView(context,Color.WHITE,0)
        tv_sticker.setOperationListener(object : BubbleTextView.OperationListener{
            override fun onDeleteClick() {
                removeAddedView(tv_sticker)
            }
            override fun onEdit(bubbleTextView: BubbleTextView?) {
            }
            override fun onClick(bubbleTextView: BubbleTextView?) {
            }
            override fun onTop(bubbleTextView: BubbleTextView?) {
            }
        })
        tv_sticker.setImageResource(com.example.abner.stickerdemo.R.mipmap.bubble_7_rb)
        tv_sticker.setText(string)
        addNewItemOnView(tv_sticker)
    }
    fun saveImage(context: Context,view: View){
        val savedPath = saveViewAsBitmap(context,view)
        addNewItemOnView(savedPath)
    }
}