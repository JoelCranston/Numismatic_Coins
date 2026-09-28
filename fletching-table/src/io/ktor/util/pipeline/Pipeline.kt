package io.ktor.util.pipeline

import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import java.util.ArrayList
import java.util.Arrays
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics

@SourceDebugExtension(["SMAP\nPipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pipeline.kt\nio/ktor/util/pipeline/Pipeline\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,539:1\n1563#2:540\n1634#2,3:541\n1869#2,2:544\n808#2,11:546\n295#2,2:557\n1869#2,2:559\n*S KotlinDebug\n*F\n+ 1 Pipeline.kt\nio/ktor/util/pipeline/Pipeline\n*L\n60#1:540\n60#1:541,3\n83#1:544,2\n195#1:546,11\n196#1:557,2\n236#1:559,2\n*E\n"])
public open class Pipeline<TSubject, TContext>(vararg phases: PipelinePhase) {
   public final val attributes: Attributes = AttributesJvmKt.Attributes(true)
   public open val developmentMode: Boolean
   private final val phasesRaw: MutableList<Any>
   private final var interceptorsQuantity: Int

   public final val items: List<PipelinePhase>
      public final get() {
         val `$this$map$iv`: java.lang.Iterable = this.phasesRaw;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.phasesRaw, 10));

         for (Object item$iv$iv : $this$map$iv) {
            var var10000: PipelinePhase = `item$iv$iv` as? PipelinePhase;
            if ((`item$iv$iv` as? PipelinePhase) == null) {
               var10000 = if ((`item$iv$iv` as? PhaseContent) != null) (`item$iv$iv` as? PhaseContent).getPhase() else null;
            }

            `destination$iv$iv`.add(var10000);
         }

         return `destination$iv$iv` as MutableList<PipelinePhase>;
      }


   public final val isEmpty: Boolean
      public final get() {
         return this.interceptorsQuantity == 0;
      }


   private final var interceptorsListShared: Boolean
   private final var interceptorsListSharedPhase: PipelinePhase?

   init {
      this.phasesRaw = CollectionsKt.mutableListOf(Arrays.copyOf(phases, phases.length));
      this.interceptors$delegate = null;
   }

   fun getInterceptors(): MutableList<(PipelineContext<TSubject, TContext>?, TSubject?, Continuation<? super Unit>?) -> Any> {
      return this.interceptors$delegate as MutableList<(PipelineContext<TSubject, TContext>?, TSubject?, Continuation<? super Unit>?) -> Any>;
   }

   fun setInterceptors(var1: MutableList<(PipelineContext<TSubject, TContext>?, TSubject?, Continuation<? super Unit>?) -> Any>) {
      this.interceptors$delegate = var1;
   }

   public constructor(phase: PipelinePhase, interceptors: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>) : this(phase)
   public suspend fun execute(context: Any, subject: Any): Any {
      return this.createContext((TContext)context, (TSubject)subject, `$completion`.getContext()).execute$ktor_utils((TSubject)subject, `$completion`);
   }

   public fun addPhase(phase: PipelinePhase) {
      if (!this.hasPhase(phase)) {
         this.phasesRaw.add(phase);
      }
   }

   public fun insertPhaseAfter(reference: PipelinePhase, phase: PipelinePhase) {
      if (!this.hasPhase(phase)) {
         val index: Int = this.findPhaseIndex(reference);
         if (index == -1) {
            throw new InvalidPhaseException("Phase $reference was not registered for this pipeline");
         } else {
            var lastRelatedPhaseIndex: Int = index;
            var i: Int = index + 1;
            val var6: Int = CollectionsKt.getLastIndex(this.phasesRaw);
            if (i <= var6) {
               while (true) {
                  val var9: Any = this.phasesRaw.get(i);
                  val var10000: PhaseContent = var9 as? PhaseContent;
                  if ((var9 as? PhaseContent) == null) {
                     break;
                  }

                  val var10: PipelinePhaseRelation = var10000.getRelation();
                  if (var10 == null) {
                     break;
                  }

                  val var11: PipelinePhaseRelation.After = var10 as? PipelinePhaseRelation.After;
                  if ((var10 as? PipelinePhaseRelation.After) != null) {
                     val var12: PipelinePhase = var11.getRelativeTo();
                     if (var12 != null) {
                        lastRelatedPhaseIndex = if (var12 == reference) i else lastRelatedPhaseIndex;
                     }
                  }

                  if (i == var6) {
                     break;
                  }

                  i++;
               }
            }

            this.phasesRaw.add(lastRelatedPhaseIndex + 1, new PhaseContent(phase, new PipelinePhaseRelation.After(reference)));
         }
      }
   }

   public fun insertPhaseBefore(reference: PipelinePhase, phase: PipelinePhase) {
      if (!this.hasPhase(phase)) {
         val index: Int = this.findPhaseIndex(reference);
         if (index == -1) {
            throw new InvalidPhaseException("Phase $reference was not registered for this pipeline");
         } else {
            this.phasesRaw.add(index, new PhaseContent(phase, new PipelinePhaseRelation.Before(reference)));
         }
      }
   }

   public fun intercept(phase: PipelinePhase, block: (PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?) {
      val var10000: PhaseContent = this.findPhase(phase);
      if (var10000 == null) {
         throw new InvalidPhaseException("Phase $phase was not registered for this pipeline");
      } else if (this.tryAddToPhaseFastPath(phase, block)) {
         val var5: Int = this.interceptorsQuantity++;
      } else {
         var10000.addInterceptor(block);
         val var4: Int = this.interceptorsQuantity++;
         this.resetInterceptorsList();
         this.afterIntercepted();
      }
   }

   public open fun afterIntercepted() {
   }

   public fun interceptorsForPhase(phase: PipelinePhase): List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      val `$this$firstOrNull$iv`: java.lang.Iterable = this.phasesRaw;
      val `element$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filterIsInstance$iv) {
         if (`element$iv$iv` is PhaseContent) {
            `element$iv`.add(`element$iv$iv`);
         }
      }

      val `$this$filterIsInstanceTo$iv$iv`: java.util.Iterator = (`element$iv` as java.util.List).iterator();

      var var10000: Any;
      while (true) {
         if (`$this$filterIsInstanceTo$iv$iv`.hasNext()) {
            val var12: Any = `$this$filterIsInstanceTo$iv$iv`.next();
            if (!((var12 as PhaseContent).getPhase() == phase)) {
               continue;
            }

            var10000 = (java.util.List)var12;
            break;
         }

         var10000 = null;
         break;
      }

      var10000 = if (var10000 as PhaseContent != null) (var10000 as PhaseContent).sharedInterceptors() else null;
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }

      return var10000;
   }

   public fun mergePhases(from: Pipeline<Any, Any>) {
      val toInsert: java.util.List = CollectionsKt.toMutableList(from.phasesRaw);

      while (!toInsert.isEmpty()) {
         val iterator: java.util.Iterator = toInsert.iterator();

         while (iterator.hasNext()) {
            val fromPhaseOrContent: Any = iterator.next();
            var var10000: PipelinePhase = fromPhaseOrContent as? PipelinePhase;
            if ((fromPhaseOrContent as? PipelinePhase) == null) {
               var10000 = (fromPhaseOrContent as PhaseContent).getPhase();
            }

            if (this.hasPhase(var10000)) {
               iterator.remove();
            } else if (this.insertRelativePhase(fromPhaseOrContent, var10000)) {
               iterator.remove();
            }
         }
      }
   }

   private fun mergeInterceptors(from: Pipeline<Any, Any>) {
      if (this.interceptorsQuantity == 0) {
         this.setInterceptorsListFromAnotherPipeline(from);
      } else {
         this.resetInterceptorsList();
      }

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         var var10000: PipelinePhase = `element$iv` as? PipelinePhase;
         if ((`element$iv` as? PipelinePhase) == null) {
            var10000 = (`element$iv` as PhaseContent).getPhase();
         }

         if (`element$iv` is PhaseContent && !(`element$iv` as PhaseContent).isEmpty()) {
            val var10: PhaseContent = `element$iv` as PhaseContent;
            val var10001: PhaseContent = this.findPhase(var10000);
            var10.addTo(var10001);
            this.interceptorsQuantity = this.interceptorsQuantity + (`element$iv` as PhaseContent).getSize();
         }
      }
   }

   public fun merge(from: Pipeline<Any, Any>) {
      if (!this.fastPathMerge(from)) {
         this.mergePhases(from);
         this.mergeInterceptors(from);
      }
   }

   public fun resetFrom(from: Pipeline<Any, Any>) {
      this.phasesRaw.clear();
      if (this.interceptorsQuantity != 0) {
         throw new IllegalStateException("Check failed.");
      } else {
         this.fastPathMerge(from);
      }
   }

   public override fun toString(): String {
      return super.toString();
   }

   internal fun phaseInterceptors(phase: PipelinePhase): List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      val var10000: PhaseContent = this.findPhase(phase);
      if (var10000 != null) {
         val var2: java.util.List = var10000.sharedInterceptors();
         if (var2 != null) {
            return var2;
         }
      }

      return CollectionsKt.emptyList();
   }

   internal fun interceptorsForTests(): List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      var var10000: java.util.List = this.getInterceptors();
      if (var10000 == null) {
         var10000 = this.cacheInterceptors();
      }

      return var10000;
   }

   private fun createContext(context: Any, subject: Any, coroutineContext: CoroutineContext): PipelineContext<Any, Any> {
      return PipelineContextKt.pipelineContextFor(
         (TContext)context, this.sharedInterceptorsList(), (TSubject)subject, coroutineContext, this.getDevelopmentMode()
      );
   }

   private fun findPhase(phase: PipelinePhase): PhaseContent<Any, Any>? {
      val phasesList: java.util.List = this.phasesRaw;
      var index: Int = 0;

      for (int var4 = this.phasesRaw.size(); index < var4; index++) {
         val current: Any = phasesList.get(index);
         if (current === phase) {
            val content: PhaseContent = new PhaseContent(phase, PipelinePhaseRelation.Last.INSTANCE);
            phasesList.set(index, content);
            return content;
         }

         if (current is PhaseContent && (current as PhaseContent).getPhase() === phase) {
            return current as PhaseContent<TSubject, TContext>;
         }
      }

      return null;
   }

   private fun findPhaseIndex(phase: PipelinePhase): Int {
      val phasesList: java.util.List = this.phasesRaw;
      var index: Int = 0;

      for (int var4 = this.phasesRaw.size(); index < var4; index++) {
         val current: Any = phasesList.get(index);
         if (current === phase || current is PhaseContent && (current as PhaseContent).getPhase() === phase) {
            return index;
         }
      }

      return -1;
   }

   private fun hasPhase(phase: PipelinePhase): Boolean {
      val phasesList: java.util.List = this.phasesRaw;
      var index: Int = 0;

      for (int var4 = this.phasesRaw.size(); index < var4; index++) {
         val current: Any = phasesList.get(index);
         if (current === phase || current is PhaseContent && (current as PhaseContent).getPhase() === phase) {
            return true;
         }
      }

      return false;
   }

   private fun cacheInterceptors(): List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      if (this.interceptorsQuantity == 0) {
         this.notSharedInterceptorsList(CollectionsKt.emptyList());
         return CollectionsKt.emptyList();
      } else {
         val phases: java.util.List = this.phasesRaw;
         if (this.interceptorsQuantity == 1) {
            var destination: Int = 0;
            val phaseIndex: Int = CollectionsKt.getLastIndex(this.phasesRaw);
            if (0 <= phaseIndex) {
               while (true) {
                  val var7: Any = phases.get(destination);
                  val var10000: PhaseContent = var7 as? PhaseContent;
                  if ((var7 as? PhaseContent) != null) {
                     if (!var10000.isEmpty()) {
                        val var12: java.util.List = var10000.sharedInterceptors();
                        this.setInterceptorsListFromPhase(var10000);
                        return var12;
                     }
                  }

                  if (destination == phaseIndex) {
                     break;
                  }

                  destination++;
               }
            }
         }

         val var9: java.util.List = new ArrayList();
         var var10: Int = 0;
         val var11: Int = CollectionsKt.getLastIndex(phases);
         if (0 <= var11) {
            while (true) {
               val var8: Any = phases.get(var10);
               val var13: PhaseContent = var8 as? PhaseContent;
               if ((var8 as? PhaseContent) != null) {
                  var13.addTo(var9);
               }

               if (var10 == var11) {
                  break;
               }

               var10++;
            }
         }

         this.notSharedInterceptorsList(var9);
         return var9;
      }
   }

   private fun fastPathMerge(from: Pipeline<Any, Any>): Boolean {
      if (from.phasesRaw.isEmpty()) {
         return true;
      } else if (!this.phasesRaw.isEmpty()) {
         return false;
      } else {
         val fromPhases: java.util.List = from.phasesRaw;
         var index: Int = 0;
         val var4: Int = CollectionsKt.getLastIndex(from.phasesRaw);
         if (0 <= var4) {
            while (true) {
               val fromPhaseOrContent: Any = fromPhases.get(index);
               if (fromPhaseOrContent is PipelinePhase) {
                  this.phasesRaw.add(fromPhaseOrContent);
               } else if (fromPhaseOrContent is PhaseContent) {
                  this.phasesRaw
                     .add(
                        new PhaseContent(
                           (fromPhaseOrContent as PhaseContent).getPhase(),
                           (fromPhaseOrContent as PhaseContent).getRelation(),
                           (fromPhaseOrContent as PhaseContent).sharedInterceptors()
                        )
                     );
               }

               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         this.interceptorsQuantity = this.interceptorsQuantity + from.interceptorsQuantity;
         this.setInterceptorsListFromAnotherPipeline(from);
         return true;
      }
   }

   private fun sharedInterceptorsList(): List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      if (this.getInterceptors() == null) {
         this.cacheInterceptors();
      }

      this.interceptorsListShared = true;
      val var10000: java.util.List = this.getInterceptors();
      return var10000;
   }

   private fun resetInterceptorsList() {
      this.setInterceptors(null);
      this.interceptorsListShared = false;
      this.interceptorsListSharedPhase = null;
   }

   private fun notSharedInterceptorsList(list: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>) {
      this.setInterceptors(list);
      this.interceptorsListShared = false;
      this.interceptorsListSharedPhase = null;
   }

   private fun setInterceptorsListFromPhase(phaseContent: PhaseContent<Any, Any>) {
      this.setInterceptors(phaseContent.sharedInterceptors());
      this.interceptorsListShared = false;
      this.interceptorsListSharedPhase = phaseContent.getPhase();
   }

   private fun setInterceptorsListFromAnotherPipeline(pipeline: Pipeline<Any, Any>) {
      this.setInterceptors(pipeline.sharedInterceptorsList());
      this.interceptorsListShared = true;
      this.interceptorsListSharedPhase = null;
   }

   private fun tryAddToPhaseFastPath(phase: PipelinePhase, block: (PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?): Boolean {
      val currentInterceptors: java.util.List = this.getInterceptors();
      if (this.phasesRaw.isEmpty() || currentInterceptors == null) {
         return false;
      } else if (this.interceptorsListShared || !TypeIntrinsics.isMutableList(currentInterceptors)) {
         return false;
      } else if (this.interceptorsListSharedPhase == phase) {
         currentInterceptors.add(block);
         return true;
      } else if (!(phase == CollectionsKt.last(this.phasesRaw)) && this.findPhaseIndex(phase) != CollectionsKt.getLastIndex(this.phasesRaw)) {
         return false;
      } else {
         val var10000: PhaseContent = this.findPhase(phase);
         var10000.addInterceptor(block);
         currentInterceptors.add(block);
         return true;
      }
   }

   private fun insertRelativePhase(fromPhaseOrContent: Any, fromPhase: PipelinePhase): Boolean {
      val var10000: PipelinePhaseRelation;
      if (fromPhaseOrContent === fromPhase) {
         var10000 = PipelinePhaseRelation.Last.INSTANCE;
      } else {
         var10000 = (fromPhaseOrContent as PhaseContent).getRelation();
      }

      if (var10000 is PipelinePhaseRelation.Last) {
         this.addPhase(fromPhase);
      } else if (var10000 is PipelinePhaseRelation.Before && this.hasPhase((var10000 as PipelinePhaseRelation.Before).getRelativeTo())) {
         this.insertPhaseBefore((var10000 as PipelinePhaseRelation.Before).getRelativeTo(), fromPhase);
      } else {
         if (var10000 !is PipelinePhaseRelation.After) {
            return false;
         }

         this.insertPhaseAfter((var10000 as PipelinePhaseRelation.After).getRelativeTo(), fromPhase);
      }

      return true;
   }
}
