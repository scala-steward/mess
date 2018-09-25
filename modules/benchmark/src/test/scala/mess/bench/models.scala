package mess.bench

import java.nio.ByteBuffer

import mess.Encoder
import mess.ast.MsgPack
import mess.codec.generic.derived._
import org.msgpack.core.MessagePack

import scala.util.Random

object models {

  private[this] val Rnd: Random = new Random()

  def packer(ast: MsgPack): Array[Byte] = {
    val p = MessagePack.DEFAULT_PACKER_CONFIG.newBufferPacker()
    ast.pack(p)
    val r = p.toByteArray
    p.close()
    r
  }

  object StringT {
    def str16: String = Rnd.nextString((1 << 16) - 1)
    def str32: String = Rnd.nextString(1 << 20)

    def str16Bytes: ByteBuffer = ByteBuffer.wrap(packer(MsgPack.MString(str16)))
    def str32Bytes: ByteBuffer = ByteBuffer.wrap(packer(MsgPack.MString(str32)))
  }

  object IntT {
    def uInt64: BigInt = (BigInt(1) << 64) - 1
    def uInt32: Long   = (1L << 32) - 1

    def uInt64Bytes: ByteBuffer =
      ByteBuffer.wrap(packer(MsgPack.MBigInt(uInt64)))
    def uInt32Bytes: ByteBuffer = ByteBuffer.wrap(packer(MsgPack.MLong(uInt32)))
  }

  case class Long10(a000000001: Long = Long.MaxValue,
                    a000000002: Long = Long.MinValue,
                    a000000003: Long = Long.MaxValue,
                    a000000004: Long = Long.MinValue,
                    a000000005: Long = Long.MaxValue,
                    a000000006: Long = Long.MinValue,
                    a000000007: Long = Long.MaxValue,
                    a000000008: Long = Long.MinValue,
                    a000000009: Long = Long.MaxValue,
                    a000000010: Long = Long.MinValue)

  object Long10 {
    def default = Long10()

    def bytes: ByteBuffer =
      ByteBuffer.wrap(packer(Encoder[Long10].apply(default)))
  }

  case class Long30(a000000001: Long = Long.MaxValue,
                    a000000002: Long = Long.MinValue,
                    a000000003: Long = Long.MaxValue,
                    a000000004: Long = Long.MinValue,
                    a000000005: Long = Long.MaxValue,
                    a000000006: Long = Long.MinValue,
                    a000000007: Long = Long.MaxValue,
                    a000000008: Long = Long.MinValue,
                    a000000009: Long = Long.MaxValue,
                    a000000010: Long = Long.MinValue,
                    a000000011: Long = Long.MaxValue,
                    a000000012: Long = Long.MinValue,
                    a000000013: Long = Long.MaxValue,
                    a000000014: Long = Long.MinValue,
                    a000000015: Long = Long.MaxValue,
                    a000000016: Long = Long.MinValue,
                    a000000017: Long = Long.MaxValue,
                    a000000018: Long = Long.MinValue,
                    a000000019: Long = Long.MaxValue,
                    a000000020: Long = Long.MinValue,
                    a000000021: Long = Long.MaxValue,
                    a000000022: Long = Long.MinValue,
                    a000000023: Long = Long.MaxValue,
                    a000000024: Long = Long.MinValue,
                    a000000025: Long = Long.MaxValue,
                    a000000026: Long = Long.MinValue,
                    a000000027: Long = Long.MaxValue,
                    a000000028: Long = Long.MinValue,
                    a000000029: Long = Long.MaxValue,
                    a000000030: Long = Long.MinValue)

  object Long30 {
    def default = Long30()

    def bytes: ByteBuffer =
      ByteBuffer.wrap(packer(Encoder[Long30].apply(default)))
  }

  case class Long60(a000000001: Long = Long.MaxValue,
                    a000000002: Long = Long.MinValue,
                    a000000003: Long = Long.MaxValue,
                    a000000004: Long = Long.MinValue,
                    a000000005: Long = Long.MaxValue,
                    a000000006: Long = Long.MinValue,
                    a000000007: Long = Long.MaxValue,
                    a000000008: Long = Long.MinValue,
                    a000000009: Long = Long.MaxValue,
                    a000000010: Long = Long.MinValue,
                    a000000011: Long = Long.MaxValue,
                    a000000012: Long = Long.MinValue,
                    a000000013: Long = Long.MaxValue,
                    a000000014: Long = Long.MinValue,
                    a000000015: Long = Long.MaxValue,
                    a000000016: Long = Long.MinValue,
                    a000000017: Long = Long.MaxValue,
                    a000000018: Long = Long.MinValue,
                    a000000019: Long = Long.MaxValue,
                    a000000020: Long = Long.MinValue,
                    a000000021: Long = Long.MaxValue,
                    a000000022: Long = Long.MinValue,
                    a000000023: Long = Long.MaxValue,
                    a000000024: Long = Long.MinValue,
                    a000000025: Long = Long.MaxValue,
                    a000000026: Long = Long.MinValue,
                    a000000027: Long = Long.MaxValue,
                    a000000028: Long = Long.MinValue,
                    a000000029: Long = Long.MaxValue,
                    a000000030: Long = Long.MinValue,
                    b000000001: Long = Long.MaxValue,
                    b000000002: Long = Long.MinValue,
                    b000000003: Long = Long.MaxValue,
                    b000000004: Long = Long.MinValue,
                    b000000005: Long = Long.MaxValue,
                    b000000006: Long = Long.MinValue,
                    b000000007: Long = Long.MaxValue,
                    b000000008: Long = Long.MinValue,
                    b000000009: Long = Long.MaxValue,
                    b000000010: Long = Long.MinValue,
                    b000000011: Long = Long.MaxValue,
                    b000000012: Long = Long.MinValue,
                    b000000013: Long = Long.MaxValue,
                    b000000014: Long = Long.MinValue,
                    b000000015: Long = Long.MaxValue,
                    b000000016: Long = Long.MinValue,
                    b000000017: Long = Long.MaxValue,
                    b000000018: Long = Long.MinValue,
                    b000000019: Long = Long.MaxValue,
                    b000000020: Long = Long.MinValue,
                    b000000021: Long = Long.MaxValue,
                    b000000022: Long = Long.MinValue,
                    b000000023: Long = Long.MaxValue,
                    b000000024: Long = Long.MinValue,
                    b000000025: Long = Long.MaxValue,
                    b000000026: Long = Long.MinValue,
                    b000000027: Long = Long.MaxValue,
                    b000000028: Long = Long.MinValue,
                    b000000029: Long = Long.MaxValue,
                    b000000030: Long = Long.MinValue)

  object Long60 {
    def default = Long60()

    def bytes: ByteBuffer =
      ByteBuffer.wrap(packer(Encoder[Long60].apply(default)))
  }
}
