package com.example.hellofigma.androidsmall2

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.hellofigma.R
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayImage
import com.google.relay.compose.RelayText
import com.google.relay.compose.RelayVector

/**
 * learn perk click
 *
 * This composable was generated from the UI Package 'android_small_2'.
 * Generated code; do not edit directly
 */
@Composable
fun AndroidSmall2(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        MainVideo1(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 435.0.dp
                )
            )
        )
        Rectangle4(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 627.0.dp
                )
            )
        )
        Group2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 278.0.dp,
                    y = 0.0.dp
                )
            )
        ) {
            Rectangle5()
            Class100Coins(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 5.0.dp,
                        y = 24.0.dp
                    )
                )
            )
        }
        Group1 {
            Ellipse1()
            L(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 20.0.dp,
                        y = 17.0.dp
                    )
                )
            )
        }
        IconHome2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 20.0.dp,
                    y = 640.0.dp
                )
            )
        )
        IconActivityLog2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 154.0.dp,
                    y = 646.0.dp
                )
            )
        )
        Vector3(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 287.0.dp,
                    y = 646.0.dp
                )
            )
        )
        Vector12(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 272.0.dp,
                    y = 663.0.dp
                )
            )
        )
        Group3(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 8.0.dp,
                    y = 88.0.dp
                )
            )
        ) {
            Rectangle6()
            TablerIconSearch1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 1.0.dp,
                        y = 13.0.dp
                    )
                )
            )
            CityWaterfrontsWithBridges(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 27.0.dp,
                        y = 13.0.dp
                    )
                )
            )
        }
        Rectangle7(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 9.0.dp,
                    y = 151.0.dp
                )
            )
        )
        CityBridgeWater4KAerialDroneFootage(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 134.0.dp,
                    y = 158.0.dp
                )
            )
        )
        WalkingTheBrooklynBridgeInNYCAllYouNeedToKnow(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 134.0.dp,
                    y = 247.0.dp
                )
            )
        )
    }
}

@Preview(widthDp = 360, heightDp = 690)
@Composable
private fun AndroidSmall2Preview() {
    MaterialTheme {
        RelayContainer {
            AndroidSmall2(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun MainVideo1(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_main_video_1),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(360.0.dp).requiredHeight(192.0.dp)
    )
}

@Composable
fun Rectangle4(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.android_small_2_rectangle_4),
        modifier = modifier.requiredWidth(360.0.dp).requiredHeight(88.0.dp)
    )
}

@Composable
fun Rectangle5(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.android_small_2_rectangle_5),
        modifier = modifier.requiredWidth(82.0.dp).requiredHeight(52.0.dp)
    )
}

@Composable
fun Class100Coins(modifier: Modifier = Modifier) {
    RelayText(
        content = "100 coins",
        fontSize = 16.0.sp,
        fontFamily = inter,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2102272510528564.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(94.0.dp).requiredHeight(28.0.dp)
    )
}

@Composable
fun Group2(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(99.0.dp).requiredHeight(52.0.dp)
    )
}

@Composable
fun Ellipse1(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.android_small_2_ellipse_1),
        modifier = modifier.requiredWidth(52.0.dp).requiredHeight(52.0.dp)
    )
}

@Composable
fun L(modifier: Modifier = Modifier) {
    RelayText(
        content = "L",
        fontSize = 16.0.sp,
        fontFamily = inter,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2102272510528564.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(14.0.dp).requiredHeight(32.0.dp)
    )
}

@Composable
fun Group1(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(52.0.dp).requiredHeight(52.0.dp)
    )
}

@Composable
fun IconHome2(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_icon_home_2),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(43.0.dp).requiredHeight(46.0.dp)
    )
}

@Composable
fun IconActivityLog2(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_icon_activity_log_2),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(41.0.dp).requiredHeight(37.0.dp)
    )
}

@Composable
fun Vector3(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_vector_2),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(25.0.dp).requiredHeight(17.0.dp)
    )
}

@Composable
fun Vector12(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_vector_1_2),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(58.0.dp).requiredHeight(18.0.dp)
    )
}

@Composable
fun Rectangle6(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.android_small_2_rectangle_6),
        modifier = modifier.requiredWidth(343.0.dp).requiredHeight(46.0.dp)
    )
}

@Composable
fun TablerIconSearch1(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.android_small_2_tabler_icon_search_1),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(26.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun CityWaterfrontsWithBridges(modifier: Modifier = Modifier) {
    RelayText(
        content = "City waterfronts with bridges",
        fontSize = 16.0.sp,
        fontFamily = inter,
        color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        height = 1.2102272510528564.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(247.0.dp).requiredHeight(30.0.dp)
    )
}

@Composable
fun Group3(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(343.0.dp).requiredHeight(46.0.dp)
    )
}

@Composable
fun Rectangle7(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.android_small_2_rectangle_7),
        modifier = modifier.requiredWidth(113.0.dp).requiredHeight(74.0.dp)
    )
}

@Composable
fun CityBridgeWater4KAerialDroneFootage(modifier: Modifier = Modifier) {
    RelayText(
        content = "City Bridge Water 4K Aerial \nDrone Footage",
        fontSize = 16.0.sp,
        fontFamily = inter,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2102272510528564.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun WalkingTheBrooklynBridgeInNYCAllYouNeedToKnow(modifier: Modifier = Modifier) {
    RelayText(
        content = "Walking the Brooklyn bridge \nin NYC-All you need to know",
        fontSize = 16.0.sp,
        fontFamily = inter,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2102272510528564.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(232.0.dp)
    )
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        isStructured = false,
        content = content,
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}
