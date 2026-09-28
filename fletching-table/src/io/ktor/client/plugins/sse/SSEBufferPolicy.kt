package io.ktor.client.plugins.sse

import kotlin.jvm.internal.SourceDebugExtension

public sealed interface SSEBufferPolicy {
   public data object All : SSEBufferPolicy {
      public override fun toString(): String {
         return "All";
      }

      public override fun hashCode(): Int {
         return 1036633514;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is SSEBufferPolicy.All;
         }
      }
   }

   public data object LastEvent : SSEBufferPolicy {
      public override fun toString(): String {
         return "LastEvent";
      }

      public override fun hashCode(): Int {
         return 1946844909;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is SSEBufferPolicy.LastEvent;
         }
      }
   }

   @SourceDebugExtension(["SMAP\nSSEBufferPolicy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SSEBufferPolicy.kt\nio/ktor/client/plugins/sse/SSEBufferPolicy$LastEvents\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,109:1\n1#2:110\n*E\n"])
   public data class LastEvents(count: Int) : SSEBufferPolicy {
      public final val count: Int

      init {
         this.count = count;
         if (this.count <= 0) {
            throw new IllegalArgumentException("Count must be > 0".toString());
         }
      }

      public operator fun component1(): Int {
         return this.count;
      }

      public fun copy(count: Int = this.count): io.ktor.client.plugins.sse.SSEBufferPolicy.LastEvents {
         return new SSEBufferPolicy.LastEvents(count);
      }

      public override fun toString(): String {
         return "LastEvents(count=${this.count})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.count);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is SSEBufferPolicy.LastEvents) {
            return false;
         } else {
            return this.count == (other as SSEBufferPolicy.LastEvents).count;
         }
      }
   }

   @SourceDebugExtension(["SMAP\nSSEBufferPolicy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SSEBufferPolicy.kt\nio/ktor/client/plugins/sse/SSEBufferPolicy$LastLines\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,109:1\n1#2:110\n*E\n"])
   public data class LastLines(count: Int) : SSEBufferPolicy {
      public final val count: Int

      init {
         this.count = count;
         if (this.count <= 0) {
            throw new IllegalArgumentException("Count must be > 0".toString());
         }
      }

      public operator fun component1(): Int {
         return this.count;
      }

      public fun copy(count: Int = this.count): io.ktor.client.plugins.sse.SSEBufferPolicy.LastLines {
         return new SSEBufferPolicy.LastLines(count);
      }

      public override fun toString(): String {
         return "LastLines(count=${this.count})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.count);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is SSEBufferPolicy.LastLines) {
            return false;
         } else {
            return this.count == (other as SSEBufferPolicy.LastLines).count;
         }
      }
   }

   public data object Off : SSEBufferPolicy {
      public override fun toString(): String {
         return "Off";
      }

      public override fun hashCode(): Int {
         return 1036646776;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is SSEBufferPolicy.Off;
         }
      }
   }
}
