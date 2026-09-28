package com.charleskorn.kaml

import kotlin.enums.EnumEntries

public enum class SingleLineStringStyle {
   DoubleQuoted,
   SingleQuoted,
   Plain,
   PlainExceptAmbiguous

   public final val multiLineStringStyle: MultiLineStringStyle
      public final get() {
         var var10000: MultiLineStringStyle;
         switch (SingleLineStringStyle.WhenMappings.$EnumSwitchMapping$0[this.ordinal()]) {
            case 1:
               var10000 = MultiLineStringStyle.DoubleQuoted;
               break;
            case 2:
               var10000 = MultiLineStringStyle.SingleQuoted;
               break;
            case 3:
               var10000 = MultiLineStringStyle.Plain;
               break;
            case 4:
               var10000 = MultiLineStringStyle.Plain;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }


   @JvmStatic
   fun getEntries(): EnumEntries<SingleLineStringStyle> {
      return $ENTRIES;
   }
}
