package com.example.kotlinassignment

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FirstAssignmentTest {
    private val testFirstAssignment: FirstAssignment = FirstAssignment()

    @Test
    fun testDecimalToBase26() {
        assertEquals("A", testFirstAssignment.decimalToBase26(1))
        assertEquals("Z", testFirstAssignment. decimalToBase26(26))
        assertEquals("AZ", testFirstAssignment.decimalToBase26(52))
        assertEquals("ZZZ", testFirstAssignment.decimalToBase26(18278))
    }

    @Test
    fun testGenerateColumnLabels() {
        println(testFirstAssignment.generateColumnLabels(1, 2))
        assertArrayEquals(arrayOf("A","B"), testFirstAssignment.generateColumnLabels(1, 2))
        assertArrayEquals(arrayOf("ZZ","AAA"), testFirstAssignment.generateColumnLabels(702, 2))
        assertArrayEquals(arrayOf("ZZX","ZZY","ZZZ"), testFirstAssignment.generateColumnLabels(18276, 3))
    }
}