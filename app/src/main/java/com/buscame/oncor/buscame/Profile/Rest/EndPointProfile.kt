package com.buscame.oncor.buscame.Profile.Rest

import com.buscame.oncor.buscame.RestApi.ConstantsRestApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface EndPointProfile {

    @Headers("Content-Type: application/json ")
    @POST(ConstantsRestApi.UPDATE_ACCOUNT)
    fun updateDataProfile(
            @Body accountUpdateRequest: AccountUpdateRequest,
            @Header("Authorization") authorization: String

    ): Call<AccountUpdateResponse>
}