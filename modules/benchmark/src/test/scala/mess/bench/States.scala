package mess.bench

import java.nio.ByteBuffer

import mess.ast.MsgPack
import mess.bench.models._
import org.msgpack.core.{MessageBufferPacker, MessagePack}
import org.openjdk.jmh.annotations.{Scope, Setup, State, TearDown}

object States {

  def newPacker: MessageBufferPacker =
    MessagePack.DEFAULT_PACKER_CONFIG.newBufferPacker()

  @State(Scope.Benchmark)
  class PackData {
    final val Str16    = StringT.str16
    final val Str32    = StringT.str32
    final val UInt32   = IntT.uInt32
    final val UInt64   = IntT.uInt64
    final val Long10CC = Long10.default
    final val Long30CC = Long30.default
    final val Long60CC = Long60.default

    var packer: MessageBufferPacker = _

    @Setup
    def open(): Unit = {
      packer = newPacker
    }

    @TearDown
    def close(): Unit = {
      packer.close()
    }
  }

  @State(Scope.Benchmark)
  class UnpackData {
    private[this] val uInt32_            = IntT.uInt32Bytes
    @inline final def uInt32: ByteBuffer = uInt32_.duplicate()

    private[this] val uInt64_            = IntT.uInt64Bytes
    @inline final def uInt64: ByteBuffer = uInt64_.duplicate()

    private[this] val str16V_            = StringT.str16Bytes
    @inline final def str16V: ByteBuffer = str16V_.duplicate()

    private[this] val str32V_            = StringT.str32Bytes
    @inline final def str32V: ByteBuffer = str32V_.duplicate()

    private[this] val long10CC_            = Long10.bytes
    @inline final def long10CC: ByteBuffer = long10CC_.duplicate()
    val long10CCP: MsgPack = {
      val unpacker = MessagePack.DEFAULT_UNPACKER_CONFIG.newUnpacker(long10CC)
      try MsgPack.unpack(unpacker)
      finally unpacker.close()
    }

    private[this] val long30CC_            = Long30.bytes
    @inline final def long30CC: ByteBuffer = long30CC_.duplicate()
    val long30CCP: MsgPack = {
      val unpacker = MessagePack.DEFAULT_UNPACKER_CONFIG.newUnpacker(long30CC)
      try MsgPack.unpack(unpacker)
      finally unpacker.close()
    }

    private[this] val long60CC_            = Long60.bytes
    @inline final def long60CC: ByteBuffer = long60CC_.duplicate()
    val long60CCP: MsgPack = {
      val unpacker = MessagePack.DEFAULT_UNPACKER_CONFIG.newUnpacker(long60CC)
      try MsgPack.unpack(unpacker)
      finally unpacker.close()
    }
  }
}
