package kotlinx.coroutines.internal

internal fun <T> commonThreadLocal(name: Symbol): ThreadLocal<T> {
   return new ThreadLocal();
}
