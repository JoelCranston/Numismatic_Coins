package io.ktor.util.pipeline

import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics

@SourceDebugExtension(["SMAP\nPhaseContent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PhaseContent.kt\nio/ktor/util/pipeline/PhaseContent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,80:1\n1#2:81\n*E\n"])
internal class PhaseContent<TSubject, Call>(phase: PipelinePhase,
   relation: PipelinePhaseRelation,
   interceptors: MutableList<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>
) {
   public final val phase: PipelinePhase
   public final val relation: PipelinePhaseRelation
   private final var interceptors: MutableList<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>
   public final var shared: Boolean

   public final val isEmpty: Boolean
      public final get() {
         return this.interceptors.isEmpty();
      }


   public final val size: Int
      public final get() {
         return this.interceptors.size();
      }


   init {
      this.phase = phase;
      this.relation = relation;
      this.interceptors = interceptors;
      this.shared = true;
   }

   public constructor(phase: PipelinePhase, relation: PipelinePhaseRelation)  {
      val var10003: java.util.List = SharedArrayList;
      this(phase, relation, TypeIntrinsics.asMutableList(var10003));
      if (!SharedArrayList.isEmpty()) {
         throw new IllegalStateException("The shared empty array list has been modified".toString());
      }
   }

   public fun addInterceptor(interceptor: (PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?) {
      if (this.shared) {
         this.copyInterceptors();
      }

      this.interceptors.add(interceptor);
   }

   public fun addTo(destination: MutableList<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>) {
      val interceptors: java.util.List = this.interceptors;
      if (destination is ArrayList) {
         (destination as ArrayList).ensureCapacity((destination as ArrayList).size() + interceptors.size());
      }

      var index: Int = 0;

      for (int var4 = this.interceptors.size(); index < var4; index++) {
         destination.add(interceptors.get(index));
      }
   }

   public fun addTo(destination: PhaseContent<Any, Any>) {
      if (!this.isEmpty()) {
         if (destination.isEmpty()) {
            destination.interceptors = this.sharedInterceptors();
            destination.shared = true;
         } else {
            if (destination.shared) {
               destination.copyInterceptors();
            }

            this.addTo(destination.interceptors);
         }
      }
   }

   public fun sharedInterceptors(): MutableList<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      this.shared = true;
      return this.interceptors;
   }

   private fun copiedInterceptors(): MutableList<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?> {
      return CollectionsKt.toMutableList(this.interceptors);
   }

   public override fun toString(): String {
      return "Phase `${this.phase.getName()}`, ${this.getSize()} handlers";
   }

   private fun copyInterceptors() {
      this.interceptors = this.copiedInterceptors();
      this.shared = false;
   }

   public companion object {
      public final val SharedArrayList: MutableList<Any?>
   }
}
