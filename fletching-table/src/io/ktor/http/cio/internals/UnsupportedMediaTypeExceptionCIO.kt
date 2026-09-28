package io.ktor.http.cio.internals

import io.ktor.utils.io.InternalAPI
import java.io.IOException

@InternalAPI
public class UnsupportedMediaTypeExceptionCIO(message: String) : IOException(message)
