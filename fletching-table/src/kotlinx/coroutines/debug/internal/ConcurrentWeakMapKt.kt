package kotlinx.coroutines.debug.internal

import kotlinx.coroutines.internal.Symbol

private const val MAGIC: Int = -1640531527
private const val MIN_CAPACITY: Int = 16
private final val REHASH: Symbol = new Symbol("REHASH")
private final val MARKED_NULL: Marked = new Marked(null)
private final val MARKED_TRUE: Marked = new Marked(true)

private fun Any?.mark(): Marked {
   return if (`$this$mark` == null) MARKED_NULL else (if (`$this$mark` == true) MARKED_TRUE else new Marked(`$this$mark`));
}

private fun noImpl(): Nothing {
   throw new UnsupportedOperationException("not implemented");
}

@JvmSynthetic
fun `access$getREHASH$p`(): Symbol {
   return REHASH;
}

@JvmSynthetic
fun `access$mark`(`$receiver`: Any): Marked {
   return mark(`$receiver`);
}

@JvmSynthetic
fun `access$noImpl`(): Void {
   return noImpl();
}
