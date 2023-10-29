
fun main() {
    val book = Book("JN VT", "VT", 100.0)
    val ebook = EBook("VT YE", "VT VT", 70.0, "pdf", "public")

    book.read()
    ebook.read()
    println(book.title)
}

open class Book(val title: String, val author: String, val price: Double) {
    open fun read() {
        println("Reading Paper book")
    }
}

class EBook(title: String, author: String, price: Double,
            val pdf: String, val epub: String) : Book(title = title, author = author, price = price) {
                override fun read() {
                    println("Read from Electronic Device")
                }
            }