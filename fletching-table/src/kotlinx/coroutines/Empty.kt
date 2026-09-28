package kotlinx.coroutines

private class Empty(isActive: Boolean) : Incomplete {
   public open val isActive: Boolean

   public open val list: NodeList?
      public open get() {
         return null;
      }


   init {
      this.isActive = isActive;
   }

   public override fun toString(): String {
      return "Empty{${if (this.isActive()) "Active" else "New"}}";
   }
}
