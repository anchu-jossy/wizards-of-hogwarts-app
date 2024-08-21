package com.example.wizardsofhogwarts.domain.util

/**
 * A sealed class representing the state of a resource operation.
 *
 * This class encapsulates the different states of a resource operation: loading, success, and error.
 * It helps to manage and handle data operations and their states uniformly across the application.
 *
 * @param T The type of data that the resource will handle.
 * @property data The data associated with the resource state. Can be null.
 * @property message An optional message providing additional information, typically used for error cases.
 */
sealed class Resource<T>(
    val data: T? = null,      // The data returned with the resource state.
    val message: String? = null // An optional message associated with the resource state.
) {

    /**
     * Represents the loading state of a resource operation.
     *
     * @param data The data associated with this state, can be null.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents the success state of a resource operation.
     *
     * @param data The successfully loaded data.
     */
    class Success<T>(data: T?) : Resource<T>(data)

    /**
     * Represents the error state of a resource operation.
     *
     * @param message An error message describing what went wrong.
     * @param data Optional data that may be returned with the error.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
