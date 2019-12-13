package utility

class ThreadStaticExt {
  inline fun <T> ThreadLocal<T>.getOr(factory: () -> T) : T {
    val result = this.get()
    if(result != null)
      return result

    val createValue = factory()
    this.set(createValue)
    return createValue
  }
}
