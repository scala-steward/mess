package mess

import mess.derived.derivedCodecs._
import org.msgpack.core.MessagePack
import org.scalacheck.{Arbitrary, Gen, Prop, Shrink}
import org.scalatest.prop.Checkers
import org.scalatest.{Assertion, FunSuite}

class CodecChecker extends FunSuite with Checkers with MsgpackHelper {

  implicit val arbBigInt: Arbitrary[BigInt] = Arbitrary(gen.genBigInt)

  case class User[F[_]](int: Int, friends: F[User[F]])
  object User {

    def fix(depth: Int, i: Int, acc: User[List]): User[List] = {
      if (depth == 0) acc
      else fix(depth - 1, i + 1, User(i, List(acc)))
    }

    val genFix: Gen[User[List]] = for {
      depth <- Gen.chooseNum(1, 100)
      i     <- Gen.size
    } yield fix(depth, i, User[List](i, Nil))

    implicit val arbFix: Arbitrary[User[List]] = Arbitrary(genFix)
  }

  def roundTrip[A: Encoder: Decoder: Arbitrary: Shrink]: Assertion =
    check(Prop.forAll({ a: A =>
      val encode = Encoder[A]
      val decode = Decoder[A]

      val ast = encode(a)
      Codec.serialize(ast, packer)
      val unpacker =
        MessagePack.DEFAULT_UNPACKER_CONFIG.newUnpacker(packer.toByteArray)
      val b = decode(Codec.deserialize(unpacker))
      packer.clear()
      a === b.right.get
    }))

  test("Boolean")(roundTrip[Boolean])
  test("Byte")(roundTrip[Byte])
  test("Short")(roundTrip[Short])
  test("Int")(roundTrip[Int])
  test("Long")(roundTrip[Long])
  test("Float")(roundTrip[Float])
  test("Double")(roundTrip[Double])
  test("String")(roundTrip[String])
  test("BigInt")(roundTrip[BigInt])
  test("Map[String, Int]")(roundTrip[Map[String, Int]])
  test("Map[String, Long]")(roundTrip[Map[String, Long]])
  test("Map[String, Float]")(roundTrip[Map[String, Float]])
  test("Map[String, Double]")(roundTrip[Map[String, Double]])
  test("Map[String, BigInt]")(roundTrip[Map[String, BigInt]])
  test("Map[String, String]")(roundTrip[Map[String, String]])
  test("Vector[Int]")(roundTrip[Vector[Int]])
  test("Vector[Long]")(roundTrip[Vector[Long]])
  test("Vector[Float]")(roundTrip[Vector[Float]])
  test("Vector[Double]")(roundTrip[Vector[Double]])
  test("Vector[BigInt]")(roundTrip[Vector[BigInt]])
  test("Vector[String]")(roundTrip[Vector[String]])
  test("List[Int]")(roundTrip[List[Int]])
  test("List[Long]")(roundTrip[List[Long]])
  test("List[Float]")(roundTrip[List[Float]])
  test("List[Double]")(roundTrip[List[Double]])
  test("List[BigInt]")(roundTrip[List[BigInt]])
  test("List[String]")(roundTrip[List[String]])
  test("Seq[Int]")(roundTrip[Seq[Int]])
  test("Seq[Long]")(roundTrip[Seq[Long]])
  test("Seq[Float]")(roundTrip[Seq[Float]])
  test("Seq[Double]")(roundTrip[Seq[Double]])
  test("Seq[BigInt]")(roundTrip[Seq[BigInt]])
  test("Seq[String]")(roundTrip[Seq[String]])
  test("User[List]")(roundTrip[User[List]])
}
