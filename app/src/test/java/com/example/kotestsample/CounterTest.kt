package com.example.kotestsample

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CounterTest : FunSpec(
    {
        lateinit var counter: Counter

        context("constructor") {
            context("正常系") {
                withData(
                    listOf(0, 100, 99999, Int.MAX_VALUE),
                ) { expect ->
                    counter = Counter(Count(expect))
                    counter.count.value shouldBe expect
                }
            }
            context("異常系") {
                withData(
                    listOf(-1, -100, Int.MIN_VALUE),
                ) { expect ->
                    shouldThrow<AssertionError> {
                        counter = Counter(Count(expect))
                        Unit
                    }
                }
            }
        }

        fun initializedCounter(count: Int): Counter {
            val newCounter = Counter(Count(count))
            newCounter.count shouldBe Count(count)
            return newCounter
        }

        context("increment") {
            test("正常系") {
                counter = initializedCounter(0)

                counter.run {
                    increment()
                    count.value shouldBe 1

                    increment()
                    count.value shouldBe 2
                }
            }
        }

        context("decrement") {
            context("正常系") {
                test("結果が0以上") {
                    counter = initializedCounter(3)

                    counter.run {
                        decrement()
                        count.value shouldBe 2

                        decrement()
                        count.value shouldBe 1

                        decrement()
                        count.value shouldBe 0
                    }
                }
            }
            context("異常系") {
                test("結果が0未満") {
                    counter = initializedCounter(0)

                    shouldThrow<AssertionError> {
                        counter.decrement()
                    }
                }
            }
        }
    },
)
