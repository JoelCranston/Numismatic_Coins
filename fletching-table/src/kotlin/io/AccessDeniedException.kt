package kotlin.io

import java.io.File

public class AccessDeniedException(file: File, other: File? = null, reason: String? = null) : FileSystemException(file, other, reason)
