import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { getToken, setToken, removeToken, clearAuth, setUsername, setRoles, setDonorId, getDonorId } from '@/utils/auth'
import { login as loginApi, donorRegister as registerApi } from '@/api/auth'

export const useAuthStore = defineStore('auth', () => {
  const token = ref(getToken() || '')
  const donorId = ref(getDonorId())

  const isLoggedIn = computed(() => !!token.value)

  async function login(credentials) {
    const data = await loginApi(credentials)
    token.value = data.token
    donorId.value = data.donorId || null
    setToken(data.token)
    setUsername(data.username)
    setRoles(data.roles)
    if (data.donorId) setDonorId(data.donorId)
  }

  async function register(data) {
    const res = await registerApi(data)
    token.value = res.token
    donorId.value = res.donorId
    setToken(res.token)
    setUsername(res.username)
    setRoles(['ROLE_DONOR'])
    setDonorId(res.donorId)
  }

  function logout() {
    token.value = ''
    donorId.value = null
    clearAuth()
  }

  return { token, donorId, isLoggedIn, login, register, logout }
})
