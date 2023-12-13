package com.dicoding.toekangku1.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.data.User
import com.dicoding.toekangku1.response.RegisterResponse
import com.dicoding.toekangku1.ui.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    val registerResponse: LiveData<RegisterResponse> = repository.registerResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun register(
        tipePengguna: String,
        name: String,
        email: String,
        password: String,
        konfirmasiPassword: String,
        nik: String,
        nomorTelepon: String,
        jenisKelamin: String,
        ulangTahun: String,
        provinsi: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        kodePos: String,
        profesi: String,
        tahun_mulai_bekerja: String,
        pengalaman: Array<Experience>
    ) {
        viewModelScope.launch {
            repository.register(tipePengguna, name, email, password, konfirmasiPassword, nik, nomorTelepon, jenisKelamin, ulangTahun, provinsi, kota, kecamatan, kelurahan, kodePos, profesi, tahun_mulai_bekerja, pengalaman
            )}
    }

    fun registerPelanggan(
        tipePengguna: String,
        name: String,
        email: String,
        password: String,
        konfirmasiPassword: String,
        nik: String,
        nomorTelepon: String,
        jenisKelamin: String,
        ulangTahun: String,
        provinsi: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        kodePos: String
    ){
        viewModelScope.launch {
            repository.registerPelanggan(tipePengguna, name, email, password, konfirmasiPassword, nik, nomorTelepon, jenisKelamin, ulangTahun, provinsi, kota, kecamatan, kelurahan, kodePos)
        }

    }
}