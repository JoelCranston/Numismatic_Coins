package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.FlowKt__MigrationKt.delayFlow.1
import kotlinx.coroutines.flow.FlowKt__MigrationKt.onErrorReturn.2

@SourceDebugExtension(["SMAP\nMigration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Migration.kt\nkotlinx/coroutines/flow/FlowKt__MigrationKt\n+ 2 Merge.kt\nkotlinx/coroutines/flow/FlowKt__MergeKt\n*L\n1#1,492:1\n189#2:493\n*S KotlinDebug\n*F\n+ 1 Migration.kt\nkotlinx/coroutines/flow/FlowKt__MigrationKt\n*L\n431#1:493\n*E\n"])
@JvmSynthetic
internal class FlowKt__MigrationKt {
   @JvmStatic
   internal fun noImpl(): Nothing {
      throw new UnsupportedOperationException("Not implemented, should not be called");
   }

   @Deprecated(message = "Collect flow in the desired context instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.observeOn(context: CoroutineContext): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Collect flow in the desired context instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.publishOn(context: CoroutineContext): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Use 'flowOn' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.subscribeOn(context: CoroutineContext): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.onErrorResume(fallback: Flow<T>): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.onErrorResumeNext(fallback: Flow<T>): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.subscribe() {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.subscribe(onEach: (T, Continuation<Unit>) -> Any?) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.subscribe(onEach: (T, Continuation<Unit>) -> Any?, onError: (Throwable, Continuation<Unit>) -> Any?) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T, R> Flow<T>.flatMap(mapper: (T, Continuation<Flow<R>>) -> Any?): Flow<R> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'concatMap' is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T, R> Flow<T>.concatMap(mapper: (T) -> Flow<R>): Flow<R> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'merge' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<Flow<T>>.merge(): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'flatten' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<Flow<T>>.flatten(): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'compose' is 'let'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T, R> Flow<T>.compose(transformer: (Flow<T>) -> Flow<R>): Flow<R> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'skip' is 'drop'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.skip(count: Int): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'forEach' is 'collect'", replaceWith = @ReplaceWith(expression = "collect(action)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.forEach(action: (T, Continuation<Unit>) -> Any?) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow has less verbose 'scan' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T, R> Flow<T>.scanFold(initial: R, operation: (R, T, Continuation<R>) -> Any?): Flow<R> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emit(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emit(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.onErrorReturn(fallback: T): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { e -> if (predicate(e)) emit(fallback) else throw e }'", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.onErrorReturn(fallback: T, predicate: (Throwable) -> Boolean = FlowKt__MigrationKt::onErrorReturn$lambda$0$FlowKt__MigrationKt): Flow<
         T
      > {
      return FlowKt.catch(`$this$onErrorReturn`, new 2(predicate, fallback, null));
   }

   @Deprecated(message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emit(value) }'", replaceWith = @ReplaceWith(expression = "onStart { emit(value) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.startWith(value: T): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onStart { emitAll(other) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.startWith(other: Flow<T>): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emit(value) }'", replaceWith = @ReplaceWith(expression = "onCompletion { emit(value) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.concatWith(value: T): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { if (it == null) emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onCompletion { if (it == null) emitAll(other) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.concatWith(other: Flow<T>): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "this.combine(other, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T1, T2, R> Flow<T1>.combineLatest(other: Flow<T2>, transform: (T1, T2, Continuation<R>) -> Any?): Flow<R> {
      return FlowKt.combine(`$this$combineLatest`, other, transform);
   }

   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T1, T2, T3, R> Flow<T1>.combineLatest(other: Flow<T2>, other2: Flow<T3>, transform: (T1, T2, T3, Continuation<R>) -> Any?): Flow<R> {
      return FlowKt.combine(`$this$combineLatest`, other, other2, transform);
   }

   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T1, T2, T3, T4, R> Flow<T1>.combineLatest(
      other: Flow<T2>,
      other2: Flow<T3>,
      other3: Flow<T4>,
      transform: (T1, T2, T3, T4, Continuation<R>) -> Any?
   ): Flow<R> {
      return FlowKt.combine(`$this$combineLatest`, other, other2, other3, transform);
   }

   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T1, T2, T3, T4, T5, R> Flow<T1>.combineLatest(
      other: Flow<T2>,
      other2: Flow<T3>,
      other3: Flow<T4>,
      other4: Flow<T5>,
      transform: (T1, T2, T3, T4, T5, Continuation<R>) -> Any?
   ): Flow<R> {
      return FlowKt.combine(`$this$combineLatest`, other, other2, other3, other4, transform);
   }

   @Deprecated(message = "Use 'onStart { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onStart { delay(timeMillis) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.delayFlow(timeMillis: Long): Flow<T> {
      return FlowKt.onStart(`$this$delayFlow`, new 1(timeMillis, null));
   }

   @Deprecated(message = "Use 'onEach { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onEach { delay(timeMillis) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.delayEach(timeMillis: Long): Flow<T> {
      return FlowKt.onEach(`$this$delayEach`, new kotlinx.coroutines.flow.FlowKt__MigrationKt.delayEach.1(timeMillis, null));
   }

   @Deprecated(message = "Flow analogues of 'switchMap' are 'transformLatest', 'flatMapLatest' and 'mapLatest'", replaceWith = @ReplaceWith(expression = "this.flatMapLatest(transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T, R> Flow<T>.switchMap(transform: (T, Continuation<Flow<R>>) -> Any?): Flow<R> {
      return FlowKt.transformLatest(`$this$switchMap`, new kotlinx.coroutines.flow.FlowKt__MigrationKt.switchMap..inlined.flatMapLatest.1(transform, null));
   }

   @Deprecated(message = "'scanReduce' was renamed to 'runningReduce' to be consistent with Kotlin standard library", replaceWith = @ReplaceWith(expression = "runningReduce(operation)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.scanReduce(operation: (T, T, Continuation<T>) -> Any?): Flow<T> {
      return FlowKt.runningReduce(`$this$scanReduce`, operation);
   }

   @Deprecated(message = "Flow analogue of 'publish()' is 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \npublish().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, 0)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.publish(): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'publish(bufferSize)' is 'buffer' followed by 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \npublish().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.buffer(bufferSize).shareIn(scope, 0)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.publish(bufferSize: Int): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'replay()' is 'shareIn' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.replay(): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'replay(bufferSize)' is 'shareIn' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, bufferSize)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.replay(bufferSize: Int): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(message = "Flow analogue of 'cache()' is 'shareIn' with unlimited replay and 'started = SharingStarted.Lazily' argument'", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, started = SharingStarted.Lazily, replay = Int.MAX_VALUE)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   public fun <T> Flow<T>.cache(): Flow<T> {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @JvmStatic
   fun `onErrorReturn$lambda$0$FlowKt__MigrationKt`(it: java.lang.Throwable): Boolean {
      return true;
   }
}
