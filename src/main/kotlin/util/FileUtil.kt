package util

import java.io.File

fun fetchData(path: String): List<String> {
    val file = File(path)

    return file.readLines()
}