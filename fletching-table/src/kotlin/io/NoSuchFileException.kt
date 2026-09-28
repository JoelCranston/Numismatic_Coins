package kotlin.io

import java.io.File

public class NoSuchFileException(file: File, other: File? = null, reason: String? = null) : FileSystemException(file, other, reason)
