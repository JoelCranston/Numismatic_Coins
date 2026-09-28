package kotlinx.coroutines.flow.internal

internal fun AbortFlowException.checkOwnership(owner: Any) {
   if (`$this$checkOwnership`.owner != owner) {
      throw `$this$checkOwnership`;
   }
}

@PublishedApi
internal inline fun checkIndexOverflow(index: Int): Int {
   if (index < 0) {
      throw new ArithmeticException("Index overflow has happened");
   } else {
      return index;
   }
}
