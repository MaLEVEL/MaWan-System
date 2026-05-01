import axios from 'axios'
import { getToken, removeToken } from '@/utils/auth'
import { ElMessage } from 'element-plus'
import i18n from '@/locales'
import router from '@/router'

function t(key) {
  return i18n.global.t(key)
}

// Map backend error codes to i18n keys
function getErrorMessage(error) {
  const code = error?.code
  if (code && i18n.global.te(`error.${code}`)) {
    return t(`error.${code}`)
  }
  return error?.message || t('error.requestFailed')
}

const service = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 10000
})

service.interceptors.request.use(config => {
  const token = getToken()
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.success === false) {
      const message = getErrorMessage(res.error)
      ElMessage.error(message)
      return Promise.reject(new Error(message))
    }
    return res.data
  },
  error => {
    const status = error.response?.status
    if (status === 401) {
      removeToken()
      router.push('/login')
      ElMessage.warning(t('error.tokenExpired'))
    } else if (status === 403) {
      ElMessage.error(t('error.forbidden'))
    } else if (status === 404) {
      ElMessage.error(t('error.notFound'))
    } else if (status >= 500) {
      ElMessage.error(t('error.server'))
    } else {
      const backendError = error.response?.data?.error
      ElMessage.error(getErrorMessage(backendError))
    }
    return Promise.reject(error)
  }
)

export default service
