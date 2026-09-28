package kotlin.collections

import kotlin.jvm.internal.Intrinsics

@ExperimentalUnsignedTypes
private fun partition(array: UByteArray, left: Int, right: Int): Int {
   var i: Int = left;
   var j: Int = right;
   val pivot: Byte = UByteArray.get-w2LRezQ(var0, (left + right) / 2);

   while (i <= j) {
      while (Intrinsics.compare(UByteArray.get-w2LRezQ($v$c$kotlin-UByteArray$-array$0, i) & 255, pivot & 255) < 0) {
         i++;
      }

      while (Intrinsics.compare(UByteArray.get-w2LRezQ($v$c$kotlin-UByteArray$-array$0, j) & 255, pivot & 255) > 0) {
         j--;
      }

      if (i <= j) {
         val tmp: Byte = UByteArray.get-w2LRezQ(var0, i);
         UByteArray.set-VurrAj0(var0, i, UByteArray.get-w2LRezQ(var0, j));
         UByteArray.set-VurrAj0(var0, j, tmp);
         i++;
         j--;
      }
   }

   return i;
}

@ExperimentalUnsignedTypes
private fun quickSort(array: UByteArray, left: Int, right: Int) {
   val index: Int = partition-4UcCI2c(var0, left, right);
   if (left < index - 1) {
      quickSort-4UcCI2c(var0, left, index - 1);
   }

   if (index < right) {
      quickSort-4UcCI2c(var0, index, right);
   }
}

@ExperimentalUnsignedTypes
private fun partition(array: UShortArray, left: Int, right: Int): Int {
   var i: Int = left;
   var j: Int = right;
   val pivot: Short = UShortArray.get-Mh2AYeg(var0, (left + right) / 2);

   while (i <= j) {
      while (Intrinsics.compare(UShortArray.get-Mh2AYeg($v$c$kotlin-UShortArray$-array$0, i) & '\uffff', pivot & '\uffff') < 0) {
         i++;
      }

      while (Intrinsics.compare(UShortArray.get-Mh2AYeg($v$c$kotlin-UShortArray$-array$0, j) & '\uffff', pivot & '\uffff') > 0) {
         j--;
      }

      if (i <= j) {
         val tmp: Short = UShortArray.get-Mh2AYeg(var0, i);
         UShortArray.set-01HTLdE(var0, i, UShortArray.get-Mh2AYeg(var0, j));
         UShortArray.set-01HTLdE(var0, j, tmp);
         i++;
         j--;
      }
   }

   return i;
}

@ExperimentalUnsignedTypes
private fun quickSort(array: UShortArray, left: Int, right: Int) {
   val index: Int = partition-Aa5vz7o(var0, left, right);
   if (left < index - 1) {
      quickSort-Aa5vz7o(var0, left, index - 1);
   }

   if (index < right) {
      quickSort-Aa5vz7o(var0, index, right);
   }
}

@ExperimentalUnsignedTypes
private fun partition(array: UIntArray, left: Int, right: Int): Int {
   var i: Int = left;
   var j: Int = right;
   val pivot: Int = UIntArray.get-pVg5ArA(var0, (left + right) / 2);

   while (i <= j) {
      while (Integer.compareUnsigned(UIntArray.get-pVg5ArA($v$c$kotlin-UIntArray$-array$0, i), pivot) < 0) {
         i++;
      }

      while (Integer.compareUnsigned(UIntArray.get-pVg5ArA($v$c$kotlin-UIntArray$-array$0, j), pivot) > 0) {
         j--;
      }

      if (i <= j) {
         val tmp: Int = UIntArray.get-pVg5ArA(var0, i);
         UIntArray.set-VXSXFK8(var0, i, UIntArray.get-pVg5ArA(var0, j));
         UIntArray.set-VXSXFK8(var0, j, tmp);
         i++;
         j--;
      }
   }

   return i;
}

@ExperimentalUnsignedTypes
private fun quickSort(array: UIntArray, left: Int, right: Int) {
   val index: Int = partition-oBK06Vg(var0, left, right);
   if (left < index - 1) {
      quickSort-oBK06Vg(var0, left, index - 1);
   }

   if (index < right) {
      quickSort-oBK06Vg(var0, index, right);
   }
}

@ExperimentalUnsignedTypes
private fun partition(array: ULongArray, left: Int, right: Int): Int {
   var i: Int = left;
   var j: Int = right;
   val pivot: Long = ULongArray.get-s-VKNKU(var0, (left + right) / 2);

   while (i <= j) {
      while (java.lang.Long.compareUnsigned(ULongArray.get-s-VKNKU($v$c$kotlin-ULongArray$-array$0, i), pivot) < 0) {
         i++;
      }

      while (java.lang.Long.compareUnsigned(ULongArray.get-s-VKNKU($v$c$kotlin-ULongArray$-array$0, j), pivot) > 0) {
         j--;
      }

      if (i <= j) {
         val tmp: Long = ULongArray.get-s-VKNKU(var0, i);
         ULongArray.set-k8EXiF4(var0, i, ULongArray.get-s-VKNKU(var0, j));
         ULongArray.set-k8EXiF4(var0, j, tmp);
         i++;
         j--;
      }
   }

   return i;
}

@ExperimentalUnsignedTypes
private fun quickSort(array: ULongArray, left: Int, right: Int) {
   val index: Int = partition--nroSd4(var0, left, right);
   if (left < index - 1) {
      quickSort--nroSd4(var0, left, index - 1);
   }

   if (index < right) {
      quickSort--nroSd4(var0, index, right);
   }
}

@ExperimentalUnsignedTypes
internal fun sortArray(array: UByteArray, fromIndex: Int, toIndex: Int) {
   quickSort-4UcCI2c(var0, fromIndex, toIndex - 1);
}

@ExperimentalUnsignedTypes
internal fun sortArray(array: UShortArray, fromIndex: Int, toIndex: Int) {
   quickSort-Aa5vz7o(var0, fromIndex, toIndex - 1);
}

@ExperimentalUnsignedTypes
internal fun sortArray(array: UIntArray, fromIndex: Int, toIndex: Int) {
   quickSort-oBK06Vg(var0, fromIndex, toIndex - 1);
}

@ExperimentalUnsignedTypes
internal fun sortArray(array: ULongArray, fromIndex: Int, toIndex: Int) {
   quickSort--nroSd4(var0, fromIndex, toIndex - 1);
}
