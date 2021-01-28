package kz.moviedb.presentation.model

/**
 * Created by Sarsenov Yerlan on 28.01.2021.
 */
class RatingRecognizerHelper {
    fun getValueFloat(value: String): Float {
        if (value.contains("%")) {
            return try {
                value.substring(0, value.length-1).toFloat()
            } catch (e: Exception) {
                0F
            }
        }
        return try {
            val nums = value.split('/').map { it.toFloat() }
            if (nums[1] == 100.toFloat()) {
                nums[0].toFloat()
            } else {
                (nums[0] * 10).toFloat()
            }
        } catch (e: Exception) {
            0F
        }
    }
}