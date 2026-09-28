@file:JvmName(name = "StreamsKt")

package kotlin.streams.jdk8

import java.util.Spliterator
import java.util.Spliterators
import java.util.stream.Collectors
import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream
import java.util.stream.StreamSupport
import kotlin.streams.jdk8.StreamsKt.asSequence..inlined.Sequence.1
import kotlin.streams.jdk8.StreamsKt.asSequence..inlined.Sequence.2
import kotlin.streams.jdk8.StreamsKt.asSequence..inlined.Sequence.3
import kotlin.streams.jdk8.StreamsKt.asSequence..inlined.Sequence.4

@SinceKotlin(version = "1.2")
public fun <T> Stream<T>.asSequence(): Sequence<T> {
   return new 1(`$this$asSequence`);
}

@SinceKotlin(version = "1.2")
public fun IntStream.asSequence(): Sequence<Int> {
   return new 2(`$this$asSequence`);
}

@SinceKotlin(version = "1.2")
public fun LongStream.asSequence(): Sequence<Long> {
   return new 3(`$this$asSequence`);
}

@SinceKotlin(version = "1.2")
public fun DoubleStream.asSequence(): Sequence<Double> {
   return new 4(`$this$asSequence`);
}

@SinceKotlin(version = "1.2")
public fun <T> Sequence<T>.asStream(): Stream<T> {
   val var10000: Stream = StreamSupport.stream(StreamsKt::asStream$lambda$0, 16, false);
   return var10000;
}

@SinceKotlin(version = "1.2")
public fun <T> Stream<T>.toList(): List<T> {
   val var10000: Any = `$this$toList`.collect(Collectors.toList());
   return var10000 as MutableList<T>;
}

@SinceKotlin(version = "1.2")
public fun IntStream.toList(): List<Int> {
   val var10000: IntArray = `$this$toList`.toArray();
   return ArraysKt.asList(var10000);
}

@SinceKotlin(version = "1.2")
public fun LongStream.toList(): List<Long> {
   val var10000: LongArray = `$this$toList`.toArray();
   return ArraysKt.asList(var10000);
}

@SinceKotlin(version = "1.2")
public fun DoubleStream.toList(): List<Double> {
   val var10000: DoubleArray = `$this$toList`.toArray();
   return ArraysKt.asList(var10000);
}

fun `asStream$lambda$0`(`$this_asStream`: Sequence): Spliterator {
   return Spliterators.spliteratorUnknownSize(`$this_asStream`.iterator(), 16);
}
