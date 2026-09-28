package io.ktor.network.selector

import java.util.ArrayList
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSelectorManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SelectorManager.kt\nio/ktor/network/selector/SelectInterest\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,94:1\n37#2,2:95\n1563#3:97\n1634#3,3:98\n*S KotlinDebug\n*F\n+ 1 SelectorManager.kt\nio/ktor/network/selector/SelectInterest\n*L\n87#1:95,2\n89#1:97\n89#1:98,3\n*E\n"])
public enum class SelectInterest(flag: Int) {
   READ(1),
   WRITE(4),
   ACCEPT(16),
   CONNECT(8)
   public final val flag: Int
   @JvmStatic
   public SelectInterest.Companion Companion = new SelectInterest.Companion(null);
   @JvmStatic
   private SelectInterest[] AllInterests = getEntries().toArray(new SelectInterest[0]);
   @JvmStatic
   private int[] flags;
   @JvmStatic
   private int size;

   init {
      this.flag = flag;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<SelectInterest> {
      return $ENTRIES;
   }

   @JvmStatic
   fun {
      val var10: java.lang.Iterable = getEntries();
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((`item$iv$iv` as SelectInterest).flag);
      }

      flags = CollectionsKt.toIntArray(`destination$iv$iv`);
      size = getEntries().size();
   }

   public companion object {
      public final val AllInterests: Array<SelectInterest>
      public final val flags: IntArray
      public final val size: Int
   }
}
