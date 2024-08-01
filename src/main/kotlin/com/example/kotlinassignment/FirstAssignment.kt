package com.example.kotlinassignment

class FirstAssignment {
    fun decimalToBase26(number: Int): String {
        var n = number
        val result = StringBuilder()

        while (n > 0) {
            n--  // Adjust to 0-based index
            val remainder = n % 26
            result.append(('A' + remainder))
            n /= 26
        }

        return result.reverse().toString()
    }

    fun generateColumnLabels(start: Int, count: Int): Array<String?> {
        if (start <= 0 || count <= 0) throw IllegalArgumentException("Parameters must be greater than 0")
        if (start >= 18279 || start + count > 18279) throw IllegalArgumentException("Number must be smaller than 18278")

        val labels: Array<String?> = arrayOfNulls(count)
        for (i in 1..count) {
            val column = start + i -1;
            labels[i-1] = decimalToBase26(column)
        }

        return labels
    }
}