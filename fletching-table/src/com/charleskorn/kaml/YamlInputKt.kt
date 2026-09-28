package com.charleskorn.kaml

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind

private final val friendlyDescription: String
   private final get() {
      return if (`$this$friendlyDescription` is StructureKind.MAP)
         "a map"
         else
         (
            if (`$this$friendlyDescription` is StructureKind.CLASS)
               "an object"
               else
               (
                  if (`$this$friendlyDescription` is StructureKind.OBJECT)
                     "an object"
                     else
                     (
                        if (`$this$friendlyDescription` is StructureKind.LIST)
                           "a list"
                           else
                           (
                              if (`$this$friendlyDescription` is PrimitiveKind.STRING)
                                 "a string"
                                 else
                                 (
                                    if (`$this$friendlyDescription` is PrimitiveKind.BOOLEAN)
                                       "a boolean"
                                       else
                                       (
                                          if (`$this$friendlyDescription` is PrimitiveKind.BYTE)
                                             "a byte"
                                             else
                                             (
                                                if (`$this$friendlyDescription` is PrimitiveKind.CHAR)
                                                   "a character"
                                                   else
                                                   (
                                                      if (`$this$friendlyDescription` is PrimitiveKind.DOUBLE)
                                                         "a double"
                                                         else
                                                         (
                                                            if (`$this$friendlyDescription` is PrimitiveKind.FLOAT)
                                                               "a float"
                                                               else
                                                               (
                                                                  if (`$this$friendlyDescription` is PrimitiveKind.INT)
                                                                     "an integer"
                                                                     else
                                                                     (
                                                                        if (`$this$friendlyDescription` is PrimitiveKind.SHORT)
                                                                           "a short"
                                                                           else
                                                                           (
                                                                              if (`$this$friendlyDescription` is PrimitiveKind.LONG)
                                                                                 "a long"
                                                                                 else
                                                                                 (
                                                                                    if (`$this$friendlyDescription` is SerialKind.ENUM)
                                                                                       "an enumeration value"
                                                                                       else
                                                                                       "a $`$this$friendlyDescription` value"
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }


@JvmSynthetic
fun `access$getFriendlyDescription`(`$receiver`: SerialKind): java.lang.String {
   return getFriendlyDescription(`$receiver`);
}
