import com.atomicscala.AtomicTest._

// map & reduce

// 1.---------------------------------------------------------------------

// (a)

val v = Vector(1, 2, 3, 4)
v.map(n => n + 1) is Vector(2, 3, 4, 5)
v.map(n => n * 11 + 10) is Vector(21, 32, 43, 54)
/* (b)
can't replace foreach() with map(), as
foreach() works on each value and returns Unit,
while we need to transform the Vector:
map() returns a new Vector with values transformed
as per function
*/
// v.foreach(n => n * 11 + 10) is Vector(21, 32, 43, 54)

/* (c)
map approach is less complex, more concise and
less prone to errors than the for loop approach
*/

val v2 = for (i <- v.indices) yield v(i) * 11 + 10

v2 is Vector(21, 32, 43, 54)

// 2.---------------------------------------------------------------------
v.map(n => n + 1) is Vector(2, 3, 4, 5)
(for(i <- v.indices) yield v(i) + 1) is Vector(2, 3, 4, 5)

// 3.---------------------------------------------------------------------
// Reduce.scala

val r = Vector(1, 10, 100, 1000)
r.reduce((sum, n) => sum + n) is 1111

var acc: Int = 0
for(n <- r.indices) {
    acc += r(n)
    println(acc)
}
acc is 1111

// 4.---------------------------------------------------------------------

// TODO