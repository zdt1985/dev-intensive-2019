package ru.skillbranch.devintensive.utils

import android.content.Context

/**
 * Created by Denis on 01.07.2019.
 */
object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        if (fullName.isNullOrBlank()) {
             return  null to null
        }
        val  parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String?, divider:String = " "): String = buildString {
        val transliteration = hashMapOf("а" to "a", "б" to "b","в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e",
        "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o",
        "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch",
        "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya", "А" to "A",
        "Б" to "B", "В" to "V", "Г" to "G",  "Д" to "D", "Е" to "E", "Ё" to "E", "Ж" to "Zh", "З" to "Z", "И" to "I",
        "Й" to "I", "К" to "K", "Л" to "L", "М" to "M", "Н" to "N", "О" to "O",  "П" to "P", "Р" to "R", "С" to "S",
        "Т" to "T", "У" to "U", "Ф" to "F", "Х" to "H", "Ц" to "C", "Ч" to "Ch", "Ш" to "Sh", "Щ" to "Sh'", "Ъ" to "",
        "Ы" to "I", "Ь" to "", "Э" to "E", "Ю" to "Yu", "Я" to "Ya")

        val parts: List<String>? = payload?.split(" ")


        val engParts : MutableList<String> = arrayListOf()
        for (part:String in parts!!)
        {
            var engPart = ""
            for (it in part) {
                val symbol:String = transliteration[it.toString().toLowerCase()] ?: it.toString()
                engPart += if (it.isUpperCase()) symbol.substring(0, 1).toUpperCase() else symbol.substring(0, 1).toLowerCase()
                engPart += if (symbol.length > 1) symbol.substring(1) else ""
            }
            engParts.add(engPart)
        }
        return engParts.joinToString(divider)

    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val name = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val surname = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val firstInit = name?.toString() ?: ""
        val secondInit = surname?.toString() ?: ""
        return "$firstInit$secondInit".ifEmpty { null }
    }

    fun convertPxToDp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun convertDpToPx(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.toInt()
    }
}