package kotlinx.coroutines.flow

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.internal.InlineOnly
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.flow.LintKt.retry.1

@Deprecated(
   message = "isActive is resolved into the extension of outer CoroutineScope which is likely to be an error. Use currentCoroutineContext().isActive or cancellable() operator instead or specify the receiver of isActive explicitly. Additionally, flow {} builder emissions are cancellable by default.",
   replaceWith = @ReplaceWith(
      expression = "currentCoroutineContext().isActive",
      imports = {}
   ),
   level = DeprecationLevel.ERROR
)
public final val isActive: Boolean
   public final get() {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }


@Deprecated(
   message = "coroutineContext is resolved into the property of outer CoroutineScope which is likely to be an error. Use currentCoroutineContext() instead or specify the receiver of coroutineContext explicitly",
   replaceWith = @ReplaceWith(
      expression = "currentCoroutineContext()",
      imports = {}
   ),
   level = DeprecationLevel.ERROR
)
public final val coroutineContext: CoroutineContext
   public final get() {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }


@Deprecated(message = "Applying 'cancellable' to a SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.ERROR)
public fun <T> SharedFlow<T>.cancellable(): Flow<T> {
   FlowKt.noImpl();
   throw new KotlinNothingValueException();
}

@Deprecated(message = "Applying 'flowOn' to SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.ERROR)
public fun <T> SharedFlow<T>.flowOn(context: CoroutineContext): Flow<T> {
   FlowKt.noImpl();
   throw new KotlinNothingValueException();
}

@Deprecated(message = "Applying 'conflate' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.ERROR)
public fun <T> StateFlow<T>.conflate(): Flow<T> {
   FlowKt.noImpl();
   throw new KotlinNothingValueException();
}

@Deprecated(message = "Applying 'distinctUntilChanged' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.ERROR)
public fun <T> StateFlow<T>.distinctUntilChanged(): Flow<T> {
   FlowKt.noImpl();
   throw new KotlinNothingValueException();
}

@Deprecated(message = "cancel() is resolved into the extension of outer CoroutineScope which is likely to be an error. Use currentCoroutineContext().cancel() instead or specify the receiver of cancel() explicitly", replaceWith = @ReplaceWith(expression = "currentCoroutineContext().cancel(cause)", imports = []), level = DeprecationLevel.ERROR)
public fun FlowCollector<*>.cancel(cause: CancellationException? = null) {
   FlowKt.noImpl();
   throw new KotlinNothingValueException();
}

/** @deprecated */
@JvmSynthetic
fun `cancel$default`(var0: FlowCollector, var1: CancellationException, var2: Int, var3: Any) {
   if ((var2 and 1) != 0) {
      var1 = null;
   }

   cancel(var0, var1);
}

@Deprecated(message = "SharedFlow never completes, so this operator typically has not effect, it can only catch exceptions from 'onSubscribe' operator", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.WARNING)
@InlineOnly
public inline fun <T> SharedFlow<T>.catch(noinline action: (FlowCollector<T>, Throwable, Continuation<Unit>) -> Any?): Flow<T> {
   return FlowKt.catch(`$this$catch`, action);
}

@Deprecated(message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.WARNING)
@InlineOnly
public inline fun <T> SharedFlow<T>.retry(
   retries: Long = java.lang.Long.MAX_VALUE,
   noinline predicate: (Throwable, Continuation<Boolean>) -> Any? = (new 1(null)) as Function2
): Flow<T> {
   return FlowKt.retry(`$this$retry`, retries, predicate);
}

/** @deprecated */
@JvmSynthetic
fun SharedFlow.`retry$default`(retries: Long, predicate: Function2, var4: Int, var5: Any): Flow {
   if ((var4 and 1) != 0) {
      retries = java.lang.Long.MAX_VALUE;
   }

   if ((var4 and 2) != 0) {
      predicate = new 1(null);
   }

   return FlowKt.retry(`$this$retry_u24default`, retries, predicate);
}

@Deprecated(message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = []), level = DeprecationLevel.WARNING)
@InlineOnly
public inline fun <T> SharedFlow<T>.retryWhen(noinline predicate: (FlowCollector<T>, Throwable, Long, Continuation<Boolean>) -> Any?): Flow<T> {
   return FlowKt.retryWhen(`$this$retryWhen`, predicate);
}

@Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
@InlineOnly
public suspend inline fun <T> SharedFlow<T>.toList(): List<T> {
   var var10000: Flow = `$this$toList`;
   InlineMarker.mark(0);
   var10000 = (Flow)FlowKt.toList$default(var10000, null, `$completion`, 1, null);
   InlineMarker.mark(1);
   return var10000;
}

@InlineOnly
public suspend inline fun <T> SharedFlow<T>.toList(destination: MutableList<T>): Nothing {
   val var10000: Flow = `$this$toList`;
   InlineMarker.mark(0);
   FlowKt.toList(var10000, destination, `$completion`);
   InlineMarker.mark(1);
   throw new IllegalStateException("this code is supposed to be unreachable");
}

@Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
@InlineOnly
public suspend inline fun <T> SharedFlow<T>.toSet(): Set<T> {
   var var10000: Flow = `$this$toSet`;
   InlineMarker.mark(0);
   var10000 = (Flow)FlowKt.toSet$default(var10000, null, `$completion`, 1, null);
   InlineMarker.mark(1);
   return var10000;
}

@InlineOnly
public suspend inline fun <T> SharedFlow<T>.toSet(destination: MutableSet<T>): Nothing {
   val var10000: Flow = `$this$toSet`;
   InlineMarker.mark(0);
   FlowKt.toSet(var10000, destination, `$completion`);
   InlineMarker.mark(1);
   throw new IllegalStateException("this code is supposed to be unreachable");
}

@Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
@InlineOnly
public suspend inline fun <T> SharedFlow<T>.count(): Int {
   var var10000: Flow = `$this$count`;
   InlineMarker.mark(0);
   var10000 = (Flow)FlowKt.count(var10000, `$completion`);
   InlineMarker.mark(1);
   return var10000;
}
