package ru.skillbranch.devintensive.extensions


fun String.truncate(i: Int = 16): String? {
    val truncatedString = this.substring(0, i).trimEnd()
    return when(i) {
        in 0..14 -> truncatedString
        else -> "$truncatedString..."
    }
}

fun String.stripHtml(): String {
    return this.replace(Regex("<[^<]*?>|&(.)+;"),"").
        replace(Regex("[^\\S\\r\\n]+")," ")
}