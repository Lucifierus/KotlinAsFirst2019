@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean =
    number / 1000 + (number % 1000 / 100) == (number % 100 / 10) + number % 10

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y2 - y1)


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    var isLeapYear = false
    if (year % 4 !== 0) {
        isLeapYear = false
    }                //если год не делится на 4, то он не високосный
    else {                                                  //иначе
        if (year % 100 !== 0) {
            isLeapYear = true
        }       //если год не делится на 100, то он високосный
        else {                                          //иначе
            if (year % 400 == 0) {   //если год делится на 400, то он високосный
                isLeapYear = true
            }
        }
    }
    if (isLeapYear && month == 2) return 29 //в високосный год в феврале 29 дней
    if (!isLeapYear && month == 2) return 28 //не високосный год, февраль - 28 дней
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31    //в январе, марте, мае, июле, августе, октябре, декабре 31 день
        else -> 30                                               //в апреле, июне, сентябре и ноябре 30 дней
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2)) <= (r2 - r1)


/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */


fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val shorterSide = minOf(a, b, c)       //определение 2х самых коротких сторон
    val sideNumber2 = a + b + c - minOf(a, b, c) - maxOf(a, b, c)
    return shorterSide <= r && sideNumber2 <= s || shorterSide <= s && sideNumber2 <= r
}
