package com.ajinkya.formula1.domain.use_case

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FormatDateTimeUseCaseTest {

    private lateinit var formatDateTimeUseCase: FormatDateTimeUseCase

    @Before
    fun setup() {
        formatDateTimeUseCase = FormatDateTimeUseCase()
    }

    @Test
    fun testDateFormatter() {
        val date = "2010-09-15"
        val currentFormat = "yyyy-MM-dd"
        val newFormat = "dd/MM/yyyy"

        val formattedDate = formatDateTimeUseCase.invoke(date, currentFormat, newFormat)
        assertThat(formattedDate).isEqualTo("15/09/2010")
    }

    @Test
    fun testTimeFormatter() {
        val date = "03:24:35"
        val currentFormat = "HH:mm:ss"
        val newFormat = "HH:mm"

        val formattedDate = formatDateTimeUseCase.invoke(date, currentFormat, newFormat)
        assertThat(formattedDate).isEqualTo("03:24")
    }
}