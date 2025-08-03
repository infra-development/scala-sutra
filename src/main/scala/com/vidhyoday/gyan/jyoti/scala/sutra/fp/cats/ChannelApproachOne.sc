import java.io.{File, FileOutputStream}
import java.nio.ByteBuffer
import scala.util.Using

trait Channel {
  def write(obj: Any): Unit
}

val projectRoot = "C:/Users/SG911PP/work/repos/gyaan-jyoti"
val filePath = s"$projectRoot/modules/scala-sutra/src/main/resources/test.txt"

object FileChannel extends Channel {
  override def write(obj: Any): Unit = {
    val bytes: Array[Byte] = obj match {
      case n: Int =>
        val byteBuffer = ByteBuffer.allocate(4)
        byteBuffer.putInt(n)
        byteBuffer.array()
      case s: String =>
        s.getBytes
      case _ => throw new IllegalArgumentException("Unsupported type")
    }

    Using(new FileOutputStream(filePath)) { os =>
      os.write(bytes)
      os.flush()
    }
  }
}

FileChannel.write("Hello")