@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import ru.spbstu.wheels.intersperse
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = if (((age == 1) || age % 10.0 == 1.0) && age != 11 && age != 111) "$age год"   //если возраст = 1 или оканчивается на 1, кроме 11 и 111, то ГОД
else
{
    if ((age in 2..4 || age % 10 in 2..4) && age != 11 && age != 111 && age !in 5..20 && age !in 105..120) "$age года"      //если возраст в диапазоне от 2 до 4 или оканчивется на 2,3,4 и не равен 11 и 111 и не находится между 5...20 и не находится между 105...120 ГОДА
    else "$age лет"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double): Double
{
    val s: Double = (t1 * v1 + t2 * v2 + t3 * v3)    //весь путь
    var tHalf: Double = 0.0
    val sHalf: Double = s / 2.0

    if (sHalf <= t1 * v1) {tHalf = (sHalf / v1)}     //если на первой части
    else {
        if (sHalf > v1 * t1 && sHalf < (t1 * v1 + t2 * v2)) {tHalf = ((sHalf - t1 * v1) / v2 + t1)}  //если на втором промежутке
        else {tHalf = (t1 + t2 + (sHalf - t1 * v1 - t2 * v2) / v3)}     //если на третьем промежутке
    }
return tHalf
}
/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int): Int
{
    var danger: Int = 0
    if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) {danger = 3}
    if ((kingX !== rookX1 && kingY !== rookY1) && (kingX == rookX2 || kingY == rookY2)) {danger = 2}
    if ((kingX == rookX1 || kingY == rookY1) && (kingX !== rookX2 && kingY !== rookY2)) {danger = 1}
return danger
}



/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int): Int
{
    var danger: Int = 0
    val differenceX: Int = abs (bishopX - kingX)
    val differenceY: Int = abs (bishopY - kingY)
    if ((kingX == rookX || kingY == rookY) && (differenceX == differenceY)) {danger = 3}
    if ((kingX !== rookX && kingY !== rookY) && (differenceX == differenceY)) {danger = 2}
    if ((kingX == rookX || kingY == rookY) && (differenceX !== differenceY)) {danger = 1}
return danger
}


/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int
{
    var longSide: Double = 0.0
    var sideNumber2: Double = 0.0
    var sideNumber3: Double = 0.0
    var cosOfAngle: Double = 0.0
    var typeOfTriangle: Int = 0

    if (a >= b && a >= c) {longSide = a; sideNumber2 = b; sideNumber3 = c}          //*************************************
    if (b >= a && b >= c) {longSide = b; sideNumber2 = a; sideNumber3 = c}          // определение самой длинной стороны **
    if (c >= b && c >= a) {longSide = c; sideNumber2 = a; sideNumber3 = b}          //*************************************

    if (a + b > c && a + c > b && b + c > a) {
        cosOfAngle = (sideNumber2*sideNumber2 + sideNumber3*sideNumber3 - longSide * longSide)/(2*sideNumber2*sideNumber3) //определение косинуса самого большого угла
        if (cosOfAngle == 0.0) {typeOfTriangle = 1} //прямоугольный
        if (cosOfAngle > 0.0) {typeOfTriangle = 0} //остроугольный
        if (cosOfAngle < 0.0) {typeOfTriangle = 2} //тупоугольный
    }
    else {typeOfTriangle = -1} //если не выполняется условие существования
return typeOfTriangle
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
{
    var intersection: Int = 0                   //рассмотрел 5 вариантов, как могут располагаться отрезки относительно друг друга
    if (c > b || a > d) {intersection = -1}
    if (d >= b && a >= c) {intersection = b - a}
    if (b >= d && c >= a) {intersection = d - c}
    if (d > b && b > c && c > a) {intersection = b - c}
    if (b > d && d > a && a > c) {intersection = d - a}
return intersection
}
