package io.ktor.sse

import io.ktor.utils.io.InternalAPI
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nServerSentEvent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServerSentEvent.kt\nio/ktor/sse/TypedServerSentEvent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,117:1\n1#2:118\n*E\n"])
public data class TypedServerSentEvent<T>(data: Any? = null, event: String? = null, id: String? = null, retry: Long? = null, comments: String? = null) :
   ServerSentEventMetadata<T> {
   public open val data: Any?
   public open val event: String?
   public open val id: String?
   public open val retry: Long?
   public open val comments: String?

   init {
      this.data = (T)data;
      this.event = event;
      this.id = id;
      this.retry = retry;
      this.comments = comments;
   }

   @InternalAPI
   public fun toString(serializer: (Any) -> String): String {
      val var10000: Any = this.getData();
      return ServerSentEventKt.access$eventToString(
         if (var10000 != null) serializer.invoke(var10000) as java.lang.String else null, this.getEvent(), this.getId(), this.getRetry(), this.getComments()
      );
   }

   public operator fun component1(): Any? {
      return this.data;
   }

   public operator fun component2(): String? {
      return this.event;
   }

   public operator fun component3(): String? {
      return this.id;
   }

   public operator fun component4(): Long? {
      return this.retry;
   }

   public operator fun component5(): String? {
      return this.comments;
   }

   public fun copy(data: Any? = this.data, event: String? = this.event, id: String? = this.id, retry: Long? = this.retry, comments: String? = this.comments): TypedServerSentEvent<
         Any
      > {
      return new TypedServerSentEvent<>((T)data, event, id, retry, comments);
   }

   public override fun toString(): String {
      return "TypedServerSentEvent(data=${this.data}, event=${this.event}, id=${this.id}, retry=${this.retry}, comments=${this.comments})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        ((if (this.data == null) 0 else this.data.hashCode()) * 31 + (if (this.event == null) 0 else this.event.hashCode())) * 31
                           + (if (this.id == null) 0 else this.id.hashCode())
                     )
                     * 31
                  + (if (this.retry == null) 0 else this.retry.hashCode())
            )
            * 31
         + (if (this.comments == null) 0 else this.comments.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is TypedServerSentEvent) {
         return false;
      } else {
         val var2: TypedServerSentEvent = other as TypedServerSentEvent;
         if (!(this.data == (other as TypedServerSentEvent).data)) {
            return false;
         } else if (!(this.event == var2.event)) {
            return false;
         } else if (!(this.id == var2.id)) {
            return false;
         } else if (!(this.retry == var2.retry)) {
            return false;
         } else {
            return this.comments == var2.comments;
         }
      }
   }

   fun TypedServerSentEvent() {
      this(null, null, null, null, null, 31, null);
   }
}
