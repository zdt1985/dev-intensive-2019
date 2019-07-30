package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {
    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()

    init {
        Log.d("M_ProfileViewModel", "init view model")
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("M_ProfileViewModel", "view model cleared")
    }

    fun getProfileData():LiveData<Profile> = profileData

    fun getTheme():LiveData<Int> = appTheme

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        }else{
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

    fun isValidRepoURL(verifiable: String): Boolean {
        val invalidWords = ArrayList<String>()
        invalidWords.add("enterprise")
        invalidWords.add("features")
        invalidWords.add("topics")
        invalidWords.add("collections")
        invalidWords.add("trending")
        invalidWords.add("events")
        invalidWords.add("marketplace")
        invalidWords.add("pricing")
        invalidWords.add("nonprofit")
        invalidWords.add("customer-stories")
        invalidWords.add("security")
        invalidWords.add("login")
        invalidWords.add("join")


        var isValid = true

        val regex = Regex("(https://)?(www.)?github.com/([\'w'-]*[^/])")
        if (regex.matches(verifiable)) {
//            for (i in invalidWords) {
//                val regex3 = Regex(i)
//                if (!regex3.containsMatchIn(verifiable)) {
//                    isValid = false
//                    break
//                }
//            }
        } else {
            isValid = false
        }
        return isValid
    }
}