package io.ktor.util.converters

public open class DataConversionException(message: String = "Invalid data format") : Exception(message) {
   open fun DataConversionException() {
      this(null, 1, null);
   }
}
