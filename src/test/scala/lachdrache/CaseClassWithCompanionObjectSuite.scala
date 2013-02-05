package lachdrache

import org.scalatest.FunSuite

class CaseClassWithCompanionObjectSuite extends FunSuite {

  case class WithoutExplicitCompanionObject(n: Int)

  test("companion object of case class WITHOUT explicit companion object extends Function1 trait") {
    // Int => WithoutExplicitCompanionObject is just syntactic sugar for Function1[Int, WithoutExplicitCompanionObject]
    assert(WithoutExplicitCompanionObject.isInstanceOf[Int => WithoutExplicitCompanionObject])
  }

  case class WithExplicitCompanionObject(n: Int)
  object WithExplicitCompanionObject {}
  test("companion object of case class WITH explicit companion object does NOT extend Function1 trait") {
    // The compiler emits following warning:
    // fruitless type test: a value of type CaseClassWithCompanionObjectSuite.this.WithExplicitCompanionObject.type cannot also be a Int => CaseClassWithCompanionObjectSuite.this.WithExplicitCompanionObject
    assert(!WithExplicitCompanionObject.isInstanceOf[Int => WithExplicitCompanionObject])
  }
}
