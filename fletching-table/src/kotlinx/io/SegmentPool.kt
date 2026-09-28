package kotlinx.io

import java.util.concurrent.atomic.AtomicReferenceArray

internal object SegmentPool {
   public final val MAX_SIZE: Int = 65536
   private final val LOCK: Segment = Segment.Companion.new$kotlinx_io_core(new byte[0], 0, 0, null, false)
   internal final val HASH_BUCKET_COUNT: Int = Integer.highestOneBit(Runtime.getRuntime().availableProcessors() * 2 - 1)
   private final val HASH_BUCKET_COUNT_L2: Int = RangesKt.coerceAtLeast(HASH_BUCKET_COUNT / 2, 1)
   private final val DEFAULT_SECOND_LEVEL_POOL_TOTAL_SIZE: String = if (System.getProperty("java.vm.name") == "Dalvik") "0" else "4194304"
   internal final val SECOND_LEVEL_POOL_TOTAL_SIZE: Int
   private final val SECOND_LEVEL_POOL_BUCKET_SIZE: Int
   private final val hashBuckets: AtomicReferenceArray<Segment?> = new AtomicReferenceArray(HASH_BUCKET_COUNT)
   private final val hashBucketsL2: AtomicReferenceArray<Segment?> = new AtomicReferenceArray(HASH_BUCKET_COUNT_L2)

   public final val byteCount: Int
      public final get() {
         val var10000: Segment = hashBuckets.get(this.l1BucketId());
         return if (var10000 == null) 0 else var10000.getLimit();
      }


   @JvmStatic
   public fun take(): Segment {
      val buckets: AtomicReferenceArray = hashBuckets;
      val bucketId: Int = INSTANCE.l1BucketId();

      val first: Segment;
      do {
         first = buckets.getAndSet(bucketId, LOCK);
      } while (first == LOCK);

      if (first == null) {
         buckets.set(bucketId, null);
         return if (SECOND_LEVEL_POOL_TOTAL_SIZE > 0) takeL2() else Segment.Companion.new$kotlinx_io_core();
      } else {
         buckets.set(bucketId, first.getNext());
         first.setNext(null);
         first.setLimit(0);
         return first;
      }
   }

   @JvmStatic
   private fun takeL2(): Segment {
      val buckets: AtomicReferenceArray = hashBucketsL2;
      var bucket: Int = INSTANCE.l2BucketId();
      var attempts: Int = 0;

      while (true) {
         val first: Segment = buckets.getAndSet(bucket, LOCK);
         if (!(first == LOCK)) {
            if (first != null) {
               buckets.set(bucket, first.getNext());
               first.setNext(null);
               first.setLimit(0);
               return first;
            }

            buckets.set(bucket, null);
            if (attempts >= HASH_BUCKET_COUNT_L2) {
               return Segment.Companion.new$kotlinx_io_core();
            }

            bucket = bucket + 1 and HASH_BUCKET_COUNT_L2 - 1;
            attempts++;
         }
      }
   }

   @JvmStatic
   public fun recycle(segment: Segment) {
      if (segment.getNext() != null || segment.getPrev() != null) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         val var10000: SegmentCopyTracker = segment.getCopyTracker$kotlinx_io_core();
         if (var10000 == null || !var10000.removeCopy()) {
            val buckets: AtomicReferenceArray = hashBuckets;
            val bucketId: Int = INSTANCE.l1BucketId();
            segment.setPos(0);
            segment.owner = true;

            while (true) {
               val first: Segment = buckets.get(bucketId) as Segment;
               if (first != LOCK) {
                  val firstLimit: Int = if (first != null) first.getLimit() else 0;
                  if (firstLimit >= MAX_SIZE) {
                     if (SECOND_LEVEL_POOL_TOTAL_SIZE > 0) {
                        recycleL2(segment);
                     }

                     return;
                  }

                  segment.setNext(first);
                  segment.setLimit(firstLimit + 8192);
                  if (buckets.compareAndSet(bucketId, first, segment)) {
                     return;
                  }
               }
            }
         }
      }
   }

   @JvmStatic
   private fun recycleL2(segment: Segment) {
      segment.setPos(0);
      segment.owner = true;
      var bucket: Int = INSTANCE.l2BucketId();
      val buckets: AtomicReferenceArray = hashBucketsL2;
      var attempts: Int = 0;

      while (true) {
         val first: Segment = buckets.get(bucket) as Segment;
         if (first != LOCK) {
            val firstLimit: Int = if (first != null) first.getLimit() else 0;
            if (firstLimit + 8192 > SECOND_LEVEL_POOL_BUCKET_SIZE) {
               if (attempts >= HASH_BUCKET_COUNT_L2) {
                  return;
               }

               attempts++;
               bucket = bucket + 1 and HASH_BUCKET_COUNT_L2 - 1;
            } else {
               segment.setNext(first);
               segment.setLimit(firstLimit + 8192);
               if (buckets.compareAndSet(bucket, first, segment)) {
                  return;
               }
            }
         }
      }
   }

   @JvmStatic
   public fun tracker(): SegmentCopyTracker {
      return new RefCountingCopyTracker();
   }

   private fun l1BucketId(): Int {
      return this.bucketId((long)HASH_BUCKET_COUNT - 1L);
   }

   private fun l2BucketId(): Int {
      return this.bucketId((long)HASH_BUCKET_COUNT_L2 - 1L);
   }

   private fun bucketId(mask: Long): Int {
      return (int)(Thread.currentThread().getId() and mask);
   }

   @JvmStatic
   fun {
      val var10000: java.lang.String = System.getProperty("kotlinx.io.pool.size.bytes", DEFAULT_SECOND_LEVEL_POOL_TOTAL_SIZE);
      val var0: Int = StringsKt.toIntOrNull(var10000);
      SECOND_LEVEL_POOL_TOTAL_SIZE = if (var0 != null) RangesKt.coerceAtLeast(var0, 0) else 0;
      SECOND_LEVEL_POOL_BUCKET_SIZE = RangesKt.coerceAtLeast(SECOND_LEVEL_POOL_TOTAL_SIZE / HASH_BUCKET_COUNT_L2, 8192);
   }
}
