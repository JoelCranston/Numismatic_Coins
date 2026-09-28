package kotlin

import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly

internal class ContextParametersKt__ContextKt {
   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <T, R> context(with: T, block: (T) -> R): R {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (R)block.invoke(with);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, Result> context(a: A, b: B, block: (A, B) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, Result> context(a: A, b: B, c: C, block: (A, B, C) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, Result> context(a: A, b: B, c: C, d: D, block: (A, B, C, D) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, Result> context(a: A, b: B, c: C, d: D, e: E, block: (A, B, C, D, E) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, Result> context(a: A, b: B, c: C, d: D, e: E, f: F, block: (A, B, C, D, E, F) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, Result> context(a: A, b: B, c: C, d: D, e: E, f: F, g: G, block: (A, B, C, D, E, F, G) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, Result> context(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, block: (A, B, C, D, E, F, G, H) -> Result): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      block: (A, B, C, D, E, F, G, H, I) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      block: (A, B, C, D, E, F, G, H, I, J) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      block: (A, B, C, D, E, F, G, H, I, J, K) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      block: (A, B, C, D, E, F, G, H, I, J, K, L) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      r: R,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      r: R,
      s: S,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      r: R,
      s: S,
      t: T,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      r: R,
      s: S,
      t: T,
      u: U,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u);
   }

   @InlineOnly
   @SinceKotlin(version = "2.2")
   @JvmStatic
   public inline fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Result> context(
      a: A,
      b: B,
      c: C,
      d: D,
      e: E,
      f: F,
      g: G,
      h: H,
      i: I,
      j: J,
      k: K,
      l: L,
      m: M,
      n: N,
      o: O,
      p: P,
      q: Q,
      r: R,
      s: S,
      t: T,
      u: U,
      v: V,
      block: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V) -> Result
   ): Result {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      return (Result)block.invoke(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v);
   }

   open fun ContextParametersKt__ContextKt() {
   }
}
