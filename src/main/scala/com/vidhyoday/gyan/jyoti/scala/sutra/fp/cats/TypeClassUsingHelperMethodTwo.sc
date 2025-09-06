trait ByteEncoder[A] {
  def encode(obj: A): Array[Byte]
}

object ByteEncoder {
  implicit val stringByteEncoder: ByteEncoder[String] = instance[String](_.getBytes)
  
  def instance[A](f: A => Array[Byte]): ByteEncoder[A] = new ByteEncoder[A] {
    override def encode(obj: A): Array[Byte] = f(obj)
  }
}

implicit val rot3StringByteEncoder: ByteEncoder[String] = ByteEncoder.instance(_.getBytes.map(b => (b + 3).toByte))