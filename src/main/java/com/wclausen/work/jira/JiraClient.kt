package com.wclausen.work.jira

import com.github.michaelbull.result.get
import com.github.michaelbull.result.map
import com.squareup.moshi.Moshi
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigFileInfo
import com.wclausen.work.config.RealConfigManager
import com.wclausen.work.jira.api.model.JiraIssueTypeIdAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Base64

class JiraCreds(val username: String, val apiToken: String) {

    // Returns base64 encoded string for "username:jira_api_token"
    fun encoded(): String {
        return Base64.getEncoder().encodeToString((username + ":" + apiToken).toByteArray())
    }
}

val interceptor = object : Interceptor {
    val creds = RealConfigManager(ConfigFileInfo.configFilePath).getConfig().map {
        it.toJiraCreds()
    }.get()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Basic " + creds!!.encoded())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").build()
        return chain.proceed(request)
    }

}

private fun Config.toJiraCreds(): JiraCreds {
    return JiraCreds(jira.jira_email, jira.jira_api_token)
}

val httpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(    HttpLoggingInterceptor.Level.BODY))
    .addInterceptor(interceptor).build()

val moshi = Moshi.Builder().add(JiraIssueTypeIdAdapter()).build()

val retrofit = Retrofit.Builder().baseUrl("https://wclausen.atlassian.net/rest/api/2/")
    .addConverterFactory(MoshiConverterFactory.create(moshi)).client(httpClient).build()

val realJiraService = retrofit.create(JiraService::class.java)