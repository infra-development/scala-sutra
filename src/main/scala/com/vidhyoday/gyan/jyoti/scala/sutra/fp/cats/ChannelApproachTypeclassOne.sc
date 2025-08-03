import java.io.FileOutputStream
import scala.util.Using

trait ByteEncoder[A] {
  def encode(obj: A): Array[Byte]
}
trait Channel {
  def write[A](obj: A, enc: ByteEncoder[A]): Unit
}

val projectRoot = "C:/Users/SG911PP/work/repos/gyaan-jyoti"
val filePath = s"$projectRoot/modules/scala-sutra/src/main/resources/test.txt"

object FileChannel extends Channel {
  override def write[A](obj: A, enc: ByteEncoder[A]): Unit = {
    val bytes = enc.encode(obj)
    Using(new FileOutputStream(filePath, true)) { os =>
      os.write(bytes)
      os.flush()
    }.getOrElse(throw new RuntimeException("Failed to write to file"))
  }
}

object IntEncoder extends ByteEncoder[Int] {
  override def encode(obj: Int): Array[Byte] = {
    val byteBuffer = java.nio.ByteBuffer.allocate(4)
    byteBuffer.putInt(obj)
    byteBuffer.array()
  }
}

object StringEncoder extends ByteEncoder[String] {
  override def encode(obj: String): Array[Byte] = {
    obj.getBytes("UTF-8")
  }
}

FileChannel.write(4, IntEncoder)
FileChannel.write[String]("String", StringEncoder)