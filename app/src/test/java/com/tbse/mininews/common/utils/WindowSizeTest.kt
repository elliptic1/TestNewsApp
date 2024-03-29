/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tbse.mininews.common.utils

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

class WindowSizeTest {

    @Test
    fun getWindowSize_Compact() {
        assertEquals(
            com.tbse.mininews.common.utils.WindowSize.Compact,
            com.tbse.mininews.common.utils.getWindowSizeClass(DpSize(599.5.dp, 300.dp))
        )
    }

    @Test
    fun getWindowSize_Medium_lowEnd() {
        assertEquals(
            com.tbse.mininews.common.utils.WindowSize.Medium,
            com.tbse.mininews.common.utils.getWindowSizeClass(DpSize(800.dp, 300.dp))
        )
    }

    @Test
    fun getWindowSize_Medium_highEnd() {
        assertEquals(
            com.tbse.mininews.common.utils.WindowSize.Medium,
            com.tbse.mininews.common.utils.getWindowSizeClass(DpSize(839.5.dp, 300.dp))
        )
    }

    @Test
    fun getWindowSize_Expanded() {
        assertEquals(
            com.tbse.mininews.common.utils.WindowSize.Expanded,
            com.tbse.mininews.common.utils.getWindowSizeClass(DpSize(840.dp, 300.dp))
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun getWindowSize_Negative() {
        com.tbse.mininews.common.utils.getWindowSizeClass(DpSize((-1).dp, 300.dp))
    }
}
