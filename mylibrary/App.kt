import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.view.WindowManager
import java.io.IOException
import java.io.InputStream
import java.util.Random
import android.app.ActionBar
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.widget.TextView



/**Note:
░▒▓█▓▒░ Các fun có Context/Activity trước tên fun là dạng Extension fun của class Context/Activity
░▒▓█▓▒░ Có thể bỏ các fun đó bên ngoài tên class (tạo class kotlin sau đó bỏ các fun này vào bên ngoài class
(xóa text tên class nếu muốn))
░▒▓█▓▒░ Sau đó dùng nó như 1 fun mặc định
Vd:
setAppScreenOrientation(true)
setAppStatusBarColor(R.color.teal_200)
 **/
sealed class App {
    companion object{
        fun Activity.setAppFullscreenTransparentStatusNavigation(){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        fun Context.makeSoundFromAssets(){
            val mediaPlayer = MediaPlayer()
            val afd: AssetFileDescriptor
            try {
                afd = assets.openFd("sound/bit.mp3")
                mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd.close()
                mediaPlayer.prepare()
                mediaPlayer.start()
            }catch(_: IOException){}
        }

        fun Activity.setAppScreenOrientation(isPortrait: Boolean){
            requestedOrientation = if (isPortrait) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

        /**input: R.color.black**/
        fun Activity.setAppStatusBarColor(rColor: Int) {
            window.statusBarColor = ContextCompat.getColor(this, rColor)
        }

        /**input: R.color.black**/
        fun Activity.setAppNavigationBarColor(rColor: Int) {
            window.navigationBarColor = ContextCompat.getColor(this, rColor)
        }

        /**input: R.color.black**/
        fun AppCompatActivity.setAppActionBarColor(rColor: Int) {
            val actionBar = this.supportActionBar
            val colorDrawable = ColorDrawable(this.getColor(rColor))
            actionBar?.setBackgroundDrawable(colorDrawable)
        }

        fun AppCompatActivity.setAppActionBarTitle(text: String){
            title= text
        }

        fun AppCompatActivity.setAppActionBarTitleGravityCenter(text: String){
            /**Get a reference to the ActionBar**/
            val actionBar = supportActionBar
            actionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM

            /**Create a new TextView programmatically**/
            val textView = TextView(this)
            textView.text = text
            textView.textSize = 20f
            textView.setTextColor(Color.WHITE)
            textView.gravity = Gravity.CENTER

            /**Create LayoutParams for the TextView**/
            val params = androidx.appcompat.app.ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )

            /**Set the custom view to the ActionBar**/
            actionBar?.setCustomView(textView, params)
        }

        /**fileNamed= "testOnly.txt" or fileNamed= "packageName/testOnly.txt"**/
        fun Activity.readAssets(fileNamed: String): String{
            return try {
                val myOutput: String
                val fileInputStream: InputStream = assets.open(fileNamed)
                val size: Int = fileInputStream.available()
                val buffer = ByteArray(size)
                fileInputStream.read(buffer)
                myOutput = String(buffer)

                myOutput
            } catch (e: IOException) {
                return e.toString()
            }
        }

        fun Activity.setAppEdittextInputAdjustPan(){
            this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        }

    }

    object Check{
        fun isNumber(str: String): Boolean = str
            .removePrefix("-")
            .removePrefix("+")
            .all { it in '0'..'9' }
    }

    object Get{
        fun randomNumberNextInt(int: Int): Int{
            val random= Random()
            return random.nextInt(int)
        }
    }
}