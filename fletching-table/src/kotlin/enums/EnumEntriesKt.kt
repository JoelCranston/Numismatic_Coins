package kotlin.enums

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.0")
@JvmSynthetic
public inline fun <reified T : Enum<T>> enumEntries(): EnumEntries<T> {
   throw new NotImplementedError(null, 1, null);
}

@PublishedApi
@SinceKotlin(version = "1.8")
internal fun <E : Enum<E>> enumEntries(entriesProvider: () -> Array<E>): EnumEntries<E> {
   return new EnumEntriesList(entriesProvider.invoke() as Array<java.lang.Enum>);
}

@PublishedApi
@SinceKotlin(version = "1.8")
internal fun <E : Enum<E>> enumEntries(entries: Array<E>): EnumEntries<E> {
   return new EnumEntriesList(entries);
}
