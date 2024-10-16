package com.example.photospace

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Tests for the PhotoSpace logic.
 */
@RunWith(JUnit4::class)
class PhotoSpaceLogicTest {

    /**
     * Test the onPreviousClick lambda.
     *
     * Given: The current photo index is 1, and the data length is 3
     *
     * When: The onPreviousClick lambda is called with these parameters
     *
     * Then: The lambda returns 0
     */
    @Test
    @Category(NormalCase::class)
    fun testOnPreviousClick_whenCurrentIndexIs1_and_dataLengthIs3_returns0() {
        // Given
        val currentPhotoIndex = 2
        val dataLength = 3
        val expectedIndex = 1

        // When
        val actualIndex = onPreviousClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }

    /**
     * Test the onPreviousClick lambda for upper boundary case.
     *
     * Given: The current photo index is 2, and the data length is 3
     *
     * When: The onPreviousClick lambda is called with these parameters
     *
     * Then: The lambda returns 2
     */
    @Test
    @Category(BoundaryCase::class)
    fun testOnPreviousClick_whenCurrentIndexIs2_and_dataLengthIs3_returns1() {
        // Given
        val currentPhotoIndex = 2
        val dataLength = 3
        val expectedIndex = 1

        // When
        val actualIndex = onPreviousClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }

    /**
     * Test the onPreviousClick lambda for lower boundary case.
     *
     * Given: The current photo index is 0, and the data length is 3
     *
     * When: The onPreviousClick lambda is called with these parameters
     *
     * Then: The lambda returns 2
     */
    @Test
    @Category(BoundaryCase::class)
    fun testOnPreviousClick_whenCurrentIndexIs0_and_dataLengthIs2_returns3() {
        // Given
        val currentPhotoIndex = 0
        val dataLength = 2
        val expectedIndex = 1

        // When
        val actualIndex = onPreviousClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }

    /**
     * Test the onNextClick lambda.
     *
     * Given: The current photo index is 1, and the data length is 3
     *
     * When: The onNextClick lambda is called with these parameters
     *
     * Then: The lambda returns 2
     */
    @Test
    @Category(NormalCase::class)
    fun testOnNextClick_whenCurrentIndexIs1_and_dataLengthIs3_returns2() {
        // Given
        val currentPhotoIndex = 1
        val dataLength = 3
        val expectedIndex = 2

        // When
        val actualIndex = onNextClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }

    /**
     * Test the onNextClick lambda for upper boundary case.
     *
     * Given: The current photo index is 2, and the data length is 3
     *
     * When: The onNextClick lambda is called with these parameters
     *
     * Then: The lambda returns 0
     */
    @Test
    @Category(BoundaryCase::class)
    fun testOnNextClick_whenCurrentIndexIs2_and_dataLengthIs3_returns0(){
        // Given
        val currentPhotoIndex = 2
        val dataLength = 3
        val expectedIndex = 0

        // When
        val actualIndex = onNextClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }

    /**
     * Test the onNextClick lambda for lower boundary case.
     *
     * Given: The current photo index is 0, and the data length is 3
     *
     * When: The onNextClick lambda is called with these parameters
     *
     * Then: The lambda returns 1
     */
    @Test
    @Category(BoundaryCase::class)
    fun testOnNextClick_whenCurrentIndexIs0_and_dataLengthIs2_returns1() {
        // Given
        val currentPhotoIndex = 0
        val dataLength = 2
        val expectedIndex = 1

        // When
        val actualIndex = onNextClick(currentPhotoIndex, dataLength)

        // Then
        assertEquals(expectedIndex, actualIndex)
    }
}