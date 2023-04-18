package com.goodboards.tests.integration

import client.ClientUtil
import io.ktor.client.request.*
import kotlinx.html.HTML

class FormActions(val applicationUrl: String) {

    suspend fun getFormPage() = ClientUtil.getClient().get<HTML>(host = applicationUrl, path = "form")

    suspend fun checkFormPage() = getFormPage()

}