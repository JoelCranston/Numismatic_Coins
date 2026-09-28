package kotlinx.coroutines.internal

@JvmInline
internal inline class SegmentOrClosed<S extends Segment<S>> {
   private final val value: Any?

   public final val isClosed: Boolean
      public final get() {
         return arg0 === ConcurrentLinkedListKt.access$getCLOSED$p();
      }


   public final val segment: Any
      public final get() {
         if (arg0 === ConcurrentLinkedListKt.access$getCLOSED$p()) {
            throw new IllegalStateException("Does not contain segment".toString());
         } else {
            return (S)arg0;
         }
      }


   @JvmStatic
   fun `toString-impl`(arg0: Any): java.lang.String {
      return "SegmentOrClosed(value=$arg0)";
   }

   public override fun toString(): String {
      return toString-impl(this.value);
   }

   @JvmStatic
   fun `hashCode-impl`(arg0: Any): Int {
      return if (arg0 == null) 0 else arg0.hashCode();
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   fun `equals-impl`(arg0: Any, other: Any): Boolean {
      if (other !is SegmentOrClosed) {
         return false;
      } else {
         return arg0 == (other as SegmentOrClosed).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.value, other);
   }

   @JvmStatic
   fun <S extends Segment<S>> `constructor-impl`(value: Any?): Any {
      return value;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Any, p2: Any): Boolean {
      return p1 == p2;
   }
}
