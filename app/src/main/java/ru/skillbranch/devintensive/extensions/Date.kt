package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

/**
 * Created by Denis on 02.07.2019.
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time +=when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
    }
    this.time = time
    return this
}

public fun Date.humanizeDiff(date: Date = Date()): String {
    return when (val timeDifference = (abs(this.time)-abs(date.time) )/1000) {
            in 0..1 -> {"только что"}
            in 2..45 -> {"через несколько секунд"}
            in 46..75 -> {"через минуту"}
            in 76..2700 -> {
                val n = timeDifference/60
                "через $n ${getDeclinations(n,TimeUnits.MINUTE)}"
            }
            in 2701..4500 -> {
                println("через час $timeDifference")
                "через час"
            }
            in 4501..79200 -> {
                val n = timeDifference/3600
                "через $n ${getDeclinations(n,TimeUnits.HOUR)}"
            }
            in 79201..93600 -> {"через день"}
            in 93601..31104000 -> {
                val n = timeDifference/86400
                "через $n ${getDeclinations(n,TimeUnits.DAY)}"
            }
            -1L -> {"только что"}
            in -45..-2 -> {"несколько секунд назад"}
            in -76..-46 -> {"минуту назад"}
            in -2700..-76 -> {
                val n = timeDifference/60*-1
                "$n ${getDeclinations(n,TimeUnits.MINUTE)} назад"
            }
            in -4500..-2701 -> {"час назад"}
            in -79200..-4500 -> {
                val n = timeDifference/3600*-1
                    "$n ${getDeclinations(n,TimeUnits.HOUR)} назад"
            }
            in -93600..-79201 -> {"день назад"}
            in -31104000..-93601 -> {
                val n = timeDifference/86400*-1
                "$n ${getDeclinations(n,TimeUnits.DAY)} назад"
            }
            else -> {
                if (timeDifference > 0) {
                    "более чем через год"
                } else {
                    "более года назад"
                }
            }
        }
    }

fun getDeclinations(number:Long, unitOfTime:TimeUnits) : String {
    return when(unitOfTime) {
        TimeUnits.MINUTE -> when(number) {
            1L -> "минута"
            in 2..4 ->"минуты"
            else ->"минут"
        }
        TimeUnits.HOUR -> when(number) {
            1L -> "час"
            in 2..4 ->"часа"
            else ->"часов"
        }
        TimeUnits.DAY -> when(number) {
            1L -> "день"
            in 2..4 ->"дня"
            else ->"дней"
        }
        else -> throw IllegalArgumentException()
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val lastNumber = getLastNumber(value)
        return when(this.name) {
            "SECOND" -> when(lastNumber) {
                1 -> "$value секунду"
                in 2..4 -> "$value секунды"
                else -> "$value секунд"
            }
            "MINUTE" -> when(lastNumber) {
                1 -> "$value минуту"
                in 2..4 -> "$value минуты"
                else -> "$value минут"
            }
            "HOUR" -> when(lastNumber) {
                1 -> "$value час"
                in 2..4 -> "$value часа"
                else -> "$value часов"
            }
            else -> when(lastNumber) {
                1 -> "$value день"
                in 2..4 -> "$value дня"
                else -> "$value дней"
            }
        }
    }

    fun getLastNumber(value:Int) : Int {
        val numberString = value.toString()
        return numberString[numberString.length-1].toString().toInt()
    }

}