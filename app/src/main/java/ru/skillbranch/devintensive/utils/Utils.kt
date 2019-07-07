package ru.skillbranch.devintensive.utils

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

    fun transliteration(payload: String, divider:String = " "): String {
        val transliteration = hashMapOf("а" to "a", "б" to "b","в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e",
        "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o",
        "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch",
        "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

        val parts: List<String> = payload.split(divider)
        val engParts : MutableList<String> = arrayListOf()
        for (part:String in parts)
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
    val initials = when {
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> "${firstName.first()}${lastName.first()}"
        firstName.isNullOrBlank()  -> if (!lastName.isNullOrBlank()) {"${lastName.first().toString()}"} else null
        else -> firstName.first().toString()
        }
        return initials?.toUpperCase()
    }
}