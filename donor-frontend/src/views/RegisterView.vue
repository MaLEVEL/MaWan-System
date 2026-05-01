<template>
  <div class="register-page">
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
    <div class="register-card">
      <h2 class="title">{{ $t('register.title') }}</h2>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="150px" @submit.prevent="handleRegister">
        <el-divider content-position="left">{{ $t('register.accountInfo') }}</el-divider>
        <el-form-item :label="$t('auth.username')" prop="username">
          <el-input v-model="form.username" :placeholder="t('auth.usernameLength')" />
        </el-form-item>
        <el-form-item :label="$t('auth.password')" prop="password">
          <el-input v-model="form.password" type="password" :placeholder="t('auth.passwordMin')" show-password />
        </el-form-item>

        <el-divider content-position="left">{{ $t('register.personalInfo') }}</el-divider>
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item :label="$t('register.firstName')" prop="firstName">
              <el-input v-model="form.firstName" :placeholder="$t('register.firstNamePlaceholder')" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('register.lastName')" prop="lastName">
              <el-input v-model="form.lastName" :placeholder="$t('register.lastNamePlaceholder')" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item :label="$t('register.dateOfBirth')" prop="dateOfBirth">
              <el-date-picker v-model="form.dateOfBirth" type="date" :placeholder="$t('register.selectDate')" style="width: 100%"
                value-format="YYYY-MM-DD" :disabled-date="d => d > new Date()" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('register.gender')" prop="gender">
              <el-select v-model="form.gender" :placeholder="$t('register.selectGender')" style="width: 100%">
                <el-option :label="$t('register.male')" value="MALE" />
                <el-option :label="$t('register.female')" value="FEMALE" />
                <el-option :label="$t('register.other')" value="OTHER" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item :label="$t('register.passportNumber')" prop="passportNumber">
          <el-input v-model="form.passportNumber" :placeholder="$t('register.passportPlaceholder')" />
        </el-form-item>
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item :label="$t('register.bloodType')" prop="bloodType">
              <el-select v-model="form.bloodType" :placeholder="$t('register.selectBloodType')" style="width: 100%">
                <el-option v-for="(label, key) in bloodTypeLabels" :key="key" :label="label" :value="key" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('register.phone')" prop="phone">
              <el-input v-model="form.phone" :placeholder="$t('register.phonePlaceholder')" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item :label="$t('register.email')" prop="email">
          <el-input v-model="form.email" :placeholder="$t('register.emailPlaceholder')" />
        </el-form-item>
        <el-form-item :label="$t('register.address')">
          <el-input v-model="form.address" :placeholder="$t('register.addressPlaceholder')" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width: 100%" :loading="loading" native-type="submit">
            {{ $t('auth.register') }}
          </el-button>
        </el-form-item>
      </el-form>
      <div class="footer-links">
        {{ $t('auth.hasAccount') }}<router-link to="/login">{{ $t('auth.loginNow') }}</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { ElMessage } from 'element-plus'
import i18n from '@/locales'

const router = useRouter()
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

const bloodTypeLabels = computed(() => ({
  I_POSITIVE: t('bloodType.I_POSITIVE'),
  I_NEGATIVE: t('bloodType.I_NEGATIVE'),
  II_POSITIVE: t('bloodType.II_POSITIVE'),
  II_NEGATIVE: t('bloodType.II_NEGATIVE'),
  III_POSITIVE: t('bloodType.III_POSITIVE'),
  III_NEGATIVE: t('bloodType.III_NEGATIVE'),
  IV_POSITIVE: t('bloodType.IV_POSITIVE'),
  IV_NEGATIVE: t('bloodType.IV_NEGATIVE')
}))

const form = reactive({
  username: '',
  password: '',
  firstName: '',
  lastName: '',
  dateOfBirth: '',
  gender: '',
  passportNumber: '',
  phone: '',
  email: '',
  address: '',
  bloodType: '',
  hlaType: ''
})

const rules = computed(() => ({
  username: [
    { required: true, message: t('auth.usernameRequired'), trigger: 'blur' },
    { min: 3, max: 50, message: t('auth.usernameLength'), trigger: 'blur' }
  ],
  password: [
    { required: true, message: t('auth.passwordRequired'), trigger: 'blur' },
    { min: 6, message: t('auth.passwordMin'), trigger: 'blur' }
  ],
  firstName: [{ required: true, message: t('register.firstNameRequired'), trigger: 'blur' }],
  lastName: [{ required: true, message: t('register.lastNameRequired'), trigger: 'blur' }],
  dateOfBirth: [{ required: true, message: t('register.dateOfBirthRequired'), trigger: 'change' }],
  gender: [{ required: true, message: t('register.genderRequired'), trigger: 'change' }],
  passportNumber: [
    { required: true, message: t('register.passportRequired'), trigger: 'blur' },
    { pattern: /^\d{8,14}$/, message: t('register.passportInvalid'), trigger: 'blur' }
  ],
  bloodType: [{ required: true, message: t('register.bloodTypeRequired'), trigger: 'change' }],
  email: [{ type: 'email', message: t('register.emailInvalid'), trigger: 'blur' }]
}))

async function handleRegister() {
  await formRef.value.validate()
  loading.value = true
  try {
    await authStore.register(form)
    ElMessage.success(t('register.registerSuccess'))
    router.push('/home')
  } catch (e) {
    // error handled by interceptor
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
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

.register-card {
  background: #fff;
  border-radius: 12px;
  padding: 40px;
  width: 560px;
  max-width: 100%;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15);
}

.title {
  text-align: center;
  margin: 0 0 20px;
  color: #303133;
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
