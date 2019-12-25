@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    require(height > 0 && width > 0)
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val myList = MutableList(height) { MutableList(width) { e } } //значения

    init {
        require(height > 0 && width > 0)
    }

    override fun get(row: Int, column: Int): E = myList[row][column]

    override fun get(cell: Cell): E = myList[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        myList[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        myList[cell.row][cell.column] = value
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width && myList == other.myList

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")

        for (row in 0 until height) {
            sb.append("[")

            for (column in 0 until width) {
                sb.append(this[row, column])
                sb.append(", ")
                // Подумайте здесь про запятые и пробелы, или попробуйте использовать joinToString
            }
            sb.append("]")
        }

        sb.append("]")
        return sb.toString()
    }
}

