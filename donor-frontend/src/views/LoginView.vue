<template>
  <div class="login-page">
    <div class="lang-corner">
      <el-dropdown @command="switchLang" trigger="click">
        <span class="lang-btn">🌐 {{ currentLangLabel }}</span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="zh">中文</el-dropdown-item>
            <el-dropdown-item command="en">English</el-dropdown-item>
            <el-dropdown-item command="ru">Русский</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div class="login-card">
      <h2 class="title">{{ $t('common.appName') }}</h2>
      <el-form ref="formRef" :model="form" :rules="rules" @submit.prevent="handleLogin">
        <el-form-item prop="username">
          <el-input v-model="form.username" :placeholder="$t('auth.usernamePlaceholder')" :prefix-icon="User" size="large" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" :placeholder="$t('auth.passwordPlaceholder')" :prefix-icon="Lock" size="large" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="large" style="width: 100%" :loading="loading" native-type="submit">
            {{ $t('auth.login') }}
          </el-button>
        </el-form-item>
      </el-form>
      <div class="footer-links">
        {{ $t('auth.noAccount') }}<router-link to="/register">{{ $t('auth.registerNow') }}</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import i18n from '@/locales'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()
const { t } = useI18n({ useScope: 'global' })
const locale = computed(() => i18n.global.locale.value)

const formRef = ref()
const loading = ref(false)

const currentLangLabel = computed(() => {
  const map = { zh: '中文', en: 'English', ru: 'Русский' }
  return map[locale.value] || '中文'
})

function switchLang(lang) {
  i18n.global.locale.value = lang
  localStorage.setItem('app_lang', lang)
}

const form = reactive({
  username: '',
  password: ''
})

const rules = computed(() => ({
  username: [{ required: true, message: t('auth.usernameRequired'), trigger: 'blur' }],
  password: [{ required: true, message: t('auth.passwordRequired'), trigger: 'blur' }]
}))

async function handleLogin() {
  await formRef.value.validate()
  loading.value = true
  try {
    await authStore.login(form)
    ElMessage.success(t('auth.loginSuccess'))
    const redirect = route.query.redirect || '/home'
    router.push(redirect)
  } catch (e) {
    // error handled by interceptor
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: relative;
}

.lang-corner {
  position: absolute;
  top: 20px;
  right: 24px;
}

.lang-btn {
  color: #fff;
  cursor: pointer;
  font-size: 14px;
}

.login-card {
  background: #fff;
  border-radius: 12px;
  padding: 40px;
  width: 400px;
  max-width: 90vw;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15);
}

.title {
  text-align: center;
  margin: 0 0 30px;
  color: #303133;
  font-size: 24px;
}

.footer-links {
  text-align: center;
  color: #909399;
  font-size: 14px;
}

.footer-links a {
  color: #667eea;
  text-decoration: none;
}
</style>
