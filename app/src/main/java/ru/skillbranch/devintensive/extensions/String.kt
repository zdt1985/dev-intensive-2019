package ru.skillbranch.devintensive.extensions


fun String.truncate(i: Int = 16): String? {
    return if(15 > this.length) {
        this.trim()
    } else {
        "${this.substring(0, i).trim()}..."
    }
}

fun String.stripHtml(): String {
    return this.replace(Regex("<[^<]*?>|&(.)+;"),"").
        replace(Regex("[^\\S\\r\\n]+")," ")
}