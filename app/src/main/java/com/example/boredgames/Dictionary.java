package com.example.boredgames;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface DictionaryService {
    @GET("{word}?key=https://dictionaryapi.com/account/example?key=86a3e9a4-37bb-4907-b197-fea25a51b330")
    Call<Object> lookupWord(@Path("word") String word);
}