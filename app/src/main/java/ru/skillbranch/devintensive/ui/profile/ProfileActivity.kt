package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity() {

    /**
     * Вызывается при первом создании или перезапуске Activity.
     *
     * здесь задаётся внешний вил активности (UI) через метод setContentView().
     * инициализируются с необходимыми данными и ресурсами
     * связываются данные со списками
     *
     * Этот метод также представляет Bundle, содержащий ранее сохраненное
     * состояние Activity, если оно было.
     *
     * Всегда сопровождается вызовом onStart().
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

      /**
     * Этот метод сохраняет  представления в Bundle
     * Для API Level < 28 (Android P) этот метод будет выполняться до onStop(), и нет никаких гарантий относительно того,
     * произойдет ли это до или после onPause().
     * Для API Level >= 28 будет вызван после onStop()
     * Не будет вызван если Activity будет явно закрыто пользвователем при нажатии на системную клавишу back
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

}
