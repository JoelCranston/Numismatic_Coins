package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel

// $VF: Class flags could not be determined
internal class FlowKt {
   @JvmStatic
   public java.lang.String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

   @JvmStatic
   fun <T> flow(@BuilderInference block: (FlowCollector<? super T>?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__BuildersKt.flow(block);
   }

   @JvmStatic
   fun <T> (() -> T).asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> ((Continuation<? super T>?) -> Any).asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> MutableIterable<T>.asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> MutableIterator<T>.asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> Sequence<? extends T>.asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> flowOf(vararg elements: T): Flow<T> {
      return FlowKt__BuildersKt.flowOf((T[])elements);
   }

   @JvmStatic
   fun <T> flowOf(value: T): Flow<T> {
      return FlowKt__BuildersKt.flowOf((T)value);
   }

   @JvmStatic
   fun <T> emptyFlow(): Flow<T> {
      return FlowKt__BuildersKt.emptyFlow();
   }

   @JvmStatic
   fun <T> Array<T>.asFlow(): Flow<T> {
      return FlowKt__BuildersKt.asFlow((T[])`$this$asFlow`);
   }

   @JvmStatic
   fun IntArray.asFlow(): Flow<Integer> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun LongArray.asFlow(): Flow<java.lang.Long> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun IntRange.asFlow(): Flow<Integer> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun LongRange.asFlow(): Flow<java.lang.Long> {
      return FlowKt__BuildersKt.asFlow(`$this$asFlow`);
   }

   @JvmStatic
   fun <T> channelFlow(@BuilderInference block: (ProducerScope<? super T>?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__BuildersKt.channelFlow(block);
   }

   @JvmStatic
   fun <T> callbackFlow(@BuilderInference block: (ProducerScope<? super T>?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__BuildersKt.callbackFlow(block);
   }

   @JvmStatic
   fun <T> FlowCollector<? super T>.emitAll(channel: ReceiveChannel<? extends T>, `$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__ChannelsKt.emitAll(`$this$emitAll`, channel, `$completion`);
   }

   @JvmStatic
   fun <T> ReceiveChannel<? extends T>.receiveAsFlow(): Flow<T> {
      return FlowKt__ChannelsKt.receiveAsFlow(`$this$receiveAsFlow`);
   }

   @JvmStatic
   fun <T> ReceiveChannel<? extends T>.consumeAsFlow(): Flow<T> {
      return FlowKt__ChannelsKt.consumeAsFlow(`$this$consumeAsFlow`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.produceIn(scope: CoroutineScope): ReceiveChannel<T> {
      return FlowKt__ChannelsKt.produceIn(`$this$produceIn`, scope);
   }

   @JvmStatic
   fun Flow<?>.collect(`$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__CollectKt.collect(`$this$collect`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.launchIn(scope: CoroutineScope): Job {
      return FlowKt__CollectKt.launchIn(`$this$launchIn`, scope);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.collectIndexed(action: (Int?, T?, Continuation<? super Unit>?) -> Any, `$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__CollectKt.collectIndexed(`$this$collectIndexed`, action, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.collectLatest(action: (T?, Continuation<? super Unit>?) -> Any, `$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__CollectKt.collectLatest(`$this$collectLatest`, action, `$completion`);
   }

   @JvmStatic
   fun <T> FlowCollector<? super T>.emitAll(flow: Flow<? extends T>, `$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__CollectKt.emitAll(`$this$emitAll`, flow, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.toList(destination: MutableList<T>, `$completion`: Continuation<? super java.utilList<? extends T>>): Any? {
      return FlowKt__CollectionKt.toList(`$this$toList`, destination, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.toSet(destination: MutableSet<T>, `$completion`: Continuation<? super java.utilSet<? extends T>>): Any? {
      return FlowKt__CollectionKt.toSet(`$this$toSet`, destination, `$completion`);
   }

   @JvmStatic
   fun <T, C extends java.util.Collection<? super T>> Flow<? extends T>.toCollection(destination: C, `$completion`: Continuation<? super C>): Any? {
      return FlowKt__CollectionKt.toCollection(`$this$toCollection`, (C)destination, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.buffer(capacity: Int, onBufferOverflow: BufferOverflow): Flow<T> {
      return FlowKt__ContextKt.buffer(`$this$buffer`, capacity, onBufferOverflow);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.conflate(): Flow<T> {
      return FlowKt__ContextKt.conflate(`$this$conflate`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.flowOn(context: CoroutineContext): Flow<T> {
      return FlowKt__ContextKt.flowOn(`$this$flowOn`, context);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.cancellable(): Flow<T> {
      return FlowKt__ContextKt.cancellable(`$this$cancellable`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.count(`$completion`: Continuation<? super Integer>): Any? {
      return FlowKt__CountKt.count(`$this$count`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.count(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super Integer>): Any? {
      return FlowKt__CountKt.count(`$this$count`, predicate, `$completion`);
   }

   @FlowPreview
   @JvmStatic
   fun <T> Flow<? extends T>.debounce(timeoutMillis: Long): Flow<T> {
      return FlowKt__DelayKt.debounce(`$this$debounce`, timeoutMillis);
   }

   @FlowPreview
   @OverloadResolutionByLambdaReturnType
   @JvmStatic
   fun <T> Flow<? extends T>.debounce(timeoutMillis: (T?) -> java.lang.Long): Flow<T> {
      return FlowKt__DelayKt.debounce(`$this$debounce`, timeoutMillis);
   }

   @FlowPreview
   @JvmStatic
   fun <T> Flow<? extends T>.`debounce-HG0u8IE`(timeout: Long): Flow<T> {
      return FlowKt__DelayKt.debounce-HG0u8IE(`$this$debounce_u2dHG0u8IE`, timeout);
   }

   @FlowPreview
   @JvmName(name = "debounceDuration")
   @OverloadResolutionByLambdaReturnType
   @JvmStatic
   fun <T> Flow<? extends T>.debounceDuration(timeout: (T?) -> Duration): Flow<T> {
      return FlowKt__DelayKt.debounceDuration(`$this$debounce`, timeout);
   }

   @FlowPreview
   @JvmStatic
   fun <T> Flow<? extends T>.sample(periodMillis: Long): Flow<T> {
      return FlowKt__DelayKt.sample(`$this$sample`, periodMillis);
   }

   @JvmStatic
   fun CoroutineScope.fixedPeriodTicker(delayMillis: Long): ReceiveChannel<Unit> {
      return FlowKt__DelayKt.fixedPeriodTicker(`$this$fixedPeriodTicker`, delayMillis);
   }

   @FlowPreview
   @JvmStatic
   fun <T> Flow<? extends T>.`sample-HG0u8IE`(period: Long): Flow<T> {
      return FlowKt__DelayKt.sample-HG0u8IE(`$this$sample_u2dHG0u8IE`, period);
   }

   @FlowPreview
   @JvmStatic
   fun <T> Flow<? extends T>.`timeout-HG0u8IE`(timeout: Long): Flow<T> {
      return FlowKt__DelayKt.timeout-HG0u8IE(`$this$timeout_u2dHG0u8IE`, timeout);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.distinctUntilChanged(): Flow<T> {
      return FlowKt__DistinctKt.distinctUntilChanged(`$this$distinctUntilChanged`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.distinctUntilChanged(areEquivalent: (T?, T?) -> java.lang.Boolean): Flow<T> {
      return FlowKt__DistinctKt.distinctUntilChanged(`$this$distinctUntilChanged`, areEquivalent);
   }

   @JvmStatic
   fun <T, K> Flow<? extends T>.distinctUntilChangedBy(keySelector: (T?) -> K): Flow<T> {
      return FlowKt__DistinctKt.distinctUntilChangedBy(`$this$distinctUntilChangedBy`, keySelector);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.transform(@BuilderInference transform: (FlowCollector<? super R>?, T?, Continuation<? super Unit>?) -> Any): Flow<R> {
      return FlowKt__EmittersKt.transform(`$this$transform`, transform);
   }

   @PublishedApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.unsafeTransform(@BuilderInference transform: (FlowCollector<? super R>?, T?, Continuation<? super Unit>?) -> Any): Flow<R> {
      return FlowKt__EmittersKt.unsafeTransform(`$this$unsafeTransform`, transform);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.onStart(action: (FlowCollector<? super T>?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__EmittersKt.onStart(`$this$onStart`, action);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.onCompletion(action: (FlowCollector<? super T>?, java.lang.Throwable?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__EmittersKt.onCompletion(`$this$onCompletion`, action);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.onEmpty(action: (FlowCollector<? super T>?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__EmittersKt.onEmpty(`$this$onEmpty`, action);
   }

   @JvmStatic
   fun FlowCollector<?>.ensureActive() {
      FlowKt__EmittersKt.ensureActive(`$this$ensureActive`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.catch(action: (FlowCollector<? super T>?, java.lang.Throwable?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__ErrorsKt.catch(`$this$catch`, action);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.retry(retries: Long, predicate: (java.lang.Throwable?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<T> {
      return FlowKt__ErrorsKt.retry(`$this$retry`, retries, predicate);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.retryWhen(
      predicate: (FlowCollector<? super T>?, java.lang.Throwable?, java.lang.Long?, Continuation<? super java.lang.Boolean>?) -> Any
   ): Flow<T> {
      return FlowKt__ErrorsKt.retryWhen(`$this$retryWhen`, predicate);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.catchImpl(collector: FlowCollector<? super T>, `$completion`: Continuation<? super java.lang.Throwable>): Any? {
      return FlowKt__ErrorsKt.catchImpl(`$this$catchImpl`, collector, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.drop(count: Int): Flow<T> {
      return FlowKt__LimitKt.drop(`$this$drop`, count);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.dropWhile(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<T> {
      return FlowKt__LimitKt.dropWhile(`$this$dropWhile`, predicate);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.take(count: Int): Flow<T> {
      return FlowKt__LimitKt.take(`$this$take`, count);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.takeWhile(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<T> {
      return FlowKt__LimitKt.takeWhile(`$this$takeWhile`, predicate);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.transformWhile(@BuilderInference transform: (FlowCollector<? super R>?, T?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<R> {
      return FlowKt__LimitKt.transformWhile(`$this$transformWhile`, transform);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.collectWhile(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super Unit>): Any? {
      return FlowKt__LimitKt.collectWhile(`$this$collectWhile`, predicate, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.any(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super java.lang.Boolean>): Any? {
      return FlowKt__LogicKt.any(`$this$any`, predicate, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.all(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super java.lang.Boolean>): Any? {
      return FlowKt__LogicKt.all(`$this$all`, predicate, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.none(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super java.lang.Boolean>): Any? {
      return FlowKt__LogicKt.none(`$this$none`, predicate, `$completion`);
   }

   @JvmStatic
   fun getDEFAULT_CONCURRENCY(): Int {
      return FlowKt__MergeKt.getDEFAULT_CONCURRENCY();
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.flatMapConcat(transform: (T?, Continuation<? super Flow<? extends R>>?) -> Any): Flow<R> {
      return FlowKt__MergeKt.flatMapConcat(`$this$flatMapConcat`, transform);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.flatMapMerge(concurrency: Int, transform: (T?, Continuation<? super Flow<? extends R>>?) -> Any): Flow<R> {
      return FlowKt__MergeKt.flatMapMerge(`$this$flatMapMerge`, concurrency, transform);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T> Flow<? extends Flow<? extends T>>.flattenConcat(): Flow<T> {
      return FlowKt__MergeKt.flattenConcat(`$this$flattenConcat`);
   }

   @JvmStatic
   fun <T> MutableIterable<Flow<? extends T>>.merge(): Flow<T> {
      return FlowKt__MergeKt.merge(`$this$merge`);
   }

   @JvmStatic
   fun <T> merge(vararg flows: Flow<? extends T>): Flow<T> {
      return FlowKt__MergeKt.merge(flows);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T> Flow<? extends Flow<? extends T>>.flattenMerge(concurrency: Int): Flow<T> {
      return FlowKt__MergeKt.flattenMerge(`$this$flattenMerge`, concurrency);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.transformLatest(@BuilderInference transform: (FlowCollector<? super R>?, T?, Continuation<? super Unit>?) -> Any): Flow<R> {
      return FlowKt__MergeKt.transformLatest(`$this$transformLatest`, transform);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.flatMapLatest(@BuilderInference transform: (T?, Continuation<? super Flow<? extends R>>?) -> Any): Flow<R> {
      return FlowKt__MergeKt.flatMapLatest(`$this$flatMapLatest`, transform);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T, R> Flow<? extends T>.mapLatest(@BuilderInference transform: (T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__MergeKt.mapLatest(`$this$mapLatest`, transform);
   }

   @JvmStatic
   fun noImpl(): Void {
      return FlowKt__MigrationKt.noImpl();
   }

   /** @deprecated */
   @Deprecated(message = "Collect flow in the desired context instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.observeOn(context: CoroutineContext): Flow<T> {
      return FlowKt__MigrationKt.observeOn(`$this$observeOn`, context);
   }

   /** @deprecated */
   @Deprecated(message = "Collect flow in the desired context instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.publishOn(context: CoroutineContext): Flow<T> {
      return FlowKt__MigrationKt.publishOn(`$this$publishOn`, context);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'flowOn' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.subscribeOn(context: CoroutineContext): Flow<T> {
      return FlowKt__MigrationKt.subscribeOn(`$this$subscribeOn`, context);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.onErrorResume(fallback: Flow<? extends T>): Flow<T> {
      return FlowKt__MigrationKt.onErrorResume(`$this$onErrorResume`, fallback);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.onErrorResumeNext(fallback: Flow<? extends T>): Flow<T> {
      return FlowKt__MigrationKt.onErrorResumeNext(`$this$onErrorResumeNext`, fallback);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.subscribe() {
      FlowKt__MigrationKt.subscribe(`$this$subscribe`);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.subscribe(onEach: (T?, Continuation<? super Unit>?) -> Any) {
      FlowKt__MigrationKt.subscribe(`$this$subscribe`, onEach);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.subscribe(onEach: (T?, Continuation<? super Unit>?) -> Any, onError: (java.lang.Throwable?, Continuation<? super Unit>?) -> Any) {
      FlowKt__MigrationKt.subscribe(`$this$subscribe`, onEach, onError);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T, R> Flow<? extends T>.flatMap(mapper: (T?, Continuation<? super Flow<? extends R>>?) -> Any): Flow<R> {
      return FlowKt__MigrationKt.flatMap(`$this$flatMap`, mapper);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'concatMap' is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T, R> Flow<? extends T>.concatMap(mapper: (T?) -> Flow<? extends R>): Flow<R> {
      return FlowKt__MigrationKt.concatMap(`$this$concatMap`, mapper);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'merge' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends Flow<? extends T>>.merge(): Flow<T> {
      return FlowKt__MigrationKt.merge(`$this$merge`);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'flatten' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends Flow<? extends T>>.flatten(): Flow<T> {
      return FlowKt__MigrationKt.flatten(`$this$flatten`);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'compose' is 'let'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T, R> Flow<? extends T>.compose(transformer: (Flow<? extends T>?) -> Flow<? extends R>): Flow<R> {
      return FlowKt__MigrationKt.compose(`$this$compose`, transformer);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'skip' is 'drop'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.skip(count: Int): Flow<T> {
      return FlowKt__MigrationKt.skip(`$this$skip`, count);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'forEach' is 'collect'", replaceWith = @ReplaceWith(expression = "collect(action)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.forEach(action: (T?, Continuation<? super Unit>?) -> Any) {
      FlowKt__MigrationKt.forEach(`$this$forEach`, action);
   }

   /** @deprecated */
   @Deprecated(message = "Flow has less verbose 'scan' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T, R> Flow<? extends T>.scanFold(initial: R, @BuilderInference operation: (R?, T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__MigrationKt.scanFold(`$this$scanFold`, (R)initial, operation);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emit(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emit(fallback) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.onErrorReturn(fallback: T): Flow<T> {
      return FlowKt__MigrationKt.onErrorReturn(`$this$onErrorReturn`, (T)fallback);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { e -> if (predicate(e)) emit(fallback) else throw e }'", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.onErrorReturn(fallback: T, predicate: (java.lang.Throwable?) -> java.lang.Boolean): Flow<T> {
      return FlowKt__MigrationKt.onErrorReturn(`$this$onErrorReturn`, (T)fallback, predicate);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emit(value) }'", replaceWith = @ReplaceWith(expression = "onStart { emit(value) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.startWith(value: T): Flow<T> {
      return FlowKt__MigrationKt.startWith(`$this$startWith`, (T)value);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onStart { emitAll(other) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.startWith(other: Flow<? extends T>): Flow<T> {
      return FlowKt__MigrationKt.startWith(`$this$startWith`, other);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emit(value) }'", replaceWith = @ReplaceWith(expression = "onCompletion { emit(value) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.concatWith(value: T): Flow<T> {
      return FlowKt__MigrationKt.concatWith(`$this$concatWith`, (T)value);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { if (it == null) emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onCompletion { if (it == null) emitAll(other) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.concatWith(other: Flow<? extends T>): Flow<T> {
      return FlowKt__MigrationKt.concatWith(`$this$concatWith`, other);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "this.combine(other, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T1, T2, R> Flow<? extends T1>.combineLatest(other: Flow<? extends T2>, transform: (T1?, T2?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__MigrationKt.combineLatest(`$this$combineLatest`, other, transform);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T1, T2, T3, R> Flow<? extends T1>.combineLatest(
      other: Flow<? extends T2>, other2: Flow<? extends T3>, transform: (T1?, T2?, T3?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__MigrationKt.combineLatest(`$this$combineLatest`, other, other2, transform);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T1, T2, T3, T4, R> Flow<? extends T1>.combineLatest(
      other: Flow<? extends T2>, other2: Flow<? extends T3>, other3: Flow<? extends T4>, transform: (T1?, T2?, T3?, T4?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__MigrationKt.combineLatest(`$this$combineLatest`, other, other2, other3, transform);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T1, T2, T3, T4, T5, R> Flow<? extends T1>.combineLatest(
      other: Flow<? extends T2>,
      other2: Flow<? extends T3>,
      other3: Flow<? extends T4>,
      other4: Flow<? extends T5>,
      transform: (T1?, T2?, T3?, T4?, T5?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__MigrationKt.combineLatest(`$this$combineLatest`, other, other2, other3, other4, transform);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'onStart { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onStart { delay(timeMillis) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.delayFlow(timeMillis: Long): Flow<T> {
      return FlowKt__MigrationKt.delayFlow(`$this$delayFlow`, timeMillis);
   }

   /** @deprecated */
   @Deprecated(message = "Use 'onEach { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onEach { delay(timeMillis) }", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.delayEach(timeMillis: Long): Flow<T> {
      return FlowKt__MigrationKt.delayEach(`$this$delayEach`, timeMillis);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogues of 'switchMap' are 'transformLatest', 'flatMapLatest' and 'mapLatest'", replaceWith = @ReplaceWith(expression = "this.flatMapLatest(transform)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T, R> Flow<? extends T>.switchMap(transform: (T?, Continuation<? super Flow<? extends R>>?) -> Any): Flow<R> {
      return FlowKt__MigrationKt.switchMap(`$this$switchMap`, transform);
   }

   /** @deprecated */
   @Deprecated(message = "'scanReduce' was renamed to 'runningReduce' to be consistent with Kotlin standard library", replaceWith = @ReplaceWith(expression = "runningReduce(operation)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.scanReduce(operation: (T?, T?, Continuation<? super T>?) -> Any): Flow<T> {
      return FlowKt__MigrationKt.scanReduce(`$this$scanReduce`, operation);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'publish()' is 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \npublish().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, 0)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.publish(): Flow<T> {
      return FlowKt__MigrationKt.publish(`$this$publish`);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'publish(bufferSize)' is 'buffer' followed by 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \npublish().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.buffer(bufferSize).shareIn(scope, 0)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.publish(bufferSize: Int): Flow<T> {
      return FlowKt__MigrationKt.publish(`$this$publish`, bufferSize);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'replay()' is 'shareIn' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.replay(): Flow<T> {
      return FlowKt__MigrationKt.replay(`$this$replay`);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'replay(bufferSize)' is 'shareIn' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStarted.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStarted.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, bufferSize)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.replay(bufferSize: Int): Flow<T> {
      return FlowKt__MigrationKt.replay(`$this$replay`, bufferSize);
   }

   /** @deprecated */
   @Deprecated(message = "Flow analogue of 'cache()' is 'shareIn' with unlimited replay and 'started = SharingStarted.Lazily' argument'", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, started = SharingStarted.Lazily, replay = Int.MAX_VALUE)", imports = []), level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <T> Flow<? extends T>.cache(): Flow<T> {
      return FlowKt__MigrationKt.cache(`$this$cache`);
   }

   @JvmStatic
   fun <S, T extends S> Flow<? extends T>.reduce(operation: (S?, T?, Continuation<? super S>?) -> Any, `$completion`: Continuation<? super S>): Any? {
      return FlowKt__ReduceKt.reduce(`$this$reduce`, operation, `$completion`);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.fold(initial: R, operation: (R?, T?, Continuation<? super R>?) -> Any, `$completion`: Continuation<? super R>): Any? {
      return FlowKt__ReduceKt.fold(`$this$fold`, initial, operation, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.single(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.single(`$this$single`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.singleOrNull(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.singleOrNull(`$this$singleOrNull`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.first(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.first(`$this$first`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.first(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.first(`$this$first`, predicate, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.firstOrNull(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.firstOrNull(`$this$firstOrNull`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.firstOrNull(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.firstOrNull(`$this$firstOrNull`, predicate, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.last(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.last(`$this$last`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.lastOrNull(`$completion`: Continuation<? super T>): Any? {
      return FlowKt__ReduceKt.lastOrNull(`$this$lastOrNull`, `$completion`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.shareIn(scope: CoroutineScope, started: SharingStarted, replay: Int): SharedFlow<T> {
      return FlowKt__ShareKt.shareIn(`$this$shareIn`, scope, started, replay);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.stateIn(scope: CoroutineScope, started: SharingStarted, initialValue: T): StateFlow<T> {
      return FlowKt__ShareKt.stateIn(`$this$stateIn`, scope, started, (T)initialValue);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.stateIn(scope: CoroutineScope, `$completion`: Continuation<? super StateFlow<? extends T>>): Any? {
      return FlowKt__ShareKt.stateIn(`$this$stateIn`, scope, `$completion`);
   }

   @JvmStatic
   fun <T> MutableSharedFlow<T>.asSharedFlow(): SharedFlow<T> {
      return FlowKt__ShareKt.asSharedFlow(`$this$asSharedFlow`);
   }

   @JvmStatic
   fun <T> MutableStateFlow<T>.asStateFlow(): StateFlow<T> {
      return FlowKt__ShareKt.asStateFlow(`$this$asStateFlow`);
   }

   @JvmStatic
   fun <T> SharedFlow<? extends T>.onSubscription(action: (FlowCollector<? super T>?, Continuation<? super Unit>?) -> Any): SharedFlow<T> {
      return FlowKt__ShareKt.onSubscription(`$this$onSubscription`, action);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.filter(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<T> {
      return FlowKt__TransformKt.filter(`$this$filter`, predicate);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.filterNot(predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any): Flow<T> {
      return FlowKt__TransformKt.filterNot(`$this$filterNot`, predicate);
   }

   @JvmStatic
   fun <R> Flow<?>.filterIsInstance(klass: KClass<R>): Flow<R> {
      return FlowKt__TransformKt.filterIsInstance(`$this$filterIsInstance`, klass);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.filterNotNull(): Flow<T> {
      return FlowKt__TransformKt.filterNotNull(`$this$filterNotNull`);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.map(transform: (T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__TransformKt.map(`$this$map`, transform);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.mapNotNull(transform: (T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__TransformKt.mapNotNull(`$this$mapNotNull`, transform);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.withIndex(): Flow<IndexedValue<T>> {
      return FlowKt__TransformKt.withIndex(`$this$withIndex`);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.onEach(action: (T?, Continuation<? super Unit>?) -> Any): Flow<T> {
      return FlowKt__TransformKt.onEach(`$this$onEach`, action);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.scan(initial: R, @BuilderInference operation: (R?, T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__TransformKt.scan(`$this$scan`, (R)initial, operation);
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.runningFold(initial: R, @BuilderInference operation: (R?, T?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__TransformKt.runningFold(`$this$runningFold`, (R)initial, operation);
   }

   @JvmStatic
   fun <T> Flow<? extends T>.runningReduce(operation: (T?, T?, Continuation<? super T>?) -> Any): Flow<T> {
      return FlowKt__TransformKt.runningReduce(`$this$runningReduce`, operation);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   fun <T> Flow<? extends T>.chunked(size: Int): Flow<java.utilList<T>> {
      return FlowKt__TransformKt.chunked(`$this$chunked`, size);
   }

   @JvmName(name = "flowCombine")
   @JvmStatic
   fun <T1, T2, R> Flow<? extends T1>.flowCombine(flow: Flow<? extends T2>, transform: (T1?, T2?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__ZipKt.flowCombine(`$this$combine`, flow, transform);
   }

   @JvmStatic
   fun <T1, T2, R> combine(flow: Flow<? extends T1>, flow2: Flow<? extends T2>, transform: (T1?, T2?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__ZipKt.combine(flow, flow2, transform);
   }

   @JvmName(name = "flowCombineTransform")
   @JvmStatic
   fun <T1, T2, R> Flow<? extends T1>.flowCombineTransform(
      flow: Flow<? extends T2>, @BuilderInference transform: (FlowCollector<? super R>?, T1?, T2?, Continuation<? super Unit>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.flowCombineTransform(`$this$combineTransform`, flow, transform);
   }

   @JvmStatic
   fun <T1, T2, R> combineTransform(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      @BuilderInference transform: (FlowCollector<? super R>?, T1?, T2?, Continuation<? super Unit>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combineTransform(flow, flow2, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, R> combine(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      @BuilderInference transform: (T1?, T2?, T3?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combine(flow, flow2, flow3, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, R> combineTransform(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      @BuilderInference transform: (FlowCollector<? super R>?, T1?, T2?, T3?, Continuation<? super Unit>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, T4, R> combine(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      flow4: Flow<? extends T4>,
      transform: (T1?, T2?, T3?, T4?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, T4, R> combineTransform(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      flow4: Flow<? extends T4>,
      @BuilderInference transform: (FlowCollector<? super R>?, T1?, T2?, T3?, T4?, Continuation<? super Unit>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, T4, T5, R> combine(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      flow4: Flow<? extends T4>,
      flow5: Flow<? extends T5>,
      transform: (T1?, T2?, T3?, T4?, T5?, Continuation<? super R>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, flow5, transform);
   }

   @JvmStatic
   fun <T1, T2, T3, T4, T5, R> combineTransform(
      flow: Flow<? extends T1>,
      flow2: Flow<? extends T2>,
      flow3: Flow<? extends T3>,
      flow4: Flow<? extends T4>,
      flow5: Flow<? extends T5>,
      @BuilderInference transform: (FlowCollector<? super R>?, T1?, T2?, T3?, T4?, T5?, Continuation<? super Unit>?) -> Any
   ): Flow<R> {
      return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, flow5, transform);
   }

   @JvmStatic
   fun <T1, T2, R> Flow<? extends T1>.zip(other: Flow<? extends T2>, transform: (T1?, T2?, Continuation<? super R>?) -> Any): Flow<R> {
      return FlowKt__ZipKt.zip(`$this$zip`, other, transform);
   }
}
