package kotlin.io

import java.io.File

public class FileAlreadyExistsException(file: File, other: File? = null, reason: String? = null) : FileSystemException(file, other, reason)
