package lesson4.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun sqRoots() {
        assertEquals(listOf<Double>(), sqRoots(-1.0))
        assertArrayEquals(listOf(0.0).toDoubleArray(), sqRoots(0.0).toDoubleArray(), 1e-5)
        assertArrayEquals(listOf(-5.0, 5.0).toDoubleArray(), sqRoots(25.0).toDoubleArray(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun biRoots() {
        assertEquals(listOf<Double>(), biRoots(0.0, 0.0, 1.0))
        assertEquals(listOf<Double>(), biRoots(0.0, 1.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, 2.0).toDoubleArray(),
            biRoots(0.0, 1.0, -4.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, -2.0, 4.0))
        assertArrayEquals(
            listOf(-1.0, 1.0).toDoubleArray(),
            biRoots(1.0, -2.0, 1.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, 3.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, -1.0, 1.0, 2.0).toDoubleArray(),
            biRoots(1.0, -5.0, 4.0).sorted().toDoubleArray(),
            1e-5
        )
    }

    @Test
    @Tag("Example")
    fun negativeList() {
        assertEquals(listOf<Int>(), negativeList(listOf(1, 2, 3)))
        assertEquals(listOf(-1, -5), negativeList(listOf(-1, 2, 4, -5)))
    }

    @Test
    @Tag("Example")
    fun invertPositives() {
        val list1 = mutableListOf(1, 2, 3)
        invertPositives(list1)
        assertEquals(listOf(-1, -2, -3), list1)
        val list2 = mutableListOf(-1, 2, 4, -5)
        invertPositives(list2)
        assertEquals(listOf(-1, -2, -4, -5), list2)
    }

    @Test
    @Tag("Example")
    fun squares() {
        assertEquals(listOf(0), squares(listOf(0)))
        assertEquals(listOf(1, 4, 9), squares(listOf(1, 2, -3)))
    }

    @Test
    @Tag("Example")
    fun squaresVararg() {
        assertArrayEquals(arrayOf(0), squares(0))
        assertArrayEquals(arrayOf(1, 4, 9), squares(1, 2, -3))
    }

    @Test
    @Tag("Example")
    fun isPalindrome() {
        assertFalse(isPalindrome("Барабан"))
        assertTrue(isPalindrome("А роза упала на лапу Азора"))
        assertTrue(isPalindrome("Шалаш"))
    }

    @Test
    @Tag("Example")
    fun buildSumExample() {
        assertEquals("42 = 42", buildSumExample(listOf(42)))
        assertEquals("3 + 6 + 5 + 4 + 9 = 27", buildSumExample(listOf(3, 6, 5, 4, 9)))
    }

    @Test
    @Tag("Easy")
    fun abs() {
        assertEquals(0.0, abs(listOf()), 1e-5)
        assertEquals(3.0, abs(listOf(3.0)), 1e-5)
        assertEquals(5.0, abs(listOf(3.0, -4.0)), 1e-5)
        assertEquals(8.774964, abs(listOf(4.0, -5.0, 6.0)), 1e-5)
    }

    @Test
    @Tag("Easy")
    fun mean() {
        assertEquals(0.0, mean(listOf()), 1e-5)
        assertEquals(1.0, mean(listOf(1.0)), 1e-5)
        assertEquals(2.0, mean(listOf(3.0, 1.0, 2.0)), 1e-5)
        assertEquals(3.0, mean(listOf(0.0, 2.0, 7.0, 8.0, -2.0)), 1e-5)
    }

    @Test
    @Tag("Normal")
    fun center() {
        assertEquals(listOf<Double>(), center(mutableListOf()))
        assertArrayEquals(
            listOf(0.0).toDoubleArray(),
            center(mutableListOf(3.14)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(1.0, -1.0, 0.0).toDoubleArray(),
            center(mutableListOf(3.0, 1.0, 2.0)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(-3.0, -1.0, 4.0, 5.0, -5.0).toDoubleArray(),
            center(mutableListOf(0.0, 2.0, 7.0, 8.0, -2.0)).toDoubleArray(),
            1e-5
        )
        val toMutate = mutableListOf(-3.0, -1.0, 4.0, 5.0, -5.0)
        assertTrue(toMutate === center(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("Normal")
    fun times() {
        assertEquals(0, times(listOf(), listOf()))
        assertEquals(-5, times(listOf(1, -4), listOf(3, 2)))
        assertEquals(-19, times(listOf(-1, 2, -3), listOf(3, -2, 4)))
    }

    @Test
    @Tag("Normal")
    fun polynom() {
        assertEquals(0, polynom(listOf(), 1000))
        assertEquals(42, polynom(listOf(42), -1000))
        assertEquals(13, polynom(listOf(3, 2), 5))
        assertEquals(0, polynom(listOf(2, -3, 1), 1))
        assertEquals(45, polynom(listOf(-7, 6, 4, -4, 1), -2))
        assertEquals(-1247348296, polynom(listOf(-1000, -1000, -1000, -1000, -1000, -1000), -20))
    }

    @Test
    @Tag("Normal")
    fun accumulate() {
        assertEquals(listOf<Double>(), accumulate(arrayListOf()))
        assertArrayEquals(
            listOf(3).toIntArray(),
            accumulate(arrayListOf(3)).toIntArray()
        )
        assertArrayEquals(
            listOf(1, 3, 6, 10).toIntArray(),
            accumulate(arrayListOf(1, 2, 3, 4)).toIntArray()
        )
        val toMutate = mutableListOf(-3, -1, 4, 5, -5)
        assertTrue(toMutate === accumulate(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("Normal")
    fun factorize() {
        assertEquals(listOf(2), factorize(2))
        assertEquals(listOf(3, 5, 5), factorize(75))
        assertEquals(listOf(2, 3, 3, 19), factorize(342))
    }

    @Test
    @Tag("Hard")
    fun factorizeToString() {
        assertEquals("2", factorizeToString(2))
        assertEquals("3*5*5", factorizeToString(75))
        assertEquals("2*3*3*19", factorizeToString(342))
        assertEquals("7*7*31*31*151*151", factorizeToString(1073676289))
        assertEquals("1073676287", factorizeToString(1073676287))
        assertEquals(Int.MAX_VALUE.toString(), factorizeToString(Int.MAX_VALUE))
    }

    @Test
    @Tag("Normal")
    fun convert() {
        assertEquals(listOf(1), convert(1, 2))
        assertEquals(listOf(1, 2, 1, 0), convert(100, 4))
        assertEquals(listOf(1, 3, 12), convert(250, 14))
        assertEquals(listOf(2, 14, 12), convert(1000, 19))
    }

    @Test
    @Tag("Hard")
    fun convertToString() {
        assertEquals("1", convertToString(1, 2))
        assertEquals("1210", convertToString(100, 4))
        assertEquals("13c", convertToString(250, 14))
        assertEquals("2ec", convertToString(1000, 19))
        assertEquals("z", convertToString(35, 36))
        assertEquals("a02220281", convertToString(Int.MAX_VALUE, 11))
    }

    @Test
    @Tag("Normal")
    fun decimal() {
        assertEquals(1, decimal(listOf(1), 2))
        assertEquals(100, decimal(listOf(1, 2, 1, 0), 4))
        assertEquals(250, decimal(listOf(1, 3, 12), 14))
        assertEquals(1000, decimal(listOf(2, 14, 12), 19))
    }

    @Test
    @Tag("Hard")
    fun decimalFromString() {
        assertEquals(1, decimalFromString("1", 2))
        assertEquals(100, decimalFromString("1210", 4))
        assertEquals(250, decimalFromString("13c", 14))
        assertEquals(1000, decimalFromString("2ec", 19))
        assertEquals(35, decimalFromString("z", 36))
        assertEquals(Int.MAX_VALUE, decimalFromString("a02220281", 11))
    }

    @Test
    @Tag("Hard")
    fun roman() {
        assertEquals("I", roman(1))
        assertEquals("MMM", roman(3000))
        assertEquals("MCMLXXVIII", roman(1978))
        assertEquals("DCXCIV", roman(694))
        assertEquals("XLIX", roman(49))
    }

    @Test
    @Tag("Impossible")
    fun russian() {
        assertEquals("шесть", russian(6))
        assertEquals("десять", russian(10))
        assertEquals("одиннадцать", russian(11))
        assertEquals("пятнадцать", russian(15))
        assertEquals("девятнадцать", russian(19))
        assertEquals("двадцать", russian(20))
        assertEquals("двадцать один", russian(21))
        assertEquals("двадцать два", russian(22))
        assertEquals("девяносто девять", russian(99))
        assertEquals("триста десять", russian(310))
        assertEquals("четыреста одиннадцать", russian(411))
        assertEquals("пятьсот девяносто девять", russian(599))
        assertEquals("восемьсот", russian(800))
        assertEquals("восемьсот один", russian(801))
        assertEquals("восемьсот десять", russian(810))
        assertEquals("восемьсот одиннадцать", russian(811))
        assertEquals("восемьсот двадцать один", russian(821))
        assertEquals("девятьсот девяносто девять", russian(999))
        assertEquals("девять тысяч девятьсот девяносто", russian(9990))
        assertEquals("одна тысяча", russian(1000))
        assertEquals("одна тысяча сто тридцать четыре", russian(1134))
        assertEquals("одна тысяча сто", russian(1100))
        assertEquals("пять тысяч пятьдесят пять", russian(5055))
        assertEquals("шесть тысяч восемь", russian(6008))
        assertEquals("одна тысяча один", russian(1001))
        assertEquals("две тысячи три", russian(2003))
        assertEquals("три тысячи шесть", russian(3006))
        assertEquals("четыре тысячи", russian(4000))
        assertEquals("четыре тысячи два", russian(4002))
        assertEquals("четыре тысячи семь", russian(4007))
        assertEquals("пять тысяч десять", russian(5010))
        assertEquals("шесть тысяч одиннадцать", russian(6011))
        assertEquals("семь тысяч двадцать", russian(7020))
        assertEquals("восемь тысяч тридцать пять", russian(8035))
        assertEquals("девять тысяч девятьсот девяносто девять", russian(9999))
        assertEquals("девять тысяч девяносто девять", russian(9099))
        assertEquals("девять тысяч девятьсот девять", russian(9909))
        assertEquals("девять тысяч девятьсот девяносто", russian(9990))
        assertEquals("одна тысяча десять", russian(1010))
        assertEquals("три тысячи сто", russian(3100))
        assertEquals("двадцать две тысячи девятьсот шестьдесят четыре", russian(22964))
        assertEquals("десять тысяч", russian(10000))
        assertEquals("двадцать две тысячи восемьдесят семь", russian(22087))
        assertEquals("девятьсот семь тысяч триста три", russian(907303))
        assertEquals("десять тысяч один", russian(10001))
        assertEquals("десять тысяч сто два", russian(10102))
        assertEquals("четырнадцать тысяч двести двадцать два", russian(14222))
        assertEquals("одиннадцать тысяч", russian(11000))
        assertEquals("тринадцать тысяч сто шестьдесят пять", russian(13165))
        assertEquals("четырнадцать тысяч двадцать три", russian(14023))
        assertEquals("сто девятнадцать тысяч пятьсот восемь", russian(119508))
        assertEquals("двести тысяч два", russian(200002))
        assertEquals("девятьсот тысяч", russian(900000))
        assertEquals("двенадцать", russian(12))
        assertEquals("сто двадцать одна тысяча сто двадцать один", russian(121121))
    }
}