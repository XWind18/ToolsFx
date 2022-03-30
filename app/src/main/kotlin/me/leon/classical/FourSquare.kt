package me.leon.classical

import me.leon.ext.TABLE_A_Z_WO_J

/** ported from https://github.com/jameslyons/pycipher/blob/master/pycipher/foursquare.py */
fun String.fourSquare(key1: String, key2: String): String {
    val properKey1 = key1.uppercase().filter { it.isUpperCase() }
    val properKey2 = key2.uppercase().filter { it.isUpperCase() }
    if (properKey1.length != 25 || properKey2.length != 25) {
        return "Key must be 25 characters long"
    }
    return uppercase()
        .filter { it.isUpperCase() }
        .padEnd(2, 'X')
        .chunked(2)
        .fold(StringBuilder()) { acc, char ->
            val pairA = TABLE_A_Z_WO_J.indexOf(char[0]) / 5 to TABLE_A_Z_WO_J.indexOf(char[0]) % 5
            val pairB = TABLE_A_Z_WO_J.indexOf(char[1]) / 5 to TABLE_A_Z_WO_J.indexOf(char[1]) % 5
            acc.append(properKey1[pairA.first * 5 + pairB.second].toString())
                .append(properKey2[pairB.first * 5 + pairA.second].toString())
        }
        .toString()
}

fun String.fourSquareDecrypt(key1: String, key2: String): String {
    val properKey1 = key1.uppercase().filter { it.isUpperCase() }
    val properKey2 = key2.uppercase().filter { it.isUpperCase() }
    if (properKey1.length != 25 || properKey2.length != 25) {
        return "Key must be 25 characters long"
    }
    return uppercase()
        .filter { it.isUpperCase() }
        .padEnd(2, 'X')
        .chunked(2)
        .fold(StringBuilder()) { acc, char ->
            val pairA = properKey1.indexOf(char[0]) / 5 to properKey1.indexOf(char[0]) % 5
            val pairB = properKey2.indexOf(char[1]) / 5 to properKey2.indexOf(char[1]) % 5
            acc.append(TABLE_A_Z_WO_J[pairA.first * 5 + pairB.second].toString())
                .append(TABLE_A_Z_WO_J[pairB.first * 5 + pairA.second].toString())
        }
        .toString()
}