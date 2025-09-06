import java.io.FileOutputStream
import scala.util.Using

trait ByteEncoder[A] {
  def encode(obj: A): Array[Byte]
}

trait Channel {
  def write[A: ByteEncoder](obj: A): Unit
}

implicit object StringByteEncoder extends ByteEncoder[String] {
  override def encode(obj: String): Array[Byte] = obj.getBytes("UTF-8")
}

val projectRoot = "C:/Users/SG911PP/work/repos/gyaan-jyoti"
val filePath = s"$projectRoot/modules/scala-sutra/src/main/resources/test.txt"

object FileChannel extends Channel {
  override def write[A: ByteEncoder](obj: A): Unit = {
    val enc = implicitly[ByteEncoder[A]]
    val bytes: Array[Byte] = enc.encode(obj)

    Using(new FileOutputStream(filePath)) { os =>
      os.write(bytes)
      os.flush()
    }.getOrElse(throw new RuntimeException("Failed to write to file"))
  }
}

FileChannel.write("world")

case class Switch(isOn: Boolean)
// Companion object is best place to put implicit instances
object Switch {
  implicit object SwitchByteEncoder extends ByteEncoder[Switch] {
    override def encode(obj: Switch): Array[Byte] = (if (obj.isOn) "1" else "0").getBytes
  }
}

FileChannel.write(Switch(false))