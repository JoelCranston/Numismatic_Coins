package kotlin.enums

import kotlin.internal.InlineOnly

@SinceKotlin(version = "1.9")
@PublishedApi
@InlineOnly
internal inline fun <T : Enum<T>> enumEntriesIntrinsic(): EnumEntries<T> {
   throw new NotImplementedError(null, 1, null);
}
