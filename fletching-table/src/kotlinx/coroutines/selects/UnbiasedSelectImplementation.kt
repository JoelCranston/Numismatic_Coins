package kotlinx.coroutines.selects

import java.util.ArrayList
import java.util.Collections
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.selects.SelectImplementation.ClauseData

@PublishedApi
@SourceDebugExtension(["SMAP\nSelectUnbiased.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SelectUnbiased.kt\nkotlinx/coroutines/selects/UnbiasedSelectImplementation\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,65:1\n1863#2,2:66\n*S KotlinDebug\n*F\n+ 1 SelectUnbiased.kt\nkotlinx/coroutines/selects/UnbiasedSelectImplementation\n*L\n60#1:66,2\n*E\n"])
internal open class UnbiasedSelectImplementation<R>(context: CoroutineContext) : SelectImplementation(context) {
   private final val clausesToRegister: MutableList<ClauseData> = (new ArrayList()) as java.util.List

   public override operator fun SelectClause0.invoke(block: (Continuation<Any>) -> Any?) {
      this.clausesToRegister
         .add(
            this as SelectImplementation.new ClauseData(
               this,
               (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
               `$this$invoke`.getRegFunc(),
               `$this$invoke`.getProcessResFunc(),
               SelectKt.getPARAM_CLAUSE_0(),
               block,
               `$this$invoke`.getOnCancellationConstructor()
            )
         );
   }

   public override operator fun <Q> SelectClause1<Q>.invoke(block: (Q, Continuation<Any>) -> Any?) {
      this.clausesToRegister
         .add(
            this as SelectImplementation.new ClauseData(
               this,
               (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
               `$this$invoke`.getRegFunc(),
               `$this$invoke`.getProcessResFunc(),
               null,
               block,
               `$this$invoke`.getOnCancellationConstructor()
            )
         );
   }

   public override operator fun <P, Q> SelectClause2<P, Q>.invoke(param: P, block: (Q, Continuation<Any>) -> Any?) {
      this.clausesToRegister
         .add(
            this as SelectImplementation.new ClauseData(
               this,
               (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
               `$this$invoke`.getRegFunc(),
               `$this$invoke`.getProcessResFunc(),
               param,
               block,
               `$this$invoke`.getOnCancellationConstructor()
            )
         );
   }

   @PublishedApi
   internal override suspend fun doSelect(): Any {
      return doSelect$suspendImpl(this, `$completion`);
   }

   private fun shuffleAndRegisterClauses() {
      label21: {
         try {
            Collections.shuffle(this.clausesToRegister);

            val `$this$forEach$iv`: java.lang.Iterable;
            for (Object element$iv : $this$forEach$iv) {
               SelectImplementation.register$default(this, `element$iv` as SelectImplementation.ClauseData, false, 1, null);
            }
         } catch (var7: java.lang.Throwable) {
            this.clausesToRegister.clear();
         }

         this.clausesToRegister.clear();
      }
   }
}
