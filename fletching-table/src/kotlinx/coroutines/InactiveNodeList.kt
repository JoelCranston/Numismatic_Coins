package kotlinx.coroutines

private class InactiveNodeList(list: NodeList) : Incomplete {
   public open val list: NodeList

   public open val isActive: Boolean
      public open get() {
         return false;
      }


   init {
      this.list = list;
   }

   public override fun toString(): String {
      return if (DebugKt.getDEBUG()) this.getList().getString("New") else super.toString();
   }
}
