package com.dicoding.toekangku1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            val tipePengguna = preferences[USERTYPE_KEY] ?: ""
            val nama = preferences[NAME_KEY] ?: ""
            val email = preferences[EMAIL_KEY] ?: ""
            val password = preferences[PASSWORD_KEY] ?: ""
            val konfirmasiPassword = preferences[CONFIRM_PASSWORD_KEY] ?: ""
            val nik = preferences[NIK_KEY] ?: ""
            val nomorTelpon = preferences[NOTELP_KEY] ?: ""
            val jenisKelamin = preferences[GENDER_KEY] ?: ""
            val ulangTahun = preferences[BIRTHDAY_KEY] ?: ""
            val provinsi = preferences[PROVINCE_KEY] ?: ""
            val kota = preferences[CITY_KEY] ?: ""
            val kecamatan = preferences[KECAMATAN_KEY] ?: ""
            val kelurahan = preferences[KELURAHAN_KEY] ?: ""
            val kodePos = preferences[POSTCODE_KEY] ?: ""
            val profesi = preferences[PROFESI_KEY] ?: ""
            val tahunMulaiBekerja = preferences[YEAR_KEY] ?: ""
            val token = preferences[TOKEN_KEY] ?: ""

            // Get experiences from JSON
            val experiencesJson = preferences[EXPERIENCES_KEY] ?: ""
            val experiencesType = object : TypeToken<Array<Experience>>() {}.type
            val pengalaman = Gson().fromJson<Array<Experience>>(experiencesJson, experiencesType) ?: emptyArray()

            User(
                tipePengguna = tipePengguna,
                nama = nama,
                email = email,
                password = password,
                konfirmasiPassword = konfirmasiPassword,
                nik = nik,
                nomorTelpon = nomorTelpon,
                jenisKelamin = jenisKelamin,
                ulangTahun = ulangTahun,
                provinsi = provinsi,
                kota = kota,
                kecamatan = kecamatan,
                kelurahan = kelurahan,
                kodePos = kodePos,
                profesi = profesi,
                tahunMulaiBekerja = tahunMulaiBekerja,
                pengalaman = pengalaman,
                token = token
            )
        }
    }

    suspend fun saveSession(user: User) {
        dataStore.edit { preferences ->
            preferences[USERTYPE_KEY] = user.tipePengguna
            preferences[NAME_KEY] = user.nama
            preferences[EMAIL_KEY] = user.email
            preferences[PASSWORD_KEY] = user.password
            preferences[CONFIRM_PASSWORD_KEY] = user.konfirmasiPassword
            preferences[NIK_KEY] = user.nik
            preferences[NOTELP_KEY] = user.nomorTelpon
            preferences[GENDER_KEY] = user.jenisKelamin
            preferences[BIRTHDAY_KEY] = user.ulangTahun
            preferences[PROVINCE_KEY] = user.provinsi
            preferences[CITY_KEY] = user.kota
            preferences[KECAMATAN_KEY] = user.kecamatan
            preferences[KELURAHAN_KEY] = user.kelurahan
            preferences[POSTCODE_KEY] = user.kodePos
            preferences[PROFESI_KEY] = user.profesi
            preferences[YEAR_KEY] = user.tahunMulaiBekerja

            val experiencesJson = Gson().toJson(user.pengalaman)
            preferences[EXPERIENCES_KEY] = experiencesJson

            preferences[TOKEN_KEY] = user.token
        }
    }

//    fun getSessionLogin() : Flow<Login>{
//        return dataStore.data.map { prefeferences ->
//            Login(
//                prefeferences[EMAIL_KEY] ?: "",
//                prefeferences[PASSWORD_KEY] ?: ""
//            )
//        }
//    }
//
//    suspend fun saveSessionLogin(submit: SubmitOTP){
//        dataStore.edit { preferences ->
//            preferences[EMAIL_KEY] = submit.email
//            preferences[PASSWORD_KEY] = submit.secret
//        }
//    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun login(){
        dataStore.edit { preferences ->
            preferences[IS_LOGIN_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val USERTYPE_KEY = stringPreferencesKey("tipePengguna")
        private val NAME_KEY = stringPreferencesKey("nama")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val CONFIRM_PASSWORD_KEY = stringPreferencesKey("konfirmasiPassword")
        private val NIK_KEY = stringPreferencesKey("nik")
        private val NOTELP_KEY = stringPreferencesKey("nomorTelepon")
        private val GENDER_KEY = stringPreferencesKey("jenisKelamin")
        private val BIRTHDAY_KEY = stringPreferencesKey("ulangTahun")
        private val PROVINCE_KEY = stringPreferencesKey("provinsi")
        private val CITY_KEY = stringPreferencesKey("kota")
        private val KECAMATAN_KEY = stringPreferencesKey("kecamatan")
        private val KELURAHAN_KEY = stringPreferencesKey("kelurahan")
        private val POSTCODE_KEY = stringPreferencesKey("kodePos")
        private val PROFESI_KEY = stringPreferencesKey("profesi")
        private val YEAR_KEY = stringPreferencesKey("tahunMulaiBekerja")
        private val EXPERIENCES_KEY = stringPreferencesKey("pengalaman")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}