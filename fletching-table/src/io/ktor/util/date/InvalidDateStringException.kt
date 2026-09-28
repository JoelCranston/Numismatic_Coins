package io.ktor.util.date

public class InvalidDateStringException(data: String, at: Int, pattern: String) : IllegalStateException(
      "Failed to parse date string: \"$data\" at index $at. Pattern: \"$pattern""
   )
