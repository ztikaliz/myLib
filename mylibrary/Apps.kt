import android.app.Activity
import android.view.WindowManager

object Apps {
    fun Activity.setAppFullscreenTransparentStatusNavigation(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}