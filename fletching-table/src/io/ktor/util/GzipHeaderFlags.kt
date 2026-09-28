package io.ktor.util

private object GzipHeaderFlags {
   public const val FTEXT: Int = 1
   public const val FHCRC: Int = 2
   public const val EXTRA: Int = 4
   public const val FNAME: Int = 8
   public const val FCOMMENT: Int = 16
}
