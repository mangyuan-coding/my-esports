import { defineStore } from 'pinia'
import { ref } from 'vue'
import { loginUser, registerUser, logoutUser } from '@/api/auth'
import router from '@/router'

export const useAuthStore = defineStore('auth', () => {
    const user = ref(JSON.parse(localStorage.getItem('user')))
    const isAuthenticated = ref(!!user.value)

    const login = async (credentials) => {
        try {
            const response = await loginUser(credentials)
            user.value = response.data
            isAuthenticated.value = true
            localStorage.setItem('user', JSON.stringify(response.data))
            router.push('/dashboard')
        } catch (error) {
            throw error
        }
    }

    const register = async (userData) => {
        try {
            const response = await registerUser(userData)
            user.value = response.data
            isAuthenticated.value = true
            localStorage.setItem('user', JSON.stringify(response.data))
            router.push('/dashboard')
        } catch (error) {
            throw error
        }
    }

    const logout = async () => {
        try {
            await logoutUser()
            user.value = null
            isAuthenticated.value = false
            localStorage.removeItem('user')
            router.push('/login')
        } catch (error) {
            console.error('Logout error:', error)
        }
    }

    return { user, isAuthenticated, login, register, logout }
})