@file:SourceDebugExtension(["SMAP\nEither.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Either.kt\ndev/kikugie/commons/result/EitherKt\n+ 2 Either.kt\ndev/kikugie/commons/result/Either$Companion\n*L\n1#1,111:1\n107#1,2:112\n109#1:115\n107#1,3:117\n107#1,3:120\n61#1:123\n107#1,3:124\n61#1:127\n107#1,3:128\n107#1,3:131\n107#1,2:134\n109#1:137\n107#1,3:138\n107#1,3:141\n92#1:144\n107#1,3:145\n92#1:148\n107#1,3:149\n107#1,3:152\n107#1,3:155\n18#2:114\n17#2:116\n17#2:136\n18#2:158\n*S KotlinDebug\n*F\n+ 1 Either.kt\ndev/kikugie/commons/result/EitherKt\n*L\n37#1:112,2\n37#1:115\n42#1:117,3\n48#1:120,3\n52#1:123\n52#1:124,3\n56#1:127\n56#1:128,3\n61#1:131,3\n67#1:134,2\n67#1:137\n73#1:138,3\n79#1:141,3\n83#1:144\n83#1:145,3\n87#1:148\n87#1:149,3\n92#1:152,3\n98#1:155,3\n37#1:114\n37#1:116\n67#1:136\n98#1:158\n*E\n"])

package dev.kikugie.commons.result

import dev.kikugie.commons.ExperimentalCommonsAPI
import java.util.NoSuchElementException
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.swap(): Either<R, L> {
   val var10000: Either;
   if (`$this$swap` is Either.Left) {
      val it: Any = (`$this$swap` as Either.Left).getValue();
      val `this_$iv`: Either.Companion = Either.Companion;
      var10000 = new Either.Right<>(it);
   } else {
      if (`$this$swap` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val var10: Any = (`$this$swap` as Either.Right).getValue();
      val var12: Either.Companion = Either.Companion;
      var10000 = new Either.Left<>(var10);
   }

   return var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.ifLeft(action: (L) -> Unit): Either<L, R> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (`$this$ifLeft` is Either.Left) {
      action.invoke((`$this$ifLeft` as Either.Left).getValue());
   } else {
      if (`$this$ifLeft` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val it: Any = (`$this$ifLeft` as Either.Right).getValue();
   }

   return `$this$ifLeft`;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.leftOrNull(): L? {
   val var10000: Any;
   if (`$this$leftOrNull` is Either.Left) {
      var10000 = (`$this$leftOrNull` as Either.Left).getValue();
   } else {
      if (`$this$leftOrNull` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val var7: Any = (`$this$leftOrNull` as Either.Right).getValue();
      var10000 = null;
   }

   return (L)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.leftOrThrow(): L {
   if (`$this$leftOrThrow` is Either.Left) {
      return (L)(`$this$leftOrThrow` as Either.Left).getValue();
   } else if (`$this$leftOrThrow` is Either.Right) {
      val it: Any = (`$this$leftOrThrow` as Either.Right).getValue();
      throw new NoSuchElementException("No value present");
   } else {
      throw new NoWhenBranchMatchedException();
   }
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.leftOrDefault(default: L): L {
   val var10000: Any;
   if (`$this$leftOrDefault` is Either.Left) {
      var10000 = (`$this$leftOrDefault` as Either.Left).getValue();
   } else {
      if (`$this$leftOrDefault` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val it: Any = (`$this$leftOrDefault` as Either.Right).getValue();
      var10000 = var1;
   }

   return (L)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.leftOrElse(action: (R) -> L): L {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (`$this$leftOrElse` is Either.Left) {
      var10000 = (`$this$leftOrElse` as Either.Left).getValue();
   } else {
      if (`$this$leftOrElse` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = action.invoke((`$this$leftOrElse` as Either.Right).getValue());
   }

   return (L)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R, O> Either<L, R>.mapLeft(action: (L) -> O): Either<O, R> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Either;
   if (`$this$mapLeft` is Either.Left) {
      val it: Any = (`$this$mapLeft` as Either.Left).getValue();
      val `this_$iv`: Either.Companion = Either.Companion;
      var10000 = new Either.Left<>(action.invoke(it));
   } else {
      if (`$this$mapLeft` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val var11: Any = (`$this$mapLeft` as Either.Right).getValue();
      var10000 = var11 as Either;
   }

   return var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.ifRight(action: (R) -> Unit): Either<L, R> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (`$this$ifRight` is Either.Left) {
      val it: Any = (`$this$ifRight` as Either.Left).getValue();
   } else {
      if (`$this$ifRight` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      action.invoke((`$this$ifRight` as Either.Right).getValue());
   }

   return `$this$ifRight`;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.rightOrNull(): R? {
   val var10000: Any;
   if (`$this$rightOrNull` is Either.Left) {
      val it: Any = (`$this$rightOrNull` as Either.Left).getValue();
      var10000 = null;
   } else {
      if (`$this$rightOrNull` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = (`$this$rightOrNull` as Either.Right).getValue();
   }

   return (R)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.rightOrThrow(): R {
   if (`$this$rightOrThrow` is Either.Left) {
      val it: Any = (`$this$rightOrThrow` as Either.Left).getValue();
      throw new NoSuchElementException("No value present");
   } else if (`$this$rightOrThrow` is Either.Right) {
      return (R)(`$this$rightOrThrow` as Either.Right).getValue();
   } else {
      throw new NoWhenBranchMatchedException();
   }
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.rightOrDefault(default: R): R {
   val var10000: Any;
   if (`$this$rightOrDefault` is Either.Left) {
      val it: Any = (`$this$rightOrDefault` as Either.Left).getValue();
      var10000 = var1;
   } else {
      if (`$this$rightOrDefault` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = (`$this$rightOrDefault` as Either.Right).getValue();
   }

   return (R)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R> Either<L, R>.rightOrElse(action: (L) -> R): R {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (`$this$rightOrElse` is Either.Left) {
      var10000 = action.invoke((`$this$rightOrElse` as Either.Left).getValue());
   } else {
      if (`$this$rightOrElse` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = (`$this$rightOrElse` as Either.Right).getValue();
   }

   return (R)var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R, O> Either<L, R>.mapRight(action: (R) -> O): Either<L, O> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Either;
   if (`$this$mapRight` is Either.Left) {
      val it: Any = (`$this$mapRight` as Either.Left).getValue();
      var10000 = it as Either;
   } else {
      if (`$this$mapRight` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      val var11: Any = (`$this$mapRight` as Either.Right).getValue();
      val `this_$iv`: Either.Companion = Either.Companion;
      var10000 = new Either.Right<>(action.invoke(var11));
   }

   return var10000;
}

@ExperimentalCommonsAPI
public inline fun <L, R, O> Either<L, R>.fold(ifLeft: (L) -> O, ifRight: (R) -> O): O {
   contract {
      callsInPlace(ifLeft, InvocationKind.AT_MOST_ONCE)
      callsInPlace(ifRight, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (`$this$fold` is Either.Left) {
      var10000 = ifLeft.invoke((`$this$fold` as Either.Left).getValue());
   } else {
      if (`$this$fold` !is Either.Right) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = ifRight.invoke((`$this$fold` as Either.Right).getValue());
   }

   return (O)var10000;
}
