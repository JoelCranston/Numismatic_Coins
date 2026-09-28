package kotlin.math

private object Constants {
   internal final val LN2: Double = Math.log(2.0)
   internal final val epsilon: Double = Math.ulp(1.0)
   internal final val taylor_2_bound: Double = Math.sqrt(epsilon)
   internal final val taylor_n_bound: Double = Math.sqrt(taylor_2_bound)
   internal final val upper_taylor_2_bound: Double = 1 / taylor_2_bound
   internal final val upper_taylor_n_bound: Double = 1 / taylor_n_bound
}
