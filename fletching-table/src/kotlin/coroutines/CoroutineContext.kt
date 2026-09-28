package kotlin.coroutines

@SinceKotlin(version = "1.3")
public interface CoroutineContext {
   public abstract operator fun <E : kotlin.coroutines.CoroutineContext.Element> get(key: kotlin.coroutines.CoroutineContext.Key<E>): E? {
   }

   public abstract fun <R> fold(initial: R, operation: (R, kotlin.coroutines.CoroutineContext.Element) -> R): R {
   }

   public open operator fun plus(context: CoroutineContext): CoroutineContext {
   }

   public abstract fun minusKey(key: kotlin.coroutines.CoroutineContext.Key<*>): CoroutineContext {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun plus(`$this`: CoroutineContext, context: CoroutineContext): CoroutineContext {
         return if (context === EmptyCoroutineContext.INSTANCE) `$this` else context.fold(`$this`, CoroutineContext.DefaultImpls::plus$lambda$0);
      }

      @JvmStatic
      fun `plus$lambda$0`(acc: CoroutineContext, element: CoroutineContext.Element): CoroutineContext {
         val removed: CoroutineContext = acc.minusKey(element.getKey());
         val var10000: CoroutineContext;
         if (removed === EmptyCoroutineContext.INSTANCE) {
            var10000 = element;
         } else {
            val interceptor: ContinuationInterceptor = removed.get(ContinuationInterceptor.Key);
            val var5: CombinedContext;
            if (interceptor == null) {
               var5 = new CombinedContext(removed, element);
            } else {
               val left: CoroutineContext = removed.minusKey(ContinuationInterceptor.Key);
               var5 = if (left === EmptyCoroutineContext.INSTANCE)
                  new CombinedContext(element, interceptor)
                  else
                  new CombinedContext(new CombinedContext(left, element), interceptor);
            }

            var10000 = var5;
         }

         return var10000;
      }
   }

   public interface Element : CoroutineContext {
      public val key: kotlin.coroutines.CoroutineContext.Key<*>

      public override operator fun <E : kotlin.coroutines.CoroutineContext.Element> get(key: kotlin.coroutines.CoroutineContext.Key<E>): E? {
      }

      public override fun <R> fold(initial: R, operation: (R, kotlin.coroutines.CoroutineContext.Element) -> R): R {
      }

      public override fun minusKey(key: kotlin.coroutines.CoroutineContext.Key<*>): CoroutineContext {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <E extends CoroutineContext.Element> get(`$this`: CoroutineContext.Element, key: CoroutineContextKey<E>): E? {
            val var10000: CoroutineContext.Element;
            if (`$this`.getKey() == key) {
               var10000 = `$this`;
            } else {
               var10000 = null;
            }

            return (E)var10000;
         }

         @JvmStatic
         fun <R> fold(`$this`: CoroutineContext.Element, initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
            return (R)operation.invoke(initial, `$this`);
         }

         @JvmStatic
         fun minusKey(`$this`: CoroutineContext.Element, key: CoroutineContextKey<?>): CoroutineContext {
            return if (`$this`.getKey() == key) EmptyCoroutineContext.INSTANCE else `$this`;
         }

         @JvmStatic
         fun plus(`$this`: CoroutineContext.Element, context: CoroutineContext): CoroutineContext {
            return CoroutineContext.DefaultImpls.plus(`$this`, context);
         }
      }
   }

   public interface Key<E extends CoroutineContext.Element>
}
