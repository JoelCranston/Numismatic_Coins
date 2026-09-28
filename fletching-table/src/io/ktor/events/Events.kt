package io.ktor.events

import io.ktor.util.collections.CopyOnWriteHashMap
import io.ktor.util.internal.LockFreeLinkedListHead
import io.ktor.util.internal.LockFreeLinkedListNode
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.coroutines.DisposableHandle

@SourceDebugExtension(["SMAP\nEvents.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Events.kt\nio/ktor/events/Events\n+ 2 LockFreeLinkedList.kt\nio/ktor/util/internal/LockFreeLinkedListHead\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n832#2,6:103\n832#2,3:109\n835#2,3:113\n1#3:112\n*S KotlinDebug\n*F\n+ 1 Events.kt\nio/ktor/events/Events\n*L\n34#1:103,6\n49#1:109,3\n49#1:113,3\n*E\n"])
public class Events {
   private final val handlers: CopyOnWriteHashMap<EventDefinition<*>, LockFreeLinkedListHead> = new CopyOnWriteHashMap()

   public fun <T> subscribe(definition: EventDefinition<Any>, handler: (Any) -> Unit): DisposableHandle {
      val registration: Events.HandlerRegistration = new Events.HandlerRegistration(handler);
      this.handlers.computeIfAbsent(definition, Events::subscribe$lambda$0).addLast(registration);
      return registration;
   }

   public fun <T> unsubscribe(definition: EventDefinition<Any>, handler: (Any) -> Unit) {
      val var10000: LockFreeLinkedListHead = this.handlers.get(definition);
      if (var10000 != null) {
         val `this_$iv`: LockFreeLinkedListHead = var10000;
         val var8: Any = var10000.getNext();

         for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode)var8; !(`cur$iv` == `this_$iv`); cur$iv = cur$iv.getNextNode()) {
            if (`cur$iv` is Events.HandlerRegistration) {
               val it: Events.HandlerRegistration = `cur$iv` as Events.HandlerRegistration;
               if ((`cur$iv` as Events.HandlerRegistration).getHandler() == handler) {
                  it.remove();
               }
            }
         }
      }
   }

   public fun <T> raise(definition: EventDefinition<Any>, value: Any) {
      var exception: Any = null;
      val var10000: LockFreeLinkedListHead = this.handlers.get(definition);
      if (var10000 != null) {
         val `this_$iv`: LockFreeLinkedListHead = var10000;
         var var15: Any = var10000.getNext();

         for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode)var15; !(var7 == `this_$iv`); cur$iv = cur$iv.getNextNode()) {
            if (var7 is Events.HandlerRegistration) {
               val registration: Events.HandlerRegistration = var7 as Events.HandlerRegistration;

               try {
                  var15 = registration.getHandler();
                  (TypeIntrinsics.beforeCheckcastToFunctionOfArity(var15, 1) as Function1).invoke(value);
               } catch (var13: java.lang.Throwable) {
                  var15 = exception as java.lang.Throwable;
                  if (exception as java.lang.Throwable != null) {
                     ExceptionsKt.addSuppressed((java.lang.Throwable)var15, var13);
                  } else {
                     exception = var13;
                  }
               }
            }
         }
      }

      if (exception != null) {
         throw exception;
      }
   }

   @JvmStatic
   fun `subscribe$lambda$0`(it: EventDefinition): LockFreeLinkedListHead {
      return new LockFreeLinkedListHead();
   }

   private class HandlerRegistration(handler: (*) -> Unit) : LockFreeLinkedListNode, DisposableHandle {
      public final val handler: (*) -> Unit

      init {
         this.handler = handler;
      }

      public override fun dispose() {
         this.remove();
      }
   }
}
