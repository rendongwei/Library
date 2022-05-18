package com.don.library.extend

import android.content.Context
import android.graphics.*
import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.applyCanvas
import coil.ImageLoader
import coil.bitmap.BitmapPool
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import coil.loadAny
import coil.size.Size
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.don.library.util.FileUtil
import java.io.File
import kotlin.math.min

object ImageLoaderFactory {
    private var mImageLoader: ImageLoader? = null
    fun getImageLoader(context: Context): ImageLoader {
        if (mImageLoader == null) {
            mImageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder())
                    } else {
                        add(GifDecoder())
                    }
                    add(VideoFrameFileFetcher(context))
                    add(VideoFrameUriFetcher(context))
                }
                .allowHardware(false)
                .build()
        }
        return mImageLoader!!
    }
}

fun ImageView.loadImage(
    // 图片路径
    url: Any?,
    // 默认占位图片
    @DrawableRes
    placeHolder: Int? = null,
    // 出错占位图片
    @DrawableRes
    error: Int? = null,
    // 是否渐现
    isCrossfade: Boolean = false,
    // 渐现时长
    crossfadeDuration: Int = 300,
    // 是否圆形
    isCircle: Boolean = false,
    // 是否圆角
    isRoundCorner: Boolean = false,
    // 所有圆角弧度
    roundCorner: Float = 0f,
    // 圆角左上角弧度(px)
    roundCornerLeftTop: Float = 0f,
    // 圆角右上角弧度(px)
    roundCornerRightTop: Float = 0f,
    // 圆角左下角弧度(px)
    roundCornerLeftBottom: Float = 0f,
    // 圆角右下角弧度(px)
    roundCornerRightBottom: Float = 0f
) {
    var data = url
    if (FileUtil.isFileExist(url.toString())) {
        data = File(url.toString())
    }
    loadAny(data, ImageLoaderFactory.getImageLoader(context)) {
        placeHolder?.apply {
            placeholder(this)
        }
        error?.apply {
            error(this)
        }
        if (isCrossfade) {
            crossfade(crossfadeDuration)
        }
        if (isCircle) {
            scaleType = ImageView.ScaleType.CENTER_CROP
            transformations(CustomCircleCropTransformation())
        }

        if (isRoundCorner) {
            if (roundCorner != 0f) {
                transformations(
                    RoundedCornersTransformation(
                        roundCorner,
                        roundCorner,
                        roundCorner,
                        roundCorner
                    )
                )
            } else {
                transformations(
                    RoundedCornersTransformation(
                        roundCornerLeftTop,
                        roundCornerRightTop,
                        roundCornerLeftBottom,
                        roundCornerRightBottom
                    )
                )
            }
        }
    }
}
class CustomCircleCropTransformation : Transformation {
    override fun key(): String = CircleCropTransformation::class.java.name
    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        val minSize = min(input.width, input.height)
        val radius = minSize / 2f
        val output = pool.get(minSize, minSize, input.config?: Bitmap.Config.ARGB_8888)
        output.applyCanvas {
            drawCircle(radius, radius, radius, paint)
            paint.xfermode = XFERMODE
            drawBitmap(input, Rect((input.width - minSize) / 2, (input.height - minSize) / 2,
                    (input.width - minSize) / 2 + minSize, (input.height - minSize) / 2 + minSize),
                    Rect(0, 0, minSize, minSize), paint)
        }
        return output
    }
    override fun equals(other: Any?) = other is CircleCropTransformation

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "CircleCropTransformation()"

    private companion object {
        val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }
}