@file:JvmName(name = "LateinitKt")

package kotlin

import kotlin.internal.InlineOnly

@SinceKotlin(
   version = "1.2"
)
@InlineOnly
public final val isInitialized: Boolean
   public final inline get() {
      throw new NotImplementedError("Implementation is intrinsic");
   }

