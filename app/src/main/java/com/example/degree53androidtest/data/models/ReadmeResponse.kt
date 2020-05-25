package com.example.degree53androidtest.data.models

import androidx.annotation.Keep

/**
 * Model class shaped by the github's Json response to repository search
 */
@Keep
class ReadmeResponse(val name: String, val content: String? = null)
