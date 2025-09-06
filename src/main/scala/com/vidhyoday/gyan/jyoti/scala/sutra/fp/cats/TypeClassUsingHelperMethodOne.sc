trait ByteEncoder[A] {
  def encode(obj: A): Array[Byte]
}

object ByteEncoder {
  implicit object StringByteEncoder extends ByteEncoder[String] {
    override def encode(obj: String): Array[Byte] = obj.getBytes("UTF-8")
  }

  def summon[A](implicit ev: ByteEncoder[A]): ByteEncoder[A] = ev
  def apply[A](implicit ev: ByteEncoder[A]): ByteEncoder[A] = ev
}

implicit object Rot3StringByteEncoder extends ByteEncoder[String] {
  override def encode(obj: String): Array[Byte] = obj.getBytes.map(b => (b+3).toByte)
}

ByteEncoder.summon[String].encode("Hello")
ByteEncoder[String].encode("Hello")
