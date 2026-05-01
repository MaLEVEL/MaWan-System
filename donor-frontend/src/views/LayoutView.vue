<template>
  <div class="layout">
    <el-container>
      <el-header class="app-header">
        <div class="header-left">
          <h2 class="logo">{{ $t('common.appName') }}</h2>
        </div>
        <div class="header-right">
          <el-dropdown @command="switchLang" class="lang-switcher">
            <span class="lang-btn">
              🌐 {{ currentLangLabel }}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="zh">{{ $t('lang.zh') }}</el-dropdown-item>
                <el-dropdown-item command="en">{{ $t('lang.en') }}</el-dropdown-item>
                <el-dropdown-item command="ru">{{ $t('lang.ru') }}</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              <el-icon><User /></el-icon>
              {{ username }}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">{{ $t('header.personalInfo') }}</el-dropdown-item>
                <el-dropdown-item command="logout" divided>{{ $t('header.logout') }}</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" class="sidebar" v-if="!isMobile">
          <el-menu :default-active="activeMenu" router>
            <el-menu-item index="/home">
              <el-icon><HomeFilled /></el-icon>
              <span>{{ $t('nav.home') }}</span>
            </el-menu-item>
            <el-menu-item index="/appointments">
              <el-icon><Calendar /></el-icon>
              <span>{{ $t('nav.appointments') }}</span>
            </el-menu-item>
            <el-menu-item index="/donations">
              <el-icon><List /></el-icon>
              <span>{{ $t('nav.donations') }}</span>
            </el-menu-item>
            <el-menu-item index="/medical-checks">
              <el-icon><Document /></el-icon>
              <span>{{ $t('nav.medicalChecks') }}</span>
            </el-menu-item>
            <el-menu-item index="/profile">
              <el-icon><User /></el-icon>
              <span>{{ $t('nav.profile') }}</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main class="main-content">
          <router-view />
        </el-main>
      </el-container>
      <div class="mobile-nav" v-if="isMobile">
        <div
          v-for="item in navItems"
          :key="item.path"
          :class="['nav-item', { active: activeMenu === item.path }]"
          @click="$router.push(item.path)"
        >
          <el-icon :size="20"><component :is="item.icon" /></el-icon>
          <span>{{ item.label }}</span>
        </div>
      </div>
    </el-container>
  </div>
</template>

<script setup>
import { computed, ref, onMounted, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { getUsername } from '@/utils/auth'
import i18n from '@/locales'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const { t } = useI18n({ useScope: 'global' })
const locale = computed(() => i18n.global.locale.value)

const isMobile = ref(false)
const username = computed(() => getUsername())
const activeMenu = computed(() => route.path)

const currentLangLabel = computed(() => {
  const map = { zh: t('lang.zh'), en: t('lang.en'), ru: t('lang.ru') }
  return map[locale.value] || '中文'
})

const navItems = computed(() => [
  { path: '/home', icon: 'HomeFilled', label: t('nav.home') },
  { path: '/appointments', icon: 'Calendar', label: t('nav.appointments') },
  { path: '/donations', icon: 'List', label: t('nav.donations') },
  { path: '/medical-checks', icon: 'Document', label: t('nav.medicalChecks') },
  { path: '/profile', icon: 'User', label: t('nav.my') }
])

function switchLang(lang) {
  i18n.global.locale.value = lang
  localStorage.setItem('app_lang', lang)
}

function checkMobile() {
  isMobile.value = window.innerWidth <= 768
}

function handleCommand(cmd) {
  if (cmd === 'logout') {
    authStore.logout()
    router.push('/login')
  } else if (cmd === 'profile') {
    router.push('/profile')
  }
}

onMounted(() => {
  checkMobile()
  window.addEventListener('resize', checkMobile)
})

onUnmounted(() => {
  window.removeEventListener('resize', checkMobile)
})
</script>

<style scoped>
.layout {
  min-height: 100vh;
  background: #f5f7fa;
}

.app-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  height: 60px;
}

.logo {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.lang-btn {
  color: #fff;
  cursor: pointer;
  font-size: 14px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.header-right .user-info {
  color: #fff;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
}

.sidebar {
  background: #fff;
  border-right: 1px solid #e4e7ed;
}

.main-content {
  padding: 20px;
  min-height: calc(100vh - 60px);
}

.mobile-nav {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: #fff;
  border-top: 1px solid #e4e7ed;
  display: flex;
  justify-content: space-around;
  padding: 6px 0;
  z-index: 100;
}

.nav-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2px;
  font-size: 11px;
  color: #909399;
  cursor: pointer;
  transition: color 0.2s;
}

.nav-item.active {
  color: #667eea;
}
</style>
