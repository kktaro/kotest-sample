package com.example.kotestsample

class Counter(initialCount: Count) {
    var count: Count = initialCount
        private set

    init {
        if (count.value < 0) throw AssertionError("負の数は扱うことができません。")
    }

    fun increment() {
        count = Count(count.value + 1)
    }

    fun decrement() {
        val result = count.value - 1
        if (result < 0) throw AssertionError("負の数は扱うことができません。")
        count = Count(result)
    }
}
