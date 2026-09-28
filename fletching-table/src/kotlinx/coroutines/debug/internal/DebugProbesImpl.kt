package kotlinx.coroutines.debug.internal

import _COROUTINE.ArtificialStackFrames
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.concurrent.ThreadsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicLong
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineId
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.JobSupport
import kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl.3
import kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfoImpl..inlined.sortedBy.1
import kotlinx.coroutines.internal.ScopeCoroutine

@PublishedApi
@SourceDebugExtension(["SMAP\nDebugProbesImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugProbesImpl.kt\nkotlinx/coroutines/debug/internal/DebugProbesImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,616:1\n146#1:640\n147#1,4:642\n152#1,5:647\n146#1:652\n147#1,4:654\n152#1,5:659\n1#2:617\n1#2:641\n1#2:653\n774#3:618\n865#3,2:619\n1216#3,2:621\n1246#3,4:623\n1863#3,2:667\n360#3,7:675\n1827#3,8:682\n607#4:627\n607#4:646\n607#4:658\n607#4:664\n1317#4,2:665\n37#5:628\n36#5,3:629\n37#5:632\n36#5,3:633\n37#5:636\n36#5,3:637\n1682#6,6:669\n1790#6,6:690\n*S KotlinDebug\n*F\n+ 1 DebugProbesImpl.kt\nkotlinx/coroutines/debug/internal/DebugProbesImpl\n*L\n241#1:640\n241#1:642,4\n241#1:647,5\n248#1:652\n248#1:654,4\n248#1:659,5\n241#1:641\n248#1:653\n106#1:618\n106#1:619,2\n107#1:621,2\n107#1:623,4\n303#1:667,2\n412#1:675,7\n502#1:682,8\n150#1:627\n241#1:646\n248#1:658\n283#1:664\n284#1:665,2\n207#1:628\n207#1:629,3\n208#1:632\n208#1:633,3\n209#1:636\n209#1:637,3\n351#1:669,6\n554#1:690,6\n*E\n"])
internal object DebugProbesImpl {
   private final val ARTIFICIAL_FRAME: StackTraceElement = new ArtificialStackFrames().coroutineCreation()
   private final val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
   private final var weakRefCleanerThread: Thread?
   private final val capturedCoroutinesMap: ConcurrentWeakMap<kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>, Boolean> =
      new ConcurrentWeakMap(false, 1, null)

   private final val capturedCoroutines: Set<kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>>
      private final get() {
         return capturedCoroutinesMap.keySet();
      }


   private final val installations: AtomicInt

   public final val isInstalled: Boolean
      public final get() {
         return this.getInstallations().get() > 0;
      }


   private final val sequenceNumber: AtomicLong
   internal final var sanitizeStackTraces: Boolean = true
   internal final var enableCreationStackTraces: Boolean

   public final var ignoreCoroutinesWithEmptyContext: Boolean = true
      internal set

   private final val dynamicAttach: ((Boolean) -> Unit)? = INSTANCE.getDynamicAttach()
   private final val callerInfoCache: ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> = new ConcurrentWeakMap(true)

   private final val debugString: String
      private final get() {
         return if (`$this$debugString` is JobSupport) (`$this$debugString` as JobSupport).toDebugString() else `$this$debugString`.toString();
      }


   private final val isInternalMethod: Boolean
      private final get() {
         return StringsKt.startsWith$default(`$this$isInternalMethod`.getClassName(), "kotlinx.coroutines", false, 2, null);
      }


   private fun getDynamicAttach(): ((Boolean) -> Unit)? {
      val var1: DebugProbesImpl = this;

      var `$this$getDynamicAttach_u24lambda_u240`: DebugProbesImpl;
      try {
         `$this$getDynamicAttach_u24lambda_u240` = var1;
         val var10000: Any = Class.forName("kotlinx.coroutines.debug.ByteBuddyDynamicAttach").getConstructors()[0].newInstance();
         `$this$getDynamicAttach_u24lambda_u240` = (DebugProbesImpl)Result.constructor-impl(
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10000, 1) as Function1
         );
      } catch (var6: java.lang.Throwable) {
         `$this$getDynamicAttach_u24lambda_u240` = (DebugProbesImpl)Result.constructor-impl(ResultKt.createFailure(var6));
      }

      return (if (Result.isFailure-impl(`$this$getDynamicAttach_u24lambda_u240`)) null else `$this$getDynamicAttach_u24lambda_u240`) as (java.lang.Boolean?) -> Unit;
   }

   internal fun install() {
      if (this.getInstallations().incrementAndGet() <= 1) {
         this.startWeakRefCleanerThread();
         if (!AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core()) {
            if (dynamicAttach != null) {
               dynamicAttach.invoke(true);
            }
         }
      }
   }

   internal fun uninstall() {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Agent was not installed".toString());
      } else if (this.getInstallations().decrementAndGet() == 0) {
         this.stopWeakRefCleanerThread();
         capturedCoroutinesMap.clear();
         callerInfoCache.clear();
         if (!AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core()) {
            if (dynamicAttach != null) {
               dynamicAttach.invoke(false);
            }
         }
      }
   }

   private fun startWeakRefCleanerThread() {
      weakRefCleanerThread = ThreadsKt.thread$default(
         false, true, null, "Coroutines Debugger Cleaner", 0, DebugProbesImpl::startWeakRefCleanerThread$lambda$2, 21, null
      );
   }

   private fun stopWeakRefCleanerThread() {
      if (weakRefCleanerThread != null) {
         val thread: Thread = weakRefCleanerThread;
         weakRefCleanerThread = null;
         thread.interrupt();
         thread.join();
      }
   }

   internal fun hierarchyToString(job: Job): String {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Debug probes are not installed".toString());
      } else {
         var `$this$associateBy$iv`: java.lang.Iterable = this.getCapturedCoroutines();
         val `$this$associateByTo$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$associateBy$iv) {
            if ((`element$iv$iv` as DebugProbesImpl.CoroutineOwner).delegate.getContext().get(Job.Key) != null) {
               `$this$associateByTo$iv$iv`.add(`element$iv$iv`);
            }
         }

         `$this$associateBy$iv` = `$this$associateByTo$iv$iv` as java.util.List;
         val `destination$iv$ivx`: java.util.Map = new LinkedHashMap(
            RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateByTo$iv$iv` as java.util.List, 10)), 16)
         );

         for (Object element$iv$ivx : $this$associateBy$iv) {
            `destination$iv$ivx`.put(
               JobKt.getJob((`element$iv$ivx` as DebugProbesImpl.CoroutineOwner).delegate.getContext()),
               (`element$iv$ivx` as DebugProbesImpl.CoroutineOwner).info
            );
         }

         val var17: StringBuilder = new StringBuilder();
         INSTANCE.build(job, `destination$iv$ivx`, var17, "");
         return var17.toString();
      }
   }

   private fun Job.build(map: Map<Job, DebugCoroutineInfoImpl>, builder: StringBuilder, indent: String) {
      val info: DebugCoroutineInfoImpl = map.get(`$this$build`) as DebugCoroutineInfoImpl;
      val var9: java.lang.String;
      if (info == null) {
         if (`$this$build` !is ScopeCoroutine) {
            builder.append("$indent${this.getDebugString(`$this$build`)}
");
            var9 = "$indent	";
         } else {
            var9 = indent;
         }
      } else {
         builder.append(
            "$indent${this.getDebugString(`$this$build`)}, continuation is ${info.getState$kotlinx_coroutines_core()} at line ${CollectionsKt.firstOrNull(
               info.lastObservedStackTrace$kotlinx_coroutines_core()
            )}
   "
         );
         var9 = "$indent	";
      }

      for (Job child : $this$build.getChildren()) {
         this.build(var11, map, builder, var9);
      }
   }

   private inline fun <R : Any> dumpCoroutinesInfoImpl(
      crossinline create: (kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>, CoroutineContext) -> R
   ): List<R> {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Debug probes are not installed".toString());
      } else {
         return SequencesKt.toList(
            SequencesKt.mapNotNull(SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new 1<>()), new 3(create))
         );
      }
   }

   public fun dumpCoroutinesInfoAsJsonAndReferences(): Array<Any> {
      val coroutinesInfo: java.util.List = this.dumpCoroutinesInfo();
      val size: Int = coroutinesInfo.size();
      val lastObservedThreads: ArrayList = new ArrayList(size);
      val lastObservedFrames: ArrayList = new ArrayList(size);
      val coroutinesInfoAsJson: ArrayList = new ArrayList(size);

      for (DebugCoroutineInfo info : coroutinesInfo) {
         var `$i$f$toTypedArray`: CoroutineContext;
         var var19: java.lang.String;
         label27: {
            `$i$f$toTypedArray` = `$this$toTypedArray$iv`.getContext();
            val var10000: CoroutineName = `$i$f$toTypedArray`.get(CoroutineName.Key);
            if (var10000 != null) {
               var19 = var10000.getName();
               if (var19 != null) {
                  var19 = this.toStringRepr(var19);
                  break label27;
               }
            }

            var19 = null;
         }

         val var20: CoroutineDispatcher = `$i$f$toTypedArray`.get(CoroutineDispatcher.Key);
         val dispatcher: java.lang.String = if (var20 != null) this.toStringRepr(var20) else null;
         val var10001: StringBuilder = new StringBuilder()
            .append("\n                {\n                    \"name\": ")
            .append(var19)
            .append(",\n                    \"id\": ");
         val var10002: CoroutineId = `$i$f$toTypedArray`.get(CoroutineId.Key);
         coroutinesInfoAsJson.add(
            StringsKt.trimIndent(
               var10001.append(if (var10002 != null) var10002.getId() else null)
                  .append(",\n                    \"dispatcher\": ")
                  .append(dispatcher)
                  .append(",\n                    \"sequenceNumber\": ")
                  .append(`$this$toTypedArray$iv`.getSequenceNumber())
                  .append(",\n                    \"state\": \"")
                  .append(`$this$toTypedArray$iv`.getState())
                  .append("\"\n                } \n                ")
                  .toString()
            )
         );
         lastObservedFrames.add(`$this$toTypedArray$iv`.getLastObservedFrame());
         lastObservedThreads.add(`$this$toTypedArray$iv`.getLastObservedThread());
      }

      return new Object[]{
         "[${CollectionsKt.joinToString$default(coroutinesInfoAsJson, null, null, null, 0, null, null, 63, null)}]",
         lastObservedThreads.toArray(new Thread[0]),
         lastObservedFrames.toArray(new CoroutineStackFrame[0]),
         coroutinesInfo.toArray(new DebugCoroutineInfo[0])
      };
   }

   public fun enhanceStackTraceWithThreadDumpAsJson(info: DebugCoroutineInfo): String {
      val stackTraceElements: java.util.List = this.enhanceStackTraceWithThreadDump(info, info.lastObservedStackTrace());
      val stackTraceElementsInfoAsJson: java.util.List = new ArrayList();

      for (StackTraceElement element : stackTraceElements) {
         val var10001: StringBuilder = new StringBuilder()
            .append("\n                {\n                    \"declaringClass\": \"")
            .append(element.getClassName())
            .append("\",\n                    \"methodName\": \"")
            .append(element.getMethodName())
            .append("\",\n                    \"fileName\": ");
         val var10002: java.lang.String = element.getFileName();
         stackTraceElementsInfoAsJson.add(
            StringsKt.trimIndent(
               var10001.append(if (var10002 != null) this.toStringRepr(var10002) else null)
                  .append(",\n                    \"lineNumber\": ")
                  .append(element.getLineNumber())
                  .append("\n                }\n                ")
                  .toString()
            )
         );
      }

      return "[${CollectionsKt.joinToString$default(stackTraceElementsInfoAsJson, null, null, null, 0, null, null, 63, null)}]";
   }

   private fun Any.toStringRepr(): String {
      return DebugProbesImplKt.access$repr(`$this$toStringRepr`.toString());
   }

   public fun dumpCoroutinesInfo(): List<DebugCoroutineInfo> {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Debug probes are not installed".toString());
      } else {
         return SequencesKt.toList(
            SequencesKt.mapNotNull(
               SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new 1<>()),
               new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpCoroutinesInfo..inlined.dumpCoroutinesInfoImpl.1()
            )
         );
      }
   }

   public fun dumpDebuggerInfo(): List<DebuggerInfo> {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Debug probes are not installed".toString());
      } else {
         return SequencesKt.toList(
            SequencesKt.mapNotNull(
               SequencesKt.sortedWith(CollectionsKt.asSequence(this.getCapturedCoroutines()), new 1<>()),
               new kotlinx.coroutines.debug.internal.DebugProbesImpl.dumpDebuggerInfo..inlined.dumpCoroutinesInfoImpl.1()
            )
         );
      }
   }

   @JvmName(name = "dumpCoroutines")
   internal fun dumpCoroutines(out: PrintStream) {
      synchronized (out) {
         INSTANCE.dumpCoroutinesSynchronized(out);
      }
   }

   private fun kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>.isFinished(): Boolean {
      val var10000: CoroutineContext = `$this$isFinished`.info.getContext();
      if (var10000 != null) {
         val var3: Job = var10000.get(Job.Key);
         if (var3 != null) {
            if (!var3.isCompleted()) {
               return false;
            }

            capturedCoroutinesMap.remove(`$this$isFinished`);
            return true;
         }
      }

      return false;
   }

   private fun dumpCoroutinesSynchronized(out: PrintStream) {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         throw new IllegalStateException("Debug probes are not installed".toString());
      } else {
         out.print("Coroutines dump ${dateFormat.format(System.currentTimeMillis())}");

         val var12: Sequence;
         for (Object element$iv : var12) {
            val owner: DebugProbesImpl.CoroutineOwner = `element$iv` as DebugProbesImpl.CoroutineOwner;
            val info: DebugCoroutineInfoImpl = (`element$iv` as DebugProbesImpl.CoroutineOwner).info;
            val observedStackTrace: java.util.List = (`element$iv` as DebugProbesImpl.CoroutineOwner).info.lastObservedStackTrace$kotlinx_coroutines_core();
            val enhancedStackTrace: java.util.List = INSTANCE.enhanceStackTraceWithThreadDumpImpl(
               info.getState$kotlinx_coroutines_core(), info.lastObservedThread, observedStackTrace
            );
            out.print(
               "\n\nCoroutine ${owner.delegate}, state: ${if (info.getState$kotlinx_coroutines_core() == "RUNNING" && enhancedStackTrace === observedStackTrace)
                  "${info.getState$kotlinx_coroutines_core()} (Last suspension stacktrace, not an actual stacktrace)"
                  else
                  info.getState$kotlinx_coroutines_core()}"
            );
            if (observedStackTrace.isEmpty()) {
               out.print("\n\tat ${ARTIFICIAL_FRAME}");
               INSTANCE.printStackTrace(out, info.getCreationStackTrace());
            } else {
               INSTANCE.printStackTrace(out, enhancedStackTrace);
            }
         }
      }
   }

   private fun printStackTrace(out: PrintStream, frames: List<StackTraceElement>) {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         out.print("\n\tat ${`element$iv` as StackTraceElement}");
      }
   }

   public fun enhanceStackTraceWithThreadDump(info: DebugCoroutineInfo, coroutineTrace: List<StackTraceElement>): List<StackTraceElement> {
      return this.enhanceStackTraceWithThreadDumpImpl(info.getState(), info.getLastObservedThread(), coroutineTrace);
   }

   private fun enhanceStackTraceWithThreadDumpImpl(state: String, thread: Thread?, coroutineTrace: List<StackTraceElement>): List<StackTraceElement> {
      if (state == "RUNNING" && thread != null) {
         var `$this$indexOfFirst$iv`: Array<Any> = this;

         var continuationStartFrame: DebugProbesImpl;
         try {
            continuationStartFrame = `$this$indexOfFirst$iv` as DebugProbesImpl;
            continuationStartFrame = (DebugProbesImpl)Result.constructor-impl(thread.getStackTrace());
         } catch (var13: java.lang.Throwable) {
            continuationStartFrame = (DebugProbesImpl)Result.constructor-impl(ResultKt.createFailure(var13));
         }

         val var10000: Array<StackTraceElement> = (if (Result.isFailure-impl(continuationStartFrame)) null else continuationStartFrame) as Array<StackTraceElement>;
         if (var10000 == null) {
            return coroutineTrace;
         } else {
            val actualTrace: Array<StackTraceElement> = var10000;
            `$this$indexOfFirst$iv` = var10000;
            var var19: Int = 0;
            val expectedSize: Int = var10000.length;

            while (true) {
               if (var19 >= expectedSize) {
                  var26 = -1;
                  break;
               }

               if (`$this$indexOfFirst$iv`[var19].getClassName() == "kotlin.coroutines.jvm.internal.BaseContinuationImpl"
                  && `$this$indexOfFirst$iv`[var19].getMethodName() == "resumeWith"
                  && `$this$indexOfFirst$iv`[var19].getFileName() == "ContinuationImpl.kt") {
                  var26 = var19;
                  break;
               }

               var19++;
            }

            val var15: Pair = this.findContinuationStartIndex(var26, var10000, coroutineTrace);
            val var18: Int = (var15.component1() as java.lang.Number).intValue();
            var19 = (var15.component2() as java.lang.Number).intValue();
            if (var18 == -1) {
               return coroutineTrace;
            } else {
               val var22: ArrayList = new ArrayList(var26 + coroutineTrace.size() - var18 - 1 - var19);
               var var23: Int = 0;

               for (int var12 = var26 - index$iv; index < var12; index++) {
                  var22.add(actualTrace[var23]);
               }

               var23 = var18 + 1;

               for (int var25 = coroutineTrace.size(); index < var25; index++) {
                  var22.add(coroutineTrace.get(var23));
               }

               return var22;
            }
         }
      } else {
         return coroutineTrace;
      }
   }

   private fun findContinuationStartIndex(indexOfResumeWith: Int, actualTrace: Array<StackTraceElement>, coroutineTrace: List<StackTraceElement>): Pair<
         Int,
         Int
      > {
      val var4: Byte = 3;

      for (int var5 = 0; var5 < var4; var5++) {
         val result: Int = INSTANCE.findIndexOfFrame(indexOfResumeWith - 1 - var5, actualTrace, coroutineTrace);
         if (result != -1) {
            return TuplesKt.to(result, var5);
         }
      }

      return TuplesKt.to(-1, 0);
   }

   private fun findIndexOfFrame(frameIndex: Int, actualTrace: Array<StackTraceElement>, coroutineTrace: List<StackTraceElement>): Int {
      val var10000: StackTraceElement = ArraysKt.getOrNull(actualTrace, frameIndex);
      if (var10000 == null) {
         return -1;
      } else {
         val continuationFrame: StackTraceElement = var10000;
         var `index$iv`: Int = 0;
         val var8: java.util.Iterator = coroutineTrace.iterator();

         while (true) {
            if (!var8.hasNext()) {
               var12 = -1;
               break;
            }

            val it: StackTraceElement = var8.next() as StackTraceElement;
            if (it.getFileName() == continuationFrame.getFileName()
               && it.getClassName() == continuationFrame.getClassName()
               && it.getMethodName() == continuationFrame.getMethodName()) {
               var12 = `index$iv`;
               break;
            }

            `index$iv`++;
         }

         return var12;
      }
   }

   internal fun probeCoroutineResumed(frame: Continuation<*>) {
      this.updateState(frame, "RUNNING");
   }

   internal fun probeCoroutineSuspended(frame: Continuation<*>) {
      this.updateState(frame, "SUSPENDED");
   }

   private fun updateState(frame: Continuation<*>, state: String) {
      if (this.isInstalled$kotlinx_coroutines_debug()) {
         if (!ignoreCoroutinesWithEmptyContext || frame.getContext() != EmptyCoroutineContext.INSTANCE) {
            if (state == "RUNNING") {
               val var5: CoroutineStackFrame = frame as? CoroutineStackFrame;
               if ((frame as? CoroutineStackFrame) != null) {
                  this.updateRunningState(var5, state);
               }
            } else {
               val var10000: DebugProbesImpl.CoroutineOwner = this.owner(frame);
               if (var10000 != null) {
                  this.updateState(var10000, frame, state);
               }
            }
         }
      }
   }

   private fun updateRunningState(frame: CoroutineStackFrame, state: String) {
      if (this.isInstalled$kotlinx_coroutines_debug()) {
         val cached: DebugCoroutineInfoImpl = callerInfoCache.remove(frame);
         val var7: DebugCoroutineInfoImpl;
         val var8: Boolean;
         if (cached != null) {
            var7 = cached;
            var8 = false;
         } else {
            val var10000: DebugProbesImpl.CoroutineOwner = this.owner(frame);
            if (var10000 == null || var10000.info == null) {
               return;
            }

            var7 = var10000.info;
            var8 = true;
            val var11: CoroutineStackFrame = var10000.info.getLastObservedFrame$kotlinx_coroutines_core();
            val caller: CoroutineStackFrame = if (var11 != null) this.realCaller(var11) else null;
            if (caller != null) {
               callerInfoCache.remove(caller);
            }
         }

         var7.updateState$kotlinx_coroutines_core(state, frame as Continuation<?>, var8);
         val var12: CoroutineStackFrame = this.realCaller(frame);
         if (var12 != null) {
            callerInfoCache.put(var12, var7);
         }
      }
   }

   private tailrec fun CoroutineStackFrame.realCaller(): CoroutineStackFrame? {
      var var2: DebugProbesImpl = this;

      while (true) {
         val var10000: CoroutineStackFrame = `$this$realCaller`.getCallerFrame();
         if (var10000 == null) {
            return null;
         }

         if (var10000.getStackTraceElement() != null) {
            return var10000;
         }

         var2 = var2;
         `$this$realCaller` = var10000;
      }
   }

   private fun updateState(owner: kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>, frame: Continuation<*>, state: String) {
      if (this.isInstalled$kotlinx_coroutines_debug()) {
         owner.info.updateState$kotlinx_coroutines_core(state, frame, true);
      }
   }

   private fun Continuation<*>.owner(): kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>? {
      return if ((`$this$owner` as? CoroutineStackFrame) != null) this.owner(`$this$owner` as? CoroutineStackFrame) else null;
   }

   private tailrec fun CoroutineStackFrame.owner(): kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>? {
      var var2: DebugProbesImpl = this;

      var var10000: DebugProbesImpl.CoroutineOwner;
      while (true) {
         if (`$this$owner` is DebugProbesImpl.CoroutineOwner) {
            var10000 = `$this$owner` as DebugProbesImpl.CoroutineOwner;
            break;
         }

         val var3: CoroutineStackFrame = `$this$owner`.getCallerFrame();
         if (var3 == null) {
            var10000 = null;
            break;
         }

         var2 = var2;
         `$this$owner` = var3;
      }

      return var10000;
   }

   internal fun <T> probeCoroutineCreated(completion: Continuation<T>): Continuation<T> {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         return completion;
      } else {
         label20:
         if (ignoreCoroutinesWithEmptyContext && completion.getContext() === EmptyCoroutineContext.INSTANCE) {
            return completion;
         } else {
            return if (this.owner(completion) != null)
               completion
               else
               this.createOwner(completion, if (enableCreationStackTraces) this.toStackTraceFrame(this.sanitizeStackTrace(new Exception())) else null);
         }
      }
   }

   private fun List<StackTraceElement>.toStackTraceFrame(): StackTraceFrame {
      var `accumulator$iv`: Any = null;
      if (!`$this$toStackTraceFrame`.isEmpty()) {
         val `iterator$iv`: java.util.ListIterator = `$this$toStackTraceFrame`.listIterator(`$this$toStackTraceFrame`.size());

         while (iterator$iv.hasPrevious()) {
            `accumulator$iv` = new StackTraceFrame(`accumulator$iv` as CoroutineStackFrame, `iterator$iv`.previous() as StackTraceElement);
         }
      }

      return new StackTraceFrame(`accumulator$iv` as CoroutineStackFrame, ARTIFICIAL_FRAME);
   }

   private fun <T> createOwner(completion: Continuation<T>, frame: StackTraceFrame?): Continuation<T> {
      if (!this.isInstalled$kotlinx_coroutines_debug()) {
         return completion;
      } else {
         val owner: DebugProbesImpl.CoroutineOwner = new DebugProbesImpl.CoroutineOwner(
            completion, new DebugCoroutineInfoImpl(completion.getContext(), frame, this.getSequenceNumber().incrementAndGet())
         );
         capturedCoroutinesMap.put(owner, true);
         if (!this.isInstalled$kotlinx_coroutines_debug()) {
            capturedCoroutinesMap.clear();
         }

         return owner;
      }
   }

   private fun probeCoroutineCompleted(owner: kotlinx.coroutines.debug.internal.DebugProbesImpl.CoroutineOwner<*>) {
      capturedCoroutinesMap.remove(owner);
      var var10000: CoroutineStackFrame = owner.info.getLastObservedFrame$kotlinx_coroutines_core();
      if (var10000 != null) {
         var10000 = this.realCaller(var10000);
         if (var10000 != null) {
            callerInfoCache.remove(var10000);
            return;
         }
      }
   }

   private fun <T : Throwable> sanitizeStackTrace(throwable: T): List<StackTraceElement> {
      var stackTrace: Array<StackTraceElement>;
      var size: Int;
      var var10000: Int;
      label74: {
         stackTrace = throwable.getStackTrace();
         size = stackTrace.length;
         val result: Array<StackTraceElement> = stackTrace;
         var j: Int = stackTrace.length + -1;
         if (0 <= stackTrace.length + -1) {
            do {
               val k: Int = j--;
               if (result[k].getClassName() == "kotlin.coroutines.jvm.internal.DebugProbesKt") {
                  var10000 = k;
                  break label74;
               }
            } while (0 <= j);
         }

         var10000 = -1;
      }

      val traceStart: Int = 1 + var10000;
      if (!sanitizeStackTraces) {
         val var14: Int = size - traceStart;
         val var16: ArrayList = new ArrayList(size - traceStart);

         for (int var18 = 0; var18 < var14; var18++) {
            var16.add(stackTrace[var18 + traceStart]);
         }

         return var16;
      } else {
         val var13: ArrayList = new ArrayList(size - traceStart + 1);
         var var15: Int = traceStart;

         while (i < size) {
            if (this.isInternalMethod(stackTrace[var15])) {
               var13.add(stackTrace[var15]);
               var var17: Int = var15 + 1;

               while (j < size && this.isInternalMethod(stackTrace[j])) {
                  var17++;
               }

               var var19: Int = var17 - 1;

               while (k > i && stackTrace[k].getFileName() == null) {
                  var19--;
               }

               if (var19 > var15 && var19 < var17 - 1) {
                  var13.add(stackTrace[var19]);
               }

               var13.add(stackTrace[var17 - 1]);
               var15 = var17;
            } else {
               var13.add(stackTrace[var15]);
               var15++;
            }
         }

         return var13;
      }
   }

   @JvmStatic
   fun `startWeakRefCleanerThread$lambda$2`(): Unit {
      callerInfoCache.runWeakRefQueueCleaningLoopUntilInterrupted();
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `dumpCoroutinesSynchronized$lambda$14`(it: DebugProbesImpl.CoroutineOwner): Boolean {
      return !INSTANCE.isFinished(it);
   }

   public class CoroutineOwner<T> internal constructor(delegate: Continuation<Any>, info: DebugCoroutineInfoImpl) : Continuation<T>, CoroutineStackFrame {
      internal final val delegate: Continuation<Any>
      public final val info: DebugCoroutineInfoImpl

      private final val frame: StackTraceFrame?
         private final get() {
            return this.info.getCreationStackBottom$kotlinx_coroutines_core();
         }


      public open val callerFrame: CoroutineStackFrame?
         public open get() {
            val var10000: StackTraceFrame = this.getFrame();
            return if (var10000 != null) var10000.getCallerFrame() else null;
         }


      public open val context: CoroutineContext

      init {
         this.delegate = delegate;
         this.info = info;
      }

      public override fun getStackTraceElement(): StackTraceElement? {
         val var10000: StackTraceFrame = this.getFrame();
         return if (var10000 != null) var10000.getStackTraceElement() else null;
      }

      public override fun resumeWith(result: Result<Any>) {
         DebugProbesImpl.access$probeCoroutineCompleted(DebugProbesImpl.INSTANCE, this);
         this.delegate.resumeWith(result);
      }

      public override fun toString(): String {
         return this.delegate.toString();
      }
   }
}
