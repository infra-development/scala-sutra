trait ByteEncoder[A] {
  def encode(obj: A): Array[Byte]
}

//trait Channel {
//  def write[A](obj: A)(implicit enc:ByteEncoder[A]): Unit
//}

trait Channel {
  def write[A: ByteEncoder](obj: A): Unit
}




