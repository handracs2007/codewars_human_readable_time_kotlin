import java.lang.StringBuilder

object TimeFormatter {

    private const val secondsInYear = 365 * 24 * 60 * 60
    private const val secondsInDay = 24 * 60 * 60
    private const val secondsInHour = 60 * 60
    private const val secondsInMinute = 60

    fun formatDuration(seconds: Int): String {
        var durationRepresentation = ""

        if (seconds > 0) {
            val stringBuilder = StringBuilder()
            var tempSeconds = seconds

            while (tempSeconds > 0) {
                when {
                    tempSeconds >= secondsInYear -> {
                        val yearCount = tempSeconds / secondsInYear
                        stringBuilder.append(yearCount)
                        stringBuilder.append(if (yearCount > 1) " years, " else " year, ")

                        tempSeconds %= (yearCount * secondsInYear)
                    }

                    tempSeconds >= secondsInDay -> {
                        val daysCount = tempSeconds / secondsInDay
                        stringBuilder.append(daysCount)
                        stringBuilder.append(if (daysCount > 1) " days, " else " day, ")

                        tempSeconds %= (daysCount * secondsInDay)
                    }

                    tempSeconds >= secondsInHour -> {
                        val hoursCount = tempSeconds / secondsInHour
                        stringBuilder.append(hoursCount)
                        stringBuilder.append(if (hoursCount > 1) " hours, " else " hour, ")

                        tempSeconds %= (hoursCount * secondsInHour)
                    }

                    tempSeconds >= secondsInMinute -> {
                        val minutesCount = tempSeconds / secondsInMinute
                        stringBuilder.append(minutesCount)
                        stringBuilder.append(if (minutesCount > 1) " minutes, " else " minute, ")

                        tempSeconds %= (minutesCount * secondsInMinute)
                    }

                    else -> {
                        stringBuilder.append(tempSeconds)
                        stringBuilder.append(if (tempSeconds > 1) " seconds, " else " second, ")

                        tempSeconds = 0
                    }
                }
            }

            // remove the last 2 characters from the string
            durationRepresentation = stringBuilder.removeSuffix(", ").toString().trim()

            // let's replace the last ", " to " and "
            val lastCommaIndex = durationRepresentation.lastIndexOf(", ")

            if (lastCommaIndex != -1) {
                val tempDurationRepresentation = durationRepresentation

                durationRepresentation = tempDurationRepresentation.substring(0, lastCommaIndex)
                durationRepresentation += " and "
                durationRepresentation += tempDurationRepresentation.substring(lastCommaIndex + 2)
            }
        } else {
            durationRepresentation = "now"
        }

        return durationRepresentation
    }
}

fun main() {
    println(TimeFormatter.formatDuration(0))
    println(TimeFormatter.formatDuration(62))
    println(TimeFormatter.formatDuration(120))
    println(TimeFormatter.formatDuration(3662))
}